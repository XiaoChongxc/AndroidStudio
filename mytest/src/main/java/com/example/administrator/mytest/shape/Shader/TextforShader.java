package com.example.administrator.mytest.shape.Shader;/**
 * Created by  @just_for_happy (@开心就好)   on 2015/12/31.
 * Date: 2015-12-31
 * Time: 17:14
 * Usege: 功能描述。。。
 */

import android.app.Activity;
import android.os.Bundle;

import com.example.administrator.mytest.R;

/**
 * author: just_for_happy(@开心就好)
 * Date: 2015-12-31 
 * Time: 17:14 
 * FIXME   Shader类包括了5个直接子类，分别为：BitmapShader、ComposeShader、LinearGradient、RadialGradient以及SweepGradient。
 * 其中，BitmapShader用于图像渲染；
 * ComposeShader用于混合渲染；LinearGradient用于线性渲染；RadialGradient用于环形渲染；而SweepGradient则用于梯度渲染。
 */
public class TextforShader  extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textforshader);
    }
}
