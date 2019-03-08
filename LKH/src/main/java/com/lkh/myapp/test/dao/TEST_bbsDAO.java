package com.lkh.myapp.test.dao;

import java.util.List;

import com.lkh.myapp.test.dto.TEST_bbsDTO;





public interface TEST_bbsDAO {

	// 글쓰기
	int write(TEST_bbsDTO test_bbsDTO) throws Exception;

	// 글목록
	List<TEST_bbsDTO> list() throws Exception;

	List<TEST_bbsDTO> list(int startRec, int endRec) throws Exception;

	// 글읽기
	TEST_bbsDTO view(String num) throws Exception;

	// 글수정
	int modify(TEST_bbsDTO test_bbsDTO) throws Exception;

	// 글삭제
	int delete(String num) throws Exception;

	// 원글가져오기
	TEST_bbsDTO replyView(String num) throws Exception;

	// 답글쓰기
	int reply(TEST_bbsDTO test_bbsDTO) throws Exception;

	// 게시글 총계
	int totalRec() throws Exception;

	// 검색목록
	List<TEST_bbsDTO> list(int startRecord, int endRecord, String searchType, String keyword) throws Exception;

	// 검색 총계
	int SearchTotalRec(String searchType, String keyword) throws Exception;

}