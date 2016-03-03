package io.yunba.example;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.xc.myapp.R;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.yunba.android.manager.YunBaManager;

public class MainActivity extends Activity  implements android.view.View.OnClickListener {

	private final static String TAG = "MainActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initUI();
		registerMessageReceiver();  // used for receive msg
	}
	
	private Button publish;
	private Button sub;
	private Button unsubscibe;
	private Button publish_sec;
	
	private Button get_alias;
	private Button set_alias;
	private EditText pub_topic;
	private EditText pub_msg;
	private EditText sub_topic;
	private EditText alias_of_getset;
	
	private TextView pkgName;
	private TextView appKey;
	public static TextView msg_show;
	public static ScrollView scroll;
	public static boolean isForeground = false;
	public final static String MESSAGE_RECEIVED_ACTION = "io.yunba.example.msg_received_action";
	public final static String CONNECT_STATUS = "connect_status";
	
	private void initUI() {
		publish = (Button)findViewById(R.id.publish);
		sub = (Button)findViewById(R.id.subscribe);
		unsubscibe = (Button)findViewById(R.id.ping);
		publish_sec = (Button)findViewById(R.id.publish_select);
		get_alias = (Button)findViewById(R.id.get_alias);
		set_alias = (Button)findViewById(R.id.set_alias);
		publish.setOnClickListener(this);
		sub.setOnClickListener(this);
		unsubscibe.setOnClickListener(this);
		publish_sec.setOnClickListener(this);
		set_alias.setOnClickListener(this);
		get_alias.setOnClickListener(this);
		alias_of_getset = (EditText)findViewById(R.id.alias_of_getset);
		pub_topic = (EditText)findViewById(R.id.publish_topic);
		pub_msg = (EditText)findViewById(R.id.publish_msg);
		sub_topic = (EditText)findViewById(R.id.sub_topic);
		pkgName = (TextView)findViewById(R.id.tv_pkgname);
		pkgName.setText("AppID：" + getPackageName());
		appKey = (TextView)findViewById(R.id.tv_appkey);
		appKey.setText("AppKey：" + DemoUtil.getAppKey(getApplicationContext()));
//		msg_show.setMovementMethod(ScrollingMovementMethod.getInstance());
//		msg_show.setBackgroundResource(R.drawable.text_view_border); 
//		msg_show.setMaxLines(300);
		String status = SharePrefsHelper.getString(getApplicationContext(), CONNECT_STATUS, null);
		if(!DemoUtil.isEmpty(status)) {
			setTitleOfApp(status);
		}
		if (!DemoUtil.isNetworkEnabled(getApplicationContext())) {
			setTitleOfApp("YunBa - DisConnected");
		}
		initLog();
	//	scroll = (ScrollView) findViewById(R.id.scroller);
	}

	private void initLog() {
		Intent intent = getIntent();
		if (null != intent) {
		    Bundle bundle = intent.getExtras();
		    if (null != bundle) {
		    String topic = bundle.getString(YunBaManager.MQTT_TOPIC);
		    String msg = bundle.getString(YunBaManager.MQTT_MSG);
			StringBuilder showMsg = new StringBuilder();
			showMsg.append("Received msg from server: ").append(YunBaManager.MQTT_TOPIC)
					.append(" = ").append(topic).append(" ")
					.append(YunBaManager.MQTT_MSG).append(" = ").append(msg);
			setCostomMsg(showMsg.toString());
		    }
	     }
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}	



	@Override
	protected void onResume() {
		isForeground = true;
		super.onResume();
		restoreDatas();
//		scroll.post(new Runnable() {
//			public void run() {
//				if (null != scroll) scroll.fullScroll(View.FOCUS_DOWN);
//			}
//		});
	}

	private void restoreDatas() {
		String last_pub = SharePrefsHelper.getString(getApplicationContext(), YunBaManager.LAST_PUB, null);
		if (!DemoUtil.isEmpty(last_pub)) pub_topic.setText(last_pub);
		
		String last_sub = SharePrefsHelper.getString(getApplicationContext(), YunBaManager.LAST_SUB, null);
		if (!DemoUtil.isEmpty(last_sub)) sub_topic.setText(last_sub);
		
		String topicsStr = SharePrefsHelper.getString(getApplicationContext(), YunBaManager.HISTORY_TOPICS, null);
		if (!DemoUtil.isEmpty(topicsStr)) {
			Log.i(TAG, "getHistoryTopics: " + topicsStr);
			String[] topicsArr = topicsStr.split("\\$\\$");
			if (topics.size() == 0) {
				List<String> list = Arrays.asList(topicsArr);
				topics = new ArrayList<String>(list);
			}
		}
		
	}


	@Override
	protected void onPause() {
		isForeground = false;
		super.onPause();
		saveLastFiveTopics();
	}

	private void saveLastFiveTopics() {
		int size = topics.size();
		StringBuilder sb = new StringBuilder();
		for (int i = (topics.size() -1 ); (i >= 0  && i >= size -6) ; i--) {
			sb.append(topics.get(i));
			if (!(i == 0 ||  i == size -6)) {
				sb.append("$$");
			}
		}
		String topicsStr = sb.toString();
		Log.i(TAG, "saveLastFiveTopics: " + topicsStr);
		if (DemoUtil.isEmpty(topicsStr)) return;
		SharePrefsHelper.setString(getApplicationContext(), YunBaManager.HISTORY_TOPICS, topicsStr);
	}

	@Override
	protected void onDestroy() {
		unregisterReceiver(mMessageReceiver);
		super.onDestroy();
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ping:
			unsubscribe();
			break;
		case R.id.publish:
			publish();
			break;
		case R.id.subscribe:
			subscribe();
			break;
		case R.id.publish_select:
			showTopic(pub_topic);
			break;	
		case R.id.set_alias:
			setAlias();
			break;		
		case R.id.get_alias:
			getAlias();
			break;		
		default:
			break;
		}
		
	}

	private void getAlias() {
		setCostomMsg("get alias ");
		YunBaManager.getAlias(getApplicationContext(),new IMqttActionListener() {
			
			@Override
			public void onSuccess(IMqttToken arg) {
				final String alias = arg.getAlias();

				StringBuilder showMsg = new StringBuilder();
				showMsg.append("[Demo] getAlias alias ").append(" = ")
						.append(alias).append(" succeed");
				if(null != alias) {
					MainActivity.this.runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							alias_of_getset.setText(alias);							
						}
					});
					
				}
				setCostomMsg(showMsg.toString());

			}
			
			@Override
			public void onFailure(IMqttToken arg0, Throwable arg) {
				StringBuilder showMsg = new StringBuilder();
				showMsg.append("[Demo] setAlias alias ").append(" failed");
	   	        setCostomMsg(showMsg.toString());
			}
		});
		
	}

	private void setAlias() {
		final String alias = alias_of_getset.getText().toString().trim();
		if (TextUtils.isEmpty(alias)) {
			Toast.makeText(MainActivity.this, "Alias should not be null", Toast.LENGTH_SHORT).show();
			return;
		}
		setCostomMsg("set alias = " + alias);
		YunBaManager.setAlias(getApplicationContext(), alias, new IMqttActionListener() {
			
			@Override
			public void onSuccess(IMqttToken arg) {
				StringBuilder showMsg = new StringBuilder();
				showMsg.append("[Demo] setAlias alias ")
				.append(" = ").append(alias).append(" succeed");
	   	        setCostomMsg(showMsg.toString());				
			}
			
			@Override
			public void onFailure(IMqttToken arg0, Throwable arg) {
				StringBuilder showMsg = new StringBuilder();
				showMsg.append("[Demo] setAlias alias ")
				.append(" = ").append(alias).append(" failed");
	   	        setCostomMsg(showMsg.toString());
			}
		});
	}

	private void unsubscribe() {
		final String topic = sub_topic.getText().toString().trim();
		if (TextUtils.isEmpty(topic)) {
			Toast.makeText(MainActivity.this, "String should not be null", Toast.LENGTH_SHORT).show();
			return;
		}
		addTopic(topic);
		setCostomMsg("unsubscribe topic = " + topic);
		YunBaManager.unsubscribe(getApplicationContext(), topic, new IMqttActionListener() {
			
			@Override
			public void onSuccess(IMqttToken asyncActionToken) {
				DemoUtil.showToast( "unsubscribe succeed : " + topic, getApplicationContext());
				StringBuilder showMsg = new StringBuilder();
				showMsg.append("[Demo] unsubscribe ").append(YunBaManager.MQTT_TOPIC)
						.append(" = ").append(topic).append(" succeed");
				setCostomMsg(showMsg.toString());
			}
			
			@Override
			public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
				String msg =  "[Demo] unsubscribe topic = "+ topic +" failed : " + exception.getMessage();
				setCostomMsg(msg);
				DemoUtil.showToast(msg, getApplicationContext());
				
				
			}
		});
		SharePrefsHelper.setString(getApplicationContext(), YunBaManager.LAST_SUB, topic);
	}

	private void subscribe() {
		final String topic = sub_topic.getText().toString().trim();
		if (TextUtils.isEmpty(topic)) {
			Toast.makeText(MainActivity.this, "String should not be null", Toast.LENGTH_SHORT).show();
			return;
		}
		addTopic(topic);
		setCostomMsg("Subscribe topic = " + topic);
		YunBaManager.subscribe(getApplicationContext(), topic, new IMqttActionListener() {
			
			@Override
			public void onSuccess(IMqttToken asyncActionToken) {
				DemoUtil.showToast( "Subscribe succeed : " + topic, getApplicationContext());
				StringBuilder showMsg = new StringBuilder();
				showMsg.append("[Demo] subscribe ").append(YunBaManager.MQTT_TOPIC)
						.append(" = ").append(topic).append(" succeed");
				setCostomMsg(showMsg.toString());
			}
			
			@Override
			public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
				String msg =  "[Demo] Subscribe topic = "+ topic +" failed : " + exception.getMessage();
				setCostomMsg(msg);
				DemoUtil.showToast(msg, getApplicationContext());
				
				
			}
		});
		SharePrefsHelper.setString(getApplicationContext(), YunBaManager.LAST_SUB, topic);
	}

	private void publish() {
		final String topic = pub_topic.getText().toString().trim();
		final String msg = pub_msg.getText().toString().trim();
		if (TextUtils.isEmpty(topic) || TextUtils.isEmpty(msg)) {
			Toast.makeText(MainActivity.this, "String should not be null", Toast.LENGTH_SHORT).show();
			return;
		}
		addTopic(topic);
		setCostomMsg("Publish msg = " + msg + " to topic = " + topic);
		YunBaManager.publish(getApplicationContext(), topic, msg, new IMqttActionListener() {
			public void onSuccess(IMqttToken asyncActionToken) {

				String msgLog = "Publish succeed : " + topic;
				StringBuilder showMsg = new StringBuilder();
				showMsg.append("[Demo] publish msg")
						.append(" = ").append(msg).append(" to ")
						.append(YunBaManager.MQTT_TOPIC).append(" = ").append(topic).append(" succeed");
				setCostomMsg(showMsg.toString());
				DemoUtil.showToast(msgLog, getApplicationContext());
			}
			
			@Override
			public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
				String msg = "[Demo] Publish topic = " + topic + " failed : " + exception.getMessage();
				setCostomMsg(msg);
				DemoUtil.showToast(msg, getApplicationContext());
				
			}
		});
		SharePrefsHelper.setString(getApplicationContext(), YunBaManager.LAST_PUB, topic);
	}

	private MessageReceiver mMessageReceiver;
	public void registerMessageReceiver() {
		mMessageReceiver = new MessageReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(YunBaManager.MESSAGE_RECEIVED_ACTION);
		filter.addCategory(getPackageName());
		registerReceiver(mMessageReceiver, filter);
		
		IntentFilter filterCon = new IntentFilter();
		filterCon.addAction(YunBaManager.MESSAGE_CONNECTED_ACTION);
		filterCon.addCategory(getPackageName());
		registerReceiver(mMessageReceiver, filterCon);
		
		IntentFilter filterDis = new IntentFilter();
		filterDis.addAction(YunBaManager.MESSAGE_DISCONNECTED_ACTION);
		filterDis.addCategory(getPackageName());
		registerReceiver(mMessageReceiver, filterDis);
		
		IntentFilter pres = new IntentFilter();
		pres.addAction(YunBaManager.PRESENCE_RECEIVED_ACTION);
		pres.addCategory(getPackageName());
		registerReceiver(mMessageReceiver, pres);
		
	}
	
	public class MessageReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
		    Log.i(TAG, "Action - " + intent.getAction());
			if (YunBaManager.MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
				String status = "YunBa - Connected";
				setTitleOfApp(status);	
				String topic = intent.getStringExtra(YunBaManager.MQTT_TOPIC);
				String msg = intent.getStringExtra(YunBaManager.MQTT_MSG);
				StringBuilder showMsg = new StringBuilder();
				showMsg.append("[Message] ").append(YunBaManager.MQTT_TOPIC)
						.append(" = ").append(topic).append(" ,")
						.append(YunBaManager.MQTT_MSG).append(" = ").append(msg);
				setCostomMsg(showMsg.toString());
					
			} else if(YunBaManager.MESSAGE_CONNECTED_ACTION.equals(intent.getAction())) {
				setCostomMsg("[YunBa] Connected");
				String status = "YunBa - Connected";
				setTitleOfApp(status);
				SharePrefsHelper.setString(getApplicationContext(), CONNECT_STATUS, status);
			} else if(YunBaManager.MESSAGE_DISCONNECTED_ACTION.equals(intent.getAction())) {
				setCostomMsg("[YunBa] DisConnected");
				String status = "YunBa - DisConnected";
				setTitleOfApp(status);
				SharePrefsHelper.setString(getApplicationContext(), CONNECT_STATUS, status);
			} else if (YunBaManager.PRESENCE_RECEIVED_ACTION.equals(intent.getAction())) {
				
				String status = "YunBa - Connected";
				setTitleOfApp(status);
				String topic = intent.getStringExtra(YunBaManager.MQTT_TOPIC);
				String msg = intent.getStringExtra(YunBaManager.MQTT_MSG);
				StringBuilder showMsg = new StringBuilder();
				showMsg.append("[Message from prensence] ").append(YunBaManager.MQTT_TOPIC)
						.append(" = ").append(topic).append(" ,")
						.append(YunBaManager.MQTT_MSG).append(" = ").append(msg);
				setCostomMsg(showMsg.toString());
				
		}
		}
	}

	private void setCostomMsg(final String msg){
		 YunBaTabActivity.setCostomMsg(this, msg);
	}
	
	private List<String> topics = new ArrayList<String>();


	private void addTopic(String topic)  {
		if(DemoUtil.isEmpty(topic)) return;
		for (int i = 0; i < topics.size(); i++) {
			if (topic.equals(topics.get(i))) return;
		}
		topics.add(topic);
		Log.i(TAG, "Topic size = " + topics.size());
		
	}
	
	private void showTopic(final EditText text){
		 final String[] topicArr = topics.toArray(new String[0]);
			Log.i("Topic", "topicArr size = " + topicArr.length);
		 new AlertDialog.Builder(MainActivity.this).setTitle("Select a topic")
		 .setItems(topicArr,new DialogInterface.OnClickListener(){  
		      public void onClick(DialogInterface dialog, int which){  
		    	  text.setText(topicArr[which]);
		       dialog.dismiss();  
		      }  
		   }).show();
	} 
	
	private void  setTitleOfApp(final String status) {
		
		Activity parent = this.getParent();
		if(!DemoUtil.isEmpty(status) && null != parent) {
			this.getParent().setTitle(status);
		}
	}
}
