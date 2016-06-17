package baizhuan.hangzhou.com.android5study.CoordinatiorLayout;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;

import baizhuan.hangzhou.com.android5study.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.android5study.CoordinatiorLayoutActivity
 * Date:    2016-06-17
 * Time:    11:18
 * 类描述：
 */
public class CoordinatiorLayoutActivity extends Activity {


    @Bind(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinatior1);
        ButterKnife.bind(this);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(fab,"fab  pressed",Snackbar.LENGTH_LONG).show();
            }
        });
    }
}
