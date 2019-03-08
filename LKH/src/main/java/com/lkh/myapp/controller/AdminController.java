package com.lkh.myapp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lkh.myapp.admin.schedule.dto.AdminscheduleDTO;
import com.lkh.myapp.admin.schedule.service.AdminscheduleSvc;
import com.lkh.myapp.login.service.LoginSvc;
import com.lkh.myapp.logindto.LoginDTO;
import com.lkh.myapp.member.dto.MemberDTO;
import com.lkh.myapp.member.service.MemberSvc;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
//@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/admin")
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Inject
	private LoginSvc loginSvc;
	
	@Inject
	private AdminscheduleSvc asSvc;

	@Inject
	private MemberSvc memberSvc;

	// 로그인양식페이지
//	@RequestMapping("/adminlogin")
//	public void adloginForm(Model model) {
//		model.addAttribute("login", new LoginDTO());
////		return "/admin/adminlogin";
//	}

	// 로그인
//	@RequestMapping(value = "/adminloginOk") // ,method=RequestMethod.POST)
//	public String login(@Valid @ModelAttribute("login") LoginDTO login, BindingResult result, HttpSession session,
//			HttpServletResponse response) {
//
//		logger.info("String login 호출됨!");
//
//		MemberDTO mdto = null;
//
//		if (result.hasErrors()) {
//			logger.info("login 실패ㅋ");
//
//			logger.info(result.toString());
//			return "/admin/adminlogin";
//		}
//
//		// 1)회원 유무체크
//		if (loginSvc.isMember(login.getId(), login.getPw())) {
//			// 2)로그인 처리
//			mdto = loginSvc.login(login.getId(), login.getPw());
//
//			session.setAttribute("user", mdto);
//			logger.info("로그인 처리됨:" + login.getId());
//		} else {
//			logger.info("login 실패");
//			return "/admin/adminlogin";
//		}
//		return "/admin/adminmain";
//	}

	// 로그인 초기화버튼
	@RequestMapping(value = "/clear/{id:.+}", method = RequestMethod.GET)
	public String clear(@PathVariable String id, Model model) {

		logger.info("/clear");
		boolean success = false;

		try {
			success = loginSvc.resetlogincount(id);
		} catch (Exception e) {

			e.printStackTrace();
		}

		logger.info("수정처리 결과:" + success);
//			return "/member/memberList";
		return "forward:/admin/memberlist";
	}

	// 사원 목록보기 - 주소록으로 표시
	@RequestMapping("/memberlist")
	public String list(HttpServletRequest request, Model model, HttpSession session) {

		try {
			memberSvc.list(request, model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/admin/memberlist";
	}

	// 회원수정페이지:관리자
	@RequestMapping(value = "/adminModifyForm/{id:.+}")
	public String adminModifyForm(@PathVariable String id, Model model) {
		logger.info("/memberModifyForm");

		MemberDTO mdto = null;
		try {
			mdto = memberSvc.getMember(id);
			model.addAttribute("mdto", mdto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/member/memberAdminModifyForm";
	}

	// 회원수정처리:관리자
	@RequestMapping(value = "/adminModify", method = RequestMethod.POST)
	public String adminModify(@Valid @ModelAttribute("mdto") MemberDTO mdto, BindingResult result) {
		logger.info("/adminModify");
		boolean success = false;

		if (result.hasErrors()) {
			return "/member/memberAdminModifyForm";
		}
		try {
			success = memberSvc.adminmodify(mdto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.info("수정처리 결과:" + success);
		return "forward:/admin/memberlist";
	}

	// 회원삭제처리
	@RequestMapping("/memberDelete/{id:.+}")
	public String memberDelete(@PathVariable String id, Model model) {
		logger.info("/memberDelete/{id:.+}");
		boolean success = false;
		boolean result = false;
		logger.info(id);

		MemberDTO mdto = null;
		try {
			mdto = memberSvc.getMember(id);
			model.addAttribute("mdto", mdto);
			
			result = memberSvc.deleteA(id);
			logger.info("권한 삭제처리 결과:" + result);

			success = memberSvc.delete(id);
			logger.info("삭제처리 결과:" + success);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return "forward:/admin/memberlist";
	}

	// 사원등록양식페이지
	@RequestMapping("/memberJoinForm")
	public String memberJoinForm(Model model) {
		model.addAttribute("mdto", new MemberDTO());
		return "/member/memberJoinForm";
	}

	// 사원등록처리
	@RequestMapping(value = "/memberJoin", method = RequestMethod.POST)
	public String memberJoin(@Valid @ModelAttribute("mdto") MemberDTO mdto, BindingResult result, Model model) {
		logger.info("/member/memberJoin 호출됨!");
		logger.info(mdto.toString());
		boolean success = false;

		if (result.hasErrors()) {

			logger.info(result.toString());
			logger.info("회원가입시 오류발생!!");
			return "/member/memberJoinForm";
		}

		try {
			success = memberSvc.insert(mdto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("result", success);
		return "forward:/admin/memberlist";
	}

	// 일정관리 페이지
	@RequestMapping(value ="/schedule")
	public String schedule(Model model) {

		model.addAttribute("asdto", new AdminscheduleDTO());
		return "/admin/schedule";
	}
	
	// 일정 목록 Map
		@RequestMapping(value = "/schudule/list", method = RequestMethod.POST)
		public ResponseEntity<Map<String,Object>> map() {
			ResponseEntity<Map<String, Object>> responseEntity = null;
			Map<String, Object> map = new HashMap<>();

			try {
				 asSvc.slist();
				map.put("item", asSvc.slist());
				responseEntity = new ResponseEntity<>(map, HttpStatus.OK);
				
			} catch (Exception e) {
				responseEntity = new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
				e.printStackTrace();
			}

			return responseEntity;
		}
	

	// 일정 등록
	@RequestMapping(value="/addschedule", method = RequestMethod.POST)
	public String addschedule(@Valid @ModelAttribute("asdto") AdminscheduleDTO asdto , BindingResult result, Model model) {
		logger.info("/admin/addschedule 호출됨!");
		logger.info(asdto.toString());
		boolean success = false;
	
		if (result.hasErrors()) {

			logger.info(result.toString());
			logger.info("일정등록 오류발생!!");
			return "redirect:/admin/schedule";
		}
		
		try {
			asSvc.addschedule(asdto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		model.addAttribute("result", success);
		return "redirect:/admin/schedule";
	}


	

	// 일정 삭제
	@RequestMapping(value = "/delschedule/{sname}", produces = "application/text; charset=utf8")
	public ResponseEntity<String> delete(@PathVariable("sname") String sname) {
		ResponseEntity<String> resCode = null;


		if (sname == null || sname.length() == 0) {
			resCode = new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
			return resCode;
		}
		try {
			asSvc.delschedule(sname);
			resCode = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			resCode = new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return resCode;
	}
	
	// 일정 수정
	@RequestMapping(value = "/updateschedule", method = RequestMethod.PUT)
	public ResponseEntity<String> modify(@RequestBody AdminscheduleDTO asdto) {
		ResponseEntity<String> resCode = null;
		logger.info("수정호출");
		if (asdto == null) {
			resCode = new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
			return resCode;
		}
		try {
			asSvc.updateschedule(asdto);
			resCode = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			resCode = new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return resCode;
	}

	// 관리자메인페이지
	@RequestMapping("/admain")
	public String adminMain(Model model) {

		return "/admin/adminmain";
	}

}