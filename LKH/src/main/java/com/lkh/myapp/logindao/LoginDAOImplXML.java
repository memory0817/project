package com.lkh.myapp.logindao;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lkh.myapp.member.dto.MemberDTO;



@Repository(value="loginDAOImplXML")
public class LoginDAOImplXML implements LoginDAO{

	
	@Inject
	SqlSession sqlSession;
	
	// 회원유무판뵬 - 로그인시
	@Override
	public boolean isMember(String id, String pw) {
		boolean result = false;
		Map<String,String> map = new HashMap<>();
		map.put("id",id);
		map.put("pw", pw);
		int cnt = sqlSession.selectOne("ismember", map); 
		if(cnt > 0) {
			result = true;
		}
		return result;
	}

	// 로그인 - 로그인 성공시 DB에서 정보를 MemberDTO에 담아서 반환
	@Override
	public MemberDTO login(String id, String pw) {
		MemberDTO memberDTO = new MemberDTO();
		Map<String,String> map = new HashMap<>();
		map.put("id",id);
		map.put("pw", pw);
		memberDTO = (MemberDTO) sqlSession.selectOne("mappers.login.login", map); 
		return memberDTO;
	}

	// 아이디 찾기
	@Override
	public String findID(String name, String division) {
		String id =  null;
		Map<String,String> map = new HashMap<>();
		map.put("name", name);
		map.put("division", division);
		id = sqlSession.selectOne("mappers.login.findID", map);
		
		return id;
	}

	// 비밀번호 찾기
	@Override
	public String findPW(String id, String name, String division) {
		String pw =  null;
		Map<String,String> map = new HashMap<>();
		map.put("id", id);
		map.put("name", name);
		map.put("division", division);
		pw = sqlSession.selectOne("mappers.login.findPW", map);
		
		return pw;
	}
	
	// 비밀번호를 전송할 이메일 찾기
		@Override
		public String findemail(String id, String name, String division) {
			String pw =  null;
			Map<String,String> map = new HashMap<>();
			map.put("id", id);
			map.put("name", name);
			map.put("division", division);
			pw = sqlSession.selectOne("mappers.login.findemail", map);
			
			return pw;
		}

	// 회원 유무 판단 - 아이디 찾기 시 
	@Override
	public boolean isidMember(String name, String division) {
		boolean result = false;
		Map<String,String> map = new HashMap<>();
		map.put("name",name);
		map.put("division", division);
		int cnt = sqlSession.selectOne("isidmember", map); 
		if(cnt > 0) {
			result = true;
		}
		return result;
	}
	
	// 회원 유무 판단 - 비밀번호 찾기 시
	@Override
	public boolean ispwMember(String id, String name, String division) {
		boolean result = false;
		Map<String,String> map = new HashMap<>();
		map.put("id",id);
		map.put("name",name);
		map.put("division", division);
		int cnt = sqlSession.selectOne("ispwmember", map); 
		if(cnt > 0) {
			result = true;
		}
		return result;
	}


	// 로그인 실패시 로그인 카운트 증가
	@Override
	public boolean logincount(String id) {
		boolean result = false;
		int cnt = 0;
		cnt = sqlSession.update("mappers.login.logincount", id);
		if(cnt>0) {
			result = true;
		}
		return result;
	}

	@Override
	public Integer returnlogincount(String id) {
		int logincount = 0;
		logincount = sqlSession.selectOne("mappers.login.returnlogincount", id);
		return logincount;
	}

	@Override
	public boolean resetlogincount(String id) {
		boolean result = false;
		int cnt = 0;
		cnt = sqlSession.update("mappers.login.resetlogincount", id);
		if(cnt>0) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean randompw(String id, String randompw) {
		boolean result = false;
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("randompw", randompw);
		int cnt = 0;
		cnt = sqlSession.update("mappers.member.randompw", map);
		if(cnt>0) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean idchk(String id) {
		boolean result = false;
		int cnt = 0;
		cnt = sqlSession.selectOne("mappers.login.idchk", id);
		if(cnt>0) {
			result = true;
		}
		return result;
	}


}
