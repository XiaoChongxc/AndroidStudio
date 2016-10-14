package baizhuan.hangzhou.com.androidlibstudy.Shader;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import baizhuan.hangzhou.com.androidlibstudy.util.DensityUtils;

/**
 * author :   Xchong
 * 项目名：   AndroidStudio2
 * 包名   :   baizhuan.hangzhou.com.androidlibstudy.Shader
 * 日期   :   2016/10/11
 * 时间   ：  10:14
 * 描述   ：下载按钮， 带渐变效果
 */
public class DownButton extends View {

    public DownButton(Context context) {
        super(context);
        init();
    }

    public DownButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DownButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.height = h;
        this.width = w;


    }

    int height, width;

    Paint backgroundPaint;
    Paint textPaint;

    //背景颜色，  进度颜色  , 边框颜色
    int backColor;
    int progressColor;
    int borderColor;


    //记录当前文字
    private CharSequence mCurrentText;

    //显示的文字
    private CharSequence mText;




    //当前进度
    float curProgress;
    float maxProgress;

    //textSize
    int textsize;

    private void init() {
        backgroundPaint = new Paint();
        backgroundPaint.setAntiAlias(true);
        backgroundPaint.setStrokeWidth(2);

        curProgress = 10;
        maxProgress = 100;
        backColor = Color.WHITE;
        progressColor = Color.BLUE;
        borderColor = Color.GRAY;
        textsize = DensityUtils.dp2px(getContext(), 12);


        textPaint = new Paint();
        textPaint.setTextSize(textsize);

        mText="下载中";
        mCurrentText = mText +" "+ curProgress /maxProgress *100 + "%";

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackground(canvas);

        drawText(canvas);
    }

    /**
     * 画背景颜色
     *
     * @param canvas
     */
    private void drawBackground(Canvas canvas) {
        //border
        backgroundPaint.setStyle(Paint.Style.STROKE);
        backgroundPaint.setColor(borderColor);
        RectF rectF2 = new RectF(0, 0, width, height);
        backgroundPaint.setShader(null);
        canvas.drawRoundRect(rectF2, DensityUtils.dp2px(getContext(), 8), DensityUtils.dp2px(getContext(), 8), backgroundPaint);


        //画背景  根据进度变色
        backgroundPaint.setStyle(Paint.Style.FILL);
        backgroundPaint.setColor(backColor);
        RectF rectF = new RectF(2, 2, width-2, height-2);
        LinearGradient linearGradient = new LinearGradient(0, 0, width, 0,
                new int[]{progressColor, backColor},
                new float[]{curProgress / (maxProgress + 0f), curProgress / (maxProgress + 0f) + 0.001f},
                Shader.TileMode.CLAMP);
        backgroundPaint.setShader(linearGradient);
        canvas.drawRoundRect(rectF, DensityUtils.dp2px(getContext(), 8), DensityUtils.dp2px(getContext(), 8), backgroundPaint);

    }


    /***
     * 绘制 变色文字， 和变色背景进度
     *
     * @param canvas
     */
    private void drawText(Canvas canvas) {

        float progressWidth = curProgress / maxProgress * width;
        float y = height / 2 - (textPaint.descent() / 2 + textPaint.ascent() / 2);
        float textwidth = textPaint.measureText(mCurrentText.toString());       //文字的总长度
        float textX1 = width / 2 - textwidth / 2;    //文字的起点
        float curProgressWidth = curProgress/maxProgress * width;                      //进度的长度
        float textX2 = width / 2 + textwidth / 2;
           //文字的终点


        float coverTextwidth = progressWidth - (width / 2 - textwidth / 2);  //文字变色的距离

        if (curProgressWidth < textX1) {//小于文字起点 ， 文字不变色
            textPaint.setShader(null);
            textPaint.setColor(progressColor);

        } else if (curProgressWidth > textX1 && curProgressWidth < textX2) { //大于文字起点，小于文字终点
            LinearGradient linearGradient = new LinearGradient(textX1, y, textX2, y,
                    new int[]{backColor, progressColor},
                    new float[]{coverTextwidth / (textwidth + 0f), coverTextwidth / (textwidth + 0f) + 0.001f},
                    Shader.TileMode.CLAMP);
            textPaint.setShader(linearGradient);
        } else {
            textPaint.setShader(null);
            textPaint.setColor(backColor);
        }
        canvas.drawText(mCurrentText, 0, mCurrentText.length(), textX1, y, textPaint);

    }





    public float getCurProgress() {
        return curProgress;
    }

    public void setCurProgress(int curProgress) {
        this.curProgress = curProgress;
        mCurrentText = mText+"" + this.curProgress /maxProgress *100 + "%";
        postInvalidate();
    }

    public float getMaxProgress() {
        return maxProgress;
    }

    public void setMaxProgress(int maxProgress) {
        this.maxProgress = maxProgress;
        mCurrentText = mText+"" + curProgress/maxProgress *100 + "%";
        postInvalidate();
    }

    public CharSequence getmText() {
        return mText;
    }

    public void setmText(CharSequence mText) {
        this.mText = mText;
        mCurrentText =   this.mText+"" + curProgress/maxProgress *100 + "%";
        postInvalidate();
    }

    public int getTextsize() {
        return textsize;
    }

    public void setTextsize(int textsize) {
        this.textsize = textsize;
        postInvalidate();
    }

    public int getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
        postInvalidate();
    }

    public int getProgressColor() {
        return progressColor;
    }

    public void setProgressColor(int progressColor) {
        this.progressColor = progressColor;
        postInvalidate();
    }

    public int getBackColor() {
        return backColor;
    }

    public void setBackColor(int backColor) {
        this.backColor = backColor;
    }
}
