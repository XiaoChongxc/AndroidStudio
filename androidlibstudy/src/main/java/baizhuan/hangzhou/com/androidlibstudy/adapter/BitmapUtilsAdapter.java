package baizhuan.hangzhou.com.androidlibstudy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.List;

import baizhuan.hangzhou.com.androidlibstudy.R;
import baizhuan.hangzhou.com.androidlibstudy.customview.model.ImageInfo;
import baizhuan.hangzhou.com.androidlibstudy.util.BitmapUtils;
import baizhuan.hangzhou.com.androidlibstudy.util.Const;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.androidlibstudy.adapter
 * Date:    16-12-7.
 * Time:    上午10:24
 * 类描述：
 *
 * @vesion
 */
public class BitmapUtilsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    List<ImageInfo> list;
    Context ctx;

    private OnHeadViewClickLinster onHeadViewClickLinster;


    public interface OnHeadViewClickLinster {
        void onHeadViewClick(int width, int height);
    }

    public void setOnHeadViewClickLinster(OnHeadViewClickLinster onHeadViewClickLinster) {
        this.onHeadViewClickLinster = onHeadViewClickLinster;
    }

    public BitmapUtilsAdapter(Context ctx, List<ImageInfo> list) {
        this.ctx = ctx;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View v = LayoutInflater.from(ctx).inflate(R.layout.head_bitmap_util_test, parent, false);
            return new ViewHolderHead(v);
        } else {
            View v = LayoutInflater.from(ctx).inflate(R.layout.item_image, parent, false);
            return new ViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            ((ViewHolderHead) holder).btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int width = Const.string2int(((ViewHolderHead) holder).etWidth.getText().toString());
                    int height = Const.string2int(((ViewHolderHead) holder).etHeight.getText().toString());
                    if (onHeadViewClickLinster != null) {
                        onHeadViewClickLinster.onHeadViewClick(width, height);
                    }
                }
            });
        } else {
            ImageInfo imgInfo = list.get(position);
            int width = imgInfo.getWidth();
            int height = imgInfo.getHeight();
            int resId = imgInfo.getResId();
            ((ViewHolder) holder).image.setImageBitmap(BitmapUtils.getScanBitmap(ctx.getResources(), resId, width, height));
        }

    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            return position;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolderHead extends RecyclerView.ViewHolder {
        @Bind(R.id.img_head)
        ImageView imgHead;
        @Bind(R.id.et_width)
        EditText etWidth;
        @Bind(R.id.et_height)
        EditText etHeight;
        @Bind(R.id.btn_add)
        Button btnAdd;

        public ViewHolderHead(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.image)
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
