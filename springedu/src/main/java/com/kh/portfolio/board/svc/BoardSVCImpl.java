package com.kh.portfolio.board.svc;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kh.portfolio.board.dao.BoardDAO;
import com.kh.portfolio.board.vo.BoardCategoryVO;
import com.kh.portfolio.board.vo.BoardFileVO;
import com.kh.portfolio.board.vo.BoardVO;

@Service
public class BoardSVCImpl implements BoardSVC {
	private static final Logger logger = LoggerFactory.getLogger(BoardSVCImpl.class);

	@Inject
	BoardDAO boardDAO;

	// 작성( 트랜잭션)
	@Transactional//트랜잭션 처리가 필요하다고 알려준다.
	@Override
	public int write(BoardVO boardVO) {
		
		int result = 0;
		// 게시글 저장
		result = boardDAO.write(boardVO);
		// 첨부파일 저장
		// 첨부파일이 존재하면
		List<MultipartFile> files = boardVO.getFiles();
		if (files != null && boardVO.getFiles().size() > 0) {
			addFiles(files, boardVO.getBnum());
		}
		return result;
	}

	// 클래스 내부에서만 사용해서 private
	private void addFiles(List<MultipartFile> files, long bnum) {
		BoardFileVO boardFileVO = null;

		for (MultipartFile file : files) {
			boardFileVO = new BoardFileVO();
			
			try {
				// 게시글 번호
				// boardVO
				boardFileVO.setBnum(bnum);
				// 파일명
				boardFileVO.setFname(file.getOriginalFilename());
				// 파일크키
				boardFileVO.setFsize(file.getSize());
				// 파일유형
				boardFileVO.setFtype(file.getContentType());
				// 첨부파일
				boardFileVO.setFdata(file.getBytes());
				if (boardFileVO.getFsize() > 0) {
					// 파일 저장
					boardDAO.addFile(boardFileVO);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

//수정
	@Override
	@Transactional
	public int modify(BoardVO boardVO) {
		int result = 0;
		
		//게시글 수정
		result = boardDAO.modify(boardVO);
				
		//첨부파일 추가
		List<MultipartFile> files = boardVO.getFiles();
		if (files != null && boardVO.getFiles().size() > 0) {
			addFiles(files, boardVO.getBnum());
		}
		
		return result;
	}

//삭제
	@Override
	public int delete(String bnum) {

		return boardDAO.delete(bnum);
	}
	//게시글 첨부파일 개별 삭제
	@Override
	public int deleteFile(String fid) {
		
		return boardDAO.deleteFile(fid);
	}


//보기
	@Override
	public Map<String, Object> view(String bnum) {
		BoardVO boardVO = null;
		List<BoardFileVO> files = null;
		
		//1)게시글 가져오기
		boardVO = boardDAO.view(bnum);
		
		//2) 첨부파일 가져오기
		files = boardDAO.getFiles(bnum);
		
		//3) 조회수 +1
		boardDAO.updateBhit(bnum);
		//map에담아서 한번에 보내기
		Map<String, Object> map = new HashMap<>();
		map.put("board", boardVO);
		if(files !=null && files.size()>0) {
		map.put("files", files);
		}
		return map;
	}

//보기 전체
	@Override
	public List<BoardVO> list() {
		List<BoardVO> list = null;
		
		list = boardDAO.list();
		return list;
	}

	@Override
	public List<BoardCategoryVO> getCategory() {

		return boardDAO.getCategory();
	}
//첨부파일 다운로드
	@Override
	public BoardFileVO viewFile(String fid) {
		
		return boardDAO.viewFile(fid);
	}



}
