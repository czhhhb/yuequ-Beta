<!-- 签到打卡 -->
<template>
	<view class="content">
		<view class="top">
			<button type="default" @click="clock">{{ clock_state }}</button>
		</view>
		<view class="middle">
			<view>历史记录</view>
			<view>
				<uni-icons type="gear-filled" />
			</view>
		</view>
		<view class="bottom">
			<view class="item" v-for="item in list">
				<view class="date">{{ item.CLOCK_BEGIN_TIME.split("T")[0] }}</view>
				<view class="item-bottm">
					<view class="item-bottm-top">
						{{ item.CLOCK_BEGIN_TIME.split("T")[1] }}-{{item.CLOCK_OVER_TIME.split("T")[1]}}
					</view>
					<view class="cumulative-duration">
						学习 {{ item.CLOCK_TIME }} 分钟
					</view>
				</view>
			</view>
		</view>
		<view class="mask" v-if="mask_isShow" @click="close">
			<view class="window" @click.stop="">
				{{ window_text }}
			</view>
		</view>
	</view>
</template>

<script>
	import uniIcons from '../../components/uni-icons/uni-icons.vue'
	export default {

		onShow() {
			let _this = this
			console.log("进入onshow")
			wx.getStorage({ //获取本地缓存
				key: "openid",
				success: function(res) {
					_this.USERID = res.data
					console.log(_this.USERID)
					wx.request({ //获取打卡历史记录
						url: 'https://pumpkin2.zzxcloud.cn/clock/history', //仅为示例，并非真实的接口地址
						data: {
							USER_ID: _this.USERID,
						},
						header: {
							'content-type': 'application/json' // 默认值
							// 'Content-Type': 'application/x-www-form-urlencoded'
						},
						method: 'POST',
						success(res) {
							// console.log(_this.USERID)
							// console.log(res.data)
							console.log('----successed----')
							_this.list = res.data
							console.log(_this.list)
						},
						fail(res) {
							console.log('----fail----')
						}
					})
					wx.request({ //获取打卡状态码
						url: 'https://pumpkin2.zzxcloud.cn/clock/state', //仅为示例，并非真实的接口地址
						data: {
							USER_ID: _this.USERID,
						},
						header: {
							'content-type': 'application/json' // 默认值
							// 'Content-Type': 'application/x-www-form-urlencoded'
						},
						method: 'POST',
						success(res) {
							// console.log(_this.USERID)
							// console.log(res.data)
							console.log('----successed----')
							_this.state = res.data.state
							console.log(_this.state)
							if (_this.state == 0) {
								_this.clock_state = "签到"
							} else
								_this.clock_state = "签退"
						},
						fail(res) {
							console.log('----fail----')
						}
					})
				}
			})


		},
		components: {
			uniIcons
		},
		data() {
			return {
				list: [{}], //历史记录列表
				USERID: "", //openid
				mask_isShow: false, //弹窗显示
				state: "", //状态码
				clock_state: "签到",
				window_text: "" //弹窗文本
			}

		},
		methods: {
			clock: function() {
				let _this = this
				if (!_this.USERID) {
					wx.showToast({
					     title: '暂未登陆！',
					     icon: 'none',
					     duration: 2000//持续的时间
					   })
					return
				}
				if (_this.state) {
					_this.clock_state = "签到"
					wx.request({
						url: 'https://pumpkin2.zzxcloud.cn/clock/over', //仅为示例，并非真实的接口地址
						data: {
							USER_ID: _this.USERID,
						},
						header: {
							'content-type': 'application/json' // 默认值
							// 'Content-Type': 'application/x-www-form-urlencoded'
						},
						method: 'POST',
						success(res) {
							console.log(res.data)
							wx.request({ //获取打卡历史记录
								url: 'https://pumpkin2.zzxcloud.cn/clock/history', //仅为示例，并非真实的接口地址
								data: {
									USER_ID: _this.USERID,
								},
								header: {
									'content-type': 'application/json' // 默认值
									// 'Content-Type': 'application/x-www-form-urlencoded'
								},
								method: 'POST',
								success(res) {
									// console.log(_this.USERID)
									// console.log(res.data)
									console.log('----successed----')
									_this.list = res.data
									console.log(_this.list)
								},
								fail(res) {
									console.log('----fail----')
								}
							})
							wx.request({ //获取打卡状态码
								url: 'https://pumpkin2.zzxcloud.cn/clock/state', //仅为示例，并非真实的接口地址
								data: {
									USER_ID: _this.USERID,
								},
								header: {
									'content-type': 'application/json' // 默认值
									// 'Content-Type': 'application/x-www-form-urlencoded'
								},
								method: 'POST',
								success(res) {
									// console.log(_this.USERID)
									console.log(res.data)
									console.log('----successed----')
									_this.state = res.data.state
									console.log(_this.state)
								},
								fail(res) {
									console.log('----fail----')
								}
							})
							console.log('----successed----')

						},
						fail(res) {
							console.log('----fail----')
						}
					})
					this.window_text = "签退成功"
					this.mask_isShow = true
				} else {
					_this.clock_state = "签退"
					wx.request({
						url: 'https://pumpkin2.zzxcloud.cn/clock/begin', //仅为示例，并非真实的接口地址
						data: {
							USER_ID: _this.USERID,
						},
						header: {
							'content-type': 'application/json' // 默认值
							// 'Content-Type': 'application/x-www-form-urlencoded'
						},
						method: 'POST',
						success(res) {
							console.log(res.data)
							console.log('----successed----')
							wx.request({ //获取打卡状态码
								url: 'https://pumpkin2.zzxcloud.cn/clock/state', //仅为示例，并非真实的接口地址
								data: {
									USER_ID: _this.USERID,
								},
								header: {
									'content-type': 'application/json' // 默认值
									// 'Content-Type': 'application/x-www-form-urlencoded'
								},
								method: 'POST',
								success(res) {
									// console.log(_this.USERID)
									// console.log(res.data)
									console.log('----successed----')
									_this.state = res.data.state
									console.log(_this.state)
								},
								fail(res) {
									console.log('----fail----')
								}
							})
						},
						fail(res) {
							console.log('----fail----')
						}
					})
					this.window_text = "签到成功"
					this.mask_isShow = true
				}


			},
			close: function() {
				this.mask_isShow = false
			}
		}
	}
