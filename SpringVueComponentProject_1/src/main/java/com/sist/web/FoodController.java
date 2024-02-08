package com.sist.web;
// JSP와 연결되는 부분 => 화면 변경

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FoodController {

	@GetMapping("food/list.do")
	public String food_list()
	{
		return "food/list";
	}
}
