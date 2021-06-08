<template>
	<view>
		<picker-view v-if="pickerVisible" :indicator-style="indicatorStyle" v-model="value" @change="bindChange"
			class="picker-view">
			<picker-view-column>
				<view class="item" v-for="(item,index) in hours" :key="index">{{numberStr(item)}} 时</view>
			</picker-view-column>
			<picker-view-column>
				<view class="item" v-for="(item,index) in minutes" :key="index">{{numberStr(item)}} 分</view>
			</picker-view-column>
			<picker-view-column>
				<view class="item" v-for="(item,index) in seconds" :key="index">{{numberStr(item)}} 秒</view>
			</picker-view-column>
		</picker-view>
		
		<view class="state_btn">
			<button type="default" v-if="state" @click="changeState" class="learn">学习</button>
			<button type="default" v-else="state" @click="changeState" class="rest">休息</button>
		</view>
		
		<view class="mask" v-if="mask_isShow" @click="close">
			<view class="window" @click.stop="">
				{{ window_text }}
			</view>
		</view>

		<m-count-down v-if="isStart" ref="mCountDown" @timeclear="finish" :hour="hour" :minute="minute"
			:second="second"></m-count-down>

		<button
			style="width: 37%; position: absolute; bottom: 100px; left: 10%; color: #FFF; background-color: #3187d4;"
			@click="reset" v-show="!isStart">重&emsp;&emsp;置</button>
		<button
			style="width: 80%; position: absolute; bottom: 100px; left: 10%; color: #FFF; background-color: #e00005;"
			@click="reset" v-show="isStart">结&emsp;&emsp;束</button>
		<button
			style="width: 37%; position: absolute; bottom: 100px; right: 10%; color: #FFF; background-color: #4CD964;"
			@click="start" v-show="!isStart">开&emsp;&emsp;始</button>
	</view>
</template>
<script>
	import mCountDown from '../../components/m-count-down/m-count-down.vue'

	export default {
		components: {
			mCountDown
		},
		data: function() {
			return {
				hours: [],
				hour: 0,
				minutes: [],
				minute: 0,
				seconds: [],
				second: 0,
				value: [],
				indicatorStyle: `height: 50px;`,
				pickerVisible: false,
				isStart: false,
				mask_isShow: false,
				window_text: '',
				state:true
			}
		},
		created: function() {
			this.initPicker()
		},
		methods: {
			numberStr: function(number) {
				return number < 10 ? '0' + number : '' + number
			},
			initPicker: function() {
				this.hours = []
				this.hour = 0
				this.minutes = []
				this.minute = 25
				this.seconds = []
				this.second = 0
				for (let i = 0; i < 24; i++) {
					this.hours.push(i)
				}
				for (let i = 0; i < 60; i++) {
					this.minutes.push(i)
				}
				for (let i = 0; i < 60; i++) {
					this.seconds.push(i)
				}
				this.value = [this.hour, this.minute, this.second]
				this.pickerVisible = false
				this.$nextTick(() => {
					this.pickerVisible = true
				})
			},
			bindChange: function(e) {
				const val = e.detail.value
				this.hour = this.hours[val[0]]
				this.minute = this.minutes[val[1]]
				this.second = this.seconds[val[2]]
			},
			reset: function() {
				if (this.$refs.mCountDown) {
					clearInterval(this.$refs.mCountDown.interval)
				}
				this.isStart = false
				this.pickerVisible = false
				this.$nextTick(() => {
					this.initPicker()
				})
			},
			start: function() {
				this.pickerVisible = false
				this.isStart = true
			},
			finish: function() {
				let that = this
				uni.vibrateLong({
					complete: function() {
						that.window_text = '时间到啦！！！'
						that.mask_isShow = true
					}
				})
			},
			close: function() {
				this.mask_isShow = false
				this.window_text = ''
				this.reset()
			},
			changeState:function(){
				if(this.state){
					this.state = false
				}
				else{
					this.state = true
				}
			}
		}
	}
</script>
<style lang="scss">
	.picker-view {
		width: 750rpx;
		height: 600rpx;
		margin-top: 20rpx;
	}

	.item {
		height: 50px;
		align-items: center;
		justify-content: center;
		text-align: center;
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
		z-index: 9999;
	}

	.window {
		padding: 35px;
		background-color: #FFFFFF;
		border-radius: 10px;
	}
	
	.state_btn{
		margin-top: 100px;
		width: 100%;
		height: 20%;
		// background-color: #FAC858;
		.learn{
			width: 30%;
			background-color: #1890FF;
			color: #FFFFFF;
		}
		.rest{
			width: 30%;
			background-color: #DD524D;
			color: #FFFFFF;
		}
	}
</style>
