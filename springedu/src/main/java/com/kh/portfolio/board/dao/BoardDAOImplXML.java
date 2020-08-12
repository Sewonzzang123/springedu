package com.kh.portfolio.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.kh.portfolio.board.vo.BoardCategoryVO;
import com.kh.portfolio.board.vo.BoardFileVO;
import com.kh.portfolio.board.vo.BoardVO;

@Repository
public class BoardDAOImplXML implements BoardDAO {
//서비스 이름(메소드) 및 인아웃 정의:기능 
	// CRUD

	private static final Logger Logger = LoggerFactory.getLogger(BoardDAOImplXML.class);

	@Inject
	SqlSession sqlSession;

	
//게시판 카테고리 읽어오기
	@Override
	public List<BoardCategoryVO> getCategory() {
		 List<BoardCategoryVO> list = null;	 
		 list = sqlSession.selectList("mappers.BoardDAO-mapper.getCategory");		
		return list;
	}
	
	
	
	// 게시글 작성
	@Override
	public int write(BoardVO boardVO) {
		int result = 0;
		result = sqlSession.insert("mappers.BoardDAO-mapper.write", boardVO);
		return result;}
	
	//게시글 첨부파일 저장
	@Override
	public int addFile(BoardFileVO boardFileVO) {
		
		int result = 0;
		result = sqlSession.insert("mappers.BoardDAO-mapper.addFile", boardFileVO);
		
		return result;
	}
	
	// 게시글 수정
	@Override
	public int modify(BoardVO boardVO) {		
		int result = 0;
		result = sqlSession.update("mappers.BoardDAO-mapper.modify", boardVO);
		return result;
		
	}
	// 게시글 삭제
	@Override
	public int delete(String bNum) {
		int result = 0; 
		result = sqlSession.delete("mappers.BoardDAO-mapper.delete", Long.valueOf(bNum));
		return result;
	
	}


//첨부파일 삭제 (개별)
	@Override
	public int deleteFile( String fid) {
		int result = 0; 
		result = sqlSession.delete("mappers.BoardDAO-mapper.deleteFile", Long.valueOf(fid));
		return result;
	}

	

	// 1게시글 보기
	@Override
	public BoardVO view(String bNum) {
		BoardVO boardVO = null;
		boardVO = sqlSession.selectOne("mappers.BoardDAO-mapper.view",Long.valueOf(bNum));

		return boardVO;
	}
	
	
	//2첨부파일 조회
	@Override
	public List<BoardFileVO> getFiles(String bNum) {
		 List<BoardFileVO> list = null;
		 list = sqlSession.selectList("mappers.BoardDAO-mapper.getFiles", Long.valueOf(bNum));		
		return list;
	}

	//3조회수 업데이트
	@Override
	public void updateBhit(String bNum) {
		
		sqlSession.update("mappers.BoardDAO-mapper.updateBhit",Long.valueOf(bNum));		
	}
	
	
	// 게시글 목록
	@Override
	public List<BoardVO> list() {
		List<BoardVO> list = null;		
		list = sqlSession.selectList("mappers.BoardDAO-mapper.list1");
		return list;
	}

	//게시글 목록 + 페이징
	@Override
	public List<BoardVO> list(int startRec, int endRec) {
		List<BoardVO> list = null;
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("startRec", startRec);
		map.put("endRec", endRec);
		list = sqlSession.selectList("mappers.BoardDAO-mapper.list3",map);
		return list;
	}
	


	@Override
	public List<BoardVO> list(int startRec, int endRec, String searchType, String keyword) {
		List<BoardVO> list = null;
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("startRec", startRec);
		map.put("endRec", endRec);
		map.put("searchType", searchType);
		map.put("keyword", keyword);
		list = sqlSession.selectList("mappers.BoardDAO-mapper.list",map);
		return list;
	}

	
	
//첨부파일 다운로드
	@Override
	public BoardFileVO viewFile(String fid) {
		BoardFileVO boardFileVO = null;
		boardFileVO = sqlSession.selectOne("mappers.BoardDAO-mapper.viewFile", Long.valueOf(fid));		
		return boardFileVO;
	}
	
	
	
	// 답글 _ 게시글
	@Override
	public int reply(BoardVO boardVO) {
		//이전 답글 bstep 업데이트  : 최조 원글 답글중 부모글 보다 답글 단계가 큰경우 +1 증가
		updateStep(boardVO.getBgroup(), boardVO.getBstep());
		//답글달기 
		
		return sqlSession.insert("mappers.BoardDAO-mapper.reply",boardVO);
	}


	//이전 답글 step 업데이트 
	private int updateStep(int bgroup, int bstep) {	
		Map<String,Object> map = new HashMap<>();
		map.put("bgroup", bgroup);
		map.put("bstep",bstep);		
		return sqlSession.update("mappers.BoardDAO-mapper.updateStep", map);
	}

	
//게시글 총 레코드 수 
	@Override
	public int totalRecordCount() {
		return sqlSession.selectOne("mappers.BoardDAO-mapper.totalRecordCount");
	}
	@Override
	public int totalRecordCount(String searchType, String keyword) {
		Map<String, Object> map = new HashMap<>();
		map.put("searchType", searchType);
		map.put("keyword", keyword);
		return sqlSession.selectOne("mappers.BoardDAO-mapper.searchedTotalRecordCount",map);
	}








	




}
