package baizhuan.hangzhou.com.android5study.RecycleAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import baizhuan.hangzhou.com.android5study.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.android5study.RecycleAdapter
 * Date:    2016-06-20
 * Time:    15:14
 * 类描述：
 */
public class CommenAdapter extends RecyclerView.Adapter {
    ArrayList list;
    Context ctx;

    public CommenAdapter(Context ctx,ArrayList list) {
        this.list = list;
        this.ctx = ctx;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View rootview = LayoutInflater.from(ctx).inflate(R.layout.item_recycle_text, parent, false);

            return new ViewHolder(rootview);
        } else {
            View rootview = LayoutInflater.from(ctx).inflate(R.layout.item_recycle_image, parent, false);
            return new ViewHolder2(rootview);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position != 0 && position % 3 == 0) {
            return 3;
        }
        return 1;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == 1) {
            ((ViewHolder) holder).tv.setText(list.get(position).toString());
        } else {
            ((ViewHolder2) holder).img.setImageResource(R.mipmap.ic_logo);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_text)
        TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ViewHolder2 extends RecyclerView.ViewHolder {
        @Bind(R.id.img)
        ImageView img;

        public ViewHolder2(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
