package baizhuan.hangzhou.com.androidlibstudy;

import java.util.ArrayList;
import java.util.List;

public class Constants {
public static class ImageBean{
    private String title;
    private String path;

    public ImageBean(String title, String path) {
        this.title = title;
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

    public static final String[][] IMAGES = new String[][] {
            {"https://farm4.staticflickr.com/3883/15068310256_891b454952_z.jpg", "Neo"},
            {"https://farm6.staticflickr.com/5577/15068309796_1e0a5432cc_z.jpg", "Trinity"},
            {"https://farm4.staticflickr.com/3871/15090945282_28a77fdf13_z.jpg", "Morpheus"},
            {"https://farm4.staticflickr.com/3920/14904728998_4ea0126f6c_z.jpg", "Merovingian"},
            {"https://farm6.staticflickr.com/5582/15088289881_ea1f961056_z.jpg", "Seraph"},
            {"https://farm6.staticflickr.com/5566/15088290961_ce3ef590bf_z.jpg", "Architect"},
            {"https://farm6.staticflickr.com/5551/14904729458_c160044da4_z.jpg", "Persephone"},
            {"https://farm4.staticflickr.com/3877/14904657620_dca7f86632_z.jpg", "Spoon Boy"},
            {"https://farm6.staticflickr.com/5553/15068310526_bc4ac2dae1_z.jpg", "Engineer"},
            {"https://farm4.staticflickr.com/3843/14904747157_a02c252a5a_z.jpg", "Mifune"},
            {"https://farm4.staticflickr.com/3907/15090945042_4f81659a40_z.jpg", "Oracle"},
            {"https://farm4.staticflickr.com/3840/14904728468_5790cbdf89_z.jpg", "Cypher"},
            {"http://www.bz55.com/uploads1/allimg/120312/1_120312100435_8.jpg", "风景1"},
            {"http://img3.iqilu.com/data/attachment/forum/201308/21/192654ai88zf6zaa60zddo.jpg", "风景2"},
            {"http://img5.imgtn.bdimg.com/it/u=3843856211,3034830484&fm=21&gp=0.jpg", "很宽的风景图片"},
            {"http://img1.imgtn.bdimg.com/it/u=751100488,1866847485&fm=21&gp=0.jpg", "很宽的风景图片2"},
            {"http://dynamic-image.yesky.com/740x-/uploadImages/2012/263/A43538U5B9UR.jpg", "很宽的风景图片3"}




    };


    public static List<ImageBean> getList(){
        List<ImageBean> list=new ArrayList<>();
        for (int  i=0; i< IMAGES.length;i++){
            list.add(new ImageBean(IMAGES[i][1],IMAGES[i][0]));
        }
        return  list;
    }

}
