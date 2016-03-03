package com.example.administrator.mytest.ExplosionField;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.example.administrator.mytest.R;

public class ExplosionActivity extends AppCompatActivity{
    LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.explosion_activity);

        layout  =(LinearLayout)findViewById(R.id.layout);//父类
        ExplosionField view =new ExplosionField(this);
        view.addListener(layout);
    }




}
