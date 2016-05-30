package baizhuan.hangzhou.com.gankcopy.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio
 * Package: baizhuan.hangzhou.com.gankcopy.util
 * Date:    2016-05-30
 * Time:    11:57
 * 类描述： 图片处理类
 */
public class ImageUtil {
    //妹纸保存路径
    final static String imgDir = Environment.getExternalStorageDirectory().getPath() + "/GankCopyDemo";

    public static Uri    saveImage(Context ctx, String imgName, Bitmap bitmap, View view) {
        File fileDir = new File(imgDir);
        if (!fileDir.exists()) {
            fileDir.mkdir();
        }
        //创建文件
        File imageFile = new File(fileDir, imgName + ".png");
        try {
            FileOutputStream fos = new FileOutputStream(imageFile);
            boolean compress = bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            if (compress) {
                Snackbar.make(view, "妹纸已经躺在你的图库里啦.. ( ＞ω＜)", Snackbar.LENGTH_SHORT).show();
            } else {
                Snackbar.make(view, "妹纸拒绝了你的请求.. ( ＞ω＜)", Snackbar.LENGTH_SHORT).show();
            }
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Uri uri = Uri.fromFile(imageFile);
        //发送广播，通知图库更新
        ctx.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,uri));
        return uri;
    }
}
