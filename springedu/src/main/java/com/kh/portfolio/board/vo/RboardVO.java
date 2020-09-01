package com.kh.portfolio.board.vo;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Data
public class RboardVO {

@Positive
private Long rnum;				//	RNUM	NUMBER(10,0)	No		1	"댓글 번호


@Positive
private Long bnum;				//	BNUM	NUMBER(10,0)	No		2	"게시글 번호

//@NotNull
@Email
//@Pattern(regexp="\\w+@\\w.\\w+(\\.\\w+)?", message="이메일 형식으로 입력바랍니다.")
private String rid;				//	RID	VARCHAR2(40 BYTE)	No		3	"작성자 ID


@Size(min=2, max=10, message="별칭은 최대 10자까지 가능합니다.")
private String rnickname;	//	RNICKNAME	VARCHAR2(30 BYTE)	Yes		4	작성자이름(별칭)

@JsonFormat(pattern= "yyyy-MM-dd h:mm a", timezone="Asia/Seoul")
private Timestamp rcdate;			//	RCDATE	TIMESTAMP(6)	No	SYSTIMESTAMP 	5	작성일
private Timestamp rudate;			//	RUDATE	TIMESTAMP(6)	Yes	SYSTIMESTAMP 	6	수정일

@NotNull
private String rcontent;		//	RCONTENT	CLOB	No		7	본문내용

//선호도
private int rgood;				//	RGOOD	NUMBER(5,0)	Yes		8	선호
private int rbad;				//	RBAD	NUMBER(5,0)	Yes		9	비선호

//댓글 그룹
private int rgroup;			//	RGROUP	NUMBER(5,0)	Yes		10	답글그룹
private int rstep;				//	RSTEP	NUMBER(5,0)	Yes		11	답변글의단계
private int rindent;			//	RINDENT	NUMBER(5,0)	Yes		12	답변글의들여쓰기

//부모 댓글 참조
private Long prnum;				//	PRNUM	NUMBER(10,0)	Yes		13	부모댓글번호
private String prid;			//	PRID	VARCHAR2(40 BYTE)	Yes		14	부모댓글아이디
private String prnickname;//	PRNICKNAME	VARCHAR2(30 BYTE)	Yes		15	부모댓글작성자(별칭)

//프로파일 이미지
private String ftype;			//파일의 mime타입 image/png
private byte[] pic;				//첨부이미지파일의 바이트 배열

}
