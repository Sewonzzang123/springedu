package com.kh.portfolio.board.vo;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Entity
@Data
public class BoardVO {
	
	private long bnum;//	BNUM	NUMBER(10,0)	No		1	게시글번호
	
	//참조하는게 있는경우에사용, 계층형 유효성 검사시 반드시 추가해주기!!
	@Valid
	private BoardCategoryVO boardCategoryVO;//	BCATEGORY	NUMBER(10,0)	Yes		2	분류 카테고리
	
	@NotNull
	@Size(min=4,max=50, message="제목은 4~50자 까지 입력가능합니다!")//byte단위가아닌 글자단위
	private String btitle;//	BTITLE	VARCHAR2(150 BYTE)	Yes		3	제목
	
	private String bid;//	BID	VARCHAR2(40 BYTE)	Yes		4	작성자 ID
	//@Pattern(regexp="\\w+@\\w.\\w+(\\.\\w+)?", message="이메일 형식으로 입력바랍니다. ex)test@test.com")
	
	private String bnickname;//	BNICKNAME	VARCHAR2(30 BYTE)	Yes		5	작성자 이름(별칭)
	private Timestamp bcdate;//	BCDATE	TIMESTAMP(6)	Yes		6	작성일
	private Timestamp budate;//	BUDATE	TIMESTAMP(6)	Yes		7	수정일
	private int bhit;//	BHIT	NUMBER(5,0)	Yes		8	조회수
	
	@NotNull(message="내용을 입력바랍니다 !")
	@Size(min=4, message="내용은 최소 4자 이상 입력하세요")
	private String bcontent;//	BCONTENT	CLOB	Yes		9	본문내용 CLOB: character large object
	
	private int bgroup;//	BGROUP	NUMBER	Yes		10	답글 그룹
	private int bstep;//	BSTEP	NUMBER	Yes		11	답변글의 단계
	private int bindent;//	BINDENT	NUMBER	Yes		12	답변글의 들여쓰기
	
	//첨부파일
	private List<MultipartFile> files;
	
	
	
}
