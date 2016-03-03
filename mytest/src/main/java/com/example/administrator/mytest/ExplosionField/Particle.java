package com.example.administrator.mytest.ExplosionField;
/**
 * Created by  @just_for_happy (@开心就好)   on 2015/12/22.
 */

import android.graphics.Point;
import android.graphics.Rect;

import java.util.Random;

/**
 * author: just_for_happy(@开心就好)
 * Date: 2015-12-22
 * Time: 18:13
 * FIXME  用来描述粒子，包括属性有颜色、透明度、圆心坐标、半径。
 */
public class Particle {
    float cx;
    float cy;
    float radius;
    int color;
    float alpha;
    public static final int PART_WH = 8; //默认小球宽高
    Rect mBound;


    public static  Particle createParticle(int color ,Point point ,Rect bound){
        Particle p=new Particle();
        p.mBound=bound;
        p.color=color;
        int x=point.x;
        int y=point.y;
        p.cx=bound.left +x*PART_WH;
        p.cy=bound.top+y*PART_WH;
        p.radius=PART_WH;
        p.alpha=1f;
        return p;
    }
    Random random =new Random();


    /***/
    public void advance(float factor) {
        cx = cx + factor * random.nextInt(mBound.width()) * (random.nextFloat() - 0.5f);
        cy = cy + factor * random.nextInt(mBound.height() / 2);
        radius = radius - factor * random.nextInt(2);
        alpha = (1f - factor) * (1 + random.nextFloat());
    }

}
