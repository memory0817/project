package com.lkh.myapp.notice.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.lkh.myapp.notice.dto.NoticeRbbsDTO;



@Repository("noticeRbbsDAOImplXML")
public class NoticeRbbsDAOImplXML implements NoticeRbbsDAO {

	private static final Logger logger = LoggerFactory.getLogger(NoticeRbbsDAOImplXML.class);
	
	
	@Inject
	SqlSession sql;
	
	//댓글 등록
	@Override
	public int write(NoticeRbbsDTO noticerbbsDTO) throws Exception {
		return sql.insert("mappers.nrbbs.write", noticerbbsDTO);
	}

	//댓글 목록
	@Override
	public List<NoticeRbbsDTO> list(String num) throws Exception {
		return sql.selectList("mappers.nrbbs.listOld", num);
	}

	//댓글 목록 요청페이지
	@Override
	public List<NoticeRbbsDTO> list(String num, int startRec, int endRec) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("num", num);
		map.put("startRec", startRec);
		map.put("endRec", endRec);
		return sql.selectList("mappers.nrbbs.list", map);
	}

	//댓글 수정
	@Override
	public int modify(NoticeRbbsDTO noticerbbsDTO) throws Exception {
		return sql.update("mappers.nrbbs.modify",noticerbbsDTO);
	}

	//댓글 삭제
	@Override
	public int delete(String rnum) throws Exception {
		return sql.delete("mappers.nrbbs.delete",rnum);
	}


	//대댓글 등록
	@Override
	public int reply(NoticeRbbsDTO noticerbbsDTO) throws Exception {
	 int cnt1=0, cnt2=0;
	 
	 //댓글대상 읽어오기
	 NoticeRbbsDTO originDTO = replyView(noticerbbsDTO.getRrnum());
	 logger.info("originDTO: "+originDTO);
	 // 이전답글 step 업데이트(원글그룹에 대한 새로정렬 재정의)
	 cnt1 = updateStep(originDTO.getRgroup(), originDTO.getRstep());
	 
	 Map<String, Object> map = new HashMap<>();
	 map.put("originDTO", originDTO);
	 map.put("noticerbbsDTO", noticerbbsDTO);
	 logger.info("map"+map);
	 cnt2 = sql.insert("mappers.nrbbs.reply", map);
 
	 return cnt2;
	}

	
	// 이전답글 step 업데이트 동일그룹의 댓글중에 동일스텝의 글이 있으면 +1갱신
	private int updateStep(int rgroup, int rstep) {
		Map<String, Object> map = new HashMap<>();
		map.put("rgroup", rgroup);
		map.put("rstep", rstep);
		return sql.update("mappers.nrbbs.updateStep", map);		
	}

	
	//댓글대상 읽어오기
	private NoticeRbbsDTO replyView(Integer rrnum) {
		NoticeRbbsDTO grdto = null;
		grdto = sql.selectOne("mappers.nrbbs.replyView", rrnum);
		logger.info("dgto: "+ grdto);
		return grdto;
	}

	//댓글 통계
	@Override
	public int replyTotalRec(String num) throws Exception {
		return sql.selectOne("mappers.nrbbs.replyTotalRec",num);
	}

}
