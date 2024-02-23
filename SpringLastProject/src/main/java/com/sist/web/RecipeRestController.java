package com.sist.web;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sist.vo.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.service.*;

import java.text.DecimalFormat;
import java.util.*;
@RestController //다른 언어와 연동 (XML,JSON)
public class RecipeRestController {
    @Autowired
    private RecipeService rService;
    
    @Autowired
    private ReplyService replyService;
    // Vue,React에서 요청 ==> 자바스크립트가 인식한 데이터로 변경후에 전송 
    //                                             =========== JSON 자바스크립트 객체 표현법 
    // VO {} , List [] 
    @GetMapping(value="recipe/recipe_list_vue.do",produces = "text/plain;charset=UTF-8")
    public String recipe_list(int page) throws Exception
    {
    	int rowSize=20;
    	int start=(rowSize*page)-(rowSize-1);
    	int end=rowSize*page;
    	
    	List<RecipeVO> list=rService.recipeListData(start, end);
    	
    	ObjectMapper mapper=new ObjectMapper();
    	String json=mapper.writeValueAsString(list);
    	
    	return json;
    }
    @GetMapping(value="recipe/recipe_page_vue.do",produces = "text/plain;charset=UTF-8")
    public String recipe_page(int page) throws Exception
    {
    	int totalpage=rService.recipeTotalPage();
    	final int BLOCK=10;
    	int startPage=((page-1)/BLOCK*BLOCK)+1;
    	int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
    	int count=rService.recipeCount();
    	if(endPage>totalpage)
    		endPage=totalpage;
    	DecimalFormat df=new DecimalFormat("###,###");
    	String c=df.format(count);
    	
    	Map map=new HashMap();
    	map.put("curpage", page);
    	map.put("totalpage", totalpage);
    	map.put("startPage", startPage);
    	map.put("endPage", endPage);
    	map.put("count", c);
    	
    	ObjectMapper mapper=new ObjectMapper();
    	String json=mapper.writeValueAsString(map);
    	return json;
    }
    @GetMapping(value="recipe/chef_list_vue.do",produces = "text/plain;charset=UTF-8")
    public String chef_list(int page) throws Exception
    {
    	int rowSize=20;
    	int start=(rowSize*page)-(rowSize-1);
    	int end=rowSize*page;
    	
    	List<ChefVO> list=rService.chefListData(start, end);
    	
    	ObjectMapper mapper=new ObjectMapper();
    	String json=mapper.writeValueAsString(list);
    	
    	return json;
    }
    @GetMapping(value="recipe/chef_page_vue.do",produces = "text/plain;charset=UTF-8")
    public String chef_page(int page) throws Exception
    {
    	int totalpage=rService.chefTotalPage();
    	final int BLOCK=10;
    	int startPage=((page-1)/BLOCK*BLOCK)+1;
    	int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
    	
    	if(endPage>totalpage)
    		endPage=totalpage;
    	
    	Map map=new HashMap();
    	map.put("curpage", page);
    	map.put("totalpage", totalpage);
    	map.put("startPage", startPage);
    	map.put("endPage", endPage);
    	
    	
    	ObjectMapper mapper=new ObjectMapper();
    	String json=mapper.writeValueAsString(map);
    	return json;
    }
    
