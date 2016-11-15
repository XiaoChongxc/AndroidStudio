package baizhuan.hangzhou.com.androidlibstudy.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import baizhuan.hangzhou.com.androidlibstudy.Constants;
import baizhuan.hangzhou.com.androidlibstudy.Image.glideTransform.GlideRoundTransform;
import baizhuan.hangzhou.com.androidlibstudy.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/19.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    List<Constants.ImageBean> data;
    Context ctx;

    public ImageAdapter(List data, Context ctx) {
        this.data = data;
        this.ctx = ctx;
    }

    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(ctx).inflate(R.layout.list_item_image, parent, false);

        ViewHolder holder = new ViewHolder(root);

        return holder;
    }

    @Override
    public void onBindViewHolder(ImageAdapter.ViewHolder holder, final int position) {

        holder.text.setText(data.get(position).getTitle());
//        Picasso.with(ctx)
//                .load(data.get(position).getPath())
//                .placeholder(R.mipmap.ic_logo)
//                .into(holder.img);
        Glide.with(ctx)
                .load(data.get(position).getPath())
                .placeholder(R.mipmap.ic_logo)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transform(new GlideRoundTransform(ctx, 8, 4, Color.parseColor("#289fff")))
                .into(holder.img);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lintener != null) {
                    lintener.onItemclick(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
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

    private OnItemClickLintener lintener;

    public interface OnItemClickLintener {
        void onItemclick(int position);
    }

    public Constants.ImageBean getItemData(int position) {
        return data.get(position);
    }

    public void setOnclickLinstener(OnItemClickLintener lintener) {
        this.lintener = lintener;
    }
}
