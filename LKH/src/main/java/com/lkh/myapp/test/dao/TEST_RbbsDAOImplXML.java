package com.lkh.myapp.test.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.lkh.myapp.test.dto.TEST_RbbsDTO;

@Repository("test_RbbsDAOImplXML")
public class TEST_RbbsDAOImplXML implements TEST_RbbsDAO {

	private static final Logger logger = LoggerFactory.getLogger(TEST_RbbsDAOImplXML.class);

	@Inject
	SqlSession sql;

	// 댓글 등록
	@Override
	public int write(TEST_RbbsDTO test_rbbsDTO) throws Exception {
		return sql.insert("mappers.testrbbs.write", test_rbbsDTO);
	}

	// 댓글 목록
	@Override
	public List<TEST_RbbsDTO> list(String num) throws Exception {
		return sql.selectList("mappers.testrbbs.listOld", num);
	}

	// 댓글 목록 요청페이지
	@Override
	public List<TEST_RbbsDTO> list(String num, int startRec, int endRec) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("num", num);
		map.put("startRec", startRec);
		map.put("endRec", endRec);
		return sql.selectList("mappers.testrbbs.list", map);
	}

	// 댓글 수정
	@Override
	public int modify(TEST_RbbsDTO test_rbbsDTO) throws Exception {
		return sql.update("mappers.testrbbs.modify", test_rbbsDTO);
	}

	// 댓글 삭제
	@Override
	public int delete(String rnum) throws Exception {
		int cnt = 0;

		if (isReply(rnum)) {
			cnt = sql.update("mappers.testrbbs.update_isdel", rnum);
		} else {
			cnt = sql.delete("mappers.testrbbs.delete", rnum);
		}
		return cnt;
	}

	private boolean isReply(String rnum) {
		boolean isYN = false;
		int cnt = 0;
		cnt = sql.selectOne("mappers.testrbbs.isReply", rnum);
		if (cnt > 0) {
			isYN = true;
		}
		return isYN;
	}

	// 대댓글 등록
	@Override
	public int reply(TEST_RbbsDTO test_rbbsDTO) throws Exception {
		logger.info("대댓글등록");
		int cnt1 = 0, cnt2 = 0;

		// 댓글대상 읽어오기
		TEST_RbbsDTO originDTO = replyView(test_rbbsDTO.getRrnum());
		logger.info("originDTO: " + originDTO);
		// 이전답글 step 업데이트(원글그룹에 대한 새로정렬 재정의)
		cnt1 = updateStep(originDTO.getRgroup(), originDTO.getRstep());

		Map<String, Object> map = new HashMap<>();
		map.put("originDTO", originDTO);
		map.put("test_rbbsDTO", test_rbbsDTO);
		logger.info("map" + map);
		cnt2 = sql.insert("mappers.testrbbs.reply", map);

		return cnt2;
	}

	// 이전답글 step 업데이트 동일그룹의 댓글중에 동일스텝의 글이 있으면 +1갱신
	private int updateStep(int rgroup, int rstep) {
		Map<String, Object> map = new HashMap<>();
		map.put("rgroup", rgroup);
		map.put("rstep", rstep);
		return sql.update("mappers.testrbbs.updateStep", map);
	}

	// 댓글대상 읽어오기
	private TEST_RbbsDTO replyView(Integer rrnum) {
		TEST_RbbsDTO testrdto = null;
		testrdto = sql.selectOne("mappers.testrbbs.replyView", rrnum);
		logger.info("dgto: " + testrdto);
		return testrdto;
	}

	// 댓글 통계
	@Override
	public int replyTotalRec(String num) throws Exception {
		return sql.selectOne("mappers.testrbbs.replyTotalRec", num);
	}

}
