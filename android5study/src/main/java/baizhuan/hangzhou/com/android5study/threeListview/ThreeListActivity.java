package baizhuan.hangzhou.com.android5study.threeListview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import baizhuan.hangzhou.com.android5study.R;
import baizhuan.hangzhou.com.android5study.threeListview.adapter.ThreeListAdapter;
import baizhuan.hangzhou.com.android5study.util.Const;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.android5study.threeListview
 * Date:    2016-07-08
 * Time:    15:53
 * 类描述： 3级  listview
 */
public class ThreeListActivity extends Activity {

    @Bind(R.id.list)
    ListView listview;

    List list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_list);
        ButterKnife.bind(this);

        list = Const.getList();
        MyAdapter adapter = new MyAdapter();
        listview.setAdapter(adapter);
    }

    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v =  LayoutInflater.from(ThreeListActivity.this).inflate(R.layout.item_three_list_1, null, false);
            ListView list = (ListView) v.findViewById(R.id.list_1);
            TextView tv = (TextView) v.findViewById(R.id.tv_text);
            v.setBackgroundColor(Color.parseColor("#ededed"));
            tv.setText("-----------------------------这是第一级-----------------------------");
            //这里 参数 传下
            list.setAdapter(new ThreeListAdapter(ThreeListActivity.this));
            return v;
        }
    }
}
