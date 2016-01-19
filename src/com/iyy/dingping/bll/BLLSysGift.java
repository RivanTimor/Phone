package com.iyy.dingping.bll;

import com.iyy.dingping.db.DataHelper;
import com.iyy.dingping.db.dao.UsrPhoneDao;
import com.iyy.dingping.entity.UsrPhone;
import com.j256.ormlite.dao.Dao;

import android.content.Context;

public class BLLSysGift {
	private Context context;
	private UsrPhoneDao usrPhoneDao;
	
	public BLLSysGift(Context context){
		this.context = context;
		Dao<UsrPhone, Integer> dao = DataHelper.getDataHelper(context).getSysGiftDao();
		this.usrPhoneDao = new UsrPhoneDao(dao);
	}
}