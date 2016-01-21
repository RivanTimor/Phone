package com.iyy.dingping.fragment;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.iyy.dingping.R;
import com.iyy.dingping.bll.BLLTopPic;
import com.iyy.dingping.entity.TopPic;
import com.iyy.dingping.ui.personal.GeXingQmActivity;
import com.iyy.dingping.utils.BaseUtil;
import com.iyy.dingping.utils.CustomDialog;
import com.iyy.dingping.utils.DateUtil;
import com.iyy.dingping.utils.JazzyViewPager;
import com.iyy.dingping.utils.JazzyViewPager.TransitionEffect;
import com.iyy.dingping.utils.OutlineContainer;
import com.iyy.dingping.utils.constant.ConfigConstant;
import com.nostra13.universalimageloader.core.ImageLoader;

public class FragmentMy extends Fragment {

	private static final String TAG = "FragmentMy";
	private static int PAGER_START_PLAY = 0x123;
	//切换间隔时间3秒
	private static final int PLAY_TIME = 3 * 1000;
	private static final ListAdapter MyAdapter = null;
	//实现viewpager的控件
	private JazzyViewPager mViewPaper;
	//圆形标签的父层

	private RelativeLayout ry_photo;
	
	private ListView lv_information;
	private LinearLayout symbolContainer;
	private ImageView[] images;
	private ImageView[] circleSymbols;
	private ArrayList<String> imageUrlList;
	//图片框架universalimageloader的图形帮助类
	private ImageLoader mImageLoader;
	private Handler mHandler;
	private Context mContext;
	
	private BLLTopPic bllTopPic = null;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.my, null);
		mContext = getActivity();
		return view;
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		mImageLoader = ImageLoader.getInstance();
		bllTopPic = new BLLTopPic(mContext);
		ry_photo = (RelativeLayout) getView().findViewById(R.id.ry_photo);
		ry_photo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(mContext, GeXingQmActivity.class));
			}
		});
		initTopPic();
	}

	private void initTopPic() {
		// TODO Auto-generated method stub
		initMockImages();
		setupMyHandler();
		initViews();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	/**
	 * 创建本地图片数据
	 */
	private void initMockImages() {

		List<TopPic> pics = bllTopPic.getTopPicList();
		imageUrlList = new ArrayList<String>();
		if(!BaseUtil.isSpace(pics)){
			for(int i =0;i<pics.size();i++){
				String picdis = pics.get(i).getPicURL().substring(pics.get(i).getPicURL().indexOf("\"")+1, pics.get(i).getPicURL().indexOf("jpg")+3);
				imageUrlList.add(picdis);
			}
		}else{
			if(isAdded()){
				imageUrlList.add("drawable://" + R.drawable.jd_ad_0);
				imageUrlList.add("drawable://" + R.drawable.jd_ad_1);
				imageUrlList.add("drawable://" + R.drawable.jd_ad_2);
				imageUrlList.add("drawable://" + R.drawable.jd_ad_3);
				imageUrlList.add("drawable://" + R.drawable.jd_ad_4);
			}
		}
	}

	private void setupMyHandler() {
		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (PAGER_START_PLAY == msg.what) {
					if(isVisible()){
						Log.d(TAG, "----PAGER_START_PLAY-----");
						int current = mViewPaper.getCurrentItem();
						if (current == images.length - 1) {
							current = -1;
						}

						Log.d(TAG, "play item = " + current);
						mViewPaper.setCurrentItem(current + 1);

						mHandler.sendEmptyMessageDelayed(PAGER_START_PLAY,
								PLAY_TIME);
					}else{
					}
				}
			}
		};
	}

	private void initViews() {
		symbolContainer = (LinearLayout) getView().findViewById(R.id.symblo_container);
		circleSymbols = new ImageView[imageUrlList.size()];
		images = new ImageView[imageUrlList.size()];
		for (int i = 0; i < imageUrlList.size(); i++) {
			ImageView imageView = new ImageView(mContext);
			ImageView circle = new ImageView(mContext);
			imageView.setScaleType(ScaleType.CENTER_CROP);
			images[i] = imageView;
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);  
			lp.setMargins(3, 0, 3,0);
			circle.setLayoutParams(lp);
			circle.setTag(i);
			circle.setBackgroundDrawable(getResources().getDrawable(R.drawable.circle_normal));
			circleSymbols[i] = circle;
			symbolContainer.addView(circleSymbols[i]);
		}
		setViewPager(TransitionEffect.Standard);
	}


	private void setViewPager(TransitionEffect effect) {
		mViewPaper = (JazzyViewPager) getView().findViewById(R.id.topviewpager);
		mViewPaper.setTransitionEffect(effect);
		mViewPaper.setAdapter(new MyPagerAdapter());
		mViewPaper.setOnPageChangeListener(new MyPageViewChangeListener());
		mViewPaper.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (imageUrlList.size() == 0 || imageUrlList.size() == 1) {
					return true;
				} else {
					return false;
				}
			}
		});
		circleSymbols[0].setBackgroundDrawable(getResources().getDrawable(R.drawable.circle_selected));
		mViewPaper.setCurrentItem(0);

		mHandler.sendEmptyMessageDelayed(PAGER_START_PLAY, PLAY_TIME);
	}

	private class MyPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return imageUrlList.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object obj) {
			if (view instanceof OutlineContainer) {
				return ((OutlineContainer) view).getChildAt(0) == obj;
			} else {
				return view == obj;
			}
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			((ViewPager) container).removeView(mViewPaper
					.findViewFromObject(position));
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			mImageLoader.displayImage(imageUrlList.get(position),
					images[position]);
			container.addView(images[position], LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT);
			final int index = position;
			images[position].setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Log.e(TAG, "you clicked images position is" + index);
					Toast.makeText(getActivity(), "你点击了第"+(index+1)+"张图", Toast.LENGTH_SHORT).show();
				}
			});
			// 注意！不加这个方法要报IllegalStateException
			mViewPaper.setObjectForPosition(images[position], position);
			return images[position];
		}

	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

	}

	private class MyPageViewChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int stateCode) {
			switch (stateCode) {
			case 0:
				// 你什么都没动
				break;
			case 1:
				// 正在滑动哦
				break;
			case 2:
				// 滑动完了
				break;
			}
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		@Override
		public void onPageSelected(int position) {
			Log.d(TAG, "onPageSelected-->position:" + position);
			setSymbolImages(position);
		}

	}

	/**
	 * 设置圆形标签的状态
	 * @param index 当前标签的位置
	 */
	private void setSymbolImages(int index){
		for(ImageView image:circleSymbols){
			Integer i = (Integer) image.getTag();
			if(i==index){
				image.setBackgroundDrawable(getResources().getDrawable(R.drawable.circle_selected));
			}else{
				image.setBackgroundDrawable(getResources().getDrawable(R.drawable.circle_normal));
			}
		}
	}
}
