package com.lkh.myapp.member.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;
import com.lkh.myapp.member.dto.MemberDTO;
import com.lkh.myapp.member.dto.ProfilDTO;

public interface MemberSvc extends UserDetailsService{

	void execute(HttpServletRequest request);

	// 사원 등록
	public boolean insert(MemberDTO memberDTO) throws Exception;
	

	// 개인정보 수정 - 사원용
	boolean modify(MemberDTO memberDTO) throws Exception;

	// 비밀번호 변경 - 사원용
	boolean pwmodify(MemberDTO memberDTO) throws Exception;

	// 사원 수정(관리자용) : 모든사항 변경
	public boolean adminmodify(MemberDTO memberDTO) throws Exception;

	// 사원 삭제(관리자용) : 삭제는 관리자만 하도록
	public boolean delete(String id) throws Exception;
	// 권한 삭제
	public boolean deleteA(String id) throws Exception;

	// 사원 조회
	public MemberDTO getMember(String id) throws Exception;
	//사원 조회 - 비밀번호 제외
	public MemberDTO getMembernopw(String id)throws Exception;

	// 사원 목록 조회
	public List<MemberDTO> getMemberList() throws Exception;

	List<MemberDTO> list(int starRec, int endRec) throws Exception;

	// 사원 목록 총계
	int totalRec() throws Exception;

	// 이름으로 검색 - 목록
	List<MemberDTO> list(int startRecord, int endRecord, String keyword) throws Exception;

	void list(HttpServletRequest request, Model model) throws Exception;

	// 이름으로 검색 - 총계
	int SearchTotalRec(String keyword) throws Exception;

	// 아이디 중복검사
	public String idcheck(String id);

	// 프로필 사진 업로드
	public boolean upload(ProfilDTO pdto);
	
	// 프로필 사진 수정
	public boolean modify(ProfilDTO pdto);

	// 프로필 사진 정보 가져오기
	public ProfilDTO select(String id);

}
