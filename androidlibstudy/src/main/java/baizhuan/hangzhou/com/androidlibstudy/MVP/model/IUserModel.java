package baizhuan.hangzhou.com.androidlibstudy.MVP.model;

import baizhuan.hangzhou.com.androidlibstudy.MVP.bean.UserInfo;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.androidlibstudy.MVP.model
 * Date:    16-11-15.
 * Time:    下午2:43
 * 类描述： model类  接口 ，  处理 有关model的一些操作
 *
 * @vesion
 */
public interface IUserModel {

    void setFirstName(String firstName);

    void setLastName(String lastName);

    void setId(int id);

    int getId();

    UserInfo getUser(int id);
}
