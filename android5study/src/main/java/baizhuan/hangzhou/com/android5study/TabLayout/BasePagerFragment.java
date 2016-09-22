package baizhuan.hangzhou.com.android5study.TabLayout;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.android5study.TabLayout
 * Date:    2016-07-27
 * Time:    14:51
 * 类描述：  为了解决 viewpager  预加载
 * 会在 页面完全展示之后 才加载数据
 */
public abstract class BasePagerFragment extends Fragment {
    /***
     * 视图初始化
     */
    protected boolean isViewInitiated;
    /***
     * 用户可见
     */
    protected boolean isVisibleToUser;
    /***
     * 数据初始化
     */
    protected boolean isDataInitiated;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewInitiated = true;
        prepareFetchData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        prepareFetchData();
    }

    public boolean prepareFetchData() {
        return prepareFetchData(false);
    }

    public boolean prepareFetchData(boolean forceUpdate) {
        if (isVisibleToUser && isViewInitiated && (!isDataInitiated || forceUpdate)) {
            fetchData();
            isDataInitiated = true;
            return true;
        }
        return false;
    }

    public abstract void fetchData();
}
