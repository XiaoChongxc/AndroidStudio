package baizhuan.hangzhou.com.androidlibstudy;

public class MineResult extends BaseResult {


	private String userID;
	private String userNick;//昵称  -
	private String photo_url;//头像地址  -
	private String vip_url;//VIP标志图像地址
	private String userJ;//
	private String userGE;//豆豆总数
	private int smsCount;//消息数量
	private int sVipLv;//超级VIP等级<=0 不显示领奖按钮
	private String checkInState;//签到状态 0已签到 1未签到
	private String sVipState ;//超V补助 0已领取 1未领取
	private String userNet;
	private int isFinsh;
	private int error;
	private String msg;
	
	public String getUserNet() {
		return userNet;
	}
	public void setUserNet(String userNet) {
		this.userNet = userNet;
	}
	public String getUserNick() {
		return userNick;
	}
	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}
	public String getPhoto_url() {
		return photo_url;
	}
	public void setPhoto_url(String phott_url) {
		this.photo_url = phott_url;
	}
	public String getVip_url() {
		return vip_url;
	}
	public void setVip_url(String vip_url) {
		this.vip_url = vip_url;
	}
	public String getUserGE() {
		return userGE;
	}
	public void setUserGE(String userGE) {
		this.userGE = userGE;
	}
	public int getsVipLv() {
		return sVipLv;
	}
	public void setsVipLv(int sVipLv) {
		this.sVipLv = sVipLv;
	}
	public int getSmsCount() {
		return smsCount;
	}
	public void setSmsCount(int smsCount) {
		this.smsCount = smsCount;
	}
	public String getCheckInState() {
		return checkInState;
	}
	public void setCheckInState(String checkInState) {
		this.checkInState = checkInState;
	}
	public String getsVipState() {
		return sVipState;
	}
	public void setsVipState(String sVipState) {
		this.sVipState = sVipState;
	}
	public String getUserJ() {
		return userJ;
	}
	public void setUserJ(String userJ) {
		this.userJ = userJ;
	}
	public int getIsFinsh() {
		return isFinsh;
	}
	public void setIsFinsh(int isFinsh) {
		this.isFinsh = isFinsh;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}

	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
//	@Override
//	public String toString() {
//		return "MineResult [userNick=" + userNick + ", photo_url=" + photo_url
//				+ ", vip_url=" + vip_url + ", userGE=" + userGE + ", smsCount="
//				+ smsCount + ", sVipLv=" + sVipLv + ", checkInState="
//				+ checkInState + ", sVipState=" + sVipState + ", userNet=" 
//				+ userNet + ", userJ=" + userJ +", isFinsh=" + isFinsh 
//				+", userID=" + userID +", error=" + error +"]";
//	}
//	
	
}
