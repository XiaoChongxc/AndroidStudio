package baizhuan.hangzhou.com.android5study.bezier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import baizhuan.hangzhou.com.android5study.util.DensityUtils;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.android5study.bezier
 * Date:    2016-08-03
 * Time:    17:52
 * 类描述：  波浪 ，贝塞尔曲线
 */
public class WaveBezier extends View {

    Path mPath;
    Paint mPaint;

    int maxWaveCount = 3;

    //当前的浪的高度
    int mWaveY = 0;

    int mWaveHeight = DensityUtils.dp2px(getContext(), 20);

    public WaveBezier(Context context) {
        super(context);
        init();
    }

    public WaveBezier(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WaveBezier(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    void init() {
        mPath = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.LTGRAY);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        mWaveY = height / 4 * 3;
        for (int i = 0; i < maxWaveCount; i++) {
            int waveLength = width / maxWaveCount;
            mPath.quadTo(i * waveLength, mWaveY, i * waveLength + waveLength / 2, mWaveY + mWaveHeight);
            mPath.quadTo(i * waveLength + waveLength / 2, mWaveY + mWaveHeight, (i + 1) * waveLength, mWaveY);
        }
        mPath.lineTo(width, height);
        mPath.lineTo(0, height);
        mPath.close();
        canvas.drawPath(mPath, mPaint);

    }


}
