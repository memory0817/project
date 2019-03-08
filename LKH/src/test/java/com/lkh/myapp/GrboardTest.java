package com.lkh.myapp;

import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import com.lkh.myapp.gboard.dto.G_RbbsDTO;
import com.lkh.myapp.gboard.service.G_RbbsSvc;


@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})

class GrboardTest {

	private static Logger logger = LoggerFactory.getLogger(GrboardTest.class);
	
	
	@Inject
	G_RbbsSvc grSvc;
	
	G_RbbsDTO grdto;
	List<G_RbbsDTO> list;
	
	int cnt;       //실행 레코드 수
	
	@BeforeEach
	void beforeEach() {
		grdto = new G_RbbsDTO();
	}

	//글쓰기
	@Test @Disabled
	void write() {	
		
	
		grdto.setNum(142);	
		grdto.setLoginid("guest");
		grdto.setRcontent("하하하");
		grdto.setRindent(0);
	
		try {
			grSvc.write(grdto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			
	}	
	
	
	// 댓글수정
	@Test @Disabled
	void modify() {

		grdto.setRcontent("댓글수정테스트1");
		grdto.setRnum(5);
		grdto.setRpw("0000");

		try {
			cnt = grSvc.modify(grdto);
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("수정처리건수 : " + cnt);

	}
	
	
	// 댓글삭제
	@Test @Disabled
	void delete() {
		
		String rnum = "4";

		try {
			cnt = grSvc.delete(rnum);
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("삭제처리건수 : " + cnt);

	}	
	
	
	// 대댓글등록
	@Test @Disabled
	void reply() {
		

		
		grdto.setNum(1); //최초등록글
		grdto.setRcontent("대댓글 테스트3");
		grdto.setRid("0220/개발/대리");
		grdto.setRpw("0000");
		grdto.setRgroup(9); //원글번호 = 원글 그룹
		grdto.setRstep(0+1); //원글 그룹의 세로정렬(답글단계)
		grdto.setRindent(0+1); //원글 그룹의 가로정렬(들여쓰기)
		grdto.setRrnum(9);
		
		try {
			cnt = grSvc.reply(grdto);
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("대댓글등록 : " + cnt);

	}
	
	
	//댓글목록보기
	@Test //@Disabled
	void list1() {
		
		try {
			list = grSvc.list("142",1,10);
			logger.info("목록건수 : " + list.size());
			for(G_RbbsDTO g_rbbsDTO : list) {
				logger.info("목록 : " + g_rbbsDTO.toString());	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
