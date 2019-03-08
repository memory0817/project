package com.lkh.myapp.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lkh.myapp.gboard.dto.GbbsDTO;
import com.lkh.myapp.gboard.service.GbbsSvc;
import com.lkh.myapp.util.RecordCriteria;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("/gboard")
public class Gboard_bbsController {
	private static Logger logger = LoggerFactory.getLogger(Gboard_bbsController.class);

	@Inject
	GbbsSvc gbbssvc;

	// 게시글 등록양식-write_form
	@RequestMapping("/write")
	public String write(Model model) {
		logger.info("String write 호출됨:");
		GbbsDTO gbbsdto = new GbbsDTO();
		model.addAttribute("gbbsDTO", gbbsdto);
		return "/gboard/gbbswrite";
	}

	// 게시글 등록처리
	@RequestMapping("/writeOK")
	public String writeOK(@Valid @ModelAttribute("gbbsDTO") GbbsDTO gbbsDTO, BindingResult result, Model model,
			HttpSession session) {
		logger.info("String writeOK 호출됨:" + gbbsDTO);

		int cnt = 0;
		if (result.hasErrors()) {
			logger.info(result.toString());
			return "/gboard/writeForm";
		}

		try {
			cnt = gbbssvc.write(gbbsDTO);
			logger.info("게시글 등록건수 :" + cnt);

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("일반게시판글테스트:" + gbbsDTO);
		return "redirect:/gboard/list";
	}

	// 게시글 보기유효성체크-read.jsp
	@RequestMapping("/view")
	public String view(@ModelAttribute("gbbsDTO") GbbsDTO gbbsDTO, Model model, HttpServletRequest request) {
		logger.info("String view 호출됨:" + gbbsDTO);

		RecordCriteria rc = null;
		try {
			rc = new RecordCriteria(Integer.parseInt(request.getParameter("reqPage")));
			gbbsDTO = gbbssvc.view(request.getParameter("num"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("gbbsDTO", gbbsDTO);
		model.addAttribute("rc", rc);

		return "/gboard/gbbsread2";
	}
	
	//게시글 보기 성공했을때
	@RequestMapping("/viewok")
	public String viewok(@ModelAttribute("gbbsDTO") GbbsDTO gbbsDTO, Model model, HttpServletRequest request) {
		logger.info("String view 호출됨:" + gbbsDTO);

		RecordCriteria rc = null;
		try {
			rc = new RecordCriteria(Integer.parseInt(request.getParameter("reqPage")));
			gbbsDTO = gbbssvc.view(request.getParameter("num"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("gbbsDTO", gbbsDTO);
		model.addAttribute("rc", rc);

		return "/gboard/gbbsread";
	}
	
	// 게시글 수정양식
		@RequestMapping("/modify/{num}/{reqPage}")
		public String modify(@Valid @ModelAttribute("gbbsDTO") GbbsDTO gbbsDTO, BindingResult result,
				@PathVariable("num") String num, @PathVariable("reqPage") String reqPage, Model model) {
			RecordCriteria rc = null;
			try {
				rc = new RecordCriteria(Integer.parseInt(reqPage));
				gbbsDTO = gbbssvc.view(num);
			} catch (Exception e) {
				e.printStackTrace();
			}
			model.addAttribute("gbbsDTO", gbbsDTO);
			model.addAttribute("rc", rc);

			return "/gboard/gbbsmodify";
		}
	// 게시글 수정양식
	@RequestMapping("/modifyOK")
	public String modifyOK(@Valid @ModelAttribute GbbsDTO gbbsDTO, BindingResult result) {

		if (result.hasErrors()) {

			return "/gboard/read";

		}
		try {
			gbbssvc.modify(gbbsDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "forward:/gboard/list";
	}

	// 게시글 삭제처리
	@RequestMapping("/delete")
	public String delete(@RequestParam("num") String num, @RequestParam("reqPage") String reqPage) {
		logger.info(num);
		try {
			gbbssvc.delete(num);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/gboard/list?reqPage=" + reqPage;
	}

	// 게시글 목록보기-list.jsp
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model) {

		try {
			gbbssvc.list(request, model);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/gboard/gbbslist";

	}

}
