package com.iyy.dingping.db.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.iyy.dingping.entity.UsrCache;
import com.iyy.dingping.utils.BaseUtil;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

public class UsrCacheDao {
	
	private Dao<UsrCache, Integer> dao;
	
	public UsrCacheDao(Dao<UsrCache, Integer> dao) {
		this.dao = dao;
	}
	
	/** 新增用户缓存信息
	 * @param ucPo
	 */
	public Boolean save(UsrCache ucPo)
	{
		try {
			dao.create(ucPo);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
	/** 编辑用户缓存信息
	 * @param ucPo
	 */
	public Boolean update(UsrCache ucPo)
	{
		try {
			dao.update(ucPo);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	/** 删除用户缓存信息
	 * @param ucPo
	 */
	public Boolean delete(UsrCache ucPo)
	{
		try {
			dao.delete(ucPo);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	/**
	 * 根据用户id和key 查找缓存信息
	 * @param userSysID
	 * @param key
	 * @return
	 */
	public UsrCache getUsrCache(int userSysID,String key)
	{
		List<UsrCache> ucList = new ArrayList<UsrCache>();
		
		try {
			QueryBuilder<UsrCache, Integer> queryBuilder = dao.queryBuilder();

			Where<UsrCache, Integer> where = queryBuilder.where();
			
			where.eq("userSysID", userSysID)
			.and().eq("key", key);
			
			PreparedQuery<UsrCache> preparedQuery = queryBuilder.prepare();
			ucList = dao.query(preparedQuery);
			if(!BaseUtil.isSpace(ucList)){
				return ucList.get(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据用户id查找缓存信息
	 * @param userSysID
	 * @param key
	 * @return
	 */
	public List<UsrCache> getUsrCache(int userSysID)
	{
		List<UsrCache> ucList = new ArrayList<UsrCache>();
		
		try {
			QueryBuilder<UsrCache, Integer> queryBuilder = dao.queryBuilder();

			Where<UsrCache, Integer> where = queryBuilder.where();
			
			where.eq("userSysID", userSysID);
			
			PreparedQuery<UsrCache> preparedQuery = queryBuilder.prepare();
			ucList = dao.query(preparedQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ucList;
	}
	/**
	 * 根据用户id查找缓存信息
	 * @param userSysID
	 * @param key
	 * @return
	 */
	public List<UsrCache> getUsrCache(int userSysID,Date synsDate)
	{
		List<UsrCache> ucList = new ArrayList<UsrCache>();
		
		try {
			QueryBuilder<UsrCache, Integer> queryBuilder = dao.queryBuilder();

			Where<UsrCache, Integer> where = queryBuilder.where();
			
			where.eq("userSysID", userSysID).and().gt("updateDate", synsDate);
			
			PreparedQuery<UsrCache> preparedQuery = queryBuilder.prepare();
			ucList = dao.query(preparedQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ucList;
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
}
