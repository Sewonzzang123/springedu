package com.kh.portpolio.board.SVC;

import javax.inject.Inject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.kh.portfolio.board.svc.RboardSVC;
import com.kh.portfolio.board.vo.RboardVO;
import com.kh.portpolio.board.DAO.RboardDAOImplXMLTEST;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/*.xml"})
public class RboardSVCImplTEST {
	private static final Logger logger = 
			LoggerFactory.getLogger(RboardDAOImplXMLTEST.class);
	
	@Inject
	RboardSVC rboardSVC;
	
	@Test
	@DisplayName("reply")
	void reply() {
		RboardVO rboardVO = new RboardVO();
		rboardVO.setPrnum((long)81);
		rboardVO.setBnum((long)493);
		rboardVO.setRid("test3@test.com");
		rboardVO.setRcontent("81번 대댓글 테스트");
		
		rboardSVC.reply(rboardVO);
	}

}
