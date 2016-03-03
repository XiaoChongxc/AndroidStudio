package com.example.administrator.mytest.ExplosionField;/**
 * Created by  @just_for_happy (@开心就好)   on 2015/12/23.
 */

import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import java.math.MathContext;

/**
 * author: just_for_happy(@开心就好)
 * Date: 2015-12-23
 * Time: 15:49
 * FIXME
 */
public class ExplosionAnimator  extends ValueAnimator{

    Paint mPaint;
    View  mContainer;
    public static final int DEFAULT_DURATION = 1500;

    public ExplosionAnimator(View view,View v2) {
        setFloatValues(0.0f, 1.0f);
        setDuration(DEFAULT_DURATION);
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mContainer=view;
        Bitmap bit =  createBitmapByView(v2);
        CreateParticleList(v2,bit);
    }

    public void  Draw(Canvas canvas){
        if(!isStarted()){       //动画结束之后 就停止
            return;
        }
        for (Particle[] particles :Particles){
            for (Particle particle :particles){
                particle.advance((Float)getAnimatedValue());
                mPaint.setAlpha((int) (Color.alpha(particle.color) *particle.alpha)); //这样透明颜色就不是黑色了
                mPaint.setColor(particle.color);
                canvas.drawCircle(particle.cx,particle.cy,particle.radius,mPaint);
            }
        }
        mContainer.postInvalidateDelayed(50);

    }




    Particle [][]Particles;
    /**生成一个像素点集合*/
    private void CreateParticleList(View v2,Bitmap bit) {
        Rect bound = new Rect();
//        这个方法是构建一个Rect用来"套"这个view.此Rect的坐标是相对当前activity而言.
        v2.getGlobalVisibleRect(bound);
        Rect rect =new Rect();
        mContainer.getGlobalVisibleRect(rect);
        bound.offset(0,-rect.top);
        int w = bound.width();
        int h = bound.height();
        int viewW = v2.getWidth();
        int viewH = v2.getHeight();
        Log.i("TAG", "w" + w + "h" + h + "viewW" + viewW + "viewH" + viewH);
        int partW_Count = w / Particle.PART_WH;        //横向的  数量
        int partH_Count = h / Particle.PART_WH;         //纵向的数量
        Particles = new Particle[partW_Count][partH_Count];
        /**把 bitmap中的每一个点 放到像素点集合去*/
        for (int i = 0; i < partW_Count; i++) {  //横向
            for (int j = 0; j < partH_Count; j++) {//纵向
                int color = bit.getPixel(i * Particle.PART_WH, j * Particle.PART_WH);
                Point point = new Point(i, j);
                Particles[i][j] = Particle.createParticle(color, point, bound);
            }
        }
    }

    /**
     * 根据view创建一个一模一样的bitmap
     * */
    private Bitmap createBitmapByView(View v2 ){
        Bitmap bitmap=Bitmap.createBitmap(v2.getWidth(),v2.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas mCanvas=new Canvas(bitmap);      //把 空白图作为画布
        v2.draw(mCanvas);                        //把 view 画到画布上去
//        mCanvas.setBitmap(null);    //清除引用
        return bitmap;
    }

    @Override
    public void start() {
        super.start();
        mContainer.postInvalidateDelayed(50);
    }
}
