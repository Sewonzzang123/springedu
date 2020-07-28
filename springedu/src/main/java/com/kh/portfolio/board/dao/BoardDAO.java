package com.kh.portfolio.board.dao;

import com.kh.portfolio.board.vo.BoardVO;

public interface BoardDAO {
	//게시글 작성
	int write(BoardVO boardVO);
	//게시글 수정
	int modify(BoardVO boardVO);
	//게시글 삭제
	int delete(String bnum);
	//게시글 보기
	BoardVO view(String bnum); 
}
