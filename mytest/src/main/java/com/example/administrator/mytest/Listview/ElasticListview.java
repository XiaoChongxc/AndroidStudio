package com.example.administrator.mytest.Listview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.ListView;
import android.widget.Scroller;

import utils.DensityUtils;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio
 * Package: com.example.administrator.mytest.Listview
 * Date:    2016-05-17
 * Time:    15:06
 * 类描述：  具有回弹效果的 listview
 */
public class ElasticListview extends ListView {

    /**
     * 最大的 下拉距离
     */
    float maxY = 500;

    /**
     * 移动的因子
     */
    float MOVE_FACTOR = 0.6f;

    /**
     * 头部的 视图
     */
    View headView;
    /**
     * 底部的视图
     */
    View footView;

    /***
     * 标识符，  是否是上啦
     * 默认为下拉
     */
    boolean isUp = false;

    Scroller mScroller;

    public ElasticListview(Context context) {
        super(context);
        mScroller = new Scroller(context);
        init();
    }

    public ElasticListview(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
        init();
    }

    public ElasticListview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mScroller = new Scroller(context);
        init();
    }


    void init() {
        headView = new View(getContext());
        footView = new View(getContext());
        headView.setLayoutParams(new LayoutParams(0, 0));
        //获取到 控件大小，  底部的 view 应该 填充满一个屏幕
        footView.setLayoutParams(new LayoutParams(0, LayoutParams.MATCH_PARENT));
        originalHeight = footView.getHeight();
        addHeaderView(headView);
        addFooterView(footView);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        Log.d("--------", "height:" + height + "getHeight()" + DensityUtils.px2dp(getContext(), getHeight()) + "originalHeight: " + originalHeight);

    }

    int originalTop = 0;
    int originalHeight = 0;

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        originalTop = t;
    }

    float lastY = 0;
    float move_length = 0;
    //一个 基准

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float y = ev.getY();
                //这里改为 根据 下拉距离来减少
                MOVE_FACTOR -= 0.05 * (y - lastY) / 100;

                maxY = (y - lastY) * MOVE_FACTOR;
                move_length += maxY;
                lastY = y;
//                if (IsBorder())
                if (maxY > 0) isUp = false;
                else isUp = true;
                if (isUp) {
                    footView.setLayoutParams(new LayoutParams(0, (int) -move_length + originalHeight));
                } else {
                    headView.setLayoutParams(new LayoutParams(0, (int) move_length));
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                move_length = 0;
                MOVE_FACTOR = 0.6f;
                Log.d("TAG", "onTouchEvent: " + headView.getBottom() + "---------" + originalTop);
                ValueAnimator animator = null;
                if (!isUp)
                    animator = ValueAnimator.ofInt(headView.getHeight(), 0);
                else
                    animator = ValueAnimator.ofInt(footView.getHeight(), originalHeight);
                animator.setDuration(500);
                animator.setInterpolator(new DecelerateInterpolator(2f));
                animator.start();
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        if (!isUp)
                            headView.setLayoutParams(new LayoutParams(0, (int) animation.getAnimatedValue()));
                        if (isUp)
                            footView.setLayoutParams(new LayoutParams(0, (int) animation.getAnimatedValue()));
                        invalidate();
                    }
                });

                break;
        }
        return super.onTouchEvent(ev);
    }
}