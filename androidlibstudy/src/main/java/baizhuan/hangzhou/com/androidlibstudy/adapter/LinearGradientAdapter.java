package baizhuan.hangzhou.com.androidlibstudy.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
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
 * 类描述： LinearShader 测试  三种模式 ，
 */
public class LinearGradientAdapter extends RecyclerView.Adapter<LinearGradientAdapter.ViewHolder> {

    Context ctx;

    public LinearGradientAdapter(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public LinearGradientAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(ctx).inflate(R.layout.list_item_image2, parent, false);
        ViewHolder holder = new ViewHolder(root);
        return holder;
    }


    @Override
    public void onBindViewHolder(LinearGradientAdapter.ViewHolder holder, final int position) {
        Shader.TileMode mode = null;
        String title = "";
        switch (position) {
            case 0:
                title = "CLAMP模式,position不为空";
                mode = Shader.TileMode.CLAMP;
                break;
            case 1:
                title = "CLAMP模式,position为空";
                mode = Shader.TileMode.CLAMP;
                break;
            case 2:
                title = "MIRROR模式,position不为空";
                mode = Shader.TileMode.MIRROR;
                break;
            case 3:
                title = "MIRROR模式,position为空";
                mode = Shader.TileMode.MIRROR;
                break;
            case 4:
                title = "REPEAT模式,position不为空";
                mode = Shader.TileMode.REPEAT;
                break;
            case 5:
                title = "REPEAT模式,position为空";
                mode = Shader.TileMode.REPEAT;
                break;
        }


        if ((position + 1) % 2 == 0) {
            holder.img.setImageBitmap(getBitmap2( mode, DensityUtils.dp2px(ctx,200),DensityUtils.dp2px(ctx,50)));
        } else {
            holder.img.setImageBitmap(getBitmap1( mode, DensityUtils.dp2px(ctx,200),DensityUtils.dp2px(ctx,50)));
        }

        holder.text.setText(title);

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
     * @return
     */
    Bitmap getBitmap1(Shader.TileMode mode,int width,int height) {
        Bitmap ret_bit = Bitmap.createBitmap(width,height, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(ret_bit);
        Paint paint = new Paint();
        int []colors={Color.YELLOW,Color.BLUE,Color.GRAY};
        LinearGradient linearGradient =new LinearGradient(0,0,0,height/2,colors,new float[]{0.3f,0.4f,0.7f}, mode);
        paint.setShader(linearGradient);
        canvas.drawRect(0, 0,width, height, paint);
        return ret_bit;
    }


    /***
     * position=null
     *
     * @return
     */
    Bitmap getBitmap2( Shader.TileMode mode,int width,int height) {
        Bitmap ret_bit = Bitmap.createBitmap(width,height, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(ret_bit);
        Paint paint = new Paint();
        int []colors={Color.YELLOW,Color.BLUE,Color.GRAY};
        LinearGradient linearGradient =new LinearGradient(0,0,0,height/2,colors,null, mode);
        paint.setShader(linearGradient);
        canvas.drawRect(0, 0, width,height, paint);
        return ret_bit;
    }

}
