package baizhuan.hangzhou.com.androidlibstudy.H5;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import baizhuan.hangzhou.com.androidlibstudy.BaseActivity;
import baizhuan.hangzhou.com.androidlibstudy.R;
import baizhuan.hangzhou.com.androidlibstudy.util.Const;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.androidlibstudy.H5
 * Date:    16-11-4.
 * Time:    下午4:10
 * 类描述：
 *
 * @vesion
 */
public class H5Activity extends BaseActivity {


    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.web)
    WebView web;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);

        WebSettings webSettings = web.getSettings();
        webSettings.setUseWideViewPort(true);//设置此属性，可任意比例缩放
        webSettings.setJavaScriptEnabled(true);  //支持js
        webSettings.setUseWideViewPort(false);  //将图片调整到适合webview的大小
        webSettings.setSupportZoom(true);  //支持缩放
//        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);  //关闭webview中缓存
        webSettings.setNeedInitialFocus(true); //当webview调用requestFocus时为webview设置节点
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        webSettings.setLoadsImagesAutomatically(true);  //支持自动加载图片


//        webSettings.setBuiltInZoomControls(true);
//不使用缓存：
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        web.setWebViewClient(new WebViewClient() {
                                 @Override
                                 public void onPageFinished(WebView view, String url) {

                                 }

                                 @Override
                                 public void onReceivedError(WebView view, int errorCode,
                                                             String description, String failingUrl) {

                                 }
                             }

        );

        //        1、LoadUrl            直接加载网页、图片并显示.（本地或是网络上的网页、图片、gif）
//        2、LoadData           显示文字与图片内容 （模拟器1.5、1.6）
//        3、LoadDataWithBase  显示文字与图片内容（支持多个模拟器版本）
//        setPluginsEnabled(true);  //支持插件
//
//        setUseWideViewPort(false);  //将图片调整到适合webview的大小
//
//        setSupportZoom(true);  //支持缩放
//
//        setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN); //支持内容重新布局
//
//        supportMultipleWindows();  //多窗口
//
//        setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);  //关闭webview中缓存
//
//        setAllowFileAccess(true);  //设置可以访问文件
//
//        setNeedInitialFocus(true); //当webview调用requestFocus时为webview设置节点
//
//        webview webSettings.setBuiltInZoomControls(true); //设置支持缩放
//
//        setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
//
//        setLoadWithOverviewMode(true); // 缩放至屏幕的大小
//
//        setLoadsImagesAutomatically(true);  //支持自动加载图片
        initData();
    }

    public void initView() {

    }

    public void initData() {
        String url = getIntent().getStringExtra(Const.H5URL);
        String title = getIntent().getStringExtra(Const.H5TITLE);
        tvTitle.setText(title);
        web.loadUrl(url);
    }
}
