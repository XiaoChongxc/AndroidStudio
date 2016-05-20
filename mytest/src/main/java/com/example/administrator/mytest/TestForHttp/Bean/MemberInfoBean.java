package com.example.administrator.mytest.TestForHttp.Bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 会员
 */
public class MemberInfoBean extends CommonDataBean implements Parcelable {
	/**
	 * 车辆id
	 */
	private long id;
	/**
	 * 城市id
	 */
	private long host;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 现金余额
	 */
	private Double money;
	/**
	 * 是否认证驾照
	 */
	private Integer vdrive;
	/**
	 * 是否认证邮箱
	 */
	private Integer vemail;
	/**
	 * 是否认证身份真实信息
	 */
	private Integer vreal;
	/**
	 * 是否认证手机号码
	 */
	private Integer vmobile;
	/**
	 * ev卡号
	 */
	private String evcard;

	/**
	 * 证件类型
	 */
	private Integer certifyType;

	/**
	 * 证件号码
	 */
	private String certifyNum;
	/**
	 * 证件照片地址
	 */
	private String certifyImage;
	/**
	 * 驾照号码
	 */
	private String driverNum;
	/**
	 * 驾驶证照片地址
	 */
	private String driverImage;

	/**
	 * 头像图片
	 */
	private String headerImg;

	/**
	 * 性别
	 */
	private Integer sex;

	/**
	 * 地址
	 */
	private String address;

	/**
	 * 公司
	 */
	private String company;

	/**
	 * 紧急联系人
	 */
	private String person;

	/**
	 * 号码
	 */
	private String contact;
	/**
	 * 关系
	 */
	private String relation;
	/**
	 * 状态
	 */
	private Integer status;

	/**
	 * 手机账户
	 */
	private String mobile;

	/**
	 * 单位账户ID
	 */
	private Long personId;

	/**
	 * 单位ID
	 */
	private Long unitInfoId;

	/**
	 * 单位名称
	 */
	private String unitName;

	/**
	 * 叫车权限
	 */
	private int csupflag;
	/**
	 * 审核权限
	 */
	private int checkflag;


	/**
	 * 用户城市
	 */
	private String cityname;

