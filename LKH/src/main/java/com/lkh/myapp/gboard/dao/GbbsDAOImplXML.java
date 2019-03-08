package com.lkh.myapp.gboard.dao;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.lkh.myapp.gboard.dto.GbbsDTO;


@Repository(value = "gbbsDAOImplXML")
public class GbbsDAOImplXML implements GbbsDAO {

	private static final Logger logger = LoggerFactory.getLogger(GbbsDAOImplXML.class);

	@Inject
	SqlSession sqlSession;

	// 글쓰기
	@Override
	public int write(GbbsDTO gbbsDTO) throws Exception {
		return sqlSession.insert("mappers.gbbs.write", gbbsDTO);
	}

	// 글목록 전체
	@Override
	public List<GbbsDTO> list() throws Exception {
		return sqlSession.selectList("mappers.gbbs.listOld");
	}

	// 글목록 요청페이지
	@Override
	public List<GbbsDTO> list(int startRec, int endRec) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("startRec", startRec);
		map.put("endRec", endRec);
		return sqlSession.selectList("mappers.gbbs.list", map);
	}

	// 글읽기
	@Override
	public GbbsDTO view(String num) throws Exception {
		GbbsDTO gbbsDTO = null;

		gbbsDTO = sqlSession.selectOne("mappers.gbbs.view", num);

		// 조회수 증가
		updateHit(gbbsDTO.getNum());
		return gbbsDTO;
	}

	// 조회수 증가메소드
	private void updateHit(int bnum) {
		sqlSession.update("mappers.gbbs.updateHit", bnum);
	}

	// 글수정
	@Override
	public int modify(GbbsDTO gbbsDTO) throws Exception {
		return sqlSession.update("mappers.gbbs.modify", gbbsDTO);
	}

	// 글삭제
	@Override
	public int delete(String num) throws Exception {
		return sqlSession.delete("mappers.gbbs.delete", num);
	}

	// 게시글 총계
	@Override
	public int totalRec() throws Exception {
		return sqlSession.selectOne("mappers.gbbs.totalRec");
	}

	// 검색목록
	@Override
	public List<GbbsDTO> list(int startRecord, int endRecord, String searchType, String keyword) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("startRecord", startRecord);
		map.put("endRecord", endRecord);
		map.put("searchType", searchType);
		map.put("keyword", keyword);

		return sqlSession.selectList("mappers.gbbs.flist", map);

	}

	// 검색 총계
	@Override
	public int SearchTotalRec(String searchType, String keyword) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("searchType", searchType);
		map.put("keyword", keyword);

		return sqlSession.selectOne("mappers.gbbs.searchTotalRec", map);
	}

}