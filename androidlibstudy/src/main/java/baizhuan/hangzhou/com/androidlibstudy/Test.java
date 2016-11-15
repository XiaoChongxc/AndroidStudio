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
//        test3(20, 41, 15);
        test4();

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

    public static void test4() {
        int[] nums = new int[4];
        for (int i = 0; i < 4; i++) {
            System.out.println(Math.random() + "");
            nums[i] = (int) (Math.random() * 14);
        }
        String str[] = {"+", "-", "*", "/"};
        for (String s1 : str) {
            for (String s2 : str) {
                for (String s3 : str) {
                    for (String s4 : str) {

                        for (int num1 : nums) {
                            for (int num2 : nums) {
                                if (num1 == num2) continue;
                                for (int num3 : nums) {
                                    if (num2 == num3) continue;
                                    for (int num4 : nums) {
                                        if (num3 == num4) continue;


                                    }
                                }
                            }

                        }


                    }
                }
            }
        }

    }

    public static int cal(String s, int num1, int num2) {
        if (s.equals("+")) {
            return num1 + num2;

        } else if (s.equals("-")) {
            return num1 - num2;

        } else if (s.equals("*")) {
            return num1 * num2;

        } else if (s.equals("/")) {
            return num1 / num2;
        }
        return 0;
    }


}
