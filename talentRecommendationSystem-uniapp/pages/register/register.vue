<!-- 本示例未包含完整css，获取外链css请参考上文，在hello uni-app项目中查看 -->
<template>
        <view class="container">
            <form @submit="formSubmit"
			method="POST"
			v-model="people"
			@reset="formReset">
				<view class="header ">
							<view class="box">
								<label>姓名</label>
								<text class="color">*</text>
							</view>
							<view class="u-input">
								<input type="text" 
								v-model="people.name"
								placeholder="请输入姓名"
								name="name"
								maxlength="10" 
								placeholder-style="color: #B1B1B1" />
							</view>
						</view>
				<view class="header ">
							<view class="box">
								<label>年龄</label>
								<text class="color">*</text>
							</view>
							<view class="u-input">
								<input type="text" 
								placeholder="请输入年龄" 
								maxlength="18"
								name="age"
								v-model="people.age"
								placeholder-style="color: #B1B1B1" />
							</view>
						</view>

				<view class="header ">
							<view class="box">
								<label>手机号</label>
								<text class="color">*</text>
							</view>
							<view class="u-input">
								<input type="text" 
								placeholder="请输入手机号" 
								name="phone"
								v-model="people.phone"
								maxlength="11" placeholder-style="color: #B1B1B1" />
							</view>
						</view>

				<button class="box"
				@click="chooseImage"
				v-model="people.photo">上传一寸照片</button>
				<view class="header ">
							<view class="box">
								<label>工作年限</label>
								<text class="color">*</text>
							</view>
							<view class="u-input">
								<input type="text" 
								placeholder="请输入工作年限" 
								name="workTime"
								v-model="people.workTime"
								placeholder-style="color:#B1B1B1" />
							</view>
						</view>
				<view class="header ">
							<view class="box">
								<label>擅长技能</label>
								<text class="color">*</text>
							</view>
							<view class="u-input">
								<input type="text" 
								placeholder="请输入擅长技能" 
								name="skills"
								v-model="people.skills"
								placeholder-style="color: #B1B1B1" />
							</view>
						</view>
						<view class="header ">
									<view class="box">
										<label>获奖情况</label>
									</view>
									<view class="u-input">
										<input type="text" placeholder="请输入获奖情况" 
										name="rewards"
										v-model="people.rewards" 
										placeholder-style="color: #B1B1B1" />
									</view>
								</view>
						
				
                <view class="uni-btn-v">
                    <button form-type="submit">提交</button>
                    <button type="default" form-type="reset">重置</button>
                </view>
            </form>
        </view>

</template>
<script>
	import getBase64Data from "../../static/until/base64Util.js"

	
    export default {
		name:"register",
		components:{
			
		},
        data() {
            return {
				//表单数据
				people:{
					id:"",
					name:"",
					age:"",
					phone:"",
					photo:"",
					workTime:"",
					skills:"",
					rewards:""
				}
            }
        },
        methods: {
            formSubmit: function(e) {
				this.people.id = this.idMaker.idMaker();
				console.log(this.people);
                var data = JSON.stringify(e.detail.value)
				console.log(this.people);
				console.log("**************************");
                uni.request({
                	url:"http://localhost:8080/people/insertPeople",
					method:"POST",
					header:{'content-type':"application/json"},
					data:this.people,
					success:(response)=>{
						uni.navigateTo({
							url:"../index/index"
						})
					}
                })
            },
            formReset: function(e) {
                console.log('清空数据')
            },
			chooseImage: function(){
				var that = this;
					// 从本地相册选择图片或使用相机拍照。
					uni.chooseImage({
						count: 1, //最多可以选择的图片张数，默认9
						//album 从相册选图，camera 使用相机，默认二者都有。
						sourceType: ['album'],	
						success: function(res) {
							//获取图片信息。
							uni.getImageInfo({
								src: res.tempFilePaths[0],
								success: function(image) {
									that.src = (res.tempFilePaths[0]);
									//将本地资源上传到开发者服务器，客户端发起一个 POST 请求，其中 content-type 为 multipart/form-data
									uni.uploadFile({
										//开发者服务器 url
										url: 'http://localhost:8080/people/savePhoto',
										//获取要上传文件资源的路径。
										filePath: res.tempFilePaths[0],
										//文件对应的 key , 开发者在服务器端通过这个 key 可以获取到文件二进制内容
										name: 'photo',
										//HTTP 请求 Header, header 中不能设置 Referer。
										header: {
											token: uni.getStorageSync('token')
										},
										success: () => {
											that.people.photo = that.src.slice(11);
										},
										
										
									})
								}
							})	
						}
					})

			}
        }
    }
</script>

<style lang="scss" scoped>
	.header {
		width: 100%;
		height: 120rpx;
		padding: 0 32rpx;
		display: flex;
		flex-direction: row;

		.city {
			flex: 1;
			height: 100%;
			line-height: 120rpx;
			text-align: right;
			color: #B1B1B1;
			border-bottom: 1rpx solid #E4E4E4;
		}

		.row {
			position: relative;
			top: 50%;
			right: 0;
			transform: translateY(-50%);
			width: 24rpx;
			height: 24rpx;
			font-size: 0;

			image {
				width: 100%;
				height: 100%;
			}
		}

		.box {
			width: 180rpx;
			height: 100%;
			border-bottom: 1rpx solid #E4E4E4;
			line-height: 120rpx;

			label {
				font-size: 30rpx;
				font-weight: 400;
				color: #333333;
				line-height: 42px;
			}

			.color {
				color: #FC5C56;
			}
		}

		.u-input {
			border-bottom: 1rpx solid #E4E4E4;
			color: #333333;
			flex: 1;
			line-height: 120rpx;
			text-align: right;

			input {
				width: 100%;
				height: 100%;
			}

		}
	}

	.apply {
		width: 100%;
		margin-top: 49rpx;
		height: 90rpx;
		padding: 0 33rpx;
		text-align: center;

		.toapply {
			width: 100%;
			height: 100%;
			background-color: green;
			line-height: 90rpx;
			background-color: #337DFF;
			font-size: 36prx;
			border-radius: 45rpx;
			font-weight: 400;
			color: #FFFFFF;
		}
	}
</style>
