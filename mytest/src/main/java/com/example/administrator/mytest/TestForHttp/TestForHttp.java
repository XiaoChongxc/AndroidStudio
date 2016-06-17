package com.example.administrator.mytest.TestForHttp;/**
 * Created by  @just_for_happy (@开心就好)   on 2015/12/28.
 * Date: 2015-12-28
 * Time: 15:44
 * Usege: 功能描述。。。网络请求的 测试类
 */

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.mytest.R;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.http.GET;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * author: just_for_happy(@开心就好)
 * Date: 2015-12-28
 * Time: 15:44
 * FIXME
 */
public class TestForHttp extends Activity {

    static String TAG = "TestForHttp2";
    @Bind(R.id.btn_login)
    Button btnLogin;
    @Bind(R.id.layout)
    LinearLayout layout;
    @Bind(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testforhttp);
        ButterKnife.bind(this);
    }

    static OkHttpClient getHttpClient() {
        OkHttpClient client = new OkHttpClient();
        client.networkInterceptors().add(new CustomInterceptor());
        return client;
    }

    static class CustomInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Request compressedRequest = request.newBuilder()
                    .build();
            Response response = chain.proceed(compressedRequest);
            //这里 可以 获取到   返回的结果值

//            System.out.print(response.body().string());
//            System.out.print(new String(response.body().string().getBytes(),"gbk"));
            //保存cookie  headers  所有的head内容
            return response;
        }
    }

    public  void OkHttp(String baseURL) {
        tv.setText("");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(CustomConverterFactory.create())
                .client(getHttpClient())
                .build();


        Subscription sub=retrofit.create(TextApi.class).GetunJsonData()
                .subscribeOn(Schedulers.io())        //指定一个 序列 操作
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<Sond>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());

                    }

                    @Override
                    public void onNext(Sond sond) {
                        System.out.print(sond);
                        tv.setText(sond.toString());
                        Log.d(TAG, "onNext: ");
                    }
                });
        //取消这个 订阅
//        sub.unsubscribe();

    }

    private interface TextApi {
        @GET("  ")
        Observable<Sond> GetunJsonData();
    }


    /**
     * 登录
     */
    @OnClick(R.id.btn_login)
    void getLogin() {
        OkHttp("http://music.qq.com/musicbox/shop/v3/data/hit/hit_newsong.js");

//        LoginDao.getDaoService(this).doLogin("13712345612", "123456").
//                subscribeOn(Schedulers.io())        //指定一个 序列 操作
//                .doOnSubscribe(new Action0() {
//                    @Override
//                    public void call() {            //调用的时候   触发事件
//
//                    }
//                }).filter(new Func1<CommonResultBean, Boolean>() {
//            @Override
//            public Boolean call(CommonResultBean commonResultBean) {        //对结果进行过滤，  true 为 通过， false为不通过
//                return true;
//            }
//        }).observeOn(AndroidSchedulers.mainThread())        //切换 线程用的 ，可以切换后面的操作所处的线程
//                .subscribe(
//                        new Observer<CommonResultBean>() {
//                            @Override
//                            public void onCompleted() {
//                                //请求完成
//
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//                                //错误结果 或者 被 过滤的结果
//                            }
//
//                            @Override
//                            public void onNext(CommonResultBean commonResultBean) {
//                                //成功之后的操作
//                            }
//                        }
//                );

    }

}
