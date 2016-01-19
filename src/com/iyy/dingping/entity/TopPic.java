package com.iyy.dingping.entity;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="toppic")
public class TopPic extends BmobObject implements Serializable{
	private static final long serialVersionUID = 1L;
	public TopPic(){

	}

	public TopPic(long picID,String picURL,String reserve,String picQuery){
		this.picID = picID;
		this.picURL = picURL;
		this.reserve = reserve;
		this.picQuery = picQuery;
	}

	@DatabaseField(generatedId=true)
	private long picID;

	@DatabaseField
	private String picURL;

	@DatabaseField
	private String reserve;

	@DatabaseField
	private String picQuery;

	public String getPic() {
		return picQuery;
	}

	public void setPic(String pic) {
		this.picQuery = pic;
	}

	public long getPicID() {
		return picID;
	}

	public void setPicID(long picID) {
		this.picID = picID;
	}

	public String getPicURL() {
		return picURL;
	}

	public void setPicURL(String picURL) {
		this.picURL = picURL;
	}

	public String getReserve() {
		return reserve;
	}

	public void setReserve(String reserve) {
		this.reserve = reserve;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
