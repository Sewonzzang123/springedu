package com.kh.portfolio.board.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.portfolio.board.svc.RboardSVC;

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
	public ResponseEntity<String> write(){
		ResponseEntity<String> res =null;
		
		
		
		return res;
	}
	
	//댓글 수정
	
	//댓글 삭제
	
	//댓글 목록
}
