package com.lkh.myapp.IT.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lkh.myapp.IT.dao.IT_RbbsDAO;
import com.lkh.myapp.IT.dto.IT_RbbsDTO;
import com.lkh.myapp.notice.service.NoticeRbbsSvc;

@Service
public class IT_RbbsSvcImpl implements IT_RbbsSvc {

	
	@Inject
	@Qualifier("it_RbbsDAOImplXML")
	
	IT_RbbsDAO it_rbbsdao;
	
	//댓글 등록
	@Override
	public int write(IT_RbbsDTO it_rbbsDTO) throws Exception {
		int cnt = 0;
		cnt = it_rbbsdao.write(it_rbbsDTO);
		return cnt;
	}

	//댓글 목록
	@Override
	public List<IT_RbbsDTO> list(String num) throws Exception {
		List<IT_RbbsDTO> list = null;
		list = it_rbbsdao.list(num);
		return list;
	}

	//댓글 목록 요청페이지
	@Override
	public List<IT_RbbsDTO> list(String num, int startRec, int endRec) throws Exception {
		List<IT_RbbsDTO> list = null;
		list = it_rbbsdao.list(num, startRec, endRec);
		return list;
	}

	//댓글 수정
	@Override
	public int modify(IT_RbbsDTO it_rbbsDTO) throws Exception {
		int cnt = 0;
		cnt = it_rbbsdao.modify(it_rbbsDTO);
		return cnt;
	}

	//댓글 삭제
	@Override
	public int delete(String rnum) throws Exception {
		int cnt = 0;
		cnt = it_rbbsdao.delete(rnum);
		return cnt;
	}

	//대댓글 등록
	@Override
	public int reply(IT_RbbsDTO it_rbbsDTO) throws Exception {
		int cnt = 0;
		cnt = it_rbbsdao.reply(it_rbbsDTO);
		return cnt;
	}

	//댓글 통계
	@Override
	public int replyTotalRec(String num) throws Exception {
		int cnt = 0;
		cnt = it_rbbsdao.replyTotalRec(num);
		return cnt;
	}

}
