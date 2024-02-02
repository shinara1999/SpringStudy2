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
	width: 700px;
}
</style>
<script src="https://unpkg.com/vue@3"></script> <!-- vue 이용 -->
<script src="https://unpkg.com/axios/dist/axios.min.js"></script> <!-- 데이터 입출력 -->
</head>
<body>
	<div class="container">
	  <h3 class="text-center">내용보기</h3>
		<div class="row">
			<table class="table">
				<tr>
					<th width="20%" class="text-center">번호</th>
					<td width="30%" class="text-center">{{board_detail.no}}</td>
					<th width="20%" class="text-center">작성일</th>
					<td width="30%" class="text-center">{{board_detail.dbday}}</td>
				</tr>
				<tr>
					<th width="20%" class="text-center">이름</th>
					<td width="30%" class="text-center">{{board_detail.name}}</td>
					<th width="20%" class="text-center">조회수</th>
					<td width="30%" class="text-center">{{board_detail.hit}}</td>
				</tr>
				<tr>
					<th width="20%" class="text-center">제목</th>
					<td colspan="3">{{board_detail.subject}}</td>
				</tr>
				<tr>
					<td colspan="4" class="text-left" valign="top" height="200">
						<pre style="white-space: pre-wrap;">{{board_detail.content}}</pre>
					</td>
				</tr>
				<tr>
					<td colspan="4" class="text-right">
						<input type=button value="수정" class="btn-xs btn-success" @click="update()">
						<input type=button value="삭제" class="btn-xs btn-primary" @click="del()" ref="delBtn">
						<input type=button value="목록" class="btn-xs btn-warning" @click="listData()">
					</td>
				</tr>
				 <tr v-show="isShow">
		          <td colspan="4" class="text-right">
		          비밀번호:<input type=password class="input-sm" ref="pwd">
		            <input type=button class="btn-sm btn-success" value="삭제" @click="boardDelete()">
		          </td>
		        </tr>
			</table>
		</div>
	</div>
	<script>
	 let app=Vue.createApp({
		 data(){
			 return{
				 board_detail:{},
				 no:${no},
				 change:0,
				 isShow:false
			 }
		 },
		 mounted(){
			 axios.get('../board/detail_vue.do',{
				 params:{
					 no:this.no
				 }
			 }).then(response=>{
				 console.log(response.data)
				 this.board_detail=response.data
			 }).catch(error=>{
				 console.log(error.response)
			 })
			 //$(function(){=>mounted})
		 },
		 methods:{
			 update(){
				 location.href="../board/update.do?no="+this.no
			 },
			 listData(){
				 location.href="../board/list.do"
			 },
			 del(){
				 if(this.change===0)
				 {
					 this.change=1; 
					 //$('#delBtn').val("취소")
					 //$('#del').show()
					 this.isShow=true;
					 this.$refs.delBtn.value="취소";
				 }
				 else
				 {
					 this.change=0; 
					 //$('#delBtn').val("수정")
					 //$('#del').hide()
					 this.isShow=false;
					 this.$refs.delBtn.value="수정";
				 }
			 },
			 boardDelete(){
				 let pwd=this.$refs.pwd.value;
				 if(pwd==="")
				 {
					 this.$refs.pwd.focus()
					 return
				 }
				 
				 axios.get('../board/delete_vue.do',{
					 params:{
						 no:this.no,
						 pwd:pwd
					 }
				 }).then(response=>{
					 if(response.data==='yes')
				     {
						location.href="../board/list.do" 
				     }
					 else
					 {
						alert("비밀번호가 틀렸습니다!!")
						this.$refs.pwd.value=""
						this.$refs.pwd.focus()
					 }
				 }).catch(error=>{
					 console.log(error.response)
				 })
			 }
		 }
	 }).mount('.container')
	</script>
</body>
</html>



















