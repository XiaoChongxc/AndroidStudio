package baizhuan.hangzhou.com.android5study.CoordinatiorLayout;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import baizhuan.hangzhou.com.android5study.BaseActivity;
import baizhuan.hangzhou.com.android5study.R;
import baizhuan.hangzhou.com.android5study.RecycleAdapter.CommenAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.android5study.CoordinatiorLayoutActivity
 * Date:    2016-06-17
 * Time:    11:18
 * 类描述：CoordinatiorLayout 的一个实现方式
 */
public class CoordinatiorLayoutActivity extends BaseActivity {


    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.appbarlayout)
    AppBarLayout appbarlayout;
    @Bind(R.id.rv_content)
    RecyclerView rvContent;
    ArrayList list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinatior1);
        init();
    }

    void init() {
        ButterKnife.bind(this);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(fab, "fab  pressed", Snackbar.LENGTH_LONG).show();
            }
        });

        setSupportActionBar(toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        rvContent.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList();
        for (int i = 0; i < 20; i++) {
            list.add("" + i + "-" + i + "-" + "i");
        }
        rvContent.setAdapter(new CommenAdapter(this,list));
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_coordinatiorlayout, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.style1:
                setContentView(R.layout.activity_coordinatior1);
                init();
                onStart();
                break;
            case R.id.style2:
                setContentView(R.layout.activity_coordinatior2);
                init();
                onStart();
                break;
            case R.id.style3:
                setContentView(R.layout.activity_coordinatior3);
                init();
                onStart();
                break;
        }
        return true;
    }
}
