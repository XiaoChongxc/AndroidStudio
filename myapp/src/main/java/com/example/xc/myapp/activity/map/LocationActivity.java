package com.example.xc.myapp.activity.map;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.AMapUtils;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.amap.api.maps2d.model.PolylineOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.example.xc.myapp.MyApplication;
import com.example.xc.myapp.R;
import com.example.xc.myapp.activity.BaseActivity;
import com.example.xc.myapp.dao.PublicFunction;
import com.example.xc.myapp.util.DemoUtil;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.yunba.android.manager.YunBaManager;

/**
 * Author:  @just_for_happy 开心就好
 * Date: 2016-03-01
 * Time: 17:42
 * FIXME  查看我的位置和  对方的位置
 */
public class LocationActivity extends BaseActivity implements LocationSource,
        AMapLocationListener {
    @Bind(R.id.btn_getlocation)
    Button btnGetlocation;
    @Bind(R.id.tv_distance)
    TextView tvDistance;
    @Bind(R.id.tv_location)
    TextView tvLocation;
    private AMap aMap;
    private MapView mapView;
    private OnLocationChangedListener mListener;
    LocationManagerProxy mLocationManagerProxy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        ButterKnife.bind(this);

        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        init();
        //注册广播
        IntentFilter filter = new IntentFilter();
        filter.addAction(PublicFunction.MY_CUSTOM_BROADCAST);
        registerReceiver(broadcastReceiver, filter);


    }

    @OnClick(R.id.btn_getlocation)
    void getLocation() {


        publish(this, "t2", PublicFunction.SendMessage + "@@@@" + PublicFunction.getUid(LocationActivity.this));
    }

    public void publish(final Context ctx, String topic, String message) {
        YunBaManager.publish(ctx, topic, message,
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
                            MqttException ex = (MqttException) exception;
                            String msg = "publish failed with error code : " + ex.getReasonCode();
//                            DemoUtil.showToast(msg, ctx);
                        }
                    }
                }
        );
    }

    /**
     * 初始化AMap对象
     */
    private void init() {
        if (aMap == null) {
            aMap = mapView.getMap();
            setUpMap();
        }
    }

    /**
     * 设置一些amap的属性
     */
    private void setUpMap() {
        // 自定义系统定位小蓝点
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory
                .fromResource(R.mipmap.icon_marker));// 设置小蓝点的图标
        myLocationStyle.strokeColor(Color.TRANSPARENT);// 设置圆形的边框颜色
        myLocationStyle.radiusFillColor(Color.TRANSPARENT);// 设置圆形的填充颜色
        // myLocationStyle.anchor(int,int)//设置小蓝点的锚点
        myLocationStyle.strokeWidth(1.0f);// 设置圆形的边框粗细
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.setLocationSource(this);// 设置定位监听
        aMap.moveCamera(CameraUpdateFactory.zoomTo(15));
        aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        // aMap.setMyLocationType()
    }

    /**
     * 画出对方的位置点
     */
    private void drawLocation() {
        PoiItem poiItem = MyApplication.getInstance().getPersonLocation();
        if (poiItem == null) return;
        LatLonPoint latLonPoint = poiItem.getLatLonPoint();
        LatLng latLng = new LatLng(latLonPoint.getLatitude(), latLonPoint.getLongitude());

        aMap.addMarker(new MarkerOptions().position(latLng).icon(
                BitmapDescriptorFactory.fromResource(R.mipmap.location_marker))
                .title(poiItem.getSnippet()))
                .setTitle(poiItem.getSnippet());

        PoiItem poiItem2 = MyApplication.getInstance().getMyLocation();
        if (poiItem2 == null) return;
        LatLonPoint latLonPoint2 = poiItem2.getLatLonPoint();
        LatLng latLng2 = new LatLng(latLonPoint2.getLatitude(), latLonPoint2.getLongitude());


        aMap.addPolyline((new PolylineOptions())
                .add(latLng, latLng2)
                .geodesic(true).color(Color.RED));
        float s = AMapUtils.calculateLineDistance(latLng, latLng2);
        String distance;
        if (s > 1000)
            distance = s / 1000 + "km";
        else
            distance = s + "m";
        tvDistance.setText("2人相距" + distance);

        tvLocation.setText("我在"+poiItem.getTitle());
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
        deactivate();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        deactivate();
        if(broadcastReceiver!=null)
           unregisterReceiver(broadcastReceiver);

    }

    /**
     * 定位成功后回调函数
     */
    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (mListener != null && amapLocation != null) {
            if (amapLocation != null && amapLocation.getAMapException().getErrorCode() == 0) {
                aMap.removecache();
                mListener.onLocationChanged(amapLocation);// 显示系统小蓝点
                drawLocation(); //画出对方位置
            } else {
                String errText = "定位失败," + amapLocation.getAMapException().getErrorCode() + ": " + amapLocation.getAMapException().getErrorMessage();
                Log.e("AmapErr", errText);
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    /**
     * 激活定位
     */
    @Override
    public void activate(OnLocationChangedListener listener) {
        mListener = listener;

        mLocationManagerProxy = LocationManagerProxy
                .getInstance(LocationActivity.this);
        // 混合定位
        mLocationManagerProxy.setGpsEnable(false);      //不使用gps定位
        mLocationManagerProxy.requestLocationData(      //30s  定位一次
                LocationProviderProxy.AMapNetwork, 30 * 1000, 15, this);
    }

    /**
     * 停止定位
     */
    @Override
    public void deactivate() {
        mListener = null;
        if (mLocationManagerProxy != null) {
            mLocationManagerProxy.removeUpdates(this);
        }
        mLocationManagerProxy = null;

    }


    private MyCustomBroadcastReceiver broadcastReceiver;

    public class MyCustomBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            drawLocation(); //画出对方位置
        }
    }
}
