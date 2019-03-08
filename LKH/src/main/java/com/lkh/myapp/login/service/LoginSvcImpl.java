package com.lkh.myapp.login.service;

import java.sql.Timestamp;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lkh.myapp.logindao.LoginDAO;
import com.lkh.myapp.member.dto.MemberDTO;

@Service
public class LoginSvcImpl implements LoginSvc {

	@Inject
	LoginDAO loginDAO;
	
	@Inject
	private PasswordEncoder passwordEncoder;

	// 회원 유무체크
	@Override
	public boolean isMember(String id, String pw) {

		return loginDAO.isMember(id, pw);
	}

	// 로그인
	@Override
	public MemberDTO login(String id, String pw) {

		return loginDAO.login(id, pw);
	}

	@Override
	public boolean isidMember(String nickname, String division) {
		return loginDAO.isidMember(nickname, division);
	}

	@Override
	public String findID(String name, String division) {
		return loginDAO.findID(name, division);
	}

	@Override
	public String findPW(String id, String name, String division) {
		return loginDAO.findPW(id, name, division);

	}
	
	// 비밀번호를 전송할 이메일 찾기
	@Override
	public String findemail(String id, String name, String division) {
		return loginDAO.findemail(id, name, division);
	}

	@Override
	public boolean ispwMember(String id, String name, String division) {
		return loginDAO.ispwMember(id, name, division);
	}

	// 로그인 실패시 로그인카운트 증가
	@Override
	public boolean logincount(String id) {
		boolean result = false;
		result = loginDAO.logincount(id);
		return result;
	}

	// 로그인 실패시 로그인카운트 반환
	@Override
	public Integer returnlogincount(String id) {
		int logincount = 0;
		logincount = loginDAO.returnlogincount(id);
		return logincount;
	}

	// 로그인 성공시 로그인 카운트 0으로 초기화
	@Override
	public boolean resetlogincount(String id) {
		boolean result = false;
		result = loginDAO.resetlogincount(id);
		return result;
	}

	@Override
	public boolean randompw(String id, String randompw) {
		boolean result = false;
		String encodingpw = passwordEncoder.encode(randompw);
		result = loginDAO.randompw(id, encodingpw);
		return result;
	}

	@Override
	public boolean idchk(String id) {
		boolean result = false;
		result = loginDAO.idchk(id);
		return result;
	}

	


}
