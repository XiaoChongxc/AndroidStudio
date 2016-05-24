package com.example.administrator.mytest.TestForHttp.Dao;/**
 * Created by  @just_for_happy (@开心就好)   on 2015/12/28.
 * Date: 2015-12-28
 * Time: 16:35
 * Usege: 功能描述。。。
 */

import android.content.Context;

import java.util.Map;

import com.example.administrator.mytest.TestForHttp.Bean.CommonResultBean;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;
import retrofit.http.QueryMap;

/**
 * author: just_for_happy(@开心就好)
 * Date: 2015-12-28 
 * Time: 16:35 
 * FIXME Dao 层   一般都是有一个接口 ，接口里面是 需要实现的业务
 */

public class LoginDao {

private static  volatile LoginService loginService;

    public static LoginService getDaoService(Context ctx){
        if(loginService==null){
            synchronized (LoginService.class){
                if(loginService==null){
                    loginService=RetrofitServiceFactory.crateRetrofitService(LoginService.class,"http://slbapp.ccclubs.com/app/official/");
                }
            }
        }
        return loginService;
    }


    /**请求的接口*/
    public interface LoginService{

        @GET("login.ashx")
        rx.Observable<CommonResultBean>doLogin(@Query("username") String username,@Query("password")String pwd);

        @POST("login.ashx" )
        rx.Observable<CommonResultBean> doLogin(@QueryMap Map<String, String> options);

    }




}
