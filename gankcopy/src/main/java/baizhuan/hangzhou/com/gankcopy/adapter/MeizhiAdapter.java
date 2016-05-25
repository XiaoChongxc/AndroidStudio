package baizhuan.hangzhou.com.gankcopy.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import baizhuan.hangzhou.com.gankcopy.adapter.holder.MeizhiViewholder;
import baizhuan.hangzhou.com.gankcopy.model.GanHuo;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio
 * Package: baizhuan.hangzhou.com.gankcopy.adapter
 * Date:    2016-05-25
 * Time:    17:22
 * 类描述：
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
