package TestForHttp;/**
 * Created by  @just_for_happy (@开心就好)   on 2015/12/28.
 * Date: 2015-12-28
 * Time: 15:44
 * Usege: 功能描述。。。网络请求的 测试类
 */

import android.app.Activity;
import android.os.Bundle;

import com.example.administrator.mytest.R;

import TestForHttp.Bean.CommonResultBean;
import TestForHttp.Dao.LoginDao;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * author: just_for_happy(@开心就好)
 * Date: 2015-12-28
 * Time: 15:44
 * FIXME
 */
public class TestForHttp extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testforhttp);
    }

    /**
     * 登录
     */
    private void getLogin() {
        LoginDao.getDaoService(this).doLogin("13712345612", "123456").
                subscribeOn(Schedulers.io())        //指定一个 序列 操作
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {            //调用的时候   触发事件

                    }
                }).filter(new Func1<CommonResultBean, Boolean>() {
            @Override
            public Boolean call(CommonResultBean commonResultBean) {        //对结果进行过滤，  true 为 通过， false为不通过
                return true;
            }
        }).observeOn(AndroidSchedulers.mainThread())        //切换 线程用的 ，可以切换后面的操作所处的线程
                .subscribe(
                        new Observer<CommonResultBean>() {
                            @Override
                            public void onCompleted() {
                                //请求完成

                            }

                            @Override
                            public void onError(Throwable e) {
                                //错误结果 或者 被 过滤的结果
                            }

                            @Override
                            public void onNext(CommonResultBean commonResultBean) {
                                //成功之后的操作
                            }
                        }
                );

    }

}
