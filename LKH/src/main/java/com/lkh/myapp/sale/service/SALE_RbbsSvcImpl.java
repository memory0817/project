package com.lkh.myapp.sale.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lkh.myapp.sale.dao.SALE_RbbsDAO;
import com.lkh.myapp.sale.dto.SALE_RbbsDTO;

@Service
public class SALE_RbbsSvcImpl implements SALE_RbbsSvc {

	
	@Inject
	@Qualifier("sale_RbbsDAOImplXML")
	
	SALE_RbbsDAO slae_rbbsdao;
	
	//댓글 등록
	@Override
	public int write(SALE_RbbsDTO sale_rbbsDTO) throws Exception {
		int cnt = 0;
		cnt = slae_rbbsdao.write(sale_rbbsDTO);
		return cnt;
	}

	//댓글 목록
	@Override
	public List<SALE_RbbsDTO> list(String num) throws Exception {
		List<SALE_RbbsDTO> list = null;
		list = slae_rbbsdao.list(num);
		return list;
	}

	//댓글 목록 요청페이지
	@Override
	public List<SALE_RbbsDTO> list(String num, int startRec, int endRec) throws Exception {
		List<SALE_RbbsDTO> list = null;
		list = slae_rbbsdao.list(num, startRec, endRec);
		return list;
	}

	//댓글 수정
	@Override
	public int modify(SALE_RbbsDTO sale_rbbsDTO) throws Exception {
		int cnt = 0;
		cnt = slae_rbbsdao.modify(sale_rbbsDTO);
		return cnt;
	}

	//댓글 삭제
	@Override
	public int delete(String rnum) throws Exception {
		int cnt = 0;
		cnt = slae_rbbsdao.delete(rnum);
		return cnt;
	}

	//대댓글 등록
	@Override
	public int reply(SALE_RbbsDTO sale_rbbsDTO) throws Exception {
		int cnt = 0;
		cnt = slae_rbbsdao.reply(sale_rbbsDTO);
		return cnt;
	}

	//댓글 통계
	@Override
	public int replyTotalRec(String num) throws Exception {
		int cnt = 0;
		cnt = slae_rbbsdao.replyTotalRec(num);
		return cnt;
	}

}
