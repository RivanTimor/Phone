package com.iyy.dingping.bll;

import java.util.Date;
import java.util.List;

import android.content.Context;

import com.iyy.dingping.db.DataHelper;
import com.iyy.dingping.db.dao.UsrCacheDao;
import com.iyy.dingping.entity.UsrCache;
import com.iyy.dingping.utils.DateUtil;
import com.iyy.dingping.utils.DoNumberUtil;
import com.j256.ormlite.dao.Dao;

public class BLLUsrCache {
	
	private Context context;
	private UsrCacheDao usrCacheDao;
	
	public BLLUsrCache(Context context) {
		this.context = context;
		Dao<UsrCache, Integer> dao = DataHelper.getDataHelper(this.context).getUsrCacheDao();
		this.usrCacheDao = new UsrCacheDao(dao);
	}
	/**
	 * ɾ���û�������Ϣ
	 * @param userSysID
	 * @param key
	 */
	public void deleteUsrCache(int userSysID,String key){
		UsrCache ucPo = this.usrCacheDao.getUsrCache(userSysID, key);
		if(null!=ucPo){
			 this.usrCacheDao.delete(ucPo);
		}
	}
	
	/** �༭�û�������Ϣ
	 * @param newResult
	 */
	public void editUsrCache(int userSysID,String key,String value)
	{
		UsrCache ucPo = this.usrCacheDao.getUsrCache(userSysID, key);
		if(null!=ucPo){
			ucPo.setValue(value);
			ucPo.setUpdateDate(new Date());
			this.usrCacheDao.update(ucPo);
		}else{
			ucPo = new UsrCache();
			ucPo.setKey(key);
			ucPo.setUserSysID(userSysID);
			ucPo.setValue(value);
			ucPo.setUpdateDate(new Date());
			this.usrCacheDao.save(ucPo);
		}
	}
	/** �༭�û�������Ϣ
	 * @param newResult
	 */
	public void editUsrCacheAdd(int userSysID,String key)
	{
		UsrCache ucPo = this.usrCacheDao.getUsrCache(userSysID, key);
		if(null!=ucPo){
			ucPo.setValue(DoNumberUtil.IntToStr(DoNumberUtil.intNullDowith(ucPo.getValue())+1));
			ucPo.setUpdateDate(new Date());
			this.usrCacheDao.update(ucPo);
		}else{
			ucPo = new UsrCache();
			ucPo.setKey(key);
			ucPo.setUserSysID(userSysID);
			ucPo.setValue("1");
			ucPo.setUpdateDate(new Date());
			this.usrCacheDao.save(ucPo);
		}
	}
	/** �༭�û�������Ϣ
	 * @param newResult
	 */
	public void editUsrCacheSub(int userSysID,String key)
	{
		UsrCache ucPo = this.usrCacheDao.getUsrCache(userSysID, key);
		if(null!=ucPo){
			ucPo.setValue(DoNumberUtil.IntToStr(DoNumberUtil.intNullDowith(ucPo.getValue())-1));
			ucPo.setUpdateDate(new Date());
			this.usrCacheDao.update(ucPo);
		}else{
			ucPo = new UsrCache();
			ucPo.setKey(key);
			ucPo.setUserSysID(userSysID);
			ucPo.setValue("0");
			ucPo.setUpdateDate(new Date());
			this.usrCacheDao.save(ucPo);
		}
	}
	/** ����û�������Ϣ
	 * @param newResult
	 */
	public String getUsrCacheValue(int userSysID,String key)
	{
		UsrCache ucPo = this.usrCacheDao.getUsrCache(userSysID, key);
		if(null!=ucPo){
			return ucPo.getValue();
		}
		return null;
	}
	/** ����û�������Ϣ
	 * @param newResult
	 */
	public String getUsrCacheValue(int userSysID,String key,String moren)
	{
		UsrCache ucPo = this.usrCacheDao.getUsrCache(userSysID, key);
		if(null!=ucPo){
			return ucPo.getValue();
		}
		return moren;
	}
	/** ����û�������Ϣ
	 * @param newResult
	 */
	public UsrCache getUsrCache(int userSysID,String key)
	{
		UsrCache ucPo = this.usrCacheDao.getUsrCache(userSysID, key);
		return ucPo;
	}
	/** ����û�������Ϣ
	 * @param newResult
	 */
	public String getUsrCacheValue01(int userSysID,String key)
	{
		UsrCache ucPo = this.usrCacheDao.getUsrCache(userSysID, key);
		if(null!=ucPo){
			return ucPo.getValue();
		}
		return "0";
	}
	/**
	 * �����û�id���һ�����Ϣ
	 * @param userSysID
	 * @param key
	 * @return
	 */
	public List<UsrCache> getUsrCache(int userSysID)
	{
		return this.usrCacheDao.getUsrCache(userSysID);
	}
	/**
	 * �����û�id���һ�����Ϣ
	 * @param userSysID
	 * @param key
	 * @return
	 */
	public List<UsrCache> getUsrCache(int userSysID,Date synsDate)
	{
		if(null==synsDate){
			synsDate = DateUtil.dateAdd(1, -10, new Date());
		}
		return this.usrCacheDao.getUsrCache(userSysID,synsDate);
	}
}
