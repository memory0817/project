package com.lkh.myapp.test.dto;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lkh.myapp.gboard.dto.G_RbbsDTO;

import lombok.Data;

@Entity
@Data
public class TEST_RbbsDTO {

	private int rnum; // 댓글번호
	private int num; // 원글번호
	private String rid; // 작성자정보
	private String loginid; // 작성자id
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Timestamp rcdate; // 작성일
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Timestamp rudate; // 수정일
	private String rcontent; // 댓글내용
	private int rgroup; // 댓글그룹
	private int rstep; // 댓글의 단계
	private String isdel;
	private Integer rrnum; // 대댓글
	private int rindent; // 댓글들여쓰기


	private TEST_RbbsDTO test_rdto; // 대댓글정보

}
