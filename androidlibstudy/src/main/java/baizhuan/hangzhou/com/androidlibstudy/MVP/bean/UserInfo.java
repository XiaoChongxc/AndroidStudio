package baizhuan.hangzhou.com.androidlibstudy.MVP.bean;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.androidlibstudy.MVP.model
 * Date:    16-11-15.
 * Time:    下午2:30
 * 类描述：  model  类
 *
 * @vesion
 */
public class UserInfo {

    private String firstName;
    private String lastName;

    public UserInfo(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
