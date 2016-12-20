package baizhuan.hangzhou.com.androidlibstudy.customview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import baizhuan.hangzhou.com.androidlibstudy.R;
import baizhuan.hangzhou.com.androidlibstudy.util.BitmapUtils;
import baizhuan.hangzhou.com.androidlibstudy.util.DensityUtils;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.androidlibstudy.customview
 * Date:    16-12-1.
 * Time:    下午3:53
 * 类描述：  展开画卷的 view
 * 模拟 画卷 缓缓展开的效果
 *
 * @vesion
 */
public class UnfoldedScrollView extends View implements View.OnClickListener {

    public UnfoldedScrollView(Context context) {
        super(context);
        init();
    }

    public UnfoldedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public UnfoldedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /***
     * 画卷展开类型， 默认 0  从左到右
     * 0  从左到右
     * 1 中间到两边
     * 2 从右到左
     */
    private int VIEW_TYPE = 0;
    public static final int VIEW_TYPE_LEFT = 0;
    public static final int VIEW_TYPE_MIDDLE = 1;
    public static final int VIEW_TYPE_RIGHT = 2;

    final int DEFAULT_PADDING = DensityUtils.dp2px(getContext(), 8);


    Paint mPaint;
    Paint mBitmapPaint;
    //当前的 展开长度
    int curLength;

    int MaxLength;

    int layoutTop;
    /**
     * 控件 延伸的 初始宽度
     */
    int minWidth;

    /***
     * 控件 延伸的 最大宽度
     */
    int maxWidth;
    int padding = DensityUtils.dp2px(getContext(), 8);

    //图片的宽度
    int mHeight;


    //内容所在区域
    Rect mContentRect;

    Bitmap leftBitmap;
    Bitmap rightBitmap;
    Paint middlePaint;

