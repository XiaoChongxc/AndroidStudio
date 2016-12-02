package baizhuan.hangzhou.com.gankcopy.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import baizhuan.hangzhou.com.gankcopy.adapter.holder.MeizhiViewholder;
import baizhuan.hangzhou.com.gankcopy.model.GanHuo;

/**
 * Created by gaohailong on 2016/5/17.
 */
public class MeizhiAdapter extends RecyclerArrayAdapter<GanHuo.Result> {

    public MeizhiAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MeizhiViewholder(parent);
    }
}
