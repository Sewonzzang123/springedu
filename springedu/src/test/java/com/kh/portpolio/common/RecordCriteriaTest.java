package com.kh.portpolio.common;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.kh.portfolio.common.RecordCriteria;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/*.xml"})
public class RecordCriteriaTest {
	private final static Logger logger = LoggerFactory.getLogger(RecordCriteriaTest.class);
	
	@Inject
	RecordCriteria recordCriteria;
	
	@Test
	void test() {
		
		recordCriteria.setReqPage(2);
		recordCriteria.setNumPerPage(10);

		
		logger.info("시작 레코드: "+ recordCriteria.getStarRec());
		logger.info("종료 레코드: "+ recordCriteria.getEndRec());
		
	}
	

}
