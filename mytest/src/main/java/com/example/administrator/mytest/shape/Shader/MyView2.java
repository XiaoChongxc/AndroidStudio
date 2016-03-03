package com.example.administrator.mytest.shape.Shader;/**
 * Created by  @just_for_happy (@开心就好)   on 2016/1/4.
 * Date: 2016-01-04
 * Time: 10:35
 * Usege: 功能描述。。。
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.mytest.R;

import utils.DensityUtils;

/**
 * author: just_for_happy(@开心就好)
 * Date: 2016-01-04 
 * Time: 10:35 
 * FIXME  shader  的五个直接子类 BitmapShader  /LinearGradient  /RadialGradient=    /ComposeShade /SweepGradient
 */
public class MyView2   extends View {
    public MyView2(Context context) {
        super(context);
    }
    public MyView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public MyView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }






    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width=getWidth();
        int padding=DensityUtils.dp2px(getContext(), 5);
        int itemWidth=(width- padding*3)/4;
        Paint mPaint=new Paint();
        mPaint.setStrokeWidth(50);
        mPaint.setTextSize(20);
//        canvas.save();      //保存画布的初始状态
        canvas.drawText("BitmapShader 圆形，椭圆形",0,20,mPaint);
        canvas.translate(0,padding*2);
    //  bitmapShader
        Bitmap mbitmap= BitmapFactory.decodeResource(getResources(), R.drawable.changba);
        BitmapShader mBitmapShader =new BitmapShader(mbitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mPaint.setShader(mBitmapShader);
        canvas.drawCircle(itemWidth / 2, itemWidth / 2, itemWidth / 2, mPaint);//给bitmapShader  加上圆
        canvas.translate(padding, 0);
        canvas.drawOval(new RectF(itemWidth, 0, itemWidth * 3, itemWidth), mPaint);//给bitmapShader  加上椭圆
        canvas.translate(-padding, 0);
//        canvas.restore();   //回复画布
        mPaint.setShader(null);
        canvas.translate(0, itemWidth + padding);
        canvas.drawText("线性渐变 CLAMP  position= null   position！=null ", 0, 20, mPaint);
        canvas.translate(0, padding * 2);
        //LinearGradient
        int mColorLinear[] = {Color.RED, Color.GREEN, Color.BLUE};
        LinearGradient mLinearGradient=new LinearGradient(0,0,100,10,mColorLinear,null, Shader.TileMode.CLAMP);
        mPaint.setShader(mLinearGradient);
        canvas.drawRect(0, 0, itemWidth * 2, itemWidth / 2, mPaint);

        mLinearGradient=new LinearGradient(0,0,100,10,mColorLinear,new float[]{0,0.5f,1}, Shader.TileMode.CLAMP);
        mPaint.setShader(mLinearGradient);
        canvas.drawRect(itemWidth * 2 + padding, 0, width, itemWidth / 2, mPaint);
        //---------------------------------------/
        mPaint.setShader(null);
        canvas.translate(0, itemWidth/2 + padding);

        canvas.drawText("线性渐变 position= null  REPEAT  MIRROR", 0, 20, mPaint);
        canvas.translate(0, padding * 2);
        mLinearGradient=new LinearGradient(0,0,100,10,mColorLinear,null, Shader.TileMode.REPEAT);
        mPaint.setShader(mLinearGradient);
        canvas.drawRect(0, 0, itemWidth * 2, itemWidth / 2, mPaint);

        mLinearGradient=new LinearGradient(0,0,100,10,mColorLinear,null, Shader.TileMode.MIRROR);
        mPaint.setShader(mLinearGradient);
        canvas.drawRect(itemWidth * 2 + padding, 0, width, itemWidth / 2, mPaint);

        mPaint.setShader(null);
        canvas.translate(0, itemWidth/2 + padding);

        //环形渐变
//        　其中，参数x表示环形的圆心x坐标；参数y表示环形的圆心y坐标；参数radius表示环形的半径；
////        参数colors表示环形渐变的颜色数组；参数positions用来指定颜色数组的相对位置；参数tile表示平铺的方式。

        canvas.drawText("环形渐变  Climp REPEAT", 0, 20, mPaint);
        canvas.translate(0, padding * 2);
        RadialGradient mRadialGradient =new RadialGradient(itemWidth/2,itemWidth/2,itemWidth/2,mColorLinear,null, Shader.TileMode.CLAMP);
        mPaint.setShader(mRadialGradient);
        canvas.drawCircle(itemWidth, itemWidth, itemWidth, mPaint);

         mRadialGradient =new RadialGradient(itemWidth/2,itemWidth/2,itemWidth/2,mColorLinear,null, Shader.TileMode.REPEAT);
        mPaint.setShader(mRadialGradient);
        canvas.drawCircle(itemWidth*3, itemWidth, itemWidth, mPaint);

        mPaint.setShader(null);
        canvas.translate(0, itemWidth + padding);
        canvas.drawText("环形渐变  Climp  MIRROR", 0, 20, mPaint);
        canvas.translate(0, padding * 2);

         mRadialGradient =new RadialGradient(itemWidth/2,itemWidth/2,itemWidth/2,mColorLinear,new float[]{0,1,2}, Shader.TileMode.CLAMP);
        mPaint.setShader(mRadialGradient);
        canvas.drawCircle(itemWidth, itemWidth, itemWidth, mPaint);

        mRadialGradient =new RadialGradient(itemWidth/2,itemWidth/2,itemWidth/2,mColorLinear,null, Shader.TileMode.MIRROR);
        mPaint.setShader(mRadialGradient);
        canvas.drawCircle(itemWidth*3, itemWidth, itemWidth, mPaint);

        mPaint.setShader(null);
        canvas.translate(0, itemWidth + padding);
        canvas.drawText("环形渐变  null  MIRROR", 0, 20, mPaint);
        canvas.translate(0, padding * 2);
        mRadialGradient =new RadialGradient(itemWidth/2,itemWidth/2,itemWidth/2,mColorLinear,null, Shader.TileMode.MIRROR);
        mPaint.setShader(mRadialGradient);
        canvas.drawRect(0, 0, itemWidth * 2, itemWidth, mPaint);

        /**梯度渐变 SweepGradient*/

        mPaint.setShader(null);
        canvas.translate(0, itemWidth + padding);
        canvas.drawText("梯度渐变  4种颜色  null", 0, 20, mPaint);
        canvas.translate(0, padding * 2);
        int mColorSweep[] = {Color.GREEN, Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN};
        int mColorSweep2[] = {Color.GREEN, Color.RED, Color.BLUE, Color.YELLOW};
       SweepGradient   mSweepGradient = new SweepGradient(itemWidth/2, itemWidth/2, mColorSweep, null);
        mPaint.setShader(mSweepGradient);
        canvas.drawRect(0, 0, itemWidth, itemWidth, mPaint);

        mSweepGradient = new SweepGradient(itemWidth/2, itemWidth/2, mColorSweep2, null);
        mPaint.setShader(mSweepGradient);
        canvas.drawRect(itemWidth + padding, 0, itemWidth * 2, itemWidth, mPaint);


        mSweepGradient = new SweepGradient(370, 495, mColorSweep2, new float[]{0, 0.25f, 0.75f, 1});
        mPaint.setShader(mSweepGradient);
        canvas.drawRect((itemWidth + padding) * 2, 0, itemWidth * 3 + padding * 2, itemWidth, mPaint);











    }


}
