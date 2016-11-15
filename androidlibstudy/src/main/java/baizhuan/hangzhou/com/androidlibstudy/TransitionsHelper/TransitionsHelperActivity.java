package baizhuan.hangzhou.com.androidlibstudy.TransitionsHelper;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import baizhuan.hangzhou.com.androidlibstudy.BaseActivity;
import baizhuan.hangzhou.com.androidlibstudy.Constants;
import baizhuan.hangzhou.com.androidlibstudy.R;
import baizhuan.hangzhou.com.androidlibstudy.adapter.ImageAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;
import immortalz.me.library.TransitionsHeleper;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.androidlibstudy.TransitionsHeleper
 * Date:    16-11-4.
 * Time:    下午5:21
 * 类描述：     Android  转场动画 辅助类，  测试类
 *
 * @vesion
 */
public class TransitionsHelperActivity extends BaseActivity {
    @Bind(R.id.recycler)
    RecyclerView recycler;
    ImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_transtions);
        ButterKnife.bind(this);

        recycler.setLayoutManager(new GridLayoutManager(mContext, 3));
        adapter = new ImageAdapter(Constants.getList(), mContext);
        recycler.setAdapter(adapter);
        adapter.setOnclickLinstener(new ImageAdapter.OnItemClickLintener() {
            @Override
            public void onItemclick(int position) {
                //转场动画
                TransitionsHeleper.startActivity(TransitionsHelperActivity.this, ImageDetailActivity.class,
                        recycler.getChildAt(position), adapter.getItemData(position).getPath());

            }
        });


    }


}
