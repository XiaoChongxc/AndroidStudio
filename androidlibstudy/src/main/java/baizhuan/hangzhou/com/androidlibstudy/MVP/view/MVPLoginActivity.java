package baizhuan.hangzhou.com.androidlibstudy.MVP.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import baizhuan.hangzhou.com.androidlibstudy.BaseActivity;
import baizhuan.hangzhou.com.androidlibstudy.MVP.presenter.LoginPresenter;
import baizhuan.hangzhou.com.androidlibstudy.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.androidlibstudy.MVP.activity
 * Date:    16-11-15.
 * Time:    下午2:29
 * 类描述：    mvp  模式下  登录 模拟
 *
 * @vesion
 */
public class MVPLoginActivity extends BaseActivity implements IUserView {

    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.et_pwd)
    EditText etPwd;
    @Bind(R.id.btn_login)
    Button btnLogin;
    @Bind(R.id.btn_clean)
    Button btnClean;
    @Bind(R.id.progress)
    ProgressBar progress;
    private LoginPresenter mLoginPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp_login);
        ButterKnife.bind(this);
        mLoginPresenter =new LoginPresenter(this);
    }

    @OnClick(R.id.btn_clean)
    void onClen() {
        mLoginPresenter.clean();
    }

    @OnClick(R.id.btn_login)
    void onLogin() {
        mLoginPresenter.login();
    }

    @Override
    public String getUserName() {
        return etName.getText().toString();
    }

    @Override
    public String getUserPwd() {
        return etPwd.getText().toString();
    }

    @Override
    public void cleanUserName() {
        etName.setText("");
    }

    @Override
    public void cleanUserPwd() {
        etPwd.setText("");
    }

    @Override
    public void progressVisibility() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void progressunVisibility() {
        progress.setVisibility(View.GONE);
    }

    @Override
    public Context getContext() {
        return mContext;
    }
}
