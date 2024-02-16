package com.sist.dao;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
import com.sist.vo.*;

@Repository
public class MusicBoardDAO {
	@Autowired
	private MusicBoardMapper mapper;
	
	public List<MusicBoardVO> boardListData(int start, int end, int typeno)
	{
		return mapper.boardListData(start, end, typeno);
	}
	public int boardTotalPage(int typeno)
	{
		return mapper.boardTotalPage(typeno);
	}
	
	public MusicBoardVO boardDetailData(int no)
	{
		mapper.hitIncrement(no);
		return mapper.boardDetailData(no);
	}
	
	public void noticeInsert(MusicBoardVO vo)
	{
		mapper.noticeInsert(vo);
	}
	public void questionInsert(MusicBoardVO vo)
	{
		mapper.questionInsert(vo);
	}
	public void schandInsert(MusicBoardVO vo)
	{
		mapper.schandInsert(vo);
	}
}
