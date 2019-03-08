package com.lkh.myapp.aboard.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.lkh.myapp.aboard.dto.A_bbsDTO;

@Repository(value = "abbsDAOImplXML")
public class A_bbsDAOImplXML implements A_bbsDAO {

	@Inject
	SqlSession sqlSession;
	
	private static final Logger logger = LoggerFactory.getLogger(A_bbsDAOImplXML.class);
	
	
	//글쓰기
	@Override
	public int write(A_bbsDTO abbsDTO) throws Exception {
		return sqlSession.insert("mappers.abbs.write", abbsDTO);
	}

	//글목록 전체
	@Override
	public List<A_bbsDTO> list() throws Exception {
		return sqlSession.selectList("mappers.abbs.listOld");
	}

	//글목록 요청페이지
	@Override
	public List<A_bbsDTO> list(int startRec, int endRec) throws Exception {
		Map<String, Object> map= new HashMap<>();
		map.put("startRec", startRec);
		map.put("endRec", endRec);
		return sqlSession.selectList("mappers.abbs.list",map);
	}

	//글읽기
	@Override
	public A_bbsDTO view(String num) throws Exception {
		A_bbsDTO abbsDTO = null;
		
		abbsDTO = sqlSession.selectOne("mappers.abbs.view", num);

		// 조회수 증가
		updateHit(abbsDTO.getNum());
		return abbsDTO;
	}
	
	//조회수 증가메소드
	private void updateHit(int num) {
		sqlSession.update("mappers.abbs.updateHit", num);		
	}

	// 글수정
	@Override
	public int modify(A_bbsDTO abbsDTO) throws Exception {
		return sqlSession.update("mappers.abbs.modify", abbsDTO);
	}

	@Override
	public int delete(String rnum) throws Exception {
		int cnt = 0;

		if (isReply(rnum)) {
			cnt = sqlSession.update("mappers.abbs.update_isdel", rnum);
		} else {
			cnt = sqlSession.delete("mappers.abbs.delete", rnum);
		}
		return cnt;
	}

	private boolean isReply(String rnum) {
		boolean isYN = false;
		int cnt = 0;
		cnt = sqlSession.selectOne("mappers.abbs.isReply", rnum);
		if (cnt > 0) {
			isYN = true;
		}
		return isYN;
	}

	// 원글가져오기
	@Override
	public A_bbsDTO replyView(String num) throws Exception {
		return sqlSession.selectOne("mappers.abbs.replyView", num);
	}

	// 답글쓰기
	@Override
	public int reply(A_bbsDTO abbsDTO) throws Exception {
		int cnt1=0, cnt2=0;
		
		//이전 답글 step 업데이트(원글그룹에 대한 세로정렬 재정의)
		cnt1 = updateStep(abbsDTO.getBgroup(), abbsDTO.getStep());
		//답글등록		
		cnt2=sqlSession.insert("mappers.abbs.reply", abbsDTO);
			
		return cnt2;
	}

	//이전답글 step 업데이트
	private int updateStep(int bgroup, int step) {
		Map<String, Object> map= new HashMap<>();
		map.put("bgroup", bgroup);
		map.put("step", step);
		return sqlSession.update("mappers.abbs.updateStep", map);
	}

	// 게시글 총계
	@Override
	public int totalRec() throws Exception {
		return sqlSession.selectOne("mappers.abbs.totalRec");
	}

	// 검색목록
	@Override
	public List<A_bbsDTO> list(int startRecord, int endRecord, String searchType, String keyword) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("startRecord", startRecord);
		map.put("endRecord", endRecord);
		map.put("searchType", searchType);
		map.put("keyword", keyword);
		
		return sqlSession.selectList("mappers.abbs.flist", map);
	}

	// 검색 총계
	@Override
	public int SearchTotalRec(String searchType, String keyword) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("searchType", searchType);
		map.put("keyword", keyword);
		
		return sqlSession.selectOne("mappers.abbs.searchTotalRec",map);
	}

}
