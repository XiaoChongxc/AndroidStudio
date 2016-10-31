package baizhuan.hangzhou.com.androidlibstudy.Image.glideTransform;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.androidlibstudy.Image.glideTransform
 * Date:    2016/9/19.
 * Time:    11:36
 * 类描述：
 */
public class GlideRoundTransform extends BitmapTransformation {
    static String TAG = "aaaaa";

    private static float radius = 0f;

    public GlideRoundTransform(Context context) {
        this(context, 4);
    }

    public GlideRoundTransform(Context context, int dp) {
        super(context);
        this.radius = Resources.getSystem().getDisplayMetrics().density * dp;
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {

        return roundCrop(pool, toTransform, outWidth, outHeight);
    }

    private static Bitmap roundCrop(BitmapPool pool, Bitmap source, int outWidth, int outHeight) {

        if (source == null) return null;
        // 先缩放 ， 在剪切
        int sWidth = source.getWidth();
        int sHeight = source.getHeight();
        float sScan =1.0f*sWidth /sHeight;   //原始的长宽比
        float outScan =1.0f*outWidth/outHeight;  //输出的长宽比
        if(outScan<sScan){
            //按照  heigh  来 缩放
            int  nWidth= (int)(outHeight * sScan);
            source =Bitmap.createScaledBitmap(source,nWidth,outHeight,true);
            source = Bitmap.createBitmap(source, (nWidth-outWidth)/2,0 , outWidth, outHeight);

        }else{
            //按照  width 来缩放
            int  nHeight= (int)(outWidth/ sScan);
            source =Bitmap.createScaledBitmap(source,outWidth,nHeight,true);
            source = Bitmap.createBitmap(source, 0,(nHeight-outHeight)/2 , outWidth, outHeight);
        }



        Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);

        if (result == null) {
            result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(result);
        canvas.drawColor(Color.parseColor("#cccccc"));
        Paint paint = new Paint();

        paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        Paint paint2 = new Paint();
        paint2.setAntiAlias(true);
        RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
        paint2.setColor(Color.parseColor("#000000"));

        RectF rectF2 = new RectF(0f + 10, 0f + 10, source.getWidth() - 10, source.getHeight() - 10);
        canvas.drawRoundRect(rectF, radius, radius, paint2);
        canvas.drawRoundRect(rectF2, radius, radius, paint);
        return result;
    }

    @Override
    public String getId() {
        return getClass().getName() + Math.round(radius);
    }
}