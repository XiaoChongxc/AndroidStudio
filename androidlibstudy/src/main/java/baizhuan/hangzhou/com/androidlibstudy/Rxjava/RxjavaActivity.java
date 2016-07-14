package baizhuan.hangzhou.com.androidlibstudy.Rxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import baizhuan.hangzhou.com.androidlibstudy.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.androidlibstudy.RxjavaTest
 * Date:    2016-07-12
 * Time:    10:20
 * 类描述：     RxjavaTest  的 操作符学习
 */
public class RxjavaActivity extends AppCompatActivity {

    @Bind(R.id.btn_connect)
    Button btnConnect;
    @Bind(R.id.tv_result)
    TextView tvResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        ButterKnife.bind(this);

    }


    @OnClick(R.id.btn_connect)
    void text() {
//        HttpAPI.getServices().vesion("", "", "")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer() {
//                    @Override
//                    public void onCompleted() {
//                        Toast.makeText(RxjavaActivity.this, "Completed", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Toast.makeText(RxjavaActivity.this, "onError", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onNext(Object o) {
//                        Toast.makeText(RxjavaActivity.this, "onNext", Toast.LENGTH_SHORT).show();
//                    }
//                });
//        Rx_cache();
        Rx_Cache();
    }

    public static void main(String args[]) {
//        Rx_map();
//        Rx_ThrottleFirst();
//        Rx_ResultHandler();
//        Rx_Merge();

        Rx_Cache();
//        Rx_cache();
    }
  static   void Rx_Cache() {
        System.out.println("【Rx_Cache】，");
        final String memoryCache = null;
        final String diskCache = "diskCache";
        Observable memory = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                System.out.println("【memory】，");
                if (memoryCache != null) {
                    subscriber.onNext(memoryCache);
                } else {
                    subscriber.onCompleted();
                }

            }
        });
        Observable disk = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                System.out.println("【disk】，");
                if (diskCache != null) {
                    subscriber.onNext(diskCache);
                } else {
                    subscriber.onCompleted();
                }

            }
        });
        Observable network = Observable.just("netWork");
        Observable.concat(memory, disk)
                .first()
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber() {
                    @Override
                    public void onCompleted() {
                        System.out.println("【onCompleted】，");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("【onError】，" + e.getMessage());
                    }

                    @Override
                    public void onNext(Object o) {
                        System.out.println("【onNext】，" + o.toString());
                    }
                });
    }

    void Rx_cache() {
        final String data[] = {null, null, "network"};
//        final String data[] = {null, "disk", "network"};
//        final String data[] = {"memory", "disk", "network"};
        Observable<String> memorySource = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                String d = data[0];
                System.out.println("----start check memory data. value is null? " + (d == null));
                if (d != null) {
                    subscriber.onNext(d);
                }
                subscriber.onCompleted();
            }
        });
        Observable<String> diskSource = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                String d = data[1];
                System.out.println("----start check disk data. value is null? " + (d == null));
                if (d != null) {
                    subscriber.onNext(d);
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io());
        Observable<String> networkSource = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                String d = data[2];
                System.out.println("----start check network data. value is null? " + (d == null));
                if (d != null) {
                    subscriber.onNext(d);
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io());

        Observable.concat(memorySource, diskSource, networkSource)
                .first()
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println("【call】，" + s.toString());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        System.out.println("Error: " + throwable.getMessage());
                    }
                });


    }


}
