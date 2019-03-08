package com.lkh.myapp.hr.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.lkh.myapp.hr.dto.HR_bbsDTO;


@Repository(value = "hr_bbsDAOImplXML")
public class HR_bbsDAOImplXML implements HR_bbsDAO {

	private static final Logger logger = LoggerFactory.getLogger(HR_bbsDAOImplXML.class);

	@Inject
	SqlSession sqlSession;

	// 글쓰기
	@Override
	public int write(HR_bbsDTO hr_bbsDTO) throws Exception {
		return sqlSession.insert("mappers.hrbbs.write", hr_bbsDTO);
	}

	// 글목록
	@Override
	public List<HR_bbsDTO> list() throws Exception {
		return sqlSession.selectList("mappers.hrbbs.listOld");
	}

	// 글목록 페이지요청
	@Override
	public List<HR_bbsDTO> list(int startRec, int endRec) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("startRec", startRec);
		map.put("endRec", endRec);
		return sqlSession.selectList("mappers.hrbbs.list", map);
	}

	// 글읽기
	@Override
	public HR_bbsDTO view(String num) throws Exception {
		HR_bbsDTO hr_bbsDTO = null;

		hr_bbsDTO = sqlSession.selectOne("mappers.hrbbs.view", num);

		// 조회수 증가
		updateHit(hr_bbsDTO.getNum());
		return hr_bbsDTO;
	}

	// 조회수 증가메소드
	private void updateHit(int num) {
		sqlSession.update("mappers.hrbbs.updateHit", num);

	}

	// 글수정
	@Override
	public int modify(HR_bbsDTO hr_bbsDTO) throws Exception {
		return sqlSession.update("mappers.hrbbs.modify", hr_bbsDTO);
	}

	@Override
	public int delete(String rnum) throws Exception {
		int cnt = 0;

		if (isReply(rnum)) {
			cnt = sqlSession.update("mappers.hrbbs.update_isdel", rnum);
		} else {
			cnt = sqlSession.delete("mappers.hrbbs.delete", rnum);
		}
		return cnt;
	}

	private boolean isReply(String rnum) {
		boolean isYN = false;
		int cnt = 0;
		cnt = sqlSession.selectOne("mappers.hrbbs.isReply", rnum);
		if (cnt > 0) {
			isYN = true;
		}
		return isYN;
	}

	// 원글가져오기
	@Override
	public HR_bbsDTO replyView(String num) throws Exception {
		return sqlSession.selectOne("mappers.hrbbs.replyView", num);
	}

	// 답글쓰기
	@Override
	public int reply(HR_bbsDTO hr_bbsDTO) throws Exception {
		int cnt1 = 0, cnt2 = 0;

		// 이전 답글 step 업데이트(원글그룹에 대한 세로정렬 재정의)
		cnt1 = updateStep(hr_bbsDTO.getBgroup(), hr_bbsDTO.getStep());
		// 답글등록
		cnt2 = sqlSession.insert("mappers.hrbbs.reply", hr_bbsDTO);

		return cnt2;
	}

	// 이전답글 step 업데이트
	private int updateStep(int bgroup, int step) {
		Map<String, Object> map = new HashMap<>();
		map.put("bgroup", bgroup);
		map.put("step", step);
		return sqlSession.update("mappers.hrbbs.updateStep", map);
	}

	// 게시글 총계
	@Override
	public int totalRec() throws Exception {
		return sqlSession.selectOne("mappers.hrbbs.totalRec");
	}

	// 검색목록
	@Override
	public List<HR_bbsDTO> list(int startRecord, int endRecord, String searchType, String keyword) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("startRecord", startRecord);
		map.put("endRecord", endRecord);
		map.put("searchType", searchType);
		logger.info("서치타입: " + searchType);
		map.put("keyword", keyword);

		return sqlSession.selectList("mappers.hrbbs.flist", map);
	}

	// 검색 총계
	@Override
	public int SearchTotalRec(String searchType, String keyword) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("searchType", searchType);
		map.put("keyword", keyword);

		return sqlSession.selectOne("mappers.hrbbs.searchTotalRec", map);

	}

}