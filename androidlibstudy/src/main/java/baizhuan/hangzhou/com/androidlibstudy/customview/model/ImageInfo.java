package baizhuan.hangzhou.com.androidlibstudy.customview.model;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.androidlibstudy.customview.model
 * Date:    16-12-13.
 * Time:    下午3:05
 * 类描述：
 *
 * @vesion
 */
public class ImageInfo {

    private int width;
    private int height;
    private int resId;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public ImageInfo() {
    }

    public ImageInfo(int width, int height, int resId) {
        this.width = width;
        this.height = height;
        this.resId = resId;
    }
}
