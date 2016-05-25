package baizhuan.hangzhou.com.gankcopy.http;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio
 * Package: baizhuan.hangzhou.com.gankcopy.http
 * Date:    2016-05-25
 * Time:    17:00
 * 类描述：
 */
public class RetrofitFactory   {
    public  static  Retrofit retrofit;
    public static final String GANHUO_API="http://gank.io/";

    public static Retrofit getRetrofit(){
        if (retrofit == null){
            synchronized (RetrofitFactory.class){
                if (retrofit == null){
                    retrofit = new Retrofit.Builder()
                            .baseUrl(GANHUO_API)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .build();
                }
            }
        }
        return retrofit;
    }
}
