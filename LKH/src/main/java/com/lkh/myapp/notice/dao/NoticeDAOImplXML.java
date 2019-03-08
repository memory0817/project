package com.lkh.myapp.notice.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.lkh.myapp.notice.dto.NoticeDTO;




@Repository(value = "noticeDAOImplXML")
public class NoticeDAOImplXML implements NoticeDAO {

	private static final Logger logger = LoggerFactory.getLogger(NoticeDAOImplXML.class);
	
	@Inject
	SqlSession sql;
	
	// 글쓰기
	@Override
	public int write(NoticeDTO notDTO) throws Exception {		
		return sql.insert("mappers.notice.write", notDTO);
	}

	// 글읽기
	@Override
	public NoticeDTO view(String num) throws Exception {
		NoticeDTO notDTO = null;		
		notDTO = sql.selectOne("mappers.notice.view",num);
		
		// 조회수 증가
		updateHit(notDTO.getNum());
		return notDTO;
	}

	//조회수 증가메소드
	private void updateHit(int num) {
		sql.update("mappers.notice.updateHit", num);		
	}

	// 글수정
	@Override
	public int modify(NoticeDTO notDTO) throws Exception {		
		return sql.update("mappers.notice.modify", notDTO);
	}

	@Override
	public int delete(String rnum) throws Exception {
		int cnt = 0;

		if (isReply(rnum)) {
			cnt = sql.update("mappers.notice.update_isdel", rnum);
		} else {
			cnt = sql.delete("mappers.notice.delete", rnum);
		}
		return cnt;
	}

	private boolean isReply(String rnum) {
		boolean isYN = false;
		int cnt = 0;
		cnt = sql.selectOne("mappers.notice.isReply", rnum);
		if (cnt > 0) {
			isYN = true;
		}
		return isYN;
	}

	
	// 원글가져오기
	@Override
	public NoticeDTO replyView(String num) throws Exception {
		return sql.selectOne("mappers.notice.replyView", num);
	}

	// 답글쓰기
	@Override
	public int reply(NoticeDTO notDTO) throws Exception {
		int cnt1=0, cnt2=0;
		//이전 답글 step 업데이트(원글그룹에 대한 세로정렬 재정의)
			cnt1 = updateStep(notDTO.getBgroup(),notDTO.getStep());
		//답글등록
			cnt2 = sql.insert("mappers.notice.reply", notDTO);
			
		return cnt2;
	}

	//이전답글 step 업데이트
	private int updateStep(int bgroup, int step) {
		Map<String, Object> map = new HashMap<>();
		map.put("bgroup", bgroup);
		map.put("step", step);
		return sql.update("mappers.notice.updateStep", map);
	}

	// 게시글 총계
	@Override
	public int totalRec() throws Exception {
		return sql.selectOne("mappers.notice.totalRec");
	}
	
	//글목록 전체
	@Override
	public List<NoticeDTO> list() throws Exception {
		return sql.selectList("mappers.notice.listOld");
	}	
	
	// 글목록 요청페이지
	@Override
	public List<NoticeDTO> list(int startRec, int endRec) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("startRec", startRec);
		map.put("endRec", endRec);
		return sql.selectList("mappers.notice.list", map);
	}

	//검색목록
	@Override
	public List<NoticeDTO> list(int startRecord, int endRecord, String searchType, String keyword) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("startRecord", startRecord);
		map.put("endRecord", endRecord);
		map.put("searchType", searchType);
		map.put("keyword", keyword);
		return sql.selectList("mappers.notice.flist", map);
	}

	//검색총계
	@Override
	public int SearchTotalRec(String searchType, String keyword) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("searchType", searchType);
		map.put("keyword", keyword);
		
		return sql.selectOne("mappers.notice.searchTotalRec", map);
	}

}
