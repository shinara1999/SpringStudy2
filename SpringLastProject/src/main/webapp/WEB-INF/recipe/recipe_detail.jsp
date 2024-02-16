<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
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
<div class="wrapper row3" id="recipeApp">
  <main class="container clear"> 
    <!-- main body --> 
    <h2 class="sectiontitle">레시피 상세보기</h2>
    	<table class="table">
    		<tr>
    			<td colspan="3" class="text-center">
    				<img :src="recipe_data.poster" style="width: auto;height: 450px;border-radius: 30px">
    			</td>
    		</tr>
    		<tr>
    			<td colspan="3" class="text-center">
    				<h3 style="color: yellowgreen;font-weight: bold;">{{recipe_data.title}}</h3>
    			</td>
    		</tr>
    		<tr>
    			<td colspan="3" class="text-center">
    				<pre style="white-space: pre-wrap;background-color: white;border: none">{{recipe_data.content}}</pre>
    			</td>
    		</tr>
    		<tr>
    			<td class="text-center"><img src="../images/icon/a1.png"></td>
    			<td class="text-center"><img src="../images/icon/a2.png"></td>
    			<td class="text-center"><img src="../images/icon/a3.png"></td>
    		</tr>	
    		<tr>
    			<td class="text-center">{{recipe_data.info1}}</td>
    			<td class="text-center">{{recipe_data.info2}}</td>
    			<td class="text-center">{{recipe_data.info3}}</td>
    		</tr>
    		<tr>
    			<td colspan="3" class="text-center">
    			  <div style="font-size: 20px;margin: 2%">★ 재료 ★</div>
    				<div v-for="s in stuff"><a class="link" @click="find(s)">{{s}}</a></div>
    				<div style="margin-bottom: 3%"></div>
    			</td>
    		</tr>
    		<tr>
    			<td colspan="3" class="text-center">
    				<div style="font-size: 20px;margin: 2%">★ 조리순서 ★</div>
    			</td>
    		</tr>
    		<tr>
    		 <td colspan="3">
    		 	<table class="table" v-for="(m, index) in make">
    		 		<tr>
    		 			<td width="70%">{{m}}</td>
    		 			<td width="30%">
    		 			 <img :src="posterPrint(index)" style="border-radius: 30px;width: 300px;height: 180px;margin: 0px auto">
    		 			</td>
    		 		</tr>
    		 	</table>
    		 </td>
    		</tr>
    		<tr>
    			<td colspan="3">
    				<table class="table">
    					<caption><h4>레시피 작성자</h4></caption>
    					<tr>
    						<td width="20%" class="text-center" rowspan="2">
    							<img :src="recipe_data.chef_poster" style="width: 150px;height: 150px" class="img-circle">
    						</td>
    						<td width="80%"><b>{{recipe_data.chef}}</b></td>
    					</tr>
    					<tr>
    						<td width="80%">{{recipe_data.chef_profile}}</td>
    					</tr>
    				</table>
    			</td>
    		</tr>
    	</table>
  </main>
  <div id="dialog" title="레시피 관련 상품" v-show="isShow">
  	<goods-data v-bind:goods="goods"></goods-data>
  </div>
</div>
<script>
 // goods component
 const goods={
		 props:['goods'],
		 template:`<div class="container">
 				   	<div class="row">
	 				   <div class="col-md-4" v-for="g in goods">
	 				    <div class="thumbnail">
	 				        <img :src="g.goods_poster" :title="g.goods_name" style="width:100%">
	 				        <div class="caption">
	 				          <p>{{g.goods_price}}</p>
	 				        </div>
	 				    </div>
	 				  </div>
 				   	</div>
 				   </div>`
 }
 let recipeApp=Vue.createApp({
	 data(){
		 return{
			 recipe_data:{},
			 no:${no},
			 stuff:[],
			 make:[],
			 poster:[],
			 goods:[],
			 isShow:false
		 }
	 },
	 mounted(){
		axios.get('../recipe/recipe_detail_vue.do',{
			params:{
				no:this.no
			}
		}).then(response=>{
			console.log(response.data)
			this.recipe_data=response.data
			this.stuff=response.data.stuff.split(",")
			let makedata=response.data.foodmake.split("\n")
		    let p=[]
			let m=[]
			for(let i=0;i<makedata.length-1;i++)
			{
				let aa=makedata[i].split("^")
				this.make[i]=aa[0]
				this.poster[i]=aa[1]
			}
			console.log(this.make)
			console.log(this.poster)
		})
	 },
	 methods:{
		 posterPrint(i){
			 return this.poster[i]
		 },
		 find(s){
			 let ss=s.substring(0, s.indexOf(' '))
			 // alert(ss.trim())
			 axios.get('../recipe/goods_vue.do',{
				 params:{
					 stuff:ss
				 }
			 }).then(response=>{
				 console.log(response.data)
				 this.goods=response.data
				 
				 this.isShow=true
				 $('#dialog').dialog({
					 autoOpen:false,
					 modal:true,
					 width:700,
					 height:500
				 }).dialog('open')
			 })
		 }
	 },
	 components:{
		 'goods-data':goods
	 }
 }).mount('#recipeApp')
</script>
</body>
</html>














    				