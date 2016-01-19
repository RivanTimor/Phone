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
 * ֱ������ײ�����ݿ���������п���ʹ��orm��װ�õģ�Ҳ����ʹ�������Լ���sql��䣬������صĲ���
 * @author wuyue
 *
 */
public class TopPicDao {

	private Dao<TopPic, Integer> dao;

	public TopPicDao(Dao<TopPic, Integer> dao){
		this.dao = dao;
	}

	/** �����û���Ϣ
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
	/** ɾ���û���Ϣ
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
	/** �����û���Ϣ
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
	/** ɾ���û���Ϣ
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
	 * �����û�id��key ���һ�����Ϣ
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