package com.iyy.dingping;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;
import cn.bmob.v3.Bmob;

import com.iyy.dingping.fragment.FragmentHome;
import com.iyy.dingping.fragment.FragmentMy;
import com.iyy.dingping.fragment.FragmentSerach;
import com.iyy.dingping.utils.constant.ConfigConstant;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class MainActivity extends FragmentActivity implements OnCheckedChangeListener{

	@ViewInject(R.id.mian_bottom_tabs)
	private RadioGroup group;
	@ViewInject(R.id.main_home)
	private RadioButton button;
	private FragmentManager manager;
	private long exitTime = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ViewUtils.inject(this);
		Bmob.initialize(this, ConfigConstant.BMOB_PID);
		manager = getSupportFragmentManager();
		button.setChecked(true);
		group.setOnCheckedChangeListener(this);
		changeFragment(new FragmentHome(), false);
	}
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		switch (checkedId) {
		case R.id.main_home:
			changeFragment(new FragmentHome(), true);
			break;
		case R.id.main_search:
			changeFragment(new FragmentSerach(),true);
			break;
		case R.id.main_my:
			changeFragment(new FragmentMy(), true);
			break;
		default:
			break;
		}
	}
	
	public void changeFragment(Fragment fragment,boolean isInit){
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.replace(R.id.main_content, fragment);
		if(!isInit){
			transaction.addToBackStack(null);
		}
		transaction.commit();
	}
	
	@Override  
	public boolean onKeyDown(int keyCode, KeyEvent event)  
	{  
		if (keyCode == KeyEvent.KEYCODE_BACK )  
		{  
			 exit();
		}  
		return false;  
	}  
	
	public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }
}
