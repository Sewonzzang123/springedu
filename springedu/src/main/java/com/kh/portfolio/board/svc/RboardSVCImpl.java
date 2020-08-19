package com.kh.portfolio.board.svc;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.kh.portfolio.board.dao.RboardDAO;
import com.kh.portfolio.board.vo.RboardVO;

@Service
public class RboardSVCImpl implements RboardSVC {

	
	@Inject
	RboardDAO rboardDAO;
	
	
	@Override
	public int write(RboardVO rboardVO) {
	
		return 0;
	}

	@Override
	public int modify(RboardVO rboardVO) {

		return 0;
	}

	@Override
	public int delete(long rnum) {

		return 0;
	}

	@Override
	public List<RboardVO> list() {
	
		return null;
	}

}
