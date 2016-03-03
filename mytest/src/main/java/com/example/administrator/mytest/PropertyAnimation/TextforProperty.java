package com.example.administrator.mytest.PropertyAnimation;/**
 * Created by  @just_for_happy (@开心就好)   on 2016/1/12.
 * Date: 2016-01-12
 * Time: 10:54
 * Usege: 功能描述。。。
 */

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
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
public class TextforProperty extends Activity  implements View.OnClickListener {


    @Bind(R.id.btn_1)
    Button btn1;

    @Bind(R.id.btn_2)
    Button btn2;

    @Bind(R.id.btn_3)
    Button btn3;

    @Bind(R.id.btn_4)
    Button btn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.test_property);//简单的按钮组合

        ButterKnife.bind(this);

        //在  Activity中调用 动画文件
//        Animator animator = AnimatorInflater.loadAnimator(context, R.animator.test_animator);
//        animator.setTarget(view);
//        animator.start();

    }


    @Override
    @OnClick({R.id.btn_1,R.id.btn_2,R.id.btn_3,R.id.btn_4})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_1:

                byObject(btn1,"alpha",1f,0f,0.5f);
                break;

            case R.id.btn_2:
                byDecimalforFloat(0, 1, 500);
                break;

            case R.id.btn_3 :
                float start=  btn3.getTranslationX();
                ObjectAnimator animator =new ObjectAnimator().ofFloat(v, "translationX", start, start+500, start-20);
                animator.setDuration(2000);
                animator.start();
                break;
            case R.id.btn_4:
                byAnimalset();
                break;
        }

    }


    private void byAnimalset(){
        ObjectAnimator animator1=new ObjectAnimator().ofFloat(btn4,"translationX",0,-500,200);
        ObjectAnimator animator2=new ObjectAnimator().ofFloat(btn4,"alpha",1f,0f,0.5f);
        ObjectAnimator animator3=new ObjectAnimator().ofFloat(btn4, "rotation",0f,360f);
        AnimatorSet animatorSet =new AnimatorSet();
        animatorSet.play(animator1).with(animator2).after(animator3);
        animatorSet.setDuration(3000);
        animatorSet.start();
    }

    /***
     * 数字改变  float型
     * @param start
     * @param end
     * @param duration
     */
    private void  byDecimalforFloat(int start,int end,long duration){
        ValueAnimator animator=new ValueAnimator().ofFloat(start,end);
        animator.setDuration(duration);
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue = (float) animation.getAnimatedValue();
                Log.d("TAG", "cuurent value is " + currentValue);
                btn1.setText(""+currentValue);
            }
        });
    }

    /***
     * 数字改变  float型
     * @param start
     * @param end
     * @param duration
     */
    private void  byDecimalforInt(int start,int end,long duration){
        //可以有多个参数
        ValueAnimator animator=new ValueAnimator().ofInt(start, end);
        animator.setDuration(duration);
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue = (float) animation.getAnimatedValue();
                Log.d("TAG", "cuurent value is " + currentValue);
                btn1.setText("" + currentValue);
            }
        });
    }

    /***
     * 指定对象 和 属性名的属性动画
     * rotation  /
     * scaleY           ，表示在垂直方向上进行缩放
     * translationX     水平移动
     * alpha            透明度
     * @param v
     * @param objectName
     * @param start
     * @param middile
     * @param end
     */
    private void byObject(View v,String objectName , float start,float middile,float end){
        ObjectAnimator animator =new ObjectAnimator().ofFloat(v,objectName,start,middile,end);
        animator.setDuration(1000);
        animator.start();
    };



}
