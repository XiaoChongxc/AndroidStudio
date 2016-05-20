package com.example.administrator.mytest.qqnearbybody;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ToxicBakery.viewpager.transforms.ScaleInOutTransformer;
import com.ToxicBakery.viewpager.transforms.ZoomOutSlideTransformer;
import com.example.administrator.mytest.R;
import com.example.administrator.mytest.Viewpager.RecyclingPagerAdapter;
import com.example.administrator.mytest.Viewpager.tubatu.ScalePageTransformer;
import com.example.administrator.mytest.Viewpager.tubatu.TubatuViewpager;
import com.example.administrator.mytest.qqnearbybody.bean.Info;
import com.example.administrator.mytest.qqnearbybody.custom.CustomViewPager;
import com.example.administrator.mytest.qqnearbybody.custom.QQnearbyActivity;
import com.example.administrator.mytest.qqnearbybody.custom.RadarView;
import com.example.administrator.mytest.qqnearbybody.utils.FixedSpeedScroller;

import java.lang.reflect.Field;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 开心就好（@just_for_happy） on 2016/5/7.
 * qq  附近的人
 */
public class QQNearbyBody extends Activity {
    @Bind(R.id.viewpager)
    TubatuViewpager viewpager;
    @Bind(R.id.viewcontain)
    RelativeLayout viewcontain;
    @Bind(R.id.radar)
    RadarView radar;
    ArrayList<Info> list =new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qq);
        ButterKnife.bind(this);
        viewpager.setOnTouchListener(null);
        viewcontain.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return viewpager.dispatchTouchEvent(event);
            }
        });

        list = new ArrayList<>();
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


        viewpager.setAdapter(new MyViewpagerAdapter());
        viewpager.setOffscreenPageLimit(list.size());
        viewpager.setPageTransformer(true, new ZoomOutSlideTransformer());
    }

    public class MyViewpagerAdapter extends RecyclingPagerAdapter {
        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            final Info info = list.get(position);
            View view=null;
            view = LayoutInflater.from(QQNearbyBody.this).inflate(R.layout.viewpager_layout, null);
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
//            ivPortrait.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(TestPagerView.this, "这是 " + info.getName() + " >.<", Toast.LENGTH_SHORT).show();
//                }
//            });
            return view;
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }

}
