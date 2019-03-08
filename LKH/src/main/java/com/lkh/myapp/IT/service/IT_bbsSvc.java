package com.lkh.myapp.IT.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.lkh.myapp.IT.dto.IT_bbsDTO;

public interface IT_bbsSvc {

	// 글쓰기
	int write(IT_bbsDTO it_bbsDTO) throws Exception;

	// 글목록
	List<IT_bbsDTO> list() throws Exception;

	List<IT_bbsDTO> list(int startRec, int endRec) throws Exception;

	// 글읽기
	IT_bbsDTO view(String num) throws Exception;

	// 글수정
	int modify(IT_bbsDTO it_bbsDTO) throws Exception;

	// 글삭제
	int delete(String num) throws Exception;

	// 원글가져오기
	IT_bbsDTO replyView(String num) throws Exception;

	// 답글쓰기
	int reply(IT_bbsDTO it_bbsDTO) throws Exception;

	// 게시글 총계
	int totalRec() throws Exception;

	// 검색목록
	List<IT_bbsDTO> list(int startRecord, int endRecord, String searchType, String keyword) throws Exception;

	public void list(HttpServletRequest request, Model model) throws Exception;

	// 검색 총계
	int SearchTotalRec(String searchType, String keyword) throws Exception;

}