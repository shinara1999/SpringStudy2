<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<style type="text/css">
a.link:hover, img.img_click:hover{
	cursor: pointer;
}
</style>
</head>
<body>
<div class="wrapper row3" id="foodApp">
  <main class="container clear"> 
    <!-- main body --> 
    <h2 class="sectiontitle">맛집 목록</h2>
    <div class="content" > 

      <div id="gallery">
        <figure>
          <header class="heading"></header>
          <ul class="nospace clear">
            <li v-for="(vo, index) in food_list" :class="index%4==0?'one_quarter first':'one_quarter'"><a :href="'../food/food_before_list_detail.do?fno='+vo.fno"><img :src="'https://www.menupan.com'+vo.poster" :title="vo.name"></a></li>
           
          </ul>
          <figcaption></figcaption>
        </figure>
      </div>
      
      <nav class="pagination">
        <ul>
          <li v-if="startPage>1"><a @click="prev()" class="link">&laquo; Previous</a></li>
          <li v-for="i in range(startPage, endPage)" :class="i==curpage?'current':''"><a @click="pageChange(i)" class="link">{{i}}</a></li>
          <li v-if="endPage<totalpage"><a @click="next()" class="link">Next &raquo;</a></li>
        </ul>
      </nav>
      
    </div>
   
    <!-- / main body -->
    <div class="clear"></div>
    
    <div>
    	<h3>최근 방문 맛집</h3>
    	<hr>
    	<span v-for="vo in cookie_list">
    	  <a :href="'../food/food_list_detail.do?fno='+vo.fno">
    		<img :src="'http://www.menupan.com'+vo.poster" style="width: 100px;height: 100px;margin-left: 5px">
    	  </a>
    	</span>
    </div>
  </main>
</div>
<script>
 let foodApp=Vue.createApp({
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
	 updated(){
		 
	 },
	 methods:{
		 // 공통 모듈 => 반복제거
		 dataRecv(){
			axios.get('../food/food_list_vue.do',{
				params:{
					page:this.curpage
				}
			}).then(response=>{
				console.log(response.data)
				this.food_list=response.data
			})
			
			// 페이지
			axios.get('../food/food_page_vue.do',{
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
			
			// cookie
			axios.get('../food/food_cookie_vue.do').then(response=>{
				console.log(response.data)
				this.cookie_list=response.data
			})
		 },
		 range(start, end){
			 let arr=[]
			 let lang=end-start
			 for(let i=0;i<=lang;i++)
			 {
				 arr[i]=start
				 start++
			 }
			 return arr
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
			 this.curpage=page;
			 this.dataRecv()
		 }
	 }
 }).mount('#foodApp')
</script>
</body>
</html>