package baizhuan.hangzhou.com.gankcopy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import baizhuan.hangzhou.com.gankcopy.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio
 * Package: com.example.administrator.mytest.MaterialDesign.RecyclerView
 * Date:    2016-05-26
 * Time:    14:44
 * 类描述：
 */
public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyHolder>{

    List<String> list;
    Context ctx;


    public RecycleViewAdapter(Context ctx, List list) {
        this.list = list;
        this.ctx = ctx;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.item_recycler, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder viewHolder, int i) {
        viewHolder.tvName .setText(list.get(i));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_name)
        TextView tvName;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
