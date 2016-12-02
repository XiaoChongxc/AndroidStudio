package baizhuan.hangzhou.com.androidlibstudy.customview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import baizhuan.hangzhou.com.androidlibstudy.R;
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


    Paint mPaint;
    Paint mBitmapPaint;
    //当前的 展开长度
    int curLength;
    //图片的宽度
    int bwidth;
    int mWidth = DensityUtils.dp2px(getContext(), 360);
    int mHeight;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = h;
    }

    private void init() {
        mPaint = new Paint();
        mBitmapPaint = new Paint();
        mPaint.setAntiAlias(true);
        mBitmapPaint.setAntiAlias(true);

        setOnClickListener(this);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        //画一个 画卷 轴

        openImage(canvas);


    }

    /***
     * 图片 展开效果
     */
    private void openImage(Canvas canvas) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
        bwidth = bitmap.getWidth();
        int bheight = bitmap.getHeight();

        Rect rect = null;
        switch (VIEW_TYPE) {
            case VIEW_TYPE_LEFT:
                //从左到右 展开
                int left = DensityUtils.dp2px(getContext(), 40);
                int top = DensityUtils.dp2px(getContext(), 70);
                int width = DensityUtils.dp2px(getContext(), 100) + curLength - DensityUtils.dp2px(getContext(), 100);
                if (width < DensityUtils.dp2px(getContext(), 100)) {
                    width = DensityUtils.dp2px(getContext(), 100);
                }
                rect = new Rect(left, 0, width - DensityUtils.dp2px(getContext(), 40), mHeight - DensityUtils.dp2px(getContext(), 70) *2);
                layout(0, 0, width, mHeight);
                break;
            case VIEW_TYPE_MIDDLE:
                //中间到两边 展开
                int centroX = bwidth / 2;

                rect = new Rect(centroX - curLength / 2, 0, centroX + curLength / 2, bheight);
                break;
            case VIEW_TYPE_RIGHT:
                //  从右到左
                rect = new Rect(bwidth - curLength, 0, bwidth, bheight);
                break;
        }

        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mBitmapPaint.setShader(bitmapShader);
        canvas.save();
        canvas.translate(0, DensityUtils.dp2px(getContext(), 70));
        canvas.drawRect(rect, mBitmapPaint);
        canvas.restore();
        // 改变控件宽度


    }

    /***
     * 画  画卷的边框，
     */
    private void drawUnfoldedBorder(Canvas canvas) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.unfolded1);
        canvas.drawBitmap(bitmap, 0, 0, mPaint);

    }


    @Override
    public void onClick(View v) {
        ValueAnimator animator = ValueAnimator.ofInt(0, mWidth);
        animator.setDuration(2000);
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
        postInvalidate();
    }

}
