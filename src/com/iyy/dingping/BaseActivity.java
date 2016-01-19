package com.iyy.dingping;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iyy.dingping.utils.ActivityCollecor;
import com.iyy.dingping.utils.LogUtil;
import com.iyy.dingping.utils.SysConfig;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;


/**
 * 基础的窗体
 * 带头部栏
 * 新建窗体可以继承此类,获取头部
 * 如果使用此继承Activity,使用  setContentView2Base  添加自己的布局
 * @author wuyue
 *
 */
public class BaseActivity extends Activity{

	protected Context mContext ;
	protected LayoutInflater mInflater ;
	protected FrameLayout mainLayout ;  //整个窗体的布局
	protected LinearLayout l_left;			//左边布局
	protected Button b_left ;     	   		//左边按钮
	protected TextView tv_left ;     	   		//左边文字
	protected ImageButton b_left2;	
	protected TextView b_left3;//左边图片按钮
	protected LinearLayout l_right;			//右边布局
	protected Button b_right ;     	   		//右边按钮
	protected Button b_right3 ;     	   		//右边按钮
	protected TextView tv_right ;     	   		//右边文字
	protected ImageButton b_right2;			//右边图片按钮
	protected TextView tv_head_logo ;    
	protected TextView titleText ;	   //标题
	protected SysConfig sysConfig;
	protected int userSysID = 0;    
	protected String sex = "1";
	protected LinearLayout head_head;
	protected RelativeLayout bar;
	//protected int eDay = 0;

	public ImageLoader imageLoader = ImageLoader.getInstance();

