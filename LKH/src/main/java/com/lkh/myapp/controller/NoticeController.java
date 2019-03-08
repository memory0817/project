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

import com.lkh.myapp.notice.dto.NoticeDTO;
import com.lkh.myapp.notice.service.NoticeSvc;
import com.lkh.myapp.util.RecordCriteria;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/notice")
public class NoticeController {
	
	private static Logger logger = LoggerFactory.getLogger(NoticeController.class);
	
	@Inject
	NoticeSvc nsvc;
	
	
	
	//게시글 등록양식-write_form
	@RequestMapping("/write")
	public String write(Model model) {
		NoticeDTO noticeDTO = new NoticeDTO();
		model.addAttribute("noticeDTO",noticeDTO);		
		return "/notice/noticewrite";		
	}
	
	
	
	//게시글 등록처리
	@RequestMapping("/writeOK")
	public String writeOK(@Valid @ModelAttribute("noticeDTO") NoticeDTO noticeDTO, BindingResult result, Model model, HttpSession session) {
		logger.info("String writeOK 호출됨:" + noticeDTO);
		
		int cnt = 0;
		if(result.hasErrors()) {
			logger.info(result.toString());
			return "/notice/noticewrite";
		}
/*		MemberDTO mdto = (MemberDTO)session.getAttribute("user");
		// 사용자 세션정보가 없는경우
		if(mdto == null) {
			return "redirect:/login/loginForm";
		}*/
		try {
						
			cnt = nsvc.write(noticeDTO);
			logger.info("게시글 등록건수 :" + cnt);
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return "redirect:/notice/adminnotice";
	}	
	
	//게시글 답글양식
	@RequestMapping("/noticereply/{num}/{reqPage}")
	public String reply(
			@PathVariable("num") String num,
			@PathVariable("reqPage") String reqPage,
			Model model) {
		
		RecordCriteria rc = null;
		NoticeDTO noticeDTO = null;
		
		
		try {
			rc = new RecordCriteria(Integer.parseInt(reqPage));
			noticeDTO = nsvc.replyView(num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("noticeDTO",noticeDTO);
		model.addAttribute("rc",rc);
		return "/notice/noticereplyform";
	}
	
	
	//게시글 답글처리
	@RequestMapping("/replyOK")
	public String replyOK(@Valid @ModelAttribute("noticeDTO") NoticeDTO noticeDTO, 
			BindingResult result, HttpSession session, @RequestParam("reqPage") String reqPage) {
		logger.info("String replyOK 호출됨:" + noticeDTO);
		
		int cnt = 0;
		
		if(result.hasErrors()) {
			
			return "/notice/noticereplyform";
		}
		
		try {			
			cnt = nsvc.reply(noticeDTO);
			logger.info("게시글 답글 등록건수 :" + cnt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/notice/adminnotice?reqPage="+reqPage;
	}
	
	
	
	
	//게시글 보기
	@RequestMapping("/view")
	public String view(@ModelAttribute("noticeDTO") NoticeDTO noticeDTO, Model model, HttpServletRequest request){
		
		RecordCriteria rc = null;
		try {
			rc = new RecordCriteria(Integer.parseInt(request.getParameter("reqPage")));
			noticeDTO = nsvc.view(request.getParameter("num"));
		} catch (Exception e) {
			e.printStackTrace();
		}		
		model.addAttribute("noticeDTO",noticeDTO);
		model.addAttribute("rc",rc);
		
		return "/notice/noticeread";
	}
	
	
	//게시글 수정양식
	@RequestMapping("/noticemodify/{num}/{reqPage}")
	public String noticemodify(@Valid @ModelAttribute NoticeDTO noticeDTO, BindingResult result, 
			 @PathVariable("num") String num,@PathVariable("reqPage") String reqPage, Model model,HttpServletRequest request) {
		RecordCriteria rc = null;
		try {
			rc = new RecordCriteria(Integer.parseInt(reqPage));
			noticeDTO = nsvc.view(num);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		model.addAttribute("noticeDTO",noticeDTO);
		model.addAttribute("rc",rc);
		
		return "/notice/noticemodify";
	}
	
	//게시글 수정처리
		@RequestMapping("/noticemodifyok")
		public String noticemodifok(@Valid @ModelAttribute NoticeDTO noticeDTO, BindingResult result, @RequestParam("reqPage") String reqPage) {
			int cnt = 0;
			try {
				cnt = nsvc.modify(noticeDTO);
			} catch (Exception e) {
				e.printStackTrace();
			}		
			logger.info("수정건수 : "+cnt);
			
			return "redirect:/notice/adminnotice?reqPage="+reqPage;
		}
	
	
	
	
	//게시글 삭제처리
	@RequestMapping("/delete")
	public String delete(@RequestParam("num") String num,
			@RequestParam("reqPage") String reqPage) {
		
		try {
			nsvc.delete(num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/notice/adminnotice?reqPage="+reqPage;
		}
	
	
	// 공지사항목록
	@RequestMapping("/adminnotice")
	public String adminnotice(HttpServletRequest request, Model model) {
		try {
			nsvc.list(request, model);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return "/notice/adminnoticelist";
	}

}
