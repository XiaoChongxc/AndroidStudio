package baizhuan.hangzhou.com.androidlibstudy.Rxjava.model;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.androidlibstudy.RxjavaTest.model
 * Date:    2016-07-12
 * Time:    14:37
 * 类描述：
 */
public class BaseResult<T> {

    /**
     * Msg : 检测到最新版本，成功
     * List :
     * Result : 01
     */

    private String Msg;

    private T List;
    private String Result;

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String Msg) {
        this.Msg = Msg;
    }

    public T getList() {
        return List;
    }

    public void setList(T List) {
        this.List = List;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String Result) {
        this.Result = Result;
    }


}
