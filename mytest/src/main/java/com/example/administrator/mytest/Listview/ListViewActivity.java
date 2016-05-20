package com.example.administrator.mytest.Listview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.administrator.mytest.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio
 * Package: com.example.administrator.mytest.Listview
 * Date:    2016-05-17
 * Time:    15:08
 * 类描述：
 */
public class ListViewActivity extends Activity {

    @Bind(R.id.list)
    NoScrollListview list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.elastic_list_activity);
        ButterKnife.bind(this);
        String[] data = {"11111111", "222222222", "3333333333", "444444444444", "11111111", "222222222", "3333333333", "444444444444"};
        list.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data));
    }

}
