package baizhuan.hangzhou.com.androidlibstudy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.androidlibstudy
 * Date:    2016-07-12
 * Time:    11:13
 * 类描述：
 */
public class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.ViewHolder> {

    List data;
    Context ctx;

    public BaseAdapter(List data, Context ctx) {
        this.data = data;
        this.ctx = ctx;
    }

    @Override
    public BaseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(ctx).inflate(android.R.layout.simple_list_item_1, parent, false);

        ViewHolder holder = new ViewHolder(root);

        return holder;
    }

    @Override
    public void onBindViewHolder(BaseAdapter.ViewHolder holder, final int position) {

        holder.text.setText(data.get(position).toString());
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
        @Bind(android.R.id.text1)
        TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private OnItemClickLintener lintener;

    public interface OnItemClickLintener {
        void onItemclick(int position);
    }

    public void setOnclickLinstener(OnItemClickLintener lintener) {
        this.lintener = lintener;
    }
}