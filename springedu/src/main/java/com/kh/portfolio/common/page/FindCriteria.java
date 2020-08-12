package com.kh.portfolio.common.page;

import lombok.Data;

@Data
public class FindCriteria {
	private String searchType;
	private String keyword;
	
	private PageCriteria pageCriteria;
	
	public void setPageCriteria(PageCriteria pageCriteria) {
		this.pageCriteria = pageCriteria;
	}
}
