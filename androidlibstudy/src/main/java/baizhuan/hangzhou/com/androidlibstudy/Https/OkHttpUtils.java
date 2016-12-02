package baizhuan.hangzhou.com.androidlibstudy.Https;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.androidlibstudy.Https
 * Date:    16-12-1.
 * Time:    下午4:42
 * 类描述：
 *
 * @vesion
 */

import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpUtils {

    public static Interceptor interceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            return originalResponse.newBuilder().removeHeader("Pragma")
                    .header("Cache-Control", String.format("max-age=%d", 60)).build();//设置了缓存时间为10秒
        }
    };
    private static String cachedirectory = Environment.getExternalStorageDirectory() + "/okhttp/caches";
    private static Cache cache = new Cache(new File(cachedirectory), 100 * 1024 * 1024);

    private static final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .cache(cache)
            .addInterceptor(interceptor)
            .build();

    public static final MediaType JSON = MediaType
            .parse("application/json; charset=utf-8");

    /**
     * 请求回调接口
     *
     * @author winfo-wj
     */
    public interface RequestCallBack {
        /**
         * 请求成功
         *
         * @param resultData 服务器返回的结果数据
         */
        void onSuccess(String resultData);

        /**
         * 请求失败
         *
         * @param error 错误信息
         */
        void onFailure(String error);
    }

    /**
     * 开启异步线程访问，访问结果自行处理
     *
     * @param url
     */
    public static void get(String url, final RequestCallBack requestCallBack) {
        Request.Builder builder = new Request.Builder().url(url).get();
        Request request = builder.build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onResponse(Call call, Response response)
                    throws IOException {
                requestCallBack.onSuccess(response.body().string());
            }

            @Override
            public void onFailure(Call cal, IOException e) {
                requestCallBack.onFailure(e.getMessage());
            }
        });
    }

    public static void post(String url, String json,
                            final RequestCallBack requestCallBack) {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(url).post(body).build();
        okHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(Call call, Response response)
                    throws IOException {
                requestCallBack.onSuccess(response.body().string());
            }

            @Override
            public void onFailure(Call call, IOException e) {
                requestCallBack.onFailure(e.getMessage());
            }
        });
    }


    public static void post(String url, RequestBody body, final RequestCallBack requestCallBack) {
        Request request = new Request.Builder().url(url).post(body).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onResponse(Call arg0, Response arg1) throws IOException {
                requestCallBack.onSuccess(arg1.body().string());

            }

            @Override
            public void onFailure(Call arg0, IOException arg1) {
                requestCallBack.onFailure(arg1.getMessage());
            }
        });
    }

}
