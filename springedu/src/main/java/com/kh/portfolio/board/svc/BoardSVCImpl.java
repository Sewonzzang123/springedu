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
import com.kh.portfolio.common.page.FindCriteria;
import com.kh.portfolio.common.page.PageCriteria;
import com.kh.portfolio.common.page.RecordCriteria;
@Service
public class BoardSVCImpl implements BoardSVC {
//서비스 이름(메소드) 및 인아웃 정의:기능 
	//CRUD
	Logger logger = LoggerFactory.getLogger(BoardSVCImpl.class);
	
	//DAO를 참조 사용해야 하므로
	@Inject 
	BoardDAO boardDAO;
	

	@Inject
	RecordCriteria recordCriteria; //데이터 관련
	
	@Inject 
	PageCriteria pageCriteria; //페이징 관련
	
	@Inject
	FindCriteria findCriteria;
		
	
	
	//게시판 카테고리 읽어오기
	@Override
	public List<BoardCategoryVO> getCategory() {
		return boardDAO.getCategory();
	}
	
	//게시글 작성
 @Transactional 
	@Override
	public int write(BoardVO boardVO) {
		int result = 0 ; 

		//게시글 저장
		result = boardDAO.write(boardVO);
		
		logger.info(" 게시글 저장 후 게시글 번호 " + boardVO.getBnum());
		
		//첨부파일 저장
		//첨부파일이 존재하면
		List<MultipartFile> files =boardVO.getFiles();		
		if(files != null && files.size()>0) {
			logger.info("파일 존재 함 . 저장 시작");
			addFiles(files, boardVO.getBnum());			
		}
		return result;
	}
	//멤버 메소드
	//첨부파일 저장
	private void addFiles(List<MultipartFile> files, long bnum) {
		BoardFileVO boardFileVO = null;
		for(MultipartFile file : files) {		
			 try {
				 boardFileVO = new BoardFileVO();
					//파일아이디
				 //seqence 처리
					//게시글 번호
					//게시글 번호 알수 있는 시점은 게시글이 저장된 후 반환 되는 값을 통해 알수 있다.
				 boardFileVO.setBnum(bnum);
				 //파일 이름
					boardFileVO.setFname(file.getOriginalFilename());
					//파일크기
					boardFileVO.setFsize(file.getSize());
					//파일유형
					boardFileVO.setFtype(file.getContentType());
					//첨부파일 
				boardFileVO.setFdata(file.getBytes());
				//첨부파일저장
				if(boardFileVO.getFsize() > 0) { //사이즈가 0보다 크면 저장
					logger.info("파일 크기가 0보다 큼");
					int result1 = boardDAO.addFile(boardFileVO);
					logger.info("result1 = " +result1);
				}
				
				
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			
			
		}
	}
	//게시글 수정
	@Override
	@Transactional
	public int modify(BoardVO boardVO) {

		int result = 0;
		//게시글 수정 
		result = boardDAO.modify(boardVO);
		
			//첨부파일 저장
			//첨부파일이 존재하면
			List<MultipartFile> files =boardVO.getFiles();		
			if(files != null && files.size()>0) {
				logger.info("파일 존재 함 . 저장 시작");
				addFiles(files, boardVO.getBnum());			
			}
		return result ;
	}
	
	
	
	
	
	
	
	//게시글 삭제
	@Override
	public int delete(String bNum) {

		return 		boardDAO.delete(bNum);
	}
	

	@Override
	public int deleteFile(String fid) {	
		return boardDAO.deleteFile(fid);
	}
	
	
	
	//게시글 보기 
	@Transactional
	@Override
	public Map<String, Object> view(String bNum) {
		BoardVO boardVO = null;
		List<BoardFileVO> files = null;
		//1.게시글 가져오기
		boardVO = boardDAO.view(bNum);
		
		//2. 첨부파일가져오기
		files = boardDAO.getFiles(bNum); //
		
		//3.조회수 증가 +1 하기 
		boardDAO.updateBhit(bNum);
		Map<String, Object> map = new HashMap<>();
		map.put("boardVO", boardVO);
		if(files	!=null && files.size() > 0) {
		map.put("files", files);}
		
		return map;
	}
	//게시글 목록
	@Override
	public List<BoardVO> list() {
		
		 List<BoardVO>  list = null;
		 list = boardDAO.list();

		return list;
	}

	//게시글 목록+페이징

	@Override
	public List<BoardVO> list(int reqPage) {
		 List<BoardVO>  list = null;		 		

		 System.out.println("reqPage===========svc=================="+reqPage);
		 //요청페이지
			recordCriteria.setReqPage(reqPage ==  0? 1 : reqPage);
		 //한페이지에 보여줄 게시글 수 
		 recordCriteria.setRecNumPerPage(10);	 
		 //페이지에 보여줄 페이지 
			pageCriteria.setPageNumPerPage(10);
			pageCriteria.setRc(recordCriteria);							
			pageCriteria.calculatePaging();
			
			
		 list = boardDAO.list(recordCriteria.getStartRec(),recordCriteria.getEndRec() );
		return list;
	}
	
	@Override
	public List<BoardVO> list(int reqPage, String searchType, String keyword) {
		List<BoardVO>  list = null;		 		

		 System.out.println("reqPage===========svc=================="+reqPage);
		 //요청페이지
			recordCriteria.setReqPage(reqPage ==  0? 1 : reqPage);
		 //한페이지에 보여줄 게시글 수 
		 recordCriteria.setRecNumPerPage(20);	 
		 //페이지에 보여줄 페이지 
			pageCriteria.setPageNumPerPage(10);
			pageCriteria.setRc(recordCriteria);							
			pageCriteria.calculatePaging();
			
			
		 list = boardDAO.list(recordCriteria.getStartRec(),
				 									recordCriteria.getEndRec(),
				 									searchType,
				 									keyword);
		return list;
	}
	
	
	
//첨부파일 다운로드
	@Override
	public BoardFileVO viewFile(String fid) {		
		return boardDAO.viewFile(fid);
	}
//게시글 답글	
	@Override
	public int reply(BoardVO boardVO) {
	
		return boardDAO.reply(boardVO);
	}

//페이징제어 반환
	@Override
	public PageCriteria getPageCriteria(int reqPage) {

		//한페이지에 보여줄 레코드수
		recordCriteria.setRecNumPerPage(20);
		//사용자의 요청페이지
		recordCriteria.setReqPage(reqPage);
		//한페이지에보여줄 페이지수
		pageCriteria.setPageNumPerPage(10);
		//레코드정보
		pageCriteria.setRc(recordCriteria);
		
		//게시글 총 레코드 건수
		pageCriteria.setTotalRec(boardDAO.totalRecordCount());
		//페이징계산
		pageCriteria.calculatePaging();
		
		return pageCriteria;
	}

	//페이징제어 + 검색어포함
	@Override
	public FindCriteria getFindCriteria(int reqPage, String searchType, String keyword) {

		//한페이지에 보여줄 레코드수
		recordCriteria.setRecNumPerPage(20);
		//사용자의 요청페이지
		recordCriteria.setReqPage(reqPage);
		//한페이지에보여줄 페이지수
		pageCriteria.setPageNumPerPage(10);
		//레코드정보
		pageCriteria.setRc(recordCriteria);

		//게시글 총 레코드 건수
		pageCriteria.setTotalRec(boardDAO.totalRecordCount(searchType,keyword));
					
		//페이징계산
		pageCriteria.calculatePaging();
		//검색어정보
		findCriteria.setPageCriteria(pageCriteria);
		findCriteria.setSearchType(searchType);
		findCriteria.setKeyword(keyword);
			
		return findCriteria;
	}



	

	
	


}
