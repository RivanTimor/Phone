package com.iyy.dingping.db.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.iyy.dingping.entity.TopPic;
import com.iyy.dingping.utils.BaseUtil;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
/**
 * 直接面向底层的数据库操作。其中可以使用orm封装好的，也可以使用我们自己的sql语句，进行相关的操作
 * @author wuyue
 *
 */
public class TopPicDao {

	private Dao<TopPic, Integer> dao;

	public TopPicDao(Dao<TopPic, Integer> dao){
		this.dao = dao;
	}

	/** 新增用户信息
	 * @param newResult
	 */
	public Boolean save(TopPic newTarget)
	{
		try {
			System.out.println(newTarget);
			dao.create(newTarget);
			return true;
		} catch (Exception e) {
			System.out.println("catch");
			e.printStackTrace();
			return false;
		}
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
	/** 更新用户信息
	 * @param newResult
	 */
	public Boolean update(TopPic newTarget)
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
	public Boolean delete(TopPic newTarget)
	{
		try {
			dao.delete(newTarget);
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
	public List<TopPic> getTopPicList()
	{
		List<TopPic> ucList = new ArrayList<TopPic>();
		
		try {
			QueryBuilder<TopPic, Integer> queryBuilder = dao.queryBuilder();
			Where<TopPic, Integer> where = queryBuilder.where();
			where.eq("picQuery", "111");
			PreparedQuery<TopPic> preparedQuery = queryBuilder.prepare();
			ucList = dao.query(preparedQuery);
			if(!BaseUtil.isSpace(ucList)){
				return ucList;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}