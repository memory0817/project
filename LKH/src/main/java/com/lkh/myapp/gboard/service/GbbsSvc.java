package com.lkh.myapp.gboard.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.lkh.myapp.gboard.dto.GbbsDTO;

public interface GbbsSvc {

	// 글쓰기
	int write(GbbsDTO gbbsDTO) throws Exception;

	// 글목록
	List<GbbsDTO> list() throws Exception;

	List<GbbsDTO> list(int startRec, int endRec) throws Exception;

	// 글읽기
	GbbsDTO view(String num) throws Exception;

	// 글수정
	int modify(GbbsDTO gbbsDTO) throws Exception;

	// 글삭제
	int delete(String num) throws Exception;

	// 게시글 총계
	int totalRec() throws Exception;

	// 검색목록
	List<GbbsDTO> list(int startRecord, int endRecord, String searchType, String keyword) throws Exception;

	void list(HttpServletRequest request, Model model) throws Exception;

	// 검색 총계
	int SearchTotalRec(String searchType, String keyword) throws Exception;

}