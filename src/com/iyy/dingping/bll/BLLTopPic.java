package com.iyy.dingping.bll;

import java.util.List;

import android.content.Context;

import com.iyy.dingping.db.DataHelper;
import com.iyy.dingping.db.dao.TopPicDao;
import com.iyy.dingping.entity.TopPic;
import com.j256.ormlite.dao.Dao;
/**
 * bll例子，在类中调用bll的相关方法，进行对数据库的相关操作
 * @author wuyue
 *
 */
public class BLLTopPic {

	private Context context;
	private TopPicDao topPicDao;

	public BLLTopPic(Context context){
		this.context = context;
		Dao<TopPic, Integer> dao = DataHelper.getDataHelper(this.context).getTopPicDao();
		this.topPicDao = new TopPicDao(dao);
	}


	public void saveTopPic(TopPic topPic){
		System.out.println("SavaBll"+topPic);
		topPicDao.save(topPic);
	}

	public void updataTopPic(TopPic topPic){
		System.out.println("update"+topPic);
		topPicDao.update(topPic);
	}

	public List<TopPic> getTopPicList(){
		return topPicDao.getTopPicList();
	}
	
	public void delete(){
		topPicDao.deleteAll();
	}
}