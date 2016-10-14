package baizhuan.hangzhou.com.androidlibstudy.Shader;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import baizhuan.hangzhou.com.androidlibstudy.BaseActivity;
import baizhuan.hangzhou.com.androidlibstudy.R;
import baizhuan.hangzhou.com.androidlibstudy.adapter.LinearGradientAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.androidlibstudy.Shader
 * Date:    2016/9/22.
 * Time:    9:37
 * 类描述： LinearGradient
 * LinearGradient的作用是实现某一区域内颜色的线性渐变效果。
 *　　LinearGradient的函数原型为：
 *　　public LinearGradient (float x0, float y0, float x1, float y1, int[] colors, float[] positions, Shader.TileMode tile);
 *　　其中，参数x0表示渐变的起始点x坐标；参数y0表示渐变的起始点y坐标；参数x1表示渐变的终点x坐标；参数y1表示渐变的终点y坐标；参数colors表示渐变的颜色数组；参数positions用来指定颜色数组的相对位置；参数tile表示平铺方式。
 *　　通常，参数positions设为null，表示颜色数组以斜坡线的形式均匀分布。
 */
public class LinearGradientActivity extends BaseActivity{
    @Bind(R.id.recycler)
    RecyclerView recycler;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    LinearGradientAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_list);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        recycler.setLayoutManager(new LinearLayoutManager(mContext));
        adapter=new LinearGradientAdapter(mContext);
        recycler.setAdapter(adapter);
    }
}
