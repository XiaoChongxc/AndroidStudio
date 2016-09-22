package baizhuan.hangzhou.com.androidlibstudy.Shader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import baizhuan.hangzhou.com.androidlibstudy.BaseActivity;
import baizhuan.hangzhou.com.androidlibstudy.R;
import baizhuan.hangzhou.com.androidlibstudy.adapter.BaseAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.androidlibstudy.Shader
 * Date:    2016/9/21.
 * Time:    16:42
 * 类描述： Shader类
 */
public class ShaderActivity extends BaseActivity {

    @Bind(R.id.recycler)
    RecyclerView recycler;
    List list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        list = new ArrayList();
        list.add("BitmapShader");

        BaseAdapter adapter = new BaseAdapter(list, this);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);

        adapter.setOnclickLinstener(new BaseAdapter.OnItemClickLintener() {
            @Override
            public void onItemclick(int position) {
                Intent intent = null;
                switch (position) {
                    case 0:
                        intent = new Intent(mContext, BitmapShaderActivity.class);
                        break;
                }
                startActivity(intent);
            }
        });
    }


}
