package com.example.administrator.mytest.PropertyAnimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;

/**
 * Author:  @just_for_happy 开心就好
 * Date: 2016-01-15
 * Time: 15:19
 * FIXME  对属性动画的 一个研究  ，  小球的运动，
 */
public class MyPropertyView   extends View{

    public MyPropertyView(Context context) {
        super(context);
        init();
    }

    public MyPropertyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyPropertyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    int RADIUS=20;

    Point mPoint;

    Paint mPaint;
        private void init(){
            mPaint=new Paint();
            mPaint.setColor(Color.BLUE);
        }

    private boolean flag=false;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width=getWidth();


//        canvas.drawCircle(10,10, RADIUS, mPaint);

        switch (drawType){
            case 0:
                if( flag){
                    mPoint=new Point(RADIUS,RADIUS);
                        drawCirclebyEvaluator2();    //绘制一个园  改变颜色的
                }
                break;
            case 1:
                if(flag){
                    mPoint=new Point(width/2,RADIUS);
                    drawCirclebyVertical();    //绘制一个园 竖直 回弹
                }
                break;
            case 2:
                if( flag){
                    mPoint=new Point(width/2,RADIUS);
                    drawCirclebyVertical();    //绘制一个园 竖直 加速度
                }
                break;
            case 3:
                if( flag){
                    mPoint=new Point(width/2,RADIUS);
                    drawCirclebyVertical2();    //绘制一个园 自定义模式
                }
                break;

        }
        if(mPoint!=null)
        canvas.drawCircle(mPoint.x, mPoint.y, RADIUS, mPaint);

    }



    /**
     * 开启动画
     * 利用监听  来改变小球的位置
     */
    private void  drawCirclebyLinstener(){
        final int width=getWidth();
        final int height=getHeight();
        ValueAnimator valueAnimator =new ValueAnimator().ofFloat(0,1);
        valueAnimator.setDuration(5000);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float process = (float) animation.getAnimatedValue();
                mPoint.x = (int) ((width - RADIUS) * process);
                mPoint.y = (int) ((height - RADIUS) * process);
                invalidate();
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                flag=false;
            }
        });
        valueAnimator.start();
//        ValueAnimator valueAnimator1 =new        ValueAnimator().ofObject(new PointEvaluator(),new Point(RADIUS,RADIUS),new Point(width-RADIUS,height-RADIUS));
//        valueAnimator.setDuration(5000);
//        valueAnimator1.start();
    }

    /***
     * 也是设置监听  刷新位置，不同的是  把  改变位置的操作放在了Evaluator中
     */
    private void drawCirclebyEvaluator(){
        final int width=getWidth();
        final int height=getHeight();
        ValueAnimator valueAnimator =new        ValueAnimator().ofObject(new PointEvaluator(), new Point(RADIUS, RADIUS), new Point(width - RADIUS, height - RADIUS));
        valueAnimator.setDuration(5000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mPoint = (Point) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                flag=false;
            }
        });
        valueAnimator.start();

    }

    /***
     * 改变颜色
     */
    private void drawCirclebyEvaluator2(){
        final int width=getWidth();
        final int height=getHeight();
        ValueAnimator valueAnimator =new        ValueAnimator().ofObject(new PointEvaluator(), new Point(RADIUS, RADIUS), new Point(width - RADIUS, height - RADIUS));
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mPoint = (Point) animation.getAnimatedValue();
                invalidate();
            }
        });
        ObjectAnimator  objectAnimator =new ObjectAnimator().ofObject(this, "color", new PointColorEvaluator(), "#0000ff", "#ff0000");
        AnimatorSet animatorSet =new AnimatorSet();
        animatorSet.play(objectAnimator).with(valueAnimator);
        animatorSet.setDuration(5000);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                flag = false;
            }
        });
        animatorSet.start();

    }

    /***
     * 竖直的小球下落 符合物理规律
     */
    private void drawCirclebyVertical(){
        final int height=getHeight();
        ValueAnimator valueAnimator =new ValueAnimator().ofFloat(0, 1);
        valueAnimator.setDuration(5000);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float process = (float) animation.getAnimatedValue();
                mPoint.x = getWidth() / 2;
                mPoint.y = (int) ((height - RADIUS) * process);
                invalidate();
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                flag = false;
            }
        });
//        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());//先减速 后加速的Interpolator  也是默认的
//        valueAnimator.setInterpolator(new AccelerateInterpolator(2f));//加速度为 2
            valueAnimator.setInterpolator(new BounceInterpolator());//可以模拟物理规律，实现反复弹起效果的Interpolator
            valueAnimator.start();

    }

    /***
     * 竖直小球下落  自定义的下落过程
     */
    private void drawCirclebyVertical2(){

        final int height=getHeight();
        ValueAnimator valueAnimator =new ValueAnimator().ofFloat(0, 1);
        valueAnimator.setDuration(5000);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float process = (float) animation.getAnimatedValue();
                mPoint.x = getWidth() / 2;
                mPoint.y = (int) ((height - RADIUS) * process);
                invalidate();
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                flag=false;
            }
        });
        valueAnimator.setInterpolator(new CustomInterpolator());//自定义的
        valueAnimator.start();

    }


    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        mPaint.setColor(Color.parseColor(color));
        this.color = color;
        invalidate();
    }

    /***
     * 绘制类型 默认0
     */
    int drawType=0;
    public void setDrawType(int type){
        this.drawType=type;
        flag=true;
        invalidate();
    }

}
