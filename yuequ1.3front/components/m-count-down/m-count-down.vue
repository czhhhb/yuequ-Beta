<template>
	<view class="container">
		<div class="m-frame"><span class="m-text">{{checkNumber(showTime.hour)}}</span></div>
		<div class="m-frame"><span class="m-text">{{checkNumber(showTime.minute)}}</span></div>
		<div class="m-frame"><span class="m-text">{{checkNumber(showTime.second)}}</span></div>
	</view>
</template>

<script>
	export default {
		name: "m-count-down",
		props: {
			hour: {
				type: Number,
				default: 0
			},
			minute: {
				type: Number,
				default: 0
			},
			second: {
				type: Number,
				default: 0
			}
		},
		data: function() {
			return {
				showTime: {},
				timestamp: 0,
				interval: null,
				openid:""
			}
		},
		mounted: function() {
			this.initCountDown()
		},
		methods: {
			initCountDown: function() {
				this.showTime = {
					hour: this.hour,
					minute: this.minute,
					second: this.second
				}
				this.timestamp = this.second + this.minute * 60 + this.hour * 3600
				let _this = this
				
				wx.getStorage({ //获取本地缓存
					key: "openid",
					success: function(res) {
						_this.openid = res.data
						wx.request({ //获取打卡历史记录
							url: 'https://pumpkin2.zzxcloud.cn/tomato/add', //仅为示例，并非真实的接口地址
							data: {
								USER_ID: _this.openid,
								time:_this.timestamp
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
							},
							fail(res) {
								console.log('----fail----')
							}
						})
					}
				})
				let that = this
				this.interval = setInterval(function() {
					that.timestamp--
					if (that.timestamp === 0) {
						clearInterval(that.interval)
						that.$emit('timeclear')
						
					}
					// console.log(that.timestamp)
				}, 1000)
				// console.log(this.timestamp)
			},
			checkNumber: function(raw) {
				return raw < 10 ? '0' + raw : '' + raw
			}
		},
		watch: {
			timestamp: function(newValue, oldValue) {
				this.showTime.hour = parseInt(newValue / 3600)
				newValue -= this.showTime.hour * 3600
				this.showTime.minute = parseInt(newValue / 60)
				newValue -= this.showTime.minute * 60
				this.showTime.second = newValue
			}
		}
	}
</script>

<style lang="scss">
	.container {
		margin-top: 400rpx;

		.m-frame {
			float: left;
			width: 200rpx;
			height: 200rpx;
			margin-left: 37.5rpx;
			background-color: #73B7F1;
			box-shadow: 1px 1px 15px #2C405A inset,
						-1px -1px 15px #2C405A inset;
			text-align: center;
			.m-text {
				font-size: 36px;
				color: #FFF;
				line-height: 200rpx;
			}
		}
	}
</style>
