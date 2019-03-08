package com.lkh.myapp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lkh.myapp.test.dto.TEST_RbbsDTO;
import com.lkh.myapp.test.service.TEST_RbbsSvc;
import com.lkh.myapp.util.PageCriteria;
import com.lkh.myapp.util.RecordCriteria;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@PreAuthorize("hasAnyRole('ROLE_TEST','ROLE_ADMIN')")
@RequestMapping("/testrbbs")
public class TEST_RbbsController {

	private static Logger logger = LoggerFactory.getLogger(TEST_RbbsController.class);

	@Inject
	TEST_RbbsSvc testrbbsSvc;

	
	// 댓글 등록
	@RequestMapping(value = "/posts", method = RequestMethod.POST)
	public ResponseEntity<String> write(@RequestBody TEST_RbbsDTO testrdtr) {
		ResponseEntity<String> resCode = null;
		if (testrdtr == null) {
			resCode = new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
			return resCode;
		}
		try {
			testrbbsSvc.write(testrdtr);
			resCode = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			resCode = new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return resCode;
	}

	// 댓글 수정
	@RequestMapping(value = "/posts", method = RequestMethod.PUT)
	public ResponseEntity<String> modify(@RequestBody TEST_RbbsDTO testrdtr) {
		ResponseEntity<String> resCode = null;
		if (testrdtr == null) {
			resCode = new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
			return resCode;
		}
		try {
			testrbbsSvc.modify(testrdtr);
			resCode = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			resCode = new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return resCode;
	}

	// 댓글 삭제
	@RequestMapping(value = "/posts/{rnum}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable("rnum") String rnum) {
		ResponseEntity<String> resCode = null;

		if (rnum == null || rnum.length() == 0) {
			resCode = new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
			return resCode;
		}
		try {
			testrbbsSvc.delete(rnum);
			resCode = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			resCode = new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return resCode;
	}

	// 대댓글 등록
	@RequestMapping(value = "/rposts", method = RequestMethod.POST)
	public ResponseEntity<String> reply(@RequestBody TEST_RbbsDTO testrdtr) {
		ResponseEntity<String> resCode = null;
		if (testrdtr == null) {
			resCode = new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
			return resCode;
		}
		try {
			testrbbsSvc.reply(testrdtr);
			resCode = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			resCode = new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return resCode;
	}



	// 댓글 목록 Map
	@RequestMapping(value = "/posts/map/{num}/{reReqPage}", method = RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> map(
			@PathVariable("num") String num,
			@PathVariable("reReqPage") Integer reReqPage) {
		ResponseEntity<Map<String, Object>> responseEntity = null;
		Map<String, Object> map = new HashMap<>();
		RecordCriteria rc = new RecordCriteria(reReqPage, 10);

		try {
			// 페이지 처리
			// =-----------------------------------------------------------------------
			PageCriteria pc = new PageCriteria(rc, testrbbsSvc.replyTotalRec(num), 10);
			// -------------------------------------------------------------------------------

			map.put("item", testrbbsSvc.list(num, rc.getStartRecord(), rc.getEndRecord()));
			map.put("pagecriteria", pc);
			responseEntity = new ResponseEntity<>(map, HttpStatus.OK);
			
		} catch (Exception e) {
			responseEntity = new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}

		return responseEntity;
	}
	
	


}
