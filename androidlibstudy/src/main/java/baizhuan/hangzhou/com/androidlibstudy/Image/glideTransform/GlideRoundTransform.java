package baizhuan.hangzhou.com.androidlibstudy.Image.glideTransform;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;

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

    private static float radius = 0f;

    public GlideRoundTransform(Context context) {
        this(context, 4);
    }

    public GlideRoundTransform(Context context, int dp) {
        super(context);
        this.radius = Resources.getSystem().getDisplayMetrics().density * dp;
    }

    @Override protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return roundCrop(pool, toTransform);
    }

    private static Bitmap roundCrop(BitmapPool pool, Bitmap source) {
        if (source == null) return null;

        Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        }

//        Canvas canvas = new Canvas(result);
//        canvas.drawColor(Color.parseColor("#cccccc"));
//        Paint paint = new Paint();
//
////        paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
////        paint.setAntiAlias(true);
//        Paint paint2 = new Paint();
//        paint2.setAntiAlias(true);
////        RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
//        paint2.setColor(Color.parseColor("#000000"));
//
//        RectF rectF2 = new RectF(0f+10, 0f+10, source.getWidth()-10, source.getHeight()-10);
////        canvas.drawRoundRect(rectF, radius, radius, paint2);
//        canvas.drawRoundRect(rectF2,radius,radius,paint);
        return result;
    }

    @Override public String getId() {
        return getClass().getName() + Math.round(radius);
    }
}