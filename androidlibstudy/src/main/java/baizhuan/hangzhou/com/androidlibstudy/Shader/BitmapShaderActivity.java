package baizhuan.hangzhou.com.androidlibstudy.Shader;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import baizhuan.hangzhou.com.androidlibstudy.BaseActivity;
import baizhuan.hangzhou.com.androidlibstudy.R;
import baizhuan.hangzhou.com.androidlibstudy.adapter.BitmapShaderAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.androidlibstudy.Shader
 * Date:    2016/9/21.
 * Time:    16:45
 * 类描述：Bitmapshader
 * 　　BitmapShader的作用是使用一张位图作为纹理来对某一区域进行填充。可以想象成在一块区域内铺瓷砖，只是这里的瓷砖是一张张位图而已。
 *　　BitmapShader函数原型为：
 *　　public BitmapShader (Bitmap bitmap, Shader.TileMode tileX, Shader.TileMode tileY);
 *　　其中，参数bitmap表示用来作为纹理填充的位图；参数tileX表示在位图X方向上位图衔接形式；参数tileY表示在位图Y方向上位图衔接形式。
 *　　Shader.TileMode有3种参数可供选择，分别为CLAMP、REPEAT和MIRROR。
 *　　CLAMP的作用是如果渲染器超出原始边界范围，则会复制边缘颜色对超出范围的区域进行着色。REPEAT的作用是在横向和纵向上以平铺的形式重复渲染位图。MIRROR的作用是在横向和纵向上以镜像的方式重复渲染位图。
 */
public class BitmapShaderActivity extends BaseActivity {

    @Bind(R.id.recycler)
    RecyclerView recycler;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    BitmapShaderAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_list);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        recycler.setLayoutManager(new LinearLayoutManager(mContext));
        adapter=new BitmapShaderAdapter(mContext);
        recycler.setAdapter(adapter);
    }
}