    // chef =>  제작한 레시피 출력 
    @GetMapping(value="recipe/chef_detail_vue.do",produces = "text/plain;charset=UTF-8")
    /*
     *   params:{
					  page:this.curpage,
					  cno:this.cno,
					  ss:this.ss
				  }
     */
    public String chef_detail_vue(int page,int cno,String ss) throws Exception
    {
        int rowSize=20;
        int start=(rowSize*page)-(rowSize-1);
        int end=rowSize*page;
        
        Map map=new HashMap();
        map.put("cno", cno);
        map.put("start", start);
        map.put("end", end);
        
        List<RecipeVO> list=new ArrayList<RecipeVO>();
        if(ss==null || ss.equals(""))
        {
        	list=rService.chefDetailData(map);
        }
        else
        {
        	map.put("ss", ss);
        	list=rService.chefDetailFindData(map);
        }
        ObjectMapper mapper=new ObjectMapper();
        String json=mapper.writeValueAsString(list);
        return json;
        
    }
    /*
     *    void aaa(int a)
     *    
     *    aaa()
     */
    // 페이지 
    @GetMapping(value="recipe/chef_detail_page_vue.do",produces = "text/plain;charset=UTF-8")
    public String chef_detail_page(int page,int cno,String ss) throws Exception
    {
    	int totalpage=0;
    	if(ss==null || ss.equals(""))
    		totalpage=rService.chefDetailTotalPage(cno);
    	else
    	{
    		Map map=new HashMap();
    		map.put("ss", ss);
    		map.put("cno", cno);
    		totalpage=rService.chefDetailFindTotalPage(map);
    	}
    	
    	final int BLOCK=10;
    	int startPage=((page-1)/BLOCK*BLOCK)+1;
    	int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
    	
    	if(endPage>totalpage)
    		endPage=totalpage;
    	
    	Map map=new HashMap();
    	map.put("curpage", page);
    	map.put("totalpage", totalpage);
    	map.put("startPage", startPage);
    	map.put("endPage", endPage);
    	
    	
    	ObjectMapper mapper=new ObjectMapper();
    	String json=mapper.writeValueAsString(map);
    	return json;
    }
    @GetMapping(value="recipe/recipe_test_vue.do",produces = "text/plain;charset=UTF-8")
    public String recipe_test(int page) throws Exception
    {
    	int rowSize=20;
    	int start=(rowSize*page)-(rowSize-1);
    	int end=rowSize*page;
    	
    	List<RecipeVO> list=rService.recipeListData(start, end);
    	
    	int totalpage=rService.recipeTotalPage();
    	final int BLOCK=10;
    	int startPage=((page-1)/BLOCK*BLOCK)+1;
    	int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
    	int count=rService.recipeCount();
    	if(endPage>totalpage)
    		endPage=totalpage;
    	DecimalFormat df=new DecimalFormat("###,###");
    	String c=df.format(count);
    	
    	Map map=new HashMap();
    	map.put("curpage", page);
    	map.put("totalpage", totalpage);
    	map.put("startPage", startPage);
    	map.put("endPage", endPage);
    	map.put("count", c);
    	
    	Map tMap=new HashMap();
    	tMap.put("list",list); // {} > response.data
    	// {list:[],pages:{curpage...}}
    	tMap.put("pages", map);
    	
    	ObjectMapper mapper=new ObjectMapper();
    	String json=mapper.writeValueAsString(tMap);
    	return json;
    }
    // front요청 처리 
    @GetMapping(value="recipe/recipe_detail_vue.do",produces = "text/plain;charset=UTF-8")
    public String recipe_detail(int no) throws Exception
    {
    	RecipeDetailVO vo=rService.recipeDetaildata(no);
    	String s=vo.getStuff();
    	s=s.replace("구매", "");
    	vo.setStuff(s);
    	
    	List<ReplyVO> list=replyService.replyListData(no);
    	
    	Map map=new HashMap();
    	map.put("detail_data", vo);
    	map.put("reply_list", list);
    	ObjectMapper mapper=new ObjectMapper();
    	String json=mapper.writeValueAsString(map); //vue인식하는 객체로 변경 
    	return json;
    }
    @GetMapping(value="recipe/goods_vue.do",produces = "text/plain;charset=UTF-8")
    public String recipe_goods(String stuff) throws Exception
    {
    	List<GoodsVO> list=rService.recipeGoodsData(stuff);
    	ObjectMapper mapper=new ObjectMapper();
    	String json=mapper.writeValueAsString(list);
    	return json;
    }
    
}