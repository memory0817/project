package com.lkh.myapp.logintest;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import com.lkh.myapp.logindao.LoginDAO;
import com.lkh.myapp.member.dto.MemberDTO;


@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
class logintest {
	
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(logintest.class);

	@Inject
	LoginDAO loginDAO;
	
	@Test
	
	void ismember(){
		// 회원 유무 테스트
		boolean result = loginDAO.isMember("test310@test.com", "1234");	
		logger.info("result : "+result);
	}
	
	@Test
	@Disabled
	void login() {
		// 로그인 테스트
		MemberDTO mdto = loginDAO.login("d", "1111");
		logger.info("로그인 : " + mdto);
	}
	
	@Test
	@Disabled
	void isidmember() {
		// 회원 유무 테스트 - 아이디 찾기
		loginDAO.isidMember("1", "d");
	}
	
	@Test
	@Disabled
	void ispwmember() {
		// 회원 유무 테스트 - 비밀번호 찾기
		loginDAO.ispwMember("d","1", "d");
	}
	
	@Test
	@Disabled
	void findid() {
		// 아이디 찾기
		loginDAO.findID("1", "d");
	}
	
	@Test
	@Disabled
	void findpw() {
		// 아이디 찾기
		loginDAO.findPW("d","1", "d");
	}
	

}
