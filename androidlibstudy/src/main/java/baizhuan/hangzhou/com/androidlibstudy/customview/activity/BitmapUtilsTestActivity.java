package baizhuan.hangzhou.com.androidlibstudy.customview.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import baizhuan.hangzhou.com.androidlibstudy.BaseActivity;
import baizhuan.hangzhou.com.androidlibstudy.R;
import baizhuan.hangzhou.com.androidlibstudy.adapter.BitmapUtilsAdapter;
import baizhuan.hangzhou.com.androidlibstudy.customview.model.ImageInfo;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.androidlibstudy.customview.activity
 * Date:    16-12-6.
 * Time:    下午5:41
 * 类描述：  bitmap 工具类的 测试activity
 *
 * @vesion
 */
public class BitmapUtilsTestActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.recycler)
    RecyclerView recycler;
    BitmapUtilsAdapter adapter;

    List<ImageInfo> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bitmap_util_test);
        ButterKnife.bind(this);


        toolbar.setTitle("Bitmap工具类测试");
        toolbar.setNavigationIcon(R.drawable.icon_arrow_back);
        setSupportActionBar(toolbar);

        list = new ArrayList<>();
        initData();
        recycler.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new BitmapUtilsAdapter(this, list);
        recycler.setAdapter(adapter);
        if (adapter.getItemCount() == 0) {
            list.add(new ImageInfo(0, 0, R.drawable.bg));
        }
        adapter.setOnHeadViewClickLinster(new BitmapUtilsAdapter.OnHeadViewClickLinster() {
            @Override
            public void onHeadViewClick(int width, int height) {
                list.add(new ImageInfo(width, height, R.drawable.bg));
                adapter.notifyDataSetChanged();
            }
        });
    }
    private  void  initData(){

        list.add(new ImageInfo(100,100,R.drawable.bg));
        list.add(new ImageInfo(200,200,R.drawable.bg));
        list.add(new ImageInfo(300,300,R.drawable.bg));
        list.add(new ImageInfo(400,400,R.drawable.bg));
        list.add(new ImageInfo(500,500,R.drawable.bg));
        list.add(new ImageInfo(600,600,R.drawable.bg));
        list.add(new ImageInfo(700,700,R.drawable.bg));

    }


}
