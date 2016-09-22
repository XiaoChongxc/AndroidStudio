package baizhuan.hangzhou.com.androidlibstudy.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import baizhuan.hangzhou.com.androidlibstudy.R;
import baizhuan.hangzhou.com.androidlibstudy.util.DensityUtils;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.androidlibstudy.adapter
 * Date:    2016/9/21.
 * Time:    16:49
 * 类描述： BitmapShader  三种模式 ，
 * CLAMP :   画布 大于 原图， 边界拉伸，画布小于原图， 截取部分图片
 * MIRROR:   画布 大于 原图， 镜像 对称 平铺，画布小于原图， 截取部分图片
 * REPEAT:   画布 大于 原图， 复制平铺，画布小于原图， 截取部分图片
 */
public class BitmapShaderAdapter extends RecyclerView.Adapter<BitmapShaderAdapter.ViewHolder> {

    Context ctx;

    public BitmapShaderAdapter(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public BitmapShaderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(ctx).inflate(R.layout.list_item_image, parent, false);
        ViewHolder holder = new ViewHolder(root);
        return holder;
    }


    @Override
    public void onBindViewHolder(BitmapShaderAdapter.ViewHolder holder, final int position) {
        Shader.TileMode modeX = null;
        Shader.TileMode modeY = null;
        String title = "";
        switch (position) {
            case 0:
                title = "CLAMP模式,画布大于图片";
                modeX = Shader.TileMode.CLAMP;
                modeY = Shader.TileMode.CLAMP;
                break;
            case 1:
                title = "CLAMP模式,画布小于图片";
                modeX = Shader.TileMode.CLAMP;
                modeY = Shader.TileMode.CLAMP;
                break;
            //    case 2:
            //        modeX= Shader.TileMode.CLAMP;
            //        modeY= Shader.TileMode.MIRROR;
            //        break;
            //    case 3:
            //        modeX= Shader.TileMode.CLAMP;
            //        modeY= Shader.TileMode.MIRROR;
            //        break;
            case 2:
                title = "MIRROR模式,画布大于图片";
                modeX = Shader.TileMode.MIRROR;
                modeY = Shader.TileMode.MIRROR;
                break;
            case 3:
                title = "MIRROR模式,画布小于图片";
                modeX = Shader.TileMode.MIRROR;
                modeY = Shader.TileMode.MIRROR;
                break;
            //    case 6:
            //        modeX= Shader.TileMode.MIRROR;
            //        modeY= Shader.TileMode.REPEAT;
            //        break;
            //    case 7:
            //        modeX= Shader.TileMode.MIRROR;
            //        modeY= Shader.TileMode.REPEAT;
            //        break;
            case 4:
                title = "REPEAT模式,画布大于图片";
                modeX = Shader.TileMode.REPEAT;
                modeY = Shader.TileMode.REPEAT;
                break;
            case 5:
                title = "REPEAT模式,画布小于图片";
                modeX = Shader.TileMode.REPEAT;
                modeY = Shader.TileMode.REPEAT;
                break;
        }

        if ((position + 1) % 2 == 0) {
            holder.img.setImageBitmap(getBitmap2(ctx, modeX, modeY));
        } else {
            holder.img.setImageBitmap(getBitmap1(ctx, modeX, modeY));
        }

        holder.text.setText(title);
//        Picasso.with(ctx)
//                .load(data.get(position).getPath())
//                .placeholder(R.mipmap.ic_logo)
//                .into(holder.img);
//        Glide.with(ctx)
//                .load(data.get(position).getPath())
//                .placeholder(R.mipmap.ic_logo)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .transform(new GlideCircleTransform(ctx))
//                .into(holder.img);


    }

    @Override
    public int getItemCount() {
        return 6;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.text1)
        TextView text;
        @Bind(R.id.img1)
        ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /***
     * 产生一个 bitmap  画布大于 图片大小
     *
     * @param ctx
     * @param modeX x的拉伸模式
     * @param modeY y的拉伸模式
     * @return
     */
    Bitmap getBitmap1(Context ctx, Shader.TileMode modeX, Shader.TileMode modeY) {
        Bitmap ret_bit = Bitmap.createBitmap(DensityUtils.dp2px(ctx, 100), DensityUtils.dp2px(ctx, 100), Bitmap.Config.RGB_565);
        Bitmap bit = BitmapFactory.decodeResource(ctx.getResources(), R.mipmap.ic_shader_default);
        Canvas canvas = new Canvas(ret_bit);
        Paint paint = new Paint();
        BitmapShader bitmapShader = new BitmapShader(bit, modeX, modeY);
        paint.setShader(bitmapShader);
        canvas.drawRect(0, 0, ret_bit.getWidth(), ret_bit.getHeight(), paint);
        return ret_bit;
    }


    /***
     * 产生一个 bitmap  画布小于 图片大小
     *
     * @param ctx
     * @param modeX x的拉伸模式
     * @param modeY y的拉伸模式
     * @return
     */
    Bitmap getBitmap2(Context ctx, Shader.TileMode modeX, Shader.TileMode modeY) {
        Bitmap ret_bit = Bitmap.createBitmap(DensityUtils.dp2px(ctx, 100), DensityUtils.dp2px(ctx, 100), Bitmap.Config.RGB_565);
        Bitmap bit = BitmapFactory.decodeResource(ctx.getResources(), R.mipmap.ic_shader_default);
        Canvas canvas = new Canvas(ret_bit);
        //背景
        canvas.drawColor(Color.parseColor("#83BFB3"));

        Paint paint = new Paint();
        BitmapShader bitmapShader = new BitmapShader(bit, modeX, modeY);
        paint.setShader(bitmapShader);
        canvas.drawCircle( ret_bit.getWidth()/2, ret_bit.getWidth()/2, ret_bit.getWidth()/2, paint);
        return ret_bit;

    }


}
