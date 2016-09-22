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
 * 类描述： 三阶贝塞尔曲线
 */
public class ThreeOrderBezier extends View {

    private Paint mPaintBezier;
    private Paint mPaintAuxiliary;
    private Paint mPaintAuxiliaryText;

    Path mPath;

    boolean isSecondPoint=false;

    int startX, startY;
    int endX, endY;
    int mAuxiliaryX1, mAuxiliaryY1;
    int mAuxiliaryX2, mAuxiliaryY2;


    public ThreeOrderBezier(Context context) {
        super(context);
        init();
    }

    public ThreeOrderBezier(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ThreeOrderBezier(Context context, AttributeSet attrs, int defStyleAttr) {
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
        canvas.drawText("控制点1", mAuxiliaryX1, mAuxiliaryY1, mPaintAuxiliaryText);
        canvas.drawText("控制点2", mAuxiliaryX2, mAuxiliaryY2, mPaintAuxiliaryText);
        canvas.drawText("结束点", endX, endY, mPaintAuxiliaryText);

        canvas.drawLine(startX, startY, mAuxiliaryX1, mAuxiliaryY1, mPaintAuxiliary);
        canvas.drawLine(endX, endY, mAuxiliaryX2, mAuxiliaryY2, mPaintAuxiliary);
        canvas.drawLine(mAuxiliaryX1, mAuxiliaryY1, mAuxiliaryX2, mAuxiliaryY2, mPaintAuxiliary);
        // 三阶贝塞尔曲线
        mPath.cubicTo(mAuxiliaryX1, mAuxiliaryY1, mAuxiliaryX2, mAuxiliaryY1, endX, endY);
        canvas.drawPath(mPath, mPaintBezier);
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {

            case MotionEvent.ACTION_POINTER_DOWN:
                isSecondPoint = true;
                break;
            case MotionEvent.ACTION_MOVE:
                mAuxiliaryX1 = (int) event.getX(0);
                mAuxiliaryY1 = (int) event.getY(0);
                if (isSecondPoint) {
                    mAuxiliaryX2 = (int) event.getX(1);
                    mAuxiliaryY2 = (int) event.getY(1);
                }
                invalidate();
                break;
            case MotionEvent.ACTION_POINTER_UP:
                isSecondPoint = false;
                break;
        }
        return true;
    }
}
