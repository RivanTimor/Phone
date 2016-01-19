package com.iyy.dingping;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iyy.dingping.utils.SysConfig;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 基础的窗体
 * 带头部栏
 * 新建窗体可以继承此类,获取头部
 * 如果使用此继承Activity,使用  setContentView2Base  添加自己的布局
 * @author wuyue
 *
 */
public class BaseFragment extends Fragment{
	
	protected Context mContext ;
	protected LayoutInflater mInflater ;
	protected FrameLayout mainLayout ;  //整个窗体的布局
	protected LinearLayout l_left;			//左边布局、
	protected LinearLayout l_left2;			//左边布局
	protected Button b_left ;     	   		//左边按钮
	protected TextView tv_left ;     	   		//左边文字
	protected ImageButton b_left2;			//左边图片按钮
	protected LinearLayout l_right;			//右边布局
	protected LinearLayout l_right2;			//右边布局
	protected Button b_right ;     	   		//右边按钮
	protected Button b_right3 ;     	   		//右边按钮
	protected TextView tv_right ;     	   		//右边文字
	protected ImageButton b_right2;			//右边图片按钮
	
	protected TextView titleText ;	   //标题
	protected SysConfig sysConfig;
	protected int userSysID = 0;    
	protected String sex = "1";
	
	public ImageLoader imageLoader = ImageLoader.getInstance();
	
	/*public DisplayImageOptions options= new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.page_moren)			// 设置图片下载期间显示的图片
		.showImageForEmptyUri(R.drawable.page_moren)	// 设置图片Uri为空或是错误的时候显示的图片
		.considerExifParams(true)    //是否考虑JPEG图像EXIF参数（旋转，翻转）
		.bitmapConfig(Bitmap.Config.RGB_565)
		.imageScaleType(ImageScaleType.EXACTLY)
		.showImageOnFail(R.drawable.page_moren)		// 设置图片加载或解码过程中发生错误显示的图片	
		.cacheInMemory(true)						// 设置下载的图片是否缓存在内存中
		.cacheOnDisc(true)							// 设置下载的图片是否缓存在SD卡中
		.build();*/
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (container == null) {
			return null;
		}
		View view = inflater.inflate(R.layout.base, container, false);
		return view;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		sysConfig = SysConfig.getConfig(getActivity());
		mContext = getActivity();
		mInflater = LayoutInflater.from(mContext);
		mainLayout = (FrameLayout) getView().findViewById(R.id.main_layout); 
		l_left = (LinearLayout) getView().findViewById(R.id.l_left);
		b_left = (Button) getView().findViewById(R.id.b_left);
		tv_left = (TextView) getView().findViewById(R.id.tv_left);
		b_left2 = (ImageButton) getView().findViewById(R.id.b_left2);
		titleText = (TextView) getView().findViewById(R.id.tv_head_title);
		l_right = (LinearLayout) getView().findViewById(R.id.l_right);
		b_right = (Button) getView().findViewById(R.id.b_right);
		b_right3 = (Button) getView().findViewById(R.id.b_right3);
		tv_right = (TextView) getView().findViewById(R.id.tv_right);
		b_right2 = (ImageButton) getView().findViewById(R.id.b_right2);
		
		l_left.setVisibility(View.GONE);
		l_left2.setVisibility(View.GONE);
		b_left.setVisibility(View.GONE);
		tv_left.setVisibility(View.GONE);
		b_left2.setVisibility(View.GONE);
		l_right.setVisibility(View.GONE);
		l_right2.setVisibility(View.GONE);
		b_right.setVisibility(View.GONE);
		b_right3.setVisibility(View.GONE);
		tv_right.setVisibility(View.GONE);
		b_right2.setVisibility(View.GONE);
		
		l_left.setOnClickListener(btnClickLeft);
		b_left.setOnClickListener(btnClickLeft);
		tv_left.setOnClickListener(btnClickLeft);
		b_left2.setOnClickListener(btnClickLeft);
		l_left2.setOnClickListener(btnClickLeft);
		
		l_right.setOnClickListener(btnClickRight);
		b_right.setOnClickListener(btnClickRight);
		b_right3.setOnClickListener(btnClickRight);
		tv_right.setOnClickListener(btnClickRight);
		b_right2.setOnClickListener(btnClickRight);
		l_right2.setOnClickListener(btnClickRight);
		
		/**
		 * 临时处理
		 */
		userSysID = sysConfig.getUserID_();
		sex = sysConfig.getUserSex();
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
	
	
	public void setToolBarRightButton2(int drawableID) {
		l_right2.setVisibility(View.VISIBLE);
		b_right2.setVisibility(View.VISIBLE);
		b_right2.setImageResource(drawableID);
	}
	public void setToolBarLeftButton2(int drawableID) {
		b_left2.setVisibility(View.VISIBLE);
		l_left2.setVisibility(View.VISIBLE);
		b_left2.setImageResource(drawableID);
	}
	//左图标
	public void setToolBarLeftButton2() {
		b_left2.setVisibility(View.VISIBLE);
		l_left2.setVisibility(View.VISIBLE);
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
	}
	
	/**确定按钮点击**/
	public void rightButtonClick(){
		//do nothing......
	}
	
	//隐藏标题栏
	protected void hideHeadBar(){
		getView().findViewById(R.id.head_bar).setVisibility(View.GONE);
	}
	//显示标题栏
	protected void showHeadBar(){
		getView().findViewById(R.id.head_bar).setVisibility(View.VISIBLE);
	}
}
