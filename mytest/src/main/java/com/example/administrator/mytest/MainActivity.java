package com.example.administrator.mytest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.administrator.mytest.PropertyAnimation.TextforProperty;
import com.example.administrator.mytest.PropertyAnimation.TextforProperty2;
import com.example.administrator.mytest.util.T;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Author:  @just_for_happy 开心就好
 * Date: 2016-01-15
 * Time: 17:08
 * FIXME  测试类的主Activity
 */
public class MainActivity extends Activity {

    @Bind(R.id.list_main)
    ListView listMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ButterKnife.bind(this);

        String [] data={"属性动画1","属性动画2","toast测试"};
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);

        listMain.setAdapter(adapter);
        listMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =null;
            switch (position ){
                case 0  :
                     intent=new Intent(MainActivity.this, TextforProperty.class);
                   startActivity(intent);

                    break ;
                case 1  :
                     intent=new Intent(MainActivity.this, TextforProperty2.class);
                    startActivity(intent);

                    break ;
                case 2  :
                    T.showTop(MainActivity.this,"我只是一个提示而已");
                    break ;
            }
            }
        });


    }
}
