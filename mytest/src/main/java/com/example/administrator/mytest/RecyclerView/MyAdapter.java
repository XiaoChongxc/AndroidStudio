package com.example.administrator.mytest.RecyclerView;/**
 * Created by  @just_for_happy (@开心就好)   on 2015/12/28.
 * Date: 2015-12-28
 * Time: 18:12
 * Usege: 功能描述。。。
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.mytest.R;

import java.util.List;

/**
 * author: just_for_happy(@开心就好)
 * Date: 2015-12-28 
 * Time: 18:12 
 * FIXME 
 */
public class MyAdapter  extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    private List<String>mData;
    private Context ctx;
    private OnItemClickLitener mOnItemClickLitener;

    public MyAdapter(Context ctx, List<String>data) {
        this.mData=data;
        this.ctx=ctx;
    }

    /**绑定一个 viewholer  相当于 给viewholder中的控件赋值*/
    @Override
    public void onBindViewHolder(final MyViewHolder holder,final  int position) {

        ViewGroup.LayoutParams lp=holder.tv_name.getLayoutParams();
        lp.height= (int) (100 + Math.random() * 300);
        holder.tv_name.setLayoutParams(lp);
        holder.tv_name.setText(mData.get(position));
        if(mOnItemClickLitener !=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView,pos);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos=holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });

        }

    }


    /**创建一个viewholder
     * 指定要显示的viewholder
     * */
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View  view=LayoutInflater.from(ctx).inflate(R.layout.item_recycler,parent,false);
        MyViewHolder viewHolder=new MyViewHolder(view);
        return viewHolder;
    }

    /**item的数量*/
    @Override
    public int getItemCount() {
        return mData.size();
    }


    /**自定义的viewholer  需要继承 ViewHolder  和listview 中的viewholer类似，必须重写构造方法*/
    public  class MyViewHolder extends ViewHolder{
        private TextView tv_name;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv_name=(TextView)itemView.findViewById(R.id.tv_name);
        }
    }

    public interface  OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener){
        this.mOnItemClickLitener=mOnItemClickLitener;
    }

}
