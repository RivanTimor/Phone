package com.iyy.dingping.entity;

import java.io.Serializable;
import java.util.Date;

import cn.bmob.v3.BmobObject;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="usr_phone")
public class UsrPhone extends BmobObject implements Serializable{
	public UsrPhone() {
		
	}
	
	public UsrPhone(int userSysID, String userRegID, String isVal,String location,String picPath,
			Date createDateTime,String name,String phoneNum) {
		super();
		this.userSysID = userSysID;
		this.userID = userRegID;
		this.isVal = isVal;
		this.createDateTime = createDateTime;
		this.location = location;
		this.picPath = picPath;
		this.name = name;
	}
	

	 @DatabaseField(id=true)
	 int userSysID; //系统ID
	 
	 @DatabaseField
	 String userID; //用户ID
	 
	 @DatabaseField
	 String name; //用户ID
	 
	 @DatabaseField
	 String phoneNum; //用户ID
	 
	 @DatabaseField
	 String isVal; //是否有效
	 
	 @DatabaseField
	 String picPath; //头像
	 
	 @DatabaseField
	 String location; //位置
	 
	 @DatabaseField
	 Date createDateTime; //创建时间

	public int getUserSysID() {
		return userSysID;
	}

	public void setUserSysID(int userSysID) {
		this.userSysID = userSysID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getIsVal() {
		return isVal;
	}

	public void setIsVal(String isVal) {
		this.isVal = isVal;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
}
