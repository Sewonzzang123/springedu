package com.kh.portfolio.common;

import org.springframework.stereotype.Component;

import lombok.Data;

/*
 * 요청 페이지를 입력받아 시작레코드, 종료레코드 구하기
 * 한페이지에 보여줄수 있는 레코드: 10개라 가정
 * 
 * */

@Component
@Data
public class RecordCriteria {
	private int reqPage;// 요청페이지
	private int numPerPage;// 한페이지당 보여줄 레코드수

	// 시작 레코드=(요청페이지-1)*현페이지에 보여줄 레코드수+1
	public int getStarRec() {
		return (reqPage - 1) * numPerPage + 1;
	}

	// 종료 레코드 = 요청페이지 * 한페이지에 보여줄 레코드수
	public int getEndRec() {
		return reqPage * numPerPage;
	}
	
	//한페이지에 보여줄 시작페이지
	
	//한 페이지에 보여줄 종료페이지
	
	//전체 레코드수
}
