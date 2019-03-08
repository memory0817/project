package com.lkh.myapp.hr.service;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.lkh.myapp.hr.dao.HR_bbsDAO;
import com.lkh.myapp.hr.dto.HR_bbsDTO;
import com.lkh.myapp.util.FindCriteria;
import com.lkh.myapp.util.PageCriteria;
import com.lkh.myapp.util.RecordCriteria;

@Service
public class HR_bbsSvcImplXML implements HR_bbsSvc {

	
	private static Logger logger = LoggerFactory.getLogger(HR_bbsSvcImplXML.class);
	
	@Inject
	@Qualifier("hr_bbsDAOImplXML")
	
	HR_bbsDAO hr_dao;
	
	//글쓰기
	@Override
	public int write(HR_bbsDTO hr_bbsDTO) throws Exception {
		int cnt = 0;
		cnt = hr_dao.write(hr_bbsDTO);
		return cnt;
	}

	//글목록
	@Override
	public List<HR_bbsDTO> list() throws Exception {
		List<HR_bbsDTO> list = null;
		list = hr_dao.list();
		return list;
	}

	//글목록 요청
	@Override
	public List<HR_bbsDTO> list(int startRec, int endRec) throws Exception {
		List<HR_bbsDTO> list = null;
		list = hr_dao.list(startRec, endRec);
		return list;
	}

	//글읽기
	@Override
	public HR_bbsDTO view(String num) throws Exception {
		HR_bbsDTO hrdto = null;
		hrdto = hr_dao.view(num);
		return hrdto;
	}

	// 글수정
	@Override
	public int modify(HR_bbsDTO hr_bbsDTO) throws Exception {
		int cnt = 0;
		cnt = hr_dao.modify(hr_bbsDTO);
		return cnt;
	}

	// 글삭제
	@Override
	public int delete(String num) throws Exception {
		int cnt = 0;
		cnt = hr_dao.delete(num);
		return cnt;
	}

	// 원글가져오기
	@Override
	public HR_bbsDTO replyView(String num) throws Exception {
		HR_bbsDTO hrdto = null;
		hrdto = hr_dao.replyView(num);
		return hrdto;
	}

	// 답글쓰기
	@Override
	public int reply(HR_bbsDTO hr_bbsDTO) throws Exception {
		int cnt = 0;
		cnt = hr_dao.reply(hr_bbsDTO);
		return cnt;
	}

	// 게시글 총계
	@Override
	public int totalRec() throws Exception {
		int cnt = 0;
		cnt = hr_dao.totalRec();
		return cnt;
	}

	// 검색목록
	@Override
	public List<HR_bbsDTO> list(int startRecord, int endRecord, String searchType, String keyword) throws Exception {
		List<HR_bbsDTO> list = null;
		list = hr_dao.list(startRecord, endRecord, searchType, keyword);
		return list;
	}

	
	@Override
	public void list(HttpServletRequest request, Model model) throws Exception {
		logger.info("void list(HttpServletRequest request, Model model) 호출됨!");

		int NUM_PER_PAGE = 10;			 //한페이지에 보여줄 레코드수
		int PAGE_NUM_PER_PAGE = 10;		 //한페이지에 보여줄 페이지수
		
		int reqPage = 0;				 //요청페이지
		int totalRec = 0;				 //전체레코드수
		
		String searchType = null; 		//검색타입
		String keyword  = null;   		//검색어

		PageCriteria pc = null;
		RecordCriteria rc= null;        //검색조건이 없는경우의 레코드 시작, 종료페이지연산
		FindCriteria fc = null;         //검색조건이 있는경우의 레코드 시작, 종료페이지 연산 + 검색타입
		
		List<HR_bbsDTO> alist = null;
		
		
		// 요청페이지가 없는 경우 1페이지로이동
		if(request.getParameter("reqPage") == null ||
			request.getParameter("reqPage") == "") {
			reqPage = 1;			
		}else {
			reqPage = Integer.parseInt(request.getParameter("reqPage"));			
		}		
		
		// 검색어 매개값 체크(searchType, keyword)
		searchType = request.getParameter("searchType");
		keyword = request.getParameter("keyword");
		
		if(keyword == null || keyword.trim().isEmpty()) {
			//검색조건이 없는경우			
			
			rc = new RecordCriteria(reqPage, NUM_PER_PAGE);
			totalRec = hr_dao.totalRec();
			
			pc = new PageCriteria(rc,totalRec,PAGE_NUM_PER_PAGE);
			
			alist = hr_dao.list(rc.getStartRecord(), rc.getEndRecord());

			
						
		}else {
			//검색조건이 있는경우
			
			rc = new FindCriteria(reqPage, NUM_PER_PAGE,searchType,keyword);
			
			//검색목록 총레코드수 변환
			totalRec = hr_dao.SearchTotalRec(
					((FindCriteria)rc).getSearchType(),
					((FindCriteria)rc).getKeyword()
					);
			
			pc = new PageCriteria(rc,totalRec,PAGE_NUM_PER_PAGE);
			
			//검색목록
			alist = hr_dao.list(
							rc.getStartRecord(), 
							rc.getEndRecord(),
							((FindCriteria)rc).getSearchType(),
							((FindCriteria)rc).getKeyword()
							);
			
		}
		
		model.addAttribute("list", alist);
		model.addAttribute("pc", pc);
		
	}
	
	
	
	
	// 검색 총계
	@Override
	public int SearchTotalRec(String searchType, String keyword) throws Exception {
		int totalRec = 0;
		totalRec = hr_dao.SearchTotalRec(searchType, keyword);
		return totalRec;
	}

}