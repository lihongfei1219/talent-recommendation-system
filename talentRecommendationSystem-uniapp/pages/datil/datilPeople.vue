<template>
	<view>
			<uni-card
				:isShadow="true"
				v-model="people"
				:title="people.name"
				subTitle="副标题"
				mode="basic"
				:thumbnail="people.photo"
				extra="技术没有上限"
				:note="people.phone"
				@click="clickCard"
			>
				<view>
					<view style="height: 350rpx;">
						<image style="height: 100%;" mode="aspectFill" src="https://vkceyugu.cdn.bspapp.com/VKCEYUGU-dc-site/094a9dc0-50c0-11eb-b680-7980c8a877b8.jpg" />
					</view>
					<view class="skillElement">擅长技能：</view>
					<view class="content-box"><text style="font-size: 24px;">{{ people.skills }}</text></view>
				</view>
				
			</uni-card>
		</view>
</template>

<script>
	import uniCard from "../../components/uni-card/uni-card.vue"
	export default{
		components:{
			uniCard
		},
		data(){
			return {
				people:{
					id:null,
					name:null,
					age:null,
					phone:null,
					photo:null,
					workTime:null,
					successNum:null,
					talent:null,
					skills:null,
					rewards:null,
				}
			}
		},
		methods:{
			clickCard() {
						uni.makePhoneCall({
							phoneNumber:this.people.phone
						})
					},
					footerClick(types) {
						//
					}
			
		},
		onLoad:function(idJson){
			//传过来的值是id，依据id唯一性来查询,用正则表达式去进行一个提取
		var data = JSON.stringify(idJson)
		var reg = /{"(.*)":/;
		var id = reg.exec(data)[1].trim();
		//发送请求去根据id获取具体用户数据
		uni.request({
			url:"http://localhost:8080/people/getPeopleByID/"+id,
			data:id,
			success: (res) => {
				//获取到个人详情信息页
				this.people=res.data
				console.log(res.data);
			}
		})
		}
	}
</script>

<style>
	.footer-box {
		display: flex;
		justify-content: space-between;
		flex-direction: row;
	}
	 
	.footer-box__item {
		font-size: 12px;
		color: #666;
	}
	.skillElement{
		font-size: 36rpx;
	}
</style>
