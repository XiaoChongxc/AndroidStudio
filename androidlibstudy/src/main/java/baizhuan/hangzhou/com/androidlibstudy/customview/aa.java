package baizhuan.hangzhou.com.androidlibstudy.customview;

/**
 * Created by Administrator on 2016/9/19.
 */
public class aa {

    public static void main(String[] args) {
        test1();

    }





    public static void test1() {

        boolean bol1[] = {true, false};
        boolean bol2[] = {true, false};
        boolean bol3[] = {true, false};
        boolean bol4[] = {true, false};
        boolean bol5[] = {true, false};


        long  starttime =System.currentTimeMillis();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    for (int l = 0; l < 2; l++) {
                        for (int m = 0; m < 2; m++) {
                            boolean b[] = new boolean[5];
                            b[0] = bol1[i];
                            b[1] = bol2[j];
                            b[2] = bol2[k];
                            b[3] = bol2[l];
                            b[4] = bol2[m];
                            //剔除 全部 喂true false 情況
//                            if (b[0] && b[1] && b[2] && b[3] && b[4]) break;
//                            if (!b[0] && !b[1] && !b[2] && !b[3] && !b[4]) break;
                            //三个false  2个true
                            int numTrue = 0;
                            for (boolean bb : b ) {
                                if(bb)  numTrue++;
                            }
                            if(numTrue==2){
                                // 判断 a 的话
                                if(b[0]==true &&  b[3]==false  || b[0]==false && b[3]==true){
                                    //判断b的话
                                    if(b[1]==true && b[2]==false  || b[1]==false && b[2]==true){
                                        //判断c的话
                                        if(b[2]==true && b[0]==true  ||b[2]==false && b[0]==false){
                                            //判断d
                                            if(b[3]==true && b[4]==false || b[3]==false && b[4]==true){
                                                if(b[4]==true && b[1]==false ||b[4]==false &&b[1]==true){
                                                    long  curTime =System.currentTimeMillis();
                                                    System.out.println(curTime);
                                                    System.out.println(starttime );
                                                    System.out.println(curTime-starttime +"秒");
                                                    System.out.println("a:"+b[0]+"\tb:"+b[1]+"\tc:"+b[2]+"\td:"+b[3]+"\te:"+b[4]);
                                                }

                                            }
                                        }

                                    }


                                }



                            }


                        }
                    }

                }
            }
        }
    }
}
