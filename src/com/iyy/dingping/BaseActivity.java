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
 * �����Ĵ���
 * ��ͷ����
 * �½�������Լ̳д���,��ȡͷ��
 * ���ʹ�ô˼̳�Activity,ʹ��  setContentView2Base  ����Լ��Ĳ���
 * @author wuyue
 *
 */
public class BaseActivity extends Activity{

	protected Context mContext ;
	protected LayoutInflater mInflater ;
	protected FrameLayout mainLayout ;  //��������Ĳ���
	protected LinearLayout l_left;			//��߲���
	protected Button b_left ;     	   		//��߰�ť
	protected TextView tv_left ;     	   		//�������
	protected ImageButton b_left2;	
	protected TextView b_left3;//���ͼƬ��ť
	protected LinearLayout l_right;			//�ұ߲���
	protected Button b_right ;     	   		//�ұ߰�ť
	protected Button b_right3 ;     	   		//�ұ߰�ť
	protected TextView tv_right ;     	   		//�ұ�����
	protected ImageButton b_right2;			//�ұ�ͼƬ��ť
	protected TextView tv_head_logo ;    
	protected TextView titleText ;	   //����
	protected SysConfig sysConfig;
	protected int userSysID = 0;    
	protected String sex = "1";
	protected LinearLayout head_head;
	protected RelativeLayout bar;
	//protected int eDay = 0;

	public ImageLoader imageLoader = ImageLoader.getInstance();

