package baizhuan.hangzhou.com.androidlibstudy.customview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * author :   Xchong
 * 项目名：   AndroidStudio2
 * 包名   :   baizhuan.hangzhou.com.androidlibstudy.customview
 * 日期   :   2016/12/26
 * 时间   ：  15:26
 * 描述   ： 演示matrix 的 view
 *
 */
public class MatrixView  extends ImageView{

    public MatrixView(Context context) {
        super(context);
        init();
    }

    public MatrixView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MatrixView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }



    Matrix matrix;

    Paint mPaint;


    Bitmap mBitmap;


    private void  init(){
        mPaint=new Paint();
        mPaint.setAntiAlias(true);

        matrix=new Matrix();
    }

    //Required by path helper
    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        mBitmap=((BitmapDrawable)getDrawable()).getBitmap();
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        mBitmap=((BitmapDrawable)getDrawable()).getBitmap();
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        mBitmap=((BitmapDrawable)getDrawable()).getBitmap();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        int height=getHeight();
        int width=getWidth();

        canvas.translate(width/2-mBitmap.getWidth()/2 ,height/2-mBitmap.getHeight()/2);

        canvas.drawBitmap(mBitmap,matrix,mPaint);
        Log.d("TAG", "onDraw: matrix:"+matrix.toString());
        matrix.reset();
        canvas.restore();
    }


    //缩放
    public void  setScale(float  sx,float sy){
        matrix.postScale(sx,sy);
        postInvalidate();
    }


    //平移
    public void setTranslate(int  tx,int ty){
        matrix.postTranslate(tx,ty);
        postInvalidate();
    }


    //旋转
    public  void  setRotate(float  angle){
        matrix.postRotate(angle);
        postInvalidate();
    }

    //扭曲

    public  void  setSkew(float  sx,float  sy){
        matrix.setSkew(sx,sy);
        postInvalidate();
    }

    /******************动画变化*****************/


    /**
     * 缩放
     * @param osx
     * @param sx
     */
    public  void  setAnimalScanle(float osx,float sx){
        ValueAnimator animator=ValueAnimator.ofFloat(osx,sx);
        animator.setDuration(2000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float progress= (float) animation.getAnimatedValue();
                matrix.postScale(progress,progress);
                postInvalidate();
            }
        });
        animator.start();
    }

    /**
     * 旋转
     * @param or
     * @param r
     */
    public  void  setAnimalRotate(float or,float r){
        ValueAnimator animator=ValueAnimator.ofFloat(or,r);
        animator.setDuration(2000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float progress= (float) animation.getAnimatedValue();
                matrix.postRotate(progress);
                postInvalidate();
            }
        });
        animator.start();
    }


    /**
     * 平移
     * @param ox
     * @param x
     */
    public  void  setAnimalTranslate(float ox,float x){
        ValueAnimator animator=ValueAnimator.ofFloat(ox,x);
        animator.setDuration(2000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float progress= (float) animation.getAnimatedValue();
                matrix.postTranslate(progress,progress);
                postInvalidate();
            }
        });
        animator.start();
    }

    /**
     * 扭曲
     * @param ox
     * @param x
     */
    public  void  setAnimalSkew(float ox,float x){
        ValueAnimator animator=ValueAnimator.ofFloat(ox,x);
        animator.setDuration(2000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float progress= (float) animation.getAnimatedValue();
                matrix.postSkew(progress,progress);
                postInvalidate();
            }
        });
        animator.start();
    }







    public  void   reset(){
        matrix.reset();
        postInvalidate();
    }



}
