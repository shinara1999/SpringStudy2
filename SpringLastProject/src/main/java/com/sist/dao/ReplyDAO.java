package com.sist.dao;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
import com.sist.vo.ReplyVO;

@Repository
public class ReplyDAO {
	@Autowired
	private ReplyMapper mapper;
	
	public List<ReplyVO> replyListData(int rno)
	{
		return mapper.replyListData(rno);
	}
	public void replyInsert(ReplyVO vo)
	{
		mapper.replyInsert(vo);
	}
	public void replyUpdate(ReplyVO vo)
	{
		mapper.replyUpdate(vo);
	}
	public void replyDelete(int no)
	{
		mapper.replyDelete(no);
	}
}
