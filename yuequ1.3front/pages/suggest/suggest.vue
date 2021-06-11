<template>
	<view class="content">
		<view class="text">
			请输入你对图书馆的一些建议：
		</view>
		<textarea class="uni-textarea" v-model="text" />
		<view class="submit">
			<button type="default" @click="submit">提交</button>
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
					_this.openid = res.data
				}
			})
		},
		data() {
			return {
				openid: "",
				text: ""
			}
		},
		methods: {
			submit: function() {
				let _this = this
				if (!_this.openid) {
					wx.showToast({
						title: '暂未登陆，无法提交！',
						icon: 'none',
						duration: 2000 //持续的时间
					})
					return
				}
				console.log(_this.text)
				wx.request({
					url: 'https://pumpkin2.zzxcloud.cn/user/library_advice', //仅为示例，并非真实的接口地址
					data: {
						USER_ID: _this.openid,
						advice: _this.text
					},
					header: {
						'content-type': 'application/json' // 默认值
						// 'Content-Type': 'application/x-www-form-urlencoded'
					},
					method: 'POST',
					success(res) {
						console.log(res.data)
						console.log('----successed----')

					},
					fail(res) {
						console.log('----fail----')
					}
				})

				_this.text = ""
			}
		}
	}
</script>

<style lang="scss">
	.text {
		padding: 10px;
	}

	.uni-textarea {
		width: 350px;
		border-radius: 10px;
		margin: 0 auto;
		background-color: #999999;
	}

	.submit {
		height: 30px;
		width: 25%;
		margin-left: auto;
		margin-top: 10px;
		margin-right: 10px;

		button {
			height: 100%;
			width: 100%;
			font-size: 15px;
			margin-top: 17px;
		}
	}
</style>
