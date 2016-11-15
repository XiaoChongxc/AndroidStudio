package baizhuan.hangzhou.com.androidlibstudy.util;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio
 * Package: com.example.administrator.mytest.TestForHttp
 * Date:    2016-06-01
 * Time:    14:13
 * 类描述：
 */
public class CustomResponseBodyConverter implements Converter<ResponseBody, String> {


    @Override
    public String convert(ResponseBody value) throws IOException {
        String ss = new String(value.bytes(), "gbk");
        return ss;
    }
}
