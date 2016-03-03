package io.yunba.example;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xc.myapp.R;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.json.JSONArray;
import org.json.JSONObject;

import io.yunba.android.manager.YunBaManager;

public class APIActivity extends Activity implements android.view.View.OnClickListener{
	
	protected static final String TAG = "AliasActivity";
	private Button get_alias;
	private Button publish_alias;
	private Button state_alias;
	
	private Button alias_get_topic;
	
	private Button prensence_topic;
	
	private Button unprensence_topic;
	
	private EditText topic_of_prensence;
	private EditText alias_of_topic;
	private EditText alias_topic;
	private EditText alias_txt;
	private EditText alias_msg;
	
	ProgressDialog pd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.api_alias);
		initView();
	}

	private void initView() {
		get_alias = (Button)findViewById(R.id.alias_select);
		get_alias.setOnClickListener(this);
		publish_alias = (Button)findViewById(R.id.alias_publish);
		publish_alias.setOnClickListener(this);
		state_alias = (Button)findViewById(R.id.alias_state);
		state_alias.setOnClickListener(this);
		alias_get_topic =  (Button)findViewById(R.id.get_topic_alias);
		alias_get_topic.setOnClickListener(this);
		unprensence_topic =  (Button)findViewById(R.id.unprensence_topic);
		unprensence_topic.setOnClickListener(this);
		prensence_topic =  (Button)findViewById(R.id.prensence_topic);
		prensence_topic.setOnClickListener(this);
		
		topic_of_prensence = (EditText)findViewById(R.id.topic_of_prensence);
		alias_of_topic = (EditText)findViewById(R.id.alias_of_topic);
		alias_topic = (EditText)findViewById(R.id.alias_topic);
		alias_txt = (EditText)findViewById(R.id.alias_txt);
		alias_msg = (EditText)findViewById(R.id.alias_msg);
		
		
		
	

	}

	
	@Override
	protected void onResume() {
		super.onResume();
	
	}
	
    

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	

	

	@Override
	public void onClick(View view) {
//		System.out.println("onclick = " +  view.getId());
		switch (view.getId()) {

		case R.id.alias_publish:
			handlePublishAlias();
			//YunBaManager.publish(getApplicationContext(), MyApplication.TOPIC, "r", null);
			break;	
		case R.id.alias_select:
			handleGetAlias(this);
			break;		
		case R.id.alias_state:
			handleGetAliasState();
			break;
		case R.id.get_topic_alias:
			handleGetTopics();
			break;	
		case R.id.prensence_topic:
			handlePrensenceTopic();
			break;
		case R.id.unprensence_topic:
			handleUnPrensenceTopic();
			break;	
		default:
			break;
		}
		
	}

		
		

	private void handlePrensenceTopic() {
		   final String topic = topic_of_prensence.getText().toString().trim();
		   if (TextUtils.isEmpty(topic)) {
				Toast.makeText(APIActivity.this, "topic should not be null", Toast.LENGTH_SHORT).show();
				return;
			}
		   
		   setCostomMsg("SubscribePresence topic = " + topic);
		   YunBaManager.subscribePresence(getApplicationContext(), topic, new IMqttActionListener() {
			
			@Override
			public void onSuccess(IMqttToken arg) {
				String msg = "[Demo] SubscribePresence of topic = " + topic + " success ";
				setCostomMsg(msg);					
			}
			
			@Override
			public void onFailure(IMqttToken arg, Throwable exception) {
				String msg = "[Demo] SubscribePresence of topic = " + topic + " failed : " + exception.getLocalizedMessage(); 
				setCostomMsg(msg);	
			}
		});
	}

	private void handleUnPrensenceTopic() {
		   final String topic = topic_of_prensence.getText().toString().trim();
		   if (TextUtils.isEmpty(topic)) {
				Toast.makeText(APIActivity.this, "topic should not be null", Toast.LENGTH_SHORT).show();
				return;
			}
		   
		   setCostomMsg("UnSubscribePresence topic = " + topic);
		   YunBaManager.unsubscribePresence(getApplicationContext(), topic, new IMqttActionListener() {
			
			@Override
			public void onSuccess(IMqttToken arg) {
				String msg = "[Demo] UnSubscribePresence of topic = " + topic + " success "; 
				setCostomMsg(msg);					
			}
			
			@Override
			public void onFailure(IMqttToken arg, Throwable exception) {
				String msg = "[Demo] UnSubscribePresence of topic = " + topic + " failed : " + exception.getLocalizedMessage(); 
				setCostomMsg(msg);	
			}
		});
		
	}

	private void handleGetTopics() {
	    String alias = alias_of_topic.getText().toString().trim();
		if (TextUtils.isEmpty(alias)) {
			alias = null;
		}
		setCostomMsg("Get topic of to alias = " + alias);
		YunBaManager.getTopicList(getApplicationContext(), alias, new IMqttActionListener() {
			
			@Override
			public void onSuccess(IMqttToken arg) {
				String msg = "[Demo] Get topics of alias = " + arg.getAlias() + " success : " + arg.getResult();
				setCostomMsg(msg);				
			}
			
			@Override
			public void onFailure(IMqttToken arg, Throwable exception) {
				String msg = "[Demo] Get topics of alias = " + " failed : " + exception.getLocalizedMessage();
				setCostomMsg(msg);
			}
		});
	}

	private void handleGetAliasState() {
		final String alias = alias_txt.getText().toString().trim();
	
		if (TextUtils.isEmpty(alias)) {
			Toast.makeText(APIActivity.this, "Alias should not be null", Toast.LENGTH_SHORT).show();
			return;
		}
		setCostomMsg("Get state of to alias = " + alias);
		YunBaManager.getState(getApplicationContext(), alias, new IMqttActionListener() {
			
			@Override
			public void onSuccess(IMqttToken arg) {
				String msg = "[Demo] Get state of alias = " + alias + " success : " + arg.getResult();
				setCostomMsg(msg);				
			}
			
			@Override
			public void onFailure(IMqttToken arg0, Throwable exception) {
				String msg = "[Demo] Get state of alias = " + alias + " failed : " + exception.getLocalizedMessage();
				if(exception instanceof MqttException) {
					MqttException mqttException = (MqttException)exception;
					Log.e(TAG, "error code = " + mqttException.getReasonCode());
				}
				setCostomMsg(msg);
			}
		});
		
	}

	private void setCostomMsg(final String msg){
		 YunBaTabActivity.setCostomMsg(this, msg);
	}

	private void handlePublishAlias() {
		final String alias = alias_txt.getText().toString().trim();
		final String msg = alias_msg.getText().toString().trim();
		if (TextUtils.isEmpty(alias) || TextUtils.isEmpty(msg)) {
			Toast.makeText(APIActivity.this, "Alias and msg should not be null", Toast.LENGTH_SHORT).show();
			return;
		}
	
		setCostomMsg("Publish msg = " + msg + " to alias = " + alias);
		YunBaManager.publishToAlias(getApplicationContext(), alias, msg, new IMqttActionListener() {
			public void onSuccess(IMqttToken asyncActionToken) {

				String msgLog = "Publish alias succeed : " + alias;
				StringBuilder showMsg = new StringBuilder();
				showMsg.append("[Demo] publish alias msg")
						.append(" = ").append(msg).append(" to ")
						.append("alias").append(" = ").append(alias).append(" succeed");
				setCostomMsg(showMsg.toString());
				DemoUtil.showToast(msgLog, getApplicationContext());
			}
			
			@Override
			public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
				String msg = "[Demo] Publish alias = " + alias + " failed : " + exception.getMessage();
				setCostomMsg(msg);
				DemoUtil.showToast(msg, getApplicationContext());
				
			}
		});		
	}

	private void handleGetAlias(final Activity context) {
		  pd = ProgressDialog.show(APIActivity.this, null, "加载中，请稍后……"); 
		  String topic = alias_topic.getText().toString().trim();
		  if(DemoUtil.isEmpty(topic)) topic = "t1";
		YunBaManager.getAliasList(getApplicationContext(), topic, new IMqttActionListener() {
			
			@Override
			public void onSuccess(IMqttToken token) {
				 pd.dismiss();
			     Log.d(TAG, token.getResult().toString());
			     try {
			    	 JSONObject result = token.getResult();
			    	 JSONArray alias = result.getJSONArray("alias");
			    	 final String[] list = new String[alias.length()];
			    	 for(int i = 0; i < alias.length(); i++){
			    	     list[i] = (alias.getString(i));
			    	 }
			    	 context.runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							new AlertDialog.Builder(context).setTitle("选择 alias").setIcon(
								     android.R.drawable.ic_dialog_info).setSingleChoiceItems(
								     list, 0,
								     new DialogInterface.OnClickListener() {
								      public void onClick(DialogInterface dialog, int which) {
								    	  String selAlias =  list[which];
								    	  alias_txt.setText(selAlias.trim());
								    	  dialog.dismiss();
								      }
								     }).setNegativeButton("取消", null).show();
						}
					});
			     } catch (Exception e) {
			    	 e.printStackTrace();
			     }
				
			}
			
			@Override
			public void onFailure(IMqttToken arg0, Throwable arg1) {
				pd.dismiss();
				DemoUtil.showToast("get users failed", getApplicationContext());
				
			}
		});
	}
    
}
