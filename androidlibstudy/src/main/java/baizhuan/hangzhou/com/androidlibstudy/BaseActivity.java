package baizhuan.hangzhou.com.androidlibstudy;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2016/9/19.
 */
public class BaseActivity extends AppCompatActivity {

    public Context  mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
    }
}
