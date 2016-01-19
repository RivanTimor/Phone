package com.iyy.dingping.utils;

import android.content.Context;
import android.content.SharedPreferences.Editor;

/**
 * 实现标记的写入预读取
 * @author wuyue
 *
 */
public class SharedUtils {
	private static final String FILE_NAME = "DianPing";
	private static final String MODE_NAME = "welcome";
	private static final String TOKEN = "token";
	//获取boolean的值
	public static boolean getWelcomeBoolean(Context context){
		return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).getBoolean(MODE_NAME, false);
	}
	//写入boolean的值
	public static void putWelcomeBoolean(Context context,boolean isFirst){
		Editor editor = context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE).edit();
		editor.putBoolean(MODE_NAME, isFirst);
		editor.commit();
	}
	
	//写入本次定位的城市名称
	public static void putCityName(Context context,String token){
		Editor editor = context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE).edit();
		editor.putString("TOKEN", token);
		editor.commit();
	}
	//拿到上次退出时的城市名称
	public static String getCityName(Context context){
		return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).getString("cityName", "选择城市");
	}
	
	
}