	public DisplayImageOptions options= new DisplayImageOptions.Builder()
	.showImageOnLoading(R.drawable.page_moren)			// 设置图片下载期间显示的图片
	.showImageForEmptyUri(R.drawable.page_moren)	// 设置图片Uri为空或是错误的时候显示的图片
	//.considerExifParams(true)    //是否考虑JPEG图像EXIF参数（旋转，翻转）
	.bitmapConfig(Bitmap.Config.RGB_565)
	.imageScaleType(ImageScaleType.EXACTLY)
	.showImageOnFail(R.drawable.page_moren)		// 设置图片加载或解码过程中发生错误显示的图片	
	.cacheInMemory(true)						// 设置下载的图片是否缓存在内存中
	.cacheOnDisc(true)							// 设置下载的图片是否缓存在SD卡中
	.build();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.base);
		ActivityCollecor.addActivity(this);
		LogUtil.e("the Activity is:", getClass().getSimpleName());
		sysConfig = SysConfig.getConfig(this);
		mContext = this;
		mInflater = LayoutInflater.from(mContext);
		mainLayout = (FrameLayout) findViewById(R.id.main_layout); 
		l_left = (LinearLayout) findViewById(R.id.l_left);
		b_left = (Button) findViewById(R.id.b_left);
		tv_left = (TextView) findViewById(R.id.tv_left);
		b_left2 = (ImageButton) findViewById(R.id.b_left2);
		titleText = (TextView) findViewById(R.id.tv_head_title);
		l_right = (LinearLayout) findViewById(R.id.l_right);
		b_right = (Button) findViewById(R.id.b_right);
		b_right3 = (Button) findViewById(R.id.b_right3);
		tv_right = (TextView) findViewById(R.id.tv_right);
		b_right2 = (ImageButton) findViewById(R.id.b_right2);
		b_left3  = (TextView) findViewById(R.id.b_left3);
		tv_head_logo  = (TextView) findViewById(R.id.tv_head_logo);
		head_head =  (LinearLayout) findViewById(R.id.head_head);
		
		bar  =  (RelativeLayout) findViewById(R.id.head_bar);

		tv_head_logo.setVisibility(View.GONE);
		l_left.setVisibility(View.GONE);
		b_left.setVisibility(View.GONE);
		tv_left.setVisibility(View.GONE);
		b_left2.setVisibility(View.GONE);
		l_right.setVisibility(View.GONE);
		b_right.setVisibility(View.GONE);
		b_right3.setVisibility(View.GONE);
		tv_right.setVisibility(View.GONE);
		b_right2.setVisibility(View.GONE);

		l_left.setOnClickListener(btnClickLeft);
		b_left.setOnClickListener(btnClickLeft);
		tv_left.setOnClickListener(btnClickLeft);
		b_left2.setOnClickListener(btnClickLeft);

		l_right.setOnClickListener(btnClickRight);
		b_right.setOnClickListener(btnClickRight);
		b_right3.setOnClickListener(btnClickRight);
		tv_right.setOnClickListener(btnClickRight);
		b_right2.setOnClickListener(btnClickRight);

		/**
		 * 临时处理
		 */
		userSysID = sysConfig.getUserID_();
		sex = sysConfig.getUserSex();
		//eDay = DoNumberUtil.intNullDowith(sysConfig.getExpiredDay());
		/*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			Window window = getWindow();
			// 设置顶部透明
			window.setFlags(
					WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
					WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

			//头部高度自适应（占空间）
			LayoutParams lp = (LayoutParams) head_head.getLayoutParams();
			lp.height = getStatusBarHeight();
			head_head.setLayoutParams(lp);

			head_head.setVisibility(View.VISIBLE);

			// 创建状态栏的管理实例
			SystemBarTintManager tintManager = new SystemBarTintManager(this);
			// 激活状态栏设置
			tintManager.setStatusBarTintEnabled(true);
			// 激活导航栏设置
			tintManager.setNavigationBarTintEnabled(true);
			// 设置一个颜色给系统栏
			tintManager.setTintColor(Color.parseColor("#0E9C80"));
		}*/
	}

	//按钮点击
	private View.OnClickListener btnClickLeft = new OnClickListener() {  

		@Override
		public void onClick(View v) {
			leftButtonClick();
		}
	};
	//按钮点击
	private View.OnClickListener btnClickRight = new OnClickListener() {  

		@Override
		public void onClick(View v) {
			rightButtonClick();
		}
	};

	/**
	 * 添加自己的布局文件
	 * @param 布局文件ID
	 * @return 该布局VIEW
	 */
	public View setContentView2Base(int layoutID){
		View contentView = mInflater.inflate(layoutID, null);
		mainLayout.addView(contentView);
		return contentView;

	}

	/**
	 * 添加自己的布局文件
	 * @param contentView  自定义VIEW
	 */
	public void setContentView2Base(View contentView){
		mainLayout.addView(contentView);
	}

	/**
	 * 设置背景遮罩
	 * @param color 颜色
	 */
	public void setBackGroundColor(int color){
		mainLayout.setBackgroundColor(color);
	}

	/**
	 * 设置背景遮罩
	 * @param layoutID 布局文件ID
	 */
	public void setBackGroundDrawable(int drawableID){
		mainLayout.setBackgroundResource(drawableID);
	}
	/**
	 * 设置背景遮罩
	 * @param layoutID 布局文件ID
	 */
	public void setBackGroundDrawable(Drawable d){
		mainLayout.setBackgroundDrawable(d);
	}
	/*** 设置标题
	 * @param title 
	 */
	public void setTitle(String title){
		titleText.setText(title);
	}

	/***  设置标题
	 * @param stringID 资源id
	 * */
	@Override
	public void setTitle(int stringID){
		titleText.setText(stringID);
	}
	/**
	 * 设置左右侧按钮
	 * @param strID
	 */
	//左图标 右文字
	public void setToolBarLeftButtonByString(int strID) {
		setToolBarButtonString(strID, 0);
	}
	public void setToolBarRightButtonByString(int strID) {
		setToolBarButtonString(strID, 1);
	}
	//只有文字
	public void setToolBarLeftButtonByString0(int strID) {
		setToolBarButtonString(strID, 5);
	}
	public void setToolBarRightButtonByString0(int strID) {
		setToolBarButtonString(strID, 6);
	}
	//左文字 右图标
	public void setToolBarLeftButtonByString3(int strID) {
		setToolBarButtonString(strID, 3);
	}
	public void setToolBarRightButtonByString3(int strID) {
		setToolBarButtonString(strID, 4);
	}
	public void setToolBarLeftButton(int strID) {
		setToolBarButton(strID, 0);
	}
	public void setToolBarRightButton(int strID) {
		setToolBarButton(strID, 1);
	}
	//左图标
	public void setToolBarLeftButton2() {
		b_left2.setVisibility(View.VISIBLE);
	}

	public void setToolBarRightButton2(int drawableID) {
		b_right2.setVisibility(View.VISIBLE);
		b_right2.setImageResource(drawableID);
	}
	public void setToolBarLeftButton3() {
		b_left3.setVisibility(View.VISIBLE);
	}
	public void setToolBarLogo() {
		tv_head_logo.setVisibility(View.VISIBLE);
	}
	public void setToolBarLeftButton2(int drawableID) {
		b_left2.setVisibility(View.VISIBLE);
		b_left2.setImageResource(drawableID);
	}
	public void setToolBarRightButton1(int drawableID) {
		b_right.setBackgroundResource(drawableID);
	}
	public void setToolBarLeftButton1(int drawableID) {
		b_left.setBackgroundResource(drawableID);
	}
	/**
	 * 设置第一个右边按钮的背景
	 * @param color
	 */
	public void setToolBarRightButtonBgColor(int drawableID) {
		b_right2.setVisibility(View.GONE);
		l_right.setVisibility(View.VISIBLE);
		l_right.setBackgroundResource(drawableID);
	}


	private void setToolBarButtonString(int strID, int index) {
		switch (index) {
		case 0:
			// 左侧第一个按钮
			l_left.setVisibility(View.VISIBLE);
			b_left.setVisibility(View.VISIBLE);
			tv_left.setVisibility(View.VISIBLE);
			tv_left.setText(strID);
			break;
		case 1:
			// 右侧第一个按钮
			l_right.setVisibility(View.VISIBLE);
			b_right.setVisibility(View.VISIBLE);
			tv_right.setVisibility(View.VISIBLE);
			tv_right.setText(strID);
			break;
		case 3:
			// 左侧第一个按钮
			l_left.setVisibility(View.VISIBLE);
			b_left.setVisibility(View.VISIBLE);
			tv_left.setVisibility(View.VISIBLE);
			tv_left.setText(strID);
			break;
		case 4:
			// 右侧第一个按钮
			l_right.setVisibility(View.VISIBLE);
			b_right3.setVisibility(View.VISIBLE);
			tv_right.setVisibility(View.VISIBLE);
			tv_right.setText(strID);
			break;
		case 5:
			// 左侧第一个按钮
			l_left.setVisibility(View.VISIBLE);
			b_left.setVisibility(View.GONE);
			tv_left.setVisibility(View.VISIBLE);
			tv_left.setText(strID);
			break;
		case 6:
			// 右侧第一个按钮
			l_right.setVisibility(View.VISIBLE);
			b_right.setVisibility(View.GONE);
			tv_right.setVisibility(View.VISIBLE);
			tv_right.setText(strID);
			break;
		default:
			break;
		}
	}
	private void setToolBarButton(int strID, int index) {
		switch (index) {
		case 0:
			// 左侧第一个按钮
			l_left.setVisibility(View.VISIBLE);
			b_left.setVisibility(View.VISIBLE);
			b_left.setBackgroundResource(strID);
			break;
		case 1:
			// 右侧第一个按钮
			l_right.setVisibility(View.VISIBLE);
			b_right.setVisibility(View.VISIBLE);
			b_right.setBackgroundResource(strID);
			break;
		default:
			break;
		}
	}
	public void setToolBarLeftButtonGone() {
		l_left.setVisibility(View.GONE);

	}
	public void setToolBarRightButtonGone() {
		l_right.setVisibility(View.GONE);
	}
	/**
	 * 去掉右边箭头
	 */
	public void setToolBarRightArrowGone(){
		b_right.setVisibility(View.GONE);
	}


	/**返回按钮点击,通常为关闭该窗体**/
	public void leftButtonClick(){
		this.finish();
	}

	/**确定按钮点击**/
	public void rightButtonClick(){
		//do nothing......
	}

	//隐藏标题栏
	protected void hideHeadBar(){
		bar.setVisibility(View.GONE);
	}
	//显示标题栏
	protected void showHeadBar(){
		bar.setVisibility(View.VISIBLE);
	}

	protected void onResume() {
		super.onResume();
		//eDay = DoNumberUtil.intNullDowith(sysConfig.getExpiredDay());
	}

	protected void onPause() {
		super.onPause();
	}
	/**
	 * 页面跳转
	 */
	protected void close(){
		this.finish();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		ActivityCollecor.removeActivity(this);
	}

	public int getStatusBarHeight() {
		int result = 0;
		int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			result = getResources().getDimensionPixelSize(resourceId);
		}
		return result;
	}
}
