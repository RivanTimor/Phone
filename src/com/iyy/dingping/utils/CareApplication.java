package com.iyy.dingping.utils;

import java.util.HashMap;
import java.util.Map;

import android.app.Application;
import android.content.Context;
import cn.bmob.push.BmobPush;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;

import com.iyy.dingping.utils.constant.ConfigConstant;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/***
 * 这是一个生命力顽强的全局对象,直到应用完全退出才会消失
 * @author jerry
 *
 */
public class CareApplication extends Application{

	public static final String OBJ_FRIST = "obj_frist";
	public static final String OBJ_SECORD = "obj_secord";

	private Map<String,Object> ObjectMap = new HashMap<String, Object>();


	public void onCreate() {

		super.onCreate();
//		CrashHandler crashHandler = CrashHandler.getInstance();  
//		crashHandler.init(getApplicationContext());  
		initImageLoader(getApplicationContext());
		
		Bmob.initialize(getApplicationContext(), ConfigConstant.BMOB_PID);
	}


	public static void initImageLoader(Context context) {
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
		.threadPriority(Thread.NORM_PRIORITY - 2)
		.denyCacheImageMultipleSizesInMemory()
		.diskCacheFileNameGenerator(new Md5FileNameGenerator())
		.diskCacheSize(50 * 1024 * 1024) // 50 Mb
		.tasksProcessingOrder(QueueProcessingType.LIFO)
		.writeDebugLogs() // Remove for release app
		.build();
		ImageLoader.getInstance().init(config);
	}

	public Object getObjFrist() {
		return ObjectMap.get(OBJ_FRIST);
	}

	public void setObjFrist(Object objFrist) {
		this.ObjectMap.put(OBJ_FRIST, objFrist);
	}

	public void removeObjFrist(){
		this.ObjectMap.remove(OBJ_FRIST);
	}

	public Object getObjSecond() {
		return ObjectMap.get(OBJ_SECORD);
	}

	public void setObjSecond(Object objSecond) {
		this.ObjectMap.put(OBJ_SECORD, objSecond);
	}

	public void removeObjSecond(){
		this.ObjectMap.remove(OBJ_SECORD);
	}

	public void clear(){
		this.ObjectMap.remove(OBJ_FRIST);
		this.ObjectMap.remove(OBJ_SECORD);
	}

}
