package baizhuan.hangzhou.com.demolib;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.demolib
 * Date:    2016-06-23
 * Time:    15:45
 * 类描述：
 */
public class Activity2 extends AppCompatActivity {

    @butterknife.Bind(R.id.btn_1)
    Button btn1;
    @butterknife.Bind(R.id.btn_2)
    Button btn2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        butterknife.ButterKnife.bind(this);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new Event1());
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new Event2());
            }
        });
    }
}
