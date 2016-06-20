package baizhuan.hangzhou.com.android5study.TextInputLayout;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.rengwuxian.materialedittext.MaterialEditText;

import baizhuan.hangzhou.com.android5study.BaseActivity;
import baizhuan.hangzhou.com.android5study.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.android4study.TextInputLayoutActivity
 * Date:    2016-06-17
 * Time:    10:40
 * 类描述：  TextInputLayout   存在一个问题 ，  setError 之后  不能取消
 * 推荐使用  Material  design  EditText   一个 开源库
 */
public class TextInputLayoutActivity extends BaseActivity {

    @Bind(R.id.et_test)
    EditText etTest;
    @Bind(R.id.til_test)
    TextInputLayout tilTest;
    @Bind(R.id.et1)
    EditText et1;
    @Bind(R.id.tv_md)
    MaterialEditText tvMd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_input_layout);
        ButterKnife.bind(this);

        tilTest.setHint("提示文字");
        tilTest.setHintEnabled(true);   //提示可用
        tilTest.setHintAnimationEnabled(true);//动画显示提示
        etTest.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etTest.getText().length() > 5) {
                    tilTest.setErrorEnabled(true);
                    tilTest.setError("发生了错误");
                    etTest.setError("Edit的错误提示");
                } else {
                    tilTest.setError(null);
                }
            }
        });


    }
}
