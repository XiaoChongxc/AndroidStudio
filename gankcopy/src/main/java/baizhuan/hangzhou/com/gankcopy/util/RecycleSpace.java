package baizhuan.hangzhou.com.gankcopy.util;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio
 * Package: baizhuan.hangzhou.com.gankcopy.util
 * Date:    2016-05-26
 * Time:    17:27
 * 类描述：
 */
public class RecycleSpace extends RecyclerView.ItemDecoration {

    private  int  space;

    public RecycleSpace(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left=space;
        outRect.bottom=space;
        outRect.right=space;
        if(parent.getChildAdapterPosition(view)==0){
            outRect.top=space;
        }
    }
}
