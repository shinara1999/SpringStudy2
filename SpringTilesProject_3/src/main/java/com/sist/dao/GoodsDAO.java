package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.mapper.*;
import com.sist.vo.*;
@Repository
public class GoodsDAO {
	@Autowired
	private GoodsMapper mapper;
	
	public List<GoodsVO> goodsListData(Map map)
	{
		return mapper.goodsListData(map);
	}
	
	public int goodsTotalPage(Map map)
	{
		return mapper.goodsTotalPage(map);
	}
	
	public GoodsVO goodsDetailData(Map map)
	{
		return mapper.goodsDetailData(map);
	}
}
