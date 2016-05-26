package baizhuan.hangzhou.com.gankcopy.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import baizhuan.hangzhou.com.gankcopy.R;
import baizhuan.hangzhou.com.gankcopy.adapter.MeiZhiAdapter;
import baizhuan.hangzhou.com.gankcopy.adapter.TextForAdapter;
import baizhuan.hangzhou.com.gankcopy.http.APIService;
import baizhuan.hangzhou.com.gankcopy.model.GanHuo;
import baizhuan.hangzhou.com.gankcopy.util.RecycleSpace;
import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio
 * Package: com.example.administrator.mytest.MaterialDesign.RecyclerView
 * Date:    2016-05-25
 * Time:    16:35
 * 类描述：
 */
public class AndroidFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnLoadMoreListener {
    List<GanHuo.Result> list;
    @Bind(R.id.recycleview)
    EasyRecyclerView recycleview;
    MeiZhiAdapter adapter;
    TextForAdapter adapter2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_fragment, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        list = new ArrayList<>();

        adapter2 = new TextForAdapter(getActivity());
//        recycleview.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recycleview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleview.setAdapterWithProgress(adapter2);
        recycleview.setRefreshListener(this);

        recycleview.addItemDecoration(new RecycleSpace(2));
        adapter2.setMore(R.layout.load_more_layout, this);
        adapter2.setNoMore(R.layout.no_more_layout);
        adapter2.setError(R.layout.error_layout);

        //这行代码 需要 运行在 最后面 ， 不然  ，  footview 加载不出来
        adapter2.addAll(list);

        onRefresh();
    }


    void getData(String type, int count, int page) {
        APIService.getGankService().getData(type, count, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GanHuo>() {
                    @Override
                    public void onCompleted() {
                        Log.d("tag", "onCompleted: getdata");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("tag", "onCompleted: getdata");
                        //联网失败
                        Snackbar.make(recycleview, "NO WIFI，不能愉快的看妹纸啦..", Snackbar.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNext(GanHuo ganHuo) {
                        adapter2.addAll(ganHuo.getResults());
                    }
                });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onRefresh() {
        getData("Android", 20, 1);
    }
int page =1;
    @Override
    public void onLoadMore() {
        getData("Android", 20, page);
        page++;

    }
}
