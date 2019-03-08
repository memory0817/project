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

import com.lkh.myapp.sale.dto.SALE_bbsDTO;
import com.lkh.myapp.sale.service.SALE_bbsSvc;
import com.lkh.myapp.util.RecordCriteria;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@PreAuthorize("hasAnyRole('ROLE_SALE','ROLE_ADMIN')")
@RequestMapping("/SALE")
public class SALE_bbsController {
	
	private static Logger logger = LoggerFactory.getLogger(SALE_bbsController.class);
	
	@Inject
	SALE_bbsSvc salesvc;
	
	
	
	//게시글 등록양식-write_form
	@RequestMapping("/write")
	public String write(Model model) {
		SALE_bbsDTO salebbsdto = new SALE_bbsDTO();
		model.addAttribute("sale_bbsDTO",salebbsdto);		
		return "/SALE/SALEbbswrite";		
	}
	
	
	
	//게시글 등록처리
	@RequestMapping("/writeOK")
	public String writeOK(@Valid @ModelAttribute("sale_bbsDTO") SALE_bbsDTO sale_bbsDTO, BindingResult result, Model model, HttpSession session) {
		logger.info("String writeOK 호출됨:" + sale_bbsDTO);
		
		int cnt = 0;
		if(result.hasErrors()) {
			logger.info(result.toString());
			return "/SALE/SALEbbswrite";
		}
/*		MemberDTO mdto = (MemberDTO)session.getAttribute("user");
		// 사용자 세션정보가 없는경우
		if(mdto == null) {
			return "redirect:/login/loginForm";
		}*/
		try {
						
			cnt = salesvc.write(sale_bbsDTO);
			logger.info("게시글 등록건수 :" + cnt);
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return "redirect:/SALE/list";
	}	
	
	//게시글 답글양식-reply_form.jsp
	@RequestMapping("/replyForm/{num}/{reqPage}")
	public String reply(
			@PathVariable("num") String num,
			@PathVariable("reqPage") String reqPage,
			Model model) {
		
		RecordCriteria rc = null;
		SALE_bbsDTO salebbsdto = null;
		
		
		try {
			rc = new RecordCriteria(Integer.parseInt(reqPage));
			salebbsdto = salesvc.replyView(num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("sale_bbsDTO",salebbsdto);
		model.addAttribute("rc",rc);
		return "/SALE/SALEbbsreply";
	}
	
	
	//게시글 답글처리
	@RequestMapping("/replyOK")
	public String replyOK(@Valid @ModelAttribute("sale_bbsDTO") SALE_bbsDTO salebbsdto, 
			BindingResult result, HttpSession session, @RequestParam("reqPage") String reqPage) {
		logger.info("String replyOK 호출됨:" + salebbsdto);
		
		int cnt = 0;
		
		if(result.hasErrors()) {
			
			return "redirect:/SALE/replyForm";
		}
		
		try {			
			cnt = salesvc.reply(salebbsdto);
			logger.info("게시글 답글 등록건수 :" + cnt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/SALE/list?reqPage="+reqPage;
	}
	
	
	
	
	//게시글 보기-read.jsp
	@RequestMapping("/view")
	public String view(@ModelAttribute("sale_bbsDTO") SALE_bbsDTO sale_bbsDTO, Model model, HttpServletRequest request){
		
		
		RecordCriteria rc = null;
		try {
			rc = new RecordCriteria(Integer.parseInt(request.getParameter("reqPage")));
			sale_bbsDTO = salesvc.view(request.getParameter("num"));
		} catch (Exception e) {
			e.printStackTrace();
		}		
		model.addAttribute("sale_bbsDTO",sale_bbsDTO);
		model.addAttribute("rc",rc);
		
		return "/SALE/SALEbbsread";
	}
	
	//게시글 수정양식
		@RequestMapping("/modifyform/{num}/{reqPage}")
		public String noticemodify(@Valid @ModelAttribute("sale_bbsDTO") SALE_bbsDTO sale_bbsDTO, BindingResult result, 
				 @PathVariable("num") String num,@PathVariable("reqPage") String reqPage, Model model,HttpServletRequest request) {
			RecordCriteria rc = null;
			try {
				rc = new RecordCriteria(Integer.parseInt(reqPage));
				sale_bbsDTO = salesvc.view(num);
			} catch (Exception e) {
				e.printStackTrace();
			}		
			model.addAttribute("sale_bbsDTO",sale_bbsDTO);
			model.addAttribute("rc",rc);
			
			return "/SALE/SALEbbsmodify";
		}
		
		//게시글 수정처리
			@RequestMapping("/modifyok")
			public String noticemodifok(@Valid @ModelAttribute SALE_bbsDTO sale_bbsDTO, BindingResult result) {
				int cnt = 0;
				try {
					cnt = salesvc.modify(sale_bbsDTO);
				} catch (Exception e) {
					e.printStackTrace();
				}		
				logger.info("수정건수 : "+cnt);
				
				return "redirect:/SALE/list";
			}
	
	
	
	
	//게시글 삭제처리
	@RequestMapping("/delete")
	public String delete(@RequestParam("num") String num,
			@RequestParam("reqPage") String reqPage) {
		
		try {
			salesvc.delete(num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/SALE/list?reqPage="+reqPage;
		}
	
	
	
	//게시글 목록보기-list.jsp
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model) {
		
		try {
			salesvc.list(request, model);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return "/SALE/SALEbbslist";
		
	}
	
	
	
	
	

}