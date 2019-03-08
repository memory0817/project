package com.lkh.myapp.logindao;

import java.sql.Timestamp;

import com.lkh.myapp.member.dto.MemberDTO;

public interface LoginDAO {
		// 등록된 회원인지 판별
		public boolean isMember(String id, String pw);
		
		// 아이디 찾기 시 등록된 회원인지 판별
		public boolean isidMember(String name, String division);
		
		// 비밀번호 찾기 시 아이디 1차 체크
		boolean idchk(String id);
		
		// 비밀번호 찾기 시 등록된 회원인지 판별
		public boolean ispwMember(String id, String name, String division);
		
		// 판별 후 로그인시 MemberDTO에 일치하는 회원 정보를 담는다
		public MemberDTO login(String id, String pw);
		
		// 로그인 성공시 logincount 0으로 초기화
		public boolean resetlogincount(String id);
		
		// 로그인 실패시 로그인 카운트 증가
		public boolean logincount(String id);
		
		// 로그인 실패시 로그인 카운트 반환
		public Integer returnlogincount(String id);
		
		// 로그인 실패시 랜덤비밀번호 생성
		public boolean randompw(String id, String randompw);
		
		// 아이디 찾기
		public String findID(String name, String division);
		
		// 비밀번호 찾기
		public String findPW(String id, String name, String division);
		

		// 비밀번호를 전송할 이메일 찾기
		String findemail(String id, String name, String division);
}
