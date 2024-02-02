package com.sist.web;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
// => 타 시스템과 연결할 때 사용 (자바스크립트와 연동) => Ajax, Vue, React => JSON / 일반 문자열 전송
// @ResponseBody (메소드형) => 클래스형 => 많이 사용되고 있다.
// simple-json => 직접 JSON을 만드는 과정 => 자동 완성 (jacksion)
// jacksion => JSON (자바스크립트 객체 표현법) {"키":값, "키":값} => 키를 멤버변수로 인식

import java.util.*;

import javax.servlet.http.HttpSession;

import com.sist.vo.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.service.*;
@RestController
public class FoodRestController {
	@Autowired
	private FoodService service;
	
	@GetMapping( value= "food/list_vue.do", produces = "text/plain;charset=UTF-8")
	public String food_list(int page) throws Exception
	{
		int rowSize=12;
		int start=(rowSize*page)-(rowSize-1);
		int end=rowSize*page;
		List<FoodVO> list=service.foodListData(start, end);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		return json;
	}
	
	@GetMapping(value = "food/page_vue.do", produces = "text/plain;charset=UTF-8")
	public String food_page(int page) throws Exception
	{
		int totalpage=service.foodTotalPage();
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
	
	// 상세보기
	@GetMapping(value="food/detail_vue.do", produces = "text/plain;charset=UTF-8")
	public String food_detail(int fno, HttpSession session) throws Exception
	{
		String id=(String)session.getAttribute("id");
		FoodVO vo=service.foodDetailData(fno);
		
		String sId="";
		if(id==null)
			sId="";
		else
			sId=id;
		vo.setSessionId(sId); // 임시저장 => 댓글 기능 사용했을 때 수정/삭제가 가능하게 한다.
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(vo);
		return json;
	}
	
}









