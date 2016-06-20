package baizhuan.hangzhou.com.android5study.TabLayout;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import baizhuan.hangzhou.com.android5study.BaseActivity;
import baizhuan.hangzhou.com.android5study.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.android4study.TabLayout
 * Date:    2016-06-16
 * Time:    15:03
 * 类描述：
 */
public class TabLayoutActivity extends BaseActivity {

    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.tablayout)
    TabLayout tablayout;

    List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout);
        ButterKnife.bind(this);


        String[] data = {"fragmeng1", "fragmeng2", "fragmeng3", "fragmeng4"};
        fragments = new ArrayList<>();
        fragments.add(new TablayoutFragment());
        fragments.add(new TablayoutFragment());
        fragments.add(new TablayoutFragment());
        fragments.add(new TablayoutFragment());

        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), data);
        viewpager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewpager);
        //        tablayout.setTabMode(TabLayout.MODE_FIXED);MODE_SCROLLABLE  控制  是否能够滚动

        String [] subtitles={"1","5","7","3"};

//        addTabSubTitle(subtitles);

    }

    //给 tablayout  的 tab 添加 一个 角标
    private void addTabSubTitle(String[] data) {
        for (int i = 0; i < tablayout.getTabCount(); i++) {
            TabLayout.Tab tab = tablayout.getTabAt(i);
            View view = LayoutInflater.from(this).inflate(R.layout.layout_tab_sub_title, null);
            TextView tvContent = (TextView) view.findViewById(R.id.subtitle);
            tvContent.setText(data[i]);
            if (tab != null)
                tab.setCustomView(view);
        }

    }


    class FragmentAdapter extends FragmentPagerAdapter {
        String titles[];

        public FragmentAdapter(FragmentManager fm, String[] titles) {
            super(fm);
            this.titles = titles;
            Log.d("tag", "FragmentAdapter: "+titles.length);
        }

        @Override
        public Fragment getItem(int position) {
            TablayoutFragment fragment= (TablayoutFragment) fragments.get(position);
            Bundle bundle =new Bundle();
            bundle.putString("content",titles[position]+"  这是一个fragment");
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public int getCount() {
            return titles.length;
        }
    }


}
