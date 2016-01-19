package com.iyy.dingping;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import cn.bmob.v3.BmobUser;

import com.iyy.dingping.login.LoginActivity;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class WelcomeGuideActivity extends Activity{
	@ViewInject(R.id.but_welcome_guide)
	private Button btn;
	@ViewInject(R.id.welcome_viewpager)
	private ViewPager pager;
	private List<View> list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome_guide);
		ViewUtils.inject(this);
		initViewPager();
	}
	
	@OnClick(R.id.but_welcome_guide)
	public void cilck(View view){
		if(null == BmobUser.getCurrentUser(getApplicationContext())){
			startActivity(new Intent(getBaseContext(), LoginActivity.class));	
		}else{
			startActivity(new Intent(getBaseContext(), MainActivity.class));	
		}
		finish();
	}
	
	
	//初始化ViewPager的方法
	
	public void initViewPager(){
		list = new ArrayList<View>();
		ImageView imageView = new ImageView(this);
		imageView.setImageResource(R.drawable.guide_01);
		list.add(imageView);
		ImageView imageView1 = new ImageView(this);
		imageView1.setImageResource(R.drawable.guide_02);
		list.add(imageView1);
		ImageView imageView2 = new ImageView(this);
		imageView2.setImageResource(R.drawable.guide_03);
		list.add(imageView2);
		pager.setAdapter(new MypagerAdapter());
		//监听viewPager滑动
		pager.setOnPageChangeListener(new OnPageChangeListener() {
			
			
			//叶卡被选中的时候
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				if(arg0 == 2){
					btn.setVisibility(View.VISIBLE);
				}else{
					btn.setVisibility(View.GONE);
				}
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	//Apater适配器
	class MypagerAdapter extends PagerAdapter{
		//计算需要多少个item
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			container.addView(list.get(position));
			return list.get(position);
		}
		
		//销毁叶卡
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			container.removeView(list.get(position));
		}
	}
}
