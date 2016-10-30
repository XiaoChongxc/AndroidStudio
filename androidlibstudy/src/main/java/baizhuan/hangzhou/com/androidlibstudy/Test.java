package baizhuan.hangzhou.com.androidlibstudy;

import java.util.Arrays;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.androidlibstudy
 * Date:    2016-07-29
 * Time:    10:44
 * 类描述：
 */
public class Test {

    public static void main(String[] args) {
        test1();
        test2();
        test3(20,41,15);


    }


    //1
    public static void test1() {
        int t, s;
        t = 1;
        s = 0;
        for (int i = 1; i <= 20; i++) {
            t *= i;
            s += t;
        }
        System.out.println("s=" + s);

    }


    //2
    public static void test2() {

        int 学习成绩 = 80;
        String result = (学习成绩 == 90 ? "A" : 学习成绩 >= 60 && 学习成绩 <= 89 ? "B" : 学习成绩 < 60 ? "C" : "");
        System.out.println(result);
    }


    //3
    public static void test3(int a, int b, int c) {
        int[] array = new int[]{a, b, c};
        Arrays.sort(array);

        for (int i : array) {
            System.out.println(i);
        }


    }


}
