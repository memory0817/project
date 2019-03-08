package com.lkh.myapp.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import com.lkh.myapp.member.dto.MemberDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomLogoutSuccess implements LogoutSuccessHandler{

	private static final Logger logger = LoggerFactory.getLogger(CustomLogoutSuccess.class);

	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		logger.info("memberDTO : " +MemberDTO.current());
		logger.info("dd"+request.getParameter("id"));
		
		logger.info("aa : "+authentication.getAuthorities().equals("ADMIN"));
		logger.info("aa : "+authentication.getAuthorities().toString().indexOf("ROLE_ADMIN"));
		

		response.sendRedirect("/login/loginForm");
	
		
	}
	
}
