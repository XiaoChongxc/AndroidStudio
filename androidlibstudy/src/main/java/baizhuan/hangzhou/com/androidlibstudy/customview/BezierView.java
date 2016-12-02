package baizhuan.hangzhou.com.androidlibstudy.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.androidlibstudy.customview
 * Date:    16-11-24.
 * Time:    上午11:10
 * 类描述：
 *
 * @vesion
 */
public class BezierView extends View {

    public BezierView(Context context) {
        super(context);
        init();
    }

    public BezierView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BezierView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    Paint mPaint;
    Paint mPaint2;
    Path mPath;
    Canvas mCanvas;
    Bitmap mBitmap;
    //点击到了 圆内部
    boolean inCircle = false;


    int maxLength = 100;
    int mCircleRadius = 200;

    int mWidth, mHeight;

    float startX;
    float startY;

    private void init() {
        mPath = new Path();

        mPaint = new Paint();
        mPaint.setColor(Color.CYAN);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        //辅助线
        mPaint2 = new Paint();
        mPaint2.setColor(Color.RED);
        mPaint2.setAntiAlias(true);
        mPaint2.setStyle(Paint.Style.STROKE);
        mPaint2.setStrokeWidth(2);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        //中间画圆
        startX = mWidth / 2;
        startY = mHeight / 2;

        mCanvas.drawCircle(startX, startY, mCircleRadius, mPaint);
        //辅助线
        mCanvas.drawLine(startX - 200, startY, startX + 500, startY, mPaint2);
        mCanvas.drawLine(startX, startY - 200, startX, startY + 500, mPaint2);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap, new Matrix(), null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float x = event.getX();
                float y = event.getY();
                float centreX = mWidth / 2;
                float centreY = mHeight / 2;
                //判断是否在元里面
                if (Math.pow(centreX - x, 2) + Math.pow(centreY - y, 2) > Math.pow(20, 2)) {
                    inCircle = true;
                } else {
                    inCircle = false;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                float sx = event.getX();
                float sy = event.getY();


                //拖动圆
                mCanvas.drawCircle(sx, sy, mCircleRadius, mPaint);
                //辅助线
                mCanvas.drawLine(sx - 200, sy, sx + 500, sy, mPaint2);
                mCanvas.drawLine(sx, sy - 200, sx, sy + 500, mPaint2);
                // 圆心连线
                mCanvas.drawLine(startX, startY, sx, sy, mPaint2);
                mCanvas.drawCircle(startX + (sx - startX) / 2, startY + (sy - startY) / 2, 10, mPaint2);
                metaBallVersion1(mCanvas, sx, sy);
                postInvalidate();
                break;
            case MotionEvent.ACTION_UP:
                inCircle = false;
                break;
        }

        return true;
    }


    private void metaBallVersion1(Canvas canvas, float x, float y) {
        int fx = 1;
        int fy = 1;

        float controlX = (startX + x) / 2;
        float controlY = (startY + y) / 2;

        float distance = (float) Math.sqrt((controlX - startX) * (controlX - startX) + (controlY - startY) * (controlY - startY));
        double a = Math.acos(mCircleRadius / distance);

        double b = Math.acos((controlX - startX) / distance);
        float offsetX1 = (float) (mCircleRadius * Math.cos(a - b));
        float offsetY1 = (float) (mCircleRadius * Math.sin(a - b));

        float tanX1 = startX + offsetX1 * fx;
        float tanY1 = startY - offsetY1 * fy;

        double c = Math.acos((controlY - startY) / distance);
        float offsetX2 = (float) (mCircleRadius * Math.sin(a - c));
        float offsetY2 = (float) (mCircleRadius * Math.cos(a - c));

        float tanX2 = startX - offsetX2 * fx;
        float tanY2 = startY + offsetY2 * fy;

        double d = Math.acos((y - controlY) / distance);
        Log.d("/////", a + "****" + "metaBallVersion2: " + d + "****" + (y - controlY) / distance);
        float offsetX3 = (float) (mCircleRadius * Math.sin(a - d));
        float offsetY3 = (float) (mCircleRadius * Math.cos(a - d));
        Log.d("/////", Math.sin(a - d) + "***" + mCircleRadius);

        float tanX3 = x + offsetX3 * fx;
        float tanY3 = y - offsetY3 * fy;

        double e = Math.acos((x - controlX) / distance);
        float offsetX4 = (float) (mCircleRadius * Math.cos(a - e));
        float offsetY4 = (float) (mCircleRadius * Math.sin(a - e));

        float tanX4 = x - offsetX4 * fx;
        float tanY4 = y + offsetY4 * fy;

        mPath.reset();
        mPath.moveTo(tanX1, tanY1);
        mPath.quadTo(controlX, controlY, tanX3, tanY3);
        mPath.lineTo(tanX4, tanY4);
        mPath.quadTo(controlX, controlY, tanX2, tanY2);
        canvas.drawPath(mPath, mPaint);

        // 辅助线
        canvas.drawCircle(tanX1, tanY1, 5, mPaint);
        canvas.drawCircle(tanX2, tanY2, 5, mPaint);
        canvas.drawCircle(tanX3, tanY3, 5, mPaint);
        canvas.drawCircle(tanX4, tanY4, 5, mPaint);
        canvas.drawLine(startX, startY, x, y, mPaint);
        canvas.drawLine(0, startY, startX + mCircleRadius + 400, startY, mPaint);
        canvas.drawLine(startX, 0, startX, startY + mCircleRadius + 50, mPaint);
        canvas.drawLine(x, y, x, 0, mPaint);
        canvas.drawCircle(controlX, controlY, 5, mPaint);
        canvas.drawLine(startX, startY, tanX1, tanY1, mPaint);
        canvas.drawLine(tanX1, tanY1, controlX, controlY, mPaint);

        canvas.drawPath(mPath, mPaint);
    }
}
