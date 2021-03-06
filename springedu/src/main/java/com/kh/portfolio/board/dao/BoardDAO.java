package com.kh.portfolio.board.dao;

import java.util.List;

import com.kh.portfolio.board.vo.BoardCategoryVO;
import com.kh.portfolio.board.vo.BoardFileVO;
import com.kh.portfolio.board.vo.BoardVO;

public interface BoardDAO {
	//게시판 분류코드 읽어오기
	List<BoardCategoryVO> getCategory();
	//게시글 작성
	int write(BoardVO boardVO);
	//게시글 수정
	int modify(BoardVO boardVO);
	//게시글 삭제
	int delete(String bnum);
	//게시글 첨부파일 개별 삭제
	int deleteFile(String fid);
	//게시글 보기
	BoardVO view(String bnum); 
	//게시글 목록
	List<BoardVO> list();
	List<BoardVO> list(int startRec, int endRec);
	List<BoardVO> list(int startRec, int endRec, String searchType, String keyword);
	//파일첨부
	int addFile(BoardFileVO boardFileVO);
	//첨부파일 가져오기
	List<BoardFileVO> getFiles(String bnum);

	//조회수 +1 증가하기
	void updateBhit(String bnum);
	//첨부파일 다운로드
	BoardFileVO viewFile(String fid);
	//답글 작성
	int reply(BoardVO boardVO);

	//게시글 총 레코드 수 
	int totalRecordCount();	
	int totalRecordCount(String searchType, String keyword);

	
}
