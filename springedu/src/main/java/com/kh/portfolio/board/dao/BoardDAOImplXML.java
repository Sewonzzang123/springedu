package com.kh.portfolio.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.kh.portfolio.board.vo.BoardFileVO;
import com.kh.portfolio.board.vo.BoardVO;

@Repository
public class BoardDAOImplXML implements BoardDAO{
	private static final Logger logger=
			LoggerFactory.getLogger(BoardDAOImplXML.class);
	
	@Inject
	SqlSession sqlSession;
	
	//게시글 작성
	@Override
	public int write(BoardVO boardVO) {
		int result = 0;
		//namespace.id
		result = sqlSession.insert("mappers.BoardDAO-mapper.write", boardVO);
		
		return result;
	}
	//게시글 수정
	@Override
	public int modify(BoardVO boardVO) {
		// TODO Auto-generated method stub
		return 0;
	}
	//게시글 삭제
	@Override
	public int delete(String bnum) {
		// TODO Auto-generated method stub
		return 0;
	}
	//게시글 보기
	@Override
	public BoardVO view(String bnum) {
		BoardVO boardVO = null;
		boardVO = sqlSession.selectOne("mappers.BoardDAO-mapper.view", Long.valueOf(bnum));
		return boardVO;
	}
	//게시글 보기 전체
	@Override
	public List<BoardVO> list() {
		List<BoardVO> list = null;
		
		list = sqlSession.selectList("mappers.BoardDAO-mapper.list");
		return list;
	}
	//파일 첨부
	@Override
	public int addFile(BoardFileVO boardFileVO) {
		int result = 0;
		result = sqlSession.insert("mappers.BoardDAO-mapper.addFile",boardFileVO);
		return 0;
	}
	@Override
	public List<BoardFileVO> getFiles(String bnum) {
		List<BoardFileVO> list = null;
		list = sqlSession.selectList("mappers.BoardDAO-mapper.getFiles",Long.valueOf(bnum));
		return list;
	}
	@Override
	public void updateBhit(String bnum) {
		sqlSession.update("mappers.BoardDAO-mapper.updateBhit", Long.valueOf(bnum));
		
	}

}
