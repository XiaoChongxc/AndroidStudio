package com.example.administrator.mytest.PropertyAnimation;/**
 * Created by  @just_for_happy (@开心就好)   on 2016/1/12.
 * Date: 2016-01-12
 * Time: 10:54
 * Usege: 功能描述。。。
 */

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.administrator.mytest.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author: just_for_happy(@开心就好)
 * Date: 2016-01-12
 * Time: 10:54
 * FIXME 属性动画的 测试类
 */
public class TextforProperty2 extends Activity  implements View.OnClickListener{

    @Bind(R.id.btn_1)
    Button btn1;
    @Bind(R.id.btn_2)
    Button btn2;
    @Bind(R.id.btn_3)
    Button btn3;
    @Bind(R.id.btn_4)
    Button btn4;
    @Bind(R.id.my_view)
    MyPropertyView myView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.test_property2);//运动的小球
        Log.d("TAG", "---------------TextforProperty--------------");

        ButterKnife.bind(this);

        //在  Activity中调用 动画文件
//        Animator animator = AnimatorInflater.loadAnimator(context, R.animator.test_animator);
//        animator.setTarget(view);
//        animator.start();

    }

    @Override
    @OnClick({R.id.btn_1,R.id.btn_2,R.id.btn_3,R.id.btn_4})
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_1:
                myView.setDrawType(0);
                break;
            case R.id.btn_2:
                myView.setDrawType(1);
                break;
            case R.id.btn_3:
                myView.setDrawType(2);
                break;
            case R.id.btn_4:
                myView.setDrawType(3);
                break;

        }
    }
}
