package com.lkh.myapp.member.dto;

import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class ProfilDTO {
	
	private String id;
	private String randomfile;
	private String originfile;
}
