package com.lkh.myapp.member.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.lkh.myapp.authority.dto.AuthorityDTO;
import com.lkh.myapp.authority.service.AuthoritySvc;
import com.lkh.myapp.member.dao.MemberDAO;
import com.lkh.myapp.member.dto.MemberDTO;
import com.lkh.myapp.member.dto.ProfilDTO;
import com.lkh.myapp.util.FindCriteria;
import com.lkh.myapp.util.PageCriteria;
import com.lkh.myapp.util.RecordCriteria;

@Service
public class MemberSvcImpl implements MemberSvc {

	private static final Logger logger = LoggerFactory.getLogger(MemberSvcImpl.class);

	@Autowired
	@Inject
	@Qualifier("memberDAOImpl")
	MemberDAO mdao;

	@Inject
	AuthoritySvc authoritySvc;

	@Inject
	private PasswordEncoder passwordEncoder;

	// 사원가입
	@Override
	public boolean insert(MemberDTO memberDTO) throws Exception {
		logger.info("memberSvcImpl.insert 호출됨!");
		boolean success = false;
		// 사용자의 실제 비밀번호를 Bcrypt로 암호화함
		memberDTO.setPw(passwordEncoder.encode(memberDTO.getPw()));
		success = mdao.insert(memberDTO);
		logger.info(memberDTO.getId());
		mdao.insertnull(memberDTO.getId());
		logger.info("insertnull실행");
		
		AuthorityDTO authority = new AuthorityDTO();


		if (success) {
			if(memberDTO.getDivision().equals("경영지원")) {
				authority.setRoleId("ROLE_HR");

			}
			if(memberDTO.getDivision().equals("개발")) {
				authority.setRoleId("ROLE_IT");

			}
			if(memberDTO.getDivision().equals("영업")) {
				authority.setRoleId("ROLE_SALE");

			}
			if(memberDTO.getDivision().equals("테스트")) {
				authority.setRoleId("ROLE_TEST");

			}
			authority.setId(memberDTO.getId());
			success = authoritySvc.insert(authority);
		}

		return success;

	}

	// 사원정보수정(회원)
	@Override
	public boolean modify(MemberDTO memberDTO) throws Exception {
		logger.info("memberSvcImpl.modify 호출됨!");
		boolean success = false;
//		memberDTO.setPw(passwordEncoder.encode(memberDTO.getPw()));
//		logger.info(passwordEncoder.encode(memberDTO.getPw()));
		success = mdao.modify(memberDTO);
		return success;
	}

	// 비밀번호 변경 - 사원용
	@Override
	public boolean pwmodify(MemberDTO memberDTO) throws Exception {
		logger.info("memberSvcImpl.pwmodify 호출됨!");
		boolean success = false;
		memberDTO.setNewpw(passwordEncoder.encode(memberDTO.getNewpw()));
		success = mdao.pwmodify(memberDTO);
		return success;
	}

	// 사원정보수정(관리자
	@Override
	public boolean adminmodify(MemberDTO memberDTO) throws Exception {
		logger.info("memberSvcImpl.adminmodify 호출됨!");
		boolean success = false;
		success = mdao.adminmodify(memberDTO);
		return success;
	}

	// 사원정보삭제(관리자
	@Override
	public boolean delete(String id) throws Exception {
		logger.info("memberSvcImpl.delete 호출됨!");
		boolean success = false;
		success = mdao.delete(id);
		return success;
	}
	@Override
	public boolean deleteA(String id) throws Exception {
		logger.info("memberSvcImpl.deleteA 호출됨!");
		boolean success = false;
		success = mdao.deleteA(id);
		return success;
	}

	// 사원 조회
	@Override
	public MemberDTO getMember(String id) throws Exception {
		logger.info("memberSvcImpl.getMember 호출됨!");
		MemberDTO memberDTO = null;
		memberDTO = mdao.getMember(id);
		return memberDTO;
	}
	// 사원 조회
	@Override
	public MemberDTO getMembernopw(String id) throws Exception {
		logger.info("memberSvcImpl.getMember 호출됨!");
		MemberDTO memberDTO = null;
		memberDTO = mdao.getMembernopw(id);
		return memberDTO;
	}

	// 사원 목록 조회
	@Override
	public List<MemberDTO> getMemberList() throws Exception {
		logger.info("memberSvcImpl.getMemberList 호출됨!");
		List<MemberDTO> list = null;
		list = mdao.getMemberList();
		return list;
	}

	@Override
	public void execute(HttpServletRequest request) {
		// TODO Auto-generated method stub

	}

