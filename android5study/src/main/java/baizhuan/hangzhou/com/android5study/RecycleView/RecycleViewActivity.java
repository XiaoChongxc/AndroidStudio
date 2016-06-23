package baizhuan.hangzhou.com.android5study.RecycleView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import baizhuan.hangzhou.com.android5study.BaseActivity;
import baizhuan.hangzhou.com.android5study.R;
import baizhuan.hangzhou.com.android5study.RecycleAdapter.CommenAdapter;
import baizhuan.hangzhou.com.android5study.RecycleView.itemDecoration.DividerGridItemDecoration;
import baizhuan.hangzhou.com.android5study.RecycleView.itemDecoration.DividerItemDecoration;
import butterknife.Bind;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.animators.FadeInAnimator;
import jp.wasabeef.recyclerview.animators.ScaleInAnimator;
import jp.wasabeef.recyclerview.animators.adapters.ScaleInAnimationAdapter;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.android5study.RecycleViewActivity
 * Date:    2016-06-22
 * Time:    11:46
 * 类描述：
 */
public class RecycleViewActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recycle)
    RecyclerView recycle;
    CommenAdapter adapter;

    boolean hasItemdivider = true;
    boolean hasAdapterAnimal = true;
    ArrayList list;

    RecyclerView.Adapter adapter2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recycle);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        list = new ArrayList();
        for (int i = 0; i < 50; i++) {
            list.add("" + i + "-" + i + "-" + "i");
        }

        adapter = new CommenAdapter(ctx, list);
        adapter2 = new ScaleInAnimationAdapter(adapter);
        recycle.setAdapter(adapter2);
        recycle.setLayoutManager(new LinearLayoutManager(ctx));
        recycle.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recycle, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.recycle_linear:
                recycle.setLayoutManager(new LinearLayoutManager(ctx));
                recycle.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
                break;
            case R.id.recycle_grid:
                recycle.setLayoutManager(new GridLayoutManager(ctx, 3, GridLayoutManager.VERTICAL, false));
                recycle.addItemDecoration(new DividerGridItemDecoration(this));
            case R.id.recycle_staggered_grid:
                recycle.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
                recycle.addItemDecoration(new DividerGridItemDecoration(this));
                break;
            case R.id.recycle_line:
                break;
            case R.id.recycle_add_item:
                list.add(0, "ADD");
                if (adapter2 == null) {
                    adapter.notifyDataSetChanged();
                } else {
                    adapter2.notifyDataSetChanged();
                }
                break;
            case R.id.recycle_remove_item:
                list.remove(0);
                if (adapter2 == null) {
                    adapter.notifyDataSetChanged();
                } else {
                    adapter2.notifyDataSetChanged();
                }
                break;
            case R.id.recycle_animal_1:
                recycle.setItemAnimator(new ScaleInAnimator());
                break;
            case R.id.recycle_animal_2:
                recycle.setItemAnimator(new FadeInAnimator());
                break;
            case R.id.recycle_adapter_animal:
                if (hasAdapterAnimal) {
                    hasAdapterAnimal = false;
                    recycle.setAdapter(adapter);
                    adapter2 = null;
                } else {
                    hasAdapterAnimal = true;
                    adapter2 = new ScaleInAnimationAdapter(adapter);
                    recycle.setAdapter(adapter2);
                }
                break;

        }


        return true;
    }
}
