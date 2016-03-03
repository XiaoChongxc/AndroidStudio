package com.example.administrator.customshapeimageview.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.example.administrator.customshapeimageview.R;
import com.example.administrator.customshapeimageview.widget.shader.ShaderHelper;
import com.example.administrator.customshapeimageview.widget.shader.SvgShader;

public class HexagonImageView extends ShaderImageView {

    public HexagonImageView(Context context) {
        super(context);
    }

    public HexagonImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HexagonImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public ShaderHelper createImageViewHelper() {
        return new SvgShader(R.raw.imgview_hexagon);
    }
}
