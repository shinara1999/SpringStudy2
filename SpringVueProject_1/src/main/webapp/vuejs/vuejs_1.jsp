<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://unpkg.com/vue@3"></script>
<!--  <script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#msg').keyup(function(){
		$('#print').text($('#msg').val())
	})
})
</script> -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
	margin-top: 50px;
}
.row{
	margin: 0px auto;
	width: 800px;
}
</style> 
</head>
<!-- 
	Vue : 1. 가상 돔을 사용 => 속도를 빠르게 처리
		  mount => 가상 메모리에 저장
		  => String / StringBuffer
		  2. 생명주기 => callback
		  3. 디렉티브 => 제어문
		  4. 서버연동 => axios
		  5. 출력형식 => {{}} / :src=
		  6. 양방향 통신 => v-model
		  7. router => 화면 변경
		  ==============================
		  8. vue-bootstrap
		  
	1. 생명주기 => vue3(react), vuex(mvc)
	   beforeCreate()
	   created()
	   --------------------- Vue 객체 생성
	   beforeMount()
	   mounted()
	   --------------------- 가상메모리에 HTML을 올린 경우
	   	  => $(function(){}), window.onload
	   	  => 서버에서 데이터를 읽는다. : 멤버변수에 저장
	   	  => Jquery 연동
	   beforeUpdate()
	   
	
		  
 -->
<body>
	<div class="container">
		<div class="row">
			<input type="text" size=30 class="input-sm" v-model="msg">
		</div>
		<div class="row">
			<div>{{msg}}</div>
		</div>
	</div>
	<script>
	 // VueJS setting
	 // Vue 객체 생성
	 let app=Vue.createApp({
		 data(){
			 // 멤버변수 설정
			 return {
				msg: ''	 
			 }
		 },
		 // callback => Vue에 의해 자동 호출
		 beforeCreate(){
			 console.log("Vue 객체 생성전:beforeCreate() Call")
		 },
		 created(){
			console.log("Vue 객체 생성 완료:created() Call") 
		 },
		 beforeMount(){
			console.log("HTML과 데이터를 가상메모리에 올라가기 전:beforeMount() Call") 
		 },
		 mounted(){
			console.log("가상메모리에 HTML이 올라간 상태:window.onload,$(function),mounted() Call") 
		 },
		 beforeUpdate(){
			console.log("변경 전:beforeUpdate() Call") 
		 },
		 updated(){
			console.log("변경 완료:updated() Call") 
		 },
		 beforeDestroy(){
			console.log("Vue객체 메모리 해제 전:beforeDestroy() Call") 
		 },
		 destroyed(){
			console.log("Vue객체 메모리 해제 완료:destroy() Call") 
		 },
		 // 사용자정의 메소드 들어가는 부분
		 methods:{
			 
		 }
	 }).mount('.container')
	</script>
</body>
</html>
















