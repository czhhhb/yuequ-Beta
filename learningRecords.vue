<!-- 学习 -->
<template>
	<view style="padding-top: 200rpx;">
		<view class="title">学习时间记录</view>
		<view class="charts-box">
			<qiun-data-charts type="line" :chartData="chartData" :animation="false" />
		</view>
	</view>
</template>

<script>
	import uCharts from '../../components/u-charts/u-charts-v2.0.0.js';
	export default {
		onShow() {
			let _this = this
			wx.getStorage({ //获取本地缓存
				key: "openid",
				success: function(res) {
					_this.USERID = res.data
					console.log(_this.USERID)
					wx.request({
					  url: 'https://pumpkin2.zzxcloud.cn/user/history', //仅为示例，并非真实的接口地址
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
						_this.chartData.categories = res.data.chartData[1].categories
						_this.chartData.series = res.data.chartData[0].series
						console.log(_this.chartData.categories)
						console.log(_this.chartData.series)
						console.log(_this.chartData)
						console.log('----successed----')
						
					  },
					  fail(res){
						console.log('----fail----')
					  }
					})
				}
			})
		},
		data() {
			return {
				USERID:"",
				chartData: {
					"categories": [
						
					],
					"series": [{
						"data": [
							
						]
					}]
				}
			}
		},
		methods: {

		}
	}
</script>

<style>
	.title {
		margin-left: 15px;
		font-weight: bold;
		color: #353635;
	}

	.charts-box {
		width: 100%;
		height: 600rpx;
	}
</style>
