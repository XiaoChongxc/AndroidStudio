package com.example.administrator.mytest.qqnearbybody.custom;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ToxicBakery.viewpager.transforms.ScaleInOutTransformer;
import com.example.administrator.mytest.R;
import com.example.administrator.mytest.Viewpager.RecyclingPagerAdapter;
import com.example.administrator.mytest.Viewpager.tubatu.ScalePageTransformer;
import com.example.administrator.mytest.Viewpager.tubatu.TubatuViewpager;
import com.example.administrator.mytest.qqnearbybody.bean.Info;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 开心就好（@just_for_happy） on 2016/5/2.
 * 土巴兔   viewPager  的 demo   一屏展示多个 ， 中间的放大
 */
public class QQnearbyActivity extends AppCompatActivity {
    @Bind(R.id.viewpager)
    TubatuViewpager viewpager;
    private TubatuAdapter mPagerAdapter;
    ArrayList<Info> list=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qq_2);
        ButterKnife.bind(this);
        viewpager = (TubatuViewpager) findViewById(R.id.viewpager);
        viewpager.setPageTransformer(true, new ScalePageTransformer());
        findViewById(R.id.viewcontain).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return viewpager.dispatchTouchEvent(event);
            }
        });
//        mPagerAdapter = new TubatuAdapter();
//        viewpager.setAdapter(mPagerAdapter);
        initData();
    }

//    private void initData() {
//        List<Integer> list = new ArrayList<>();
//        list.add(R.mipmap.style_xiandai);
//        list.add(R.mipmap.style_jianyue);
//        list.add(R.mipmap.style_oushi);
//        list.add(R.mipmap.style_zhongshi);
//        list.add(R.mipmap.style_meishi);
//        list.add(R.mipmap.style_dzh);
//        list.add(R.mipmap.style_dny);
//        list.add(R.mipmap.style_rishi);
//
//        //设置OffscreenPageLimit
//        viewpager.setOffscreenPageLimit(list.size());
//        mPagerAdapter.addAll(list);
//    }
    public void initData(){

        list.add(new Info(R.mipmap.boy, "王尼玛", "15", false, 21f));
        list.add(new Info(R.mipmap.girl, "王1", "15", false, 21f));
        list.add(new Info(R.mipmap.len, "王2", "15", false, 21f));
        list.add(new Info(R.mipmap.leo, "王3", "15", false, 21f));
        list.add(new Info(R.mipmap.lep, "王4", "15", false, 21f));
        list.add(new Info(R.mipmap.ler, "王5", "15", false, 21f));
        list.add(new Info(R.mipmap.boy, "王6", "15", false, 21f));
        list.add(new Info(R.mipmap.leq, "王7", "15", false, 21f));
        list.add(new Info(R.mipmap.les, "王8", "15", false, 21f));
        list.add(new Info(R.mipmap.mln, "王9", "15", false, 21f));
        list.add(new Info(R.mipmap.mmz, "王10", "15", false, 21f));
        list.add(new Info(R.mipmap.mna, "王11", "15", false, 21f));
        viewpager.setAdapter(new TubatuAdapter());
        viewpager.setOffscreenPageLimit(list.size());
//        viewpager.setPageTransformer(true, new ScaleInOutTransformer());
    }

    public class TubatuAdapter extends RecyclingPagerAdapter {
        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            final Info info = list.get(position);
            View view = LayoutInflater.from(QQnearbyActivity.this).inflate(R.layout.viewpager_layout, null);
            ImageView ivPortrait = (ImageView) view.findViewById(R.id.iv);
            ImageView ivSex = (ImageView) view.findViewById(R.id.iv_sex);
            TextView tvName = (TextView) view.findViewById(R.id.tv_name);
            TextView tvDistance = (TextView) view.findViewById(R.id.tv_distance);
            tvName.setText(info.getName());
            tvDistance.setText(info.getDistance() + "km");
            ivPortrait.setImageResource(info.getPortraitId());
            if (info.isSex()) {
                ivSex.setImageResource(R.mipmap.girl);
            } else {
                ivSex.setImageResource(R.mipmap.boy);
            }
            ivPortrait.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(QQnearbyActivity.this, "这是 " + info.getName() + " >.<", Toast.LENGTH_SHORT).show();
                }
            });
//            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }

//    public static class TubatuAdapter extends RecyclingPagerAdapter {
//
//        private final List<Integer> mList;
//        private final Context mContext;
//
//        public TubatuAdapter(Context context) {
//            mList = new ArrayList<>();
//            mContext = context;
//        }
//
//        public void addAll(List<Integer> list) {
//            mList.addAll(list);
//            notifyDataSetChanged();
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup container) {
//            ImageView imageView = null;
//            if (convertView == null) {
//                imageView = new ImageView(mContext);
//            } else {
//                imageView = (ImageView) convertView;
//            }
//            imageView.setTag(position);
//            imageView.setImageResource(mList.get(position));
//            return imageView;
//        }
//
//        @Override
//        public int getCount() {
//            return mList.size();
//        }
//    }
}
