package com.example.administrator.mytest.RecyclerView;/**
 * Created by  @just_for_happy (@开心就好)   on 2015/12/28.
 * Date: 2015-12-28
 * Time: 14:25
 * Usege: 功能描述。。。
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.mytest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * author: just_for_happy(@开心就好)
 * Date: 2015-12-28 
 * Time: 14:25 
 * FIXME 
 */
public class TestForRecyclerView  extends Activity{
private List<String> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler);

        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.myrecycler);
        initData();
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));   //线性的排列方式
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));    //网格的排列方式
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL));//瀑布流的显示方式

        MyAdapter adapter=new MyAdapter(this,mData);
        recyclerView.setAdapter(adapter);
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        recyclerView.addItemDecoration(new DividerGridItemDecoration2(this));
        //设置动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        adapter.notifyItemInserted(1);//有动画的添加
//        adapter.notifyItemRemoved(1);//有动画的移除
        adapter.setOnItemClickLitener(new MyAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(TestForRecyclerView.this,""+position,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

    }

    private void initData(){
        mData=new ArrayList<String>();
        for (int i='A';i<'z';i++){
            mData.add(""+(char)i);
        }
    }
}
