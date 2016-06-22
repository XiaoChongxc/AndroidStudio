package baizhuan.hangzhou.com.android5study.TextSelection;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ActionMode;
import android.widget.TextView;

import baizhuan.hangzhou.com.android5study.BaseActivity;
import baizhuan.hangzhou.com.android5study.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.android5study.TextSelection
 * Date:    2016-06-20
 * Time:    17:08
 * 类描述：
 */
@TargetApi(23)
public class TextSelectionActivity extends BaseActivity {

    @Bind(R.id.tv_content)
    TextView tvContent;
    ActionMode.Callback2 callback2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_selection);
        ButterKnife.bind(this);
        tvContent.setText("可能版本不兼容， 之后回头再来试试");
//        callback2 = new ActionMode.Callback2(){
//            @Override
//            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
//                getMenuInflater().inflate(R.menu.menu_text_operation,menu);
//                return true;
//            }
//
//            @Override
//            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
//                return false;
//            }
//
//            @Override
//            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
//                Toast.makeText(TextSelectionActivity.this, item.getTitle(), Toast.LENGTH_LONG).show();
//                mode.finish();
//                return false;
//            }
//
//            @Override
//            public void onDestroyActionMode(ActionMode mode) {
//
//            }
//            // 控制浮动菜单的位置
//            @Override
//            public void onGetContentRect(ActionMode mode, View view, Rect outRect) {
//                super.onGetContentRect(mode, view, outRect);
//            }
//        };
//
//        tvContent.setCustomSelectionActionModeCallback(callback2);
    }
}
