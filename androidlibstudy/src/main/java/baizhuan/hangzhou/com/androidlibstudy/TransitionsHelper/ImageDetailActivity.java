package baizhuan.hangzhou.com.androidlibstudy.TransitionsHelper;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import baizhuan.hangzhou.com.androidlibstudy.BaseActivity;
import baizhuan.hangzhou.com.androidlibstudy.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import immortalz.me.library.TransitionsHeleper;
import immortalz.me.library.bean.InfoBean;
import immortalz.me.library.method.ColorShowMethod;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.androidlibstudy.TransitionsHelper
 * Date:    16-11-7.
 * Time:    上午10:46
 * 类描述：
 *
 * @vesion
 */
public class ImageDetailActivity extends BaseActivity {
    @Bind(R.id.image)
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image_detail);
        ButterKnife.bind(this);

        TransitionsHeleper.getInstance()
                .setShowMethod(new ColorShowMethod(R.color.showmethod_start_color,
                        R.color.showmethod_end_color) {
                    @Override
                    public void loadCopyView(InfoBean bean, ImageView copyView) {
                        Log.d("TAG", "loadCopyView: " + copyView.getHeight() + "****" + copyView.getWidth());
                        Glide.with(ImageDetailActivity.this)
                                .load(bean.getImgUrl())
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(copyView);
                    }

                    @Override
                    public void loadTargetView(InfoBean bean, ImageView targetView) {
                        Log.d("TAG", "loadTargetView: " + targetView.getHeight() + "/////" + targetView.getWidth());
                        Glide.with(ImageDetailActivity.this)
                                .load(bean.getImgUrl())
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(targetView);
                    }

                }).show(this, image);

    }
}
