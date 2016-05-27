package baizhuan.hangzhou.com.gankcopy.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import baizhuan.hangzhou.com.gankcopy.R;
import baizhuan.hangzhou.com.gankcopy.view.customview.photoview.PhotoViewAttacher;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio
 * Package: baizhuan.hangzhou.com.gankcopy.adapter
 * Date:    2016-05-27
 * Time:    16:22
 * 类描述：查看大图 的adapter
 */
public class ShowBigAdapter extends PagerAdapter {

    Context ctx;
    List<String> list;

    OnImageClickListener onImageClickListener;

    public void setOnImageClickListener(OnImageClickListener onImageClickListener) {
        this.onImageClickListener = onImageClickListener;
    }

    public ShowBigAdapter(Context ctx, List<String> list) {
        this.ctx = ctx;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public View instantiateItem(ViewGroup container, final int position) {

        ImageView view = (ImageView) LayoutInflater.from(ctx).inflate(R.layout.item_showbig, container, false);
        Glide.with(ctx).load(list.get(position)).placeholder(R.drawable.img_load).error(R.drawable.img_load_error).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(view);
        final PhotoViewAttacher attacher = new PhotoViewAttacher(view);
//        Glide.with(ctx)
//                .load(list.get(position))
//                .asBitmap()
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                .into(new SimpleTarget<Bitmap>() {
//                    @Override
//                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                        image.setImageBitmap(resource);
//                        attacher.update();
//                    }
//                });


        container.addView(view);
        if (onImageClickListener != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onImageClickListener.onClick(position);
                }
            });
        }

        return view;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeViewAt(position);
    }

    public interface OnImageClickListener {
        void onClick(int position);
    }
}
