package com.example.administrator.mytest.Glide;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.administrator.mytest.R;
import com.example.administrator.mytest.cunstom.CircleImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio
 * Package: com.example.administrator.mytest.Glide
 * Date:    2016-05-24
 * Time:    14:26
 * 类描述：  Glide 加载图片 库 测试类
 */
public class GlideActivity extends Activity {

    @Bind(R.id.img_1)
    ImageView img1;
    @Bind(R.id.btn_load_1)
    Button btnLoad1;
    @Bind(R.id.img_2)
    CircleImageView img2;
    @Bind(R.id.btn_load_2)
    Button btnLoad2;
    @Bind(R.id.img_3)
    ImageView img3;
    @Bind(R.id.btn_load_3)
    Button btnLoad3;
    @Bind(R.id.img_4)
    ImageView img4;
    @Bind(R.id.btn_load_4)
    Button btnLoad4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.glide_activity);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_load_1)
    void load1() {
        Glide.with(this)
                .load("http://p4.so.qhimg.com/t01aade72dccf26cffe.jpg")
                .placeholder(R.mipmap.ic_launcher)
                .crossFade(1000)    //动画时间
                .into(img1);
    }
//    http://p4.so.qhimg.com/t0102672bd8a6bd290e.jpg
//    http://p4.so.qhimg.com/t01227ffe14e36a636e.jpg

    @OnClick(R.id.btn_load_2)
    void load2() {
        Glide.with(this)
                .load("http://p4.so.qhimg.com/t018349127914f495ce.jpg")
//                .placeholder(R.mipmap.ic_launcher)
                .crossFade(1000)    //动画时间
//                .transform( new CircleTransform(this))
                .into(img2);
    }

    @OnClick(R.id.btn_load_3)
    void load3() {
        Glide.with(this)
                .load("http://p4.so.qhimg.com/t0102672bd8a6bd290e.jpg")
                .placeholder(R.mipmap.ic_launcher)
                .crossFade(1000)    //动画时间
                .transform( new CircleTransform(this))
                .into(img3);
    }
//
    @OnClick(R.id.btn_load_4)
    void load4() {
        Log.d("===============" , "onResourceReady: start！");
        Glide.with(this)
                .load("http://bbs.hebei.com.cn/data/attachment/forum/201212/18/005142hwhzdfrw6aaawa4x.gif")
                .placeholder(R.mipmap.ic_launcher)
                .crossFade(1000)    //动画时间
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        Log.d("===============" , "onResourceReady: 加载成功！");
                        return false;
                    }
                })
                .into(img4);

    }



    public static class CircleTransform extends BitmapTransformation {
        public CircleTransform(Context context) {
            super(context);
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            return circleCrop(pool, toTransform);
        }

        private static Bitmap circleCrop(BitmapPool pool, Bitmap source) {
            if (source == null) return null;

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
            canvas.drawCircle(r, r, r, paint);
            paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(10);
            paint.setColor(Color.parseColor("#333333"));
            canvas.drawCircle(r, r, r-2.5f, paint);
            return result;
        }

        @Override
        public String getId() {
            return getClass().getName();
        }
    }

}
