package com.umell.admin;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;

import com.umell.admin.base.BaseViewPagerActivity;
import com.umell.admin.jazzy.viewpager.JazzyViewPager;
import com.umell.admin.jazzy.viewpager.OnPageChangeWithDirection;
import com.umell.admin.util.ToastUtil;
import com.umell.admin.view.TabMenuView;
import com.umell.admin.view.TitleBar;

public class MainActivity extends BaseViewPagerActivity {
	private int[] draws;
	private long firstTime;
	private String[] mMenuNames;

	private TabMenuView mTabMenuView;
	private TitleBar mTitleBar;

	private void init() {
		this.mTabMenuView = ((TabMenuView) findViewById(R.id.main_tab_menu));
		this.mTitleBar = ((TitleBar) findViewById(R.id.title_bar));
		this.mMenuNames = getResources().getStringArray(R.array.main_menu_item);
		initDrawables(this.mMenuNames.length);
		for (int i = 0;; i++) {
			if (i >= this.mMenuNames.length) {
				this.mTitleBar.getTitle().setText(this.mMenuNames[0]);
				this.mTabMenuView.selectItem(0);
				this.mTitleBar.getBackButton().setVisibility(View.INVISIBLE);
				this.mTabMenuView.setOnTabMenuViewItemSelectedListener(this);
				return;
			}
			this.mTabMenuView.initItemTopDrawable(i, this.draws[i], this.mMenuNames[i]);
		}
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
				ToastUtil.showToast(this, "���ٰ�һ���˳�����");
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
}
