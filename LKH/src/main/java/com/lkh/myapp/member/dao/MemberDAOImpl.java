package com.lkh.myapp.member.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import com.lkh.myapp.member.dto.MemberDTO;
import com.lkh.myapp.member.dto.ProfilDTO;
import com.lkh.myapp.util.FindCriteria;
import com.lkh.myapp.util.PageCriteria;
import com.lkh.myapp.util.RecordCriteria;


@Repository(value="memberDAOImpl")
public class MemberDAOImpl implements MemberDAO {
	
	private static final Logger logger
	= LoggerFactory.getLogger(MemberDAOImpl.class);

	@Autowired
	@Inject
	private SqlSession sqlSession;
	MemberDAO mdao;
	
	//사원가입
	@Override
	public boolean insert(MemberDTO memberDTO) {
		logger.info("memberDAOImpl.insert 호출됨!");
		boolean success = false;
		int cnt = sqlSession.insert("mappers.member.Insert", memberDTO);
		
		if (cnt>0) { success = true; }
		return success;
		
	}
	//사원정보수정(사원)
	@Override
	public boolean modify(MemberDTO memberDTO) {
		logger.info("memberDAOImpl.modify 호출됨!");
		boolean success = false;
		int cnt = sqlSession.update("mappers.member.Modify", memberDTO);
		if (cnt>0) { success = true; }
		return success;
	}
	
	// 비밀번호 변경
	@Override
	public boolean pwmodify(MemberDTO memberDTO) throws Exception {
		logger.info("memberDAOImpl.pwmodify 호출됨!");
		boolean success = false;
		logger.info(memberDTO.getId()+"/"+memberDTO.getNewpw()+"/"+memberDTO.getPw());
		int cnt = sqlSession.update("mappers.member.pwModify", memberDTO);
		if (cnt>0) { success = true; }
		return success;
	}
	
	
	//사원정보수정(관리자)
	@Override
	public boolean adminmodify(MemberDTO memberDTO) {
		logger.info("memberDAOImpl.adminmodify 호출됨!");
		boolean success = false;
		int cnt = sqlSession.update("mappers.member.Adminmodify", memberDTO);
		if (cnt>0) { success = true; }
		return success;
	}
	
	//사원정보삭제(관리자
	@Override
	public boolean delete(String id) {
		logger.info("memberDAOImpl.delete 호출됨!");
		boolean success = false;
		
		int cnt = sqlSession.delete("mappers.member.Delete", id);
		if (cnt>0) { success = true; }
		return success;
	}
	@Override
	public boolean deleteA(String id) throws Exception {
		logger.info("memberDAOImpl.deleteA 호출됨!");
		boolean success = false;
		
		int cnt = sqlSession.delete("mappers.member.DeleteA", id);
		if (cnt>0) { success = true; }
		return success;
	}
	
	//사원 조회
	@Override
	public MemberDTO getMember(String id) {
		logger.info("memberDAOImpl.getMember 호출됨!");
		MemberDTO memberDTO = null;
		memberDTO = sqlSession.selectOne("mappers.member.memberSelectOne", id);
		return memberDTO;
	}
	//사원 조회 - 비밀번호 제외
	@Override
	public MemberDTO getMembernopw(String id) {
		logger.info("memberDAOImpl.getMember 호출됨!");
		MemberDTO memberDTO = null;
		memberDTO = sqlSession.selectOne("mappers.member.memberSelect", id);
		return memberDTO;
	}
	
	//사원 목록 조회
	@Override
	public List<MemberDTO> getMemberList() {
		logger.info("memberDAOImpl.getMemberList 호출됨!");		
		List<MemberDTO> list = null;
		list = sqlSession.selectList("mappers.member.memberSelectList");
		return list;
		
	}
	
	// 총 사원 수 계산
	@Override
	public int totalRec() throws Exception {
		return sqlSession.selectOne("mappers.member.totalRec");
	}
	
	
	@Override
	public List<MemberDTO> list(int starRec, int endRec) throws Exception {
		Map<String,Object> map = new HashMap<>();
		logger.info("list startrec end rec");
		map.put("starRec", starRec);
		map.put("endRec", endRec);
		
		return sqlSession.selectList("mappers.member.list", map);
	}
	@Override
	public List<MemberDTO> list(int startRecord, int endRecord, String keyword) throws Exception {
		Map<String,Object> map = new HashMap<>();
		map.put("startRecord", startRecord);
		map.put("endRecord", endRecord);
		map.put("keyword", keyword);
		if(keyword.equals("ROLE_HR")||keyword.equals("ROLE_SALE")||keyword.equals("ROLE_IT")||keyword.equals("ROLE_TEST")) {
			return sqlSession.selectList("mappers.member.flist2", keyword);
		}
		
		return sqlSession.selectList("mappers.member.flist", map);
	}
	@Override
	public int SearchTotalRec(String keyword) throws Exception {
		logger.info("implxml - SearchTotalRec");
		if(keyword.equals("ROLE_HR")||keyword.equals("ROLE_SALE")||keyword.equals("ROLE_IT")||keyword.equals("ROLE_TEST")) {
			return sqlSession.selectOne("mappers.member.searchTotalRec2", keyword);
		}
		return sqlSession.selectOne("mappers.member.searchTotalRec", keyword);
	}
	
	//아이디중복검사
	@Override
	public String idcheck(String id) {
		String idchk = null;
		logger.info("아이디 들어왔음 xml : "+id);
		idchk = sqlSession.selectOne("mappers.member.idcheck", id);
		logger.info("아이디 쿼리로부터 받아왔음 xml : "+idchk);
		
		return idchk;
	}
	@Override
	public boolean upload(ProfilDTO pdto) { 
		boolean result = false;
		int cnt = sqlSession.insert("mappers.profil.insert", pdto);
		if(cnt>0) {
			result = true;
		}
		return result;
	}
	@Override
	public ProfilDTO select(String id) {
		ProfilDTO pdto = null;
		pdto = sqlSession.selectOne("mappers.profil.select", id);
		return pdto;
	}
	
	@Override
	public boolean modify(ProfilDTO pdto) {
		boolean result = false;
		int cnt = sqlSession.insert("mappers.profil.modify", pdto);
		if(cnt>0) {
			result = true;
		}
		return result;
	}
	// 회원가입시 기본사진 null로 지정
	@Override
	public boolean insertnull(String id) throws Exception {
		boolean result = false;
		int cnt = sqlSession.insert("mappers.profil.insertnull", id);
		if(cnt>0) {
			result = true;
		}
		return result;
	}
	
	

	

}
