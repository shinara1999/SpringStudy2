package com.sist.service;

import java.util.List;

import com.sist.vo.GoodsVO;

public interface GoodsService {
	public List<GoodsVO> goodsListData(int start, int end);
	public int goodsTotalPage();
}
