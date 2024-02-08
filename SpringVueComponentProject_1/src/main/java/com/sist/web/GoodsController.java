package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GoodsController {
	
	@GetMapping("goods/list.do")
	public String goods_list()
	{
		return "goods/list";
	}
}
