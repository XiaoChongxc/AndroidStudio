package baizhuan.hangzhou.com.androidlibstudy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import baizhuan.hangzhou.com.androidlibstudy.Image.CircleImageActivity;
import baizhuan.hangzhou.com.androidlibstudy.PorterDuffXfermode.PorterDuffXfermodeTextActivity;
import baizhuan.hangzhou.com.androidlibstudy.Rxjava.RxjavaActivity;
import baizhuan.hangzhou.com.androidlibstudy.Shader.ShaderActivity;
import baizhuan.hangzhou.com.androidlibstudy.Sticky.StickyActivity;
import baizhuan.hangzhou.com.androidlibstudy.adapter.BaseAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.recycler)
    RecyclerView recycler;
    List list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        list = new ArrayList();
        list.add("Rxjava操作符使用");
        list.add("CircleImageView");
        list.add("16种相交模式测试");
        list.add("Shader类测试");
        list.add("悬停吸附 类测试");
        BaseAdapter adapter = new BaseAdapter(list, this);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);

        adapter.setOnclickLinstener(new BaseAdapter.OnItemClickLintener() {
            @Override
            public void onItemclick(int position) {
                Intent intent = null;
                switch (position) {
                    case 0:
                        intent = new Intent(MainActivity.this, RxjavaActivity.class);
                        break;
                    case 1:
                        intent = new Intent(MainActivity.this, CircleImageActivity.class);
                        break;
                    case 2:
                        intent = new Intent(MainActivity.this, PorterDuffXfermodeTextActivity.class);
                        break;
                    case 3:
                        intent = new Intent(MainActivity.this, ShaderActivity.class);
                        break;
                    case 4:
                        intent = new Intent(MainActivity.this, StickyActivity.class);
                        break;
                }
                startActivity(intent);
            }
        });
    }

}
