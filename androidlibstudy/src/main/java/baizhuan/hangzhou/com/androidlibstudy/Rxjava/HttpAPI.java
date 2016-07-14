package baizhuan.hangzhou.com.androidlibstudy.Rxjava;

import baizhuan.hangzhou.com.androidlibstudy.Rxjava.model.BaseResult;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
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
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .build();
                    return retrofit;
                }
            }
        }
        return retrofit;
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

    }


}
