package com.example.administrator.customshapeimageview.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.example.administrator.customshapeimageview.R;
import com.example.administrator.customshapeimageview.widget.shader.ShaderHelper;
import com.example.administrator.customshapeimageview.widget.shader.SvgShader;

public class HeartImageView extends ShaderImageView {

    public HeartImageView(Context context) {
        super(context);
    }

    public HeartImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HeartImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public ShaderHelper createImageViewHelper() {
        return new SvgShader(R.raw.imgview_heart, SvgShader.BORDER_TYPE_FILL);
    }
}
