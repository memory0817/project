package com.lkh.myapp.admin.schedule.service;

import java.util.List;

import org.springframework.ui.Model;

import com.lkh.myapp.admin.schedule.dto.AdminscheduleDTO;
import com.lkh.myapp.notice.dto.NoticeDTO;

public interface AdminscheduleSvc {
	
	// 모든 스케줄 출력
	public List<AdminscheduleDTO> slist();
	
	// 스케줄 등록
	boolean addschedule(AdminscheduleDTO asdto) throws Exception;
	
	// 스케줄 삭제
	public boolean delschedule(String sname) throws Exception;
	
	// 스케줄 수정
	public boolean updateschedule(AdminscheduleDTO asdto) throws Exception;

}
