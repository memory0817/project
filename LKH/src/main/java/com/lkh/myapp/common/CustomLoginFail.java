package com.lkh.myapp.common;

import java.io.IOException;
import java.util.Random;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Repository;

import com.lkh.myapp.login.service.LoginSvc;
import com.lkh.myapp.member.dto.MemberDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomLoginFail implements AuthenticationFailureHandler{

	private static final Logger logger = LoggerFactory.getLogger(CustomLoginFail.class);
	
	@Inject
	LoginSvc loginSvc;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		// TODO Auto-generated method stub
		boolean loginresult = false;
		logger.info("로그인실패");
		logger.info(request.getParameter("id"));
		String id = request.getParameter("id");
		loginresult = loginSvc.logincount(request.getParameter("id"));
		
		int logincount = 0;
		if (loginresult) {
			// 5) 로그인 실패시 로그인 카운트 반환
		logincount = loginSvc.returnlogincount(id);
			// 로그인 카운트 체크
			if (logincount > 5) {
		String randompw = null;
			char[] charaters = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
						'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7',
						'8', '9' };

				StringBuffer sb = new StringBuffer();
				Random rn = new Random();
				for (int i = 0; i < 15; i++) {

					sb.append(charaters[rn.nextInt(charaters.length)]);

				}
				logincount = 5;
				randompw = sb.toString();
				logger.info(randompw);
				loginSvc.randompw(id, randompw);
			} 
			request.setAttribute("insertid", request.getParameter("id"));
			request.setAttribute("errmsg", "암호가 일치하지 않습니다.\n로그인 5회 이상 실패시 계정이 비활성화 됩니다.\n(로그인시도 "+logincount+"/5)");
			request.getRequestDispatcher("/login/loginForm?error=true").forward(request, response);
			


			
		
		
	}	
	
	}
}
