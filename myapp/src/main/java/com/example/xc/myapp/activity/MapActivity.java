package com.example.xc.myapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.maps.MapView;
import com.example.xc.myapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Author:  @just_for_happy 开心就好
 * Date: 2016-02-29
 * Time: 15:30
 * FIXME
 */
public class MapActivity extends Activity {

    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.right)
    TextView right;
    @Bind(R.id.imgRignt)
    ImageView imgRignt;
    @Bind(R.id.map)
    MapView map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);


    }
}
