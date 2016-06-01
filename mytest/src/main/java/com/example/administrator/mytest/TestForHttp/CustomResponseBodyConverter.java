package com.example.administrator.mytest.TestForHttp;

import com.google.gson.Gson;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.lang.reflect.Type;

import retrofit.Converter;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio
 * Package: com.example.administrator.mytest.TestForHttp
 * Date:    2016-06-01
 * Time:    14:13
 * 类描述：
 */
public class CustomResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;

    CustomResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String ss = new String(value.bytes(), "gbk");
        ss = ss.substring(ss.indexOf("(") + 1, ss.lastIndexOf(")"));
        try {
            return gson.fromJson(ss, type);
        } finally {

        }

    }
}
