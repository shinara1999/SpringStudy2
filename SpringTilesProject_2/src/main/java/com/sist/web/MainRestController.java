package com.sist.web;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import javax.servlet.http.HttpSession;

import com.sist.vo.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.service.*;
@RestController
public class MainRestController {
	@Autowired
	private FoodService service;
	
	@GetMapping(value = "food/list_vue.do", produces = "text/plain;charset=UTF-8")
	public String food_list(int page) throws Exception
	{
		int rowSize=12;
		int start=(rowSize*page)-(rowSize-1);
		int end=rowSize*page;
		
		List<FoodVO> list=service.foodListData(start, end);
		// JSON 변경 => 그래야 vueJS에서 읽어들일 수 있다.
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
		
		Map map=new HashMap(); // vo나 list에 없는 것은 map으로 묶어서 보내줘야 한다.
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("totalpage", totalpage);
		map.put("curpage", page);
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(map);
		return json;
	}
	
	@GetMapping(value = "food/detail_vue.do", produces = "text/plain;charset=UTF-8")
	public String food_detail(int fno, HttpSession session) throws Exception
	{
		String id=(String)session.getAttribute("id");

		FoodVO vo=service.foodDetailData(fno);
		vo.setSessionId(id);
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(vo);
		return json;
	}
	
	@GetMapping(value = "member/login_vue.do", produces = "text/plain;charset=UTF-8")
	public String member_login(String id, String pwd, HttpSession session)
	{
		MemberVO vo=service.isLogin(id, pwd);
		if(vo.getMsg().equals("OK"))
		{
			session.setAttribute("id", vo.getId());
			session.setAttribute("name", vo.getName());
			// session => 서버측에 저장, 저장기간 => 30분
		}
		return vo.getMsg();
	}
	
	@GetMapping(value = "member/logout_vue.do", produces = "text/plain;charset=UTF-8")
	public void member_logout(HttpSession session)
	{
		session.invalidate();
	}
	
	public String commonsData(int fno) throws Exception
	{
		List<ReplyVO> list=service.replyListData(fno);
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		return json;
	}
	
	@GetMapping(value = "reply/list_vue.do", produces = "text/plain;charset=UTF-8")
	public String reply_list(int fno) throws Exception
	{
		return commonsData(fno);
	}
	
	@GetMapping(value = "reply/insert_vue.do", produces = "text/plain;charset=UTF-8")
	public String reply_insert(int fno, String msg, HttpSession session) throws Exception
	{
		String id=(String)session.getAttribute("id");
		String name=(String)session.getAttribute("name");
		
		ReplyVO vo=new ReplyVO();
		vo.setFno(fno);
		vo.setId(id);
		vo.setName(name);
		vo.setMsg(msg);
		service.replyInsert(vo);
		
		return commonsData(fno);
	}
	
	@GetMapping(value = "reply/delete_vue.do", produces = "text/plain;charset=UTF-8")
	public String reply_delete(int rno, int fno) throws Exception
	{
		service.replyDelete(rno);
		return commonsData(fno);
	}
	
	@GetMapping(value = "reply/update_vue.do", produces = "text/plain;charset=UTF-8")
	public String reply_update(ReplyVO vo) throws Exception
	{
		service.replyUpdate(vo);
		return commonsData(vo.getFno());
	}
}















