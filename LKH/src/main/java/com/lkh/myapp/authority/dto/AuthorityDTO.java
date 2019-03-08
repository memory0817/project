package com.lkh.myapp.authority.dto;


import org.springframework.security.core.GrantedAuthority;

public class AuthorityDTO implements GrantedAuthority{
	
private static final long serialVersionUID = 3749318759125415156L;
	
	private int seq;
	private String id;		//회원아이디
	private String roleid;	//권한영문명
	
	
public AuthorityDTO() { }
	
	public AuthorityDTO(String id, String roleid) {
		this.id = id;
		this.roleid = roleid;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleId() {
		return roleid;
	}

	public void setRoleId(String roleid) {
		this.roleid = roleid;
	}



	@Override
	public String getAuthority() {
		return roleid;
	}

	@Override
	public String toString() {
		return "AuthorityDTO [seq=" + seq + ", id=" + id + ", roleid=" + roleid + "]";
	}
	
}
