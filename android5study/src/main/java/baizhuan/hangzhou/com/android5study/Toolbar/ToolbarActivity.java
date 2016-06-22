package baizhuan.hangzhou.com.android5study.Toolbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import baizhuan.hangzhou.com.android5study.BaseActivity;
import baizhuan.hangzhou.com.android5study.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.android5study.Toolbar
 * Date:    2016-06-21
 * Time:    17:06
 * 类描述：
 */
public class ToolbarActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.drawlayout)
    DrawerLayout drawlayout;
    Menu toolMenu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
        ButterKnife.bind(this);

//
//        drawlayout.closeDrawer(Gravity.LEFT);     //关闭
//        drawlayout.openDrawer(Gravity.RIGHT);     //打开
        toolbar.setSubtitle("这是小标题");
        toolbar.setLogo(R.mipmap.ic_logo);

        setSupportActionBar(toolbar);


        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.item_toolbar_1) {
                    if (toolMenu != null) {
                        toolMenu.clear();
                        getMenuInflater().inflate(R.menu.menu_toolbar2, toolMenu);
                    }
                } else {
                    Toast.makeText(ctx, "", Toast.LENGTH_LONG).show();
                }
                return true;
            }
        });

        //设置   关联，  使  drawlayout 变化的时候， 有好看的 切换动画效果
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawlayout, toolbar, R.string.open, R.string.close);
        actionBarDrawerToggle.syncState();
        drawlayout.addDrawerListener(actionBarDrawerToggle);

    }


    @OnClick(R.id.btn_1)
    void onLeftdrawLayoutOpen() {
        if (drawlayout.isDrawerOpen(Gravity.LEFT)) {
            drawlayout.closeDrawer(Gravity.LEFT);
        } else {
            drawlayout.openDrawer(Gravity.LEFT);
        }
    }

    @OnClick(R.id.btn_2)
    void onRightdrawLayoutOpen() {
        if (drawlayout.isDrawerOpen(Gravity.RIGHT)) {
            drawlayout.closeDrawer(Gravity.RIGHT);
        } else {
            drawlayout.openDrawer(Gravity.RIGHT);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        toolMenu = menu;
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }


}
