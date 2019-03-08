package com.lkh.myapp.gboard.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.lkh.myapp.gboard.dto.G_RbbsDTO;



@Repository("G_RbbsDAOImplXML")
public class G_RbbsDAOImplXML implements G_RbbsDAO {

	private static final Logger logger = LoggerFactory.getLogger(G_RbbsDAOImplXML.class);
	
	
	@Inject
	SqlSession sql;
	
	//댓글 등록
	@Override
	public int write(G_RbbsDTO g_rbbsDTO) throws Exception {
		return sql.insert("mappers.grbbs.write", g_rbbsDTO);
	}

	//댓글 목록
	@Override
	public List<G_RbbsDTO> list(String num) throws Exception {
		return sql.selectList("mappers.grbbs.listOld", num);
	}

	//댓글 목록 요청페이지
	@Override
	public List<G_RbbsDTO> list(String num, int startRec, int endRec) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("num", num);
		map.put("startRec", startRec);
		map.put("endRec", endRec);
		return sql.selectList("mappers.grbbs.list", map);
	}

	//댓글 수정
	@Override
	public int modify(G_RbbsDTO g_rbbsDTO) throws Exception {
		return sql.update("mappers.grbbs.modify",g_rbbsDTO);
	}

	//댓글 삭제
	@Override
	public int delete(String rnum) throws Exception {
		return sql.delete("mappers.grbbs.delete",rnum);
	}


	//대댓글 등록
	@Override
	public int reply(G_RbbsDTO g_rbbsDTO) throws Exception {
	 int cnt1=0, cnt2=0;
	 
	 //댓글대상 읽어오기
	 G_RbbsDTO originDTO = replyView(g_rbbsDTO.getRrnum());
	 logger.info("originDTO: "+originDTO);
	 // 이전답글 step 업데이트(원글그룹에 대한 새로정렬 재정의)
	 cnt1 = updateStep(originDTO.getRgroup(), originDTO.getRstep());
	 
	 Map<String, Object> map = new HashMap<>();
	 map.put("originDTO", originDTO);
	 map.put("g_rbbsDTO", g_rbbsDTO);
	 logger.info("map"+map);
	 cnt2 = sql.insert("mappers.grbbs.reply", map);
 
	 return cnt2;
	}

	
	// 이전답글 step 업데이트 동일그룹의 댓글중에 동일스텝의 글이 있으면 +1갱신
	private int updateStep(int rgroup, int rstep) {
		Map<String, Object> map = new HashMap<>();
		map.put("rgroup", rgroup);
		map.put("rstep", rstep);
		return sql.update("mappers.grbbs.updateStep", map);		
	}

	
	//댓글대상 읽어오기
	private G_RbbsDTO replyView(Integer rrnum) {
		G_RbbsDTO grdto = null;
		grdto = sql.selectOne("mappers.grbbs.replyView", rrnum);
		logger.info("dgto: "+ grdto);
		return grdto;
	}

	//댓글 통계
	@Override
	public int replyTotalRec(String num) throws Exception {
		return sql.selectOne("mappers.grbbs.replyTotalRec",num);
	}

}
