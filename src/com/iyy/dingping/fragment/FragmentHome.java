package com.iyy.dingping.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.iyy.dingping.R;
import com.iyy.dingping.utils.LocationUtils;
import com.iyy.dingping.utils.SysConfig;
import com.iyy.dingping.utils.constant.ConfigConstant;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class FragmentHome extends Fragment{
	
	@ViewInject(R.id.tv_city)
	private TextView tv_city;
	@ViewInject(R.id.lv_phone_num)
	private ListView lv_phone_num;
	private LocationClient locationClient = null;
	private String CityName;
	private Context mContext;
	private LocationUtils locationUtils;
	private SysConfig sysConfig;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.home, null);
		ViewUtils.inject(this, view);
		mContext = this.getActivity();
		sysConfig = SysConfig.getConfig(mContext);
		initGps();
		initList();
		return view;
	}
	
	private void initList() {
		// TODO Auto-generated method stub
		
	}

	private void showData() {
		// TODO Auto-generated method stub
			
	}

	private Handler handler = new Handler(new Handler.Callback() {
		
		@Override
		public boolean handleMessage(Message msg) {
			// TODO Auto-generated method stub
			if(msg.what == 1){
			}
			return false;
		}
	});
	
	

	public void onStart() {
		super.onStart();
	}
	
	//初始化定位
	private void initGps() {
		locationUtils =  new LocationUtils(mContext);
		locationUtils.Location();
		tv_city.setText(sysConfig.getCustomConfig(ConfigConstant.LOCATION,""));
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		locationUtils.destroy();
	}
}
