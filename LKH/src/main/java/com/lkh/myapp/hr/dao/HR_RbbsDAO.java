package com.lkh.myapp.hr.dao;

import java.util.List;

import com.lkh.myapp.hr.dto.HR_RbbsDTO;

public interface HR_RbbsDAO {

	// 댓글 등록
	int write(HR_RbbsDTO hr_rbbsDTO) throws Exception;

	// 댓글 목록
	List<HR_RbbsDTO> list(String num) throws Exception;

	List<HR_RbbsDTO> list(String num, int startRec, int endRec) throws Exception;

	// 댓글 수정
	int modify(HR_RbbsDTO hr_rbbsDTO) throws Exception;

	// 댓글 삭제
	int delete(String rnum) throws Exception;

	// 대댓글 등록
	int reply(HR_RbbsDTO hr_rbbsDTO) throws Exception;

	// 댓글 통계
	int replyTotalRec(String num) throws Exception;

}
