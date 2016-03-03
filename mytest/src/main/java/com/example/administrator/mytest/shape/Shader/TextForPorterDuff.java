package com.example.administrator.mytest.shape.Shader;/**
 * Created by  @just_for_happy (@开心就好)   on 2016/1/4.
 * Date: 2016-01-04
 * Time: 11:10
 * Usege: 功能描述。。。
 */

/**
 * author: just_for_happy(@开心就好)
 * Date: 2016-01-04 
 * Time: 11:10 
 * FIXME 
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.mytest.R;

import utils.DensityUtils;

/** 图片的相交模式 */
public class TextForPorterDuff extends View {

    public TextForPorterDuff(Context context) {
        super(context);
        init();
    }

    public TextForPorterDuff(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextForPorterDuff(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }




    private void init() {
        mPaint = new Paint();

    }


    Paint mPaint;

    Mode[] modes = { Mode.CLEAR, Mode.SRC, Mode.DST, Mode.SRC_OVER,
            Mode.DST_OVER, Mode.SRC_IN, Mode.DST_IN, Mode.SRC_OUT,
            Mode.DST_OUT, Mode.SRC_ATOP, Mode.DST_ATOP, Mode.XOR, Mode.DARKEN,
            Mode.LIGHTEN, Mode.MULTIPLY, Mode.SCREEN };

    String[] Name = { "Clear", "Src", "Dst", "SrcOver", "DstOver", "SrcIn",
            "DstIn", "SrcOut", "DstOut", "SRCAtomP", "DstAtop", "Xor",
            "DarKen", "Lighten", "Multply", "Screen" };


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int itemWidth=(width-(3*DensityUtils.dp2px(getContext(), 20)))/4;

        canvas.drawColor(Color.GRAY);

        canvas.translate(0, DensityUtils.dp2px(getContext(), 20));
        mPaint.setTextSize(30);

        for (int i = 0; i < modes.length; i++) {
            // 画出 当前的 item的外边框
            mPaint.setColor(Color.BLACK);
            mPaint.setStyle(Style.STROKE);
            canvas.drawRect(new Rect(0, 0, itemWidth, itemWidth), mPaint);


            canvas.drawBitmap(createImage(modes[i], itemWidth), 0,0, mPaint);
            canvas.drawText(Name[i], 0, 0, mPaint);
            canvas.translate(itemWidth + DensityUtils.dp2px(getContext(), 20), 0);
            if ((i + 1) % 4 == 0) {
                canvas.translate(-width - DensityUtils.dp2px(getContext(), 20),
                        itemWidth + DensityUtils.dp2px(getContext(), 5));
            }
        }
//		Bitmap bit=BitmapFactory.decodeResource(getResources(), R.drawable.dst);
//		canvas.drawBitmap(createCircleImage(bit, DensityUtils.dp2px(getContext(), 100)), 0,0, mPaint);
    }

    private Bitmap String2Bitmap(String str){
        int width= (int) mPaint.measureText(str);
        int height=(int) (mPaint.getFontMetrics().bottom-mPaint.getFontMetrics().top);

        Bitmap bit=Bitmap.createBitmap(width, height, Config.ARGB_8888);
        Canvas canvas=new Canvas(bit);
        canvas.drawColor(Color.WHITE);
        canvas.drawText(str, 0, 0, mPaint);
        return bit;
    }

    /**
     * 根据原图和变长绘制圆形图片
     *
     * @return
     */
    private Bitmap createImage( Mode mode,int itemWidth)
    {
        final Paint paint = new Paint();
        paint.setStyle(Style.FILL);
        paint.setAntiAlias(true);
        Bitmap target = Bitmap.createBitmap(itemWidth, itemWidth, Config.ARGB_8888);

        /**
         * 产生一个同样大小的画布
         */
        Canvas canvas = new Canvas(target);
        /**
         * 首先绘制圆形
         */
        paint.setColor(Color.YELLOW);
        canvas.drawCircle(itemWidth / 4, itemWidth / 4, itemWidth / 4, paint);
//        Bitmap source1 =BitmapFactory.decodeResource(getResources(), R.drawable.src);
//        canvas.drawBitmap(source1,0, 0, paint);
//
        paint.setXfermode(new PorterDuffXfermode(mode));
        /**
         * 绘制图片
         */
        Bitmap source = BitmapFactory.decodeResource(getResources(), R.drawable.dst);
        canvas.drawBitmap(source,itemWidth / 4, itemWidth / 4, paint);


        return target;
    }

}
