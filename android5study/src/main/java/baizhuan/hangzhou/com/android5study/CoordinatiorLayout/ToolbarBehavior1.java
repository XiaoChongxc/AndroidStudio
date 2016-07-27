package baizhuan.hangzhou.com.android5study.CoordinatiorLayout;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import baizhuan.hangzhou.com.android5study.R;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.android5study.CoordinatiorLayout
 * Date:    2016-07-14
 * Time:    17:24
 * 类描述： 自定义的 behavior  用于 和  一些控件 联动
 */
public class ToolbarBehavior1 extends CoordinatorLayout.Behavior<Toolbar> {
    private static final String TAG = "ToolbarBehavior1";

    int tvStartX = 0;
    int offset = 0;
    int sreenWidth = 0;
    boolean isIntercepter = false;

    int imgheight = 0;


    public ToolbarBehavior1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onMeasureChild(CoordinatorLayout parent, Toolbar child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        Log.d(TAG, "onMeasureChild");
        tvStartX = child.findViewById(R.id.tv_search).getLeft();
        sreenWidth = child.getWidth();
        return super.onMeasureChild(parent, child, parentWidthMeasureSpec, widthUsed, parentHeightMeasureSpec, heightUsed);
    }


    //CoordinatorLayout 开始 滑动
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, Toolbar child, View directTargetChild, View target, int nestedScrollAxes) {
        Log.d(TAG, "onStartNestedScroll: true****nestedScrollAxes：" + nestedScrollAxes);

        return true;  //返回true  才会收到后续的 滑动

    }

    //CoordinatorLayout 正在 滑动
    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, Toolbar toolbar, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {

        offset += dyConsumed;
        resetLayout(toolbar, target);
        //        if (raise == 1) {   //拦截事件 下发
//
//        }

    }


//    @Override
//    public void onNestedScroll(CoordinatorLayout coordinatorLayout, Toolbar toolbar, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
//        startOffset = 0;
//        endOffset = context.getResources().getDimensionPixelOffset(R.dimen.header_height) - toolbar.getHeight();
//        offset += dyConsumed;
//        if (offset <= startOffset) {  //alpha为0
//            toolbar.getBackground().setAlpha(0);
//        } else if (offset > startOffset && offset < endOffset) { //alpha为0到255
//            float precent = (float) (offset - startOffset) / endOffset;
//            int alpha = Math.round(precent * 255);
//            toolbar.getBackground().setAlpha(alpha);
//        } else if (offset >= endOffset) {  //alpha为255
//            toolbar.getBackground().setAlpha(255);
//        }
//    }

    //CoordinatorLayout 在快速滑动
    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, Toolbar child, View target, float velocityX, float velocityY, boolean consumed) {
        Log.d(TAG, "----------------- onNestedFling: -----------------");
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, Toolbar child, int layoutDirection) {
        Log.d(TAG, "onLayoutChild: ");
        return super.onLayoutChild(parent, child, layoutDirection);
    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, Toolbar child, final View target) {
        Log.d(TAG, "onStopNestedScroll: ");
//        super.onStopNestedScroll(coordinatorLayout, child, target);
        //自动 上滑  下滑
        int start = 0;
        int end = 0;
        if (offset > imgheight / 2) {
            //下滑
            target.scrollBy(0, imgheight - offset);
//            target.scrollTo(0, imgheight);
            start = offset;
            end = imgheight;
            offset = imgheight;
        } else {
            //上滑
            target.scrollBy(0, -offset);
//            target.scrollTo(0, 0);
            start = offset;
            end = 0;
            offset = 0;
        }

//        ValueAnimator animator = ValueAnimator.ofInt(start, end);
//        animator.setDuration(500);
//        animator.start();
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                int y = (int) animation.getAnimatedValue();
//                target.scrollTo(0,y);
//            }
//        });
        resetLayout(child, target);
    }

    private void resetLayout(View toolbar, View target) {
        TextView search = (TextView) toolbar.findViewById(R.id.tv_search);
        ImageView img_right = (ImageView) toolbar.findViewById(R.id.img_search);
        ImageView img = (ImageView) target.findViewById(R.id.header);
        LinearLayout llParnet = (LinearLayout) target.findViewById(R.id.ll_parent);
        LinearLayout llParnet2 = (LinearLayout) toolbar.findViewById(R.id.ll_parent2);


        float raise = offset / (float) img.getHeight();
        if (raise >= 1) raise = 1;

        int width = (int) ((sreenWidth - search.getWidth()) * (1 - raise)) + search.getWidth();
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, 90);
        lp.gravity = Gravity.CENTER;
        llParnet.setLayoutParams(lp);
        imgheight = img.getHeight();
        search.setX(tvStartX + raise * toolbar.getWidth());
        toolbar.getBackground().setAlpha((int) (raise * 255));
        img_right.setY(-offset);
        if (raise == 1) llParnet2.setVisibility(View.VISIBLE);
        else llParnet2.setVisibility(View.GONE);


    }

}
