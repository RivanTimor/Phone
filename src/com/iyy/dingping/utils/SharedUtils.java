package com.iyy.dingping.utils;

import android.content.Context;
import android.content.SharedPreferences.Editor;

/**
 * ʵ�ֱ�ǵ�д��Ԥ��ȡ
 * @author wuyue
 *
 */
public class SharedUtils {
	private static final String FILE_NAME = "DianPing";
	private static final String MODE_NAME = "welcome";
	private static final String TOKEN = "token";
	//��ȡboolean��ֵ
	public static boolean getWelcomeBoolean(Context context){
		return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).getBoolean(MODE_NAME, false);
	}
	//д��boolean��ֵ
	public static void putWelcomeBoolean(Context context,boolean isFirst){
		Editor editor = context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE).edit();
		editor.putBoolean(MODE_NAME, isFirst);
		editor.commit();
	}
	
	//д�뱾�ζ�λ�ĳ�������
	public static void putCityName(Context context,String token){
		Editor editor = context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE).edit();
		editor.putString("TOKEN", token);
		editor.commit();
	}
	//�õ��ϴ��˳�ʱ�ĳ�������
	public static String getCityName(Context context){
		return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).getString("cityName", "ѡ�����");
	}
	
	
}
