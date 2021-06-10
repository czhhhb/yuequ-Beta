<template>
	<view class="content">
		<view class="homepage-image">
			<image src="../../static/1.jpg" mode=""></image>
		</view>
		
<!-- 		<view class="mask" v-if="mask_isShow">
			<view class="window" @click.stop="">
				<image src="../../static/login.png" mode=""></image>
				<button type="default" @click="auth">登录授权</button>
			</view>
		</view> -->
		
		<view class="homepage-middle">
			<button type="default" @tap="go('/pages/punchClock/index')">去打卡</button>
			<view class="homepage-middle-bottom">
				<view @tap="go('/pages/countDown/countDown')">
					<icon class="icon-daojishi iconfont" style="color: #77b6f3;"></icon>
					<view class="count_txt">倒计时</view>
				</view>
				<view @tap="go('/pages/rankingList/index')">
					<icon class="icon-paihangbang iconfont" style="color: #0e95da;"></icon>
					<view class="rank">排行榜</view>
				</view>
				<view @tap="go('/pages/learningRecords/index')">
					<icon class="icon-jilu iconfont" style="color: #77b6f3;"></icon>
					<view class="count_txt">学习记录</view>
				</view>
				<view @tap="go('/pages/tomatoTimer/tomatoTimer')">
					<icon class="icon-fanqie iconfont" style="color: #0e95da;"></icon>
					<view class="rank">番茄钟</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import uniIcons from '../../components/uni-icons/uni-icons.vue'
	// const appId="wxebf0773a06f773bb"   //开发者appid
	// const secret="8d1bd721af7c5fde1ba47544c5c62e7b" 
	export default {
		components: {
			uniIcons
		},
		data() {
			return {
				title: 'Hello',
				// mask_isShow:true,
				// UserHead:'../../static/3.jpeg',
				// openid:"",
				// isCeng:true,
				// userinfo:{},
				// UserName:"未登录",
				// imgurl:"",
			}
		},
		onLoad() {

		},
		methods: {
			go(params) {
				console.log(params)
				uni.navigateTo({
					url: params
				})
			},
			// auth(){  
			// 	let _this=this;
			// 	wx.getUserProfile({
			// 		desc: '用于完善会员资料', //不能删掉
			// 		success: (res) => {
			// 		_this.userInfo = res.userInfo,
			// 		_this.UserHead = _this.userInfo.avatarUrl,
			// 		_this.UserName = _this.userInfo.nickName,
			// 		_this.imgurl = _this.userInfo.avatarUrl,
			// 		_this.mask_isShow = false
			// 		console.log(_this.userInfo),
			// 		wx.setStorage({
			// 			key:'UserHead',
			// 			data:_this.UserHead
			// 		}),
			// 		wx.setStorage({
			// 			key:'UserName',
			// 			data:_this.UserName
			// 		})
			// 		wx.login({//发送状态码
			// 		  success(res) {
			// 		  	  	if (res.code) {
			// 			      // 发起网络请求
			// 				  console.log(res.code)
			// 			      wx.request({
			// 			        url: 'https://pumpkin2.zzxcloud.cn/user/testlogin', //仅为示例，并非真实的接口地址
			// 			        data: {
			// 						appID:appId,  //开发者appid
			// 						appSecret:secret, //开发者AppSecret(小程序密钥)	
			// 			      		code:res.code,
			// 			        },
			// 			        header: {
			// 			      	'content-type': 'application/json' // 默认值
			// 			      	 //'Content-Type': 'application/x-www-form-urlencoded'
			// 			        },
			// 			        method: 'POST',
			// 			        success(res) {
			// 			      	// _this.list = res.data
			// 					console.log(res.data)
			// 					_this.openid = res.data.openid
			// 					wx.setStorage({
			// 						key:'openid',
			// 						data:_this.openid,
			// 					})
			// 					wx.request({
			// 					  url: 'https://pumpkin2.zzxcloud.cn/user/login', //仅为示例，并非真实的接口地址
			// 					  data: {
			// 							openid:_this.openid,
			// 							UserName:_this.UserName,
			// 							USER_headimage:_this.UserHead
			// 					  },
			// 					  header: {
			// 						'content-type': 'application/json' // 默认值
			// 						// 'Content-Type': 'application/x-www-form-urlencoded'
			// 					  },
			// 					  method: 'POST',
			// 					  success(res) {
								
			// 						console.log('----successed----')
									
			// 					  },
			// 					  fail(res){
			// 						console.log('----fail----')
			// 					  }
			// 					})
			// 			      	console.log('----successed----')
			// 			        },
			// 			        fail(res){
			// 			      	console.log('----fail----')
			// 			        }
			// 			      })
			// 			    } else {
			// 			      console.log('登录失败！' + res.errMsg)
			// 			    }
			// 		  }
			// 		})
			// 		}
			// 	})
				
			// },
		}
	}
</script>

<style lang="scss">
	@font-face {
		font-family: "iconfont";
		src: url('../../fonts/iconfont.ttf?t=1620315771143') format('truetype');
	}

	.iconfont {
		margin-bottom: 35rpx;
		font-family: "iconfont" !important;
		font-size: 160rpx;
		font-style: normal;
		-webkit-font-smoothing: antialiased;
		-moz-osx-font-smoothing: grayscale;
	}

	.icon-fanqie:before {
		content: "\e602";
	}

	.icon-daojishi:before {
		content: "\e6af";
	}

	.icon-paihangbang:before {
		content: "\e603";
	}


	.icon-jilu:before {
		content: "\e644";
	}


	.content {
		background-color: #F3F4F3;
	}

	.homepage-image {
		width: 90%;
		height: 390rpx;
		padding: 50rpx 0 70rpx;
		margin: 0 auto;

		image {
			width: 100%;
			height: 100%;
			display: block;
			border-radius: 20rpx;
		}
	}

	.homepage-middle {
		width: 90%;
		margin: 0 auto;
		border-radius: 20rpx;
		border: 2rpx solid #cbcccc;
		background: #fff;
		padding: 100rpx 10rpx;

		button {
			border-radius: 60rpx;
			background: #77b7f4;
			color: #fff;
			width: 95%;
			font-size: 48rpx;
		}

		.homepage-middle-bottom {
			display: flex;
			text-align: center;
			margin: 80rpx 0 20rpx 0;

			view {
				flex: 1
			}
		}
	}

	.count_txt {
		margin-top: -13px;
	}

	.rank {
		margin-top: -13px;
	}

	.uni-icons {
		margin-top: -20px;
		font-size: 56rpx !important;
	}
	// .mask {
	// 	width: 100%;
	// 	height: 50%;
	// 	position: fixed;
	// 	left: 0;
	// 	bottom: 0;
	// 	// display: flex;
	// 	// background-color: #007AFF;
	// 	align-items: center;
	// 	justify-content: center;
	// }
	
	// .window {
	// 	height: 100%;
	// 	width: 80%;
	// 	margin: 0 auto;
	// 	margin-top: -50px;
	// 	// background-color: #555555;
	// 	button{
	// 		height: 45px;
	// 		background-color: #4CD964;
	// 		color: #FFFFFF;
	// 		border-radius: 10px;
	// 	}
	// }
	
</style>
