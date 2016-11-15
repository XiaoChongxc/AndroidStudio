package baizhuan.hangzhou.com.androidlibstudy.MVP.presenter;

import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import baizhuan.hangzhou.com.androidlibstudy.MVP.bean.UserInfo;
import baizhuan.hangzhou.com.androidlibstudy.MVP.model.IUserModel;
import baizhuan.hangzhou.com.androidlibstudy.MVP.model.UserModel;
import baizhuan.hangzhou.com.androidlibstudy.MVP.view.IUserView;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.androidlibstudy.MVP.presenter
 * Date:    16-11-15.
 * Time:    下午2:47
 * 类描述：  登录的 逻辑 处理类
 *
 * @vesion
 */
public class LoginPresenter {
    private IUserModel mUserModer;
    private IUserView mUserView;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    break;
                case 1: //请求成功
                    mUserView.progressunVisibility();
                    Toast.makeText(mUserView.getContext(), "登录成功", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    break;
            }

        }
    };

    public LoginPresenter(IUserView mUserView) {
        this.mUserView = mUserView;
        mUserModer = new UserModel();
    }

    public void saveUser(int id, String firstName, String lastName) {
        mUserModer.setFirstName(firstName);
        mUserModer.setId(id);
        mUserModer.setLastName(lastName);
    }

    public void loadUser(int id) {
        UserInfo user = mUserModer.getUser(id);
        mUserModer.setLastName(user.getLastName());
        mUserModer.setFirstName(user.getFirstName());
    }

    public void clean() {
        mUserView.cleanUserName();
        mUserView.cleanUserPwd();
    }

    public void login() {
        mUserView.getUserName();
        mUserView.getUserPwd();
        mUserView.progressVisibility();
        //模拟 请求 联网操作
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    mHandler.sendEmptyMessage(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }

}
