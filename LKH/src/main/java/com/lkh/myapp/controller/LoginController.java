package com.lkh.myapp.controller;

import java.util.Random;

import javax.inject.Inject;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lkh.myapp.login.service.LoginSvc;
import com.lkh.myapp.logindto.LoginDTO;
import com.lkh.myapp.member.service.MemberSvc;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Inject
	LoginSvc loginSvc;
	@Autowired
	@Inject
	private MemberSvc memberSvc;
	
	@Autowired
	private JavaMailSender mailSender;


	@RequestMapping("/loginForm")
	public void loginForm(Model model) {
		logger.info("loginForm() 호출");
		model.addAttribute("login", new LoginDTO());
		
	}

	@RequestMapping("/login")
	public String login() {

		return "/member/main";
	}

	// 로그인
//	@RequestMapping(value = "/loginOk") // ,method=RequestMethod.POST)
//	public String login(@Valid @ModelAttribute("login") LoginDTO login, BindingResult result, HttpSession session,
//			HttpServletResponse response, Model model) {
//
//		logger.info("String login 호출됨!");
//
//		MemberDTO mdto = null;
//		ProfilDTO pdto = null;
//		int logincount = 0;
//		boolean loginresult = false;
//
//		if (result.hasErrors()) {
//			logger.info("login 실패 에러메시지");
//
//			logger.info(result.toString());
//			return "/login/loginForm";
//		}
//
//
//		// 1)회원 유무체크
//		if (loginSvc.isMember(login.getId(), login.getPw())) {
//			// 2)로그인 처리
//			mdto = loginSvc.login(login.getId(), login.getPw());
//			// 3)로그인 성공시 로크인 카운트 0으로 초기화
//			loginSvc.resetlogincount(login.getId());
//
//			session.setAttribute("user", mdto);
//
//			logger.info("로그인 처리됨:" + login.getId());
//			pdto = memberSvc.select(login.getId());
//			session.setAttribute("pdto", pdto);
//			
//			
//			
//		} else {
//
//			logger.info("login 실패");
//
//			// 4)로그인 실패시 로그인 카운트 증가
//			loginresult = loginSvc.logincount(login.getId());
//			if (loginresult) {
//				// 5) 로그인 실패시 로그인 카운트 반환
//				logincount = loginSvc.returnlogincount(login.getId());
//				// 로그인 카운트 체크
//				if (logincount > 5) {
//					String randompw = null;
//					char[] charaters = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
//							'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7',
//							'8', '9' };
//
//					StringBuffer sb = new StringBuffer();
//					Random rn = new Random();
//					for (int i = 0; i < 15; i++) {
//
//						sb.append(charaters[rn.nextInt(charaters.length)]);
//
//					}
//					logincount = 5;
//					randompw = sb.toString();
//					logger.info(randompw);
//					loginSvc.randompw(login.getId(), randompw);
//				} else {
//
//				}
//				model.addAttribute("logincount", logincount);
//				model.addAttribute("errmsg",
//						"잘못된 로그인 시도입니다. 5회 초과시 계정이 잠깁니다. 아이디 또는 비밀번호를 다시 확인하여 주세요. (" + logincount + "/5)");
//				return "forward:/login/loginForm";
//			}
//			model.addAttribute("errmsg", "아이디 또는 비밀번호를 다시 확인하여 주세요.");
//			return "forward:/login/loginForm";
//		}
//
//		return "redirect:/member/main";
//	}

	// 아이디 찾기
	@RequestMapping("/findID")
	public void findid(Model model) {
		logger.info("findid() 호출");
		model.addAttribute("login", new LoginDTO());
	}
	// Model model -> response
	// @ModelAttribute -> request

	// 아이디 찾기 실행
	@RequestMapping(value = "/findidok", method = RequestMethod.POST) // ,method=RequestMethod.POST)
	public String findidok(@Valid @ModelAttribute("login") LoginDTO login, BindingResult result, Model model) {

		logger.info("findid 호출됨!");

		String id = null;
		LoginDTO logindto = null;
		if (result.hasErrors()) {

			logger.info(result.toString());
			logger.info("아이디찾기 오류발생!!");
			return "/login/findID";
		}

		logger.info(login.getName() + login.getDivision());
		// 1)회원 유무체크
		if (loginSvc.isidMember(login.getName(), login.getDivision())) {
			// 2)로그인 처리
			id = loginSvc.findID(login.getName(), login.getDivision());

		} else {
			model.addAttribute("errmsg", "입력하신 내용을 다시 확인해주세요.");
			return "forward:/login/findID";
		}
		logger.info("아이디찾기 처리됨:" + id);
		model.addAttribute("id", id);
		return "/login/findidlog";
	}

	// 아이디보여주기
	@RequestMapping(value = "/findidlog") // ,method=RequestMethod.POST)
	public String findidview(@ModelAttribute("login") LoginDTO login) {

		logger.info("findidlog 호출됨!");

		return "/login/findidlog";
	}

	// 아이디 체크
	@RequestMapping("/idchk")
	public void idchk(Model model) {
		logger.info("idchk() 호출");
		model.addAttribute("login", new LoginDTO());
	}

	// 아이디 체크 실행
	@RequestMapping(value = "/idchkok", method = RequestMethod.POST) // ,method=RequestMethod.POST)
	public String idchkok(@Valid @ModelAttribute("login") LoginDTO login, BindingResult result, Model model) {

		logger.info("pwchk 호출됨!");

		String id = login.getId();
		boolean idchk = false;
		if (result.hasErrors()) {

			logger.info(result.toString());
			logger.info("아이디체크오류!! 등록된 아이디가 없습니다.");
			return "/login/idchk";
		}

		idchk = loginSvc.idchk(id);
		logger.info("결과 : " + idchk);
		// 1)아이디 유무체크
		if (idchk) {

			logger.info("등록된 아이디 있음");

		} else {
			model.addAttribute("errmsg", "등록된 아이디 정보가 존재하지 않습니다.");
			return "forward:/login/idchk";
		}
		model.addAttribute("id", id);
		return "/login/findPW";

	}

	// 비밀번호 찾기
	@RequestMapping("/findPW")
	public void findpw(Model model) {
		logger.info("findpw() 호출");
		model.addAttribute("login", new LoginDTO());
	}

	// 비밀번 찾기 실행
	@RequestMapping(value = "/findpwok", method = RequestMethod.POST) // ,method=RequestMethod.POST)
	public String findpwok(@Valid @ModelAttribute("login") LoginDTO login, BindingResult result, Model model) {

		logger.info("findpw 호출됨!");

		String pw = null;
		String email = null;
		
		if (result.hasErrors()) {

			logger.info(result.toString());
			logger.info("pw찾기 오류발생!!");
			return "/login/idchkok";
		}

		logger.info(login.getId() + login.getName() + login.getDivision());
		// 1)회원 유무체크
		if (loginSvc.ispwMember(login.getId(), login.getName(), login.getDivision())) {
			// 2) 임시 비밀번호 난수생성
			String randompw = null;
			char[] charaters = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
					'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7',
					'8', '9' };

			StringBuffer sb = new StringBuffer();
			Random rn = new Random();
			for (int i = 0; i < 15; i++) {

				sb.append(charaters[rn.nextInt(charaters.length)]);

			}
		
			randompw = sb.toString();
			logger.info(randompw);
			loginSvc.randompw(login.getId(), randompw);
			
			
			// 3)비밀번호 찾기 실행
			pw = loginSvc.findPW(login.getId(), login.getName(), login.getDivision());
			email = loginSvc.findemail(login.getId(), login.getName(), login.getDivision());
			if(pw != null) {
				try {
					logger.info("비번 메일발송");
					logger.info(email);
					
					MimeMessage message = mailSender.createMimeMessage();
					  message.setFrom(new InternetAddress("ljy4337@gmail.com"));  
					  message.addRecipient(RecipientType.TO, new InternetAddress(email));
					  message.setSubject("LKH Co. 비밀번호 찾기 결과 안내");
					  message.setText("임시비밀번호 : " +randompw, "utf-8", "html");

					  mailSender.send(message);
					
					
				} catch (Exception e) {
					System.out.println(e);
				}
				logger.info("seccess");
			}
			

		} else {
			model.addAttribute("errmsg", "입력하신 내용을 다시 확인해주세요.");
			return "forward:/login/idchkok";
		}
		logger.info("pw 처리됨:" + pw);
		model.addAttribute("pw", pw);
		return "/login/findpwlog";
	}

	// 비밀번보여주기
	@RequestMapping(value = "/findpwlog") // ,method=RequestMethod.POST)
	public String findpwview(@ModelAttribute("login") LoginDTO login) {

		logger.info("findpwlog 호출됨!");

		return "/login/findpwlog";
	}

//	// 사원로그아웃
//	@RequestMapping("/logout")
//	public String logout(HttpSession session) {
//
//		session.invalidate();
//
//		return "redirect:/login/loginForm";
//	}
//
//	// 관리자 로그아웃
//	@RequestMapping("/adminlogout")
//	public String adminlogout(HttpSession session) {
//
//		session.invalidate();
//
//		return "redirect:/admin/adminlogin";
//	}

}
