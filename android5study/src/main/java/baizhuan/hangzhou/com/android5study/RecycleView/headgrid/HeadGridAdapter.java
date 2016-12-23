package baizhuan.hangzhou.com.android5study.RecycleView.headgrid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import baizhuan.hangzhou.com.android5study.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author :   Xchong
 * 项目名：   AndroidStudio2
 * 包名   :   baizhuan.hangzhou.com.android5study.RecycleView.headgrid
 * 日期   :   2016/12/23
 * 时间   ：  10:27
 * 描述   ：
 */
public class HeadGridAdapter extends RecyclerView.Adapter {

    private final int view_type_head = 0;
    private final int view_type_text = 1;


    List<HeadGrid> list;
    Context ctx;

    public HeadGridAdapter(List<HeadGrid> list, Context ctx) {
        this.list = list;
        this.ctx = ctx;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == view_type_head) {//标题
            view = LayoutInflater.from(ctx).inflate(R.layout.item_headgrid_head, parent, false);
            return new HeadViewHolder(view);
        } else {              //内容
            view = LayoutInflater.from(ctx).inflate(R.layout.item_headgrid_content, parent, false);
            return new ContentViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HeadGrid headGrid=list.get(position);
        if( headGrid.getType()==view_type_head){//标题
            ((HeadViewHolder)holder ).text.setText(headGrid.getText()+"");
        }else{

        }

    }

    @Override
    public int getItemViewType(int position) {

        HeadGrid headGrid=list.get(position);
        return headGrid.getType();

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HeadViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.text)
        TextView text;
        public HeadViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class ContentViewHolder extends RecyclerView.ViewHolder {

        public ContentViewHolder(View itemView) {
            super(itemView);
        }
    }




}
