package baizhuan.hangzhou.com.android5study.CollapsingToolbarLayout;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.ButtonBarLayout;

import baizhuan.hangzhou.com.android5study.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.android5study.CollapsingToolbarLayout
 * Date:    2016-07-18
 * Time:    11:34
 * 类描述：  自定义的  behavior  测试类，
 * 仿        哔哩哔哩
 */
public class CollapsingToolbarBehaviorActivity2 extends Activity {

    @Bind(R.id.playButton)
    ButtonBarLayout playButton;
    @Bind(R.id.collapsingLayout)
    CollapsingToolbarLayout toolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_collapsing_behavior2);
        ButterKnife.bind(this);
    }


}
