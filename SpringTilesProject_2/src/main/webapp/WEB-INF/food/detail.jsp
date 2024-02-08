<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">

</script>
<style type="text/css">
a.alink:hover{
	cursor: pointer;
}
</style>
</head>
<body>
	<div class="container" id="detailApp">
		<div class="row">
			<table class="table">
				<tr>
					<td width="30%" class="text-center" rowspan="8">
						<img :src="'https://www.menupan.com/'+detail_data.poster" style="width:100%">
					</td>
					<td colspan="2">
						<h3><span id="name">{{detail_data.name}}</span>&nbsp;<span style="color: orange;">{{detail_data.score}}</span></h3>
					</td>
				</tr>
				<tr>
					<td class="text-center" width="10%">음식종류</td>
					<td width="60%">{{detail_data.type}}</td>
				</tr>
				<tr>
					<td class="text-center" width="10%">주소</td>
					<td width="60%">{{detail_data.address}}</td>
				</tr>
				<tr>
					<td class="text-center" width="10%">전화</td>
					<td width="60%">{{detail_data.phone}}</td>
				</tr>
				<tr>
					<td class="text-center" width="10%">테마</td>
					<td width="60%">{{detail_data.theme}}</td>
				</tr>
				<tr>
					<td class="text-center" width="10%">가격대</td>
					<td width="60%">{{detail_data.price}}</td>
				</tr>
				<tr>
					<td class="text-center" width="10%">영업시간</td>
					<td width="60%">{{detail_data.time}}</td>
				</tr>
				<tr>
					<td class="text-center" width="10%">좌석</td>
					<td width="60%">{{detail_data.seat}}</td>
				</tr>
				<tr>
					<td colspan="3" class="text-center">
						<input type=button value="목록" class="btn btn-xs btn-primary" onclick="javascript:history.back()">
					</td>
				</tr>
			</table>
		</div>
	</div>
	<!-- component : 재사용 목적 -->
	<div class="container" id="replyApp">
		<div class="row">
			<table class="table">
				<tr>
					<td>
						<table class="table" v-for="vo in reply_list">
							<tr>
								<td class="text-left">★{{vo.name}}&nbsp;(작성일:&nbsp;{{vo.dbday}})</td>
								<td class="text-right">
									<span v-if="vo.id===sessionId">
										<input type=button value="수정" class="btn btn-xs btn-info updates" style="margin-right: 3px" :id="'up'+vo.rno" @click="replyUpdateForm(vo.rno)">
										<input type=button value="삭제" class="btn btn-xs btn-warning" @click="replyDelete(vo.rno)">
									</span>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<pre style="white-space: pre-wrap;background-color: white;border: none;">{{vo.msg}}</pre>
								</td>
							</tr>
							<!-- 수정 -->
							<tr :id="'u'+vo.rno" class="ups" style="display: none;">
								<td colspan="2">
									<textarea rows="5" cols="90" style="float: left;" :id="'msg'+vo.rno">{{vo.msg}}</textarea>
									<input type="button" value="댓글수정" style="float: left;height: 106px" class="btn-danger" @click="replyUpdate(vo.rno)">
								</td>
							</tr>
							<!--  -->
						</table>
					</td>
				</tr>
			</table>
			<table class="table" v-show="sessionId!=''">
				<tr>
					<td class="text-left">
						<textarea rows="5" cols="90" style="float: left;" v-model="msg" ref="msg"></textarea>
						<input type="button" value="댓글쓰기" style="float: left;height: 106px" class="btn-danger" @click="replyInsert()">
					</td>
				</tr>
			</table>
		</div>
	</div>
	<script>
	 let detailApp=Vue.createApp({
		 data(){
			 return{
				 fno:${fno},
				 detail_data:{}
			 }
		 },
		 // $(function(){}) , componentDidMount() , window.onload=function(){}
		 mounted(){
			 axios.get('../food/detail_vue.do',{
				 params:{
					 fno:this.fno
				 }
			 }).then(response=>{
				 console.log(response.data)
				 this.detail_data=response.data
			 })
		 },
		 updated(){
			 
		 },
		 methods:{
			 
		 }
	 }).mount('#detailApp')
	 
	 let replyApp=Vue.createApp({
		 data(){
			 return{
				 fno:${fno},
				 sessionId:'${id}',
				 reply_list:[],
				 rno:0,
				 msg:'',
				 bCheck:true
			 }
		 },
		 mounted(){ // 자동 호출 함수
			 // 시작과 동시에 댓글을 읽어온다.
			 axios.get('../reply/list_vue.do',{
				params:{
					 fno:this.fno
				}
			 }).then(response=>{
				 this.reply_list=response.data
			 })
		 },
		 methods:{
			 // 수정하기
			 replyUpdateForm(rno){
				$('.ups').hide();
				$('.updates').attr("value", "수정")
				if(this.bCheck===true)
				{
					$('#u'+rno).show();
					$('#up'+rno).attr("value", "취소")
					this.bCheck=false
				}
				else
				{
					$('#u'+rno).hide();
					$('#up'+rno).attr("value", "수정")
					this.bCheck=true
				}
			 },
			 replyUpdate(rno){
				let msg=$('#msg'+rno).val();
				axios.get('../reply/update_vue.do',{
					params:{
						rno:rno,
						fno:this.fno,
						msg:msg
					}
				}).then(response=>{
					this.reply_list=response.data
					$('#u'+rno).hide()
					$('#up'+rno).attr("value", "수정")
				})
			 },
			 
			 // 삭제하기
			 replyDelete(rno){
				axios.get("../reply/delete_vue.do",{
					params:{
						rno:rno,
						fno:this.fno
					}
				}).then(response=>{
					this.reply_list=response.data
				})
			 },
			 
			 // 추가하기
			 replyInsert(){
				 if(this.msg=="")
				 {
					this.$refs.msg.focus()
					return
				 }
				 
				 axios.get('../reply/insert_vue.do',{
					 params:{
						 fno:this.fno,
						 msg:this.msg
					 }
				 }).then(response=>{
					 this.reply_list=response.data
					 this.msg=""
				 })
			 }
		 }
	 }).mount('#replyApp')
	</script>
</body>
</html>












