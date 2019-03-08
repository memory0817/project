package com.lkh.myapp.authority.dao;



import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.lkh.myapp.authority.dto.AuthorityDTO;

@Repository
public class AuthorityDAOImplXML implements AuthorityDAO{

	@Inject
	private SqlSession sqlsession;
	
	@Override
	public boolean insert(AuthorityDTO authorityDTO) {
		boolean success = false;
		int cnt = sqlsession.insert("mappers.authority.insert", authorityDTO);
		if(cnt>0) {
			success = true;
		}
		return success;
	}

	@Override
	public boolean update(AuthorityDTO authorityDTO) {
		boolean success = false;
		int cnt = sqlsession.update("mappers.authority.update", authorityDTO);
		if(cnt>0) {
			success = true;
		}
		return success;
	}

	@Override
	public boolean delete(AuthorityDTO authorityDTO) {
		boolean success = false;
		int cnt = sqlsession.delete("mappers.authority.delete", authorityDTO);
		if(cnt>0) {
			success = true;
		}
		return success;
	}

	@Override
	public List<AuthorityDTO> selectOne(String id) {
		List<AuthorityDTO> list = null;
		list = sqlsession.selectList("mappers.authority.selectOne", id);
		return list;
	}

	@Override
	public List<AuthorityDTO> list() {
		return sqlsession.selectList("mappers.authorty.list");
	}

}
