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
				interval: null
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
				let that = this
				this.interval = setInterval(function() {
					that.timestamp--
					if (that.timestamp === 0) {
						clearInterval(that.interval)
						that.$emit('timeclear')
					}
				}, 1000)
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
