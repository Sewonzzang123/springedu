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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.kh.portfolio.member.dao.MemberDAO;
import com.kh.portfolio.member.vo.MemberVO;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class MemberDAOImplXMLTest {
	private static final Logger logger = 
			LoggerFactory.getLogger(MemberDAOImplXMLTest.class);
	
	@Inject
	//@Qualifier: spring 컨테이너에 동일타입의 bean이 존재할 경우 명시적으로 
	//참조하고자하는 bean을 지정할때 사용
	//spring컨테이너에서 관리되는 bean이름은 특별히 지정해주지 않으면 클래스명을 기본값으로.
	//첫글자는 소문자 !!
	@Qualifier("memberDAOImplXML")
	MemberDAO memberDAO;
	
	@Test
	@DisplayName("회원등록")
	@Disabled //테스트 대상에서 제외
	void joinMember() {
		MemberVO memberVO = new MemberVO();
		memberVO.setId("test1@test.com");
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
		memberVO.setId("test1@test.com");
		memberVO.setTel("010-1234-6789");
		memberVO.setNickname("관리자123");
		memberVO.setGender("여");
		memberVO.setRegion("서울");
		memberVO.setBirth(java.sql.Date.valueOf("2000-01-02"));
		
		int cnt=memberDAO.modifyMember(memberVO);
	//예상값과 결과값이 다를경우 fail이됨
		Assertions.assertEquals(1,cnt); 
		logger.info("cnt: "+cnt);
		
	}
	
	@Test
	@DisplayName("회원전체조회")
	@Disabled
	void listAllMember() {
		
		List<MemberVO> list = memberDAO.listAllMember();
		
		for(MemberVO memberVO : list) {
			logger.info(memberVO.toString());
		}
	}

	@Test
	@DisplayName("회원 개별 조회")
	@Disabled
	void listOneMember() {	
		String id="test@test.com";
		MemberVO memberVO = memberDAO.listOneMember(id);
		
		logger.info(memberVO.toString());
	}
	
	@Test
	@DisplayName("회원 탈퇴")
	@Disabled
	void outMember() {
		String id="test2@test.com";
		String pw="1223";
		
		int result= memberDAO.outMember(id, pw);
		
		Assertions.assertEquals(1,result);
	}
	
	@Test
	@DisplayName("로그인")
	@Disabled
	void login() {
		String id="test3@test.com";
		String pw="1234";
		
		MemberVO memberVO = memberDAO.login(id, pw);
		logger.info(memberVO.toString());
	}
	
	@Test
	@DisplayName("아이디 찾기")
	@Disabled
	void findID() {
		String tel="010-1234-6789";
		String birth="2000-01-02";
		
		String id = memberDAO.findID(tel, birth);
		logger.info(id);
		//Assertions.assertEquals("test@test.com",id);
				
	}
	
	@Test
	@DisplayName("비밀번호 찾기")
	@Disabled
	void findPW() {
		String id="test1@test.com";
		String tel="010-1234-6789";
		String birth="2000-01-02";
		
		String pw = memberDAO.findPW(id, tel, birth);
		Assertions.assertEquals("1234",pw);
				
	}
	
	@Test
	@DisplayName("비밀번호 변경")
//	@Disabled
	void changePW() {
		String id = "test1@test.com";
		String pw="1323";
		int result = memberDAO.changePW(id, pw);
		Assertions.assertEquals(pw,memberDAO.listOneMember(id).getPw());
	}
}
