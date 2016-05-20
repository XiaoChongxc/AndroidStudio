package com.example.administrator.mytest.TestForHttp.Bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;

/**
 * 默认获取数据格式
 * 
 * @author qsxiaogang
 */
public class CommonResultBean<T> extends CommonDataBean implements Parcelable
{
	private HashMap<String, T> data;

	@Override
	public int describeContents()
	{
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags)
	{
		dest.writeBooleanArray(new boolean[]
		{ success });
		dest.writeString(code);
		dest.writeString(text);
		dest.writeMap(data);
	}

	public static final Parcelable.Creator<CommonResultBean> CREATOR = new Parcelable.Creator<CommonResultBean>()
	{
		public CommonResultBean createFromParcel(Parcel in)
		{
			CommonResultBean loginBean = new CommonResultBean();
			boolean[] booleans = new boolean[1];
			in.readBooleanArray(booleans);
			loginBean.success = booleans[0];
			loginBean.code = in.readString();
			loginBean.text = in.readString();
			loginBean.data = in.readHashMap(HashMap.class.getClassLoader());
			return loginBean;
		}

		public CommonResultBean[] newArray(int size)
		{
			return new CommonResultBean[size];
		}
	};

	/**
	 * @return the data
	 */
	public HashMap<String, T> getData()
	{
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(HashMap<String, T> data)
	{
		this.data = data;
	}

}
