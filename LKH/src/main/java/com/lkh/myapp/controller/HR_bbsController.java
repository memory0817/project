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

import com.lkh.myapp.hr.dto.HR_bbsDTO;
import com.lkh.myapp.hr.service.HR_bbsSvc;
import com.lkh.myapp.util.RecordCriteria;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@PreAuthorize("hasAnyRole('ROLE_HR','ROLE_ADMIN')")
@RequestMapping("/HR")
public class HR_bbsController {
	
	private static Logger logger = LoggerFactory.getLogger(HR_bbsController.class);
	
	@Inject
	HR_bbsSvc hrsvc;
	
	
	
	//게시글 등록양식-write_form
	@RequestMapping("/write")
	public String write(Model model) {
		HR_bbsDTO hrbbsdto = new HR_bbsDTO();
		model.addAttribute("hr_bbsDTO",hrbbsdto);		
		return "/HR/HRbbswrite";		
	}
	
	
	
	//게시글 등록처리
	@RequestMapping("/writeOK")
	public String writeOK(@Valid @ModelAttribute("hr_bbsDTO") HR_bbsDTO hr_bbsDTO, BindingResult result, Model model, HttpSession session) {
		logger.info("String writeOK 호출됨:" + hr_bbsDTO);
		
		int cnt = 0;
		if(result.hasErrors()) {
			logger.info(result.toString());
			return "/HR/HRbbswrite";
		}
/*		MemberDTO mdto = (MemberDTO)session.getAttribute("user");
		// 사용자 세션정보가 없는경우
		if(mdto == null) {
			return "redirect:/login/loginForm";
		}*/
		try {
						
			cnt = hrsvc.write(hr_bbsDTO);
			logger.info("게시글 등록건수 :" + cnt);
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return "redirect:/HR/list";
	}	
	
	//게시글 답글양식-reply_form.jsp
	@RequestMapping("/replyForm/{num}/{reqPage}")
	public String reply(
			@PathVariable("num") String num,
			@PathVariable("reqPage") String reqPage,
			Model model) {
		
		RecordCriteria rc = null;
		HR_bbsDTO hrbbsdto = null;
		
		
		try {
			rc = new RecordCriteria(Integer.parseInt(reqPage));
			hrbbsdto = hrsvc.replyView(num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("hr_bbsDTO",hrbbsdto);
		model.addAttribute("rc",rc);
		return "/HR/HRbbsreply";
	}
	
	
	//게시글 답글처리
	@RequestMapping("/replyOK")
	public String replyOK(@Valid @ModelAttribute("hr_bbsDTO") HR_bbsDTO hrbbsdto, 
			BindingResult result, HttpSession session, @RequestParam("reqPage") String reqPage) {
		logger.info("String replyOK 호출됨:" + hrbbsdto);
		
		int cnt = 0;
		
		if(result.hasErrors()) {
			
			return "redirect:/HR/replyForm";
		}
		
		try {			
			cnt = hrsvc.reply(hrbbsdto);
			logger.info("게시글 답글 등록건수 :" + cnt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/HR/list?reqPage="+reqPage;
	}
	
	
	
	
	//게시글 보기-read.jsp
	@RequestMapping("/view")
	public String view(@ModelAttribute("hr_bbsDTO") HR_bbsDTO hr_bbsDTO, Model model, HttpServletRequest request){
		
		
		RecordCriteria rc = null;
		try {
			rc = new RecordCriteria(Integer.parseInt(request.getParameter("reqPage")));
			hr_bbsDTO = hrsvc.view(request.getParameter("num"));
		} catch (Exception e) {
			e.printStackTrace();
		}		
		model.addAttribute("hr_bbsDTO",hr_bbsDTO);
		model.addAttribute("rc",rc);
		
		return "/HR/HRbbsread";
	}
	
	//게시글 수정양식
		@RequestMapping("/modifyform/{num}/{reqPage}")
		public String noticemodify(@Valid @ModelAttribute("hr_bbsDTO") HR_bbsDTO hr_bbsDTO, BindingResult result, 
				 @PathVariable("num") String num,@PathVariable("reqPage") String reqPage, Model model,HttpServletRequest request) {
			RecordCriteria rc = null;
			try {
				rc = new RecordCriteria(Integer.parseInt(reqPage));
				hr_bbsDTO = hrsvc.view(num);
			} catch (Exception e) {
				e.printStackTrace();
			}		
			model.addAttribute("hr_bbsDTO",hr_bbsDTO);
			model.addAttribute("rc",rc);
			
			return "/HR/HRbbsmodify";
		}
		
		//게시글 수정처리
			@RequestMapping("/modifyok")
			public String noticemodifok(@Valid @ModelAttribute HR_bbsDTO hr_bbsDTO, BindingResult result) {
				int cnt = 0;
				try {
					cnt = hrsvc.modify(hr_bbsDTO);
				} catch (Exception e) {
					e.printStackTrace();
				}		
				logger.info("수정건수 : "+cnt);
				
				return "redirect:/HR/list";
			}
	
	
	
	
	//게시글 삭제처리
	@RequestMapping("/delete")
	public String delete(@RequestParam("num") String num,
			@RequestParam("reqPage") String reqPage) {
		
		try {
			hrsvc.delete(num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/HR/list?reqPage="+reqPage;
		}
	
	
	
	//게시글 목록보기-list.jsp
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model) {
		
		try {
			hrsvc.list(request, model);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return "/HR/HRbbslist";
		
	}
	
	
	
	
	

}