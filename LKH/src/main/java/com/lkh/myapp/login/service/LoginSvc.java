package com.lkh.myapp.login.service;

import java.sql.Timestamp;

import org.springframework.stereotype.Service;

import com.lkh.myapp.member.dto.MemberDTO;

@Service
public interface LoginSvc {

	public boolean isMember(String id, String pw);

	public boolean isidMember(String name, String division);

	public boolean ispwMember(String id, String name, String division);

	public MemberDTO login(String id, String pw);

	// 로그인 성공시 로그인 카운트 0으로 초기화
	public boolean resetlogincount(String id);

	// 로그인 실패시 로그인 카운트 증가
	public boolean logincount(String id);

	// 로그인 실패시 로그인 카운트 반환
	public Integer returnlogincount(String id);

	// 로그인 실패시 비밀번호 랜덤 생성
	public boolean randompw(String id, String randompw);

	// 아이디 찾기
	public String findID(String name, String division);

	// 비밀번호 찾기
	public String findPW(String id, String name, String division);

	// 비밀번호 찾기 시 아이디 1차 체크
	boolean idchk(String id);

	

	// 비밀번호를 전송할 이메일 찾기
	String findemail(String id, String name, String division);
}
