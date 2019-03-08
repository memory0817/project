package com.lkh.myapp.gboard.service;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.lkh.myapp.gboard.dao.GbbsDAO;
import com.lkh.myapp.gboard.dto.GbbsDTO;
import com.lkh.myapp.util.FindCriteria;
import com.lkh.myapp.util.PageCriteria;
import com.lkh.myapp.util.RecordCriteria;

@Service
public class GbbsSvcImplXML implements GbbsSvc {

	private static Logger logger = LoggerFactory.getLogger(GbbsSvcImplXML.class);

	@Inject
	@Qualifier("gbbsDAOImplXML")

	GbbsDAO gbbsdao;

	// 글쓰기
	@Override
	public int write(GbbsDTO gbbsDTO) throws Exception {
		int cnt = 0;
		cnt = gbbsdao.write(gbbsDTO);
		return cnt;
	}

	// 글목록
	@Override
	public List<GbbsDTO> list() throws Exception {
		List<GbbsDTO> list = null;
		list = gbbsdao.list();
		return list;
	}

	// 글목록 요청페이지
	@Override
	public List<GbbsDTO> list(int startRec, int endRec) throws Exception {
		List<GbbsDTO> list = null;
		list = gbbsdao.list(startRec, endRec);
		return list;
	}

	// 글읽기
	@Override
	public GbbsDTO view(String num) throws Exception {
		GbbsDTO gbbsdto = null;
		gbbsdto = gbbsdao.view(num);
		return gbbsdto;
	}

	// 글수정
	@Override
	public int modify(GbbsDTO gbbsDTO) throws Exception {
		int cnt = 0;
		cnt = gbbsdao.modify(gbbsDTO);
		return cnt;
	}

	// 글삭제
	@Override
	public int delete(String num) throws Exception {
		int cnt = 0;
		cnt = gbbsdao.delete(num);
		return cnt;
	}

	// 게시글 총계
	@Override
	public int totalRec() throws Exception {
		int cnt = 0;
		cnt = gbbsdao.totalRec();
		return cnt;
	}

	// 검색목록
	@Override
	public List<GbbsDTO> list(int startRecord, int endRecord, String searchType, String keyword) throws Exception {
		List<GbbsDTO> list = null;
		list = gbbsdao.list(startRecord, endRecord, searchType, keyword);
		return list;
	}

	@Override
	public void list(HttpServletRequest request, Model model) throws Exception {
		logger.info("void list(HttpServletRequest request, Model model) 호출됨!");

		int NUM_PER_PAGE = 10; // 한페이지에 보여줄 레코드수
		int PAGE_NUM_PER_PAGE = 10; // 한페이지에 보여줄 페이지수

		int reqPage = 0; // 요청페이지
		int totalRec = 0; // 전체레코드수

		String searchType = null; // 검색타입
		String keyword = null; // 검색어

		PageCriteria pc = null;
		RecordCriteria rc = null; // 검색조건이 없는경우의 레코드 시작, 종료페이지연산
		FindCriteria fc = null; // 검색조건이 있는경우의 레코드 시작, 종료페이지 연산 + 검색타입

		List<GbbsDTO> alist = null;

		// 요청페이지가 없는 경우 1페이지로이동
		if (request.getParameter("reqPage") == null || request.getParameter("reqPage") == "") {
			reqPage = 1;
		} else {
			reqPage = Integer.parseInt(request.getParameter("reqPage"));
		}

		// 검색어 매개값 체크(searchType, keyword)
		searchType = request.getParameter("searchType");
		keyword = request.getParameter("keyword");

		if (keyword == null || keyword.trim().isEmpty()) {
			// 검색조건이 없는경우

			rc = new RecordCriteria(reqPage, NUM_PER_PAGE);
			totalRec = gbbsdao.totalRec();

			pc = new PageCriteria(rc, totalRec, PAGE_NUM_PER_PAGE);

			alist = gbbsdao.list(rc.getStartRecord(), rc.getEndRecord());

		} else {
			// 검색조건이 있는경우

			rc = new FindCriteria(reqPage, NUM_PER_PAGE, searchType, keyword);

			// 검색목록 총레코드수 변환
			totalRec = gbbsdao.SearchTotalRec(((FindCriteria) rc).getSearchType(), ((FindCriteria) rc).getKeyword());

			pc = new PageCriteria(rc, totalRec, PAGE_NUM_PER_PAGE);

			// 검색목록
			alist = gbbsdao.list(rc.getStartRecord(), rc.getEndRecord(), ((FindCriteria) rc).getSearchType(),
					((FindCriteria) rc).getKeyword());

		}

		model.addAttribute("list", alist);
		model.addAttribute("pc", pc);

	}

	// 검색 총계
	@Override
	public int SearchTotalRec(String searchType, String keyword) throws Exception {
		int totalRec = 0;
		totalRec = gbbsdao.SearchTotalRec(searchType, keyword);
		return totalRec;
	}

}