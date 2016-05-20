package com.example.administrator.mytest.TestForHttp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.administrator.mytest.R;
import com.example.administrator.mytest.TestForHttp.download_util.DownOrUpLoadFile;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio
 * Package: com.example.administrator.mytest.TestForHttp
 * Date:    2016-05-20
 * Time:    13:56
 * 类描述：  okhttp 下载文件 监听 扩展
 */
public class DownloadFileActivity extends Activity {

    @Bind(R.id.down_progress)
    ProgressBar downProgress;
    @Bind(R.id.btn_down)
    Button btnDown;
    @Bind(R.id.upload_progress)
    ProgressBar uploadProgress;
    @Bind(R.id.btn_upload)
    Button btnUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_down_up_load);
        ButterKnife.bind(this);
        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Down();
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Upload();
            }
        });

    }


    void Down() {
        String url = "http://img.daimg.com/uploads/allimg/130920/1-130920231350.jpg";
        DownOrUpLoadFile.DownLoad(url, new DownOrUpLoadFile.DownOrUpLoadFileCallBack() {
            @Override
            public void onStart(long progress, long total, boolean done) {
                downProgress.setProgress((int) (progress / total) * 100);
                Toast.makeText(DownloadFileActivity.this, "开始下载", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onProgress(long progress, long total, boolean done) {
                downProgress.setProgress((int) (progress / total) * 100);
            }

            @Override
            public void onFinish(long progress, long total, boolean done) {
                downProgress.setProgress((int) (progress / total) * 100);
                Toast.makeText(DownloadFileActivity.this, "下载完成", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void Upload() {

        String url = "http://img.daimg.com/uploads/allimg/130920/1-130920231350.jpg";
        DownOrUpLoadFile.upload(url, "", new DownOrUpLoadFile.DownOrUpLoadFileCallBack() {
            @Override
            public void onStart(long progress, long total, boolean done) {
                uploadProgress.setProgress((int)(progress/total)*100);
                Toast.makeText(DownloadFileActivity.this, "开始上传", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onProgress(long progress, long total, boolean done) {
                uploadProgress.setProgress((int)(progress/total)*100);
            }

            @Override
            public void onFinish(long progress, long total, boolean done) {
                uploadProgress.setProgress((int)(progress/total)*100);
                Toast.makeText(DownloadFileActivity.this, "上传完成", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
