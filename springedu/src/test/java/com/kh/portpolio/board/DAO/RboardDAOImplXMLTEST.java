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

import com.kh.portfolio.board.dao.RboardDAO;
import com.kh.portfolio.board.vo.RboardVO;
import com.kh.portfolio.board.vo.Vote;
import com.kh.portfolio.board.vo.VoteVO;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/*.xml"})
public class RboardDAOImplXMLTEST {
	
	private static final Logger logger = 
			LoggerFactory.getLogger(RboardDAOImplXMLTEST.class);
	@Inject
	RboardDAO rboardDAO;
	
	@Test
	@DisplayName("댓글 작성")
//	@Disabled
	void write() {
		RboardVO rboardVO = new RboardVO();
		rboardVO.setBnum((long)493);
		rboardVO.setRid("test3@test.com");
		rboardVO.setRcontent("테스트 댓글3");		
		rboardDAO.write(rboardVO);
		
		rboardVO = rboardDAO.replyView(rboardVO.getRnum());
		logger.info(rboardVO.toString());
	}
	
	@Test
	@DisplayName("댓글 수정")
	@Disabled
	void modify() {
		long rnum=43;
		String rcontent = "테스트 댓글 내용 수정 321";
		RboardVO rboardVO = new RboardVO();
		rboardVO.setRcontent(rcontent);	
		rboardVO.setBnum((long)503);
		rboardVO.setRid("sewon0618@naver.com");
		rboardVO.setRnum(rnum);
		
		rboardDAO.modify(rboardVO);
		RboardVO rboardVO2 = rboardDAO.replyView(rnum);
		Assertions.assertEquals(rboardVO2.getRcontent(),rcontent);
	}
	
	@Test
	@DisplayName("댓글 삭제")
	@Disabled
	void delete() {
		Assertions.assertEquals(1,rboardDAO.delete((long)45));
	}
	
	@Test
	@DisplayName("댓글목록")
	@Disabled
	void List() {
		List<RboardVO> list = rboardDAO.list();
		for(RboardVO rboardVO:list) {
			logger.info(rboardVO.toString());
		}
	}

	@Test
	@DisplayName("호감도 투표")
	@Disabled
	void vote() {
		VoteVO voteVO = new VoteVO();
		voteVO.setRnum((long)24);
		voteVO.setBnum((long)502);
		voteVO.setRid("sewon0618@naver.com");
		voteVO.setVote(Vote.GOOD);
		rboardDAO.vote(voteVO);
	}
}
