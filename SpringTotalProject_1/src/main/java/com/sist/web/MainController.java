package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.sist.service.FoodService;

/*
		@Controller / @RestController => 면접 (80%)
		=============================
		Spring
			=> Spring / Spring MVC / Spring-Boot의 차이점
			=> Spring Framework => 생명주기
			=> Bean
			=> Interceptor / Filter의 차이점
			   				  ------- WebContext -----------------
			   client -----   |
			   		  -----	  |	   ------ SpringContext ------
			   		  	      | Filter ---- DispacherServlet
			   		  	      |    ---------------------------
			   		  	      ------------------------------------
			   		  요청을 전달받으면 전 / 후 => url 패턴에 맞게 모든 요청에 대한 부가적인 작업
			   		    => web.xml <filter>
			   		    예 ) 보안 처리 (인가) , 한글변환
			   		    
			   => 중간에 처리
			      client ==== DispacherServlet ==== HandlerMapping ==== ViewResolver ==== JSP
			      								 |					 |				   |
			      						    preHandle()			postHandle()	afterCompletion()
			      						     요청처리 전    		 요청처리 후			  완료
			      						     					 데이터 전송
			      						     	 | 자동로그인
			   Filter
			    => 용도
			       공통된 보안 및 인증 / 인가 관련 작업
			       요청에 대한 로깅 / 검사
			       이미지 / 데이터 압축, 문자열 인코딩
			       ---------------- docker
			   Interceptor
			    => 용도
			      
			***=> Ioc / DI
			***=> Container의 종류 / Container란?
			***=> VO / DTO / Entity ===> 100%
			***=> MVC 구조에 대한 설명
			      => servlet / jsp
			=> Spring MVC에서 Http요청이 들어왔을 경우 흐름에 대해 설명
			   => 면접 => 돌려서 나온다..
		    => 기타
		       => Spring-Security의 구조에 대해 설명 (올린 경우 필수)
		       => *** AOP 이용하는 방법 (AOP : 내가 필요한 위치에서 언제든지 가져다 쓸 수 있다 , OOP를 보완한 것이 AOP이다.)
		       			=> 코드를 부분적으로 나눠서 모듈화 처리
		       									| 메소드
		       									| 클래스
		       모든 프로그램은
		       	= 핵심
		       	= 공통 : getConnection, disConnection => 자동 호출
		       두개로 나눠진다.
		       JoinPoint : 어떤 위치에서 호출할 것인지
		                   Before
		                   After
		                   After-Returning
		                   After-Throwing
		                   Around
		       PointCut : 어떤 메소드에서 적용할 것인지 => 메소드 지정
		       		public String display()
		       		{
		       		    => Before 영역
		       			try
		       			{
		       				---------------- Around 영역
		       				
		       				----------------
		       				
		       			}catch(Exception e)
		       			{
		       				=> 에러 처리 => After-Throwing
		       			}
		       			finally
		       			{
		       				=> 자동화 처리 => After
		       			}
		       			return 값 => After-Returning
		       		}
		       Advice : JoinPoint + PointCut
		       Aspect : Advice => 공통 모듈
		       Weaving : 메소드가 합쳐지는 과정
		       
		       - Weaving 예시 => 호출하는 위치를 묶어주는 것
		       @Before("disp()")
		       public void aaa(){}
		       @After("disp()")
		       public void bbb(){}
		       @AfterThrowing("disp()")
		       public void ccc(){}
		       
		       public void disp()
		       {
		       	aaa()
		       	try
		       	{
		       	}catch(Exception e)
		       	{
		       		ccc()
		       	}
		       	finally
		       	{
		       		bbb()
		       	}
		       }
		       
		       OOP => 객체지향 프로그램
		       
		Java (자바에서는 질문에 답 못하면 100% 떨어진다.)
			=> Java / Python => 차이점
			=> Java에서 접근지정어
			=> JVM의 구조
			=> GC
			=> ***** Call By Reference / Call By Value
			=> *** 리플렉션
			=> for / foreach 차이점
			=> Wrapper의 사용처 => 만들어진 배경
			=> 클래스, 객체, 인스턴스
			=> 제네릭스
			=> 직렬화 / 역직렬화 => IO
			=> equals / ==
			=> 추상 클래스 / 인터페이스의 차이점
			=> 오버로딩 / 오버라이딩
			=> 싱글턴 / 팩토리패턴
			=> String / StringBuffer / StringBuilder
			=> 예외처리의 종류와 예외처리의 정의
			=> List / Set / Map
		Oracle
			=> JOIN / Subquery
			=> 프로시저 / 트리거
			=> 데이터형 (BLOB / BFile)
			=> View의 종류
			=> DML / DDL / TCL
		Front
			=> HTML
			   GET / POST => http/https
			=> JSP : 생명주기 / Session/Cookie, JSTL/EL, DBCP, MVC
			=> Servlet => JSP/Servlet 비교
			=> JavaScript => ES6 => let/var/cosnt , => , ... , 호이스팅 / ***클로저
															   -----------------
			   JSON / JSONP => Cross Origin
			=> VueJS : 장단점, 특징
			=> ReactJS / Redux : 장단점, 특징
			=> Ajax : XML , JSON , 문자열
		============================================ 40%, 60%는 프로젝트 (위에것들을 프로젝트의 어떤 부분에 적용했는지 부연설명을 해야한다.)
			솔루션 : 쓰레드, 네트워크, 자료구조 => 코딩테스트
		*** 정의 => 부여(프로그램안에서) => 모르는 내용
		============================================
		
			@Controller @RestController => 문자열 , JSON
						 React / Vue ...
						 => 독립적 / 브라우저 => cdn
		
		# 파일 관리 => 다른 프로그램 연동
					  | 자바스크립트 / 코틀린 (모바일)
					  | Spring-Boot
					  	  | 자바 / 코틀린 => 라이센스
					  	  
		사용할 클래스 제작
		--------------
			클래스와 클래스의 연관 관계 => DI
			프레임워크 : 툴 => 메인보드 (CPU, 메모리... => 클래스)
			  | 라이브러리의 집합 => 레고 / 완제품
			  					 === 전자정부 프레임워크 / Any프레임워크 (삼성)
			  					 === 마이플랫폼
			  					 === WAS (제우스, 웹로직, 웹스페어)
			1. 클래스 구분자 => 필요한 클래스 찾기
		
		=> Spring / Spring Boot 차이점
		   => 핵심 모듈을 모아서 만든 프레임워크
		      ------- 동작을 위한 라이브러리 집합
		      ------- 단점 : 설정파일이 어렵다.
		              pom.xml / web.xml / application-*.xml
		              ------- 호환성
		   => 설정 파일을 자동으로 생성 => spring 프레임워크를 쉽게 사용 가능하게 만든 것
		      xml => properties => yml (파이썬) => 들여쓰기
		      server:
		      			port=80 => Spring Cloud
 */
@Controller
// Target(value={TYPE})
public class MainController {
	private FoodService fService; // 통합 => BI
	// 권장사항 => 자동 주입
	// Target(value={CONTRUCTOR, METHOD, PARAMETER, FIELD, ANNOTATION_TYPE})
	@Autowired 
	// @Qualifier => 여러개가 중복된 경우에 특정 개체를 주입받는 경우
	// @Qualifier @Resource => @Autowired+@Qualifier
	// @Resource => 1.8 지원 => 1.8이 가장 호환성이 좋다.
	// 스프링에 등록된 클래스 객체의 주소값을 찾아서 대입
	// interface => 중복이 있는 경우에는 @Autowired는 사용 불가
	public MainController(FoodService fService)
	{
		this.fService=fService;
	}
}


















