package baizhuan.hangzhou.com.androidlibstudy.Sticky;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.widget.TextView;

import baizhuan.hangzhou.com.androidlibstudy.BaseActivity;
import baizhuan.hangzhou.com.androidlibstudy.R;
import baizhuan.hangzhou.com.androidlibstudy.util.DensityUtils;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author :   Xchong
 * 项目名：   AndroidStudio2
 * 包名   :   baizhuan.hangzhou.com.androidlibstudy.Sticky
 * 日期   :   2016/10/28
 * 时间   ：  5:58
 * 描述   ：   吸附悬停   测试类
 *            直接 监听  AppbarLayout  的 OnOffsetChangedListener  事件。就能在 事件里面 修改   子类控件的位置
 *            利用方法   ViewCompat.offsetTopAndBottom
 *
 */
public class StickyActivity extends BaseActivity {
    String TAG = "StickyActivity";


    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_lable)
    TextView tvLable;

    int  lastY=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sticky);
        ButterKnife.bind(this);


        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                //0- 控件高度时候
                if(Math.abs(verticalOffset)  >=0  && Math.abs(verticalOffset)  <= DensityUtils.dp2px(mContext, 44)){
                    float  alpha = (float)Math.abs(verticalOffset) / DensityUtils.dp2px(mContext, 44);
                    Log.d(TAG, "onOffsetChanged: alpha"+alpha);
                    tvTitle.setAlpha( alpha );
                }
                if (Math.abs(verticalOffset) >= DensityUtils.dp2px(mContext, 44)) {
                    tvTitle.setAlpha(1);
                }
                lastY=tvTitle.getTop();
                //移动 标题栏的位置
                if(-verticalOffset +appBarLayout.getHeight() > tvTitle.getHeight()+tvLable.getHeight()){
                    ViewCompat.offsetTopAndBottom(tvTitle, -verticalOffset -lastY);
                    lastY=tvTitle.getTop();
                }
                Log.d(TAG, "onOffsetChanged: " + verticalOffset +"appBarLayout.getHeight()"+appBarLayout.getHeight());
            }
        });

    }

}
