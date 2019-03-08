package com.lkh.myapp.sale.service;

import java.util.List;

import com.lkh.myapp.sale.dto.SALE_RbbsDTO;

public interface SALE_RbbsSvc {
	
	
	
	//댓글 등록
	int write(SALE_RbbsDTO sale_rbbsDTO) throws Exception;
	
	//댓글 목록
	List<SALE_RbbsDTO> list(String num) throws Exception;
	List<SALE_RbbsDTO> list(String num, int startRec, int endRec) throws Exception;
	
	//댓글 수정
	int modify(SALE_RbbsDTO sale_rbbsDTO) throws Exception;
	
	//댓글 삭제
	int delete(String rnum) throws Exception;
		
	
	//대댓글 등록
	int reply(SALE_RbbsDTO sale_rbbsDTO) throws Exception;
	
		
	//댓글 통계
	int replyTotalRec(String num) throws Exception;
	
	

}
