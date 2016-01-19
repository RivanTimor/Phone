package com.iyy.dingping.login;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.RequestSMSCodeListener;

import com.iyy.dingping.BaseActivity;
import com.iyy.dingping.MainActivity;
import com.iyy.dingping.R;
import com.iyy.dingping.entity.MyUser;
import com.iyy.dingping.entity.User;
import com.iyy.dingping.entity.UsrPhone;
import com.iyy.dingping.utils.BaseUtil;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class LoginActivity extends BaseActivity{

	@ViewInject(R.id.bt_login)
	private Button bt_login;

	@ViewInject(R.id.bt_next)
	private Button bt_next;

	@ViewInject(R.id.et_phone)
	private EditText et_phone;

	@ViewInject(R.id.tv_prompt)
	private TextView tv_prompt;

	@ViewInject(R.id.bt_getcode)
	private Button bt_getcode;

	@ViewInject(R.id.et_code)
	private EditText et_code;

	@ViewInject(R.id.ly_two)
	private LinearLayout ly_two;

	@ViewInject(R.id.ly_login_pic)
	private LinearLayout ly_login_pic;

	private String phoneNum;
	private String code;
	int timeNum = 60;
	private long exitTime = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView2Base(R.layout.login);
		ViewUtils.inject(this);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub


		LayoutParams l_menulp = (LayoutParams) ly_login_pic.getLayoutParams();
		l_menulp.height = (int) ((sysConfig.getScreenWidth()/480f) * 173f);
		ly_login_pic.setLayoutParams(l_menulp);

		bt_next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(checkPhoneNum()){
					et_phone.setVisibility(View.GONE);
					ly_two.setVisibility(View.VISIBLE);
					bt_next.setVisibility(View.GONE);
					bt_login.setVisibility(View.VISIBLE);
					setTextSend();
					sendSmsNew(phoneNum);
				}else{
					Toast.makeText(mContext, "请输入正确的手机号", Toast.LENGTH_LONG).show();
				}
			}
		});


		bt_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				code = et_code.getText().toString().trim();
				if(BaseUtil.isSpace(code)){
					Toast.makeText(mContext, "验证码不能为空！", Toast.LENGTH_LONG).show();
				}else{
					oneKeyLogin();
				}
			}
		});
		
		bt_getcode.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				sendSmsNew(phoneNum);
			}
		});
	}

	private boolean checkPhoneNum() {
		// TODO Auto-generated method stub
		phoneNum = et_phone.getText().toString().trim();
		if(isMobileNO(phoneNum)){
			return true;
		}else{
			return false;
		}
	}

	//正则表达式验证是否为手机号码
	public boolean isMobileNO(String mobiles) {
		Pattern p = Pattern.compile("^((17[0-9])|(13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	
	public void sendSmsNew(String phone){
		BmobSMS.requestSMSCode(mContext, phone,"Address", new RequestSMSCodeListener() {


			@Override
			public void done(Integer arg0, BmobException arg1) {
				// TODO Auto-generated method stub
				if(arg1==null){//验证码发送成功
		        	myHandler.sendEmptyMessageDelayed(30000, 0);
		        }
			}
		    });
	}

	/**
	 * 设置发送验证码按键的倒计时
	 */
	private void setTextSend(){
		timeNum = timeNum - 1;
		if(timeNum==0){
			timeNum = 60;
			bt_getcode.setEnabled(true);
			bt_getcode.setText("获取验证码");
		}else{
			bt_getcode.setEnabled(false);
			bt_getcode.setText(timeNum+"秒重发");
			myHandler.sendEmptyMessageDelayed(20000, 1000);
		}
	}

	/**
	 * 消息机制
	 */
	private Handler myHandler = new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 10000:
				break;
			case 20000:
				setTextSend();
				break;
			case 30000:
				Toast.makeText(mContext, "验证码已发送！", Toast.LENGTH_LONG).show();
				break;
			case 40000:
				
				break;
			case 50000:
				Toast.makeText(mContext, "验证码输入有误！", Toast.LENGTH_LONG).show();
				break;
			case 60000:
				startActivity(new Intent(mContext, MainActivity.class));
				finish();
				break;
			case 70000:
				Toast.makeText(mContext, "注册失败！", Toast.LENGTH_LONG).show();
				break;
			default:
				break;
			}
		};
	};
	
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
	
	/**
	 * 一键登录操作
	 * 
	 * @method login
	 * @return void
	 * @exception
	 */
	private void oneKeyLogin() {
		final String phone = et_phone.getText().toString();
		final String code = et_code.getText().toString();

		if (TextUtils.isEmpty(phone)) {
			Toast.makeText(mContext, "手机号码不能为空", Toast.LENGTH_LONG).show();
			return;
		}

		if (TextUtils.isEmpty(code)) {
			Toast.makeText(mContext, "验证码不能为空", Toast.LENGTH_LONG).show();
			return;
		}
		final ProgressDialog progress = new ProgressDialog(LoginActivity.this);
		progress.setMessage("正在验证短信验证码...");
		progress.setCanceledOnTouchOutside(false);
		progress.show();
		// V3.3.9提供的一键注册或登录方式，可传手机号码和验证码
		BmobUser.signOrLoginByMobilePhone(LoginActivity.this,phone, code, new LogInListener<User>() {

			@Override
			public void done(User user, BmobException ex) {
				// TODO Auto-generated method stub
				progress.dismiss();
				if(ex==null){
					Toast.makeText(mContext, "登录成功", Toast.LENGTH_LONG).show();
					Intent intent = new Intent(LoginActivity.this,MainActivity.class);
					intent.putExtra("from", "loginonekey");
					startActivity(intent);
					finish();
				}else{
					Toast.makeText(mContext, "登录失败：code="+ex.getErrorCode()+"，错误描述："+ex.getLocalizedMessage(), Toast.LENGTH_LONG).show();
				}
			}
		});
	}
}
