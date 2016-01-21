package com.iyy.dingping.entity;

import cn.bmob.v3.BmobObject;

public class UserName extends BmobObject{
	
	private String name;
	private String phoneNum;
	private String gxqm;
	

	public String getGxqm() {
		return gxqm;
	}

	public void setGxqm(String gxqm) {
		this.gxqm = gxqm;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
