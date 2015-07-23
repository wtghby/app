package com.umell.admin;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;

import com.google.zxing.client.android.CaptureActivity;
import com.google.zxing.client.android.Intents;
import com.umell.admin.base.BaseViewPagerActivity;
import com.umell.admin.jazzy.viewpager.JazzyViewPager;
import com.umell.admin.jazzy.viewpager.OnPageChangeWithDirection;
import com.umell.admin.util.ToastUtil;
import com.umell.admin.view.MenuItemView;
import com.umell.admin.view.TabMenuView;
import com.umell.admin.view.TitleBar;

public class MainActivity extends BaseViewPagerActivity {
	private int[] draws;
	private long firstTime;
	private String[] mMenuNames;

	private TabMenuView mTabMenuView;
	private TitleBar mTitleBar;

	private MenuItemView consumeQrcode;
	
	private void init() {
		this.mTabMenuView = ((TabMenuView) findViewById(R.id.main_tab_menu));
		this.mTitleBar = ((TitleBar) findViewById(R.id.title_bar));
		this.mMenuNames = getResources().getStringArray(R.array.main_menu_item);
		initDrawables(this.mMenuNames.length);
		for (int i = 0;i<mMenuNames.length; i++) {
			this.mTabMenuView.initItemTopDrawable(i, this.draws[i], this.mMenuNames[i]);
			
		}
		this.mTitleBar.getTitle().setText(this.mMenuNames[0]);
		this.mTabMenuView.selectItem(0);
		this.mTitleBar.getBackButton().setVisibility(View.INVISIBLE);
		this.mTabMenuView.setOnTabMenuViewItemSelectedListener(this);
		
		initListener();
	}

	private void initListener() {
		OnClickListener click = new OnClick();
		consumeQrcode = (MenuItemView) mViews.get(0).findViewById(R.id.consume_qrcode);
		consumeQrcode.setOnClickListener(click);
		
	}
	
	private void initDrawables(int paramInt) {
		this.draws = new int[paramInt];
		this.draws[0] = R.drawable.main_menu_consume_selector;
		this.draws[1] = R.drawable.main_menu_customer_selector;
		this.draws[2] = R.drawable.main_menu_setting_selector;
	}

	protected void initViewPager() {

		this.mJazzyViewPager = (JazzyViewPager) findViewById(R.id.view_pager);
		this.mJazzyViewPager.setOnPageChangeListener(new OnPageChange());
	}

	@SuppressLint({ "InflateParams" })
	protected void initViews() {
		LayoutInflater localLayoutInflater = LayoutInflater.from(this);

		mViews = new ArrayList<View>();

		this.mViews.add(localLayoutInflater.inflate(R.layout.main_consume_layout, null));
		this.mViews.add(localLayoutInflater.inflate(R.layout.main_customer_layout, null));
		this.mViews.add(localLayoutInflater.inflate(R.layout.main_setting_layout, null));
	
	}

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		init();
	}

	public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
		if ((paramInt == KeyEvent.KEYCODE_BACK) && (paramKeyEvent.getAction() == KeyEvent.ACTION_DOWN)) {
			if (System.currentTimeMillis() - this.firstTime < 2000L) {
				finish();
				System.exit(0);
			} else {
				firstTime = System.currentTimeMillis();
				ToastUtil.showToast(this, "请再按一次退出系统");
			}
			return true;
		}
		return super.onKeyDown(paramInt, paramKeyEvent);
	}

	protected void setLayout() {
		setContentView(R.layout.main_layout);
	}

	private class OnPageChange extends OnPageChangeWithDirection {
		private OnPageChange() {
		}

		public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2) {
			super.onPageScrolled(paramInt1, paramFloat, paramInt2);
			if (this.isScrolling)
				MainActivity.this.mTabMenuView.scrollItem(paramInt1, paramFloat, this.direction);
			if ((!this.isScrolling) && (this.isEnd) && (this.currentPosition == paramInt1))
				MainActivity.this.mTabMenuView.selectItem(paramInt1);
		}

		public void onPageSelected(int paramInt) {
			super.onPageSelected(paramInt);
			MainActivity.this.mTitleBar.getTitle().setText(MainActivity.this.mMenuNames[paramInt]);
			MainActivity.this.mTabMenuView.selectItem(paramInt);
		}
	}
	
	private class OnClick implements OnClickListener{

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			
			case R.id.consume_qrcode:
				
				Intent consumeQr = new Intent();
				consumeQr.setAction(Intents.Scan.ACTION);
				consumeQr.putExtra(Intents.Scan.CHARACTER_SET, "UTF-8");
				consumeQr.putExtra(Intents.Scan.WIDTH, 800);
				consumeQr.putExtra(Intents.Scan.HEIGHT, 600);
				consumeQr.setClass(MainActivity.this, CaptureActivity.class);
		        startActivity(consumeQr);
		        
				break;

			default:
				break;
			}
			
		}
		
	}
}
