package com.sist.web;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.dao.*;
import com.sist.vo.*;

// 자바스크립트와 연동 => 데이터를 받아서 처리후에 결과값 전송
// @ResponseBody가 변경된 것이다.
@RestController // 화면이동을 제외한 처리부분
public class BoardRestController {
	@Autowired
	private BoardDAO dao;
	
	@GetMapping(value="board/list_vue.do", produces = "text/plain;charset=UTF-8")
	public String board_list(int page) throws Exception
	{
		int rowSize=10;
		int start=(rowSize*page)-(rowSize-1);
		int end=rowSize*page;
		List<BoardVO> list=dao.boardListData(start, end);
		
		ObjectMapper mapper=new ObjectMapper(); // JSON을 자동으로 만들어주는 클래스
		String json=mapper.writeValueAsString(list); // list에 대한 JSON을 자동으로 만들어줬다. => jackson
		return json;
	}
	
	@GetMapping(value = "board/page_vue.do", produces = "text/plain;charset=UTF-8")
	public String board_page(int page) throws Exception
	{
		Map map=new HashMap(); // list에 들어간 값이 없으면 map을 이용해 처리한다.
		int totalpage=dao.boardTotalPage();
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(map);
		return json;
	}
	
	@GetMapping(value = "board/insert_ok.do", produces = "text/plain;charset=UTF-8") // 화면이동 안하고 데이터만 보내준다.
	public void board_insert_ok(BoardVO vo)
	{
		dao.boardInsert(vo);
//		String url="<script>"	// 화면이동 못하니까 script를 이용한 처리
//				+"location.href=\"list.do\""
//				+"</script>";
//		return url;
		// return "redirect:../board/list.do"; // redirect: : 일반 문자열이나 json만 넘길 수 있다. => 사용 불가
	}
	
	@GetMapping(value="board/detail_vue.do", produces = "text/plain;charset=UTF-8")
	public String board_detail(int no) throws Exception
	{
		BoardVO vo=dao.boardDetailData(no);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(vo);
		return json;
	}
	
	@GetMapping(value = "board/update_vue.do", produces = "text/plain;charset=UTF-8")
	public String board_update(int no) throws Exception
	{
		BoardVO vo=dao.boardUpdateData(no);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(vo);
		return json;
	}
	
	@PostMapping(value = "board/update_ok.do", produces = "text/plain;charset=UTF-8")
	public String board_update_ok(BoardVO vo)
	{
		String result=dao.boardUpdate(vo);
		return result;
	}
	
	@GetMapping(value = "board/delete_vue.do", produces = "text/plain;charset=UTF-8")
	public String board_delete_ok(int no, String pwd)
	{
		String result=dao.boardDelete(no, pwd);
		return result;
	}
}













