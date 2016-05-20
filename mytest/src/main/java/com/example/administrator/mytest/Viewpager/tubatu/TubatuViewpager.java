package com.example.administrator.mytest.Viewpager.tubatu;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by 开心就好（@just_for_happy） on 2016/5/2.
 */
public class TubatuViewpager extends ViewPager {

    public TubatuViewpager(Context context) {
        super(context);
    }

    public TubatuViewpager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        if (ev.getAction() == MotionEvent.ACTION_UP) {
            View view = viewOfClickOnScreen(ev);
            if (view != null) {
                setCurrentItem(indexOfChild(view));
            }
        }

        return super.dispatchTouchEvent(ev);
    }
    /**
     * @param ev
     * @return
     */
    private View viewOfClickOnScreen(MotionEvent ev) {
        int childCount = getChildCount();
        Log.i("自雷数量",childCount+"");
        int[] location = new int[2];
        for (int i = 0; i < childCount; i++) {
            View v = getChildAt(i);
            v.getLocationInWindow(location);//获取在父窗口的坐标
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
}
