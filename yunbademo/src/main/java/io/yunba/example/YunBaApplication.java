package io.yunba.example;

import android.app.Application;
import android.util.Log;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import io.yunba.android.manager.YunBaManager;

public class YunBaApplication extends Application {
	private final static String TAG = "YunBaApplication";
	@Override
	public void onCreate() {
		super.onCreate();
		initConnectStatus();
		startBlackService();
	}

	private void initConnectStatus() {
		//set MainActivity title status
		SharePrefsHelper.setString(getApplicationContext(), MainActivity.CONNECT_STATUS, "");
	}

	private void startBlackService() {
		YunBaManager.start(getApplicationContext());
		
		IMqttActionListener listener = new IMqttActionListener() {
			
			@Override
			public void onSuccess(IMqttToken asyncActionToken) {
				String topic = DemoUtil.join(asyncActionToken.getTopics(), ",");
				Log.d(TAG, "Subscribe succeed : " + topic);
//				DemoUtil.showToast( "Subscribe succeed : " + topic, getApplicationContext());
				StringBuilder showMsg = new StringBuilder();
				showMsg.append("subscribe succ：").append(YunBaManager.MQTT_TOPIC)
						.append(" = ").append(topic);
			}
			
			@Override
			public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
				String msg =  "Subscribe failed : " + exception.getMessage();
				Log.d(TAG, msg);
//				DemoUtil.showToast(msg, getApplicationContext());
//				
				
			}
		};
		
		//for test
		YunBaManager.subscribe(getApplicationContext(), new String[]{"t1", "t2", "t3"}, listener);
	}


	@Override
	public void onTerminate() {
		super.onTerminate();
	}

	

}
