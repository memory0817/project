package com.lkh.myapp.member.dto;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.lkh.myapp.authority.dto.AuthorityDTO;

import lombok.Data;

@Entity
@Data
public class MemberDTO implements UserDetails{
	
	//@Pattern(regexp="^\\w+@\\w+\\.\\w+(\\.\\w+)?$", message="ex)aaa@bbb.com")
	private String id; 			//사원아이디
	private String division; 	//사원부서
	private String position; 	//사원직급
	//@Size(min=6,max=30, message="비밀번호는 6~30자리로 입력바랍니다.")
	private String pw; 			//현재비밀번호
	private String newpw; 		//새비밀번호
	private String snumber; 		//사원주민번호
	private String memberno; 		//사번
	private String tel; 		//전화번호
	private String address;  	//주소
	private String cdate;		//입사일
	private int logincount; 	//로그인시도
	private String name; 		//이름
	private String email;		//비밀번호 찾기 시 사용되는 이메일 주소
	private String originfile;	//원본파일이름
	private String savefile;	//서버에 저장된 파일이름
	
	private List<AuthorityDTO> authorities; //권한
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}
	
	public void setAuthorities(List<AuthorityDTO> authorities) {
		this.authorities = authorities;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return pw;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return id;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public static MemberDTO current() {
		try {
			return (MemberDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}
	
}