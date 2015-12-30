package com.geekylab.general.network;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;

public class TimerService extends Service {
	public enum MINUTES {
		THREE, FIVE
	}

	public TimerService() {
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				sendBroadcast(MINUTES.THREE);
			}
		}, 3000);

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				sendBroadcast(MINUTES.FIVE);
			}
		}, 5000);
		return Service.START_STICKY;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	private void sendBroadcast(MINUTES minute){
		Intent intent = new Intent(TimerService.class.getName());
		intent.putExtra(MINUTES.class.getName(), minute.toString());
		LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
	}




}