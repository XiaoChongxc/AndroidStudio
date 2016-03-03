package com.example.administrator.mytest.PropertyAnimation;

import android.animation.TimeInterpolator;

/**
 * Author:  @just_for_happy 开心就好
 * Date: 2016-01-18
 * Time: 14:34
 * FIXME  自定义的Interpolator
 */


public class CustomInterpolator implements TimeInterpolator {
    @Override
    public float getInterpolation(float input) {
        float result;
        if (input <= 0.5) {
            result = (float) (Math.sin(Math.PI * input)) / 2;
        } else {
            result = (float) (2 - Math.sin(Math.PI * input)) / 2;
        }
        return result;
    }
}
