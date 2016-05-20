package com.example.administrator.mytest.TestForHttp.Dao;/**
 * Created by  @just_for_happy (@开心就好)   on 2015/12/28.
 * Date: 2015-12-28
 * Time: 16:54
 * Usege: 功能描述。。。
 */

import com.example.administrator.mytest.TestForHttp.Bean.CommonResultBean;
import com.example.administrator.mytest.TestForHttp.Bean.MemberInfoBean;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * author: just_for_happy(@开心就好)
 * Date: 2015-12-28 
 * Time: 16:54 
 * FIXME 
 */
public class MemberDao {




    public interface MemberService{

        /**获取到会员信息*/
        @GET("getMemberInfo.ashx")
        Observable<CommonResultBean<MemberInfoBean>>getMemberInfo(@Query("access_token")  String access_token);

    }

}
