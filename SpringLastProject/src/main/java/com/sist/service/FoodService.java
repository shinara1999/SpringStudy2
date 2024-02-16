package com.sist.service;

import java.util.List;
import java.util.Map;

import com.sist.vo.FoodVO;
import com.sist.vo.NoticeVO;
import com.sist.vo.RecipeDetailVO;

public interface FoodService {
	public List<FoodVO> foodFindData(Map map);
	public int foodFindCount(Map map);
	public FoodVO foodDetailData(int fno);
	public List<FoodVO> foodListData(Map map);
	public int foodListCount();
	public FoodVO foodListDetailData(int fno);
	
	// 공지사항 service
	public List<NoticeVO> noticeTop7();
	
	// 인기맛집
	public List<FoodVO> foodTop7();
	
	// 인기맛집 home
	public List<FoodVO> foodHome12();
	
	
}
