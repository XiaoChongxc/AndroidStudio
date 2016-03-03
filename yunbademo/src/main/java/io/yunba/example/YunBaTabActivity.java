package io.yunba.example;


import android.R.string;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.xc.myapp.R;

import io.yunba.android.manager.YunBaManager;


public class YunBaTabActivity extends TabActivity {
	protected static final String TAG = "YunBaTabActivity";
	private TabHost mTabHost;
	public static TextView msg_log;
	public static ScrollView scroll;
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.tab);//这里使用了上面创建的xml文件（Tab页面的布局）
	    Resources res = getResources(); // Resource object to get Drawables
	    TabHost tabHost = getTabHost();  // The activity TabHost
	    TabSpec spec;
	    Intent intent;  // Reusable Intent for each tab
 
	  //第一个TAB
	    intent = new Intent(this,MainActivity.class);//新建一个Intent用作Tab1显示的内容
	    spec = tabHost.newTabSpec("Main")//新建一个 Tab
	    .setIndicator("Main", res.getDrawable(android.R.drawable.ic_media_play))//设置名称以及图标
	    .setContent(intent);//设置显示的intent，这里的参数也可以是R.id.xxx
	    tabHost.addTab(spec);//添加进tabHost
 
	    //第二个TAB
	    intent = new Intent(this, APIActivity.class);//第二个Intent用作Tab1显示的内容
	    spec = tabHost.newTabSpec("API")//新建一个 Tab
	    .setIndicator("API", res.getDrawable(android.R.drawable.ic_menu_camera))//设置名称以及图标
	    .setContent(intent);//设置显示的intent，这里的参数也可以是R.id.xxx
	    tabHost.addTab(spec);//添加进tabHost
 
	    tabHost.setCurrentTab(0);
	    
	    initView();
		
	
    }



	private void initView() {
		msg_log = (TextView)findViewById(R.id.msg_log);
		msg_log.setMovementMethod(ScrollingMovementMethod.getInstance());
	
		msg_log.setBackgroundResource(R.drawable.text_view_border); 
		msg_log.setMaxLines(300);
	
		scroll = (ScrollView) findViewById(R.id.scroller);
		int outerHeight = getResources().getDisplayMetrics().heightPixels- (int) (25 * getResources().getDisplayMetrics().density);
//	   
		outerHeight = (int) (outerHeight*0.3);
		scroll = (ScrollView) findViewById(R.id.scroller);
		scroll.getLayoutParams().height = outerHeight;
	}
	
	
	
	public static void setCostomMsg(Activity context, final String msg){
		 if (null != msg_log) {
			 context.runOnUiThread(new Runnable() {
		            @Override
		            public void run() {
		            	msg_log.append(msg+"\r\n");
		    		    if (null != scroll) {		    	
		    		    	scroll.fullScroll(View.FOCUS_DOWN);
		    		    }
		            }
		     });
        }
			
	}
	
	public static void setTitle(final string status) {
		setTitle(status);
	}
	
	//only for Test
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
        case R.id.action_settings:
        	final EditText txtBroker = new EditText(this);
        	txtBroker.setHint("192.168.2.106");
        	new AlertDialog.Builder(this).setTitle("请输入").
        	setIcon(android.R.drawable.ic_dialog_info).setView(txtBroker)
        	.setPositiveButton("确定", new OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
						    String broker = txtBroker.getText().toString().trim();
						    Log.i(TAG, "Broker ip = " +  broker);
				 		     if(!DemoUtil.isEmpty(broker)){
						    	 YunBaManager.setBroker(getApplicationContext(), "tcp://"+ broker+":1883");
				 		     } else {
				 		    	YunBaManager.setBroker(getApplicationContext(), null);
				 		     }
							
						}
					})
        			.setNegativeButton("取消", null).show();

     }
	 return super.onOptionsItemSelected(item);
	}
}
