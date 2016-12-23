package baizhuan.hangzhou.com.android5study.NavigationView;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import baizhuan.hangzhou.com.android5study.AppBarLayout.AppBarLayoutActivity;
import baizhuan.hangzhou.com.android5study.BaseActivity;
import baizhuan.hangzhou.com.android5study.CardView.CardViewActivity;
import baizhuan.hangzhou.com.android5study.CollapsingToolbarLayout.CollapsingToolbarBehaviorActivity;
import baizhuan.hangzhou.com.android5study.CollapsingToolbarLayout.CollapsingToolbarBehaviorActivity2;
import baizhuan.hangzhou.com.android5study.CollapsingToolbarLayout.CollapsingToolbarLayoutActivity;
import baizhuan.hangzhou.com.android5study.CoordinatiorLayout.BehaviorTest1Activity;
import baizhuan.hangzhou.com.android5study.CoordinatiorLayout.CoordinatiorLayoutActivity;
import baizhuan.hangzhou.com.android5study.ExpandListView.ExpandListActivity;
import baizhuan.hangzhou.com.android5study.ExpandListView.ExpressDetailActivity;
import baizhuan.hangzhou.com.android5study.Palette.PaletteActivity;
import baizhuan.hangzhou.com.android5study.R;
import baizhuan.hangzhou.com.android5study.RecycleView.RecycleViewActivity;
import baizhuan.hangzhou.com.android5study.RecycleView.headgrid.ActivityHeadgrid;
import baizhuan.hangzhou.com.android5study.Ripple.RippleActivity;
import baizhuan.hangzhou.com.android5study.TabLayout.TabLayoutActivity;
import baizhuan.hangzhou.com.android5study.TextInputLayout.TextInputLayoutActivity;
import baizhuan.hangzhou.com.android5study.TextSelection.TextSelectionActivity;
import baizhuan.hangzhou.com.android5study.TextureView.TextureViewActivity;
import baizhuan.hangzhou.com.android5study.Toolbar.ToolbarActivity;
import baizhuan.hangzhou.com.android5study.threeListview.ThreeListActivity;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.android4study
 * Date:    2016-06-16
 * Time:    15:02
 * 类描述：兼容包中提供的用来实现导航菜单界面的新控件，(抽屉菜单)
 */
public class NavigationViewActivity extends BaseActivity {


    @Bind(R.id.recycle)
    RecyclerView recycle;
    @Bind(R.id.framelayout1)
    LinearLayout framelayout1;
    @Bind(R.id.navigation_left)
    NavigationView navigationLeft;
    @Bind(R.id.drawlayout)
    DrawerLayout drawlayout;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getString(R.string.app_name));

        navigationLeft.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (item.getItemId() == R.id.show_with_action) {
                    final Snackbar snackbar = Snackbar.make(navigationLeft, item.getTitle() + "pressed", Snackbar.LENGTH_LONG);
                    snackbar.setAction("撤销", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(NavigationViewActivity.this, "撤销成功", Toast.LENGTH_SHORT).show();
                            snackbar.dismiss();
                        }
                    });
                    snackbar.show();
                } else if (item.getItemId() == R.id.show_with_callback) {
                    final Snackbar snackbar = Snackbar.make(navigationLeft, item.getTitle() + "pressed", Snackbar.LENGTH_LONG);
                    snackbar.setCallback(new Snackbar.Callback() {
                        @Override
                        public void onDismissed(Snackbar snackbar, int event) {
                            super.onDismissed(snackbar, event);
                            Toast.makeText(NavigationViewActivity.this, "onDismissed", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onShown(Snackbar snackbar) {
                            super.onShown(snackbar);
                            Toast.makeText(NavigationViewActivity.this, "onShown", Toast.LENGTH_SHORT).show();
                        }
                    });
                    snackbar.show();
                } else {
                    Snackbar.make(navigationLeft, item.getTitle() + " pressed", Snackbar.LENGTH_LONG).show();
                    item.setChecked(true);
                }
                return true;
            }
        });

        final String[] datas = getResources().getStringArray(R.array.demo_item);
        final Class[] clazz = {CoordinatiorLayoutActivity.class, BehaviorTest1Activity.class, AppBarLayoutActivity.class, CollapsingToolbarLayoutActivity.class, CollapsingToolbarBehaviorActivity.class,CollapsingToolbarBehaviorActivity2.class,
                TabLayoutActivity.class, TextInputLayoutActivity.class, TextSelectionActivity.class, CardViewActivity.class, PaletteActivity.class, RippleActivity.class, ToolbarActivity.class, RecycleViewActivity.class,
                TextureViewActivity.class, ExpandListActivity.class, ExpressDetailActivity.class, ThreeListActivity.class, ActivityHeadgrid.class};
        recycle.setLayoutManager(new LinearLayoutManager(this));
        NavigationViewAdapter adapter = new NavigationViewAdapter(this, datas);
        recycle.setAdapter(adapter);
        adapter.setOnItemClickListener(new NavigationViewAdapter.OnItemClickListener() {
            @Override
            public void onitemClick(int position) {
                Intent intent = new Intent(NavigationViewActivity.this, clazz[position]);
                startActivity(intent);
            }
        });

    }


}
