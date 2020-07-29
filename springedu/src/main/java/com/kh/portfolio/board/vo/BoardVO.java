package com.kh.portfolio.board.vo;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class BoardVO {
	private long bnum;//	BNUM	NUMBER(10,0)	No		1	게시글번호
	private BoardCategoryVO bcategory;//	BCATEGORY	NUMBER(10,0)	Yes		2	분류 카테고리
	private String btitle;//	BTITLE	VARCHAR2(150 BYTE)	Yes		3	제목
	private String bid;//	BID	VARCHAR2(40 BYTE)	Yes		4	작성자 ID
	private String bnickname;//	BNICKNAME	VARCHAR2(30 BYTE)	Yes		5	작성자 이름(별칭)
	private Timestamp bcdate;//	BCDATE	TIMESTAMP(6)	Yes		6	작성일
	private Timestamp budate;//	BUDATE	TIMESTAMP(6)	Yes		7	수정일
	private int bhit;//	BHIT	NUMBER(5,0)	Yes		8	조회수
	private String bcontent;//	BCONTENT	CLOB	Yes		9	본문내용 CLOB: character large object
	private int bgroup;//	BGROUP	NUMBER	Yes		10	답글 그룹
	private int bstep;//	BSTEP	NUMBER	Yes		11	답변글의 단계
	private int bident;//	BINDENT	NUMBER	Yes		12	답변글의 들여쓰기
	
}
