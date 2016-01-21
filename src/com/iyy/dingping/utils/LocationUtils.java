package com.iyy.dingping.utils;

import android.content.Context;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.iyy.dingping.utils.constant.ConfigConstant;
import com.lidroid.xutils.util.LogUtils;

public class LocationUtils {
	
	private LocationClient locationClient = null;
	private SysConfig sysConfig;
	private Context mContext;
	
	public LocationUtils(Context context ){
		sysConfig = SysConfig.getConfig(context);
		this.mContext = context;
	}
	
	public void Location(){
		try{
			MyLocationListenner myListener = new MyLocationListenner();
			locationClient = new LocationClient(mContext); 
			locationClient.registerLocationListener(myListener);
			LocationClientOption option = new LocationClientOption();
			option.setOpenGps(true);
			option.setAddrType("all");
			option.setCoorType("bd09ll");
			option.setScanSpan(5000);
			option.disableCache(true);
			option.setPoiNumber(5); 
			option.setPoiDistance(1000); 
			option.setPoiExtraInfo(true); 
			option.setPriority(LocationClientOption.GpsFirst);
			locationClient.setLocOption(option);
			locationClient.start();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 百度获取定位服务
	 * @author wuyue
	 *
	 */
	private class MyLocationListenner implements BDLocationListener {
		@Override
		public void onReceiveLocation(BDLocation location) {

			if (location == null){
				return;
			}
			StringBuffer sb = new StringBuffer(256);
			if (location.getLocType() == BDLocation.TypeGpsLocation) {
				sb.append(location.getCity());
			} else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
				sb.append(location.getCity());
			}
			if (sb.toString() != null && sb.toString().length() > 0) {
				sysConfig.setCustomConfig(ConfigConstant.LOCATION, sb.toString());
			}else{
			}
		}

		public void onReceivePoi(BDLocation poiLocation) {

		}
	}
	
	public void destroy(){
		locationClient.stop();
	}
}
