package com.iyy.dingping.ui.personal;

import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.EditText;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

import com.iyy.dingping.BaseActivity;
import com.iyy.dingping.R;
import com.iyy.dingping.bll.BLLUsrCache;
import com.iyy.dingping.entity.UserName;
import com.iyy.dingping.utils.constant.BaseConstant;
import com.lidroid.xutils.util.LogUtils;

public class ChangeNameActivity extends BaseActivity{


	private EditText et_name;
	private String name;
	private BLLUsrCache bllUsrCache = null;

	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1000:
				Toast.makeText(mContext, "校验成功", 1).show();
				bllUsrCache.editUsrCache(userSysID, BaseConstant.USER_NAME,name);
				save();
				finish();
				break;

			case 2000:
				Toast.makeText(mContext, "名字这东西，好好给我填撒！", 1).show();
				break;

			default:
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView2Base(R.layout.change_name);
		this.setTitle(R.string.change_name);
		this.setToolBarLeftButtonByString(R.string.head_return);
		this.setToolBarRightButtonByString0(R.string.check_name);
		et_name = (EditText) findViewById(R.id.et_change_name);
		bllUsrCache = new BLLUsrCache(mContext);
	}


	@Override
	public void rightButtonClick() {
		// TODO Auto-generated method stub
		name = et_name.getText().toString().trim();
		LogUtils.e(name);
		checkName(name);
	}

	public void checkName(String name){
		BmobQuery<UserName> query = new BmobQuery<UserName>();
		query.addWhereEqualTo("name", name);
		query.findObjects(mContext, new FindListener<UserName>() {

			@Override
			public void onSuccess(List<UserName> arg0) {
				// TODO Auto-generated method stub
				Message message = handler.obtainMessage();
				if(arg0.size() > 0){
					message.what = 1000;
					bllUsrCache.editUsrCache(userSysID, BaseConstant.OBJECTID, arg0.get(0).getObjectId());
					sysConfig.setUserID(arg0.get(0).getObjectId());
				}else{
					message.what = 2000;
				}
				handler.sendMessage(message);
			}

			@Override
			public void onError(int arg0, String arg1) {
				// TODO Auto-generated method stub
				Message message = handler.obtainMessage();
				message.what = 2000;
				handler.sendMessage(message);
			}
		});
	}
	
	public void save(){
		String phoneString = bllUsrCache.getUsrCacheValue(userSysID, BaseConstant.USER_PHONE);
		UserName userName = new UserName();
		userName.setPhoneNum(phoneString);
		String objectId = bllUsrCache.getUsrCacheValue(userSysID, BaseConstant.OBJECTID);
		userName.update(mContext, objectId, new UpdateListener() {
			
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}
		} );
	}
}
