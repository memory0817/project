package com.lkh.myapp.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lkh.myapp.member.dto.MemberDTO;
import com.lkh.myapp.member.dto.ProfilDTO;
import com.lkh.myapp.member.service.MemberSvc;
import com.lkh.myapp.notice.dto.NoticeDTO;
import com.lkh.myapp.notice.service.NoticeSvc;
import com.lkh.myapp.util.RecordCriteria;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@PreAuthorize("hasAnyRole('ROLE_HR','ROLE_IT','ROLE_SALE','ROLE_TEST','ROLE_ADMIN')")
@Controller
@RequestMapping("/member")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	
	@Inject
	MemberSvc memberSvc;
	
	@Inject
	NoticeSvc nsvc;

	// 사원 메인페이지 이동
	@RequestMapping("/main")
	public String main(HttpServletRequest request, Model model,  HttpSession session) {
		model.addAttribute("mdto", new MemberDTO());
		// 로그인 세션 없으면 로그인 화면으로 유도
		try {
			nsvc.list(request, model);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return "/member/main";
	}

	// 사원등록양식페이지
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping("/memberJoinForm")
	public String memberJoinForm(Model model) {
		model.addAttribute("mdto", new MemberDTO());
		return "/member/memberJoinForm";
	}

	// 사원등록처리
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/memberJoin", method = RequestMethod.POST)
	public String memberJoin(@Valid @ModelAttribute("mdto") MemberDTO mdto, BindingResult result, Model model) {
		logger.info("/member/memberJoin 호출됨!");
		logger.info(mdto.toString());
		boolean success = false;

		if (result.hasErrors()) {

			logger.info(result.toString());
			logger.info("회원가입시 오류발생!!");
			return "/member/memberJoinForm";
		}

		try {
			
			success = memberSvc.insert(mdto);
			logger.info("id : " + mdto.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("result", success);
		return "forward:/admin/memberlist";
	}


	// 내정보 이동
	@RequestMapping("/myinfo")
	public String myinfo() {

		return "/member/memberinfo";
	}

	// 회원개인정보수정페이지
	@RequestMapping(value = "/memberModifyForm/{id:.+}")
	public String memberModifyForm(@PathVariable String id, Model model) {
		logger.info("/memberModifyForm");

		MemberDTO mdto = null;

		try {
			mdto = memberSvc.getMembernopw(id);
			model.addAttribute("mdto", mdto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/member/memberModifyForm";
	}

	// 회원개인정보수정처리
	@RequestMapping(value = "/memberModify", method = RequestMethod.POST)
	public String memberModify(@Valid @ModelAttribute("mdto") MemberDTO mdto, BindingResult result) {
		logger.info("/memberModify");
		boolean success = false;

		if (result.hasErrors()) {
			return "/member/memberModify";
		}
		try {
			success = memberSvc.modify(mdto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.info("수정처리 결과:" + success);
//		return "/member/memberList";
		return "forward:/member/myinfo";
	}

	// 비밀번호 변경 페이지
	@RequestMapping(value = "/memberPwModifyForm/{id:.+}")
	public String membePwModifyForm(@PathVariable String id, Model model) {
		logger.info("/memberModifyForm");

		MemberDTO mdto = null;
		try {
			mdto = memberSvc.getMembernopw(id);
			model.addAttribute("mdto", mdto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/member/memberPwModifyForm";
	}



	// 비밀번호 변경처리
	@RequestMapping(value = "/memberPwModify", method = RequestMethod.POST)
	public String memberPwModify(@Valid @ModelAttribute("mdto") MemberDTO mdto, BindingResult result) {
		logger.info("/memberModify");
		boolean success = false;

		if (result.hasErrors()) {
			return "/member/memberPwModify";
		}
		try {
			success = memberSvc.pwmodify(mdto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.info("수정처리 결과:" + success);

		return "forward:/member/myinfo";
	}

	// 회원삭제이동
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping("/memberdelForm/{id:.+}")
	public String memberDeletefrom(@PathVariable String id, Model model) {
		logger.info("/memberdelForm/{id:.+}");

		MemberDTO mdto = null;
		try {
			mdto = memberSvc.getMember(id);
			model.addAttribute("mdto", mdto);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/member/memberdelForm/{id:.+}";
	}

	// 회원삭제처리
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping("/memberDelete/{id:.+}")
	public String memberDelete(@PathVariable String id, Model model) {
		logger.info("/memberDelete/{id:.+}");
		boolean success = false;

		MemberDTO mdto = null;
		try {
			mdto = memberSvc.getMember(id);
			model.addAttribute("mdto", mdto);

			success = memberSvc.delete(id);
			logger.info("삭제처리 결과:" + success);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return "forward:/member/memberList";
	}

	// 사원 목록보기 - 주소록으로 표시
	@RequestMapping("/addressbook")
	public String list(HttpServletRequest request, Model model, HttpSession session) {

		try {
			memberSvc.list(request, model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/member/addressbook";
	}
	
	// 공지사항목록
	@RequestMapping("/notice")
	public String notice(HttpServletRequest request, Model model,  HttpSession session) {
		try {
			nsvc.list(request, model);
		} catch (Exception e) {
			e.printStackTrace();
		}	
			return "/member/membernotice";
	}
	
	// 공지사항읽기
		@RequestMapping("/noticeread")
		public String view(@ModelAttribute("noticeDTO") NoticeDTO noticeDTO, Model model, HttpServletRequest request){
			
			RecordCriteria rc = null;
			try {
				rc = new RecordCriteria(Integer.parseInt(request.getParameter("reqPage")));
				noticeDTO = nsvc.view(request.getParameter("num"));
			} catch (Exception e) {
				e.printStackTrace();
			}		
			model.addAttribute("noticeDTO",noticeDTO);
			model.addAttribute("rc",rc);
			
			return "/member/noticeread";
		}
		
		// 아이디 중복검사
		@RequestMapping(value="/idcheck/{id:.+}", method=RequestMethod.PUT)
		public ResponseEntity<String> idcheck(@PathVariable("id") String id) {
			ResponseEntity<String> resCode = null;
			String idcheck = null;
			logger.info("id받아옴" + id);
//			if(id == null) {
//				resCode = new ResponseEntity<String>("fail",HttpStatus.BAD_REQUEST);
//				return resCode;
//			}
			try {
				idcheck = memberSvc.idcheck(id);
				logger.info("result : " + idcheck);
				if(idcheck != null) {
					resCode = new ResponseEntity<String>("success",HttpStatus.OK);	
					logger.info("rescode : " +resCode);
				}
			} catch (Exception e) {
				resCode = new ResponseEntity<String>("fail",HttpStatus.BAD_REQUEST);
				e.printStackTrace();
			}
//			model.addAttribute("idcheckresult", cnt);
			
			return resCode;
		}
		
		@RequestMapping("/profilupload")
		public String profilupload(){
			return "/member/profilupload";
		}
		
		@RequestMapping("/profilmodify")
		public String profilmodify(){
			return "/member/profilmodify";
		}
	

	// 이미지업로드
	//@RequestMapping(value="/upload",method=RequestMethod.POST) /*밑과 같은 의미*/
	@PostMapping("/upload")
	@ResponseBody //restFul 서비스 (뷰를 리턴하지 않고 httpstatus 값을 리턴하도록 설계)	
	public ResponseEntity<String> doUpload(@RequestParam("file") MultipartFile file, @RequestParam("id") String id, Model model,HttpSession session) {

		logger.info("파일 업로드받음");
		ResponseEntity<String> resCode = null;
		String randomFileName = null; //난수파일명
		String originFileName = null; //초기파일명
		String fileLocation ="/Users/jinyoung/Desktop/JAVA/LKH/src/main/webapp/resources/images/upload";
		
		if (!file.isEmpty()) {
			
			randomFileName=UUID.randomUUID().toString();
			originFileName=file.getOriginalFilename();
			
			//초기화면에서 확장자 추출
			int pos= originFileName.lastIndexOf(".");
			String ext = originFileName.substring(pos+1);
			randomFileName= randomFileName + "." + ext;
			File tmpFile = new File(fileLocation, randomFileName);
			logger.info(tmpFile.getPath());
			try {
				//파일시스템에 파일쓰기
				file.transferTo(tmpFile);
				resCode= new ResponseEntity<String>("success",HttpStatus.OK);
				ProfilDTO pdto = new ProfilDTO();
				pdto.setId(id);
				logger.info(pdto.getId());
				pdto.setOriginfile(originFileName);
				pdto.setRandomfile(randomFileName);
				
				memberSvc.upload(pdto);
				model.addAttribute("pdto", pdto);
				session.setAttribute("pdto", pdto);
			}catch (IOException e) {
				e.printStackTrace();
				resCode= new ResponseEntity<String>("fail",HttpStatus.BAD_REQUEST);
				return resCode;
			}
		}
		
		return resCode;
	}
	
	// 이미지업로드
		//@RequestMapping(value="/upload",method=RequestMethod.POST) /*밑과 같은 의미*/
		@PostMapping("/modify")
		@ResponseBody //restFul 서비스 (뷰를 리턴하지 않고 httpstatus 값을 리턴하도록 설계)	
		public ResponseEntity<String> doModify(@RequestParam("file") MultipartFile file, @RequestParam("id") String id, Model model,HttpSession session) {

			logger.info("파일수정 업로드받음");
			ResponseEntity<String> resCode = null;
			String randomFileName = null; //난수파일명
			String originFileName = null; //초기파일명
			String fileLocation ="/Users/jinyoung/Desktop/JAVA/LKH/src/main/webapp/resources/images/upload";
			
			if (!file.isEmpty()) {
				
				randomFileName=UUID.randomUUID().toString();
				originFileName=file.getOriginalFilename();
				
				//초기화면에서 확장자 추출
				int pos= originFileName.lastIndexOf(".");
				String ext = originFileName.substring(pos+1);
				randomFileName= randomFileName + "." + ext;
				File tmpFile = new File(fileLocation, randomFileName);
				logger.info(tmpFile.getPath());
				try {
					//파일시스템에 파일쓰기
					file.transferTo(tmpFile);
					resCode= new ResponseEntity<String>("success",HttpStatus.OK);
					ProfilDTO pdto = new ProfilDTO();
					pdto.setId(id);
					logger.info(pdto.getId());
					pdto.setOriginfile(originFileName);
					pdto.setRandomfile(randomFileName);
					
					memberSvc.modify(pdto);
					model.addAttribute("pdto", pdto);
					session.setAttribute("pdto", pdto);
				}catch (IOException e) {
					e.printStackTrace();
					resCode= new ResponseEntity<String>("fail",HttpStatus.BAD_REQUEST);
					return resCode;
				}
			}
			
			return resCode;
		}

}
