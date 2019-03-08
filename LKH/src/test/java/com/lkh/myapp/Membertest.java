package com.lkh.myapp;

import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import com.lkh.myapp.admin.schedule.dao.AdminscheduleDAOImplXML;
import com.lkh.myapp.admin.schedule.dto.AdminscheduleDTO;
import com.lkh.myapp.admin.schedule.service.AdminscheduleSvc;
import com.lkh.myapp.admin.schedule.service.AdminscheduleSvcImplXML;
import com.lkh.myapp.member.dao.MemberDAOImpl;
import com.lkh.myapp.member.dto.MemberDTO;
import com.lkh.myapp.notice.dao.NoticeDAOImplXML;
import com.lkh.myapp.notice.dto.NoticeDTO;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class Membertest {
	
	private static Logger logger = LoggerFactory.getLogger("memtest.class");
	
	@Inject
	AdminscheduleDAOImplXML xml;
	//AdminscheduleSvc asSvc;
	
//	private String id; 	    //사원아이디
//	private String division; 	//사원부서
//	private String position; 	//사원직급
//	private String pw; 			//비밀번호
//	private int snumber; 		//사원주민번호
//	private int memberno; 		//사번
//	private int tel; 			//전화번호
//	private String address;  	//주소
//	private Date cdate;			//입사일
//	private int logincount; 	//로그인시도
//	private String name; 		//이름
 
	

//		@Test 
//		void selectList() {
//			AdminscheduleDTO asdto = new AdminscheduleDTO();
//			asdto.setGrade("A");
//			asdto.setSchedule("2018-01-03");
//			try {
//				asSvc.addschedule(asdto);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		}
	
	
	
}
