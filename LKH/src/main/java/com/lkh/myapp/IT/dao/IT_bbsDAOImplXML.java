package com.lkh.myapp.IT.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.lkh.myapp.IT.dto.IT_bbsDTO;

@Repository(value = "it_bbsDAOImplXML")
public class IT_bbsDAOImplXML implements IT_bbsDAO {

	private static final Logger logger = LoggerFactory.getLogger(IT_bbsDAOImplXML.class);

	@Inject
	SqlSession sqlSession;

	// 글쓰기
	@Override
	public int write(IT_bbsDTO it_bbsDTO) throws Exception {
		return sqlSession.insert("mappers.itbbs.write", it_bbsDTO);
	}

	// 글목록
	@Override
	public List<IT_bbsDTO> list() throws Exception {
		return sqlSession.selectList("mappers.itbbs.listOld");
	}

	// 글목록 페이지요청
	@Override
	public List<IT_bbsDTO> list(int startRec, int endRec) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("startRec", startRec);
		map.put("endRec", endRec);
		return sqlSession.selectList("mappers.itbbs.list", map);
	}

	// 글읽기
	@Override
	public IT_bbsDTO view(String num) throws Exception {
		IT_bbsDTO it_bbsDTO = null;

		it_bbsDTO = sqlSession.selectOne("mappers.itbbs.view", num);

		// 조회수 증가
		updateHit(it_bbsDTO.getNum());
		return it_bbsDTO;
	}

	// 조회수 증가메소드
	private void updateHit(int num) {
		sqlSession.update("mappers.itbbs.updateHit", num);

	}

	// 글수정
	@Override
	public int modify(IT_bbsDTO it_bbsDTO) throws Exception {
		return sqlSession.update("mappers.itbbs.modify", it_bbsDTO);
	}

	@Override
	public int delete(String rnum) throws Exception {
		int cnt = 0;

		if (isReply(rnum)) {
			cnt = sqlSession.update("mappers.itbbs.update_isdel", rnum);
		} else {
			cnt = sqlSession.delete("mappers.itbbs.delete", rnum);
		}
		return cnt;
	}

	private boolean isReply(String rnum) {
		boolean isYN = false;
		int cnt = 0;
		cnt = sqlSession.selectOne("mappers.itbbs.isReply", rnum);
		if (cnt > 0) {
			isYN = true;
		}
		return isYN;
	}

	// 원글가져오기
	@Override
	public IT_bbsDTO replyView(String num) throws Exception {
		return sqlSession.selectOne("mappers.itbbs.replyView", num);
	}

	// 답글쓰기
	@Override
	public int reply(IT_bbsDTO it_bbsDTO) throws Exception {
		int cnt1 = 0, cnt2 = 0;

		// 이전 답글 step 업데이트(원글그룹에 대한 세로정렬 재정의)
		cnt1 = updateStep(it_bbsDTO.getBgroup(), it_bbsDTO.getStep());
		// 답글등록
		cnt2 = sqlSession.insert("mappers.itbbs.reply", it_bbsDTO);

		return cnt2;
	}

	// 이전답글 step 업데이트
	private int updateStep(int bgroup, int step) {
		Map<String, Object> map = new HashMap<>();
		map.put("bgroup", bgroup);
		map.put("step", step);
		return sqlSession.update("mappers.itbbs.updateStep", map);
	}

	// 게시글 총계
	@Override
	public int totalRec() throws Exception {
		return sqlSession.selectOne("mappers.itbbs.totalRec");
	}

	// 검색목록
	@Override
	public List<IT_bbsDTO> list(int startRecord, int endRecord, String searchType, String keyword) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("startRecord", startRecord);
		map.put("endRecord", endRecord);
		map.put("searchType", searchType);
		logger.info("서치타입: " + searchType);
		map.put("keyword", keyword);

		return sqlSession.selectList("mappers.itbbs.flist", map);
	}

	// 검색 총계
	@Override
	public int SearchTotalRec(String searchType, String keyword) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("searchType", searchType);
		map.put("keyword", keyword);

		return sqlSession.selectOne("mappers.itbbs.searchTotalRec", map);

	}

}