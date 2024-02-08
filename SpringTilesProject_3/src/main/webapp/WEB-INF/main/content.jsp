<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
	<div class="container" id="foodlistApp">
		<div class="row">
		 <div class="col-md-3" v-for="vo in food_list">
		    <div class="thumbnail">
		      <a href="#">
		        <img :src="'https://www.menupan.com'+vo.poster"style="width:100%">
		        <div class="caption">
		          <p style="font-size: 9px">{{vo.name}}</p>
		        </div>
		      </a>
		    </div>
		  </div>
		</div>
		<div style="height: 10px"></div>
		<div class="row">
			<div class="text-center">
				<ul class="pagination">
				  <li v-if="startPage>1"><a class="alink" @click="prev(i)">&laquo;</a></li>
				  <li v-for="i in range(startPage, endPage)" :class="i===curpage?'active':''"><a class="alink" @click="pageChange(i)">{{i}}</a></li>
				  <li v-if="endPage<totalpage"><a class="alink" @click="next(i)">&raquo;</a></li>
				</ul>	
			</div>
		</div>
	</div>
	<script>
	 let foodlistApp=Vue.createApp({
		 data(){
			 return{
				 food_list:[],
				 curpage:1,
				 totalpage:0,
				 startPage:0,
				 endPage:0
			 }
		 },
		 mounted(){
			 this.dataRecv()
		 },
		 // 이벤트 처리
		 methods:{
			 dataRecv(){
				 axios.get('../food/list_vue.do',{
					 params:{
						 page:this.curpage
					 }
				 }).then(response=>{
					 console.log(response.data)
					 this.food_list=response.data
				 })
				 
				 // 페이지 읽기
				 /*
				 	axios.get => @GetMapping
				 	axios.post => @PostMapping
				 	
				 	=> ajax({
				 		type:'get', 'post'
				 	})
				 	=> <form method="get, post">
				 */
				 axios.get('../food/page_vue.do',{
					 params:{
						 page:this.curpage
					 }
				 }).then(response=>{
					 this.curpage=response.data.curpage
					 this.totalpage=response.data.totalpage
					 this.startPage=response.data.startPage
					 this.endPage=response.data.endPage
				 })
			 },
			 // 블록별 페이지 출력 => v-for="받는 변수 in 배열"
			 range(start, end){
					let arr=[];
					let len=end-start
					for(let i=0;i<=len;i++)
					{
						arr[i]=start;
						start++;
					}
					return arr;
				},
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
		 }
	 }).mount('#foodlistApp')
	 /*foodlistApp.component(
		templete:<div>
	 				<img/>
	 			 </div>`		 
	 )
	 foodlistApp.mount('')*/
	</script>
</body>
</html>














