package baizhuan.hangzhou.com.android5study.CollapsingToolbarLayout;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.android5study.CollapsingToolbarLayout
 * Date:    2016-07-18
 * Time:    11:34
 * 类描述：
 */
public class ToolbarBehavior2 extends CoordinatorLayout.Behavior {
    String TAG = "ToolbarBehavior2";


    int offset = 0;

    public ToolbarBehavior2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        Log.d(TAG, "layoutDependsOn: ");
        return dependency instanceof NestedScrollView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        return super.onDependentViewChanged(parent, child, dependency);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        Log.d(TAG, "onStartNestedScroll: ");
        return true;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        boolean child1 = child instanceof TextView;
        boolean target1 = target instanceof NestedScrollView;
        Log.d(TAG, "onNestedScroll: " + child1 + "******" + target1);
        Log.d(TAG, "****************onNestedScroll:****************" + offset);
        if (Math.abs(offset) >= 60) {
            if (offset >= 0) {
                child.setTranslationY(-60);
                if (dyConsumed < 0) {
                    offset += dyConsumed;
                }
            } else {
                child.setTranslationY(60);
                if (dyConsumed > 0) {
                    offset += dyConsumed;
                }
            }
        } else {
            offset += dyConsumed;
            child.setTranslationY(-offset);
        }
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target) {
        Log.d(TAG, "onStopNestedScroll: ");
        offset = 0;
        super.onStopNestedScroll(coordinatorLayout, child, target);
    }


    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY, boolean consumed) {
        Log.d(TAG, "onNestedFling: ");
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
    }
}
