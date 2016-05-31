package com.example.administrator.mytest.MaterialDesign.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import it.gmariotti.recyclerview.adapter.AlphaAnimatorAdapter;
import it.gmariotti.recyclerview.adapter.ScaleInAnimatorAdapter;
import it.gmariotti.recyclerview.adapter.SlideInBottomAnimatorAdapter;
import it.gmariotti.recyclerview.adapter.SlideInLeftAnimatorAdapter;

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
        AlphaAnimatorAdapter alphaAnimatorAdapter = new AlphaAnimatorAdapter(adapter, easyRecycle);
        easyRecycle.setAdapter(alphaAnimatorAdapter);

        String[] data = {"Alpha", "Slide Left", "Slide Bottom", "Scale", };
        sp.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, data));
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        AlphaAnimatorAdapter alphaAnimatorAdapter = new AlphaAnimatorAdapter(adapter, easyRecycle);
                        easyRecycle.setAdapter(alphaAnimatorAdapter);
                        break;
                    case 1:
                        SlideInLeftAnimatorAdapter slideInLeftAnimatorAdapter = new SlideInLeftAnimatorAdapter(adapter, easyRecycle);
                        easyRecycle.setAdapter(slideInLeftAnimatorAdapter);
                        slideInLeftAnimatorAdapter.notifyDataSetChanged();
                        break;
                    case 2:
                        SlideInBottomAnimatorAdapter slideInOutBottomItemAnimator = new SlideInBottomAnimatorAdapter(adapter, easyRecycle);
                        easyRecycle.setAdapter(slideInOutBottomItemAnimator);
                        slideInOutBottomItemAnimator.notifyDataSetChanged();
                        break;
                    case 3:
                        ScaleInAnimatorAdapter scaleInAnimatorAdapter = new ScaleInAnimatorAdapter(adapter, easyRecycle);
                        easyRecycle.setAdapter(scaleInAnimatorAdapter);
                        scaleInAnimatorAdapter.notifyDataSetChanged();
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
