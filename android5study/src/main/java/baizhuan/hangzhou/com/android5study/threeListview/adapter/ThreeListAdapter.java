package baizhuan.hangzhou.com.android5study.threeListview.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import baizhuan.hangzhou.com.android5study.R;
import baizhuan.hangzhou.com.android5study.util.Const;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.android5study.threeListview.adapter
 * Date:    2016-07-08
 * Time:    16:08
 * 类描述： 第二个 listview
 */
public class ThreeListAdapter extends BaseAdapter {

    List list;
    Context ctx;

    public ThreeListAdapter(Context ctx) {
        this.ctx = ctx;
        list = Const.getList2();
    }

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
        View v = LayoutInflater.from(ctx).inflate(R.layout.item_three_list_1, null, false);
        ListView list = (ListView) v.findViewById(R.id.list_1);
        TextView tv = (TextView) v.findViewById(R.id.tv_text);
        v.setBackgroundColor(Color.parseColor("#fff289"));
        tv.setText("-----------------------------这是第二级-----------------------------");
        //这里 参数 传下
        list.setAdapter(new ThreeListAdapter2(ctx));
        return v;
    }
}
