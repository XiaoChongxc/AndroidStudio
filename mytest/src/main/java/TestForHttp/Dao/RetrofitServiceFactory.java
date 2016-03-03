package TestForHttp.Dao;/**
 * Created by  @just_for_happy (@开心就好)   on 2015/12/28.
 * Date: 2015-12-28
 * Time: 16:31
 * Usege: 功能描述。。。
 */

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * author: just_for_happy(@开心就好)
 * Date: 2015-12-28 
 * Time: 16:31 
 * FIXME 
 */
public class RetrofitServiceFactory  {
    /**
     * 创建RetrofitService
     * @param clazz RetrofitService 对应实例
     * @param baseURL baseUrl
     * @param <T>
     * @return  返回一个 service
     */
    public static <T> T crateRetrofitService(Class<T> clazz ,String baseURL){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(baseURL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
            return retrofit.create(clazz);
    }

}
