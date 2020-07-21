package com.kh.portfolio.member.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.eclipse.jdt.internal.compiler.ast.MemberValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import com.kh.portfolio.member.vo.MemberVO;

@Repository
public class MemberDAOImplJDBC implements MemberDAO {
	// 로거
	private static final Logger logger = LoggerFactory.getLogger(MemberDAOImplJDBC.class);

	@Inject
	JdbcTemplate jdbcTemplate;

	// 회원 등록
	@Override
	public int joinMember(MemberVO memberVO) {
		logger.info("MemberDAOImpl.joinMember");
		logger.info(memberVO.toString());
		int result = 0;
		// sql작성
		// StringBuffer sql = new StringBuffer();//동기화 환경에서 사용
		StringBuilder sql = new StringBuilder();// 비동기 환경에서 사용
		sql.append("insert into member(id, pw, tel, nickname, gender, region, birth) ");
		sql.append("values(?,?,?,?,?,?,?) ");

		result = jdbcTemplate.update(sql.toString(), memberVO.getId(), memberVO.getPw(), memberVO.getTel(),
				memberVO.getNickname(), memberVO.getGender(), memberVO.getRegion(), memberVO.getBirth());
		return result;
	}

	// 회원 수정
	@Override
	public int modifyMember(MemberVO memberVO) {
		logger.info("MemberDAOImpl.modifyMember");
		logger.info(memberVO.toString());
		int result = 0;
		StringBuilder sql = new StringBuilder();
		sql.append("update member ");
		sql.append("set tel=?, ");
		sql.append("    nickname=?, ");
		sql.append("    gender=?, ");
		sql.append("    region=?, ");
		sql.append("    birth=?, ");
		sql.append("    udate=systimestamp ");
		sql.append("where id=? ");

		result = jdbcTemplate.update(sql.toString(), memberVO.getTel(), memberVO.getNickname(), memberVO.getGender(),
				memberVO.getRegion(), memberVO.getBirth(), memberVO.getId());

		return result;
	}

	// 회원 조회(전체)
	@Override
	public List<MemberVO> listAllMember() {
		logger.info("MemberDAOImpl.listAllMember()");
		List<MemberVO> list = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select id,pw,nickname,tel,gender,region,birth,cdate,udate ");
		sql.append(" from member ");
		
		//조회같은 경우에는 복잡해 ㅇㅅㅇ
		list = jdbcTemplate.query(sql.toString(), getRowMapper() );
		return list;
		}

	//spring framework import해야함
	private RowMapper<MemberVO> getRowMapper() {
		//RowMapper: 인터페이스
		RowMapper<MemberVO> rowMapper = new RowMapper<MemberVO>() {
			@Override
			public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				//결과를 ResultSet에 저장
				MemberVO memberVO = new MemberVO();
				memberVO.setId(rs.getString("id"));
				memberVO.setPw(rs.getString("pw"));
				memberVO.setTel(rs.getString("tel"));
				memberVO.setNickname(rs.getString("nickname"));
				memberVO.setGender(rs.getString("gender"));
				memberVO.setRegion(rs.getString("region"));
				memberVO.setBirth(rs.getDate("birth"));
				memberVO.setCdate(rs.getTimestamp("cdate"));
				memberVO.setUdate(rs.getTimestamp("udate"));
				return memberVO;
			}
		};
		return rowMapper;
	}

	// 회원 조회(개별)
	@Override
	public MemberVO listOneMember(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	// 회원 탈퇴
	@Override
	public int outMember(String id, String pw) {
		// TODO Auto-generated method stub
		return 0;
	}

	// 로그인
	@Override
	public int login(String id, String pw) {
		// TODO Auto-generated method stub
		return 0;
	}

	// 아이디 찾기
	@Override
	public String findID(String tel, String birth) {
		// TODO Auto-generated method stub
		return null;
	}

	// 비밀번호 찾기
	@Override
	public String findPW(String id, String tel, String birth) {
		// TODO Auto-generated method stub
		return null;
	}

	// 비밀번호 변경
	@Override
	public int changePW(String id, String pw) {
		// TODO Auto-generated method stub
		return 0;
	}

	// 프로필 파일 이미지 조회
	@Override
	public byte[] findProfileImg(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
