package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;

public interface RecipeMapper {
	
	// 인기쉐프
	@Select("SELECT cno, chef, poster, rownum "
			+"FROM (SELECT cno, chef, poster "
			+"FROM chef ORDER BY mem_cont7 DESC) "
			+"WHERE rownum<=12")
	public List<ChefVO> chefHome12();
	
	// 인기쉐프
	@Select("SELECT no, title, poster, rownum "
			+"FROM (SELECT no, title, poster "
			+"FROM recipe ORDER BY hit DESC) "
			+"WHERE rownum<=12")
	public List<RecipeVO> recipeHome12();
	
	// 목록
	// @Select("SELECT COUNT(*) FROM recipe")
	@Select("SELECT COUNT(*) FROM recipe "
			+"WHERE no IN(SELECT no FROM recipe "
			+"intersect SELECT no FROM recipeDetail)")
	public int recipeCount();
	
	@Select("SELECT no, title, poster, num "
			+"FROM (SELECT no, title, poster, rownum as num "
			+"FROM (SELECT /*+ INDEX_ASC(recipe recipe_no_pk)*/no, title, poster "
			+"FROM recipe WHERE no IN(SELECT no FROM recipe "
			+"intersect SELECT no FROM recipeDetail) "
			+")) "
			+"WHERE num BETWEEN #{start} AND #{end}")
	public List<RecipeVO> recipeListData(@Param("start") int start, @Param("end") int end);
	
	@Select("SELECT CEIL(COUNT(*)/20.0) FROM recipe "
			+"WHERE no IN(SELECT no FROM recipe "
			+"intersect SELECT no FROM recipeDetail)")
	public int recipeTotalPage();
	
	// 상세보기
	// 쉐프
	@Select("SELECT cno, chef, poster, mem_cont1, mem_cont2, mem_cont3, mem_cont7, num "
			+"FROM (SELECT cno, chef, poster, mem_cont1, mem_cont2, mem_cont3, mem_cont7, rownum as num "
			+"FROM (SELECT /*+ INDEX_ASC(chef chef_cno_pk)*/cno, chef, poster, mem_cont1, mem_cont2, mem_cont3, mem_cont7 "
			+"FROM chef)) "
			+"WHERE num BETWEEN #{start} AND #{end}")
	public List<ChefVO> chefListData(@Param("start") int start, @Param("end") int end);
	
	@Select("SELECT CEIL(COUNT(*)/20.0) FROM chef")
	public int chefTotalPage();
	
	// 쉐프별 레시피
	@Select("SELECT no, title, poster, chef, num "
			+"FROM (SELECT no, title, poster, chef, rownum as num "
			+"FROM (SELECT no, title, poster, chef "
			+"FROM recipe WHERE chef=(SELECT chef FROM chef WHERE cno=#{cno}))) "
			+"WHERE num BETWEEN #{start} AND #{end}")
	public List<RecipeVO> chefDetailData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/20.0) FROM recipe "
			+"WHERE chef=(SELECT chef FROM chef WHERE cno=#{cno})")
	public int chefDetailTotalPage(int cno);
	
	// 쉐프별 레시피 find
	@Select("SELECT no, title, poster, chef, num "
			+"FROM (SELECT no, title, poster, chef, rownum as num "
			+"FROM (SELECT no, title, poster, chef "
			+"FROM recipe WHERE chef=(SELECT chef FROM chef WHERE cno=#{cno}) AND title LIKE '%'||#{ss}||'%')) "
			+"WHERE num BETWEEN #{start} AND #{end}")
	public List<RecipeVO> chefDetailFindData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/20.0) FROM recipe "
			+"WHERE chef=(SELECT chef FROM chef WHERE cno=#{cno}) AND title LIKE '%'||#{ss}||'%'")
	public int chefDetailFindTotalPage(Map map);
	
	// 레시피 디테일
	@Select("SELECT * FROM recipeDetail "
			+"WHERE no=#{no}")
	public RecipeDetailVO recipeDetaildata(int no);
	
	// goods
	@Select("SELECT goods_poster, goods_name, goods_price "
			+"FROM goods_all "
			+"WHERE goods_name LIKE '%'||#{goods_name}||'%'")
	public List<GoodsVO> recipeGoodsData(String goods_name);
}

















