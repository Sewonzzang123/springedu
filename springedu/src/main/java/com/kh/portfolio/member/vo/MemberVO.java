package com.kh.portfolio.member.vo;

import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class MemberVO {
	private String id;			// ID VARCHAR2(40 BYTE) No 1 "이메일 ex)admin@google.com"
	private String pw;			// PW VARCHAR2(10 BYTE) No 2 5~10자리,특수문자 포함
	private String tel;			// TEL NVARCHAR2(13 CHAR) Yes 3 '-'포함
	private String nickname;// NICKNAME VARCHAR2(20 BYTE) Yes 4 별명
	private String gender;	// GENDER CHAR(3 BYTE) Yes 5 '남'OR'여'
	private String region;	// REGION VARCHAR2(30 BYTE) Yes 6 지역
	private Date birth;			// BIRTH DATE Yes 7 생년월일
	private Timestamp cdate;// CDATE TIMESTAMP(6) Yes "SYSTIMESTAMP " 8 생성일시
	private Timestamp udate;// UDATE TIMESTAMP(6) Yes 9 수정일시
	
	//회원 이미지 첨부용
	private MultipartFile file;	
	private String fsize;							//	FSIZE	VARCHAR2(45 BYTE)	Yes		11	파일 사이즈
	private String ftype;							//	FTYPE	VARCHAR2(50 BYTE)	Yes		12	파일 유형
	private String fname;							//	FNAME	VARCHAR2(150 BYTE)	Yes		13	파일명
	private byte[] pic;								// 	PIC BLOB Yes 10 사진
	
	
	
}
