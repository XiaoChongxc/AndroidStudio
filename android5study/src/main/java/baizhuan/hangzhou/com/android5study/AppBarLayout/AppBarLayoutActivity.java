package baizhuan.hangzhou.com.android5study.AppBarLayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import java.util.ArrayList;

import baizhuan.hangzhou.com.android5study.BaseActivity;
import baizhuan.hangzhou.com.android5study.R;
import baizhuan.hangzhou.com.android5study.RecycleAdapter.CommenAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.android5study.AppBarLayout
 * Date:    2016-06-20
 * Time:    14:13
 * 类描述：
 */
public class AppBarLayoutActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.img_head)
    ImageView imgHead;
    @Bind(R.id.recycle)
    RecyclerView recycle;
    ArrayList list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_appbar_layout);
        ButterKnife.bind(this);
        init();

    }

    void init() {
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        recycle.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList();
        for (int i = 0; i < 20; i++) {
            list.add("" + i + "-" + i + "-" + "i");
        }
        recycle.setAdapter(new CommenAdapter(this, list));
    }
}
