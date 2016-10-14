package baizhuan.hangzhou.com.androidlibstudy.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import baizhuan.hangzhou.com.androidlibstudy.util.DensityUtils;

/**
 * author :   Xchong
 * 项目名：   AndroidStudio2
 * 包名   :   baizhuan.hangzhou.com.androidlibstudy.customview
 * 日期   :   2016/9/28
 * 时间   ：  10:22
 * 描述   ：
 */
public class CircleProgress  extends View {


    private Paint paint;

    private RectF rectF;

    private int stroke_width;
    private final int  default_stroke_width= DensityUtils.dp2px(getContext(),2);

    public CircleProgress(Context context) {
        this(context, null, 0);
    }

    public CircleProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    void  init(){

        stroke_width=default_stroke_width;
        paint=new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(stroke_width);
        paint.setColor(Color.BLUE);

        rectF=new RectF();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        rectF.set(stroke_width / 2, stroke_width / 2,DensityUtils.dp2px(getContext(),100)- stroke_width / 2, DensityUtils.dp2px(getContext(),100)- stroke_width / 2);
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        canvas.drawArc(rectF,0,120,false,paint);
        canvas.translate(DensityUtils.dp2px(getContext(),110),0);
        paint.setColor(Color.BLACK);
        canvas.drawArc(rectF,90,120,false,paint);
        canvas.translate(DensityUtils.dp2px(getContext(),110),0);
        paint.setColor(Color.GRAY);
        canvas.drawArc(rectF,180,120,false,paint);

    }
}
