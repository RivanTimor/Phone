package com.iyy.dingping.entity;

import java.io.Serializable;
import java.util.Date;

import cn.bmob.v3.BmobObject;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="usr_cache")
public class UsrCache extends BmobObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public UsrCache() {
		
	}

	public UsrCache(long id, int userSysID, String key, String value,
			Date updateDate) {
		super();
		this.id = id;
		this.userSysID = userSysID;
		this.key = key;
		this.value = value;
		this.updateDate = updateDate;
	}

	@DatabaseField(generatedId=true)
	long id; //主键
	
	@DatabaseField
	int userSysID; //用户ID
	
	@DatabaseField
	String key; //关键字
	
	@DatabaseField
	String value; //值

	@DatabaseField
	Date updateDate; //更新时间
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getUserSysID() {
		return userSysID;
	}

	public void setUserSysID(int userSysID) {
		this.userSysID = userSysID;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	
}
