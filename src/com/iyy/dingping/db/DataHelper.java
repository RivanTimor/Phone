package com.iyy.dingping.db;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.iyy.dingping.entity.TopPic;
import com.iyy.dingping.entity.UsrPhone;
import com.iyy.dingping.utils.BYFileUtil;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DataHelper extends OrmLiteSqliteOpenHelper {

	public static final String DATABASE_NAME = "ClassRom11.db";
	private static final int DATABASE_VERSION = 1;

	private static DataHelper staticDB;
	private Context context;

	@SuppressWarnings("rawtypes")
	Map<String, Dao> daoMaps = null; // 所有DAO的集合

	public DataHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context=context;
		initDaoMaps();		
	}

	public static DataHelper getDataHelper(Context context) {
		if (staticDB == null) {
			staticDB = new DataHelper(context);
		}
		return staticDB;
	}

	public static SQLiteDatabase getDB()
	{
		return staticDB.getWritableDatabase();
	}

	@SuppressWarnings("rawtypes")
	private void initDaoMaps() {
		daoMaps = new HashMap<String, Dao>();
		daoMaps.put("usrPhone", null);   //List信息表
		daoMaps.put("topPic", null);   //List信息表
	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, UsrPhone.class);
			TableUtils.createTable(connectionSource, TopPic.class);
			// 建表成功后，插入初始数据
			initData(db);

		} catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
			int oldVersion, int newVersion) {
	}

	/**
	 * 插入初始数据
	 * 
	 */
	private void initData(SQLiteDatabase db) {

	}
	
	private void executeSQLFile(SQLiteDatabase db,int resID)
	{

		String strSQL=BYFileUtil.getFileText(context, resID);
		try{
			String [] strSQLArray=strSQL.split("\\n");
			if (strSQLArray!=null && strSQLArray.length>0) {
				for (int i = 0; i < strSQLArray.length; i++) {
					db.execSQL(strSQLArray[i]);
				}
			}
		} catch (Exception e){
		}
	}

	@Override
	public void close() {
		super.close();
		daoMaps.clear();
	}

	
	@SuppressWarnings("unchecked")
	public Dao<UsrPhone , Integer> getSysGiftDao() {
		Dao<UsrPhone , Integer> accountDao = daoMaps.get("usrPhone"); 
		if (accountDao == null) {
			try {
				accountDao = getDao(UsrPhone .class);
			} catch (SQLException e) {
			}
		}
		return accountDao;
	}
	
	public Dao<TopPic , Integer> getTopPicDao() {
		Dao<TopPic , Integer> accountDao = daoMaps.get("topPic"); 
		if (accountDao == null) {
			try {
				accountDao = getDao(TopPic .class);
			} catch (SQLException e) {
			}
		}
		return accountDao;
	}
	
}
