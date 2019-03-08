package com.lkh.myapp.test.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.lkh.myapp.test.dto.TEST_bbsDTO;






@Repository(value = "test_bbsDAOImplXML")
public class TEST_bbsDAOImplXML implements TEST_bbsDAO {

	private static final Logger logger = LoggerFactory.getLogger(TEST_bbsDAOImplXML.class);

	@Inject
	SqlSession sqlSession;

	// 글쓰기
	@Override
	public int write(TEST_bbsDTO test_bbsDTO) throws Exception {
		return sqlSession.insert("mappers.testbbs.write", test_bbsDTO);
	}

	// 글목록
	@Override
	public List<TEST_bbsDTO> list() throws Exception {
		return sqlSession.selectList("mappers.testbbs.listOld");
	}

	// 글목록 페이지요청
	@Override
	public List<TEST_bbsDTO> list(int startRec, int endRec) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("startRec", startRec);
		map.put("endRec", endRec);
		return sqlSession.selectList("mappers.testbbs.list", map);
	}

	// 글읽기
	@Override
	public TEST_bbsDTO view(String num) throws Exception {
		TEST_bbsDTO test_bbsDTO = null;

		test_bbsDTO = sqlSession.selectOne("mappers.testbbs.view", num);

		// 조회수 증가
		updateHit(test_bbsDTO.getNum());
		return test_bbsDTO;
	}

	// 조회수 증가메소드
	private void updateHit(int num) {
		sqlSession.update("mappers.testbbs.updateHit", num);

	}

	// 글수정
	@Override
	public int modify(TEST_bbsDTO test_bbsDTO) throws Exception {
		return sqlSession.update("mappers.testbbs.modify", test_bbsDTO);
	}

	// 글삭제
	@Override
	public int delete(String num) throws Exception {		
		int cnt = 0;
		
		logger.info("답글 : "+ isReply(num));
		//답글 있는 경우
		if(isReply(num)) {
			cnt = sqlSession.update("mappers.testbbs.update_isdel", num);
		}
		//답글 없는 경우
		else {
			cnt = sqlSession.update("mappers.testbbs.delete", num);
		}
		
		return cnt;
	}
	private boolean isReply(String num) {
		boolean isYN = false;
		int cnt = 0;
		cnt = sqlSession.selectOne("mappers.testbbs.isReply", num);
		logger.info("cntisYN : " + cnt);
		if(cnt > 0) {
			isYN = true;
			logger.info("isYS : " +isYN);
		}
		return isYN;
	}

	// 원글가져오기
	@Override
	public TEST_bbsDTO replyView(String num) throws Exception {
		return sqlSession.selectOne("mappers.testbbs.replyView", num);
	}

	// 답글쓰기
	@Override
	public int reply(TEST_bbsDTO test_bbsDTO) throws Exception {
		int cnt1 = 0, cnt2 = 0;

		// 이전 답글 step 업데이트(원글그룹에 대한 세로정렬 재정의)
		cnt1 = updateStep(test_bbsDTO.getBgroup(), test_bbsDTO.getStep());
		// 답글등록
		cnt2 = sqlSession.insert("mappers.testbbs.reply", test_bbsDTO);

		return cnt2;
	}

	// 이전답글 step 업데이트
	private int updateStep(int bgroup, int step) {
		Map<String, Object> map = new HashMap<>();
		map.put("bgroup", bgroup);
		map.put("step", step);
		return sqlSession.update("mappers.testbbs.updateStep", map);
	}

	// 게시글 총계
	@Override
	public int totalRec() throws Exception {
		return sqlSession.selectOne("mappers.testbbs.totalRec");
	}

	// 검색목록
	@Override
	public List<TEST_bbsDTO> list(int startRecord, int endRecord, String searchType, String keyword) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("startRecord", startRecord);
		map.put("endRecord", endRecord);
		map.put("searchType", searchType);
		logger.info("서치타입: " + searchType);
		map.put("keyword", keyword);

		return sqlSession.selectList("mappers.testbbs.flist", map);
	}

	// 검색 총계
	@Override
	public int SearchTotalRec(String searchType, String keyword) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("searchType", searchType);
		map.put("keyword", keyword);

		return sqlSession.selectOne("mappers.testbbs.searchTotalRec", map);

	}

}