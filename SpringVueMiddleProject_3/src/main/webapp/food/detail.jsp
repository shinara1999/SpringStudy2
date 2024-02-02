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
.row1{
	width: 600px;
}
</style>
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<!-- <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=36ffb60501517948747514f7e2afe780&libraries=services"></script> -->
</head>
<body>
  <jsp:include page="${login_jsp }"></jsp:include>
	<!-- fno, score, poster, name, tyep, address, phone, theme, price, time, seat, score => 포스터 제외 8개 : rowspan="8" -->
	<div class="container" id="app1">
		<div class="row">
			<table class="table">
				<tr>
					<td width="30%" class="text-center" rowspan="8">
						<img :src="'https://www.menupan.com/'+vo.poster" style="width:100%">
					</td>
					<td colspan="2">
						<h3><span id="name">{{vo.name}}</span>&nbsp;<span style="color: orange;">{{vo.score}}</span></h3>
					</td>
				</tr>
				<tr>
					<td class="text-center" width="10%">음식종류</td>
					<td width="60%">{{vo.type}}</td>
				</tr>
				<tr>
					<td class="text-center" width="10%">주소</td>
					<td width="60%">{{vo.address}}</td>
				</tr>
				<tr>
					<td class="text-center" width="10%">전화</td>
					<td width="60%">{{vo.phone}}</td>
				</tr>
				<tr>
					<td class="text-center" width="10%">테마</td>
					<td width="60%">
						<ul>
							<li v-for="t in theme">{{t}}</li>
						</ul>
					</td>
				</tr>
				<tr>
					<td class="text-center" width="10%">가격대</td>
					<td width="60%">{{vo.price}}</td>
				</tr>
				<tr>
					<td class="text-center" width="10%">영업시간</td>
					<td width="60%">{{vo.time}}</td>
				</tr>
				<tr>
					<td class="text-center" width="10%">좌석</td>
					<td width="60%">{{vo.seat}}</td>
				</tr>
				<tr>
					<td colspan="3" class="text-center">
						<input type=button value="목록" class="btn btn-xs btn-primary" onclick="javascript:history.back()">
					</td>
				</tr>
			</table>
		</div>
		<div style="height: 20px"></div>
		<div class="row">
			<div id="map" style="width:100%;height:350px;"></div>
		</div>
	</div>
	<div class="container" id="app2">
		<div class="row row1">
			
		</div>
	</div>
	<script>
	 let app=Vue.createApp({
		 data(){
			 return{
				 vo:{},
				 fno:${fno},
				 address:'',
				 name:'',
				 theme:[],
				 sessionId:''
			 }
		 },
		 mounted(){
			 axios.get('../food/detail_vue.do',{
				 params:{
					 fno:this.fno
				 }
			 }).then(response=>{
				 console.log(response.data)
				 this.vo=response.data
				 this.theme=response.data.theme.split(",")
				 this.address=response.data.address
				 this.name=response.data.name
				 if(window.kakao && window.kakao.maps)
				 {
					 this.initMap()
				 }
				 else
				 {
					 this.addScript()
				 }
			 })
		 },
		 updated(){
			 
		 },
		 methods:{
			 addScript(){ // 라이브러리 추가
				 const script=document.createElement("script") // <script>
				 /* global kakao */
				 script.onload=()=>kakao.maps.load(this.initMap)
				 script.src="http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=4d9d9ca62321ffd7cb4e3c7f71e6a9dc&libraries=services"
				 document.head.appendChild(script)
			 },
			 initMap(){ // 지도 출력
				 var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
				    mapOption = {
				        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
				        level: 3 // 지도의 확대 레벨
				    };  

				// 지도를 생성합니다    
				var map = new kakao.maps.Map(mapContainer, mapOption); 

				// 주소-좌표 변환 객체를 생성합니다
				var geocoder = new kakao.maps.services.Geocoder();

				// 주소로 좌표를 검색합니다
				geocoder.addressSearch(this.address, function(result, status) {

				    // 정상적으로 검색이 완료됐으면 
				     if (status === kakao.maps.services.Status.OK) {

				        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

				        // 결과값으로 받은 위치를 마커로 표시합니다
				        var marker = new kakao.maps.Marker({
				            map: map,
				            position: coords
				        });

				        // 인포윈도우로 장소에 대한 설명을 표시합니다
				        var infowindow = new kakao.maps.InfoWindow({
				            content: '<div style="width:150px;text-align:center;padding:6px 0;">'+$('#name').text()+'</div>'
				        });
				        infowindow.open(map, marker);

				        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
				        map.setCenter(coords);
				    } 
				});    
			 }
		 }
	 }).mount('#app1')
	 // 댓글
	 let app2=createApp({
		 
	 }).mount("#app2")
	</script>
</body>
</html>














