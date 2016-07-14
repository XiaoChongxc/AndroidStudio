package baizhuan.hangzhou.com.android5study.threeListview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import baizhuan.hangzhou.com.android5study.util.Const;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.android5study.threeListview.adapter
 * Date:    2016-07-08
 * Time:    16:08
 * 类描述： 第二个 listview
 */
public class ThreeListAdapter2 extends BaseAdapter {

    List list;
    Context ctx;

    public ThreeListAdapter2(Context ctx) {
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
        TextView listview = (TextView) LayoutInflater.from(ctx).inflate(android.R.layout.simple_list_item_1, null, false);
        //这里 参数 传下
        listview.setText(list.get(position).toString());
        return listview;
    }
}
