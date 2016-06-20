package baizhuan.hangzhou.com.android5study.CollapsingToolbarLayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import baizhuan.hangzhou.com.android5study.BaseActivity;
import baizhuan.hangzhou.com.android5study.R;
import baizhuan.hangzhou.com.android5study.RecycleAdapter.CommenAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.android5study.CollapsingToolbarLayout
 * Date:    2016-06-20
 * Time:    15:24
 * 类描述：可以控制 内部控件，在响应 layout_behavior事件时做出ScrollFlag 滚动事件(移除屏幕或固定在屏幕顶端)，形成视觉效果
 * 1.可折叠MD风格ToolbarLayout
 * 2.可以折叠的Toolbar
 */
public class CollapsingToolbarLayoutActivity extends BaseActivity {
    ArrayList list;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.ctl_title)
    CollapsingToolbarLayout ctlTitle;
    @Bind(R.id.recycle)
    RecyclerView recycle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_collapsing_toolbar_layout);
        ButterKnife.bind(this);
        init();
    }


    void init() {
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        recycle.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList();
        for (int i = 0; i < 20; i++) {
            list.add("" + i + "-" + i + "-" + "i");
        }
        recycle.setAdapter(new CommenAdapter(this, list));
    }
}
