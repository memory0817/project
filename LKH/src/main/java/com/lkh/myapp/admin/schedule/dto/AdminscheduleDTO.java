package com.lkh.myapp.admin.schedule.dto;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class AdminscheduleDTO {
	
	private String grade;	// 스케줄 구분자
	private String startday;	// 스케줄 시작일
	private String endday;	// 스케줄 종료일
	private String sname;	//스케줄 명

}
