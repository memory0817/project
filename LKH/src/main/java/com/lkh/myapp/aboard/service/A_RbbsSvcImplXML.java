package com.lkh.myapp.aboard.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lkh.myapp.aboard.dao.A_RbbsDAO;
import com.lkh.myapp.aboard.dto.A_RbbsDTO;

@Service
public class A_RbbsSvcImplXML implements A_RbbsSvc {

	
	@Inject
	@Qualifier("a_RbbsDAOImplXML")
	
	A_RbbsDAO a_rbbsdao;
	
	//댓글 등록
	@Override
	public int write(A_RbbsDTO A_rdto) throws Exception {
		int cnt = 0;
		cnt = a_rbbsdao.write(A_rdto);
		return cnt;
	}

	//댓글 목록
	@Override
	public List<A_RbbsDTO> list(String num) throws Exception {
		List<A_RbbsDTO> list = null;
		list = a_rbbsdao.list(num);
		return list;
	}

	//댓글 목록 요청페이지
	@Override
	public List<A_RbbsDTO> list(String num, int startRec, int endRec) throws Exception {
		List<A_RbbsDTO> list = null;
		list = a_rbbsdao.list(num, startRec, endRec);
		return list;
	}

	//댓글 수정
	@Override
	public int modify(A_RbbsDTO A_rdto) throws Exception {
		int cnt = 0;
		cnt = a_rbbsdao.modify(A_rdto);
		return cnt;
	}

	//댓글 삭제
	@Override
	public int delete(String rnum) throws Exception {
		int cnt = 0;
		cnt = a_rbbsdao.delete(rnum);
		return cnt;
	}

	//대댓글 등록
	@Override
	public int reply(A_RbbsDTO A_rdto) throws Exception {
		int cnt = 0;
		cnt = a_rbbsdao.reply(A_rdto);
		return cnt;
	}

	//댓글 통계
	@Override
	public int replyTotalRec(String num) throws Exception {
		int cnt = 0;
		cnt = a_rbbsdao.replyTotalRec(num);
		return cnt;
	}

	@Override
	public int goodOrbad(String rnum, String goodOrbad) {
		int cnt = 0;
		cnt = a_rbbsdao.goodOrbad(rnum, goodOrbad);
		return cnt;
	}

}
