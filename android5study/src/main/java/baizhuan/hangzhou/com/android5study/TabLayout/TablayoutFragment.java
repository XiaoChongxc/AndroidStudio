package baizhuan.hangzhou.com.android5study.TabLayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import baizhuan.hangzhou.com.android5study.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.android4study.TabLayout
 * Date:    2016-06-16
 * Time:    15:26
 * 类描述：
 */
public class TablayoutFragment extends BasePagerFragment {
    String TAG = "TablayoutFragment";

    @Bind(R.id.tv_content)
    TextView tvContent;
    @Bind(R.id.progress)
    ProgressBar progress;
    @Bind(R.id.btn_refresh)
    Button btnRefresh;
    Subscription subscription;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_tablayout, null);
        ButterKnife.bind(this, rootview);


        String content = getArguments().getString("content");
        tvContent.setText(content);
        return rootview;
    }

    @OnClick(R.id.btn_refresh)
    void refresh() {
        prepareFetchData(true);
    }

    /**
     * 请求网络
     */
    @Override
    public void fetchData() {
        Log.d(TAG, "fetchData: ");

        subscription = Observable.timer(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        Log.d(TAG, "call: 开启进度条！");
                        progress.setVisibility(View.VISIBLE);
                    }
                })
                .doOnNext(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        Log.d(TAG, "call: 在获取数据之前做点别的事！");
                    }
                })
                .unsubscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        Log.d(TAG, "call: 联网已经获取到数据了！");
                        progress.setVisibility(View.GONE);
                        Log.d(TAG, "call: 关闭进度条！");
                        isDataInitiated = true;
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (subscription != null)
            subscription.unsubscribe();
        ButterKnife.unbind(this);
    }
}
