package com.sist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
@Service
public class FoodServiceImpl implements FoodService{
	@Autowired
	private FoodDAO dao;
	
	@Autowired
	private ReplyDAO rDao;
	
	@Autowired
	private MemberDAO mDao;

	@Override
	public List<FoodVO> foodListData(int start, int end) {
		// TODO Auto-generated method stub
		return dao.foodListData(start, end);
	}

	@Override
	public int foodTotalPage() {
		// TODO Auto-generated method stub
		return dao.foodTotalPage();
	}

	@Override
	public FoodVO foodDetailData(int fno) {
		// TODO Auto-generated method stub
		return dao.foodDetailData(fno);
	}

	@Override
	public MemberVO isLogin(String id, String pwd) {
		// TODO Auto-generated method stub
		return mDao.isLogin(id, pwd);
	}

	@Override
	public List<ReplyVO> replyListData(int fno) {
		// TODO Auto-generated method stub
		return rDao.replyListData(fno);
	}

	@Override
	public void replyInsert(ReplyVO vo) {
		// TODO Auto-generated method stub
		rDao.replyInsert(vo);
	}

	@Override
	public void replyDelete(int rno) {
		// TODO Auto-generated method stub
		rDao.replyDelete(rno);
	}

	@Override
	public void replyUpdate(ReplyVO vo) {
		// TODO Auto-generated method stub
		rDao.replyUpdate(vo);
	}
}
