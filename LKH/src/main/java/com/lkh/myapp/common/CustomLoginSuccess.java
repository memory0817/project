package com.lkh.myapp.common;

import java.io.IOException;

import javax.inject.Inject;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.lkh.myapp.login.service.LoginSvc;
import com.lkh.myapp.member.dto.MemberDTO;
import com.lkh.myapp.member.dto.ProfilDTO;
import com.lkh.myapp.member.service.MemberSvc;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomLoginSuccess implements AuthenticationSuccessHandler{

	private static final Logger logger = LoggerFactory.getLogger(CustomLoginSuccess.class);
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Inject
	LoginSvc loginSvc;
	
	@Inject
	MemberSvc memberSvc;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		ProfilDTO pdto = new ProfilDTO();
		
		logger.info("memberDTO : " +MemberDTO.current());
		String email = MemberDTO.current().getEmail();
		String id = request.getParameter("id");

		if(!MemberDTO.current().getDivision().equals("ROLE_ADMIN")) {
			try {
				logger.info("로그인 메일발송");
				
				MimeMessage message = mailSender.createMimeMessage();
				  message.setFrom(new InternetAddress("ljy4337@gmail.com"));  
				  message.addRecipient(RecipientType.TO, new InternetAddress(email));
				  message.setSubject("LKH Co. 로그인 알림");
				  message.setText(MemberDTO.current().getName()+"님 로그인 되었습니다. 본인이 아니시라면 관리자에게 문의해주십시오.", "utf-8", "html");

				  mailSender.send(message);
				
				
			} catch (Exception e) {
				System.out.println(e);
			}
			loginSvc.resetlogincount(id);
			response.sendRedirect("/member/main");			
		}else {
			
			try {
				logger.info("로그인 메일발송");
				
				MimeMessage message = mailSender.createMimeMessage();
				  message.setFrom(new InternetAddress("ljy4337@gmail.com"));  
				  message.addRecipient(RecipientType.TO, new InternetAddress(email));
				  message.setSubject("LKH Co. 로그인 알림");
				  message.setText(MemberDTO.current().getName()+"님 로그인 되었습니다. 본인이 아니시라면 관리자에게 문의해주십시오.", "utf-8", "html");

				  mailSender.send(message);
				
				
			} catch (Exception e) {
				System.out.println(e);
			}
			loginSvc.resetlogincount(id);
			response.sendRedirect("/admin/admain");
		}
		pdto = memberSvc.select(id);
		String randomfile = pdto.getRandomfile();
		logger.info(randomfile);
		//request.setAttribute("randomfile", randomfile);
		session.setAttribute("randomfile", randomfile);
	
		
	}

}
