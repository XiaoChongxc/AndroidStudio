package com.example.administrator.mytest.shape.Shader;/**
 * Created by  @just_for_happy (@开心就好)   on 2015/12/31.
 * Date: 2015-12-31
 * Time: 17:25
 * Usege: 功能描述。。。
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.mytest.R;

/**
 * author: just_for_happy(@开心就好)
 * Date: 2015-12-31 
 * Time: 17:25 
 * FIXME   初步了解 shader的五个子类的 用法和特征
 */
public class MyView  extends View {
    public MyView(Context context) {
        super(context);
        init();
    }
    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private Bitmap mBitmap=null;
    private BitmapShader mBitmapShader =null;
    private LinearGradient  mLinearGradient=null;
    private ComposeShader mComposeShader=null;
    private RadialGradient mRadialGradient=null;
    private SweepGradient mSweepGradient=null;

    private void init(){
        //获取到 bitmap实例
//        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.azz);
////        　其中，参数bitmap表示用来作为纹理填充的位图；参数tileX表示在位图X方向上位图衔接形式；参数tileY表示在位图Y方向上位图衔接形式。
////        　　Shader.TileMode有3种参数可供选择，分别为CLAMP、REPEAT和MIRROR。
////        　CLAMP的作用是如果渲染器超出原始边界范围，则会复制边缘颜色对超出范围的区域进行着色。REPEAT的作用是在横向和纵向上以平铺的形式重复渲染位图。
////            MIRROR的作用是在横向和纵向上以镜像的方式重复渲染位图。
//        //创建bitmap渲染对象
//        mBitmapShader =new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.REPEAT);
////        其中，参数x0表示渐变的起始点x坐标；参数y0表示渐变的起始点y坐标；参数x1表示渐变的终点x坐标；
////      参数y1表示渐变的终点y坐标；参数colors表示渐变的颜色数组；参数positions用来指定颜色数组的相对位置；参数tile表示平铺方式。
////        　　通常，参数positions设为null，表示颜色数组以斜坡线的形式均匀分布。
//        mLinearGradient =new LinearGradient(0,0,100,100,new int[]{Color.BLUE,Color.YELLOW,Color.GREEN},null, Shader.TileMode.CLAMP);
//        //创建环形渲染对象
////    　　其中，参数x表示环形的圆心x坐标；参数y表示环形的圆心y坐标；参数radius表示环形的半径；
////        参数colors表示环形渐变的颜色数组；参数positions用来指定颜色数组的相对位置；参数tile表示平铺的方式。
//        int mColorRadial[] = {Color.GREEN, Color.RED, Color.BLUE, Color.WHITE};
//        mRadialGradient=new RadialGradient(350, 325, 75,mColorRadial,null, Shader.TileMode.REPEAT);
//
////        其中，参数shaderA表示某一种渲染效果；参数shaderB也表示某一种渲染效果；参数mode表示两种渲染效果的叠加模式。
////      　PorterDuff.Mode有16种参数可供选择，分别为：CLEAR、SRC、DST、SRC_OVER、DST_OVER、SRC_IN、DST_IN、SRC_OUT、
////        DST_OUT、SRC_ATOP、DST_ATOP、XOR、DARKEN、LIGHTEN、MULTIPLY、SCREEN。
//        //混合渲染对象
//        mComposeShader =new ComposeShader(mLinearGradient,mRadialGradient, PorterDuff.Mode.DARKEN);
//
//        //创建梯度渲染对象
////    　其中，参数cx表示扫描的中心x坐标；参数cy表示扫描的中心y坐标；参数colors表示梯度渐变的颜色数组；参数positions用来指定颜色数组的相对位置。
//        int mColorSweep[] = {Color.GREEN, Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN};
//        mSweepGradient =new SweepGradient(370, 495,mColorSweep,null);



        //加载图像资源
         mBitmap = ((BitmapDrawable) getResources().
                getDrawable(R.drawable.changba)).getBitmap();

            //创建Bitmap渲染对象
            mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.REPEAT,
                        Shader.TileMode.MIRROR);

               //创建线性渲染对象
           int mColorLinear[] = {Color.RED, Color.GREEN, Color.BLUE, Color.WHITE};
           mLinearGradient = new LinearGradient(0, 0, 100, 100, mColorLinear, null,
                  Shader.TileMode.REPEAT);

              //创建环形渲染对象
             int mColorRadial[] = {Color.GREEN, Color.RED, Color.BLUE, Color.WHITE};
               mRadialGradient = new RadialGradient(350, 325, 75, mColorRadial, null,
                         Shader.TileMode.REPEAT);

              //创建混合渲染对象
           mComposeShader = new ComposeShader(mLinearGradient, mRadialGradient,
                         PorterDuff.Mode.DARKEN);

              //创建梯形渲染对象
                int mColorSweep[] = {Color.GREEN, Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN};
                 mSweepGradient = new SweepGradient(370, 495, mColorSweep, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        Paint mPaint=new Paint();
//        canvas.drawColor(Color.GRAY);
//        mPaint.setShader(mBitmapShader);
//        mPaint.setShader(mBitmapShader);
//        canvas.drawOval(new RectF(90, 20, 90 + mBitmap.getWidth(), 20 + mBitmap.getHeight()), mPaint);
//        //绘制Bitmap渲染的椭圆
//           mPaint.setShader(mBitmapShader);
//    canvas.drawOval(new RectF(90, 20, 90 + mBitmap.getWidth(),
//                      20+mBitmap.getHeight()), mPaint);
//         //绘制线性渐变的矩形
//        mPaint.setShader(mLinearGradient);
//        canvas.drawRect(10, 250, 250, 400, mPaint);
//         //绘制环形渐变的圆 76 mPaint.setShader(mRadialGradient);
//          canvas.drawCircle(350, 325, 75, mPaint);
//        //绘制混合渐变(线性与环形混合)的矩形 80 mPaint.setShader(mComposeShader);
//         canvas.drawRect(10, 420, 250, 570, mPaint);
//          //绘制梯形渐变的矩形 84 mPaint.setShader(mSweepGradient);
//          canvas.drawRect(270, 420, 470, 570, mPaint);

        Paint mPaint = new Paint();
        canvas.drawColor(Color.GRAY);      //背景置为灰色

        //绘制Bitmap渲染的椭圆
        mPaint.setShader(mBitmapShader);
        canvas.drawOval(new RectF(90, 20, 90+mBitmap.getWidth(),
                20+mBitmap.getHeight()), mPaint);

        //绘制线性渐变的矩形
        mPaint.setShader(mLinearGradient);
        canvas.drawRect(10, 250, 250, 400, mPaint);

        //绘制环形渐变的圆
        mPaint.setShader(mRadialGradient);
        canvas.drawCircle(350, 325, 75, mPaint);

        //绘制混合渐变(线性与环形混合)的矩形
        mPaint.setShader(mComposeShader);
        canvas.drawRect(10, 420, 250, 570, mPaint);

        //绘制梯形渐变的矩形
        mPaint.setShader(mSweepGradient);
        canvas.drawRect(270, 420, 470, 570, mPaint);

    }



}
