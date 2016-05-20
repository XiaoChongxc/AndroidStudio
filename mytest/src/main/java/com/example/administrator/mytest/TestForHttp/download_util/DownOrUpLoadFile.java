package com.example.administrator.mytest.TestForHttp.download_util;

import android.util.Log;

import com.example.administrator.mytest.TestForHttp.download_util.helper.ProgressHelper;
import com.example.administrator.mytest.TestForHttp.download_util.listener.impl.UIProgressListener;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio
 * Package: com.example.administrator.mytest.TestForHttp.Dao
 * Date:    2016-05-20
 * Time:    13:58
 * 类描述：  上传 或者下载的   封装类
 */
public class DownOrUpLoadFile {


   public  interface DownOrUpLoadFileCallBack {
        void onStart(long progress, long total, boolean done);

        void onProgress(long progress, long total, boolean done);

        void onFinish(long progress, long total, boolean done);
    }


    public static void DownLoad(String url,final DownOrUpLoadFileCallBack callBack) {
        OkHttpClient client = new OkHttpClient.Builder()
                //设置超时，不设置可能会报异常
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .build();
        final UIProgressListener uiProgressResponseListener = new UIProgressListener() {
            @Override
            public void onUIProgress(long bytesRead, long contentLength, boolean done) {
                Log.e("TAG", "bytesRead:" + bytesRead);
                Log.e("TAG", "contentLength:" + contentLength);
                Log.e("TAG", "done:" + done);
                if (contentLength != -1) {
                    //长度未知的情况下回返回-1
                    Log.e("TAG", (100 * bytesRead) / contentLength + "% done");
                }
                Log.e("TAG", "================================");
                callBack.onProgress(bytesRead, contentLength, done);
            }

            @Override
            public void onUIStart(long bytesRead, long contentLength, boolean done) {
                super.onUIStart(bytesRead, contentLength, done);
                callBack.onStart(bytesRead, contentLength, done);
            }

            @Override
            public void onUIFinish(long bytesRead, long contentLength, boolean done) {
                super.onUIFinish(bytesRead, contentLength, done);
                callBack.onFinish(bytesRead, contentLength, done);
            }
        };   //构造请求
        final Request request1 = new Request.Builder()
                .url(url)
                .build();

        //包装Response使其支持进度回调
        ProgressHelper.addProgressResponseListener(client, uiProgressResponseListener).newCall(request1).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.e("TAG", "error ", e);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                Log.e("TAG", response.body().string());
            }
        });
    }


    public static void upload(String url, String FilePath,final DownOrUpLoadFileCallBack callBack) {
        OkHttpClient client = new OkHttpClient.Builder()
                //设置超时，不设置可能会报异常
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .build();
        File file = new File(FilePath);
        //此文件必须在手机上存在，实际情况下请自行修改，这个目录下的文件只是在我手机中存在。

        //这个是非ui线程回调，不可直接操作UI progressListener
        //这个是ui线程回调，可直接操作UI
        final UIProgressListener uiProgressRequestListener = new UIProgressListener() {
            @Override
            public void onUIProgress(long bytesWrite, long contentLength, boolean done) {
                Log.e("TAG", "bytesWrite:" + bytesWrite);
                Log.e("TAG", "contentLength" + contentLength);
                Log.e("TAG", (100 * bytesWrite) / contentLength + " % done ");
                Log.e("TAG", "done:" + done);
                Log.e("TAG", "================================");
                callBack.onProgress(bytesWrite, contentLength, done);
            }

            @Override
            public void onUIStart(long bytesWrite, long contentLength, boolean done) {
                super.onUIStart(bytesWrite, contentLength, done);
                callBack.onStart(bytesWrite, contentLength, done);
            }

            @Override
            public void onUIFinish(long bytesWrite, long contentLength, boolean done) {
                super.onUIFinish(bytesWrite, contentLength, done);
                callBack.onFinish(bytesWrite, contentLength, done);
            }
        };

        //构造上传请求，类似web表单
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("hello", "android")
                .addFormDataPart("photo", file.getName(), RequestBody.create(null, file))
                .addPart(Headers.of("Content-Disposition", "form-data; name=\"another\";filename=\"another.dex\""), RequestBody.create(MediaType.parse("application/octet-stream"), file))
                .build();

        //进行包装，使其支持进度回调
        final Request request = new Request.Builder().url(url)
                .post(ProgressHelper.addProgressRequestListener(requestBody, uiProgressRequestListener))
                .build();
        //开始请求
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.e("TAG", "error ", e);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                Log.e("TAG", response.body().string());
            }
        });
    }


}
