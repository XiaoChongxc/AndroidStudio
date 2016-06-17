package com.example.administrator.mytest.MaterialDesign.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.administrator.mytest.R;
import com.example.administrator.mytest.easyRecycleView.adapter.BaseViewHolder;
import com.example.administrator.mytest.easyRecycleView.adapter.RecyclerArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.animators.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.adapters.SlideInBottomAnimationAdapter;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio
 * Package: com.example.administrator.mytest.MaterialDesign.RecyclerView
 * Date:    2016-05-31
 * Time:    10:58
 * 类描述： RecycleView Adapter animal
 */
public class RecycleViewAdapterAnimalFragment extends Fragment {

    @Bind(R.id.sp)
    Spinner sp;
    @Bind(R.id.easy_recycle)
    RecyclerView easyRecycle;
    MyRecycleAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_recycle_adapter_animal, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        List list = new ArrayList();
        for (int i = 0; i < 100; i++) {
            list.add("" + i);
        }
        adapter = new MyRecycleAdapter(getActivity());
        adapter.addAll(list);

        easyRecycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        AlphaInAnimationAdapter alphaAnimatorAdapter = new AlphaInAnimationAdapter(adapter);
        easyRecycle.setAdapter(alphaAnimatorAdapter);
        easyRecycle.setItemAnimator(new DefaultItemAnimator() {
            @Override
            public boolean animateRemove(RecyclerView.ViewHolder holder) {
                return false;
            }

            @Override
            public boolean animateAdd(RecyclerView.ViewHolder holder) {
                return false;
            }

            @Override
            public boolean animateMove(RecyclerView.ViewHolder holder, int fromX, int fromY, int toX, int toY) {
                return false;
            }

            @Override
            public boolean animateChange(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder, int fromLeft, int fromTop, int toLeft, int toTop) {
                return false;
            }

            @Override
            public void runPendingAnimations() {

            }

            @Override
            public void endAnimation(RecyclerView.ViewHolder item) {

            }

            @Override
            public void endAnimations() {

            }

            @Override
            public boolean isRunning() {
                return false;
            }
        });

        String[] data = {"Alpha", "Slide Left", "Slide Bottom", "Scale", };
        sp.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, data));
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        AlphaInAnimationAdapter alphaInAnimationAdapter =new AlphaInAnimationAdapter(adapter);
                        easyRecycle.setAdapter(alphaInAnimationAdapter);
                        break;
                    case 1:
                        SlideInBottomAnimationAdapter slideInBottomAnimationAdapter =new SlideInBottomAnimationAdapter(adapter);
                        easyRecycle.setAdapter(slideInBottomAnimationAdapter);
                        break;
                    case 2:
                        SlideInBottomAnimationAdapter slideInOutBottomItemAnimator = new SlideInBottomAnimationAdapter(adapter);
                        easyRecycle.setAdapter(slideInOutBottomItemAnimator);
                        break;
                    case 3:
                        ScaleInAnimationAdapter scaleInAnimatorAdapter = new ScaleInAnimationAdapter(adapter);
                        easyRecycle.setAdapter(scaleInAnimatorAdapter);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    class MyRecycleAdapter extends RecyclerArrayAdapter<String> {
        public MyRecycleAdapter(Context context) {
            super(context);
        }

        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(parent);
        }

        class ViewHolder extends BaseViewHolder<String> {
            TextView tv;

            public ViewHolder(ViewGroup itemView) {
                super(itemView, android.R.layout.simple_expandable_list_item_1);
                tv = $(android.R.id.text1);
            }

            @Override
            public void setData(String data) {
                super.setData(data);
                tv.setText(data + "");
            }
        }

    }

}
