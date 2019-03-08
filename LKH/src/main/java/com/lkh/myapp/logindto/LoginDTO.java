package com.lkh.myapp.logindto;

import javax.persistence.Entity;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity

public class LoginDTO {

	private String id; //아이디
	private String pw; //비밀번호
	
	private String name; // 이름
	private String division; // 부서
	
	private String rememberme; // 로그인 상태 유지

}



