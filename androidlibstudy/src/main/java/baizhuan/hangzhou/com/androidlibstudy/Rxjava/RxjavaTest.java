package baizhuan.hangzhou.com.androidlibstudy.Rxjava;

import android.view.View;

import java.util.List;
import java.util.concurrent.TimeUnit;

import baizhuan.hangzhou.com.androidlibstudy.Rxjava.model.BaseResult;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.androidlibstudy.RxjavaTest
 * Date:    2016-07-13
 * Time:    09:54
 * 类描述： Rxjava  操作符练习
 *          filter()输出和输入相同的元素，并且会过滤掉那些不满足检查条件的。
 *          take()输出最多指定数量的结果。
 *          doOnNext()允许我们在每次输出一个元素之前做一些额外的事情
 *          timer() 延迟 时间后 执行
 *          interval()间隔固定时间 执行
 *
 */
public class RxjavaTest {
    static Observable mObservable;

    public static void main(String args[]) {
//        Rx_map();
//        Rx_ThrottleFirst();
//        Rx_ResultHandler();
//        Rx_Merge();

        Rx_Cache();
//        Rx_cache();
    }


    /***
     * 一个 通用的 observable
     *
     * @param observable
     * @param <T>
     * @return
     */
    <T> Observable<T> Rx_Comment(Observable<T> observable) {
        return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }


    static void Rx_map() {
        Rx_From()
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer o) {
                        return o.toString();
                    }
                })
                .buffer(3)
//                .compose(Rx_Transformer())        加上转换器， 把 一些通用的代码写在这里
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onError");
                    }

                    @Override
                    public void onNext(List<String> strings) {
                        System.out.println("onNext");
                        for (String str : strings) {
                            System.out.println("【" + str + "】，");
                        }

                    }
                });
    }


    /***
     * Rxjava  对结果的处理  filter  对结果过滤 ，  不满足   直接  completed   满足 就 onNext   completed
     */
    static void Rx_ResultHandler() {
        System.out.println("Rx_ResultHandler");
        HttpAPI.getServices().vesion("2.1.6", "Android", "OPPO")
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        System.out.println("对话框显示");
                    }
                })
                .filter(new Func1<BaseResult, Boolean>() {
                    @Override
                    public Boolean call(BaseResult stringBaseResult) {
                        System.out.println("【onCompleted】，msg:" + stringBaseResult.getMsg() + "list:" + stringBaseResult.getList() + "code:" +
                                stringBaseResult.getResult());
                        return "01".equals(stringBaseResult.getResult()) ? true : false;
                    }
                })
                .subscribe(new Subscriber<BaseResult>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("【onCompleted】，");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("【onError】，" + e.getMessage());
                    }

                    @Override
                    public void onNext(BaseResult stringBaseResult) {
                        System.out.println("【onNext】，" + stringBaseResult);
                    }
                });
    }


    /***
     * Merge  合并两个 Observable   被观察者， 一起处理数据
     */
    static void Rx_Merge() {
        System.out.println("【Rx_Merge】，");
        Observable observable1 = Observable.just("123456", "aaabbb");
        Observable observable2 = Observable.just("bbbbbbbb", "=====");
        Observable.merge(observable1, observable2).subscribe(new Subscriber() {
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

    /***
     * 多级缓存，  三个 observable  分别 来处理  获取 缓存， 硬盘，网络的 数据，  和first 配合使用，  如果 有一个 获取到了， 后面的不会执行
     * 使用 java 调用， 和 在activity中  显示 ， 结果会有差异，  java的 结果显示不全
     */
    static void Rx_Cache() {
        System.out.println("【Rx_Cache】，");
        final String memoryCache = "memoryCache";
        final String diskCache = "diskCache";
        Observable memory = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                System.out.println("【memory】，");
                if (memoryCache == null) {
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
                if (memoryCache == null) {
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

    static void Rx_cache() {
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

    /**
     * 转换器，  把 原始的 Observable  进行 变化， 封装
     *
     * @param <T>
     * @return
     */
    static <T> Observable.Transformer<T, T> Rx_Transformer() {

        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /***
     * 使用 from  方式 生成一个 被观察者，
     * from  从一个 数组  或者 Iterable  futrue 里面 循环 读取数据源
     *
     * @return
     */
    static Observable Rx_From() {
        return Observable.from(new Integer[]{1, 2, 3, 4, 5, 6, 7});
    }

    /***
     * 依次 执行 just 里面的 内容
     *
     * @return
     */
    static Observable Rx_Just() {
        return Observable.just("aaaa", 111, false, (char) 11);
    }


    /***
     * throttleFirst  会丢弃 在 给定时间内的 请求
     */
    static void Rx_ThrottleFirst() {
        Observable.create(new Observable.OnSubscribe<String>() {
                              @Override
                              public void call(Subscriber<? super String> subscriber) {
                                  //给view 设置一个点击事件， 然后 view 点击事件里面放 next 方法，  如果 出发点击事件， 就会出发 被观察者
                                  System.out.println("第一次点击" + System.currentTimeMillis());
                                  subscriber.onNext(null);
                                  try {
                                      Thread.sleep(200);  //休息0.2s
                                      System.out.println("第二次误点击" + System.currentTimeMillis());
                                      subscriber.onNext(null);

                                  } catch (InterruptedException e) {
                                      e.printStackTrace();
                                  }
                              }
                          }

        ).throttleFirst(500, TimeUnit.SECONDS)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println("打开了一个页面" + System.currentTimeMillis());
                    }
                });
//        or
        Observable.create(new ViewClickOnSubscribe(new View(null)))
                .throttleFirst(500, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        System.out.println("打开了一个页面" + System.currentTimeMillis());
                    }
                });
    }

}
