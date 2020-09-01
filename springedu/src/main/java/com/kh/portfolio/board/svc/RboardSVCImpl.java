package com.kh.portfolio.board.svc;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.kh.portfolio.board.dao.RboardDAO;
import com.kh.portfolio.board.vo.RboardVO;
import com.kh.portfolio.board.vo.VoteVO;
import com.kh.portfolio.common.page.FindCriteria;
import com.kh.portfolio.common.page.PageCriteria;
import com.kh.portfolio.common.page.RecordCriteria;

@Service
public class RboardSVCImpl implements RboardSVC {

	
	@Inject
	RboardDAO rboardDAO;
	
	@Inject
	RecordCriteria recordCriteria;
	
	@Inject
	PageCriteria pageCriteria;
	
	@Inject
	FindCriteria findCriteria;
	
	//댓글 작성
	@Override
	public int write(RboardVO rboardVO) {
		
		return rboardDAO.write(rboardVO);
	}
	//댓글 수정
	@Override
	public int modify(RboardVO rboardVO) {

		return rboardDAO.modify(rboardVO);
	}
	//댓글 삭제
	@Override
	public int delete(long rnum) {

		return rboardDAO.delete(rnum);
	}
	//댓글 목록
	@Override
	public List<RboardVO> list(int reqPage, long bnum) {
		List<RboardVO> list = null;
		
		//댓글 목록
		recordCriteria.setReqPage(reqPage);
		//한페이지에 보여줄 게시글 수 
		recordCriteria.setRecNumPerPage(10);	
		list = rboardDAO.list(bnum,
													recordCriteria.getStartRec()
													,recordCriteria.getEndRec());
		//페이징 정보
		
		
		return list;
	}
	//대댓글 작성
	@Override
	public int reply(RboardVO rboardVO) {
		
		//1) 부모글 정보 읽어오기
		RboardVO parentRboardVO =rboardDAO.replyView(rboardVO.getPrnum());
		
		//2) 이전댓글 step 업데이트
		rboardDAO.updateStep(parentRboardVO.getRgroup(), parentRboardVO.getRstep());
		
		//3) 대댓글 작성
		rboardVO.setBnum(parentRboardVO.getBnum());
		rboardVO.setRgroup(parentRboardVO.getRgroup());
		rboardVO.setRstep(parentRboardVO.getRstep());
		rboardVO.setRindent(parentRboardVO.getRindent());
		
		//4)부모댓글 아이디
		rboardVO.setPrid(parentRboardVO.getRid());
		//5)부모댓글 별칭
		rboardVO.setPrnickname(parentRboardVO.getRnickname());
		
		int cnt = rboardDAO.reply(rboardVO);
		
		return cnt;
	}
	//댓글 호감, 비호감	
	//투표이력 없으면 추가 있으면 변경
	@Override
	public int vote(VoteVO voteVO) {
		
		return rboardDAO.vote(voteVO);
	}
//페이징제어 반환
	@Override
	public PageCriteria getPageCriteria(int reqPage, long bnum) {

		//한페이지에 보여줄 레코드수
		recordCriteria.setRecNumPerPage(10);
		//사용자의 요청페이지
		recordCriteria.setReqPage(reqPage);
		//한페이지에보여줄 페이지수
		pageCriteria.setPageNumPerPage(10);
		//레코드정보
		pageCriteria.setRc(recordCriteria);
		
		//게시글 총 레코드 건수
		pageCriteria.setTotalRec(rboardDAO.totalRecordCount(bnum));
		//페이징계산
		pageCriteria.calculatePaging();
		
		return pageCriteria;
	}

	//페이징제어 + 검색어포함
	@Override
	public FindCriteria getFindCriteria(int reqPage, String searchType, String keyword, long bnum) {

		//한페이지에 보여줄 레코드수
		recordCriteria.setRecNumPerPage(10);
		//사용자의 요청페이지
		recordCriteria.setReqPage(reqPage);
		//한페이지에보여줄 페이지수
		pageCriteria.setPageNumPerPage(10);
		//레코드정보
		pageCriteria.setRc(recordCriteria);

		//게시글 총 레코드 건수
		pageCriteria.setTotalRec(rboardDAO.totalRecordCount(searchType,keyword,bnum));
					
		//페이징계산
		pageCriteria.calculatePaging();
		//검색어정보
		findCriteria.setPageCriteria(pageCriteria);
		findCriteria.setSearchType(searchType);
		findCriteria.setKeyword(keyword);
			
		return findCriteria;
	}

}
