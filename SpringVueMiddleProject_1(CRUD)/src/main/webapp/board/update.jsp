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
	  <h3 class="text-center">수정</h3>
		<div class="row">
		  <form @submit.prevent="submitForm">
			<table class="table">
				<tr>
					<th width="15%" class="text-center">이름</th>
					<td width="85%">
						<input type=text size=15 class="input-sm" v-model="name" ref="name">
					</td>
				</tr>
				<tr>
					<th width="15%" class="text-center">제목</th>
					<td width="85%">
						<input type=text size=56 class="input-sm" v-model="subject" ref="subject">
					</td>
				</tr>
				<tr>
					<th width="15%" class="text-center">내용</th>
					<td width="85%">
						<textarea rows="10" cols="52" v-model="content" ref="content"></textarea>
					</td>
				</tr>
				<tr>
					<th width="15%" class="text-center">비밀번호</th>
					<td width="85%">
						<input type=password size=15 class="input-sm" v-model="pwd" ref="pwd">
					</td>
				</tr>
				<tr>
					<td colspan="2" class="text-center">
						<input type="submit" value="수정" class="btn-sm btn-warning">
						<input type="button" value="취소" onclick="javascript:history.back()"
							class="btn-sm btn-info"
						>
					</td>
				</tr>
			</table>
		  </form>
		</div>
	</div>
	<script>
	 let app=Vue.createApp({
		 data(){
			 return{
				 name:'',
				 subject:'',
				 content:'',
				 pwd:'',
				 no:${no}
			 }
		 },
		 mounted(){
			axios.get('../board/update_vue.do',{
				params:{
					no:this.no
				}
			}).then(response=>{
				this.name=response.data.name
				this.subject=response.data.subject
				this.content=response.data.content
			})
		 },
		 methods:{
			 submitForm(){
				 if(this.name==='')
				 {
					 this.$refs.name.focus()
					 return
				 }
				 if(this.subject==='')
				 {
					 this.$refs.subject.focus()
					 return
				 }
				 if(this.content==='')
				 {
					 this.$refs.content.focus()
					 return
				 }
				 if(this.pwd==='')
				 {
					 this.$refs.pwd.focus()
					 return
				 }
				 
				 /*let form=new FormData()
				 form.append("name", this.name)
				 form.append("subject", this.subject)
				 form.append("content", this.content)
				 form.append("pwd", this.pwd)*/
				 
				 axios.post('../board/update_ok.do',null,{
					params:{
					 	name:this.name,
					 	subject:this.subject,
					 	content:this.content,
					 	pwd:this.pwd,
					 	no:this.no
					} 
				 }).then(response=>{
					 			if(response.data==='yes')
					 			{
				 					location.href="../board/detail.do"+this.no
					 			}
					 			else
					 			{
					 				alert("비밀번호가 틀립니다!!")
					 				this.pwd='';
					 				this.$refs.pwd.focus()
					 			}
				 			})
				 			.catch(error=>{
				 				console.log(error.response)
				 			})
			 }
		 }
	 }).mount('.container')
	</script>
</body>
</html>













