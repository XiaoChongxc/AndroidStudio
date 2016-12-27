package baizhuan.hangzhou.com.androidlibstudy.matrix;

import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import baizhuan.hangzhou.com.androidlibstudy.BaseActivity;
import baizhuan.hangzhou.com.androidlibstudy.R;
import baizhuan.hangzhou.com.androidlibstudy.adapter.BaseAdapter;
import baizhuan.hangzhou.com.androidlibstudy.customview.MatrixView;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author :   Xchong
 * 项目名：   AndroidStudio2
 * 包名   :   baizhuan.hangzhou.com.androidlibstudy.matrix
 * 日期   :   2016/12/21
 * 时间   ：  14:00
 * 描述   ：  演示 matrix 的 activity
 */
public class MatrixActivity extends BaseActivity {

    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;

    BaseAdapter adapter;

    Matrix matrix;
    @Bind(R.id.img_head)
    MatrixView imgHead;

    final float DEFUALT_S = 0.5f;
    final float DEFUALT_R = 90;
    final int DEFUALT_T = -300;
    float curS = DEFUALT_S;
    float curR = DEFUALT_R;
    int curT = DEFUALT_T;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix);
        ButterKnife.bind(this);
        matrix = new Matrix();
        recyclerview.setLayoutManager(new GridLayoutManager(mContext, 4));

        List list = new ArrayList();

        list.add("缩放");
        list.add("旋转");
        list.add("平移");
        list.add("扭曲");
        list.add("重置");
        list.add("动画缩放");
        list.add("动画旋转");
        list.add("动画平移");
        list.add("动画扭曲");





        adapter = new BaseAdapter(list, mContext);
        recyclerview.setAdapter(adapter);
        adapter.setOnclickLinstener(new BaseAdapter.OnItemClickLintener() {
            @Override
            public void onItemclick(int position) {

                switch (position) {
                    case 0:  //缩放
                        if (curS >= 0.5f && curS < 1.5f) {
                            curS += 0.1f;
                        }
                        imgHead.setScale(curS, curS);
                        break;
                    case 1:
                        //旋转
                        curR += 45;
                        imgHead.setRotate(curR);
                        break;
                    case 2:
                        //平移
                        if (curT >= -300 && curT < 300) {
                            curT += 10;
                        }
                        imgHead.setTranslate(curT, curT);
                        break;

                    case 3:
                        //扭曲
                        if(curS>=1.5){
                            curS=DEFUALT_S;
                        }

                        if (curS >= 0.5f && curS < 1.5f) {
                            curS += 0.1f;
                        }
                        imgHead.setSkew(curS, curS);
                        break;
                    case 4:
                        //重置
                        imgHead.reset();
                        reset();
                        break;

                    case 5:  //缩放
                        imgHead.setAnimalScanle(1,2f);
                        break;
                    case 6:
                        //旋转
                        imgHead.setAnimalRotate(0,360);
                        break;
                    case 7:
                        //平移
                        imgHead.setAnimalTranslate(-300,300);
                        break;

                    case 8:
                        //扭曲
                        imgHead.setAnimalSkew(1f,2f);
                        break;
                }

                Log.d("TAG", "onItemclick:curs: "+curS+"\tcurR:"+curR+"\tcurT:"+curT);
            }
        });
    }

    private void reset() {
        curS = DEFUALT_S;
        curR = DEFUALT_R;
        curT = DEFUALT_T;
    }
}
