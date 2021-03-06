package com.kh.portpolio.board.DAO;

import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.kh.portfolio.board.dao.BoardDAO;
import com.kh.portfolio.board.vo.BoardCategoryVO;
import com.kh.portfolio.board.vo.BoardFileVO;
import com.kh.portfolio.board.vo.BoardVO;
import com.kh.portfolio.common.page.RecordCriteria;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/*.xml"})
public class BoardDAOImplXMLTEST {

	private final static Logger logger
	= LoggerFactory.getLogger(BoardDAOImplXMLTEST.class);

	@Inject
	BoardDAO boardDAO;
	
	@Inject
	RecordCriteria recordCriteria;
	
	@Test
	@DisplayName("게시판 카테고리 불러오기")
	@Disabled
	void getCategory() {
		List<BoardCategoryVO> list = null;
		
		list = boardDAO.getCategory();
		//for문
//		for(BoardCategoryVO boardCategoryVO: list )
//		{
//		logger.info(boardCategoryVO.getCname());
//		}
//		//스트림 사용(람다식)
//		list.stream().forEach(boardCategoryVO->{
//			System.out.println(boardCategoryVO);
//		});
		//메소드
		list.stream().forEach(System.out::println);
	}
	
	@Test
	@DisplayName("게시글 작성")
	@Disabled
	void write() {
//    #{cid},
//    #{btitle},
//    #{bid},
//    #{nickname},
//    #{bcontent},		
		BoardVO boardVO = new BoardVO();
		BoardCategoryVO boardCategoryVO = new BoardCategoryVO();
		
		boardVO.setBoardCategoryVO(boardCategoryVO);
		boardVO.getBoardCategoryVO().setCid(1001);
		boardVO.setBtitle("테스트 제목");
		boardVO.setBid("sewon0618@naver.com");
		boardVO.setBnickname("별칭1");
		boardVO.setBcontent("본문1");
		
		int result = boardDAO.write(boardVO);
		Assertions.assertEquals(1, result);
	}
	
	@Test
	@DisplayName("샘플 게시글 작성")
	@Disabled
	void writeSampleData() {
//    #{cid},
//    #{btitle},
//    #{bid},
//    #{nickname},
//    #{bcontent},	
		for(int i=1; i<325; i++) {
		BoardVO boardVO = new BoardVO();
		BoardCategoryVO boardCategoryVO = new BoardCategoryVO();
		
		boardVO.setBoardCategoryVO(boardCategoryVO);
		boardVO.getBoardCategoryVO().setCid(1001);
		boardVO.setBtitle("테스트 제목");
		boardVO.setBid("sewon0618@naver.com");
		boardVO.setBnickname("별칭"+i);
		boardVO.setBcontent("본문"+i);
		
		int result = boardDAO.write(boardVO);
		}
		//Assertions.assertEquals(1, result);
	}
	
	@Test
	@DisplayName("게시글 보기")
	@Disabled
	void view() {
		String bnum="84";
		
		BoardVO boardVO = boardDAO.view(bnum);
		logger.info("boardVO:"+ boardVO.toString());
	}

	@Test
	@DisplayName("첨부파일조회")
	@Disabled
	void getFiles() {
		String bnum="84";
		List<BoardFileVO> list = boardDAO.getFiles(bnum);
		
		list.stream().forEach(System.out::println);
		logger.info(""+list.size());
	}
	
	@Test
	@DisplayName("조회수 증가")
	@Disabled
	void updateBhit() {
		String bnum="20";
		int preBhit = boardDAO.view(bnum).getBhit();
		boardDAO.updateBhit(bnum);
		int postBhit =boardDAO.view(bnum).getBhit();
		Assertions.assertEquals(1, postBhit-preBhit);
		
	}
	
	@Test
	@DisplayName("게시글 삭제")
	@Disabled
	void delete() {
		String bnum="42";
		int result = boardDAO.delete(bnum);
		Assertions.assertEquals(1,result);
	}
	
	@Test
	@DisplayName("첨부파일 개별 삭제")
	@Disabled
	void fileDelete() {
		String fid="41";
		int result = boardDAO.deleteFile(fid);
		Assertions.assertEquals(1,result);
	}
	
	@Test
	@DisplayName("첨부파일 다운로드")
	@Disabled
	void viewFile() {
		String fid="42";
		BoardFileVO boardFileVO = boardDAO.viewFile(fid);
		logger.info(boardFileVO.toString());		
	}
	
	@Test
	@DisplayName("게시글 수정")
	@Disabled
	void modify() {
		BoardVO boardVO = new BoardVO();
		BoardCategoryVO boardCategoryVO = new BoardCategoryVO();
		
		boardVO.setBoardCategoryVO(boardCategoryVO);
		boardVO.getBoardCategoryVO().setCid(1001);
		boardVO.setBnum(44);
		boardVO.setBtitle("수정된 제목");
		boardVO.setBcontent("수정된 내용");
		
		Assertions.assertEquals(1,boardDAO.modify(boardVO));
		

		
	}
	
	@Test
	@DisplayName("답글 작성")
	@Disabled
	void reply() {
//		#{boardCategoryVO.cid},
//		#{btitle},
//		#{bid},
//		#{bnickname},
//		#{bcontent},
//		board_bnum_seq.nextval,
//		#{bstep}+1,
//		#{bindent}+1)
	BoardVO boardVO = new BoardVO();
	BoardCategoryVO boardCategoryVO = new BoardCategoryVO();
	
	boardVO.setBoardCategoryVO(boardCategoryVO);
	boardVO.getBoardCategoryVO().setCid(1001);
	boardVO.setBtitle("답글의답글 제목1");
	boardVO.setBid("sewon0618@naver.com");
	boardVO.setBcontent("답글 내용1");
	boardVO.setBgroup(68);
	boardVO.setBstep(1);
	boardVO.setBindent(1);
	logger.info(boardVO.toString());
	int result = boardDAO.reply(boardVO);
	
	Assertions.assertEquals(1, result);
	}
	
	@Test
	@DisplayName("통합 레코드 수")
	@Disabled
	void totalRecordCount() {
		//logger.info(":"+boardDAO.totalRecordCount());
	}
	
	@Test
	@DisplayName("게시글 목록+페이징")
	@Disabled
	void list() {
		String searchType="A";
		String keyword = "답글 제목3";
		recordCriteria.setReqPage(1);
		recordCriteria.setRecNumPerPage(10);
		List<BoardVO> list = boardDAO.list(recordCriteria.getStartRec(), 
				recordCriteria.getEndRec(), searchType, keyword);
		
		logger.info("레코드 갯수="+list.size());
		
		list.stream().forEach(System.out::println);
	}
}