package com.kh.portpolio.member.DAO;



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

import com.kh.portfolio.member.dao.MemberDAO;
import com.kh.portfolio.member.vo.MemberVO;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class MemberDAOImplJDBCTest {
	private static final Logger logger = 
			LoggerFactory.getLogger(MemberDAOImplJDBCTest.class);
	
	@Inject
	MemberDAO memberDAO;
	
	@Test
	@DisplayName("회원등록")
	@Disabled //테스트 대상에서 제외
	void joinMember() {
		MemberVO memberVO = new MemberVO();
		memberVO.setId("test@test.com");
		memberVO.setPw("1234");
		memberVO.setTel("010-1234-5678");
		memberVO.setNickname("관리자2");
		memberVO.setGender("남");
		memberVO.setRegion("부산");
		memberVO.setBirth(java.sql.Date.valueOf("2000-01-02"));
		
		int cnt=memberDAO.joinMember(memberVO);
		logger.info("cnt: "+cnt);
	}
	
	@Test
	@DisplayName("회원수정")
	@Disabled
	void modifyMember() {
		MemberVO memberVO = new MemberVO();
		memberVO.setId("test@test.com");
		memberVO.setTel("010-1234-6789");
		memberVO.setNickname("관리자");
		memberVO.setGender("남");
		memberVO.setRegion("서울");
		memberVO.setBirth(java.sql.Date.valueOf("2000-01-02"));
		
		int cnt=memberDAO.modifyMember(memberVO);
	//예상값과 결과값이 다를경우 fail이됨
		Assertions.assertEquals(2,cnt); 
		logger.info("cnt: "+cnt);
		
	}
	
	@Test
	@DisplayName("회원전체조회")
	void listAllMember() {
		
		List<MemberVO> list = memberDAO.listAllMember();
		
		for(MemberVO memberVO : list) {
			logger.info(memberVO.toString());
		}
	}

}
