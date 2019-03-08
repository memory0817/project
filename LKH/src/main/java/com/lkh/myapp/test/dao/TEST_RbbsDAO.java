package com.lkh.myapp.test.dao;

import java.util.List;

import com.lkh.myapp.test.dto.TEST_RbbsDTO;

public interface TEST_RbbsDAO {

	// 댓글 등록
	int write(TEST_RbbsDTO test_rbbsDTO) throws Exception;

	// 댓글 목록
	List<TEST_RbbsDTO> list(String num) throws Exception;

	List<TEST_RbbsDTO> list(String num, int startRec, int endRec) throws Exception;

	// 댓글 수정
	int modify(TEST_RbbsDTO test_rbbsDTO) throws Exception;

	// 댓글 삭제
	int delete(String rnum) throws Exception;

	// 대댓글 등록
	int reply(TEST_RbbsDTO test_rbbsDTO) throws Exception;

	// 댓글 통계
	int replyTotalRec(String num) throws Exception;

}
