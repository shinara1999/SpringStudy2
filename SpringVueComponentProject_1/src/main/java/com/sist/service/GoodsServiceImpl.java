package com.sist.service;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.dao.GoodsDAO;
import com.sist.vo.GoodsVO;

@Service
public class GoodsServiceImpl implements GoodsService{

	@Autowired
	private GoodsDAO dao;
	@Override
	public List<GoodsVO> goodsListData(int start, int end) {
		// TODO Auto-generated method stub
		return dao.goodsListData(start, end);
	}

	@Override
	public int goodsTotalPage() {
		// TODO Auto-generated method stub
		return dao.goodsTotalPage();
	}

}
