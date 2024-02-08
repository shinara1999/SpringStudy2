const pagination={
			 props:['page_list'],
			 template:`<ul class="pagination">
						<li v-if="page_list.startPage>1"><a class="link" @click="prev()">&laquo</a></li>
						<li v-for="i in range(page_list.startPage, page_list.endPage)" :class="i===page_list.curpage?'active':''"><a class="link" @click="pageChange(i)">{{i}}</a></li>
						<li v-if="page_list.endPage<page_list.totalpage"><a class="link" @click="next()">&raquo</a></li>
					   </ul>`,
			 methods:{
				 range(start, end){
					 let arr=[]
				 	 let leng=end-start
				 	 
				 	 for(let i=0;i<=leng;i++)
				 	 {
				 		 arr[i]=start
				 		 start++;
				 	 }
				 	 return arr
				 },
				 prev(){
					 this.$parent.prev()
				 },
				 next(){
					 this.$parent.next()
				 },
				 pageChange(page){
					this.$parent.pageChange(page) 
				 }
			 }
	 }