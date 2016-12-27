package baizhuan.hangzhou.com.androidlibstudy.Rxjava;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import baizhuan.hangzhou.com.androidlibstudy.Https.HttpsUtils;
import baizhuan.hangzhou.com.androidlibstudy.Rxjava.model.BaseResult;
import baizhuan.hangzhou.com.androidlibstudy.util.CustomConverterFactory;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.androidlibstudy.RxjavaTest
 * Date:    2016-07-12
 * Time:    11:47
 * 类描述：联网请求类
 */
public class HttpAPI {
    static volatile Retrofit retrofit;
    static volatile HttpServices services;

    private static Retrofit getRetrofit() {
        if (retrofit == null) {
            synchronized (Retrofit.class) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl("http://www.52wzb.com/wzb2/")
                            .addConverterFactory(CustomConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .client(getClient())
                            .build();
                    return retrofit;
                }
            }
        }
        return retrofit;
    }

    public static OkHttpClient getClient() {
        HttpsUtils.SSLParams mSslParams = HttpsUtils.getSslSocketFactory(null, null, null);
        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .sslSocketFactory(mSslParams.sSLSocketFactory, mSslParams.trustManager)
                .build();

        return mOkHttpClient;

    }

    public static HttpServices getServices() {
        if (services == null) {
            synchronized (HttpServices.class) {
                if (services == null) {
                    services = getRetrofit().create(HttpServices.class);
                    return services;
                }
            }
        }
        return services;
    }


    public interface HttpServices {

        @POST("app/product/version2")
        Observable<BaseResult> vesion(@Query("version") String tel, @Query("type") String dlmm, @Query("qdsx") String qdsx);

        //        @GET("http://blog.csdn.net/chengyingzhilian/article/details/7279494")
        @GET("https://www.wenzhuanbao.com.cn/package.json")
        Observable<String> getHttpsHtml();

        @GET("http://121.8.249.13:8081/gdmsaec-app/act/AppUser/loginin")
        Observable<String> getTest(@Query("username") String username, @Query("password") String password );



    }


}
