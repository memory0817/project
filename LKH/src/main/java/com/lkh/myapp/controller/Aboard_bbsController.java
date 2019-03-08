package com.lkh.myapp.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lkh.myapp.aboard.dto.A_bbsDTO;
import com.lkh.myapp.aboard.service.A_bbsSvc;
import com.lkh.myapp.util.RecordCriteria;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@PreAuthorize("hasAnyRole('HR','SALE','IT','TEST','ADMIN')")
@RequestMapping("/Aboard")
public class Aboard_bbsController {

	private static Logger logger = LoggerFactory.getLogger(Aboard_bbsController.class);

	@Inject
	A_bbsSvc asvc;

	// 게시글 등록양식-write_form
	@RequestMapping("/write")
	public String write(Model model) {
		A_bbsDTO abbsdto = new A_bbsDTO();
		model.addAttribute("abbsdto", abbsdto);
		return "/Aboard/abbswrite";
	}

	// 게시글 등록처리
	@RequestMapping("/writeOK")
	public String writeOK(@Valid @ModelAttribute("abbsdto") A_bbsDTO abbsdto, BindingResult result, Model model,
			HttpSession session) {
		logger.info("String writeOK 호출됨:" + abbsdto);

		int cnt = 0;
		if (result.hasErrors()) {
			logger.info(result.toString());
			return "/Aboard/list";
		}
		/*
		 * MemberDTO mdto = (MemberDTO)session.getAttribute("user"); // 사용자 세션정보가 없는경우
		 * if(mdto == null) { return "redirect:/login/loginForm"; }
		 */
		try {

			cnt = asvc.write(abbsdto);
			logger.info("게시글 등록건수 :" + cnt);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/Aboard/list";
	}

	// 게시글 답글양식-reply_form.jsp
	@RequestMapping("/replyForm/{num}/{reqPage}")
	public String reply(@PathVariable("num") String num, @PathVariable("reqPage") String reqPage, Model model) {

		RecordCriteria rc = null;
		A_bbsDTO abbsdto = null;

		try {
			rc = new RecordCriteria(Integer.parseInt(reqPage));
			abbsdto = asvc.replyView(num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("abbsdto", abbsdto);
		model.addAttribute("rc", rc);
		return "/Aboard/abbsreply";
	}

	// 게시글 답글처리
	@RequestMapping("/replyOK")
	public String replyOK(@Valid @ModelAttribute("abbsdto") A_bbsDTO abbsdto, BindingResult result,
			HttpSession session, @RequestParam("reqPage") String reqPage) {
		logger.info("String replyOK 호출됨:" + abbsdto);

		int cnt = 0;

		if (result.hasErrors()) {

			return "redirect:/Aboard/replyForm";
		}

		try {
			cnt = asvc.reply(abbsdto);
			logger.info("게시글 답글 등록건수 :" + cnt);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/Aboard/list?reqPage=" + reqPage;
	}

	// 게시글 보기-read.jsp
	@RequestMapping("/view")
	public String view(@ModelAttribute("abbsdto") A_bbsDTO abbsdto, Model model, HttpServletRequest request) {

		RecordCriteria rc = null;
		try {
			rc = new RecordCriteria(Integer.parseInt(request.getParameter("reqPage")));
			abbsdto = asvc.view(request.getParameter("num"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("abbsdto", abbsdto);
		model.addAttribute("rc", rc);

		return "/Aboard/abbsread";
	}

	// 게시글 수정양식
	@RequestMapping("/modifyform/{num}/{reqPage}")
	public String noticemodify(@Valid @ModelAttribute("abbsdto") A_bbsDTO abbsdto, BindingResult result,
			@PathVariable("num") String num, @PathVariable("reqPage") String reqPage, Model model) {
		RecordCriteria rc = null;
		try {
			rc = new RecordCriteria(Integer.parseInt(reqPage));
			abbsdto = asvc.view(num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("abbsdto", abbsdto);
		model.addAttribute("rc", rc);

		return "/Aboard/abbsmodify";
	}

	// 게시글 수정처리
	@RequestMapping("/modifyok")
	public String noticemodifok(@Valid @ModelAttribute A_bbsDTO abbsdto, BindingResult result) {
		int cnt = 0;
		try {
			cnt = asvc.modify(abbsdto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("수정건수 : " + cnt);

		return "redirect:/Aboard/list";
	}

	// 게시글 삭제처리
	@RequestMapping("/delete")
	public String delete(@RequestParam("num") String num, @RequestParam("reqPage") String reqPage) {

		try {
			asvc.delete(num);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/Aboard/list?reqPage=" + reqPage;
	}

	// 게시글 목록보기-list.jsp
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model) {

		try {
			asvc.list(request, model);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/Aboard/abbslist";

	}

}