package com.example.administrator.mytest.ExplosionField;/**
 * Created by  @just_for_happy (@开心就好)   on 2015/12/22.
 */

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import java.util.ArrayList;

/**
 * author: just_for_happy(@开心就好)
 * Date: 2015-12-22
 * Time: 18:15
 * FIXME  继承自View，用于做粒子集的画布，需要重写onDraw()方法
 */
public class ExplosionField   extends View {

    public static  int stateHeight;

    public ExplosionField(Context context) {
        super(context);
        init();
    }

    ArrayList<ExplosionAnimator> explosionAnimators;

    /**监听器*/
    private View.OnClickListener onClickListener;

    private void init(){
        explosionAnimators = new ArrayList<ExplosionAnimator>();
        attach2Activity();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Log.i("TAG", "Ondraw====");
        super.onDraw(canvas);
            for ( ExplosionAnimator animator: explosionAnimators){
              animator.Draw(canvas);
        }
    }

    private void explode(final View v){
        ExplosionAnimator animator=new ExplosionAnimator(this,v);
        explosionAnimators.add(animator);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                v.animate().alpha(0f).setDuration(150).start();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                v.animate().alpha(1f).setDuration(150).start();
                //动画结束时从动画集中移除
                explosionAnimators.remove(animation);
                animation = null;
            }
        });
        animator.start();


    }





    /**添加监听器*/
    public  void addListener(View view){
    if(  view instanceof  ViewGroup){    //如果是 viewGroup
        ViewGroup viewGroup = (ViewGroup) view;
        int viewCount=viewGroup.getChildCount();
            for (int i=0;i<viewCount;i++){
                addListener(viewGroup.getChildAt(i));
            }
        }else{      //不是viewgroup  添加 监听
        view.setClickable(true);
        view.setOnClickListener(getOnClickListener());
    }

    }



    private  OnClickListener getOnClickListener(){
        if (null == onClickListener) {

            onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ExplosionField.this.explode(v);

//                view.setOnClickListener(null); // 用过一次就不需要了
                }
            };
        }

        return onClickListener;
    }
    /**使 view  充满整个屏幕 ，并 盖在  原来的view上面*/
    private void attach2Activity(){
        Activity activity=(Activity)getContext();
        ViewGroup rootView=(ViewGroup)activity.findViewById(Window.ID_ANDROID_CONTENT);        //获取到Activity的根view
        ViewGroup.LayoutParams lp=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rootView.addView(this, lp);
    }
}
