package com.iyy.dingping.entity;

import cn.bmob.v3.BmobUser;

public class MyUser extends BmobUser{
	private Boolean sex;
	private String nick;
	private Integer age;
	
	
	public Boolean getSex() {
		return sex;
	}
	public void setSex(Boolean sex) {
		this.sex = sex;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}

}
