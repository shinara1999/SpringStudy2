<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container-fluid{
	margin-top: 50px;
}
.row{
	margin: 0px auto;
	width: 100%;
}
.images:hover{
	cursor: pointer;
}
</style>
<script src="https://unpkg.com/vue@3"></script>
</head>
<body>
	<%--  View --%>
	<div class="container">
		<div class="row">
			<h3 class="text-center">{{message}}</h3>
			<input type="button" value="클릭" @click="change()"><br>
			<input type=text size=20 v-model="message">
		</div>
	</div>
	<%-- 여기까지 --%>
	<script>
	 let app=Vue.createApp({
		 // 데이터 설정 => Model
		 data(){
			 // 멤버변수 설정위치
			 return{
				 message:'Hello Vue'
			 }
		 },
		 // 데이터 처리 / 초기화 => ViewModel => MVVM
		 methods:{
			 change(){
				 this.message="변경됨"
			 }
		 }
	 }).mount('.container')
	</script>
</body>
</html>











