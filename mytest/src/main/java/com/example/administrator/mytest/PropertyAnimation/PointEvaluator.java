package com.example.administrator.mytest.PropertyAnimation;

import android.animation.TypeEvaluator;
import android.graphics.Point;

/**
 * Author:  @just_for_happy 开心就好
 * Date: 2016-01-15
 * Time: 17:40
 * FIXME
 */
public class PointEvaluator  implements TypeEvaluator {
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        Point start=  (Point)startValue;
        Point end=  (Point)endValue;
        Point point=new Point();
        point.x= (int)(start.x+fraction*(end.x-start.x));
        point.y= (int)(start.y+fraction*(end.y-start.y));
        return point;
    }
}
