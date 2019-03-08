package com.lkh.myapp.gboard.dao;

import java.util.List;

import com.lkh.myapp.gboard.dto.G_RbbsDTO;

public interface G_RbbsDAO {
	
	

	//댓글 등록
	int write(G_RbbsDTO g_rbbsDTO) throws Exception;
	
	//댓글 목록
	List<G_RbbsDTO> list(String num) throws Exception;
	List<G_RbbsDTO> list(String num, int startRec, int endRec) throws Exception;
	
	//댓글 수정
	int modify(G_RbbsDTO g_rbbsDTO) throws Exception;
	
	//댓글 삭제
	int delete(String rnum) throws Exception;
		
	
	//대댓글 등록
	int reply(G_RbbsDTO g_rbbsDTO) throws Exception;
	
		
	//댓글 통계
	int replyTotalRec(String num) throws Exception;
	
	

}
