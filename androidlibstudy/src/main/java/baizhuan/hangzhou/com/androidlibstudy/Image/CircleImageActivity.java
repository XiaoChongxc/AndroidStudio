package baizhuan.hangzhou.com.androidlibstudy.Image;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.bumptech.glide.Glide;

import baizhuan.hangzhou.com.androidlibstudy.BaseActivity;
import baizhuan.hangzhou.com.androidlibstudy.Constants;
import baizhuan.hangzhou.com.androidlibstudy.R;
import baizhuan.hangzhou.com.androidlibstudy.adapter.ImageAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/19.
 *
 *  主要测试了 Glide 和 Picasso 加载的不一样，
 *  Glide 加载 到有边框的 imageview 上， 边框会消失，  Picasso 不会，
 *  Glide  可以通过Transform 处理 显示的图片结果，  需要自己写过程
 */
public class CircleImageActivity extends BaseActivity {

    @Bind(R.id.recycler)
    RecyclerView recycler;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    ImageAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_list);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        recycler.setLayoutManager(new GridLayoutManager(mContext,2));
         adapter=new ImageAdapter(Constants.getList(),mContext);
        recycler.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate(R.menu.clean_cache,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId() ){
            case R.id.menu_clean:
                //清楚缓存
                Glide.get(mContext).clearMemory();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.get(mContext).clearDiskCache();
                    }
                }).start();
                adapter.notifyDataSetChanged();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
