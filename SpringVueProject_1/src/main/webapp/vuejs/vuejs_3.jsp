<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
<script src="https://unpkg.com/vue@3"></script> 
</head>
<body>
	<div class="container">
		<div class="row">
		  <table class="table">
			<tr>
				<th class="text-center">사번</th>
				<th class="text-center">이름</th>
				<th class="text-center">성별</th>
				<th class="text-center">부서</th>
				<th class="text-center">근무지</th>
			</tr>
			<tr v-for="vo in sawon">
				<th class="text-center" v-if="vo.loc=='부산'">{{vo.sabun}}</th>
				<th class="text-center" v-if="vo.loc=='부산'">{{vo.name}}</th>
				<th class="text-center" v-if="vo.loc=='부산'">{{vo.sex}}</th>
				<th class="text-center" v-if="vo.loc=='부산'">{{vo.dept}}</th>
				<th class="text-center" v-if="vo.loc=='부산'">{{vo.loc}}</th>
			</tr>
		  </table>
		</div>
		<div class="col-sm-6" v-show="isShow">
			<span>사번:{{}}</span>
		</div>
	</div>
	<script>
	 let app=Vue.createApp({
		 data(){
			 return {
				 sawon:[
					 {"sabun":0318, "name":'신아라', "sex":'여자', "dept":'개발부', "loc":'서울'},
					 {"sabun":0408, "name":'주찬양', "sex":'남자', "dept":'기획부', "loc":'서울'},
					 {"sabun":0506, "name":'전영중', "sex":'남자', "dept":'총무부', "loc":'부산'},
					 {"sabun":1224, "name":'성준수', "sex":'남자', "dept":'영업부', "loc":'부산'},
					 {"sabun":1231, "name":'최종수', "sex":'남자', "dept":'기획부', "loc":'서울'}
				 ]
			 }
		 }
	 }).mount('.container')
	</script>
</body>
</html>