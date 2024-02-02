package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.vo.BoardVO;

@Controller	// 화면 변경 => forward (request 전송 / sendRedirect (재호출))
			//			  경로명 / 파일명           redirect:.do => request를 초기화 
public class BoardController {
	
	// 게시판 목록 화면 이동
	@GetMapping("board/list.do")
	public String board_list()
	{
		return "board/list";
	}
	
	// 게시판 글 작성 화면 이동
	@GetMapping("board/insert.do")
	public String board_insert()
	{
		return "board/insert";
	}
	
	@GetMapping("board/detail.do")
	public String board_detail(int no, Model model)
	{
		model.addAttribute("no", no);
		return "board/detail";
	}
	
	@GetMapping("board/update.do")
	public String board_update(int no, Model model)
	{
		model.addAttribute("no", no);
		return "board/update";
	}
	
}
