package baizhuan.hangzhou.com.gankcopy.adapter.holder;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import baizhuan.hangzhou.com.gankcopy.R;
import baizhuan.hangzhou.com.gankcopy.model.GanHuo;

/**
 * Created by gaohailong on 2016/5/17.
 */
public class MeizhiViewholder extends BaseViewHolder<GanHuo.Result> {
    private ImageView image;

    public MeizhiViewholder(ViewGroup parent) {
        super(parent, R.layout.meizhi_item);
        image = $(R.id.image);
    }

    @Override
    public void setData(GanHuo.Result data) {
        super.setData(data);
        Glide.with(getContext())
                .load(data.getUrl())
                .placeholder(R.drawable.img_load)
                .error(R.drawable.img_load_error)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(image);
    }
}