    private int[] backgroundRes = new int[]{R.drawable.unfloded_bg_1_1, R.drawable.unfloded_bg_1_2, R.drawable.unfloded_bg_1_3};


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//      EXACTLY：一般是设置了明确的值或者是MATCH_PARENT
//      AT_MOST：表示子布局限制在一个最大值内，一般为WARP_CONTENT
//      UNSPECIFIED：表示子布局想要多大就多大，很少使用
        layoutTop = getTop();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = h;
        MaxLength = w - mContentRect.left - mContentRect.right;


        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
        //缩放图片
        int Imgheight = mHeight - mContentRect.top - mContentRect.bottom;
        int ImgWidth = maxWidth - mContentRect.left - mContentRect.right;
        bitmap = BitmapUtils.getScanBitmap(bitmap, ImgWidth, Imgheight);
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mBitmapPaint.setShader(bitmapShader);
        initBitmap();
    }

    private void initBitmap() {
        //左边
        leftBitmap = BitmapUtils.getScanBitmapbyHeight(getResources(), backgroundRes[0], mHeight);
        rightBitmap = BitmapUtils.getScanBitmapbyHeight(getResources(), backgroundRes[1], mHeight);
        Bitmap middlebitmap = BitmapUtils.getScanBitmapbyHeight(getResources(), backgroundRes[2], mHeight);
        BitmapShader shader = new BitmapShader(middlebitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        middlePaint.setShader(shader);
    }

    private void init() {
        mPaint = new Paint();
        mBitmapPaint = new Paint();
        mPaint.setAntiAlias(true);
        mBitmapPaint.setAntiAlias(true);

        setOnClickListener(this);

        //图的边距
        int left = DensityUtils.dp2px(getContext(), 30);
        int right = DensityUtils.dp2px(getContext(), 30);
        int top = DensityUtils.dp2px(getContext(), 60);
        int bottom = DensityUtils.dp2px(getContext(), 60);
        mContentRect = new Rect(left, top, right, bottom);

        //设置 图片延伸的 最小宽度，最大宽度
        minWidth = DensityUtils.dp2px(getContext(), 0);
        maxWidth = getWindowsWidth();

        middlePaint = new Paint();
        middlePaint.setAntiAlias(true);


    }


    @Override
    protected void onDraw(Canvas canvas) {

        //画背景
        drawBg(canvas);
        //画一个 画卷 轴
        openImage(canvas);


    }

    /**
     * 画背景
     */
    private void drawBg(Canvas canvas) {
        //
        int right, left, left2;
        switch (VIEW_TYPE) {
            case VIEW_TYPE_LEFT:
                right = curLength + mContentRect.left + minWidth;
                if (right > maxWidth - rightBitmap.getWidth())
                    right = maxWidth - rightBitmap.getWidth();
                canvas.drawBitmap(leftBitmap, 0, 0, mPaint);
                canvas.drawRect(leftBitmap.getWidth(), 0, right, mHeight, middlePaint);
                canvas.drawBitmap(rightBitmap, right, 0, mPaint);
                break;
            case VIEW_TYPE_MIDDLE:
                int centroX = maxWidth / 2;
                left = centroX - curLength / 2 - minWidth / 2 - leftBitmap.getWidth();
                if (left < 0) left = 0;
                right = centroX + curLength / 2 + mContentRect.right + minWidth / 2 - rightBitmap.getWidth();
                if (right > maxWidth - rightBitmap.getWidth())
                    right = maxWidth - rightBitmap.getWidth();

                canvas.drawBitmap(leftBitmap, left, 0, mPaint);
                canvas.drawRect(left + leftBitmap.getWidth(), 0, right, mHeight, middlePaint);
                canvas.drawBitmap(rightBitmap, right, 0, mPaint);
                break;
            case VIEW_TYPE_RIGHT:
                left = maxWidth - rightBitmap.getWidth();
                left2 = left - curLength - minWidth;
                right = maxWidth - rightBitmap.getWidth();
                if (left2 < leftBitmap.getWidth()) left2 = leftBitmap.getWidth();

                canvas.drawBitmap(rightBitmap, left, 0, mPaint);
                canvas.drawRect(left2, 0, right, mHeight, middlePaint);
                canvas.drawBitmap(leftBitmap, left2 - leftBitmap.getWidth(), 0, mPaint);
                break;

        }


    }

    /***
     * 图片 展开效果
     */
    private void openImage(Canvas canvas) {

        //缩放图片
        int Imgheight = mHeight - mContentRect.top - mContentRect.bottom;
        int ImgWidth = maxWidth - mContentRect.left - mContentRect.right;

        Rect rect = null;
        if (curLength == 0) padding = 0;
        else padding = DEFAULT_PADDING;
        switch (VIEW_TYPE) {
            case VIEW_TYPE_LEFT:
                //从左到右 展开

                //当前的 图片宽度 <=ImgWidth
                int ImgRight = minWidth + curLength + mContentRect.left + padding;
                if (ImgRight > ImgWidth + mContentRect.left)
                    ImgRight = ImgWidth + mContentRect.left;

                rect = new Rect(mContentRect.left, 0, ImgRight, Imgheight);
                break;
            case VIEW_TYPE_MIDDLE:
                //中间到两边 展开
                int centroX = maxWidth / 2;

                int imgleft = centroX - curLength / 2 - minWidth / 2 - padding;
                int imgright = centroX + curLength / 2 + minWidth / 2 + padding;
                if (imgleft < mContentRect.left) imgleft = mContentRect.left;
                if (imgright > maxWidth - mContentRect.right)
                    imgright = maxWidth - mContentRect.right;
                rect = new Rect(imgleft, 0, imgright, Imgheight);

                break;
            case VIEW_TYPE_RIGHT:
                //  从右到左
                int imgLeft = maxWidth - curLength - minWidth - mContentRect.right - padding;
                if (imgLeft < mContentRect.left) imgLeft = mContentRect.left;
                rect = new Rect(imgLeft, 0, maxWidth - mContentRect.right, Imgheight);
                break;
        }

        canvas.save();
        //画布下移
        canvas.translate(0, mContentRect.top);
        canvas.drawRect(rect, mBitmapPaint);
        canvas.restore();

    }


    @Override
    public void onClick(View v) {
        ValueAnimator animator = ValueAnimator.ofInt(0, maxWidth - mContentRect.left - mContentRect.right);
        animator.setDuration(4000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                curLength = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.start();
    }


    public void setVIEW_TYPE(int VIEW_TYPE) {
        this.VIEW_TYPE = VIEW_TYPE;
        curLength = 0;

        postInvalidate();
    }


    private int getWindowsWidth() {
        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        int width = dm.widthPixels;
        return width;
    }


    public void changeBackground() {
        if (backgroundRes[0] == R.drawable.unfloded_bg_1_1) {
            backgroundRes = new int[]{R.drawable.unfloded_bg_2_1, R.drawable.unfloded_bg_2_3, R.drawable.unfloded_bg_2_2};
        } else {
            backgroundRes = new int[]{R.drawable.unfloded_bg_1_1, R.drawable.unfloded_bg_1_2, R.drawable.unfloded_bg_1_3};
        }
        initBitmap();
        postInvalidate();
    }

}
