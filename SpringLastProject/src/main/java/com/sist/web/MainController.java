package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.*;
import com.sist.vo.*;
import com.sist.service.*;

@Controller
public class MainController {
	
	@Autowired
	private FoodService service;
	
	@Autowired
	private RecipeService service2;
	
	@GetMapping("main/main.do")
	public String main_main(Model model)
	{
		// jsp로 값을 전송하는 객체 ==> Model (HttpServletRequest)
		List<FoodVO> foodList=service.foodHome12();
		model.addAttribute("foodList", foodList);
		
		// 레시피, 쉐프
		List<ChefVO> chefList=service2.chefHome12();
		List<RecipeVO> recipeList=service2.recipeHome12();
		model.addAttribute("chefList", chefList);
		model.addAttribute("recipeList", recipeList);
		return "main";
	}
	
}