	public DisplayImageOptions options= new DisplayImageOptions.Builder()
	.showImageOnLoading(R.drawable.page_moren)			// ����ͼƬ�����ڼ���ʾ��ͼƬ
	.showImageForEmptyUri(R.drawable.page_moren)	// ����ͼƬUriΪ�ջ��Ǵ����ʱ����ʾ��ͼƬ
	//.considerExifParams(true)    //�Ƿ���JPEGͼ��EXIF��������ת����ת��
	.bitmapConfig(Bitmap.Config.RGB_565)
	.imageScaleType(ImageScaleType.EXACTLY)
	.showImageOnFail(R.drawable.page_moren)		// ����ͼƬ���ػ��������з���������ʾ��ͼƬ	
	.cacheInMemory(true)						// �������ص�ͼƬ�Ƿ񻺴����ڴ���
	.cacheOnDisc(true)							// �������ص�ͼƬ�Ƿ񻺴���SD����
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
		 * ��ʱ����
		 */
		userSysID = sysConfig.getUserID_();
		sex = sysConfig.getUserSex();
		//eDay = DoNumberUtil.intNullDowith(sysConfig.getExpiredDay());
		/*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			Window window = getWindow();
			// ���ö���͸��
			window.setFlags(
					WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
					WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

			//ͷ���߶�����Ӧ��ռ�ռ䣩
			LayoutParams lp = (LayoutParams) head_head.getLayoutParams();
			lp.height = getStatusBarHeight();
			head_head.setLayoutParams(lp);

			head_head.setVisibility(View.VISIBLE);

			// ����״̬���Ĺ���ʵ��
			SystemBarTintManager tintManager = new SystemBarTintManager(this);
			// ����״̬������
			tintManager.setStatusBarTintEnabled(true);
			// �����������
			tintManager.setNavigationBarTintEnabled(true);
			// ����һ����ɫ��ϵͳ��
			tintManager.setTintColor(Color.parseColor("#0E9C80"));
		}*/
	}

	//��ť���
	private View.OnClickListener btnClickLeft = new OnClickListener() {  

		@Override
		public void onClick(View v) {
			leftButtonClick();
		}
	};
	//��ť���
	private View.OnClickListener btnClickRight = new OnClickListener() {  

		@Override
		public void onClick(View v) {
			rightButtonClick();
		}
	};

	/**
	 * ����Լ��Ĳ����ļ�
	 * @param �����ļ�ID
	 * @return �ò���VIEW
	 */
	public View setContentView2Base(int layoutID){
		View contentView = mInflater.inflate(layoutID, null);
		mainLayout.addView(contentView);
		return contentView;

	}

	/**
	 * ����Լ��Ĳ����ļ�
	 * @param contentView  �Զ���VIEW
	 */
	public void setContentView2Base(View contentView){
		mainLayout.addView(contentView);
	}

	/**
	 * ���ñ�������
	 * @param color ��ɫ
	 */
	public void setBackGroundColor(int color){
		mainLayout.setBackgroundColor(color);
	}

	/**
	 * ���ñ�������
	 * @param layoutID �����ļ�ID
	 */
	public void setBackGroundDrawable(int drawableID){
		mainLayout.setBackgroundResource(drawableID);
	}
	/**
	 * ���ñ�������
	 * @param layoutID �����ļ�ID
	 */
	public void setBackGroundDrawable(Drawable d){
		mainLayout.setBackgroundDrawable(d);
	}
	/*** ���ñ���
	 * @param title 
	 */
	public void setTitle(String title){
		titleText.setText(title);
	}

	/***  ���ñ���
	 * @param stringID ��Դid
	 * */
	@Override
	public void setTitle(int stringID){
		titleText.setText(stringID);
	}
	/**
	 * �������Ҳఴť
	 * @param strID
	 */
	//��ͼ�� ������
	public void setToolBarLeftButtonByString(int strID) {
		setToolBarButtonString(strID, 0);
	}
	public void setToolBarRightButtonByString(int strID) {
		setToolBarButtonString(strID, 1);
	}
	//ֻ������
	public void setToolBarLeftButtonByString0(int strID) {
		setToolBarButtonString(strID, 5);
	}
	public void setToolBarRightButtonByString0(int strID) {
		setToolBarButtonString(strID, 6);
	}
	//������ ��ͼ��
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
	//��ͼ��
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
	 * ���õ�һ���ұ߰�ť�ı���
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
			// ����һ����ť
			l_left.setVisibility(View.VISIBLE);
			b_left.setVisibility(View.VISIBLE);
			tv_left.setVisibility(View.VISIBLE);
			tv_left.setText(strID);
			break;
		case 1:
			// �Ҳ��һ����ť
			l_right.setVisibility(View.VISIBLE);
			b_right.setVisibility(View.VISIBLE);
			tv_right.setVisibility(View.VISIBLE);
			tv_right.setText(strID);
			break;
		case 3:
			// ����һ����ť
			l_left.setVisibility(View.VISIBLE);
			b_left.setVisibility(View.VISIBLE);
			tv_left.setVisibility(View.VISIBLE);
			tv_left.setText(strID);
			break;
		case 4:
			// �Ҳ��һ����ť
			l_right.setVisibility(View.VISIBLE);
			b_right3.setVisibility(View.VISIBLE);
			tv_right.setVisibility(View.VISIBLE);
			tv_right.setText(strID);
			break;
		case 5:
			// ����һ����ť
			l_left.setVisibility(View.VISIBLE);
			b_left.setVisibility(View.GONE);
			tv_left.setVisibility(View.VISIBLE);
			tv_left.setText(strID);
			break;
		case 6:
			// �Ҳ��һ����ť
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
			// ����һ����ť
			l_left.setVisibility(View.VISIBLE);
			b_left.setVisibility(View.VISIBLE);
			b_left.setBackgroundResource(strID);
			break;
		case 1:
			// �Ҳ��һ����ť
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
	 * ȥ���ұ߼�ͷ
	 */
	public void setToolBarRightArrowGone(){
		b_right.setVisibility(View.GONE);
	}


	/**���ذ�ť���,ͨ��Ϊ�رոô���**/
	public void leftButtonClick(){
		this.finish();
	}

	/**ȷ����ť���**/
	public void rightButtonClick(){
		//do nothing......
	}

	//���ر�����
	protected void hideHeadBar(){
		bar.setVisibility(View.GONE);
	}
	//��ʾ������
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
	 * ҳ����ת
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
