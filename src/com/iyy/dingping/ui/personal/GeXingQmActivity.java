package com.iyy.dingping.ui.personal;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.iyy.dingping.BaseActivity;
import com.iyy.dingping.R;
import com.iyy.dingping.bll.BLLUsrCache;
import com.iyy.dingping.utils.AppInfoUtil;
import com.iyy.dingping.utils.BaseUtil;
import com.iyy.dingping.utils.CustomDialog;
import com.iyy.dingping.utils.DateUtil;
import com.iyy.dingping.utils.DoNumberUtil;
import com.iyy.dingping.utils.constant.BaseConstant;
import com.iyy.dingping.utils.constant.ConfigConstant;
import com.iyy.dingping.utils.widget.CircleImageView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class GeXingQmActivity extends BaseActivity implements OnClickListener{

	@ViewInject(R.id.info_photo)
	private CircleImageView info_photo;

	@ViewInject(R.id.userphonenum)
	private TextView userphonenum;

	@ViewInject(R.id.my_location)
	private RelativeLayout my_location;

	@ViewInject(R.id.location_str)
	private TextView location_str;

	@ViewInject(R.id.ly_usrname)
	private LinearLayout ly_usrname;

	@ViewInject(R.id.username)
	private TextView username;

	@ViewInject(R.id.imageView2)
	private ImageView imageView2;

	@ViewInject(R.id.userqm)
	private TextView userqm;

	@ViewInject(R.id.ly_gxqm)
	private LinearLayout ly_gxqm;
	
	@ViewInject(R.id.et_com)
	private EditText et_com;
	
	@ViewInject(R.id.tv_com)
	private TextView tv_com;
	
	@ViewInject(R.id.tv_version)
	private TextView tv_version;
	
	@ViewInject(R.id.tv_new_version_pr)
	private TextView tv_new_version_pr;
	
	@ViewInject(R.id.tv_red_version)
	private TextView tv_red_version;
	
	

	private CustomDialog dialog;
	private File temp;
	private Bitmap photo;
	private String phoneNum;
	private String cityName;
	private ToggleButton tBtn;
	private String result = "";

	private BLLUsrCache bllUsrCache = null;

	private File file = new File(Environment.getExternalStorageDirectory(),DateUtil.getFormatDate("yyyy-MM-dd HH:mm:ss", new Date())+".jpg");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView2Base(R.layout.info_set);
		this.setTitle(R.string.info_set);
		this.setToolBarLeftButtonByString(R.string.head_return);
		this.setToolBarRightButtonByString0(R.string.save);
		ViewUtils.inject(this);

		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		tBtn = (ToggleButton) findViewById(R.id.c1_toggle_weather);
		bllUsrCache = new BLLUsrCache(mContext);
		temp = new File(Environment.getExternalStorageDirectory()+ "/photo.jpg");
		my_location.setOnClickListener(this);
		info_photo.setOnClickListener(this);
		ly_usrname.setOnClickListener(this);
		ly_gxqm.setOnClickListener(this);
	}


	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		setName();
		setGxqm();
		setLocation();
		setWeather();
		setCom();
		setIsNewVersion();
		phoneNum = bllUsrCache.getUsrCacheValue(userSysID, BaseConstant.USER_PHONE);
		userphonenum.setText(phoneNum);

	}

	public void setName(){
		String userName = bllUsrCache.getUsrCacheValue(userSysID, BaseConstant.USER_NAME);
		if(!BaseUtil.isSpace(userName)){
			username.setText(userName);
			ly_usrname.setClickable(false);
			imageView2.setVisibility(View.GONE);
		}else{
			username.setText("快去输入你的姓名吧！");
			ly_usrname.setClickable(true);
			imageView2.setVisibility(View.VISIBLE);
		}
	}

	public void setGxqm(){
		String gxqm = bllUsrCache.getUsrCacheValue(userSysID, BaseConstant.GXQM);
		if(!BaseUtil.isSpace(gxqm)){
			userqm.setText(gxqm);
		}else{
			userqm.setText("写写心情，让给我们笑笑呗！");
		}
	}

	public void setLocation(){
		String location= bllUsrCache.getUsrCacheValue(userSysID, BaseConstant.LOCATION_WORK);
		if(!BaseUtil.isSpace(location)){
			location_str.setText(location);
		}else{
			location_str.setText("来自未知的火星");
		}
	}

	public void setWeather(){
		result = bllUsrCache.getUsrCacheValue(userSysID, BaseConstant.REMIND_STEP_TOGGLE);
		if(BaseUtil.isSpace(result)){
			bllUsrCache.editUsrCache(userSysID, BaseConstant.REMIND_STEP_TOGGLE, "0");
			result = "0";
		}

		tBtn.setChecked(result.equals("0"));
		tBtn.setOnCheckedChangeListener(checkChange);
	}
	
	public void setCom(){
		String comname = bllUsrCache.getUsrCacheValue(userSysID, BaseConstant.COMNAME);
		if(!BaseUtil.isSpace(comname)){
			tv_com.setVisibility(View.VISIBLE);
			tv_com.setText(comname);
			et_com.setVisibility(View.GONE);
		}else{
			tv_com.setVisibility(View.GONE);
			et_com.setVisibility(View.VISIBLE);
			et_com.setHint("在哪高就啊？");
		}
	}
	
	public void setIsNewVersion(){
		String versionNum = sysConfig.getCustomConfig(ConfigConstant.VERSIONNUM,"1");
		if(DoNumberUtil.intNullDowith(versionNum)>AppInfoUtil.getAppVersionCode(mContext)){
			tv_version.setVisibility(View.GONE);
			tv_new_version_pr.setVisibility(View.VISIBLE);
			tv_red_version.setVisibility(View.VISIBLE);
		}else{
			tv_version.setText("当前版本"+AppInfoUtil.getAppVersionName(mContext));
			tv_new_version_pr.setVisibility(View.GONE);
			tv_red_version.setVisibility(View.GONE);
		}
	}
	
	//开关
	OnCheckedChangeListener checkChange = new OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			result = isChecked ? "0" : "1";
			bllUsrCache.editUsrCache(userSysID, BaseConstant.REMIND_STEP_TOGGLE, result);
		}
	};

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.info_photo:
			showPickDialog();
			break;
		case R.id.my_location:
			startActivityForResult(new Intent(mContext,ActivitySelectCity.class), 99);
			break;
		case R.id.ly_usrname:
			startActivity(new Intent(mContext, ChangeNameActivity.class));
		case R.id.ly_gxqm:
			startActivity(new Intent(mContext, GeXingQianMing.class));
		default:
			break;
		}
	}

	/**
	 * 点击头像按钮弹出提示框的方法
	 */
	private void showPickDialog() {

		CustomDialog.Builder customBuilder = new
				CustomDialog.Builder(mContext);
		customBuilder.setTitle("提示")
		.setMessage("请选择照片的来源？")
		.setNegativeButton("相册", 
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				Intent intent = new Intent(Intent.ACTION_PICK, null);//新建一个意图
				intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
				startActivityForResult(intent, 1);
			}
		})
		.setPositiveButton("拍照", 
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(),"photo.jpg")));
				startActivityForResult(intent, 2);
			}
		});
		dialog = customBuilder.create();
		dialog.show();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (resultCode) {
		case 99:

			cityName = data.getStringExtra("lngCityName");
			location_str.setText(cityName);
			bllUsrCache.editUsrCache(userSysID, BaseConstant.LOCATION_WORK, cityName);
		}	
		// TODO Auto-generated method stub
		switch (requestCode) {
		case 1:
			if(null != data){
				startPhotoZoom(data.getData());
			}
			break;
		case 2:
			try {
				long i = getFileSize(temp);
				if(i != 0){
					startPhotoZoom(Uri.fromFile(temp));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 3:
			if(data != null){
				setPicToView(data);
			}
			temp.delete();
			break;
		default:
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	//获取文件大小
	private static long getFileSize(File file) throws Exception {
		long size = 0;
		if (file.exists()) {
			FileInputStream fis = null;
			fis = new FileInputStream(file);
			size = fis.available();
		} else {
			file.createNewFile();
		}
		return size;
	}
	/**
	 * 保存裁剪之后的图片数据
	 * @param picdata
	 */
	private void setPicToView(Intent picdata) {
		Bundle extras = picdata.getExtras();
		if (extras != null) {
			photo = extras.getParcelable("data");
			info_photo.setImageBitmap(photo);
		}
	}


	/**
	 * 对照片的剪裁
	 * @param uri
	 */
	public void startPhotoZoom(Uri uri) {

		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");

		//下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
		intent.putExtra("crop", "true");
		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY 是裁剪图片宽高
		intent.putExtra("outputX", 120);
		intent.putExtra("outputY", 120);
		intent.putExtra("return-data", true);
		//这个地方将图片设置成了phone2
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
		startActivityForResult(intent, 3);
	}


	@Override
	public void rightButtonClick() {
		// TODO Auto-generated method stub
		super.rightButtonClick();

	}
}
