package baizhuan.hangzhou.com.androidlibstudy.customview.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import baizhuan.hangzhou.com.androidlibstudy.BaseActivity;
import baizhuan.hangzhou.com.androidlibstudy.R;
import baizhuan.hangzhou.com.androidlibstudy.customview.AlorithmView;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.androidlibstudy.customview.activity
 * Date:    16-11-30.
 * Time:    下午3:42
 * 类描述：   展示算法的 view
 *
 * @vesion
 */
public class AlorithmActivity extends BaseActivity {

    @Bind(R.id.alorithm)
    AlorithmView alorithm;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alorithm);
        ButterKnife.bind(this);
        toolbar.setTitle("算法展示");
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.alorithm_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.type_view_maopao:
                alorithm.setViewType(AlorithmView.VIEW_TYPE_MAOPAO);
                break;
            case R.id.type_view_chose:
                alorithm.setViewType(AlorithmView.VIEW_TYPE_CHOSE);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
