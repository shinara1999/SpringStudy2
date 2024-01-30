<%@page import="org.jsoup.Jsoup"%>
<%@page import="org.jsoup.nodes.Document"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, java.net.*"%>
<%
	String data="";
	try
	{
		String no=request.getParameter("no");
		String[] movies={"", "searchMainDailyBoxOffice.do", // 일일박스오피스
						"searchMainRealTicket.do", 		    // 실시간예매율
						"searchMainDailySeatTicket.do"};    // 좌석점유율
		String url="https://www.kobis.or.kr/kobis/business/main/";
		Document doc=Jsoup.connect(url+movies[Integer.parseInt(no)]).get();
		// System.out.println(doc.toString());
		data=doc.toString();
		data=data.substring(data.indexOf("["), data.lastIndexOf("]")+1);
	}catch(Exception e){}
%>
<%=data %>