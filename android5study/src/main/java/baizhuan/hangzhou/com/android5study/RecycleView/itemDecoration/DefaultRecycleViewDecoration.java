package baizhuan.hangzhou.com.android5study.RecycleView.itemDecoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.android5study.RecycleView.itemDecoration
 * Date:    2016-06-22
 * Time:    14:13
 * 类描述：
 */
public class DefaultRecycleViewDecoration extends RecyclerView.ItemDecoration {

    int lineSize = 1;

    public DefaultRecycleViewDecoration(int space) {
        super();
        this.lineSize = space;

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.bottom = lineSize;
        outRect.right = lineSize;
        outRect.left = lineSize;
        outRect.top = lineSize;
        super.getItemOffsets(outRect, view, parent, state);
    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }
}
