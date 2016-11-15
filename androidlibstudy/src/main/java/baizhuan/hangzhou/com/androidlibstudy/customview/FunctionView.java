package baizhuan.hangzhou.com.androidlibstudy.customview;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import baizhuan.hangzhou.com.androidlibstudy.util.DensityUtils;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.androidlibstudy.customview
 * Date:    16-11-9.
 * Time:    上午11:10
 * 类描述：  展示各种 数学函数的 类
 *
 * @vesion
 */
public class FunctionView extends View {

    public FunctionView(Context context) {
        super(context);
        init();
    }

    public FunctionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FunctionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    Paint mPaint;

    /**
     * X坐标轴颜色
     */
    int mXaxesColor;

    /**
     * Y坐标轴颜色
     */
    int mYaxesColor;
    /**
     * 布局的 宽
     */
    int mWidth;
    /**
     * 布局的高
     */
    int mHeight;

    int padding;

    /***
     * X 最大数
     */
    int maxXNum;
    /***
     * X 开始数
     */
    int startXNum;

    /***
     * Y 最大数
     */
    int maxYNum;
    /***
     * Y 开始数
     */
    int startYNum;

    Path mPath;
    int lineColor;


    /***
     * 初始化方法
     */
    private void init() {
        mPaint =new Paint();
        mPaint.setTextSize(DensityUtils.dp2px(getContext(),20));
        mPaint.setAntiAlias(true);
        padding = DensityUtils.dp2px(getContext(), 10);
        mXaxesColor = Color.BLACK;
        mYaxesColor = Color.BLACK;

        maxXNum = 5;
        startXNum = 0;
        maxYNum = 10;
        startYNum = 0;
        mPath = new Path();
        mPath.moveTo(0, 0);
        lineColor = Color.GREEN;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;

    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);

        //文字高度

        int textHeight = (int) (mPaint.descent() - mPaint.ascent());

        //画 横轴
//        canvas.drawLine(padding, mHeight - padding - DensityUtils.dp2px(getContext(), 30), mWidth - padding, mHeight - padding - DensityUtils.dp2px(getContext(), 30), mPaint);
        mPaint.setColor(mXaxesColor);
        drawAL(padding, mHeight - padding * 2 - textHeight, mWidth - padding, mHeight - padding * 2 - textHeight, canvas, mPaint);
        //画  字 和 刻度
        int perPadding = (mWidth - padding * 2) / (maxXNum - startXNum);

        for (int i = 0; i < maxXNum - startXNum; i++) {
            String curText = (startXNum + i) + "";
            int curX = padding + i * perPadding;
            //画 文字
            canvas.drawText(curText, 0, curText.length(), curX, mHeight - padding, mPaint);
            //画 刻度
            canvas.drawLine(curX, mHeight - padding * 2 - textHeight, curX, mHeight - padding * 2 - textHeight - DensityUtils.dp2px(getContext(), 2), mPaint);
        }

        //画 纵轴
//        canvas.drawLine(padding, padding, padding, mHeight - padding, mPaint);
        mPaint.setColor(mYaxesColor);
        drawAL(padding, mHeight - padding,padding, padding,  canvas, mPaint);

        perPadding = (mHeight - padding * 2 - textHeight) / (maxYNum - startYNum);
        for (int i = 0; i < maxYNum - startYNum; i++) {
            String curText = (startYNum + i) + "";
            int curY = mHeight - padding * 2 + textHeight + i * perPadding;
            //画 文字
            canvas.drawText(curText, 0, curText.length(), mWidth - padding, curY, mPaint);
            //画 刻度
            canvas.drawLine(mWidth - padding, curY, mWidth - padding + DensityUtils.dp2px(getContext(), 2), curY, mPaint);
        }


        // 画 函数 线条

        ValueAnimator valueAnimator = ValueAnimator.ofObject(new PointEvaluator(),new Point(startXNum,startYNum),new Point(maxXNum,maxYNum));
        valueAnimator.setDuration(1000);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                mPaint.setColor(lineColor);
//                Point mPoint = (Point) valueAnimator.getAnimatedValue();
//                mPath.lineTo(mPoint.getX(), mPoint.getY());
//                canvas.drawPath(mPath, mPaint);
//                postInvalidate();
            }
        });

        valueAnimator.start();

//        canvas.drawPath();


    }


    /**
     * 画箭头
     *
     * @param sx
     * @param sy
     * @param ex
     * @param ey
     */
    public void drawAL(int sx, int sy, int ex, int ey, Canvas myCanvas, Paint myPaint) {
        double H = 8; // 箭头高度
        double L = 3.5; // 底边的一半
        int x3 = 0;
        int y3 = 0;
        int x4 = 0;
        int y4 = 0;
        double awrad = Math.atan(L / H); // 箭头角度
        double arraow_len = Math.sqrt(L * L + H * H); // 箭头的长度
        double[] arrXY_1 = rotateVec(ex - sx, ey - sy, awrad, true, arraow_len);
        double[] arrXY_2 = rotateVec(ex - sx, ey - sy, -awrad, true, arraow_len);
        double x_3 = ex - arrXY_1[0]; // (x3,y3)是第一端点
        double y_3 = ey - arrXY_1[1];
        double x_4 = ex - arrXY_2[0]; // (x4,y4)是第二端点
        double y_4 = ey - arrXY_2[1];
        Double X3 = new Double(x_3);
        x3 = X3.intValue();
        Double Y3 = new Double(y_3);
        y3 = Y3.intValue();
        Double X4 = new Double(x_4);
        x4 = X4.intValue();
        Double Y4 = new Double(y_4);
        y4 = Y4.intValue();
        // 画线
        myCanvas.drawLine(sx, sy, ex, ey, myPaint);
        Path triangle = new Path();
        triangle.moveTo(ex, ey);
        triangle.lineTo(x3, y3);
        triangle.lineTo(x4, y4);
        triangle.close();
        myCanvas.drawPath(triangle, myPaint);

    }

    // 计算
    public double[] rotateVec(int px, int py, double ang, boolean isChLen, double newLen) {
        double mathstr[] = new double[2];
        // 矢量旋转函数，参数含义分别是x分量、y分量、旋转角、是否改变长度、新长度
        double vx = px * Math.cos(ang) - py * Math.sin(ang);
        double vy = px * Math.sin(ang) + py * Math.cos(ang);
        if (isChLen) {
            double d = Math.sqrt(vx * vx + vy * vy);
            vx = vx / d * newLen;
            vy = vy / d * newLen;
            mathstr[0] = vx;
            mathstr[1] = vy;
        }
        return mathstr;
    }

    class PointEvaluator implements TypeEvaluator<Point> {
        public PointEvaluator() {

        }

        @Override
        public Point evaluate(float v, Point point, Point t1) {
            float x = point.getX() * (1 + v);

            return new Point(x, Fmethod(x));
        }
    }

    private float Fmethod(float v) {

        return v * v;
    }

    /***
     * 坐标点 类
     */
    class Point {

        private float x;
        private float y;

        public float getX() {
            return x;
        }

        public void setX(float x) {
            this.x = x;
        }

        public float getY() {
            return y;
        }

        public void setY(float y) {
            this.y = y;
        }

        public Point(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }

}
