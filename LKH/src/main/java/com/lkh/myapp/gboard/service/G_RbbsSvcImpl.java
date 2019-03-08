package com.lkh.myapp.gboard.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lkh.myapp.gboard.dao.G_RbbsDAO;
import com.lkh.myapp.gboard.dto.G_RbbsDTO;

@Service
public class G_RbbsSvcImpl implements G_RbbsSvc {

	
	@Inject
	@Qualifier("G_RbbsDAOImplXML")
	
	G_RbbsDAO grbbsdao;
	
	//댓글 등록
	@Override
	public int write(G_RbbsDTO g_rbbsDTO) throws Exception {
		int cnt = 0;
		cnt = grbbsdao.write(g_rbbsDTO);
		return cnt;
	}

	//댓글 목록
	@Override
	public List<G_RbbsDTO> list(String num) throws Exception {
		List<G_RbbsDTO> list = null;
		list = grbbsdao.list(num);
		return list;
	}

	//댓글 목록 요청페이지
	@Override
	public List<G_RbbsDTO> list(String num, int startRec, int endRec) throws Exception {
		List<G_RbbsDTO> list = null;
		list = grbbsdao.list(num, startRec, endRec);
		return list;
	}

	//댓글 수정
	@Override
	public int modify(G_RbbsDTO g_rbbsDTO) throws Exception {
		int cnt = 0;
		cnt = grbbsdao.modify(g_rbbsDTO);
		return cnt;
	}

	//댓글 삭제
	@Override
	public int delete(String rnum) throws Exception {
		int cnt = 0;
		cnt = grbbsdao.delete(rnum);
		return cnt;
	}

	//대댓글 등록
	@Override
	public int reply(G_RbbsDTO g_rbbsDTO) throws Exception {
		int cnt = 0;
		cnt = grbbsdao.reply(g_rbbsDTO);
		return cnt;
	}

	//댓글 통계
	@Override
	public int replyTotalRec(String num) throws Exception {
		int cnt = 0;
		cnt = grbbsdao.replyTotalRec(num);
		return cnt;
	}

}
