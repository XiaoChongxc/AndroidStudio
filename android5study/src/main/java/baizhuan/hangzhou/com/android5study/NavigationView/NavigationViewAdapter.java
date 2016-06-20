package baizhuan.hangzhou.com.android5study.NavigationView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import baizhuan.hangzhou.com.android5study.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.android5study.NavigationView
 * Date:    2016-06-20
 * Time:    10:44
 * 类描述：
 */
public class NavigationViewAdapter extends RecyclerView.Adapter {

    Context ctx;
    String[] datas;

    OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    public NavigationViewAdapter(Context ctx, String[] datas) {
        this.datas = datas;
        this.ctx = ctx;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(ctx).inflate(R.layout.item_recycle_text, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((ViewHolder) holder).tv.setText(datas[position]);
        ((ViewHolder) holder).itemParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onitemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.length;
    }

    interface OnItemClickListener {
        void onitemClick(int position);
    }

}

class ViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.tv_text)
    TextView tv;
    @Bind(R.id.item_parent)
    LinearLayout itemParent;


    public ViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
