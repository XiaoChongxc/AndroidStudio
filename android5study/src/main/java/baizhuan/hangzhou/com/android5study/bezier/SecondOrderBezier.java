package baizhuan.hangzhou.com.android5study.bezier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.android5study.bezier
 * Date:    2016-08-03
 * Time:    16:12
 * 类描述： 二阶贝塞尔曲线
 */
public class SecondOrderBezier extends View {

    private Paint mPaintBezier;
    private Paint mPaintAuxiliary;
    private Paint mPaintAuxiliaryText;

    Path mPath;

    int startX, startY;
    int endX, endY;
    int mAuxiliaryX, mAuxiliaryY;


    public SecondOrderBezier(Context context) {
        super(context);
        init();
    }

    public SecondOrderBezier(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SecondOrderBezier(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        startX = w / 4;
        startY = h / 2 - 200;

        endX = w / 4 * 3;
        endY = h / 2 - 200;
    }

    void init() {
        mPaintBezier = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintBezier.setStyle(Paint.Style.STROKE);
        mPaintBezier.setStrokeWidth(8);

        mPaintAuxiliary = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintAuxiliary.setStyle(Paint.Style.STROKE);
        mPaintAuxiliary.setStrokeWidth(2);

        mPaintAuxiliaryText = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintAuxiliaryText.setStyle(Paint.Style.STROKE);
        mPaintAuxiliaryText.setTextSize(20);

        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPath.reset();
        mPath.moveTo(startX, startY);
        canvas.drawText("开始点", startX, startY, mPaintAuxiliaryText);
        canvas.drawText("控制点", mAuxiliaryX, mAuxiliaryY, mPaintAuxiliaryText);
        canvas.drawText("结束点", endX, endY, mPaintAuxiliaryText);

        canvas.drawLine(startX, startY, mAuxiliaryX, mAuxiliaryY, mPaintAuxiliary);
        canvas.drawLine(endX, endY, mAuxiliaryX, mAuxiliaryY, mPaintAuxiliary);

        mPath.quadTo(mAuxiliaryX, mAuxiliaryY, endX, endY);
        canvas.drawPath(mPath, mPaintBezier);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                mAuxiliaryX = (int) event.getX();
                mAuxiliaryY = (int) event.getY();
                invalidate();
        }
        return true;
    }
}
