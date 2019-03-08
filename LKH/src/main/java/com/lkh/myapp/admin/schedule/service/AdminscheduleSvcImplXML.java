package com.lkh.myapp.admin.schedule.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.lkh.myapp.admin.schedule.dao.AdminscheduleDAO;
import com.lkh.myapp.admin.schedule.dto.AdminscheduleDTO;

@Service
public class AdminscheduleSvcImplXML implements AdminscheduleSvc{
	
	private static final Logger logger = LoggerFactory.getLogger(AdminscheduleSvcImplXML.class);

	@Autowired
	@Inject 
	@Qualifier("adminscheduleDAOImplXML")
	AdminscheduleDAO asdao;

	
	// 모든 스케줄 출력
	@Override
	public List<AdminscheduleDTO> slist() {
		List<AdminscheduleDTO> list = null;
		list = asdao.slist();
		return list;
	}
	
	// 스케줄 등록
	@Override
	public boolean addschedule(AdminscheduleDTO asdto) throws Exception {
		boolean result = asdao.addschedule(asdto);
		return result;
	}

	@Override
	public boolean delschedule(String sname) throws Exception {
		boolean result = asdao.delschedule(sname);
		return result;
	}

	@Override
	public boolean updateschedule(AdminscheduleDTO asdto) throws Exception {
		boolean result = asdao.updateschedule(asdto);
		return result;
	}




	
	
	
	

}
