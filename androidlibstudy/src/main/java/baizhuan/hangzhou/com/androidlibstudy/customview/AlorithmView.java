package baizhuan.hangzhou.com.androidlibstudy.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import baizhuan.hangzhou.com.androidlibstudy.util.DensityUtils;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.androidlibstudy.customview
 * Date:    16-11-30.
 * Time:    下午2:01
 * 类描述：   演示 冒泡排序  选择排序算法的  view
 *
 * @vesion
 */
public class AlorithmView extends View implements View.OnClickListener {

    /**
     * 视图的类型， 默认0  冒泡排序类型
     */
    public static int VIEW_TYPE;

    public static final int VIEW_TYPE_MAOPAO = 0;

    public static final int VIEW_TYPE_CHOSE = 1;


    public AlorithmView(Context context) {
        super(context);
        init();
    }

    public AlorithmView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AlorithmView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private Paint mPaint;
    private int[] nums = {10, 50, 11, 14, 40, 88, 45, 25, 90};
    private int mWidth, mHeight;
    /***
     * 默认的边距
     */
    private int mPadding = DensityUtils.dp2px(getContext(), 16);

    /***
     * 坐标轴颜色
     */
    private int mXYColor = Color.BLACK;

    private int mContentColor = Color.LTGRAY;
    private int mBorderColor = Color.GREEN;

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        setOnClickListener(this);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //画坐标轴
        drawXY(canvas);
        //画柱状图
        drawContent(canvas);
    }

    /**
     * 画坐标轴
     *
     * @param canvas
     */
    private void drawXY(Canvas canvas) {
        mPaint.setColor(mXYColor);
        mPaint.setTextSize(DensityUtils.dp2px(getContext(), 15));
        //draw Y
        canvas.drawLine(mPadding * 2, mPadding, mPadding * 2, mHeight - mPadding * 2, mPaint);
        //draw X
        canvas.drawLine(mPadding * 2, mHeight - mPadding * 2, mWidth - mPadding, mHeight - mPadding * 2, mPaint);
    }

    /***
     * 画柱状图
     *
     * @param canvas
     */
    private void drawContent(Canvas canvas) {
        //左边空 2 padding 柱状图 前空 1 padding ， 右边空 1 padding ，柱状图后空 1 padding =5paddind
        mPaint.setColor(mContentColor);
        int perWidth = (mWidth - mPadding * 5) / nums.length;
        int perHeight = (mHeight - mPadding * 4) / getMax();

        for (int i = 0; i < nums.length; i++) {
            //画边框
            Rect mRect = new Rect(3 * mPadding + i * perWidth, mHeight - 2 * mPadding - perHeight * nums[i], 3 * mPadding + (i + 1) * perWidth, mHeight - 2 * mPadding);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(mBorderColor);
            canvas.drawRect(mRect, mPaint);
            //画柱子
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(mContentColor);
            mRect = new Rect(3 * mPadding + i * perWidth + 2, mHeight - 2 * mPadding - perHeight * nums[i] + 2, 3 * mPadding + (i + 1) * perWidth - 2, mHeight - 2 * mPadding);
            canvas.drawRect(mRect, mPaint);
            //画文字
            float textWidth = mPaint.measureText("" + nums[i]);
            float textHeight = mPaint.descent() - mPaint.ascent();
            Log.d("-----------", "drawContent: " + textWidth + "/////" + textHeight);
            canvas.drawText(nums[i] + "", 3 * mPadding + i * perWidth + textWidth / 2 - 2, mHeight - 2 * mPadding - perHeight * nums[i] - 2, mPaint);

        }

    }


    @Override
    public void onClick(View v) {
        nums = new int[]{10, 50, 11, 14, 40, 88, 45, 25, 90};
////        ValueAnimator animator = ValueAnimator.ofInt(0, nums.length);
//        ValueAnimator animator = ValueAnimator.ofObject(new MyAlorithmE(), new IJ(0, 0), new IJ(10, 10));
//        animator.setInterpolator(new LinearInterpolator());
//        animator.setDuration(2000);
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
////                int position = (int) animation.getAnimatedValue();
////                if (VIEW_TYPE == VIEW_TYPE_MAOPAO) {
////                    MaoPaoSort(position);
////                } else {
////                    choseSort2(position);
////                }
//            }
//        });
//        animator.start();
        new Thread() {
            @Override
            public void run() {
                if (VIEW_TYPE == VIEW_TYPE_MAOPAO) {
                    MaoPaoSort2();
                } else {
                    choseSort2();
                }
            }
        }.start();


    }

    /***
     * 获取一个数组中最大的数
     */
    private int getMax() {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (max < nums[i]) {
                max = nums[i];
            }
        }
        return max;
    }

    /***
     * 冒泡排序
     * 相邻2个数 进行对比， 然后交换位置
     */
    public void MaoPaoSort(int i) {
        int temp;
        for (int j = 0; j < nums.length - 1 - i; j++) {
            if (nums[j + 1] > nums[j]) {
                temp = nums[j];
                nums[j] = nums[j + 1];
                nums[j + 1] = temp;
                invalidate();
            }
        }
    }

    public void MaoPaoSort2() {
        for (int i = 0; i < nums.length; i++) {
            int temp;
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j + 1] > nums[j]) {
                    temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.d("///", "MaoPaoSort2: 11111");
                    postInvalidate();
                }
            }
        }
    }


    /***
     * 简单选择排序
     */
    public void choseSort(int i) {
        int temp;
        for (int j = i + 1; j < nums.length; j++) {
            if (nums[i] < nums[j]) {
                temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                postInvalidate();
            }
        }

    }

    public void choseSort2() {

        for (int i = 0; i < nums.length; i++) {
            int temp;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] < nums[j]) {
                    temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    postInvalidate();
                }
            }
        }
    }


    /***
     * 设置 算法展示类型
     *
     * @param viewType
     */
    public void setViewType(int viewType) {
        nums = new int[]{10, 50, 11, 14, 40, 88, 45, 25, 90};
        this.VIEW_TYPE = viewType;
        postInvalidate();
    }

    public void setNums(int[] nums) {
        this.nums = nums;
        postInvalidate();
    }

}
