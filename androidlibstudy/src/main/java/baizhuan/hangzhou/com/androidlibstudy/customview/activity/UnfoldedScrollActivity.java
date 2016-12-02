package baizhuan.hangzhou.com.androidlibstudy.customview.activity;

import android.os.Bundle;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unfolded_scroll);
        ButterKnife.bind(this);

//        unfoldedScroll.setVIEW_TYPE(UnfoldedScrollView.VIEW_TYPE_MIDDLE);
    }


}
