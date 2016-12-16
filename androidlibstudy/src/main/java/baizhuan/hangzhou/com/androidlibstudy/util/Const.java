package baizhuan.hangzhou.com.androidlibstudy.util;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.androidlibstudy.util
 * Date:    16-11-4.
 * Time:    下午4:14
 * 类描述：
 *
 * @vesion
 */
public class Const {

    /**
     * H5 页面的链接参数名
     */
    public static final String H5URL = "H5URL";

    /**
     * H5 页面的标题
     */
    public static final String H5TITLE = "H5TITLE";


    /***
     * String2int
     */
    public static int string2int(String str) {

        int a;
        try {
            a = Integer.parseInt(str);
        } catch (Exception e) {
            a = 0;
        }
        return a;
    }

}
