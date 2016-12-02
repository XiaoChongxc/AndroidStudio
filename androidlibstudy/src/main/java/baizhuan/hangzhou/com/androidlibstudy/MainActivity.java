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
import baizhuan.hangzhou.com.androidlibstudy.bezier.BezierActivity;
import baizhuan.hangzhou.com.androidlibstudy.bezier.BezierWaveActivity;
import baizhuan.hangzhou.com.androidlibstudy.customview.activity.AlorithmActivity;
import baizhuan.hangzhou.com.androidlibstudy.customview.activity.UnfoldedScrollActivity;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.recycler)
    RecyclerView recycler;
    List list;

    List<Class> clasz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initData();

        BaseAdapter adapter = new BaseAdapter(list, this);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);

        adapter.setOnclickLinstener(new BaseAdapter.OnItemClickLintener() {
            @Override
            public void onItemclick(int position) {
                Intent intent = null;
                intent = new Intent(MainActivity.this, clasz.get(position));
//
                startActivity(intent);
            }
        });
    }


    private void initData() {
        list = new ArrayList();
        clasz = new ArrayList();
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
        list.add("bezier 曲线 测试");
        list.add("bezier 曲线 测试2");
        list.add(" 算法展示");
        list.add(" 画卷  展开效果");
        //****************************
        clasz.add(RxjavaActivity.class);
        clasz.add(CircleImageActivity.class);
        clasz.add(PorterDuffXfermodeTextActivity.class);
        clasz.add(ShaderActivity.class);
        clasz.add(StickyActivity.class);
        clasz.add(H5Activity.class);
        clasz.add(TransitionsHelperActivity.class);
        clasz.add(FunctionActivity.class);
        clasz.add(HttpsActivity.class);
        clasz.add(MVPLoginActivity.class);
        clasz.add(BezierActivity.class);
        clasz.add(BezierWaveActivity.class);
        clasz.add(AlorithmActivity.class);
        clasz.add(UnfoldedScrollActivity.class);

    }
}
