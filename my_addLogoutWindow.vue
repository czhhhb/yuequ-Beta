<template>
	<view class="content">
		<view class="topview">
			<view class="headImg">
				<image :src="UserHead" mode=""></image>
			</view>
			<view class="nick" @click="auth" v-if="!openid">
				点此登录
			</view>
			<view class="nick" v-else="!openid">
				{{ UserName }}
			</view>
		</view>
		<view class="line"></view>
		<view class="record" @click="go('/pages/learningRecords/index')">
			<view class="txt">
				学习记录
			</view>
		</view>
		<view class="line"></view>
		<view class="record" @click="go('/pages/rankingList/index')">
			<view class="txt">
				排行榜
			</view>
		</view>
		<view class="line"></view>
		<view class="record" @click="go('/pages/countDown/countDown')">
			<view class="txt">
				倒计时
			</view>
		</view>
		<view class="line"></view>
		<view class="record" @click="go('/pages/suggest/suggest')">
			<view class="txt">
				建议反馈
			</view>
		</view>
		<view class="line"></view>
		<view class="auth" v-if="auth_button">
			<button type="default" @click="auth">登录授权</button>
		</view>
		<view class="logout" v-else="auth_button">
			<button type="default" @click="logout">退出</button>
		</view>
		
	</view>
	</view>
</template>

<script>
	import uniIcons from '../../components/uni-icons/uni-icons.vue'
	const appId="wxebf0773a06f773bb"   //开发者appid
	const secret="8d1bd721af7c5fde1ba47544c5c62e7b" 
	export default {
		onShow() {
			let _this = this
			wx.getStorage({ //获取本地缓存
				key: "UserHead",
				success: function(res) {
					_this.UserHead = res.data
				}
			})
			wx.getStorage({ //获取本地缓存
				key: "UserName",
				success: function(res) {
					_this.UserName = res.data
				}
			})
			wx.getStorage({ //获取本地缓存
				key: "openid",
				success: function(res) {
					_this.openid = res.data
					_this.auth_button = false
				}
			})
		},
		data() {
			return {
				UserHead: '../../static/3.jpeg',
				UserName: "未登录",
				openid:"",
				// isCeng:true,
				userinfo:{},
				auth_button:true
			}
		},
		methods: {
			go(params) {
				console.log(params)
				uni.navigateTo({
					url: params
				})
			},
			auth(){
				let _this=this;
				wx.getUserProfile({
					desc: '用于完善会员资料', //不能删掉
					success: (res) => {
					_this.userInfo = res.userInfo,
					_this.UserHead = _this.userInfo.avatarUrl,
					_this.UserName = _this.userInfo.nickName,
					_this.auth_button = false
					console.log(_this.userInfo),
					wx.setStorage({
						key:'UserHead',
						data:_this.UserHead
					}),
					wx.setStorage({
						key:'UserName',
						data:_this.UserName
					})
					wx.login({//发送状态码
					  success(res) {
					  	  	if (res.code) {
						      // 发起网络请求
							  console.log(res.code)
						      wx.request({
						        url: 'https://pumpkin2.zzxcloud.cn/user/testlogin', //仅为示例，并非真实的接口地址
						        data: {
									appID:appId,  //开发者appid
									appSecret:secret, //开发者AppSecret(小程序密钥)	
						      		code:res.code,
						        },
						        header: {
						      	'content-type': 'application/json' // 默认值
						      	 //'Content-Type': 'application/x-www-form-urlencoded'
						        },
						        method: 'POST',
						        success(res) {
						      	// _this.list = res.data
								console.log(res.data)
								_this.openid = res.data.openid
								wx.setStorage({
									key:'openid',
									data:_this.openid,
								})
								wx.request({
								  url: 'https://pumpkin2.zzxcloud.cn/user/login', //仅为示例，并非真实的接口地址
								  data: {
										openid:_this.openid,
										UserName:_this.UserName,
										USER_headimage:_this.UserHead
								  },
								  header: {
									'content-type': 'application/json' // 默认值
									// 'Content-Type': 'application/x-www-form-urlencoded'
								  },
								  method: 'POST',
								  success(res) {
									console.log('----successed----')
								  },
								  fail(res){
									console.log('----fail----')
								  }
								})
						      	console.log('----successed----')
						        },
						        fail(res){
						      	console.log('----fail----')
						        }
						      })
						    } else {
						      console.log('登录失败！' + res.errMsg)
						    }
					  }
					})
					}
				})
			},
			logout:function(){
				let _this=this;
				wx.showModal({
					title: '提示',
					content: '确认退出登录吗？',
					success: function(res) {
						if (res.confirm) { //这里是点击了确定以后
							console.log('用户点击确定')
							wx.removeStorage({
							  key: 'openid',
							  success (res) {
							    console.log(res)
							  }
							})
							wx.removeStorage({
							  key: 'UserName',
							  success (res) {
							    console.log(res)
							  }
							})
							wx.removeStorage({
							  key: 'UserHead',
							  success (res) {
							    console.log(res)
							  }
							})
							_this.UserName = "未登录"
							_this.UserHead = '../../static/3.jpeg'
							_this.auth_button = true
						} else { //这里是点击了取消以后
							console.log('用户点击取消')
							// wx.navigateBack({})
						}
					}
				})
				
			}
		}
	}
</script>

<style lang="scss">
	@font-face {
		font-family: "iconfont";
		src: url('../../fonts/iconfont.ttf?t=1620315771143') format('truetype');
	}

	.content {
		// background-color: #F3F4F3;
	}

	.icon-jilu:before {
		content: "\e6e9";
	}

	.iconfont {
		margin-bottom: 35rpx;
		font-family: "iconfont" !important;
		font-size: 160rpx;
		font-style: normal;
		-webkit-font-smoothing: antialiased;
		-moz-osx-font-smoothing: grayscale;
	}

	.uni-icons {
		font-size: 56rpx !important;
	}

	.line {
		background-color: #808080;
		height: 1rpx;
		width: 100%;
		align-items: center;
	}

	.topview {
		display: flex;
		height: 150px;
		background-color: #83B7EF;

		.headImg {
			width: 200rpx;
			height: 200rpx;
			border-radius: 50%;
			background: #fbf6f6;
			margin-top: 30px;
			margin-left: 30px;

			image {
				width: 100%;
				height: 100%;
				display: block;
				border-radius: 50%;
			}
		}

		.nick {
			margin-top: 60px;
			margin-left: 60px;
			font-size: 25px;
		}
	}

	.record {
		height: 30px;

		.txt {
			margin-top: 10px;
			margin-left: 20px;
			// background-color: #007AFF;
		}
	}

	.auth {
		height: 45px;
		width: 80%;
		margin: 0 auto;
		margin-top: 45px;

		button {
			height: 100%;
			background-color: #4CD964;
			color: #FFFFFF;
			border-radius: 10px;
		}
	}
	.logout {
		height: 45px;
		width: 80%;
		margin: 0 auto;
		margin-top: 45px;
	
		button {
			height: 100%;
			background-color: #DD524D;
			color: #FFFFFF;
			border-radius: 10px;
		}
	}
</style>
