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
</style>
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script src="card.js"></script>
<script src="page.js"></script>
</head>
<body>
<div class="container">
	<div class="row">
		<food_card v-bind:food_list="food_list"></food_card>
	</div>
	<div style="height: 20px"></div>
	<div class="row">
		<div class="text-center">
			<pagination v-bind:page_list="page_list"></pagination>
		</div>
	</div>
	<div style="height: 10px"></div>
</div>
	<script>
	 
	 let app=Vue.createApp({
		 data(){
			 return{
				 food_list:[],
				 curpage:1,
				 totalpage:0,
				 startPage:0,
				 endPage:0,
				 page_list:{}
			 }
		 },
		 mounted(){
			 this.dataRecv()
		 },
		 components:{
			 "food_card":foodCard,
			 "pagination":pagination
		 },
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
					
				axios.get('../food/page_vue.do',{
					params:{
						page:this.curpage
					}
				}).then(response=>{
					console.log(response.data)
					this.page_list=response.data
					this.curpage=response.data.curpage
					this.totalpage=response.data.totalpage
					this.startPage=response.data.startPage
					this.endPage=response.data.endPage
				})
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
	 }).mount('.container')
	</script>
</body>
</html>
















