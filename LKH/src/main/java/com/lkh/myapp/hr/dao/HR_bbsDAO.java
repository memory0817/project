package com.lkh.myapp.hr.dao;

import java.util.List;

import com.lkh.myapp.hr.dto.HR_bbsDTO;



public interface HR_bbsDAO {

	// 글쓰기
	int write(HR_bbsDTO hr_bbsDTO) throws Exception;

	// 글목록
	List<HR_bbsDTO> list() throws Exception;

	List<HR_bbsDTO> list(int startRec, int endRec) throws Exception;

	// 글읽기
	HR_bbsDTO view(String num) throws Exception;

	// 글수정
	int modify(HR_bbsDTO hr_bbsDTO) throws Exception;

	// 글삭제
	int delete(String num) throws Exception;

	// 원글가져오기
	HR_bbsDTO replyView(String num) throws Exception;

	// 답글쓰기
	int reply(HR_bbsDTO hr_bbsDTO) throws Exception;

	// 게시글 총계
	int totalRec() throws Exception;

	// 검색목록
	List<HR_bbsDTO> list(int startRecord, int endRecord, String searchType, String keyword) throws Exception;

	// 검색 총계
	int SearchTotalRec(String searchType, String keyword) throws Exception;

}