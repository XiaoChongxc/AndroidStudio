package com.example.administrator.mytest.qqnearbybody.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.mytest.R;

/**
 * Created by 开心就好（@just_for_happy） on 2016/5/7.
 * 雷达扫描的控件
 * <p/>
 * 1绘制雷达线    2 绘制  扫描层 ，  3绘制 中间的图片
 */
public class RadarView extends View {

    public RadarView(Context context) {
        super(context);
        init();
        post(run);
    }

    public RadarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        post(run);
    }

    public RadarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        post(run);
    }


    Paint mPaintLine;
    Paint mPaintScan;
    Paint mPaintCircle;
    private int width, height;
    private Bitmap mCircleIcon;
    private Matrix matrix;
    private int scanSpeed = 5;
    private int scanAngle;//扫描旋转的角度


    private int currentScanCount;//当前扫描次数
    private int currentScanItem ;//当前显示的item
    private int  maxScanCount;//最大扫描次数
    private boolean startScan =false; //是否开始扫描
    private IScanningListener scanningListener;

    public void setScanningListener(IScanningListener scanningListener) {
        this.scanningListener = scanningListener;
    }

    private SweepGradient scanShader;

    //旋转 矩阵
    private Runnable  run =new Runnable() {
        @Override
        public void run() {
            scanAngle = (scanAngle + scanSpeed) % 360;
            matrix.postRotate(scanSpeed, width / 2, height / 2);
            invalidate();
            postDelayed(run, 130);
            //开始扫描显示标志为true 且 只扫描一圈
            if (startScan && currentScanCount <= (360 / scanSpeed)) {
                if (scanningListener != null && currentScanCount % scanSpeed == 0
                        && currentScanItem < maxScanCount) {

                    scanningListener.onScanning(currentScanItem, scanAngle);
                    currentScanItem++;
                } else if (scanningListener != null && currentScanItem == currentScanCount) {
                    scanningListener.onScanSuccess();
                }
                currentScanCount++;
            }
        }
    };

    private void init() {
        mPaintLine = new Paint();
        mPaintLine.setColor(Color.parseColor("#3C8EAE"));
        mPaintLine.setAntiAlias(true);
        mPaintLine.setStrokeWidth(1);
        mPaintLine.setStyle(Paint.Style.STROKE);

        mPaintCircle = new Paint();
        mPaintCircle.setColor(Color.WHITE);
        mPaintCircle.setAntiAlias(true);

        mPaintScan = new Paint();
        mPaintScan.setStyle(Paint.Style.FILL_AND_STROKE);
        matrix=new Matrix();

    }

    private float[] circleProportion = {1 / 13f, 2 / 13f, 3 / 13f, 4 / 13f, 5 / 13f, 6 / 13f};

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureSize(widthMeasureSpec), measureSize(heightMeasureSpec));
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        width = height = Math.min(width, height);
        mCircleIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        scanShader = new SweepGradient(width / 2, height / 2,
                new int[]{Color.TRANSPARENT, Color.parseColor("#84B5CA")}, null);
    }

    private int measureSize(int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 300;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawLine(canvas);
        drawScan(canvas);
        drawIcon(canvas);
    }

    private  void  drawLine(Canvas canvas){
        canvas.drawCircle(width/2,height/2,width*circleProportion[0],mPaintLine);
        canvas.drawCircle(width/2,height/2,width*circleProportion[1],mPaintLine);
        canvas.drawCircle(width/2,height/2,width*circleProportion[2],mPaintLine);
        canvas.drawCircle(width / 2, height / 2, width * circleProportion[3], mPaintLine);
        canvas.drawCircle(width / 2, height / 2, width * circleProportion[4], mPaintLine);
        canvas.drawCircle(width / 2, height / 2, width * circleProportion[5], mPaintLine);
    }
    private void  drawScan(Canvas canvas){
        canvas.save();
        mPaintScan.setShader(scanShader);
        canvas.concat(matrix);
        canvas.drawCircle(width / 2, height / 2, width * circleProportion[4], mPaintScan);
        canvas.restore();
    }

    private void  drawIcon(Canvas canvas){
        canvas.drawBitmap(mCircleIcon,null,
                new Rect((int) (width / 2 - width * circleProportion[0]), (int) (height / 2 - width * circleProportion[0]),
                        (int) (width / 2 + width * circleProportion[0]), (int) (height / 2 + width * circleProportion[0])), mPaintCircle);}


    public  interface  IScanningListener{
        //正在扫描（此时还没有扫描完毕）时回调
        void onScanning(int position, float scanAngle);

        //扫描成功时回调
        void onScanSuccess();
    }

    public void setMaxScanCount(int maxScanCount) {
        this.maxScanCount = maxScanCount;
    }

    //开始扫描
    public void  setStartScan(){
        this.startScan=true;
    }
}
