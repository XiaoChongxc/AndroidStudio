package baizhuan.hangzhou.com.androidlibstudy.Https;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import baizhuan.hangzhou.com.androidlibstudy.BaseActivity;
import baizhuan.hangzhou.com.androidlibstudy.R;
import baizhuan.hangzhou.com.androidlibstudy.Rxjava.HttpAPI;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.androidlibstudy.Https
 * Date:    16-11-11.
 * Time:    下午2:48
 * 类描述：  https 测试类
 *
 * @vesion
 */
public class HttpsActivity extends BaseActivity {

    @Bind(R.id.btn_connect)
    Button btnConnect;
    @Bind(R.id.tv_content)
    TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_https);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_connect)
    void onConnection() {
//        Https 测试类
        HttpAPI.getServices().getHttpsHtml()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        tvContent.setText(e.getMessage());

                    }

                    @Override
                    public void onNext(String s) {
                        tvContent.setText(s);
                    }
                });
//        RequestBody body = new FormBody.Builder().add("username", "15012551396").add("password", "123456").build();
//        OkHttpUtils.post("http://121.8.249.13:8081/gdmsaec-app/act/AppUser/loginin", body, new OkHttpUtils.RequestCallBack() {
//            @Override
//            public void onSuccess(String resultData) {
//                try {
//                    System.out.println(resultData);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    System.out.println("登陆出错" + e.getMessage());
//                }
//            }
//
//            @Override
//            public void onFailure(String error) {
//                System.out.println("登陆出错" + error);
//            }
//        });

//        String url = "https://kyfw.12306.cn/otn/";
//        String url = "http://blog.csdn.net/chengyingzhilian/article/details/7279494";
//        HttpsUtils.SSLParams mSslParams = HttpsUtils.getSslSocketFactory(null, null, null);
//
//        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
//                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
//                .readTimeout(10000L, TimeUnit.MILLISECONDS)
//                .hostnameVerifier(new HostnameVerifier() {
//                    @Override
//                    public boolean verify(String hostname, SSLSession session) {
//                        return true;
//                    }
//                })
//                .sslSocketFactory(mSslParams.sSLSocketFactory, mSslParams.trustManager)
//                .build();
//        Request mRequest = new Request.Builder().get().
//                url(url).build();
//
//        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                tvContent.setText("链接失败 ！" + e.getMessage());
//            }
//
//            @Override
//            public void onResponse(Call call, final Response response) throws IOException {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        ResponseBody responseBody = response.body();
//                        try {
//                            tvContent.setText("我是获取的内容：" + responseBody.string());
//                        } catch (Exception e) {
//
//                        }
//                    }
//                });
//
//            }
//        });
    }
}
