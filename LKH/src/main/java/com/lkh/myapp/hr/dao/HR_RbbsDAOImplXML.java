package com.lkh.myapp.hr.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.lkh.myapp.hr.dto.HR_RbbsDTO;
import com.lkh.myapp.notice.dao.NoticeRbbsDAO;



@Repository("hr_RbbsDAOImplXML")
public class HR_RbbsDAOImplXML implements HR_RbbsDAO {

	private static final Logger logger = LoggerFactory.getLogger(HR_RbbsDAOImplXML.class);
	
	
	@Inject
	SqlSession sql;
	
	//댓글 등록
	@Override
	public int write(HR_RbbsDTO hr_rbbsDTO) throws Exception {
		return sql.insert("mappers.hrrbbs.write", hr_rbbsDTO);
	}

	//댓글 목록
	@Override
	public List<HR_RbbsDTO> list(String num) throws Exception {
		return sql.selectList("mappers.hrrbbs.listOld", num);
	}

	//댓글 목록 요청페이지
	@Override
	public List<HR_RbbsDTO> list(String num, int startRec, int endRec) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("num", num);
		map.put("startRec", startRec);
		map.put("endRec", endRec);
		return sql.selectList("mappers.hrrbbs.list", map);
	}

	//댓글 수정
	@Override
	public int modify(HR_RbbsDTO hr_rbbsDTO) throws Exception {
		return sql.update("mappers.hrrbbs.modify",hr_rbbsDTO);
	}

	//댓글 삭제
	@Override
	public int delete(String rnum) throws Exception {
		int cnt = 0;

		if(isReply(rnum)) {
			cnt = sql.update("mappers.hrrbbs.update_isdel", rnum);
		}else {
			cnt = sql.delete("mappers.hrrbbs.delete", rnum);
		}
		return cnt;
	}
	
	private boolean isReply(String rnum) {
		boolean isYN = false;
		int cnt = 0;
		cnt = sql.selectOne("mappers.hrrbbs.isReply", rnum);
		if(cnt>0) {
			isYN = true;
		}
		return isYN;
	}


	//대댓글 등록
	@Override
	public int reply(HR_RbbsDTO hr_rbbsDTO) throws Exception {
	 int cnt1=0, cnt2=0;
	 
	 //댓글대상 읽어오기
	 HR_RbbsDTO originDTO = replyView(hr_rbbsDTO.getRrnum());
	 logger.info("originDTO: "+originDTO);
	 // 이전답글 step 업데이트(원글그룹에 대한 새로정렬 재정의)
	 cnt1 = updateStep(originDTO.getRgroup(), originDTO.getRstep());
	 
	 Map<String, Object> map = new HashMap<>();
	 map.put("originDTO", originDTO);
	 map.put("hr_rbbsDTO", hr_rbbsDTO);
	 logger.info("map"+map);
	 cnt2 = sql.insert("mappers.hrrbbs.reply", map);
 
	 return cnt2;
	}

	
	// 이전답글 step 업데이트 동일그룹의 댓글중에 동일스텝의 글이 있으면 +1갱신
	private int updateStep(int rgroup, int rstep) {
		Map<String, Object> map = new HashMap<>();
		map.put("rgroup", rgroup);
		map.put("rstep", rstep);
		return sql.update("mappers.hrrbbs.updateStep", map);		
	}

	
	//댓글대상 읽어오기
	private HR_RbbsDTO replyView(Integer rrnum) {
		HR_RbbsDTO hrrdto = null;
		hrrdto = sql.selectOne("mappers.hrrbbs.replyView", rrnum);
		logger.info("dgto: "+ hrrdto);
		return hrrdto;
	}

	//댓글 통계
	@Override
	public int replyTotalRec(String num) throws Exception {
		return sql.selectOne("mappers.hrrbbs.replyTotalRec",num);
	}

}
