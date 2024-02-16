package com.sist.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.sist.manager.NewsManager;
import com.sist.service.*;
import com.sist.vo.*;

@Aspect // 공통모듈이라는 것을 알려준다.
@Component // 메모리 할당
public class CommonsSendAop {
	
	@Autowired
	private FoodService service;
	
	@Autowired
	private NewsManager mgr;
	
	@After("execution(* com.sist.web.*Controller.*(..))")
	public void footerSend()
	{
		List<FoodVO> fList=service.foodTop7();
		List<NoticeVO> nList=service.noticeTop7();
		List<NewsVO> newList=mgr.newsFind("맛집");
		/*
		 	@Controller, @RestController 를 제외한 모든 클래스에서 request 필요한 경우에 사용
		 	============================
		 	 | request를 요청하면 => DispacherServlet에 의해 매개변수로 주입
		 */
		// 직접 생성해서 사용해야 데이터 출력이 가능하다.
		HttpServletRequest request=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		
		request.setAttribute("nList", nList);
		request.setAttribute("fList", fList);
		request.setAttribute("newList", newList);
	}
}
