package baizhuan.hangzhou.com.androidlibstudy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import baizhuan.hangzhou.com.androidlibstudy.Rxjava.RxjavaActivity;
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
                }
                startActivity(intent);
            }
        });
    }


}
