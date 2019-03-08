package com.lkh.myapp.test.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lkh.myapp.test.dao.TEST_RbbsDAO;
import com.lkh.myapp.test.dto.TEST_RbbsDTO;

@Service
public class TEST_RbbsSvcImpl implements TEST_RbbsSvc {

	@Inject
	@Qualifier("test_RbbsDAOImplXML")

	TEST_RbbsDAO test_rbbsdao;

	// 댓글 등록
	@Override
	public int write(TEST_RbbsDTO test_rbbsDTO) throws Exception {
		int cnt = 0;
		cnt = test_rbbsdao.write(test_rbbsDTO);
		return cnt;
	}

	// 댓글 목록
	@Override
	public List<TEST_RbbsDTO> list(String num) throws Exception {
		List<TEST_RbbsDTO> list = null;
		list = test_rbbsdao.list(num);
		return list;
	}

	// 댓글 목록 요청페이지
	@Override
	public List<TEST_RbbsDTO> list(String num, int startRec, int endRec) throws Exception {
		List<TEST_RbbsDTO> list = null;
		list = test_rbbsdao.list(num, startRec, endRec);
		return list;
	}

	// 댓글 수정
	@Override
	public int modify(TEST_RbbsDTO test_rbbsDTO) throws Exception {
		int cnt = 0;
		cnt = test_rbbsdao.modify(test_rbbsDTO);
		return cnt;
	}

	// 댓글 삭제
	@Override
	public int delete(String rnum) throws Exception {
		int cnt = 0;
		cnt = test_rbbsdao.delete(rnum);
		return cnt;
	}

	// 대댓글 등록
	@Override
	public int reply(TEST_RbbsDTO test_rbbsDTO) throws Exception {
		int cnt = 0;
		cnt = test_rbbsdao.reply(test_rbbsDTO);
		return cnt;
	}

	// 댓글 통계
	@Override
	public int replyTotalRec(String num) throws Exception {
		int cnt = 0;
		cnt = test_rbbsdao.replyTotalRec(num);
		return cnt;
	}

}
