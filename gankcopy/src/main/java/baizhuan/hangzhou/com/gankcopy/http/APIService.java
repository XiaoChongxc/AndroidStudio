package baizhuan.hangzhou.com.gankcopy.http;

import baizhuan.hangzhou.com.gankcopy.model.GanHuo;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio
 * Package: baizhuan.hangzhou.com.gankcopy.http
 * Date:    2016-05-25
 * Time:    17:01
 * 类描述：公共 的 联网数据接口
 */
public class APIService {

    private static volatile GankService mGankService;

    public interface GankService {
        @GET("api/data/{type}/{count}/{page}")
        Observable<GanHuo> getData(@Path("type") String type,
                                   @Path("count") int count,
                                   @Path("page") int page);
    }


    public static GankService getGankService() {
        if (mGankService == null) {
            synchronized (GankService.class) {
                if (mGankService == null) {
                    return RetrofitFactory.getRetrofit().create(GankService.class);
                }
            }
        }
        return mGankService;
    }

}
