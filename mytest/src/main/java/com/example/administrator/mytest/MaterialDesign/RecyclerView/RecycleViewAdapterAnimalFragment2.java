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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.administrator.mytest.R;
import com.example.administrator.mytest.cunstom.EasyRecyclerView;
import com.example.administrator.mytest.easyRecycleView.adapter.BaseViewHolder;
import com.example.administrator.mytest.easyRecycleView.adapter.RecyclerArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio
 * Package: com.example.administrator.mytest.MaterialDesign.RecyclerView
 * Date:    2016-05-31
 * Time:    10:58
 * 类描述： easyRecycleView Adapter animal
 */
public class RecycleViewAdapterAnimalFragment2 extends Fragment {

    @Bind(R.id.sp)
    Spinner sp;
    @Bind(R.id.easy_recycle)
    EasyRecyclerView easyRecycle;
    MyRecycleAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_recycle_adapter_animal2, container, false);
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
        easyRecycle.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    class MyRecycleAdapter2 extends RecyclerView.Adapter<MyRecycleAdapter2.Viewhodler> {
        private int lastPosition = -1;
        Context ctx;
        List<String> list;

        public MyRecycleAdapter2(Context ctx, List<String> list) {
            this.ctx = ctx;
            this.list = list;
        }

        @Override
        public MyRecycleAdapter2.Viewhodler onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(ctx).inflate(android.R.layout.simple_list_item_1, parent, false);
            Viewhodler viewhodler = new Viewhodler(v);
            return viewhodler;
        }

        @Override
        public void onBindViewHolder(MyRecycleAdapter2.Viewhodler holder, int position) {

            holder.tv.setText(list.get(position) + "");
            setAnimation(holder.itemView, position);

        }

        protected void setAnimation(View viewToAnimate, int position) {
            if (position > lastPosition) {
                Animation animation = AnimationUtils.loadAnimation(viewToAnimate.getContext(), R.anim.fade_in);
                viewToAnimate.startAnimation(animation);
                lastPosition = position;
            }
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class Viewhodler extends RecyclerView.ViewHolder {
            TextView tv;

            public Viewhodler(View itemView) {
                super(itemView);
                tv = (TextView) itemView.findViewById(android.R.id.text1);
            }
        }

        @Override
        public void onViewDetachedFromWindow(MyRecycleAdapter2.Viewhodler holder) {
            super.onViewDetachedFromWindow(holder);
            holder.itemView.clearAnimation();
        }


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
            private int lastPosition = -1;
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

            @Override
            public void setAnimal(int position) {
                super.setAnimal(position);
                setAnimation(itemView,position);
            }

            protected void setAnimation(View viewToAnimate, int position) {
                if (position > lastPosition) {
                    Animation animation = AnimationUtils.loadAnimation(viewToAnimate.getContext(), R.anim.fade_in);
                    viewToAnimate.startAnimation(animation);
                    lastPosition = position;
                }
            }

        }
    }

}
