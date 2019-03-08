package com.lkh.myapp.notice.dao;

import java.util.List;

import com.lkh.myapp.notice.dto.NoticeRbbsDTO;

public interface NoticeRbbsDAO {

	// 댓글 등록
	int write(NoticeRbbsDTO noticerbbsDTO) throws Exception;

	// 댓글 목록
	List<NoticeRbbsDTO> list(String num) throws Exception;

	List<NoticeRbbsDTO> list(String num, int startRec, int endRec) throws Exception;

	// 댓글 수정
	int modify(NoticeRbbsDTO noticerbbsDTO) throws Exception;

	// 댓글 삭제
	int delete(String rnum) throws Exception;

	// 대댓글 등록
	int reply(NoticeRbbsDTO noticerbbsDTO) throws Exception;

	// 댓글 통계
	int replyTotalRec(String num) throws Exception;

}
