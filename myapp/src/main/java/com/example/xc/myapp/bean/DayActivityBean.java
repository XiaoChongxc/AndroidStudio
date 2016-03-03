package com.example.xc.myapp.bean;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Author:  @just_for_happy 开心就好
 * Date: 2016-02-25
 * Time: 09:53
 * FIXME
 */
public class DayActivityBean {

    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;

    /**纬度*/
    private String latitude="";
    /***经度*/
    private String longitude="";
    /**当前位置*/
    private String location="";
    /**时间*/
    private String datetime="";
    /**打电话*/
    private String toPhone="";
    /**接电话*/
    private String callPhone="";
    /**发短信*/
    private String toMessage="";
    /**收短信*/
    private String fromMessage="";
    /***手机正在运行的程序*/
    private String usingApp="";
    /***正在操作的程序*/
    private String operatingApp="";



/**位置信息*/
    public DayActivityBean(String location,String latitude, String longitude,  String datetime){
        this.latitude=latitude;
        this.longitude=longitude;
        this.location=location;
        this.datetime=datetime;
    }



    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getToPhone() {
        return toPhone;
    }

    public void setToPhone(String toPhone) {
        this.toPhone = toPhone;
    }

    public String getCallPhone() {
        return callPhone;
    }

    public void setCallPhone(String callPhone) {
        this.callPhone = callPhone;
    }

    public String getToMessage() {
        return toMessage;
    }

    public void setToMessage(String toMessage) {
        this.toMessage = toMessage;
    }

    public String getFromMessage() {
        return fromMessage;
    }

    public void setFromMessage(String fromMessage) {
        this.fromMessage = fromMessage;
    }

    public String getUsingApp() {
        return usingApp;
    }

    public void setUsingApp(String usingApp) {
        this.usingApp = usingApp;
    }

    public String getOperatingApp() {
        return operatingApp;
    }

    public void setOperatingApp(String operatingApp) {
        this.operatingApp = operatingApp;
    }
}
