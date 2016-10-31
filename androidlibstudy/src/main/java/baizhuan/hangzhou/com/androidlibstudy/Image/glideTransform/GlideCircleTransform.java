package baizhuan.hangzhou.com.androidlibstudy.Image.glideTransform;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * Created by Administrator on 2016/9/19.
 */
public class GlideCircleTransform extends BitmapTransformation {
    public GlideCircleTransform(Context context) {
        super(context);
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return circleCrop(pool, toTransform, outWidth, outHeight);
    }

    private static Bitmap circleCrop(BitmapPool pool, Bitmap source, int outWidth, int outHeight) {
        if (source == null) return null;

        // 先缩放 ， 在剪切
        int sWidth = source.getWidth();
        int sHeight = source.getHeight();
        float sScan = 1.0f * sWidth / sHeight;   //原始的长宽比
        float outScan = 1.0f * outWidth / outHeight;  //输出的长宽比
        if (outScan < sScan) {
            //按照  heigh  来 缩放
            int nWidth = (int) (outHeight * sScan);
            source = Bitmap.createScaledBitmap(source, nWidth, outHeight, true);
            source = Bitmap.createBitmap(source, (nWidth - outWidth) / 2, 0, outWidth, outHeight);

        } else {
            //按照  width 来缩放
            int nHeight = (int) (outWidth / sScan);
            source = Bitmap.createScaledBitmap(source, outWidth, nHeight, true);
            source = Bitmap.createBitmap(source, 0, (nHeight - outHeight) / 2, outWidth, outHeight);
        }


        int size = Math.min(source.getWidth(), source.getHeight());
        int x = (source.getWidth() - size) / 2;
        int y = (source.getHeight() - size) / 2;

        // TODO this could be acquired from the pool too
        Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);

        Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        float r = size / 2f;
        canvas.drawCircle(r, r, r - 10, paint);
        Paint p1 = new Paint();
        p1.setAntiAlias(true);
        p1.setColor(Color.parseColor("#289fff"));
        p1.setStrokeWidth(5);
        p1.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(r, r, r - 5, p1);
        return result;
    }

    @Override
    public String getId() {
        return getClass().getName();
    }
}
