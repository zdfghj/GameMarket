package com.game.gamemarket;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;

/**
 * 软件启动 类
 */
public class SplashActivity extends Activity{
	
	// 最短的显示时间
	private static final int SHOW_TIME_MIN = 3000;
	// 开始时间
	private long mStartTime;
	
	private Handler splashHandler = new Handler(new Callback() {
		
		@Override
		public boolean handleMessage(Message msg) {
			// 加载的时间
			long loadingTime = System.currentTimeMillis() - mStartTime;
			if(loadingTime <  SHOW_TIME_MIN){
				splashHandler.postDelayed(goToMainActivity, SHOW_TIME_MIN - loadingTime);				
			}else {
				splashHandler.post(goToMainActivity);
			}
			return false;
		}
	});
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_activity);
		// 记录界面开始的时间
		mStartTime = System.currentTimeMillis();
		/**
		 * 这里放入首页信息加载完成的信号
		 */
		splashHandler.sendEmptyMessage(1);
	}
	
	/**
	 * 跳转到MainActivity的线程
	 */
	private Runnable goToMainActivity = new Runnable() {
		
		@Override
		public void run() {
			Intent intent = new Intent(SplashActivity.this,MainActivity.class);
			startActivity(intent);
			SplashActivity.this.finish();
		}
	};	
}
