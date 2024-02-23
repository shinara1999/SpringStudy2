package com.sist.web;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.manager.FoodRecommandManager;
import com.sist.service.*;
import com.sist.vo.*;

@RestController
@RequestMapping("food/")
/*
	사용자 (클라이언트 => 브라우저) => 요청 (URL) .do
										  ===== 현재 사이트에서 가능
										  
	- 서버 관련 부분
	DispacherServlet => 요청 받기 (request, response)
		= WebApplicationContext => 사용자가 등록한 클래스 관리
								   관리가 필요한 모든 클래스를 xml에 설정해서 전송
		= HandlerMapping => 요청했을 때 처리하는 Controller/RestController를 찾는 역할
		= ViewResolver : JSP를 찾아서 request 전송
		
	Model : 
	Mapper : 테이블 1개를 다루는 경우
	Service : 관련된 mapper가 여러개 있는 경우
	View (jsp) : 화면 출력
	
	list.do
	
	Model
	=====
		=> RestController
		   => 다른 언어 (JSON)와 연동 시에 사용
		   => 자바스크립트와 자바간 연동
		      자바 => VO => {} (Object)
		      자바 => List => [] (Array)
		      => String => string => '', ""
		=> Controller : 화면 이동 (변경)
		   => forward => request를 전송할 경우
		                 ======= 스프링에서 제공하는 전송 객체 이용 : Model
		                 return "폴더명/jsp이름";
		   => redirect => 재호출 시 사용 => .do
		   				  request를 초기화시키고 간다.
		   				  return "redirect:.. .do";
		   				  _ok
 */
public class FoodRestController {
	@Autowired
	private FoodService service;
	
	@Autowired
	private FoodRecommandManager mgr;
	
	@GetMapping(value = "find_vue.do", produces = "text/plain;charset=UTF-8")
	public String food_find(int page, String fd) throws Exception
	{
		int rowSize=20;
		int start=(rowSize*page)-(rowSize-1);
		int end=rowSize*page;
		
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		map.put("address", fd);
		
		List<FoodVO> list=service.foodFindData(map);
		
		//JSON변경
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		return json;
	}
	
	@GetMapping(value = "page_vue.do", produces = "text/plain;charset=UTF-8")
	public String food_page(int page, String fd) throws Exception
	{
		final int BLOCK=10;
		int startPage=((page-1)/BLOCK*BLOCK)+1;
		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		
		Map map=new HashMap();
		map.put("address", fd);
		int totalpage=service.foodFindCount(map);
		if(endPage>totalpage)
			endPage=totalpage;
		
		map=new HashMap();
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		
		//JSON변경
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(map);
		return json;
	}
	
	/*
		중요
		= 어노테이션은 반드시 밑에 있는 변수, 메소드, 클래스 제어
	 */
	@GetMapping(value = "detail_vue.do", produces = "text/plain;charset=UTF-8")
	public String food_detail(int fno) throws Exception
	{
		FoodVO vo=service.foodDetailData(fno);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(vo);
		return json;
	}
	
	// cookie
	@GetMapping(value = "food_cookie_vue.do", produces = "text/plain;charset=UTF-8")
	public String food_cookie(HttpServletRequest request) throws Exception
	{
		Cookie[] cookies=request.getCookies();
		List<FoodVO> list=new ArrayList<FoodVO>();
		int k=0;
		if(cookies!=null)
		{
			for(int i=cookies.length-1;i>=0;i--)
			{
				if(k<9)
				{
					if(cookies[i].getName().startsWith("food_"))
					{
						String fno=cookies[i].getValue();
						FoodVO vo=service.foodDetailData(Integer.parseInt(fno));
						list.add(vo);
					}
					k++;
				}
			}
		}
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		return json;
	}
	
	@GetMapping(value = "food_list_vue.do", produces = "text/plain;charset=UTF-8")
	public String food_list(int page) throws Exception
	{
		Map map=new HashMap();
		map.put("start", (20*page)-19);
		map.put("end", 20*page);
		
		List<FoodVO> list=service.foodListData(map);
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		return json;
	}
	
