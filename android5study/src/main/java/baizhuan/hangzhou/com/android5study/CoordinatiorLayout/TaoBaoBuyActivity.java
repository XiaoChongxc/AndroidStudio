package baizhuan.hangzhou.com.android5study.CoordinatiorLayout;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import baizhuan.hangzhou.com.android5study.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.android5study.CoordinatiorLayout
 * Date:    2016-07-14
 * Time:    16:57
 * 类描述： 沃商店  主页 滑动效果的  测试类
 * 主要是 coordinatorLayout   和 behavior  的一个 联动 demo  完成 了 简单的 功能
 */
public class TaoBaoBuyActivity extends Activity {

    @Bind(R.id.header)
    ImageView header;
    @Bind(R.id.img_home)
    ImageView imgHome;
    @Bind(R.id.tv_search)
    TextView tvSearch;
    @Bind(R.id.img_search)
    ImageView imgSearch;
    @Bind(R.id.toolbar_behavior)
    Toolbar toolbar;
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taobao);
        ButterKnife.bind(this);
        final String data[] = {"消灭星星", "QQ", "交友", "暑假游"};
        //3秒改变一下   textview 的内容
        Observable.interval(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        tvSearch.setText(data[position % data.length]);
                        position++;
                    }
                });
    }


}
