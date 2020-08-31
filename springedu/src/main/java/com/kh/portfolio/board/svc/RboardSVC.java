package com.kh.portfolio.board.svc;

import java.util.List;

import com.kh.portfolio.board.vo.RboardVO;
import com.kh.portfolio.board.vo.VoteVO;
import com.kh.portfolio.common.page.FindCriteria;
import com.kh.portfolio.common.page.PageCriteria;

public interface RboardSVC {
		
	//댓글 작성
	int write(RboardVO rboardVO);
	//댓글 수정
	int modify(RboardVO rboardVO);
	//댓글 삭제
	int delete(long rnum);
	//댓글 목록
	List<RboardVO> list(int reqPage, long bnum);
	//대댓글 작성
	int reply(RboardVO rboardVO);	
	//댓글 호감, 비호감	
	//투표이력 없으면 추가 있으면 변경
	int vote(VoteVO voteVO);
	
	//페이징제어 반환
	PageCriteria getPageCriteria(int reqPage);	
	//페이징제어 + 검색포함
	FindCriteria getFindCriteria(int reqPage,String searchType, String keyword);
}
