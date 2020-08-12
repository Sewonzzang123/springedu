package com.kh.portpolio.common.util;

import javax.inject.Inject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.kh.portfolio.common.page.RecordCriteria;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/*.xml"})
public class RecordCriteriaTest {
	private final static Logger logger = LoggerFactory.getLogger(RecordCriteriaTest.class);
	
	@Inject
	RecordCriteria recordCriteria;
	
	@Test
	@DisplayName("페이징 구현")
	void paging() {
		
		recordCriteria.setReqPage(2);
		recordCriteria.setRecNumPerPage(10);

		
		logger.info("시작 레코드: "+ recordCriteria.getStartRec());
		logger.info("종료 레코드: "+ recordCriteria.getEndRec());
		
	}
	

}
