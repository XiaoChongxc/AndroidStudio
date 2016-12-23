package baizhuan.hangzhou.com.android5study.RecycleView.headgrid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import baizhuan.hangzhou.com.android5study.BaseActivity;
import baizhuan.hangzhou.com.android5study.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author :   Xchong
 * 项目名：   AndroidStudio2
 * 包名   :   baizhuan.hangzhou.com.android5study.RecycleView.headgrid
 * 日期   :   2016/12/21
 * 时间   ：  16:49
 * 描述   ：   有head 的 grildlayoutManager
 */
public class ActivityHeadgrid extends BaseActivity {






    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recycle)
    RecyclerView recycle;
    HeadGridAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recycle);
        ButterKnife.bind(this);

        toolbar.setTitle("recyclerview 带head的item");

        ArrayList<HeadGrid> list=new ArrayList();
        for (int i = 0; i < 50; i++) {
            HeadGrid headGrid=new HeadGrid();
            headGrid.setText("" + i + "-" + i + "-" + "i");
            if(i %7==0){
                headGrid.setType(0);
            }else{
                headGrid.setType(1);
            }
            list.add(  headGrid);
        }

        adapter = new HeadGridAdapter( list,ctx);
        recycle.setAdapter(adapter);
        GridLayoutManager  gridLayoutManager=new GridLayoutManager(ctx,3);
        recycle.setLayoutManager(gridLayoutManager);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if(adapter.getItemViewType(position)==0){//head
                    return 3;
                }else{
                    return 1;
                }
            }
        });



    }
}
