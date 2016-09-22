package baizhuan.hangzhou.com.androidlibstudy.PorterDuffXfermode.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import baizhuan.hangzhou.com.androidlibstudy.util.DensityUtils;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.androidlibstudy.PorterDuffXfermode.View
 * Date:    2016/9/21.
 * Time:    9:45
 * 类描述：16 种模式展示 view
 */
public class ViewFor16  extends View {

    String  TAG="------------ViewFor16---------------";
    public ViewFor16(Context context) {
        super(context);
        init();
    }

    public ViewFor16(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ViewFor16(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    final int itemPadding= DensityUtils.dp2px(getContext(),10);

    Paint borderPaint;
    Paint paint;

    void init(){
        borderPaint=new Paint();
        borderPaint.setAntiAlias(true);
        borderPaint.setColor(Color.BLACK);
        borderPaint.setTextSize(DensityUtils.dp2px(getContext(),15));
        borderPaint.setStrokeWidth(DensityUtils.dp2px(getContext(),1));
        borderPaint.setStyle(Paint.Style.STROKE);

        paint=new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.FILL);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float itemWidth=(getWidth()- 5 * itemPadding)/4;
        //设置背景
        canvas.drawColor(Color.parseColor("#83BFB3"));
        //画边框

        //16种相交模式
//        CLEAR、SRC、DST、SRC_OVER、DST_OVER、SRC_IN、DST_IN、SRC_OUT、DST_OUT、SRC_ATOP、DST_ATOP、XOR、DARKEN、LIGHTEN、MULTIPLY、SCREEN。
        String [] modeName={"CLEAR","SRC","DST","SRC_OVER","DST_OVER","SRC_IN","DST_IN","SRC_OUT","DST_OUT","SRC_ATOP","DST_ATOP","XOR","DARKEN","LIGHTEN","MULTIPLY","SCREEN"};
        PorterDuff.Mode [] mode={PorterDuff.Mode.CLEAR,PorterDuff.Mode.SRC,PorterDuff.Mode.DST,PorterDuff.Mode.SRC_OVER,PorterDuff.Mode.DST_OVER, PorterDuff.Mode.SRC_IN,
                                 PorterDuff.Mode.DST_IN, PorterDuff.Mode.SRC_OUT, PorterDuff.Mode.DST_OUT, PorterDuff.Mode.SRC_ATOP, PorterDuff.Mode.DST_ATOP, PorterDuff.Mode.XOR,
                PorterDuff.Mode.DARKEN, PorterDuff.Mode.LIGHTEN, PorterDuff.Mode.MULTIPLY, PorterDuff.Mode.SCREEN};
        for (int  i=0;i<mode.length;i++){
            canvas.drawRect(itemPadding,itemPadding,itemPadding +itemWidth,itemPadding +itemWidth,borderPaint);
            paint.setColor(Color.YELLOW);
            //画圆
            canvas.drawCircle(itemPadding+itemWidth/4,itemPadding+itemWidth/4,itemWidth/4,paint);
            //画框框
            paint.setColor(Color.parseColor("#5FA3FC"));
            paint.setXfermode(new PorterDuffXfermode(mode[i]));
            canvas.drawRect(itemPadding+itemWidth/4,itemPadding+itemWidth/4,itemPadding+itemWidth/4*3,itemPadding+itemWidth/4*3,paint);
            paint.setXfermode(null);
            //画文字

            String  title=modeName[i];
            Log.d(TAG, "onDraw: title:"+title+"\t textWidth:"+borderPaint.measureText(title) );
            canvas.drawText(title,itemPadding+(itemWidth -borderPaint.measureText(title)  )/2,itemWidth+itemPadding,borderPaint);

            //画布平移， 画下一个
            canvas.translate(itemWidth+ itemPadding,0);

            if((i+1)%4==0){
                canvas.translate(-getWidth()+itemPadding,itemWidth+itemPadding);
            }
        }


    }
}
