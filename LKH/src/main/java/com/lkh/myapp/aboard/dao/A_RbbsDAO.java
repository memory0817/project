package com.lkh.myapp.aboard.dao;

import java.util.List;

import com.lkh.myapp.aboard.dto.A_RbbsDTO;

public interface A_RbbsDAO {

	// 댓글 등록
	int write(A_RbbsDTO A_rdto) throws Exception;

	// 댓글 목록
	List<A_RbbsDTO> list(String num) throws Exception;

	List<A_RbbsDTO> list(String num, int startRec, int endRec) throws Exception;

	// 댓글 수정
	int modify(A_RbbsDTO A_rdto) throws Exception;

	// 댓글 삭제
	int delete(String rnum) throws Exception;

	// 대댓글 등록
	int reply(A_RbbsDTO A_rdto) throws Exception;

	// 댓글 통계
	int replyTotalRec(String num) throws Exception;

	// 댓글 좋아요 싫어요
	int goodOrbad(String rnum, String goodOrbad);

}
