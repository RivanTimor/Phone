package com.iyy.dingping.ui.personal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import cn.bmob.v3.listener.UpdateListener;

import com.iyy.dingping.BaseActivity;
import com.iyy.dingping.R;
import com.iyy.dingping.bll.BLLUsrCache;
import com.iyy.dingping.entity.UserName;
import com.iyy.dingping.utils.DoNumberUtil;
import com.iyy.dingping.utils.constant.BaseConstant;

public class GeXingQianMing extends BaseActivity implements OnClickListener{
	
	private Button bt_save;
	
	private EditText et_gxqm;
	
	private String gxqm;
	private BLLUsrCache bllUsrCache = null;
	
	
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1000:
				bllUsrCache.editUsrCache(userSysID, BaseConstant.GXQM,gxqm);
				finish();
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
		setContentView2Base(R.layout.gexing_qianming);
		this.setTitle(R.string.gexing_qianming);
		this.setToolBarLeftButtonByString(R.string.head_return);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		bt_save = (Button) findViewById(R.id.bt_save);
		bt_save.setOnClickListener(this);
		et_gxqm = (EditText) findViewById(R.id.et_gxqm);
		bllUsrCache = new BLLUsrCache(mContext);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bt_save:
			saveQm();
			break;

		default:
			break;
		}
	}
	
	
	public void saveQm(){
		gxqm = et_gxqm.getText().toString().trim();
		UserName userName = new UserName();
		userName.setGxqm(gxqm);
		String objectId = bllUsrCache.getUsrCacheValue(userSysID, BaseConstant.OBJECTID);
		userName.update(mContext,objectId, new UpdateListener() {
			
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				Message message = handler.obtainMessage();
				message.what = 1000;
				handler.sendMessage(message);
			}
			
			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
				Message message = handler.obtainMessage();
				message.what = 2000;
				handler.sendMessage(message);
			}
		});
	}
}
