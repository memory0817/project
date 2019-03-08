package com.lkh.myapp.sale.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.lkh.myapp.sale.dto.SALE_bbsDTO;

@Repository(value = "sale_bbsDAOImplXML")
public class SALE_bbsDAOImplXML implements SALE_bbsDAO {
	private static final Logger logger = LoggerFactory.getLogger(SALE_bbsDAOImplXML.class);

	@Inject
	SqlSession sqlSession;

	// 글쓰기
	@Override
	public int write(SALE_bbsDTO sale_bbsDTO) throws Exception {
		return sqlSession.insert("mappers.salebbs.write", sale_bbsDTO);
	}

	// 글목록
	@Override
	public List<SALE_bbsDTO> list() throws Exception {
		return sqlSession.selectList("mappers.salebbs.listOld");
	}

	// 글목록 페이지요청
	@Override
	public List<SALE_bbsDTO> list(int startRec, int endRec) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("startRec", startRec);
		map.put("endRec", endRec);
		return sqlSession.selectList("mappers.salebbs.list", map);
	}

	// 글읽기
	@Override
	public SALE_bbsDTO view(String num) throws Exception {
		SALE_bbsDTO sale_bbsDTO = null;

		sale_bbsDTO = sqlSession.selectOne("mappers.salebbs.view", num);

		// 조회수 증가
		updateHit(sale_bbsDTO.getNum());
		return sale_bbsDTO;
	}

	// 조회수 증가메소드
	private void updateHit(int num) {
		sqlSession.update("mappers.salebbs.updateHit", num);

	}

	// 글수정
	@Override
	public int modify(SALE_bbsDTO sale_bbsDTO) throws Exception {
		return sqlSession.update("mappers.salebbs.modify", sale_bbsDTO);
	}

	@Override
	public int delete(String rnum) throws Exception {
		int cnt = 0;

		if (isReply(rnum)) {
			cnt = sqlSession.update("mappers.salebbs.update_isdel", rnum);
		} else {
			cnt = sqlSession.delete("mappers.salebbs.delete", rnum);
		}
		return cnt;
	}

	private boolean isReply(String rnum) {
		boolean isYN = false;
		int cnt = 0;
		cnt = sqlSession.selectOne("mappers.salebbs.isReply", rnum);
		if (cnt > 0) {
			isYN = true;
		}
		return isYN;
	}

	// 원글가져오기
	@Override
	public SALE_bbsDTO replyView(String num) throws Exception {
		return sqlSession.selectOne("mappers.salebbs.replyView", num);
	}

	// 답글쓰기
	@Override
	public int reply(SALE_bbsDTO sale_bbsDTO) throws Exception {
		int cnt1 = 0, cnt2 = 0;

		// 이전 답글 step 업데이트(원글그룹에 대한 세로정렬 재정의)
		cnt1 = updateStep(sale_bbsDTO.getBgroup(), sale_bbsDTO.getStep());
		// 답글등록
		cnt2 = sqlSession.insert("mappers.salebbs.reply", sale_bbsDTO);

		return cnt2;
	}

	// 이전답글 step 업데이트
	private int updateStep(int bgroup, int step) {
		Map<String, Object> map = new HashMap<>();
		map.put("bgroup", bgroup);
		map.put("step", step);
		return sqlSession.update("mappers.salebbs.updateStep", map);
	}

	// 게시글 총계
	@Override
	public int totalRec() throws Exception {
		return sqlSession.selectOne("mappers.salebbs.totalRec");
	}

	// 검색목록
	@Override
	public List<SALE_bbsDTO> list(int startRecord, int endRecord, String searchType, String keyword) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("startRecord", startRecord);
		map.put("endRecord", endRecord);
		map.put("searchType", searchType);
		logger.info("서치타입: " + searchType);
		map.put("keyword", keyword);

		return sqlSession.selectList("mappers.salebbs.flist", map);
	}

	// 검색 총계
	@Override
	public int SearchTotalRec(String searchType, String keyword) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("searchType", searchType);
		map.put("keyword", keyword);

		return sqlSession.selectOne("mappers.salebbs.searchTotalRec", map);

	}
}
