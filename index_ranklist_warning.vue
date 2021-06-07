<!-- 排行榜 -->
<template>
	<view class="content">
		<view class="title">
			<text class="title-text">排行榜</text>
		</view>
		<view class="list-wrapper">
			<view class="me">
				<text class="me-title">我的成绩</text>
				<view class="me-image">
					<image :src="me.USER_headimage" mode=""></image>
				</view>
				<view class="me-nick">{{ me.USER_NICKNAME }}
					<text style="margin: 0 10rpx;">累计打卡{{ me.sumtime }}</text>min
					<text style="margin-left: 40rpx;color: #4287f4;">第{{me.rankindex}}名</text>
				</view>
			</view>
		</view>
		<view style="margin-top: 40rpx;padding-bottom: 120rpx;">
		
			<view class="item"  :style="{color: item.color}" v-for="(item, index) in list">
				<view class="item-left">
					
					<!-- <icon v-if="index <= 2" :class="[item.icon ? item.icon : '']" :style="{color: item.iconColor}"></icon> -->
					<!-- 排名 -->
					<view style="margin: 0 20rpx;" >{{index + 1}}</view>
					<!-- 头像 -->
					<view class="item-image">
						<image :src="item.USER_headimage" mode=""></image>
					</view>
					<!-- 昵称 -->
					<text>{{ item.USER_NICKNAME }}</text>
				</view>
				<!-- 打卡时长 -->
				<view class="item-nick">
					累计打卡<text > {{ item.sumtime }}</text>min
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		onShow() {
			let _this = this
			
			wx.getStorage({ //获取本地缓存
				key: "openid",
				success: function(res) {
					_this.USERID = res.data
					console.log(_this.USERID)
					wx.request({
					  url: 'https://pumpkin2.zzxcloud.cn/user/rank', //仅为示例，并非真实的接口地址
					  data: {
							USER_ID:_this.USERID,
					  },
					  header: {
						'content-type': 'application/json' // 默认值
						// 'Content-Type': 'application/x-www-form-urlencoded'
					  },
					  method: 'POST',
					  success(res) {
						console.log(res.data)
						_this.list = res.data
						console.log('----successed----')
						
					  },
					  fail(res){
						console.log('----fail----')
					  }
					})
					wx.request({
					  url: 'https://pumpkin2.zzxcloud.cn/user/myrank', //仅为示例，并非真实的接口地址
					  data: {
							USER_ID:_this.USERID,
					  },
					  header: {
						'content-type': 'application/json' // 默认值
						// 'Content-Type': 'application/x-www-form-urlencoded'
					  },
					  method: 'POST',
					  success(res) {
						console.log(res.data)
						_this.me = res.data
						console.log('----successed----')
						
					  },
					  fail(res){
						console.log('----fail----')
					  }
					})
				},
				fail:function(){
						wx.showModal({
							title: '提示',
							content: '暂未登陆,请前往我的页面登录！',
							success: function(res) {
								if (res.confirm) { //这里是点击了确定以后
									console.log('用户点击确定')
									wx.navigateBack({})
								} else { //这里是点击了取消以后
									console.log('用户点击取消')
									wx.navigateBack({})
								}
							}
						})
				}
			})
			
		},
		data() {
			return {
				USERID:"",
				me:{

				},
				list: [
					
				]
				
			}
		},
		methods: {

		}
	}
</script>

<style lang="scss">
	@font-face {
	  font-family: "iconfont"; /* Project id  */
	  src: url('../../fonts/iconfont.ttf?t=1620315771143') format('truetype');
	}
	
	.iconfont {
	  font-family: "iconfont" !important;
	  font-size: 60rpx;
	  font-style: normal;
	  -webkit-font-smoothing: antialiased;
	  -moz-osx-font-smoothing: grayscale;
	}
	
	.icon-second:before {
	  content: "\e66b";
	}
	
	.icon-third:before {
	  content: "\e66c";
	}
	
	.icon-diyi:before {
	  content: "\e6e9";
	}
	
	
	

	page {
		min-height: 100%;
		background-color: #FFFFFF;
	}

	.content {
		background: #ababaa;
		width: 90%;
		margin: 40rpx auto 0;
		border-radius: 20rpx;

		.title {
			text-align: center;
			padding: 40rpx;

			.title-text {
				display: block;
				width: 60%;
				text-align: center;
				margin: 0 auto;
				background: #77b7f4;
				font-size: 36rpx;
				color: #fafcff;
				border-radius: 40rpx;
				letter-spacing: 2rpx;
				border: 2rpx solid #bbbcbb;
				padding: 20rpx;
			}
		}
	}

	.list-wrapper {
		.me {
			display: flex;
			justify-content: center;
			align-items: center;
			background: #fefefe;
			border-radius: 20rpx;
			width: 90%;
			padding: 20rpx 0;
			margin: 0 auto;

			.me-title {
				width: 70rpx;
				font-size: 28rpx;
				color: #4287f4;
			}

			.me-image {
				width: 50rpx;
				height: 50rpx;
				border-radius: 50%;
				padding: 10rpx;
				background: #fbf6f6;

				image {
					width: 100%;
					height: 100%;
					display: block;
					border-radius: 50%;
				}
			}

			.me-nick {
				font-size: 28rpx;
				padding-left: 10rpx;
			}
		}
	}

	.item {
		display: flex;
		justify-content: space-between;
		width: 88%;
		margin: 0 auto;
		margin-bottom: 24rpx;

		.item-left {
			display: flex;
			justify-content: center;
			align-items: center;
			text{
				font-size: 24rpx;
			}
		}

		.item-image {
			width: 50rpx;
			height: 50rpx;
			border-radius: 50%;
			padding: 8rpx;
			background: #fbf6f6;
			margin: 0 20rpx 0 15rpx;	

			image {
				width: 100%;
				height: 100%;
				display: block;
				border-radius: 50%;
			}
		}

		.item-nick {
			display: flex;
			justify-content: center;
			align-items: center;
			font-size: 24rpx;
			text {
				margin: 0 10rpx;
				color: #4388f4;
			}
		}
	}
</style>