	@GetMapping(value = "food_page_vue.do", produces = "text/plain;charset=UTF-8")
	public String food_list_page(int page) throws Exception
	{
		final int BLOCK=10;
		int startPage=((page-1)/BLOCK*BLOCK)+1;
		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;

		int totalpage=service.foodListCount();
		if(endPage>totalpage)
			endPage=totalpage;
		
		Map map=new HashMap();
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		
		//JSON변경
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(map);
		return json;
	}
	
	@GetMapping(value = "food_detail_vue.do", produces = "text/plain;charset=UTF-8")
	public String food_detail_vue(int fno) throws Exception
	{
		FoodVO vo=service.foodListDetailData(fno);
		
		//JSON변경
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(vo);
		return json;
	}
	
	@GetMapping(value = "food_recommand_sub.do", produces = "text/plain;charset=UTF-8")
	public String food_recommand(int no) throws Exception
	{
		String[] menu1= {
				"퇴근길", "휴식", "휴가", "여행", "드라이브", "산책", "운동"
		};
		String[] menu2= {
				"기분전환", "외로움", "슬픔", "힘찬", "이별", "지침", "힘듦", "설렘", "오후", "위로", "밤", "새벽", "저녁", "아침", "사랑", "축하"
		};
		String[] menu3= {
				"밝은", "신나는", "편안한", "따뜻한", "달콤한", "발렌타인데이", "화이트데이"
		};
		String[] menu4= {
				"봄", "여름", "가을", "겨울", "추운날", "흐린날", "비오는날", "더운날", "눈오는날"
		};
		
		ObjectMapper mapper=new ObjectMapper();
		String json="";
		if(no==1)
		{
			json=mapper.writeValueAsString(menu1);
		}
		else if(no==2)
		{
			json=mapper.writeValueAsString(menu2);
		}
		else if(no==3)
		{
			json=mapper.writeValueAsString(menu3);
		}
		else if(no==4)
		{
			json=mapper.writeValueAsString(menu4);
		}
		
		return json;
	}
	
	@GetMapping(value = "food_recommand_data.do", produces = "text/plain;charset=UTF-8" )
	public String food_recommand_data(String fd) throws Exception
	{
		List<String> list=mgr.newsFind(fd);
		List<String> fList=service.foodAllData();
		/*
			1. 단어 => constains()
			2. 기호 : * (0 이상)	맛있다 , 맛있고 , 맛있는 => 맛* (맛으로 시작하는 모든 단어)
					 + (1 이상)  맛+
					 ? (0, 1)
					 | (선택)
					 . 임의의 한글자 맛.
					 => 실제 기호 : \\+
					 [] => 범위
					    숫자 [0-3] => [0-9]
					    영문 [A-Z] [a-z] => [A-Za-z]
					    한글 [가-힣]
					 {} => 갯수
					 {3} , {1,3}
					 ^ : 시작
					 $ : 끝
		 */
		
		List<String> rList=new ArrayList<String>();
		int[] count=new int[fList.size()];
		for(String s:list) // 추천
		{
			System.out.println(s);
			for(int i=0;i<fList.size();i++)
			{
				if(s.contains(fList.get(i)))
				{
					System.out.println(fList.get(i));
					rList.add(fList.get(i));
					count[i]++;
				}
			}
		}
		Set set=new HashSet();
		for(int i=0;i<rList.size();i++)
		{
			set.add(rList.get(i)); // 중복제거
		}
		
		List<FoodVO> dList=new ArrayList<FoodVO>();
		Iterator iter=set.iterator();
		while(iter.hasNext())
		{
			String ss=iter.next().toString();
			List<FoodVO> vo=service.foodNameInfoData(ss);
			dList.add(vo.get(0));
		}
		//JSON변경
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(dList);
		return json;
	}
	
	@GetMapping(value="food_detail_recipe.do", produces = "text/plain;charset=UTF-8")
	public String food_detail_recipe(int fno) throws Exception
	{
		FoodVO vo=service.foodListDetailData(fno);
		System.out.println("type:"+vo.getType());
		String s=vo.getType().replace("/", "|");
		
		List<RecipeVO> list=service.foodRecipeData(s);
		
		//JSON변경
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		return json;
	}

}











