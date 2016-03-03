package com.example.administrator.mytest.TestForRx;/**
 * Created by  @just_for_happy (@开心就好)   on 2016/1/7.
 * Date: 2016-01-07
 * Time: 16:45
 * Usege: 功能描述。。。
 */

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.administrator.mytest.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * author: just_for_happy(@开心就好)
 * Date: 2016-01-07
 * Time: 16:45
 * FIXME
 */
public class TestForRx extends Activity   {

    @Bind(R.id.btn_1)
    Button btn1;    //防止手抖点击多次
    @Bind(R.id.btn_2)
    Button btn2;//检查缓存
    @Bind(R.id.btn_3)
    Button btn3;//多个请求完成之后 在做操作
    @Bind(R.id.btn_4)
    Button btn4;//一个接口的请求依赖另一个API请求返回的数据
    @Bind(R.id.btn_5)
    Button btn5;//响应式的界面
    @Bind(R.id.btn_6)
    Button btn6;//复杂的数据变换

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_rx);
        ButterKnife.bind(this);
        String  str[]={"aaa","cccc","bbbb","eeeee","ddddd"};
        Observable.from(str).subscribeOn(Schedulers.newThread()).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.i("tag", "-------------111---------" + s + "-------------------");
            }
        });

        Observable observable= Observable.create(new Observable.OnSubscribe<String>(){
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("aaaa");
                subscriber.onNext("bbb");
                subscriber.onNext("cccc");
                subscriber.onCompleted();
            }
        });

        Observer observer=new Observer() {
            @Override
            public void onCompleted() {
                Log.i("tag","------------222----------onCompleted-------------------");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {
                Log.i("tag","------------222----------"+o.toString()+"-------------------");
            }
        };

        observable.subscribe(observer);
        Log.i("tag", "-------------------------------------------------------------------");
        observable.just("1111", "22222", "33333");
        observable.subscribe(observer);

        observable.just("111","2222","3333","4444")
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        return Integer.parseInt(s);
                    }
                })
                .observeOn(Schedulers.newThread())
                .subscribe(observer);

    User [] users= {  new User("张三","1223"), new User("张四","1223"), new User("张五","1223"), new User("张⑥","1223") };

       Observable.from(users).flatMap(new Func1<User, Observable<String>>() {
           @Override
           public Observable<String> call(final User user) {
               Observable<String>o1=Observable.create(new Observable.OnSubscribe<String>() {
                   @Override
                   public void call(Subscriber<? super String> subscriber) {
                       subscriber.onNext(user.userName+"处理了一下");
                   }
               });
               return   o1;
           }
       }) .subscribe(observer);

    }


    private class User {
        public User(String userName,String userPwd){
            this.userName=userName;
            this.userPwd=userPwd;
        }
        String  userName;
        String  userPwd;

    }

}
