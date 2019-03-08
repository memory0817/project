package com.lkh.myapp.sale.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.lkh.myapp.sale.dto.SALE_RbbsDTO;



@Repository("sale_RbbsDAOImplXML")
public class SALE_RbbsDAOImplXML implements SALE_RbbsDAO {

	private static final Logger logger = LoggerFactory.getLogger(SALE_RbbsDAOImplXML.class);
	
	
	@Inject
	SqlSession sql;
	
	//댓글 등록
	@Override
	public int write(SALE_RbbsDTO sale_rbbsDTO) throws Exception {
		return sql.insert("mappers.salerbbs.write", sale_rbbsDTO);
	}

	//댓글 목록
	@Override
	public List<SALE_RbbsDTO> list(String num) throws Exception {
		return sql.selectList("mappers.salerbbs.listOld", num);
	}

	//댓글 목록 요청페이지
	@Override
	public List<SALE_RbbsDTO> list(String num, int startRec, int endRec) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("num", num);
		map.put("startRec", startRec);
		map.put("endRec", endRec);
		return sql.selectList("mappers.salerbbs.list", map);
	}

	//댓글 수정
	@Override
	public int modify(SALE_RbbsDTO sale_rbbsDTO) throws Exception {
		return sql.update("mappers.salerbbs.modify",sale_rbbsDTO);
	}

	//댓글 삭제
	@Override
	public int delete(String rnum) throws Exception {
		int cnt = 0;

		if(isReply(rnum)) {
			cnt = sql.update("mappers.salerbbs.update_isdel", rnum);
		}else {
			cnt = sql.delete("mappers.salerbbs.delete", rnum);
		}
		return cnt;
	}
	
	private boolean isReply(String rnum) {
		boolean isYN = false;
		int cnt = 0;
		cnt = sql.selectOne("mappers.salerbbs.isReply", rnum);
		if(cnt>0) {
			isYN = true;
		}
		return isYN;
	}

	//대댓글 등록
	@Override
	public int reply(SALE_RbbsDTO sale_rbbsDTO) throws Exception {
	 int cnt1=0, cnt2=0;
	 
	 //댓글대상 읽어오기
	 SALE_RbbsDTO originDTO = replyView(sale_rbbsDTO.getRrnum());
	 logger.info("originDTO: "+originDTO);
	 // 이전답글 step 업데이트(원글그룹에 대한 새로정렬 재정의)
	 cnt1 = updateStep(originDTO.getRgroup(), originDTO.getRstep());
	 
	 Map<String, Object> map = new HashMap<>();
	 map.put("originDTO", originDTO);
	 map.put("sale_rbbsDTO", sale_rbbsDTO);
	 logger.info("map"+map);
	 cnt2 = sql.insert("mappers.salerbbs.reply", map);
 
	 return cnt2;
	}

	
	// 이전답글 step 업데이트 동일그룹의 댓글중에 동일스텝의 글이 있으면 +1갱신
	private int updateStep(int rgroup, int rstep) {
		Map<String, Object> map = new HashMap<>();
		map.put("rgroup", rgroup);
		map.put("rstep", rstep);
		return sql.update("mappers.salerbbs.updateStep", map);		
	}

	
	//댓글대상 읽어오기
	private SALE_RbbsDTO replyView(Integer rrnum) {
		SALE_RbbsDTO salrrdto = null;
		salrrdto = sql.selectOne("mappers.salerbbs.replyView", rrnum);
		logger.info("dgto: "+ salrrdto);
		return salrrdto;
	}

	//댓글 통계
	@Override
	public int replyTotalRec(String num) throws Exception {
		return sql.selectOne("mappers.salerbbs.replyTotalRec",num);
	}

}
