package com.example.administrator.mytest.qqnearbybody.custom;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by 开心就好（@just_for_happy） on 2016/5/7.
 * <p>
 * 因为我们想要viewpager左右滑动的时候，当滑动速度大于一定值，则可以一次滑动两个item，所以我们需要自定义一个获取速度的ViewPager
 */
public class CustomViewPager extends ViewPager {


    private long downTime;
    private float LastX;
    private float mSpeed;


    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    int type = 0;  //按下 标识

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        float x = ev.getX();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downTime = System.currentTimeMillis();
                LastX = x;
                type=0;
                break;
            case MotionEvent.ACTION_MOVE:
                x = ev.getX();
                type=1;
                break;
            case MotionEvent.ACTION_UP:
                //计算得到手指从按下到离开的滑动速度
                mSpeed = (x - LastX) * 1000 / (System.currentTimeMillis() - downTime);
                if(type!=1){//没有移动  ， 认为是点击动作
                    View view=viewOfClickOnScreen(ev);
                    if(view!=null){
                        setCurrentItem(indexOfChild(view));
                    }
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    private View viewOfClickOnScreen(MotionEvent ev) {
       int  viewCount=getChildCount();
       int [] location=new int[2];
        for (int i=0;i<viewCount ;i++){
            View  v=getChildAt(i);
            v.getLocationOnScreen(location);
            int minX = location[0];
            int minY = getTop();

            int maxX = location[0] + v.getWidth();
            int maxY = getBottom();

            float x = ev.getX();
            float y = ev.getY();

            if ((x > minX && x < maxX) && (y > minY && y < maxY)) {
                return v;
            }
        }
        return null;
    }


    public float getSpeed() {
        return mSpeed;
    }

    public void setSpeed(float mSpeed) {
        this.mSpeed = mSpeed;
    }
}