	// 사원 목록 조회
	@Override
	public List<MemberDTO> list(int starRec, int endRec) throws Exception {
		List<MemberDTO> list = null;
		list = mdao.list(starRec, endRec);
		return list;
	}

	@Override
	public int totalRec() throws Exception {
		int cnt = 0;
		cnt = mdao.totalRec();
		return cnt;
	}

	@Override
	public List<MemberDTO> list(int startRecord, int endRecord, String keyword) throws Exception {
		List<MemberDTO> list = null;
		list = mdao.list(startRecord, endRecord, keyword);
		return list;
	}

	@Override
	public void list(HttpServletRequest request, Model model) throws Exception {
		int NUM_PER_PAGE = 10; // 한 페이지에 보여줄 레코드 수
		int PAGE_NUM_PAGE = 10; // 한 페이지에 보여줄 페이지 수

		int reqPage = 0; // 요청 페이지
		int totalRec = 0; // 전체 레코드 수

		String searchType = null; // 검색어 타입
		String keyword = null; // 검색어

		PageCriteria pc = null;
		RecordCriteria rc = null; // 검색조건이 없는 경우의 레코드 시작, 종료페이지 연산
		FindCriteria fc = null; // 검색조건이 있는 경우의 레코드 시작, 종료페이지 연산

		List<MemberDTO> alist = null;

		// 요청 페이지가 없는 경우 1페이지로 이동
		if (request.getParameter("reqPage") == null || request.getParameter("reqPage") == "") {
			reqPage = 1;
		} else {
			reqPage = Integer.parseInt(request.getParameter("reqPage"));
		}

		logger.info("rereqpage : " + reqPage);

		// 검색 매개값 체크(searchType, keyword)

		keyword = request.getParameter("keyword");

		logger.info("keyword: " + keyword);

		if (keyword == null || keyword.trim().isEmpty()) {
			logger.info("검색조건 없어");
			rc = new RecordCriteria(reqPage, NUM_PER_PAGE);
			totalRec = mdao.totalRec();

			pc = new PageCriteria(rc, totalRec, PAGE_NUM_PAGE);
			logger.info("pc = " + pc);

			alist = (ArrayList<MemberDTO>) mdao.list(rc.getStartRecord(), rc.getEndRecord());

			model.addAttribute("list", alist);
			logger.info("alist : " + alist);
			model.addAttribute("pc", pc);
		}

		else {
			logger.info("검색조건이 있는 경우");
			// 검색조건이 있는 경우

			rc = new FindCriteria(reqPage, NUM_PER_PAGE, keyword);

			// 검색목록 총 레코드 수 변환
			totalRec = mdao.SearchTotalRec(((FindCriteria) rc).getKeyword());

			pc = new PageCriteria(rc, totalRec, PAGE_NUM_PAGE);
			logger.info("pc = " + pc);

			alist = mdao.list(rc.getStartRecord(), rc.getEndRecord(), ((FindCriteria) rc).getKeyword());
			logger.info("검색레코드 수 : " + totalRec);
			logger.info("검색목록 : ");
			logger.info(alist + "\n");
			model.addAttribute("list", alist);
			model.addAttribute("pc", pc);
		}

	}

	@Override
	public int SearchTotalRec(String keyword) throws Exception {
		int cnt = 0;
		cnt = mdao.SearchTotalRec(keyword);
		return cnt;
	}

	// 아이디 중복검사
	@Override
	public String idcheck(String id) {
		logger.info("service" + id);
		String idchk = null;

		idchk = mdao.idcheck(id);
		logger.info("service" + idchk);
		return idchk;
	}

	@Override
	public boolean upload(ProfilDTO pdto) {
		boolean success = false;
		success = mdao.upload(pdto);
		return success;
	}

	@Override
	public ProfilDTO select(String id) {
		ProfilDTO pdto = null;
		pdto = mdao.select(id);
		return pdto;
	}

	@Override
	public boolean modify(ProfilDTO pdto) {
		boolean success = false;
		success = mdao.modify(pdto);
		return success;
	}

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		MemberDTO mdto = null;
		try {
			mdto = mdao.getMember(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		if(mdto == null) {
			throw new UsernameNotFoundException("Invalid username/password");
		}
		
		List<AuthorityDTO> authorities = authoritySvc.selectOne(mdto.getId());
		mdto.setAuthorities(authorities);
		logger.info("msi_mdto = " + mdto);
		logger.info("msi_mdto = " + mdto.getUsername());
		logger.info("msi_mdto = " + id);
		return mdto;
	}





}
