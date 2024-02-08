package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
@Controller
public class MainController {
	
	@GetMapping("chat/chat.do")
	public String chat_chat()
	{
		return "site/chat/chat";
	}
}
