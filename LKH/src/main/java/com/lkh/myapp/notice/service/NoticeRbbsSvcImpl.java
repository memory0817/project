package com.lkh.myapp.notice.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lkh.myapp.notice.dao.NoticeRbbsDAO;
import com.lkh.myapp.notice.dto.NoticeRbbsDTO;

@Service
public class NoticeRbbsSvcImpl implements NoticeRbbsSvc {

	
	@Inject
	@Qualifier("noticeRbbsDAOImplXML")
	NoticeRbbsDAO nrbbsdao;
	
	//댓글 등록
	@Override
	public int write(NoticeRbbsDTO noticerbbsDTO) throws Exception {
		int cnt = 0;
		cnt = nrbbsdao.write(noticerbbsDTO);
		return cnt;
	}

	//댓글 목록
	@Override
	public List<NoticeRbbsDTO> list(String num) throws Exception {
		List<NoticeRbbsDTO> list = null;
		list = nrbbsdao.list(num);
		return list;
	}

	//댓글 목록 요청페이지
	@Override
	public List<NoticeRbbsDTO> list(String num, int startRec, int endRec) throws Exception {
		List<NoticeRbbsDTO> list = null;
		list = nrbbsdao.list(num, startRec, endRec);
		return list;
	}

	//댓글 수정
	@Override
	public int modify(NoticeRbbsDTO noticerbbsDTO) throws Exception {
		int cnt = 0;
		cnt = nrbbsdao.modify(noticerbbsDTO);
		return cnt;
	}

	//댓글 삭제
	@Override
	public int delete(String rnum) throws Exception {
		int cnt = 0;
		cnt = nrbbsdao.delete(rnum);
		return cnt;
	}

	//대댓글 등록
	@Override
	public int reply(NoticeRbbsDTO noticerbbsDTO) throws Exception {
		int cnt = 0;
		cnt = nrbbsdao.reply(noticerbbsDTO);
		return cnt;
	}

	//댓글 통계
	@Override
	public int replyTotalRec(String num) throws Exception {
		int cnt = 0;
		cnt = nrbbsdao.replyTotalRec(num);
		return cnt;
	}

}
