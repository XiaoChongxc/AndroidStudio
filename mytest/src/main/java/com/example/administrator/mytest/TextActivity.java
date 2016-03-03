package com.example.administrator.mytest;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Author:  @just_for_happy 开心就好
 * Date: 2016-03-02
 * Time: 16:29
 * FIXME
 */
public class TextActivity extends Activity  implements View.OnTouchListener{


    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_text);
       tv  =(TextView)findViewById(R.id.tv_text);
        tv.setOnTouchListener(this);

    }

    private final  int MaxScroll=500;

    private float startX;
    private float startY;

    @Override
    public boolean onTouch(View v, MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                startX=ev.getX();
                startY=ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float X=ev.getX();
                float Y=ev.getY();
                float alpha= Math.abs(Y - startY)/MaxScroll ;
                    tv.setAlpha(alpha);
                break;
            case MotionEvent.ACTION_UP:
                break;

        }
        return true;
    }
}
