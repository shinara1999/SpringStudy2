package com.sist.web;

import java.net.http.HttpRequest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FoodController {
	@GetMapping("food/food_find.do")
	public String food_find()
	{
		return "food/food_find";
	}
	
	@GetMapping("food/food_list.do")
	public String food_list()
	{
		return "food/food_list";
	}
	
	// 쿠키
	@GetMapping("food/food_before_list_detail.do")
	public String food_before_list(int fno, RedirectAttributes ra, HttpServletResponse response)
	{
		Cookie cookie=new Cookie("food_"+fno, String.valueOf(fno)); // cookie는 문자열만 저장 가능
		cookie.setPath("/");
		cookie.setMaxAge(60*60*24);
		response.addCookie(cookie);
		ra.addAttribute("fno", fno);
		return "redirect:../food/food_list_detail.do";
	}
	
	@GetMapping("food/food_list_detail.do")
	public String food_list_detail(int fno, Model model)
	{
		model.addAttribute("fno", fno);
		return "food/food_detail";
	}
	
	@GetMapping("recipe/recipe_test.do")
	public String recipe_test()
	{
		return "recipe/recipe_test";
	}
	
}
