package com.lkh.myapp.test.dto;

import java.sql.Date;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class TEST_bbsDTO {

	private int num;         //게시글번호
	@Size(min=4, max=30, message="제목은 4~30자 입력가능합니다!")
	private String title;    //제목
	private String name;  	   //작성자아이디
	private String division;  //작성자부서
	private String position;  //작성자부서	
	private Date cdate;      //작성일
	private Date udate;      //수정일
	private int hit;         //조회수
	@Size(min=4, max=300, message="내용은 4~100자 입력가능합니다!")
	private String content;  //본문내용
	private int bgroup;       //답변그룹
	private int step;        //답변글의 단계
	private int indent;      //답변글의 들여쓰기
	private String isdel;        //답글존재유무
	

}