package com.lkh.myapp.gboard.dto;

import java.sql.Date;

import javax.persistence.Entity;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class GbbsDTO {

	private int num; // 게시글번호
	@Size(min = 4, max = 30, message = "제목은 4~30자 입력가능합니다!")
	private String title; // 제목
//	@Pattern(regexp = "^\\w+@\\w+\\.\\w+(\\.\\w+)?$", message = "ex)aaa@bbb.com")
	private String id; // 작성자ID
	private String pw; // 작성자PW
	private Date cdate; // 작성일
	private Date udate; // 수정일
	private int hit; // 조회수
	@Size(min = 4, max = 300, message = "내용은 4~100자 입력가능합니다!")
	private String content; // 본문내용

}