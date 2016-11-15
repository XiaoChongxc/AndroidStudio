package baizhuan.hangzhou.com.androidlibstudy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import baizhuan.hangzhou.com.androidlibstudy.H5.H5Activity;
import baizhuan.hangzhou.com.androidlibstudy.Https.HttpsActivity;
import baizhuan.hangzhou.com.androidlibstudy.Image.CircleImageActivity;
import baizhuan.hangzhou.com.androidlibstudy.MVP.view.MVPLoginActivity;
import baizhuan.hangzhou.com.androidlibstudy.PorterDuffXfermode.PorterDuffXfermodeTextActivity;
import baizhuan.hangzhou.com.androidlibstudy.Rxjava.RxjavaActivity;
import baizhuan.hangzhou.com.androidlibstudy.Shader.ShaderActivity;
import baizhuan.hangzhou.com.androidlibstudy.Sticky.StickyActivity;
import baizhuan.hangzhou.com.androidlibstudy.TransitionsHelper.TransitionsHelperActivity;
import baizhuan.hangzhou.com.androidlibstudy.adapter.BaseAdapter;
import baizhuan.hangzhou.com.androidlibstudy.util.Const;
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
        list.add("H5测试 类测试");
        list.add("Transtion转场动画测试 类测试");
        list.add("FunctionActivity  一个 图表 类测试");
        list.add("Https 类测试");
        list.add("MVP Lgoin 测试");
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
                    case 5:
                        intent = new Intent(MainActivity.this, H5Activity.class);
                        intent.putExtra(Const.H5URL,"http://www.msa.gov.cn/page/article.do?articleId=C0C2D93E-66EF-49CF-9F97-B13D4CBCAD65");
                        intent.putExtra(Const.H5TITLE,"测试咯");
                        break;
                    case 6:
                        intent = new Intent(MainActivity.this, TransitionsHelperActivity.class);
                        break;
                    case 7:
                        intent = new Intent(MainActivity.this, FunctionActivity.class);
                        break;
                    case 8:
                        intent = new Intent(MainActivity.this, HttpsActivity.class);
                        break;
                    case 9:
                        intent = new Intent(MainActivity.this, MVPLoginActivity.class);
                        break;
                }
//
                startActivity(intent);
            }
        });
    }

}
