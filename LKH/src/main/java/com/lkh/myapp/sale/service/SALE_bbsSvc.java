package com.lkh.myapp.sale.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.lkh.myapp.sale.dto.SALE_bbsDTO;


public interface SALE_bbsSvc {
	// 글쓰기
	int write(SALE_bbsDTO sale_bbsDTO) throws Exception;

	// 글목록
	List<SALE_bbsDTO> list() throws Exception;

	List<SALE_bbsDTO> list(int startRec, int endRec) throws Exception;

	// 글읽기
	SALE_bbsDTO view(String num) throws Exception;

	// 글수정
	int modify(SALE_bbsDTO sale_bbsDTO) throws Exception;

	// 글삭제
	int delete(String num) throws Exception;

	// 원글가져오기
	SALE_bbsDTO replyView(String num) throws Exception;

	// 답글쓰기
	int reply(SALE_bbsDTO sale_bbsDTO) throws Exception;

	// 게시글 총계
	int totalRec() throws Exception;

	// 검색목록
	List<SALE_bbsDTO> list(int startRecord, int endRecord, String searchType, String keyword) throws Exception;

	public void list(HttpServletRequest request, Model model) throws Exception;

	// 검색 총계
	int SearchTotalRec(String searchType, String keyword) throws Exception;
}
