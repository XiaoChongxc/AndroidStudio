package baizhuan.hangzhou.com.androidlibstudy.Rxjava.model;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.androidlibstudy.Rxjava.model
 * Date:    2016-07-13
 * Time:    16:51
 * 类描述：
 */
public class Version {

    /**
     * content : 1.修复缺陷；
     2.优化结构；
     3.为下一次更新方便，建议本次升级。
     * gxcd : 1
     * xbbh : 2.1.7
     * xzdz : http://www.52wzb.com/wzb2/app/h5/file/wzb-OPPO-release.apk
     */

    private String content;
    private String gxcd;
    private String xbbh;
    private String xzdz;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getGxcd() {
        return gxcd;
    }

    public void setGxcd(String gxcd) {
        this.gxcd = gxcd;
    }

    public String getXbbh() {
        return xbbh;
    }

    public void setXbbh(String xbbh) {
        this.xbbh = xbbh;
    }

    public String getXzdz() {
        return xzdz;
    }

    public void setXzdz(String xzdz) {
        this.xzdz = xzdz;
    }
}
