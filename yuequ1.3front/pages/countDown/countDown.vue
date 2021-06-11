<template>
	<view class="content">
		<view class="top">
			<view class="event">
				当前事项
			</view>
			<view class="addbut">
				<button type="default" @click="add">添加事件</button>
			</view>
		</view>
		<view class="line"></view>
		<view class="input">
			<view class="in-name-date">
				<input type="text" value="" class="name-input" placeholder=" 事件名称" v-model="name_value" />
				<picker mode="date" :value="date_value" @change="dateChange">
					<input type="text" class="date-input" :value="date_value" placeholder=" 事件日期" />
				</picker>
			</view>
			<view class="top-line"></view>
		</view>
		<view class="incident" v-for="(item,index) in incident">
			<view class="name-delete">
				<view class="name">
					{{item.EVENT_NAME}}
				</view>
				<button type="default" class="delete" @click="remove(item.EVENT_ID)">删除</button>
			</view>
			<view class="date-time">
				<view class="date">
					{{item.END_TIME}}
				</view>
				<view class="time">
					剩余{{item.remain_day}}天
				</view>
			</view>
			<view class="line"></view>
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
						url: 'https://pumpkin2.zzxcloud.cn/count/history', //仅为示例，并非真实的接口地址
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
							_this.incident = res.data
							_this.eventid = res.data.EVENT_ID
							// console.log(_this.incident)
							// _this.list = res.data
							console.log('----successed----')
						},
						fail(res) {
							console.log('----fail----')
						}
					})
				}
			})
		},
		data() {
			return {
				incident: [],
				USERID: "",
				name_value: "",
				date_value: "",
				eventid: "",
			}
		},
		methods: {
			remove: function(index) {
				let _this = this
				if (!_this.USERID) {
					wx.showToast({
						title: '暂未登陆！',
						icon: 'none',
						duration: 2000 //持续的时间
					})
					return
				}
				wx.request({
					url: 'https://pumpkin2.zzxcloud.cn/count/delete', //仅为示例，并非真实的接口地址
					data: {
						USER_ID: _this.USERID,
						EVENT_ID: index,
					},
					header: {
						'content-type': 'application/json' // 默认值
						// 'Content-Type': 'application/x-www-form-urlencoded'
					},
					method: 'POST',
					success(res) {
						console.log(res.data)
						// _this.list = res.data
						console.log('----successed----')
						// _this.incident.splice(index-1,1)
						// console.log(_this.incident+"我在这啊")
						
						wx.request({
							url: 'https://pumpkin2.zzxcloud.cn/count/history', //仅为示例，并非真实的接口地址
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
								_this.incident = res.data
								// _this.list = res.data
								console.log('----successed----')

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
			},
			add: function() {
				let _this = this
				if (!_this.USERID) {
					wx.showToast({
						title: '暂未登陆！',
						icon: 'none',
						duration: 2000 //持续的时间
					})
					return
				} else if ((!_this.name_value && _this.date_value) || (!_this.name_value && !_this.date_value)) {
					wx.showToast({
						title: '请填写事件名称！',
						icon: 'none',
						duration: 2000 //持续的时间
					})
					return
				} else if (_this.name_value && !_this.date_value) {
					wx.showToast({
						title: '请填写日期！',
						icon: 'none',
						duration: 2000 //持续的时间
					})
					return
				}
				wx.request({
					url: 'https://pumpkin2.zzxcloud.cn/count/add', //仅为示例，并非真实的接口地址
					data: {
						USER_ID: _this.USERID,
						EVENT_NAME: _this.name_value,
						END_TIME: _this.date_value
					},
					header: {
						'content-type': 'application/json' // 默认值
						// 'Content-Type': 'application/x-www-form-urlencoded'
					},
					method: 'POST',
					success(res) {
						console.log(res.data)
						// _this.list = res.data
						console.log('----successed----')
						wx.request({
							url: 'https://pumpkin2.zzxcloud.cn/count/history', //仅为示例，并非真实的接口地址
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
								_this.incident = res.data
								// _this.list = res.data
								console.log('----successed----')

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
			},
			dateChange: function(e) {
				this.date_value = e.target.value
			}
		}
	}
</script>

<style lang="scss">
	.top {
		display: flex;
	}

	.event {
		margin: 50rpx 40rpx 40rpx 60rpx;
		display: flex;
	}

	.top-line {
		margin-top: 12px;
		background-color: #808080;
		height: 1rpx;
		width: 700rpx;
		margin-left: 23rpx;
		align-items: center;
	}

	.line {
		background-color: #808080;
		height: 1rpx;
		width: 700rpx;
		margin-left: 23rpx;
		margin-top: 10px;
		margin-bottom: 10px;
		align-items: center;
	}

	.addbut {
		width: 40%;
		// background-color: #4CD964;
		margin-left: auto;

		button {
			margin-top: 17px;
			width: 60%;
			font-size: 13px;
			background-color: #8ABAEF;
			border-radius: 20px;
			color: #FFFFFF;
		}

	}

	.input {
		height: 50px;
		// background-color: #007AFF;

	}

	.incident {
		height: 85px;
		// background-color: #007AFF;
	}

	.in-name-date {
		display: flex;
	}

	.name-input {
		margin-left: 23px;
		height: 30px;
		width: 40%;
		border: solid;
		border-radius: 10px;
		border-width: 1px;
		border-color: #353635;
		// background-color: #007AFF;
	}

	.date-input {
		margin-left: 25px;
		height: 30px;
		width: 70%;
		border: solid;
		border-radius: 10px;
		border-width: 1px;
		border-color: #353635;
	}

	.name-delete {
		display: flex;

		button {
			background-color: #DD524D;
			color: #FFFFFF;
		}
	}

	.name {
		width: 30%;
		margin-left: 30px;
		margin-top: 10px;
		font-size: 18px;
		// background-color: #007AFF;
	}

	.delete {
		margin-top: 10px;
		margin-left: 120px;
		width: 20%;
		height: 30px;
		font-size: 13px;
		border-radius: 13px;
	}

	.date-time {
		display: flex;
	}

	.date {
		width: 30%;
		margin-left: 30px;
		margin-top: 13px;
		color: #a9a9a9;
		// background-color: #007AFF;

	}

	.time {
		width: 28%;
		margin-left: auto;
		margin-top: 13px;
		color: #8ABADB;
		// background-color: #007AFF;

	}
</style>