	/*
		 * @see android.os.Parcelable#describeContents()
		 */
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * @see android.os.Parcelable#writeToParcel(android.os.Parcel, int)
	 */
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(id);
		dest.writeLong(host);
		dest.writeString(name);
		dest.writeDouble(money);
		dest.writeInt(vdrive);
		dest.writeInt(vemail);
		dest.writeInt(vreal);
		dest.writeInt(vmobile);
		dest.writeString(evcard);
		dest.writeInt(certifyType);
		dest.writeString(certifyNum);
		dest.writeString(certifyImage);
		dest.writeString(driverNum);
		dest.writeString(driverImage);
		dest.writeInt(sex);
		dest.writeString(address);
		dest.writeString(company);
		dest.writeString(person);
		dest.writeString(contact);
		dest.writeString(relation);
		dest.writeInt(status);
		dest.writeString(headerImg);
		dest.writeString(mobile);
		dest.writeLong(personId);
		dest.writeLong(unitInfoId);
		dest.writeString(unitName);
		dest.writeInt(csupflag);
		dest.writeInt(checkflag);
		dest.writeString(cityname);
	}

	public static final Parcelable.Creator<MemberInfoBean> CREATOR = new Parcelable.Creator<MemberInfoBean>() {
		public MemberInfoBean createFromParcel(Parcel in) {
			MemberInfoBean bean = new MemberInfoBean();
			bean.id = in.readLong();
			bean.host = in.readLong();
			bean.name = in.readString();
			bean.name = in.readString();
			bean.money = in.readDouble();
			bean.vdrive = in.readInt();
			bean.vemail = in.readInt();
			bean.vreal = in.readInt();
			bean.vmobile = in.readInt();
			bean.evcard = in.readString();
			bean.certifyType = in.readInt();
			bean.certifyNum = in.readString();
			bean.certifyImage = in.readString();
			bean.driverNum = in.readString();
			bean.driverImage = in.readString();
			bean.sex = in.readInt();
			bean.address = in.readString();
			bean.company = in.readString();
			bean.contact = in.readString();
			bean.relation = in.readString();
			bean.status = in.readInt();
			bean.headerImg = in.readString();
			bean.mobile = in.readString();
			bean.personId = in.readLong();
			bean.unitInfoId = in.readLong();
			bean.unitName = in.readString();
			bean.csupflag = in.readInt();
			bean.checkflag = in.readInt();
			bean.cityname = in.readString();
			return bean;
		}

		public MemberInfoBean[] newArray(int size) {
			return new MemberInfoBean[size];
		}
	};

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the host
	 */
	public long getHost() {
		return host;
	}

	/**
	 * @param host the host to set
	 */
	public void setHost(long host) {
		this.host = host;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the money
	 */
	public Double getMoney() {
		return money;
	}

	/**
	 * @param money the money to set
	 */
	public void setMoney(Double money) {
		this.money = money;
	}

	/**
	 * @return the vdrive
	 */
	public Integer getVdrive() {
		return vdrive;
	}

	/**
	 * @param vdrive the vdrive to set
	 */
	public void setVdrive(Integer vdrive) {
		this.vdrive = vdrive;
	}

	/**
	 * @return the vemail
	 */
	public Integer getVemail() {
		return vemail;
	}

	/**
	 * @param vemail the vemail to set
	 */
	public void setVemail(Integer vemail) {
		this.vemail = vemail;
	}

	/**
	 * @return the vreal
	 */
	public Integer getVreal() {
		return vreal;
	}

	/**
	 * @param vreal the vreal to set
	 */
	public void setVreal(Integer vreal) {
		this.vreal = vreal;
	}

	/**
	 * @return the vmobile
	 */
	public Integer getVmobile() {
		return vmobile;
	}

	/**
	 * @param vmobile the vmobile to set
	 */
	public void setVmobile(Integer vmobile) {
		this.vmobile = vmobile;
	}

	/**
	 * @return the evcard
	 */
	public String getEvcard() {
		return evcard;
	}

	/**
	 * @param evcard the evcard to set
	 */
	public void setEvcard(String evcard) {
		this.evcard = evcard;
	}

	/**
	 * @return the certifyType
	 */
	public Integer getCertifyType() {
		return certifyType;
	}

	/**
	 * @param certifyType the certifyType to set
	 */
	public void setCertifyType(Integer certifyType) {
		this.certifyType = certifyType;
	}

	/**
	 * @return the certifyNum
	 */
	public String getCertifyNum() {
		return certifyNum;
	}

	/**
	 * @param certifyNum the certifyNum to set
	 */
	public void setCertifyNum(String certifyNum) {
		this.certifyNum = certifyNum;
	}

	/**
	 * @return the certifyImage
	 */
	public String getCertifyImage() {
		return certifyImage;
	}

	/**
	 * @param certifyImage the certifyImage to set
	 */
	public void setCertifyImage(String certifyImage) {
		this.certifyImage = certifyImage;
	}

	/**
	 * @return the driverNum
	 */
	public String getDriverNum() {
		return driverNum;
	}

	/**
	 * @param driverNum the driverNum to set
	 */
	public void setDriverNum(String driverNum) {
		this.driverNum = driverNum;
	}

	/**
	 * @return the driverImage
	 */
	public String getDriverImage() {
		return driverImage;
	}

	/**
	 * @param driverImage the driverImage to set
	 */
	public void setDriverImage(String driverImage) {
		this.driverImage = driverImage;
	}

	/**
	 * @return the sex
	 */
	public Integer getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * @return the person
	 */
	public String getPerson() {
		return person;
	}

	/**
	 * @param person the person to set
	 */
	public void setPerson(String person) {
		this.person = person;
	}

	/**
	 * @return the contact
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * @param contact the contact to set
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}

	/**
	 * @return the relation
	 */
	public String getRelation() {
		return relation;
	}

	/**
	 * @param relation the relation to set
	 */
	public void setRelation(String relation) {
		this.relation = relation;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the personId
	 */
	public Long getPersonId() {
		return personId;
	}

	/**
	 * @param personId the personId to set
	 */
	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	/**
	 * @return the unitInfoId
	 */
	public Long getUnitInfoId() {
		return unitInfoId;
	}

	/**
	 * @param unitInfoId the unitInfoId to set
	 */
	public void setUnitInfoId(Long unitInfoId) {
		this.unitInfoId = unitInfoId;
	}

	/**
	 * @return the unitName
	 */
	public String getUnitName() {
		return unitName;
	}

	/**
	 * @param unitName the unitName to set
	 */
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	/**
	 * @return the headerImg
	 */
	public String getHeaderImg() {
		return headerImg;
	}

	/**
	 * @param headerImg the headerImg to set
	 */
	public void setHeaderImg(String headerImg) {
		this.headerImg = headerImg;
	}

	public String getSexText() {
		if (this.sex != null) {
			if (sex == 0) {
				return "女";
			} else if (sex == 1) {
				return "男";
			}
		}
		return null;
	}

	/**
	 * 0:未认证 1:已认证 2:等待认证 3:认证失败
	 *
	 * @return
	 * @author qsxiaogang
	 * @createtime 2015年8月19日 下午3:27:49
	 */
	public String getVStatusText(int status) {
		if (status == 0) {
			return "未认证";
		} else if (status == 1) {
			return "已认证";
		} else if (status == 2) {
			return "等待认证";
		} else if (status == 3) {
			return "认证失败";
		}
		return null;
	}

	public String getVMobileText() {
		return getVStatusText(this.vmobile);
	}

	public String getVEmailText() {
		return getVStatusText(this.vemail);
	}

	public String getVRealText() {
		return getVStatusText(this.vreal);
	}

	public String getVDriveText() {
		return getVStatusText(this.vdrive);
	}

	public int getCheckflag() {
		return checkflag;
	}

	public void setCheckflag(int checkflag) {
		this.checkflag = checkflag;
	}

	public int getCsupflag() {
		return csupflag;
	}

	public void setCsupflag(int csupflag) {
		this.csupflag = csupflag;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
}
