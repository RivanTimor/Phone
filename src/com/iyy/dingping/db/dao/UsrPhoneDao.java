package com.iyy.dingping.db.dao;

import java.sql.SQLException;

import com.iyy.dingping.entity.UsrPhone;
import com.j256.ormlite.dao.Dao;

public class UsrPhoneDao {
		
	private Dao<UsrPhone, Integer> dao;
	
	public UsrPhoneDao(Dao<UsrPhone, Integer> dao){
		this.dao = dao;
	}
	/** 删除用户信息
	 * @param newResult
	 */
	public Boolean deleteAll()
	{
		try {
			dao.delete(dao.deleteBuilder().prepare());
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	/** 新增用户信息
	 * @param newResult
	 */
	public Boolean save(UsrPhone newTarget)
	{
		try {
			dao.create(newTarget);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/** 更新用户信息
	 * @param newResult
	 */
	public Boolean update(UsrPhone newTarget)
	{
		try {
			dao.update(newTarget);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	/** 删除用户信息
	 * @param newResult
	 */
	public Boolean delete(UsrPhone newTarget)
	{
		try {
			dao.delete(newTarget);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
}