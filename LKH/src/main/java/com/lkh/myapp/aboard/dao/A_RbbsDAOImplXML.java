package com.lkh.myapp.aboard.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.lkh.myapp.aboard.dto.A_RbbsDTO;



@Repository("a_RbbsDAOImplXML")
public class A_RbbsDAOImplXML implements A_RbbsDAO {

	private static final Logger logger = LoggerFactory.getLogger(A_RbbsDAOImplXML.class);
	
	
	@Inject
	SqlSession sql;
	
	//댓글 등록
	@Override
	public int write(A_RbbsDTO A_rdto) throws Exception {
		return sql.insert("mappers.arbbs.write", A_rdto);
	}

	//댓글 목록
	@Override
	public List<A_RbbsDTO> list(String num) throws Exception {
		return sql.selectList("mappers.arbbs.listOld", num);
	}

	//댓글 목록 요청페이지
	@Override
	public List<A_RbbsDTO> list(String num, int startRec, int endRec) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("num", num);
		map.put("startRec", startRec);
		map.put("endRec", endRec);
		return sql.selectList("mappers.arbbs.list", map);
	}

	//댓글 수정
	@Override
	public int modify(A_RbbsDTO A_rdto) throws Exception {
		return sql.update("mappers.arbbs.modify",A_rdto);
	}

	//댓글 삭제
	@Override
	public int delete(String rnum) throws Exception {
		int cnt = 0;

		if(isReply(rnum)) {
			cnt = sql.update("mappers.arbbs.update_isdel", rnum);
		}else {
			cnt = sql.delete("mappers.arbbs.delete", rnum);
		}
		return cnt;
	}
	
	private boolean isReply(String rnum) {
		boolean isYN = false;
		int cnt = 0;
		cnt = sql.selectOne("mappers.arbbs.isReply", rnum);
		if(cnt>0) {
			isYN = true;
		}
		return isYN;
	}


	//대댓글 등록
	@Override
	public int reply(A_RbbsDTO A_rdto) throws Exception {
	 int cnt1=0, cnt2=0;
	 
	 //댓글대상 읽어오기
	 A_RbbsDTO originDTO = replyView(A_rdto.getRrnum());
	 logger.info("originDTO: "+originDTO);
	 // 이전답글 step 업데이트(원글그룹에 대한 새로정렬 재정의)
	 cnt1 = updateStep(originDTO.getRgroup(), originDTO.getRstep());
	 
	 Map<String, Object> map = new HashMap<>();
	 map.put("originDTO", originDTO);
	 map.put("A_rdto", A_rdto);
	 logger.info("map"+map);
	 cnt2 = sql.insert("mappers.arbbs.reply", map);
 
	 return cnt2;
	}

	
	// 이전답글 step 업데이트 동일그룹의 댓글중에 동일스텝의 글이 있으면 +1갱신
	private int updateStep(int rgroup, int rstep) {
		Map<String, Object> map = new HashMap<>();
		map.put("rgroup", rgroup);
		map.put("rstep", rstep);
		return sql.update("mappers.arbbs.updateStep", map);		
	}

	
	//댓글대상 읽어오기
	private A_RbbsDTO replyView(Integer rrnum) {
		A_RbbsDTO ardto = null;
		ardto = sql.selectOne("mappers.arbbs.replyView", rrnum);
		logger.info("dgto: "+ ardto);
		return ardto;
	}

	//댓글 통계
	@Override
	public int replyTotalRec(String num) throws Exception {
		return sql.selectOne("mappers.arbbs.replyTotalRec",num);
	}

	@Override
	public int goodOrbad(String rnum, String goodOrbad) {
		Map<String,String> map = new HashMap<>();
		map.put("rnum", rnum);
		logger.info(goodOrbad);
		map.put("goodOrbad", goodOrbad);

		return sql.update("mappers.arbbs.goodOrbad", map);
	}

}
