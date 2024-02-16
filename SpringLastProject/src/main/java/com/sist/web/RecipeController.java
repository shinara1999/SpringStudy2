package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecipeController {
	@GetMapping("recipe/recipe_list.do")
	public String recipe_list()
	{
		return "recipe/recipe_list";
	}
	
	@GetMapping("recipe/chef_list.do")
	public String chef_list()
	{
		return "recipe/chef_list";
	}
	
	@GetMapping("recipe/chef_detail.do")
	public String chef_detail(int cno, Model model)
	{
		model.addAttribute("cno", cno);
		return "recipe/chef_detail";
	}
	
	@GetMapping("recipe/recipe_detail.do")
	public String recipe_detail(int no, Model model)
	{
		model.addAttribute("no", no);
		return "recipe/recipe_detail";
	}
	
	
	
}
