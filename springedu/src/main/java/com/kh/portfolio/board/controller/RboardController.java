package com.kh.portfolio.board.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.portfolio.board.svc.RboardSVC;
import com.kh.portfolio.board.vo.RboardVO;

//댓글에 대한것만 url요청
@RestController
@RequestMapping("/rboard")
public class RboardController {
	private static final Logger logger = 
			LoggerFactory.getLogger(RboardController.class);
	
	@Inject
	RboardSVC rboardSVC;
	
	//댓글 작성
	@PostMapping(value="", produces="applicaion/json")
	public ResponseEntity<String> write(
			@RequestBody RboardVO rboardVO){
		ResponseEntity<String> res =null;
		
		int cnt = rboardSVC.write(rboardVO);
		
		//성공
		if(cnt ==1) {
			res = new ResponseEntity<String>("success",HttpStatus.OK);
		}else {
		//실패
			res = new ResponseEntity<String>("fail",HttpStatus.BAD_REQUEST);
		}
		
		return res;
	}
	
	//댓글 수정
	
	//댓글 삭제
	
	//댓글 목록
}
