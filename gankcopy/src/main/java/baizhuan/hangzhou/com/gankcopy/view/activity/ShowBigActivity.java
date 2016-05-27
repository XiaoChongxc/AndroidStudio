package baizhuan.hangzhou.com.gankcopy.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import baizhuan.hangzhou.com.gankcopy.R;
import baizhuan.hangzhou.com.gankcopy.adapter.ShowBigAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio
 * Package: baizhuan.hangzhou.com.gankcopy.view.activity
 * Date:    2016-05-27
 * Time:    11:26
 * 类描述：查看大图 ， 然后保存
 */
public class ShowBigActivity extends Activity {


    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.img_return)
    ImageView imgReturn;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_cur_position)
    TextView tvCurPosition;
    @Bind(R.id.lin_title)
    RelativeLayout linTitle;
    @Bind(R.id.img_del)
    ImageView imgDel;
    @Bind(R.id.img_save)
    ImageView imgSave;
    @Bind(R.id.img_share)
    ImageView imgShare;
    @Bind(R.id.lin_bottom)
    LinearLayout linBottom;

    List<String> data;      //数据源
    int curPosition;        //当前的选中项索引

    public static final String DATA_SOURCE_LIST = "DATA_SOURCE_LIST";
    public static final String DATA_SOURCE_OBJECT = "DATA_SOURCE_OBJECT";

    ShowBigAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_big);
        ButterKnife.bind(this);
        data = new ArrayList<>();
        //接收  从 前一个页面传来的 数据
        ArrayList list = getIntent().getStringArrayListExtra(DATA_SOURCE_LIST);
        if (list != null) data.addAll(list);
        String object = getIntent().getStringExtra(DATA_SOURCE_OBJECT);
        if (object != null) data.add(object);

        tvTitle.setText("1/"+data.size());
        adapter = new ShowBigAdapter(this, data);
        viewpager.setAdapter(adapter);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                tvTitle.setText(handFileName(data.get(position)));
                tvCurPosition.setText(position + "/" + data.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        adapter.setOnImageClickListener(new ShowBigAdapter.OnImageClickListener() {
            @Override
            public void onClick(int position) {
                if (linBottom.isShown()) {


                    linBottom.setVisibility(View.GONE);

                    //加上动画
                } else {
                    linBottom.setVisibility(View.VISIBLE);
                }
            }
        });
    }


    String handFileName(String file) {
        String name = file.substring(file.lastIndexOf("/"), file.lastIndexOf("."));
        return name;
    }

    @OnClick(R.id.img_save)
    void onSave() {

    }

    @OnClick(R.id.img_return)
    void onReturn() {
        finish();
    }

    @OnClick(R.id.img_del)
    void onDelete() {   //浏览本地图片 显示

    }

    @OnClick(R.id.img_share)
    void onShare() {

    }


}
