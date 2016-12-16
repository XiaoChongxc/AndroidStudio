package baizhuan.hangzhou.com.androidlibstudy.customview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.androidlibstudy.customview
 * Date:    16-11-28.
 * Time:    下午4:26
 * 类描述： 贝塞尔测试 ， 水波浪纹
 *
 * @vesion
 */
public class BezierWave extends View implements View.OnClickListener {
    public BezierWave(Context context) {
        super(context);
        init();
    }

    public BezierWave(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BezierWave(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    int mWaveHeight = 60;
    int mWaveLength = 700;
    int mWaveCount = 5;
    int mWidth, mHeight;
    int mCurHeight = 300;
    int mColor1 = Color.parseColor("#99289fff");
    Path path;
    Path path2;
    private OnWaveMovingLinstener onWaveMovingLinstener;

    Paint paint;
    int mOffset;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
//        mMaveCount = (int) Math.round(mWidth / mMaveLength + 1.5);
    }

    void init() {
        path = new Path();
        path2 = new Path();
        paint = new Paint();
        paint.setColor(mColor1);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        setOnClickListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mWaveCount = mWidth / mWaveLength + 1;
        int mCenterY = mHeight - mCurHeight;
        path.reset();
        path.moveTo(-mWaveLength + mOffset, mCenterY);
        for (int i = 0; i < mWaveCount + 1; i++) {
            path.quadTo((-mWaveLength * 3 / 4) + (i * mWaveLength) + mOffset, mCenterY + mWaveHeight, (-mWaveLength / 2) + (i * mWaveLength) + mOffset, mCenterY);
            path.quadTo((-mWaveLength / 4) + (i * mWaveLength) + mOffset, mCenterY - mWaveHeight, i * mWaveLength + mOffset, mCenterY);
        }
        path.lineTo(mWidth, mHeight);
        path.lineTo(0, mHeight);
        path.close();

        path2.reset();
        path2.moveTo(-mWaveLength * 2 + mOffset * 2, mCenterY);
        for (int i = -2; i < mWaveCount + 3; i++) {
            path2.quadTo((-mWaveLength * 3 / 4) + (i * mWaveLength) + mOffset * 2, mCenterY + mWaveHeight, (-mWaveLength / 2) + (i * mWaveLength) + mOffset * 2, mCenterY);
            path2.quadTo((-mWaveLength / 4) + (i * mWaveLength) + mOffset * 2, mCenterY - mWaveHeight, i * mWaveLength + mOffset * 2, mCenterY);
        }
        path2.lineTo(mWidth, mHeight);
        path2.lineTo(0, mHeight);
        path2.close();
        canvas.drawPath(path, paint);
        canvas.drawPath(path2, paint);
        if (onWaveMovingLinstener != null) {
            int waveOffset = (int) (mOffset * 1f / mWaveLength * mWaveHeight);
            onWaveMovingLinstener.onWaveMove(waveOffset);
        }

    }

    @Override
    public void onClick(View v) {
        ValueAnimator animator = ValueAnimator.ofInt(0, mWaveLength);
        animator.setDuration(2000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mOffset = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.start();
    }

    /**
     * 波浪运动的回调接口
     */
    interface OnWaveMovingLinstener {
        void onWaveMove(int offset);
    }
}
