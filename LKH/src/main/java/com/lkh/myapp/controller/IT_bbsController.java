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

import com.lkh.myapp.IT.dto.IT_bbsDTO;
import com.lkh.myapp.IT.service.IT_bbsSvc;
import com.lkh.myapp.util.RecordCriteria;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@PreAuthorize("hasAnyRole('ROLE_IT','ROLE_ADMIN')")
@RequestMapping("/IT")
public class IT_bbsController {
	
	private static Logger logger = LoggerFactory.getLogger(IT_bbsController.class);
	
	@Inject
	IT_bbsSvc itsvc;
	
	
	
	//게시글 등록양식-write_form

	@RequestMapping("/write")
	public String write(Model model) {
		IT_bbsDTO itbbsdto = new IT_bbsDTO();
		model.addAttribute("it_bbsDTO",itbbsdto);		
		return "/IT/ITbbswrite";		
	}
	
	
	
	//게시글 등록처리
	@RequestMapping("/writeOK")
	public String writeOK(@Valid @ModelAttribute("it_bbsDTO") IT_bbsDTO it_bbsDTO, BindingResult result, Model model, HttpSession session) {
		logger.info("String writeOK 호출됨:" + it_bbsDTO);
		
		int cnt = 0;
		if(result.hasErrors()) {
			logger.info(result.toString());
			return "/IT/ITbbswrite";
		}
/*		MemberDTO mdto = (MemberDTO)session.getAttribute("user");
		// 사용자 세션정보가 없는경우
		if(mdto == null) {
			return "redirect:/login/loginForm";
		}*/
		try {
						
			cnt = itsvc.write(it_bbsDTO);
			logger.info("게시글 등록건수 :" + cnt);
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return "redirect:/IT/list";
	}	
	
	//게시글 답글양식-reply_form.jsp
	@RequestMapping("/replyForm/{num}/{reqPage}")
	public String reply(
			@PathVariable("num") String num,
			@PathVariable("reqPage") String reqPage,
			Model model) {
		
		RecordCriteria rc = null;
		IT_bbsDTO itbbsdto = null;
		
		
		try {
			rc = new RecordCriteria(Integer.parseInt(reqPage));
			itbbsdto = itsvc.replyView(num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("it_bbsDTO",itbbsdto);
		model.addAttribute("rc",rc);
		return "/IT/ITbbsreply";
	}
	
	
	//게시글 답글처리
	@RequestMapping("/replyOK")
	public String replyOK(@Valid @ModelAttribute("it_bbsDTO") IT_bbsDTO itbbsdto, 
			BindingResult result, HttpSession session, @RequestParam("reqPage") String reqPage) {
		logger.info("String replyOK 호출됨:" + itbbsdto);
		
		int cnt = 0;
		
		if(result.hasErrors()) {
			
			return "redirect:/IT/replyForm";
		}
		
		try {			
			cnt = itsvc.reply(itbbsdto);
			logger.info("게시글 답글 등록건수 :" + cnt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/IT/list?reqPage="+reqPage;
	}
	
	
	
	
	//게시글 보기-read.jsp
	@RequestMapping("/view")
	public String view(@ModelAttribute("it_bbsDTO") IT_bbsDTO it_bbsDTO, Model model, HttpServletRequest request){
		
		
		RecordCriteria rc = null;
		try {
			rc = new RecordCriteria(Integer.parseInt(request.getParameter("reqPage")));
			it_bbsDTO = itsvc.view(request.getParameter("num"));
		} catch (Exception e) {
			e.printStackTrace();
		}		
		model.addAttribute("it_bbsDTO",it_bbsDTO);
		model.addAttribute("rc",rc);
		
		return "/IT/ITbbsread";
	}
	
	//게시글 수정양식
		@RequestMapping("/modifyform/{num}/{reqPage}")
		public String noticemodify(@Valid @ModelAttribute("it_bbsDTO") IT_bbsDTO it_bbsDTO, BindingResult result, 
				 @PathVariable("num") String num,@PathVariable("reqPage") String reqPage, Model model,HttpServletRequest request) {
			RecordCriteria rc = null;
			try {
				rc = new RecordCriteria(Integer.parseInt(reqPage));
				it_bbsDTO = itsvc.view(num);
			} catch (Exception e) {
				e.printStackTrace();
			}		
			model.addAttribute("it_bbsDTO",it_bbsDTO);
			model.addAttribute("rc",rc);
			
			return "/IT/ITbbsmodify";
		}
		
		//게시글 수정처리
			@RequestMapping("/modifyok")
			public String noticemodifok(@Valid @ModelAttribute IT_bbsDTO it_bbsDTO, BindingResult result) {
				int cnt = 0;
				try {
					cnt = itsvc.modify(it_bbsDTO);
				} catch (Exception e) {
					e.printStackTrace();
				}		
				logger.info("수정건수 : "+cnt);
				
				return "redirect:/IT/list";
			}
	
	
	
	
	//게시글 삭제처리
	@RequestMapping("/delete")
	public String delete(@RequestParam("num") String num,
			@RequestParam("reqPage") String reqPage) {
		
		try {
			itsvc.delete(num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/IT/list?reqPage="+reqPage;
		}
	
	
	
	//게시글 목록보기-list.jsp
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model) {
		
		try {
			itsvc.list(request, model);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return "/IT/ITbbslist";
		
	}
	
	
	
	
	

}