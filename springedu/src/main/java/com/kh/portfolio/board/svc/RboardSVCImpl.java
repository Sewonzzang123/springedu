package com.kh.portfolio.board.svc;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.kh.portfolio.board.dao.RboardDAO;
import com.kh.portfolio.board.dao.RboardDAOImplXML;
import com.kh.portfolio.board.vo.RboardVO;
import com.kh.portfolio.board.vo.VoteVO;

@Service
public class RboardSVCImpl implements RboardSVC {

	
	@Inject
	RboardDAO rboardDAO;
	
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
	public List<RboardVO> list() {
	
		return rboardDAO.list();
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

}
