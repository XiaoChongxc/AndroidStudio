package com.example.administrator.mytest.qqnearbybody.bean;

/**
 * Created by 开心就好（@just_for_happy） on 2016/5/7.
 *
 */
public class Info {
    private int portraitId;//头像id
    private String name;//名字
    private String age;//年龄
    private boolean sex;//false为男，true为女
    private float distance;//距离

    public Info(int portraitId, String name, String age, boolean sex, float distance) {
        this.portraitId = portraitId;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.distance = distance;
    }
    public  Info(){}

    public int getPortraitId() {
        return portraitId;
    }

    public void setPortraitId(int portraitId) {
        this.portraitId = portraitId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }
}
