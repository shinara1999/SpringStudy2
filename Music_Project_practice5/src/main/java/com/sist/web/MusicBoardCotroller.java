package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.service.MusicBoardService;
import com.sist.vo.MusicBoardVO;

@Controller
public class MusicBoardCotroller {
	
	@GetMapping("musicboard/notice.do")
	public String musicboard_notice()
	{
		return "musicboard/notice";
	}
	
	@GetMapping("musicboard/detail.do")
	public String musicboard_detail(int no, Model model)
	{
		model.addAttribute("no", no);
		return "musicboard/detail";
	}
	
	@GetMapping("musicboard/noticeInsert.do")
	public String musicboard_noticeInsert()
	{
		return "musicboard/noticeInsert";
	}
}
