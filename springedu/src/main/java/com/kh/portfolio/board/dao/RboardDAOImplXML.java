package com.kh.portfolio.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.kh.portfolio.board.vo.RboardVO;
import com.kh.portfolio.board.vo.VoteVO;

@Repository
public class RboardDAOImplXML implements RboardDAO {
	
	private static final Logger logger =
			LoggerFactory.getLogger(RboardDAOImplXML.class);
	
	@Inject
	SqlSession sqlSession;
	
	//댓글 작성
	@Override
	public int write(RboardVO rboardVO) {		
		return sqlSession.insert("mappers.RboardDAO-mapper.write", rboardVO);
	}
//댓글 수정
	@Override
	public int modify(RboardVO rboardVO) {
		return sqlSession.update("mappers.RboardDAO-mapper.modify",rboardVO);
	}
	//댓글 삭제
	@Override
	public int delete(long rnum) {
		return sqlSession.delete("mappers.RboardDAO-mapper.delete", rnum);
	}
	//댓글 목록
	@Override
	public List<RboardVO> list(long bnum, int startRec, int endRec) {
		Map<String,Integer> map = new HashMap<>();
		map.put("bnum",(int)bnum);
		map.put("startRec",startRec);
		map.put("endRec",endRec);
		return sqlSession.selectList("mappers.RboardDAO-mapper.list", map);
	}
	//댓글 조회
	@Override
	public RboardVO replyView(long rnum) {
		return sqlSession.selectOne("mappers.RboardDAO-mapper.replyView", rnum);
	}
	//대댓글 작성
	@Override
	public int reply(RboardVO rboardVO) {
		return sqlSession.insert("mappers.RboardDAO-mapper.reply", rboardVO);
	}
	@Override
	public int updateStep(long rgroup,long rstep) {
		Map<String, Long> map = new HashMap<>();
		map.put("rgroup",rgroup);
		map.put("rstep",rstep);
		return sqlSession.update("mappers.RboardDAO-mapper.updateStep", map);
	}
	//댓글 호감, 비호감	
	//투표이력 없으면 추가 있으면 변경
	@Override
	public int vote(VoteVO voteVO) {
		return sqlSession.update("mappers.RboardDAO-mapper.vote", voteVO);
	}
	
	//댓글 총 레코드 수 
	@Override
	public int totalRecordCount() {
		return sqlSession.selectOne("mappers.RboardDAO-mapper.totalRecordCount");
	}
	@Override
	public int totalRecordCount(String searchType, String keyword) {
		Map<String, Object> map = new HashMap<>();
		map.put("searchType", searchType);
		map.put("keyword", keyword);
		return sqlSession.selectOne("mappers.RboardDAO-mapper.searchedTotalRecordCount",map);
	}


}
