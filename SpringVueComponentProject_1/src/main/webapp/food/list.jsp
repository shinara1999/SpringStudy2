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
	width: 960px;
}
a.link:hover{
	cursor: pointer;
}
</style>
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
	<div class="container" id="foodListApp">
		<div class="row">
			<div class="col-md-3" v-for="vo in food_list">
			    <div class="thumbnail">
			      <a href="#">
			        <img :src="'https://www.menupan.com'+vo.poster" style="width:100%">
			        <div class="caption">
			          <p style="font-size: 9px">{{vo.name}}</p>
			        </div>
			      </a>
			    </div>
			</div>
		</div>
	<div style="height: 20px"></div>
		<div class="row">
			<div class="text-center">
				<ul class="pagination">
					<li v-if="startPage>1"><a class="link" @click="prev()">&laquo;</a></li>
					<li v-for="i in range(startPage, endPage)" :class="i===curpage?'active':''"><a class="link" @click="pageChange(i)">{{i}}</a></li>
					<li v-if="endPage<totalpage"><a class="link" @click="next()">&raquo;</a></li>
				</ul>
			</div>
		</div>
	</div>
	<script>
	 let foodApp=Vue.createApp({
		 // 변수 설정
		 data (){ 
			 return{
				 food_list:[],
				 curpage:1,
				 totalpage:0,
				 startPage:0,
				 endPage:0
			 }
		 },
		 mounted(){
			 // 콜백 => 시작과 동시에 호출되는 함수 ==> window.onload => Vue에 의한 자동 호출
			 this.dataRecv()
		 },
		 updated(){
			 // 자동 호출되는 함수 => data에 등록된 값이 변경될 때 호출되는 함수 
		 },
		 // 사용자정의 함수 : 이벤트 처리, 버튼 클릭, 이미지 처리
		 methods:{
			// 중복 코딩이 있는 경우
			dataRecv(){
				// 서버와 연결시킨다. (스프링) => axios 사용
				axios.get('../food/list_vue.do',{
					params:{
						page:this.curpage
						
					}
				}).then(response=>{
					console.log(response.data)
					this.food_list=response.data
				})
				
				// 페이지 읽기
				axios.get('../food/page_vue.do',{
					params:{
						page:this.curpage
					}
				}).then(response=>{
					console.log(response.data)
					this.curpage=response.data.curpage
					this.totalpage=response.data.totalpage
					this.startPage=response.data.startPage
					this.endPage=response.data.endPage
				})
			},
			range(start, end){
				let arr=[]
				let leng=end-start
				for(let i=0;i<=leng;i++)
				{
					arr[i]=start
					start++;
				}
				return arr;
			}
			,
			prev(){
				this.curpage=this.startPage-1
				this.dataRecv()
			},
			next(){
				this.curpage=this.endPage+1
				this.dataRecv()
			},
			pageChange(page){
				this.curpage=page
				this.dataRecv()
			}
		 },
		 // 반복적인 기능이 나올 때 처리되는 함수
		 components:{
			 
		 }
		 // computed, watch, filter
	 }).mount('#foodListApp')
	</script>
</body>
</html>














