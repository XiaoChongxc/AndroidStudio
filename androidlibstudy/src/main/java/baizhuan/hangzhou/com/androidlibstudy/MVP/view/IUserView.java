package baizhuan.hangzhou.com.androidlibstudy.MVP.view;

import android.content.Context;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.androidlibstudy.MVP.model
 * Date:    16-11-15.
 * Time:    下午2:39
 * 类描述：   处理 页面 上的一些 操作
 *
 * @vesion
 */
public interface IUserView {


    String getUserName();

    String getUserPwd();

    void cleanUserName();

    void cleanUserPwd();

    void progressVisibility();

    void progressunVisibility();

    Context getContext();

}
