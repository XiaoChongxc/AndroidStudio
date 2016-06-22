package baizhuan.hangzhou.com.android5study;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.android5study
 * Date:    2016-06-20
 * Time:    15:25
 * 类描述：
 */
public class BaseActivity extends AppCompatActivity {
    public Context ctx;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx=this;

    }
}
