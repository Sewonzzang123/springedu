package com.kh.portfolio.board.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.portfolio.board.svc.RboardSVC;
import com.kh.portfolio.board.vo.RboardVO;
import com.kh.portfolio.board.vo.VoteVO;
import com.kh.portfolio.common.page.PageCriteria;
import com.kh.portfolio.exception.ErrorMsg;
import com.kh.portfolio.exception.RestAccessException;
import com.kh.portfolio.member.svc.MemberSVC;
import com.kh.portfolio.member.vo.MemberVO;

//댓글에 대한것만 url요청
@RestController
@RequestMapping("/rboard")
public class RboardController {
	private static final Logger logger = 
			LoggerFactory.getLogger(RboardController.class);
	
	@Inject
	RboardSVC rboardSVC;
	
	@Inject
	MemberSVC memberSVC;
	
	//댓글 작성
	@PostMapping(value="", produces="application/json")
	public ResponseEntity<String> write(
			@Valid @RequestBody RboardVO rboardVO,
			BindingResult result,
			HttpServletRequest request){
		ResponseEntity<String> res =null;
		
		if (result.hasErrors()) {
			throwRestAccessException(result);
		}
		
		//getSession : true면 새로 생성, false면 새로 생성 x
		MemberVO memberVO = (MemberVO)request.getSession(false).getAttribute("member");
		if(memberVO!=null) {
		//세션에서 아이디, 별칭 가져오기
			rboardVO.setRid(memberVO.getId());
			rboardVO.setRnickname(memberVO.getNickname());
		}
		
		int cnt = rboardSVC.write(rboardVO);
		
		//성공
		if(cnt ==1) {
			res = new ResponseEntity<String>("success",HttpStatus.OK);//200
		}else {
		//실패
			res = new ResponseEntity<String>("fail",HttpStatus.BAD_REQUEST);//400
		}
		
		return res;
	}

	
	//댓글 수정 
	@PutMapping(value="",produces = "application/json")
	public ResponseEntity<String> modify(
			@Valid @RequestBody RboardVO rboardVO,
			BindingResult result,
			HttpServletRequest request){
		ResponseEntity<String> res =null;
		
		if (result.hasErrors()) {
			throwRestAccessException(result);
		}
		
		//getSession : true면 새로 생성, false면 새로 생성 x
		MemberVO memberVO = (MemberVO)request.getSession(false).getAttribute("member");
		if(memberVO!=null) {
		//세션에서 아이디, 별칭 가져오기
			rboardVO.setRid(memberVO.getId());
			rboardVO.setRnickname(memberVO.getNickname());
		}
		
		int cnt = rboardSVC.modify(rboardVO);
		
		//성공
		if(cnt ==1) {
			res = new ResponseEntity<String>("success",HttpStatus.OK);//200
		}else {
		//실패
			res = new ResponseEntity<String>("fail",HttpStatus.BAD_REQUEST);//400
		}
		
		return res;
		
	}
	//댓글 삭제
	@DeleteMapping(value="/{rnum}",produces = "application/json")
	public ResponseEntity<String> delete(
			@PathVariable(value="rnum", required=true) long rnum
			){
		ResponseEntity<String> res =null;

		int cnt = rboardSVC.delete(rnum);
		
		//성공
		if(cnt ==1) {
			res = new ResponseEntity<String>("success",HttpStatus.OK);//200
		}else {
		//실패
			res = new ResponseEntity<String>("fail",HttpStatus.BAD_REQUEST);//400
		}
		
		return res;	
	}
	//대댓글 작성
	@PostMapping(value="/reply",produces = "application/json")
	public ResponseEntity<String> reply(
			@Valid @RequestBody RboardVO rboardVO,
			BindingResult result,
			HttpServletRequest request){
		ResponseEntity<String> res =null;
		
		if (result.hasErrors()) {
			throwRestAccessException(result);
		}
		//getSession : true면 새로 생성, false면 새로 생성 x
		MemberVO memberVO = (MemberVO)request.getSession(false).getAttribute("member");
		if(memberVO != null) {
		//세션에서 아이디, 별칭 가져오기
			rboardVO.setRid(memberVO.getId());
			rboardVO.setRnickname(memberVO.getNickname());
		}
		
		int cnt = rboardSVC.reply(rboardVO);
		
		//성공
		if(cnt ==1) {
			res = new ResponseEntity<String>("success",HttpStatus.OK);//200
		}
		
		return res;
	}
	
	//댓글 목록
	@GetMapping(value="/{reqPage}/{bnum}", produces="application/json")
	public ResponseEntity<Map<String,Object>> list(
			@PathVariable(value="reqPage",required = false) Optional<Integer> reqPage,
			@PathVariable(value="bnum",required = true) long bnum,
			HttpServletRequest request){
		
		ResponseEntity<Map<String,Object>> res = null;
		Map<String,Object> map = new HashMap<>();
		
		
		
		//1) 댓글 목록
		List<RboardVO> list = rboardSVC.list(reqPage.orElse(1), bnum);
		
		//2) 페이징 정보
		PageCriteria pageCriteria = rboardSVC.getPageCriteria(reqPage.orElse(1), bnum);
		
		
		map.put("list",list);
		map.put("pc",pageCriteria);
		
		if(list.size() > 0) {
			res = new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		}
		
		return res;		
	}
	
	//선호 비선호
	@PutMapping(value="/vote", produces="application/json")
	public ResponseEntity<String> vote(
			@Valid @RequestBody VoteVO voteVO,
			HttpServletRequest request,
			BindingResult result
			){
		
		ResponseEntity<String> res = null;
		if (result.hasErrors()) {
			throwRestAccessException(result);
		}
		//세션에서 아이디
		if(request.getSession(false) != null) {
			MemberVO memberVO = (MemberVO)request.getSession(false).getAttribute("member");
			if(memberVO!=null) {
				voteVO.setRid(memberVO.getId());
			}
		}
		logger.info(""+voteVO);
		int cnt = rboardSVC.vote(voteVO);
		if(cnt ==1) {
			res = new ResponseEntity<String>("success",HttpStatus.OK);//200
		}
		return res;		
	}
	
//vo객체 바인딩처리에 대한 예외 처리
	private void throwRestAccessException(BindingResult result) {
		List<FieldError> list = result.getFieldErrors();
		List<ErrorMsg> errList = new ArrayList<>();
		
		for(FieldError item:list) {
//			logger.info(item.getField()+"/"
//									+item.getDefaultMessage()+"/"
//									+item.getRejectedValue());
			ErrorMsg errorMsg = new ErrorMsg();
			errorMsg.setFieldName(item.getField());
			errorMsg.setRequestValue((String)item.getRejectedValue());
			errorMsg.setErrMsg(item.getDefaultMessage());
			errList.add(errorMsg);
		}
		throw new RestAccessException(errList);
	}
}
