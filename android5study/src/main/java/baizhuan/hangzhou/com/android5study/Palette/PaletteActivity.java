package baizhuan.hangzhou.com.android5study.Palette;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.graphics.Palette;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import baizhuan.hangzhou.com.android5study.BaseActivity;
import baizhuan.hangzhou.com.android5study.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.android5study.Palette
 * Date:    2016-06-21
 * Time:    09:12
 * 类描述： Palatte  调色板类，可以从图像中 提取突出颜色， 这样可以把颜色赋值给  actionbar  toolbar ， 使界面颜色协调
 */
public class PaletteActivity extends BaseActivity {

    @Bind(R.id.img)
    ImageView img;
    @Bind(R.id.btn_1)
    Button btn1;
    @Bind(R.id.btn_2)
    Button btn2;

    Palette p;
    @Bind(R.id.tv_1)
    TextView tv1;
    @Bind(R.id.tv_2)
    TextView tv2;
    @Bind(R.id.tv_3)
    TextView tv3;
    @Bind(R.id.tv_4)
    TextView tv4;
    @Bind(R.id.tv_5)
    TextView tv5;
    @Bind(R.id.tv_6)
    TextView tv6;

    int[] imgs = {R.drawable.bg, R.drawable.bg_1, R.drawable.bg_2, R.drawable.bg_3, R.drawable.bg_4, R.drawable.bg_5};

    int curPosition = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.img)
    void changImage() {
        curPosition++;
        img.setImageResource(imgs[curPosition % 6]);
    }


    //  同步 ，已经被Deprecated  的方法
    void generateByDeprecatedMethod(int paletteSize) {      //调色板大小  默认 16
        Bitmap b = BitmapFactory.decodeResource(getResources(), imgs[curPosition]);
        if (paletteSize <= 0) {
            p = Palette.generate(b);
        } else {
            p = Palette.generate(b, paletteSize);
        }
    }

    //异步方法 ，
    void AsyncGenerateByDeprecateMethod(int paletteSize) {
        Bitmap b = BitmapFactory.decodeResource(getResources(), imgs[curPosition]);
        if (paletteSize <= 0) {
            Palette.generateAsync(b, new Palette.PaletteAsyncListener() {
                @Override
                public void onGenerated(Palette palette) {
                    p = palette;
                }
            });
        } else {
            Palette.generateAsync(b, paletteSize, new Palette.PaletteAsyncListener() {
                @Override
                public void onGenerated(Palette palette) {
                    p = palette;
                }
            });
        }
    }

    @OnClick(R.id.btn_2)
    void AsyncGenerateByBuilder() {
        Bitmap b = BitmapFactory.decodeResource(getResources(), imgs[curPosition % 6]);
        new Palette.Builder(b).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                p = palette;
                getColor();
            }
        });

    }

    @OnClick(R.id.btn_1)
    void GenerateByBuilder() {
        Bitmap b = BitmapFactory.decodeResource(getResources(), imgs[curPosition % 6]);
        p = new Palette.Builder(b).generate();
        getColor();
    }

    void getColor() {
        Palette.Swatch s1 = p.getVibrantSwatch();//充满活力的色板
        Palette.Swatch s2 = p.getDarkVibrantSwatch();//充满活力的暗色类型色板
        Palette.Swatch s3 = p.getLightVibrantSwatch();//充满活力的亮色类型色板
        Palette.Swatch s4 = p.getMutedSwatch();//黯淡的色板
        Palette.Swatch s5 = p.getDarkMutedSwatch();//黯淡的暗色类型色板（翻译过来没有原汁原味的赶脚啊！）
        Palette.Swatch s6 = p.getLightMutedSwatch();//黯淡的亮色类型色板

        setColor(tv1, s1);
        setColor(tv2, s2);
        setColor(tv3, s3);
        setColor(tv4, s4);
        setColor(tv5, s5);
        setColor(tv6, s6);
    }

    void setColor(TextView tv, Palette.Swatch s) {
        if (s != null) {
            tv.setBackgroundColor(s.getRgb());
            tv.setTextColor(s.getTitleTextColor());
        } else {
            tv.setBackgroundColor(Color.WHITE);
            tv.setTextColor(Color.WHITE);
        }
    }


}