</script>

<style lang="scss">
	page {
		min-height: 100%;
		background-color: #f3f3f3;
	}

	.content {
		.top {
			padding: 80rpx 0;

			button {
				width: 45%;
				padding: 10rpx 0;
				color: #fffffe;
				letter-spacing: 10rpx;
				border-radius: 290rpx;
				background-color: #77b7ff;
			}
		}

		@mixin wrapper() {
			width: 85%;
			padding: 0 30rpx;
			margin: 0 auto;
			border-bottom: 2rpx solid #babbbb;
			padding-bottom: 30rpx;
		}

		.middle {
			@include wrapper;
			display: flex;
			align-items: center;
			justify-content: space-between;

			.uni-icons {
				color: #77b7ff !important;
				font-size: 48rpx !important;
			}
		}

		.bottom {
			display: flex;
			flex-wrap: wrap;

			.item {
				@include wrapper;

				.date {
					padding: 20rpx 0;
					font-weight: bold;
				}

				.item-bottm {
					display: flex;
					justify-content: space-between;
					align-items: flex-end;

					.item-bottm-top {
						view {
							margin-bottom: 14rpx;
							color: #828181;
						}

						view:last-child {
							margin-bottom: 0;
						}
					}

					.cumulative-duration {
						color: #508bf3;
						font-size: 36rpx;
					}
				}
			}
		}

		.mask {
			width: 100%;
			height: 100%;
			position: fixed;
			left: 0;
			top: 0;
			display: flex;
			background-color: rgba(0, 0, 0, 0.6);
			align-items: center;
			justify-content: center;
		}

		.window {
			padding: 35px;
			background-color: #FFFFFF;
			border-radius: 10px;
		}
	}
</style>
