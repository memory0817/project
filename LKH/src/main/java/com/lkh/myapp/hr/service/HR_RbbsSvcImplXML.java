package com.lkh.myapp.hr.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lkh.myapp.hr.dao.HR_RbbsDAO;
import com.lkh.myapp.hr.dto.HR_RbbsDTO;

@Service
public class HR_RbbsSvcImplXML implements HR_RbbsSvc {

	
	@Inject
	@Qualifier("hr_RbbsDAOImplXML")
	
	HR_RbbsDAO hr_rbbsdao;
	
	//댓글 등록
	@Override
	public int write(HR_RbbsDTO hr_rbbsDTO) throws Exception {
		int cnt = 0;
		cnt = hr_rbbsdao.write(hr_rbbsDTO);
		return cnt;
	}

	//댓글 목록
	@Override
	public List<HR_RbbsDTO> list(String num) throws Exception {
		List<HR_RbbsDTO> list = null;
		list = hr_rbbsdao.list(num);
		return list;
	}

	//댓글 목록 요청페이지
	@Override
	public List<HR_RbbsDTO> list(String num, int startRec, int endRec) throws Exception {
		List<HR_RbbsDTO> list = null;
		list = hr_rbbsdao.list(num, startRec, endRec);
		return list;
	}

	//댓글 수정
	@Override
	public int modify(HR_RbbsDTO hr_rbbsDTO) throws Exception {
		int cnt = 0;
		cnt = hr_rbbsdao.modify(hr_rbbsDTO);
		return cnt;
	}

	//댓글 삭제
	@Override
	public int delete(String rnum) throws Exception {
		int cnt = 0;
		cnt = hr_rbbsdao.delete(rnum);
		return cnt;
	}

	//대댓글 등록
	@Override
	public int reply(HR_RbbsDTO hr_rbbsDTO) throws Exception {
		int cnt = 0;
		cnt = hr_rbbsdao.reply(hr_rbbsDTO);
		return cnt;
	}

	//댓글 통계
	@Override
	public int replyTotalRec(String num) throws Exception {
		int cnt = 0;
		cnt = hr_rbbsdao.replyTotalRec(num);
		return cnt;
	}

}
