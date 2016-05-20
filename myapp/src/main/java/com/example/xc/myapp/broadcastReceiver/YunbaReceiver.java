package com.example.xc.myapp.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.example.xc.myapp.MyApplication;
import com.example.xc.myapp.dao.PublicFunction;
import com.example.xc.myapp.util.DemoUtil;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttException;

import io.yunba.android.manager.YunBaManager;

/**
 * Author:  @just_for_happy 开心就好
 * Date: 2016-03-01
 * Time: 14:33
 * FIXME
 */
public class YunbaReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (YunBaManager.MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {


            //在这里处理从服务器发布下来的消息， 比如显示通知栏， 打开 Activity 等等
//            StringBuilder showMsg = new StringBuilder();
//            showMsg.append("Received message from server: ")
//                    .append(YunBaManager.MQTT_TOPIC)
//                    .append(" = ")
//                    .append(topic)
//                    .append(" ")
//                    .append(YunBaManager.MQTT_MSG)
//                    .append(" = ")
//            .append(msg);
//            DemoUtil.showNotifation(context, topic, msg);
            //接受到消息 就发通知到 t1 频道

//            LiteOrm liteOrm =LiteOrm.newSingleInstance(context, context.getString(R.string.datebase_name));
//            ArrayList<DayActivityBean> list= liteOrm.query(DayActivityBean.class);
//            liteOrm.close();
//
//            DayActivityBean info =list.get(list.size()-1);
//            for (DayActivityBean info : list){
//                sb.append(info.getDatetime()+"\n我在"+info.getLocation()+"纬度"+info.getLatitude()+"经度"+info.getLongitude()+
//                        "手机正在运行的程序有：\n"+info.getUsingApp()+"\n");
//                if(! "".equals(info.getToPhone())  && info.getToPhone()!=null){
//                    //当时正在打电话
//                    sb.append("我当时正在给"+info.getToPhone()+"打电话");
//                }
//                if(!"".equals(info.getFromMessage())){
//                    sb.append("我收到了一条信息"+info.getFromMessage());
//                }
//            }
            //一次能够发送500个字符
//            StringBuffer sb=new StringBuffer(   new Gson().toJson(info));
//            StringBuffer sb=new StringBuffer(info.getLatitude()+"***"+info.getLongitude()+"--"+info.getLocation());
//            int maxLength= sb.length();
//            if(maxLength>500){
//                for (int i=490; i<maxLength ;i+=490){
//                    if(i<maxLength){
//                        String message =sb.substring(i-490, i );
//                        publish(context,"aaa",message);
//                    }
//                }
//            }else{
//                String message =sb.substring(0, maxLength );
//                publish(context,"aaa",message);
//            }



            handMessage(context,intent);


        }
    }

    /**接受到服务器传来的消息 ，进行解析
     * t1  发送  广播
     * t2 接受广播
     *
     * **/
    private void  handMessage( Context ctx,Intent intent){
        String topic = intent.getStringExtra(YunBaManager.MQTT_TOPIC);
        String msg = intent.getStringExtra(YunBaManager.MQTT_MSG);

        if(msg.contains( PublicFunction.SendMessage)  && topic.equals("t2")){
            String strs[] =msg.split("@@@@");
            if(strs.length<2) return;
            if(strs[1].equals(PublicFunction.getUid(ctx))) return;
            //收到请求位置信息的请求
            //发送位置信息
           PoiItem poiItem= MyApplication.getInstance().getMyLocation();
            String location= poiItem.getTitle();
            double latitude =poiItem.getLatLonPoint().getLatitude();
            double longitude =poiItem.getLatLonPoint().getLongitude();
            String  str= location+"@@@"+latitude+"@@@"+longitude+"@@@"+PublicFunction.getUid(ctx);
            publish(ctx,"t1",str);

        }else if(msg.equals("2")){
            //

        }
        else{  //收到发送的  位置信息
            Log.i("TAG","收到了发送过来的位置信息"+msg);
          String [] str=   msg.split("@@@");
            if(str.length<4) return ;
            if(str[3].equals(PublicFunction.getUid(ctx))) return ;      //同一个手机 不用收到自己的消息
            PoiItem item =new PoiItem(str[0],new LatLonPoint(string2double(str[1]),string2double(str[2])),str[0],str[0]);
            MyApplication.getInstance().setPersonLocation(item);
            //发消息 提醒 自己 更新页面
            Intent data=new Intent(PublicFunction.MY_CUSTOM_BROADCAST);
            ctx.sendBroadcast(data);

        }


    }


    public void publish(final Context ctx,String topic,String message){
        YunBaManager.publish(ctx,topic ,message ,
                new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {
                        String topic = DemoUtil.join(asyncActionToken.getTopics(), ", ");
                        String msgLog = "Publish succeed : " + topic;
//                        DemoUtil.showToast(msgLog, ctx);
                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                        if (exception instanceof MqttException) {
                            MqttException ex = (MqttException)exception;
                            String msg =  "publish failed with error code : " + ex.getReasonCode();
//                            DemoUtil.showToast(msg, ctx);
                        }
                    }
                }
        );
    }

    private   double string2double(String str){
        double  b= 0;
        try{
            b=Double.parseDouble(str);
        }catch (Exception e){
            b=0;
        }
        return b;


    }

}
