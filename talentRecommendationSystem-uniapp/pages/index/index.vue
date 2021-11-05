<template>
	<view>
		<!-- 设置圆角 -->
		<uni-search-bar :radius="100" 
		class="search"
		
		:placeholder="searchData"
		clearButton="auto"
		@focus="getHotData"
		@confirm="search"></uni-search-bar>
		
		<uni-fab
		:pattern="pattern"
		:content="content"
		horizontal="left"
		vertical="bottom"
		direction="horizontal"
		:popMenu="true"
		@trigger="trigger"
		@fabClick="fabClick"
		></uni-fab>
				<uni-fab
				:pattern="button"
				horizontal="right"
				vertical="bottom"
				direction="horizontal"
				@fabClick="Toregister()"
				></uni-fab>
		<uni-list>
		    <uni-list-item 
			v-for="people,index in listData"
			:title="people.name"
			@click="ToDatil(people.id)"
			link="navigateTo"
			:note="people.skills" 
			:thumb="people.photo"

		    thumb-size="lg" 
			:rightText="people.successNum"></uni-list-item>
			 
		    
		</uni-list>
	</view>
</template>

<script>
	import uniSearchBar from "../../components/uni-search-bar/uni-search-bar.vue"
	import uniFab from "../../components/uni-fab/uni-fab.vue"
	export default {
		components:{
			uniSearchBar,uniFab
		},
		data() {
			return {
				listData: [],
				searchData:"",
				pattern: {
								color: 'gray',
								backgroundColor: '#FFFFFF',
								selectedColor: '#007AFF',
							},
							content: [
								{
									iconPath: '/static/images/talent.png',
									selectedIconPath: '/static/images/talent.png',
									text: '综合能力',
									active: false
								},
								{
									iconPath: '/static/images/workTime.png',
									selectedIconPath: '/static/images/workTime.png',
									text: '工作年限',
									active: false
								},
								{
									iconPath: '/static/images/successNum.png',
									selectedIconPath: '/static/images/successNum.png',
									text: '科技成果',
									active: false
								}
							]
						}
		},
		onLoad: function() {
			var that = this
			//获取全部人才
			uni.request({
				url:"http://localhost:8080/people/getAllPeople",
				method:"GET",
				dataType:"json",
				success: (response) => {
					var JsonObjectData = response.data; //有规律的json数组可以直接.属性
					that.listData = JsonObjectData;
				},
				fail: (e) => {
					console.log(e.code);
				}
			})
		},
		methods: {
			getHotData:function(){
				//获取今日热搜数据
				var that = this
				uni.request({
					url:"http://localhost:8080/people/getHostData",
					method:"GET",
					dataType:"json",
					success:function(response){
						if(response.data[0].data!=undefined){
							that.searchData = response.data[0].data; //获取目前热搜数据
						}else{
							that.searchData = "暂无热搜，快来搜索吧！" 
						}
						console.log(response.data);
					},
					fail: (e) => {
						console.log(e.code);
					}
				})
			},
			//点击搜索
			search:function(e) {
				const search = e.value
				var that = this
				uni.request({
					url:"http://localhost:8080/people/getPeopleBySearch",
					data:{
						
						"data":search
					},
					method:"GET",
					dataType:"json",
					success:function(response){
						var JsonObjectData = response.data; //有规律的json数组可以直接.属性
						that.listData = JsonObjectData;
					},
					fail: (e) => {
						console.log(e.code);
					}
				})
			},
			ToDatil:function(people){
				//跳转到个人详细信息页面
				uni.navigateTo({
					url:"../datil/datilPeople?"+people,
					
				})
			},
			Toregister:function(){
				console.log("跳转注册页面");
				uni.navigateTo({
					url:"../register/register",
					success: (response) => {
						console.log("成功跳转");
					}
				})
			},
			trigger:function(e) {
				const select = e.item.text
				
				if(select=="工作年限"){
					//按照工作年限排名
					console.log("工作年限");
					var list = this.listData.sort(this.sortUtil.compare("workTime"))
					
				}else if(select=="科技成果"){
					//按照科技成果排名
					console.log("科技成果");
					var list = this.listData.sort(this.sortUtil.compare("successNum"))
				}else{
					//默认综合能力排名
					console.log("综合能力");
					var list = this.listData.sort(this.sortUtil.compare("talent"))
				}

			},
			fabClick:function() {
				uni.showToast({
					title: '请选择优先匹配',
					icon: 'none'
				});
			},
		}
	}
</script>

<style>
	.search{
		display: inline;
	}
	.content {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}
	.picker-view {
	        width: 300rpx;
	        height: 300rpx;
			display: flex;
			
	    }
	    .item {
	        height: 50px;
	        align-items: center;
	        justify-content: center;
	        text-align: center;
	    }
</style>
