<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="row">
		<c:forEach var="vo" items="${list }">
			<div class="col-md-3" v-for="vo in food_list">
		    <div class="thumbnail">
		      <a href="../goods/goods_detail.do?no=${vo.no }&type=${type}">
		        <img src="${ vo.goods_poster}" style="width:100%" title="${vo.goods_name }">
		        <div class="caption">
		          <p style="font-size: 9px">${vo.goods_price}</p>
		        </div>
		      </a>
		    </div>
		  </div>
		</c:forEach>
	</div>
</body>
</html>