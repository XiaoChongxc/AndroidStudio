package baizhuan.hangzhou.com.gankcopy.adapter.holder;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import baizhuan.hangzhou.com.gankcopy.R;
import baizhuan.hangzhou.com.gankcopy.model.GanHuo;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio
 * Package: baizhuan.hangzhou.com.gankcopy.adapter.holder
 * Date:    2016-05-25
 * Time:    17:25
 * 类描述：
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
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(image);
    }
}
