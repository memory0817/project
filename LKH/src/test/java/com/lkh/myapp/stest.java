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
public class stest {
	
	private static Logger logger = LoggerFactory.getLogger("stest.class");
	
	@Inject
	//AdminscheduleDAOImplXML xml;
	AdminscheduleSvc asSvc;

	

	@Test 
	void selectList() {
		List<AdminscheduleDTO> list = asSvc.slist();
		logger.info("list : " + list.toString());
		
	}

	
	
	
}
