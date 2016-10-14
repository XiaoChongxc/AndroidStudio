package baizhuan.hangzhou.com.androidlibstudy.Shader;

import android.os.Bundle;
import android.widget.Button;

import baizhuan.hangzhou.com.androidlibstudy.BaseActivity;
import baizhuan.hangzhou.com.androidlibstudy.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author :   Xchong
 * 项目名：   AndroidStudio2
 * 包名   :   baizhuan.hangzhou.com.androidlibstudy.Shader
 * 日期   :   2016/10/11
 * 时间   ：  10:09
 * 描述   ：
 */
public class LinearGradientActivity2 extends BaseActivity {

    @Bind(R.id.btn_reset)
    Button btnReset;
    @Bind(R.id.btn_add)
    Button btnAdd;

    int curProgress = 14;

    @Bind(R.id.progress)
    LinearGradientButton progress;
    @Bind(R.id.progress2)
    LinearGradientButton progress2;
    @Bind(R.id.progress3)
    LinearGradientButton progress3;
    @Bind(R.id.progress4)
    LinearGradientButton progress4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lineargradient2);
        ButterKnife.bind(this);
        progress.setMaxProgress(100);
        progress.setCurProgress(curProgress);

        progress2.setMaxProgress(100);
        progress2.setCurProgress(curProgress);
        progress2.setOrientation(LinearGradientButton.Orientation_Vertical);

        progress3.setMaxProgress(100);
        progress3.setCurProgress(curProgress);
        progress3.setOrientation(LinearGradientButton.Orientation_Vertical);
        progress3.setOrientation_Reverse(true);

        progress4.setMaxProgress(100);
        progress4.setCurProgress(curProgress);
        progress4.setOrientation(LinearGradientButton.Orientation_Horizontal);
        progress4.setOrientation_Reverse(true);
    }


    @OnClick(R.id.btn_add)
    void add() {
        curProgress += 7;
        if (curProgress >= 100) curProgress = 100;
        progress.setCurProgress(curProgress);
        progress2.setCurProgress(curProgress);
        progress3.setCurProgress(curProgress);
        progress4.setCurProgress(curProgress);


    }

    @OnClick(R.id.btn_reset)
    void reset() {
        curProgress = 0;

        progress.setCurProgress(curProgress);
        progress2.setCurProgress(curProgress);
        progress3.setCurProgress(curProgress);
        progress4.setCurProgress(curProgress);
    }

}
