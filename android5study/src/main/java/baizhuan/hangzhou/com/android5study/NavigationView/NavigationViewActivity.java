package baizhuan.hangzhou.com.android5study.NavigationView;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import baizhuan.hangzhou.com.android5study.R;
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
public class NavigationViewActivity extends Activity {

    @Bind(R.id.framelayout1)
    FrameLayout framelayout1;
    @Bind(R.id.navigation_left)
    NavigationView navigationLeft;
    @Bind(R.id.drawlayout)
    DrawerLayout drawlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        ButterKnife.bind(this);
        navigationLeft.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (item.getItemId() == R.id.show_with_action) {
                    final Snackbar snackbar =Snackbar.make(navigationLeft,item.getTitle()+"pressed",Snackbar.LENGTH_LONG);
                    snackbar.setAction("撤销", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(NavigationViewActivity.this, "撤销成功", Toast.LENGTH_SHORT).show();
                            snackbar.dismiss();
                        }
                    });

                }else if(item.getItemId()==R.id.show_with_callback){
                    final Snackbar snackbar =Snackbar.make(navigationLeft,item.getTitle()+"pressed",Snackbar.LENGTH_LONG);
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
                }
                else  {
                    Snackbar.make(navigationLeft, item.getTitle() + " pressed", Snackbar.LENGTH_LONG).show();
                    item.setChecked(true);
                }
                return true;
            }
        });

    }
}
