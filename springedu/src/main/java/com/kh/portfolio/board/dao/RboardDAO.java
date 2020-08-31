package com.kh.portfolio.board.dao;

import java.util.List;

import com.kh.portfolio.board.vo.RboardVO;
import com.kh.portfolio.board.vo.VoteVO;

public interface RboardDAO {
	//댓글 작성
	int write(RboardVO rboardVO);
	//댓글 수정
	int modify(RboardVO rboardVO);
	//댓글 삭제
	int delete(long rnum);
	//댓글 목록
	List<RboardVO> list(long bnum, int startRec, int endRec);
	//댓글 조회
	RboardVO replyView(long rnum);
	
	//대댓글 작성
	int reply(RboardVO rboardVO);	
	//이전step update
	int updateStep(long rgroup, long rstep);
	//댓글 호감, 비호감	
	//투표이력 없으면 추가 있으면 변경
	int vote(VoteVO voteVO);
	
	//댓글 총 레코드 수 
	int totalRecordCount();	
	int totalRecordCount(String searchType, String keyword);
	
}
