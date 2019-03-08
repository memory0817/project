package com.lkh.myapp.admin.schedule.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.lkh.myapp.admin.schedule.dto.AdminscheduleDTO;

@Repository(value="adminscheduleDAOImplXML")
public class AdminscheduleDAOImplXML implements AdminscheduleDAO{
	
	private static final Logger logger = LoggerFactory.getLogger(AdminscheduleDAOImplXML.class);
	
	@Inject
	SqlSession sql;

	// 모든 스케줄 출력
	@Override
	public List<AdminscheduleDTO> slist() {
		
		return sql.selectList("mappers.aschedule.slist");
		
	}

	// 스케줄 등록
	@Override
	public boolean addschedule(AdminscheduleDTO asdto) throws Exception{
		boolean result = false;
		int cnt =  sql.insert("mappers.aschedule.addschedule", asdto);
		if(cnt > 0) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean delschedule(String sname) throws Exception {
		boolean result = false;
		int cnt =  sql.insert("mappers.aschedule.delschedule", sname);
		if(cnt > 0) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean updateschedule(AdminscheduleDTO asdto) throws Exception {
		logger.info("수정호출");
		logger.info(asdto.toString());

		boolean result = false;
		int cnt =  sql.insert("mappers.aschedule.updateschedule", asdto);
		if(cnt > 0) {
			result = true;
		}
		logger.info("결과 : " +result);
		return result;
	}

	

}
