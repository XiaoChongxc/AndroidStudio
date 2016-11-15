package baizhuan.hangzhou.com.androidlibstudy;

import android.os.Bundle;

import baizhuan.hangzhou.com.androidlibstudy.customview.FunctionView;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.androidlibstudy
 * Date:    16-11-10.
 * Time:    上午9:28
 * 类描述：
 *
 * @vesion
 */
public class FunctionActivity extends BaseActivity {

    @Bind(R.id.functionview)
    FunctionView functionview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function);
        ButterKnife.bind(this);

    }


}
