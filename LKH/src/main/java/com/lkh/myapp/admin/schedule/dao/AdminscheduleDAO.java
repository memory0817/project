package com.lkh.myapp.admin.schedule.dao;

import java.util.List;

import com.lkh.myapp.admin.schedule.dto.AdminscheduleDTO;

public interface AdminscheduleDAO {

	// 모든 스케줄 출력
	public List<AdminscheduleDTO> slist();

	// 스케줄 등록
	public boolean addschedule(AdminscheduleDTO asdto) throws Exception;

	// 스케줄 삭제
	public boolean delschedule(String sname) throws Exception;

	// 스케줄 수정
	public boolean updateschedule(AdminscheduleDTO asdto) throws Exception;

}
