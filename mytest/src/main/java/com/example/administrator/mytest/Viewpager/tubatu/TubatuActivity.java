package com.example.administrator.mytest.Viewpager.tubatu;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.mytest.R;
import com.example.administrator.mytest.Viewpager.RecyclingPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 开心就好（@just_for_happy） on 2016/5/2.
 * 土巴兔   viewPager  的 demo   一屏展示多个 ， 中间的放大
 */
public class TubatuActivity extends AppCompatActivity {
    TubatuViewpager viewpager;
    private TubatuAdapter mPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tubatu);
        viewpager = (TubatuViewpager) findViewById(R.id.viewpager);
        viewpager.setPageTransformer(true,new ScalePageTransformer());
        findViewById(R.id.page_container).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return viewpager.dispatchTouchEvent(event);
            }
        });
        mPagerAdapter = new TubatuAdapter(this);
        viewpager.setAdapter(mPagerAdapter);
        initData();
    }

    private void initData() {
        List<Integer> list = new ArrayList<>();
        list.add(R.mipmap.style_xiandai);
        list.add(R.mipmap.style_jianyue);
        list.add(R.mipmap.style_oushi);
        list.add(R.mipmap.style_zhongshi);
        list.add(R.mipmap.style_meishi);
        list.add(R.mipmap.style_dzh);
        list.add(R.mipmap.style_dny);
        list.add(R.mipmap.style_rishi);

        //设置OffscreenPageLimit
        viewpager.setOffscreenPageLimit(list.size());
        mPagerAdapter.addAll(list);
    }

    public static class TubatuAdapter extends RecyclingPagerAdapter {

        private final List<Integer> mList;
        private final Context mContext;

        public TubatuAdapter(Context context) {
            mList = new ArrayList<>();
            mContext = context;
        }

        public void addAll(List<Integer> list) {
            mList.addAll(list);
            notifyDataSetChanged();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            ImageView imageView = null;
            if (convertView == null) {
                imageView = new ImageView(mContext);
            } else {
                imageView = (ImageView) convertView;
            }
            imageView.setTag(position);
            imageView.setImageResource(mList.get(position));
            return imageView;
        }

        @Override
        public int getCount() {
            return mList.size();
        }
    }
}
