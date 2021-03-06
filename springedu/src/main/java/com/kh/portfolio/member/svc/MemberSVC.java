package com.kh.portfolio.member.svc;

import java.sql.Date;
import java.util.List;

import com.kh.portfolio.member.vo.MemberVO;

public interface MemberSVC {
//회원관련 인터페이스
	//회원 등록
	int joinMember(MemberVO memberVO);
	//회원 수정
	int modifyMember(MemberVO memberVO);
	//회원 조회(전체)
	List<MemberVO> listAllMember();
	//회원 조회(개별)
	MemberVO listOneMember(String id);
	//회원 탈퇴
	int outMember(String id, String pw);
	//로그인
	MemberVO login(String id, String pw);
	//아이디 찾기
	String findID(String tel, Date birth);
	//비밀번호 찾기
	String findPW(String id, String tel, Date birth);
	//비밀번호 변경
	int changePW(String id,String prepw, String postpw);
	//프로필 파일 이미지 조회
	byte[] findProfileImg(String id);
}
