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

import com.lkh.myapp.test.dto.TEST_bbsDTO;
import com.lkh.myapp.test.service.TEST_bbsSvc;
import com.lkh.myapp.util.RecordCriteria;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@PreAuthorize("hasAnyRole('ROLE_TEST','ROLE_ADMIN')")
@RequestMapping("/TEST")
public class TEST_bbsController {
	
	private static Logger logger = LoggerFactory.getLogger(TEST_bbsController.class);
	
	@Inject
	TEST_bbsSvc testsvc;
	
	
	
	//게시글 등록양식-write_form
	@RequestMapping("/write")
	public String write(Model model) {
		TEST_bbsDTO testbbsdto = new TEST_bbsDTO();
		model.addAttribute("test_bbsDTO",testbbsdto);		
		return "/TEST/TESTbbswrite";		
	}
	
	
	
	//게시글 등록처리
	@RequestMapping("/writeOK")
	public String writeOK(@Valid @ModelAttribute("test_bbsDTO") TEST_bbsDTO test_bbsDTO, BindingResult result, Model model, HttpSession session) {
		logger.info("String writeOK 호출됨:" + test_bbsDTO);
		
		int cnt = 0;
		if(result.hasErrors()) {
			logger.info(result.toString());
			return "/TEST/TESTbbswrite";
		}
/*		MemberDTO mdto = (MemberDTO)session.getAttribute("user");
		// 사용자 세션정보가 없는경우
		if(mdto == null) {
			return "redirect:/login/loginForm";
		}*/
		try {
						
			cnt = testsvc.write(test_bbsDTO);
			logger.info("게시글 등록건수 :" + cnt);
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return "redirect:/TEST/list";
	}	
	
	//게시글 답글양식-reply_form.jsp
	@RequestMapping("/replyForm/{num}/{reqPage}")
	public String reply(
			@PathVariable("num") String num,
			@PathVariable("reqPage") String reqPage,
			Model model) {
		
		RecordCriteria rc = null;
		TEST_bbsDTO testbbsdto = null;
		
		
		try {
			rc = new RecordCriteria(Integer.parseInt(reqPage));
			testbbsdto = testsvc.replyView(num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("test_bbsDTO",testbbsdto);
		model.addAttribute("rc",rc);
		return "/TEST/TESTbbsreply";
	}
	
	
	//게시글 답글처리
	@RequestMapping("/replyOK")
	public String replyOK(@Valid @ModelAttribute("test_bbsDTO") TEST_bbsDTO testbbsdto, 
			BindingResult result, HttpSession session, @RequestParam("reqPage") String reqPage) {
		logger.info("String replyOK 호출됨:" + testbbsdto);
		
		int cnt = 0;
		
		if(result.hasErrors()) {
			
			return "redirect:/TEST/replyForm";
		}
		
		try {			
			cnt = testsvc.reply(testbbsdto);
			logger.info("게시글 답글 등록건수 :" + cnt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/TEST/list?reqPage="+reqPage;
	}
	
	
	
	
	//게시글 보기-read.jsp
	@RequestMapping("/view")
	public String view(@ModelAttribute("test_bbsDTO") TEST_bbsDTO test_bbsDTO, Model model, HttpServletRequest request){
		
		
		RecordCriteria rc = null;
		try {
			rc = new RecordCriteria(Integer.parseInt(request.getParameter("reqPage")));
			test_bbsDTO = testsvc.view(request.getParameter("num"));
		} catch (Exception e) {
			e.printStackTrace();
		}		
		model.addAttribute("test_bbsDTO",test_bbsDTO);
		model.addAttribute("rc",rc);
		
		return "/TEST/TESTbbsread";
	}
	
	//게시글 수정양식
		@RequestMapping("/modifyform/{num}/{reqPage}")
		public String noticemodify(@Valid @ModelAttribute("test_bbsDTO") TEST_bbsDTO test_bbsDTO, BindingResult result, 
				 @PathVariable("num") String num,@PathVariable("reqPage") String reqPage, Model model,HttpServletRequest request) {
			RecordCriteria rc = null;
			try {
				rc = new RecordCriteria(Integer.parseInt(reqPage));
				test_bbsDTO = testsvc.view(num);
			} catch (Exception e) {
				e.printStackTrace();
			}		
			model.addAttribute("test_bbsDTO",test_bbsDTO);
			model.addAttribute("rc",rc);
			
			return "/TEST/TESTbbsmodify";
		}
		
		//게시글 수정처리
			@RequestMapping("/modifyok")
			public String noticemodifok(@Valid @ModelAttribute TEST_bbsDTO test_bbsDTO, BindingResult result) {
				int cnt = 0;
				try {
					cnt = testsvc.modify(test_bbsDTO);
				} catch (Exception e) {
					e.printStackTrace();
				}		
				logger.info("수정건수 : "+cnt);
				
				return "redirect:/TEST/list";
			}
	
	
	
	
	//게시글 삭제처리
	@RequestMapping("/delete")
	public String delete(@RequestParam("num") String num,
			@RequestParam("reqPage") String reqPage) {
		
		try {
			testsvc.delete(num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/TEST/list?reqPage="+reqPage;
		}
	
	
	
	//게시글 목록보기-list.jsp
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model) {
		
		try {
			testsvc.list(request, model);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return "/TEST/TESTbbslist";
		
	}
	
	
	
	
	

}