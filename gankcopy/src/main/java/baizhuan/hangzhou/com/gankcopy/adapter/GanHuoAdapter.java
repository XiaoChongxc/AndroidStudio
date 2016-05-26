package baizhuan.hangzhou.com.gankcopy.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import baizhuan.hangzhou.com.gankcopy.adapter.holder.GanHuoViewHolder;
import baizhuan.hangzhou.com.gankcopy.model.GanHuo;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio
 * Package: baizhuan.hangzhou.com.gankcopy.adapter
 * Date:    2016-05-26
 * Time:    16:01
 * 类描述：
 */
public class GanHuoAdapter extends RecyclerArrayAdapter<GanHuo.Result> {
    public GanHuoAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new GanHuoViewHolder(parent);
    }
}
