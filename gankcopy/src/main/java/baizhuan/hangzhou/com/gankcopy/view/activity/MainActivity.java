package baizhuan.hangzhou.com.gankcopy.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import baizhuan.hangzhou.com.gankcopy.R;
import baizhuan.hangzhou.com.gankcopy.view.fragment.AndroidFragment;
import baizhuan.hangzhou.com.gankcopy.view.fragment.FuliFragment;
import baizhuan.hangzhou.com.gankcopy.view.fragment.IOSFragment;
import baizhuan.hangzhou.com.gankcopy.view.fragment.VideoFragment;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    ArrayList<Fragment> list;
    String[] title = {"福利", "Android", "IOS", "休息视频"};
    @Bind(R.id.tab_layout)
    TabLayout tabLayout;
    @Bind(R.id.pager)
    ViewPager pager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        init();


    }


    //初始化 页面布局
    void init() {
        //toolbar
//        setSupportActionBar(toolbar);
        //fragment
        list = new ArrayList<>();
        list.add(new FuliFragment());
        list.add(new AndroidFragment());
        list.add(new IOSFragment());
        list.add(new VideoFragment());
        pager.setOffscreenPageLimit(title.length);
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return title[position];
            }

            @Override
            public int getCount() {
                return title.length;
            }
        });

        //tablayout
        tabLayout.setupWithViewPager(pager);

    }

}
