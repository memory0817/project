package com.lkh.myapp.authority.service;


import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.lkh.myapp.authority.dao.AuthorityDAO;
import com.lkh.myapp.authority.dto.AuthorityDTO;

@Service
public class AuthortySvcImplXML implements AuthoritySvc{
	
	@Inject
	AuthorityDAO authoritydao;

	@Override
	public boolean insert(AuthorityDTO authorityDTO) {
		boolean success = false;
		success = authoritydao.insert(authorityDTO);
		return success;
	}

	@Override
	public boolean update(AuthorityDTO authorityDTO) {
		boolean success = false;
		success = authoritydao.update(authorityDTO);
		return success;
	}

	@Override
	public boolean delete(AuthorityDTO authorityDTO) {
		boolean success = false;
		success = authoritydao.delete(authorityDTO);
		return success;
	}

	@Override
	public List<AuthorityDTO> selectOne(String id) {
		return authoritydao.selectOne(id);
		 
	}

	@Override
	public List<AuthorityDTO> list() {
		return null;
	}

}
