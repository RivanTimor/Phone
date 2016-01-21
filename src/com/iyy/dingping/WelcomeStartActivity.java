package com.iyy.dingping;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.GetListener;

import com.iyy.dingping.entity.Version;
import com.iyy.dingping.login.LoginActivity;
import com.iyy.dingping.utils.BaseUtil;
import com.iyy.dingping.utils.SharedUtils;
import com.iyy.dingping.utils.SysConfig;
import com.iyy.dingping.utils.constant.ConfigConstant;

/**
 * ʵ�ֻ�ӭ������ӳ���ת�����ַ�����
 * 1��ʹ��hander������hander������Ϣ���յ���Ϣ��ʼ����
 * 2��ʹ��Java�����Timer��ʱ����Utils���µ�timer.schedule��дһ���ڲ��࣬Task�����ڲ���ʵ��һ���߳�
 * @author wuyue
 *
 */
public class WelcomeStartActivity extends Activity{
	
	private SysConfig sysConfig;
	private static final String VERSIONID = "PR2u777H";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		sysConfig = SysConfig.getConfig(this);
// 1.��һ�ַ�ʽ
//		new Handler(new Handler.Callback() {
//		//�����յ�����Ϣ����
//			@Override
//			public boolean handleMessage(Message msg) {
//				// TODO Auto-generated method stub
//				startActivity(new Intent(getApplicationContext(), MainActivity.class));
//				return false;
//			}
//		}).sendEmptyMessageDelayed(0, 3000);
		
// 2���ڶ��ַ�ʽ		
		//������ʹ�ö�ʱ������
		Timer timer = new Timer();
		timer.schedule(new Task(), 3000);
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		BmobQuery<Version> query = new BmobQuery<Version>();
		query.getObject(getApplicationContext(), VERSIONID, new GetListener<Version>() {
			
			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onSuccess(Version arg0) {
				// TODO Auto-generated method stub
				sysConfig.setCustomConfig(ConfigConstant.VERSIONNUM, arg0.getVersionNum());
			}
		});
	}
	
	class Task extends TimerTask{
		@Override
		public void run() {
			DisplayMetrics metric = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(metric);
			sysConfig.setScreenWidth(metric.widthPixels);
			
			if(SharedUtils.getWelcomeBoolean(getBaseContext())){
				if(null == BmobUser.getCurrentUser(getApplicationContext())){
					startActivity(new Intent(getBaseContext(), LoginActivity.class));	
				}else{
					startActivity(new Intent(getBaseContext(), MainActivity.class));	
				}
			}else{
				startActivity(new Intent(getApplicationContext(), WelcomeGuideActivity.class));	
				SharedUtils.putWelcomeBoolean(getBaseContext(), true);
			}
			finish();
		}
	}
}
