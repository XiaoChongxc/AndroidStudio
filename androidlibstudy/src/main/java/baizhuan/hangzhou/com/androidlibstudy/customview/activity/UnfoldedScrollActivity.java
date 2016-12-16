package baizhuan.hangzhou.com.androidlibstudy.customview.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import baizhuan.hangzhou.com.androidlibstudy.BaseActivity;
import baizhuan.hangzhou.com.androidlibstudy.R;
import baizhuan.hangzhou.com.androidlibstudy.customview.UnfoldedScrollView;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.androidlibstudy.customview.activity
 * Date:    16-12-2.
 * Time:    下午1:53
 * 类描述：  画卷 展开的效果
 *
 * @vesion
 */
public class UnfoldedScrollActivity extends BaseActivity {

    @Bind(R.id.unfoldedScroll)
    UnfoldedScrollView unfoldedScroll;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unfolded_scroll);
        ButterKnife.bind(this);
        toolbar.setTitle("画卷展开");
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.unfolded_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.type_left:
                unfoldedScroll.setVIEW_TYPE(UnfoldedScrollView.VIEW_TYPE_LEFT);
                break;
            case R.id.type_middle:
                unfoldedScroll.setVIEW_TYPE(UnfoldedScrollView.VIEW_TYPE_MIDDLE);
                break;
            case R.id.type_right:
                unfoldedScroll.setVIEW_TYPE(UnfoldedScrollView.VIEW_TYPE_RIGHT);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
