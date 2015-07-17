package com.umell.admin.jazzy.viewpager;

import com.umell.admin.enums.ViewPagerDirection;

import android.support.v4.view.ViewPager.OnPageChangeListener;

public class OnPageChangeWithDirection implements OnPageChangeListener {
	protected int currentPosition;
	protected ViewPagerDirection direction;
	protected boolean isEnd;
	protected boolean isScrolling;
	private int lastValue;

	public void onPageScrollStateChanged(int paramInt) {
		if (paramInt == 1) {
			this.isScrolling = true;
			this.isEnd = false;
		} else if (paramInt == 2) {
			isScrolling = false;
			isEnd = true;
		} else {
			isScrolling = false;
		}
		direction = ViewPagerDirection.NULL;
	}

	public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2) {
		if (this.isScrolling) {
			if (this.lastValue < paramInt2) {// 页面向左
				this.direction = ViewPagerDirection.LEFT;
			} else if (lastValue > paramInt2) {// 页面向右
				direction = ViewPagerDirection.RIGHT;
			} else if (lastValue == paramInt2) {
				direction = ViewPagerDirection.NULL;
			}

		}

		lastValue = paramInt2;
	}

	public void onPageSelected(int paramInt) {
		this.currentPosition = paramInt;
	}
}
