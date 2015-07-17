package com.umell.admin.base;

import java.util.List;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.umell.admin.inter.OnJazzyViewPagerItemSelectedListener;
import com.umell.admin.jazzy.viewpager.JazzyViewPager;
import com.umell.admin.jazzy.viewpager.OutlineContainer;

public abstract class BaseViewPagerActivity extends BaseActivity implements
		OnJazzyViewPagerItemSelectedListener {
	protected JazzyViewPager mJazzyViewPager;
	protected List<View> mViews;

	private void initViewPagerAdapter() {
		this.mJazzyViewPager.setAdapter(new MyViewPagerAdapter(this.mViews));
	}

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		initViewPagerAdapter();
	}

	public void onItemSelected(int paramInt) {
		if (this.mJazzyViewPager.getAdapter().getCount() > paramInt)
			this.mJazzyViewPager.setCurrentItem(paramInt);
	}

	private class MyViewPagerAdapter extends PagerAdapter {
		private List<View> views;

		public MyViewPagerAdapter(List<View> views) {
			this.views = views;
		}

		public void destroyItem(ViewGroup paramViewGroup, int paramInt,
				Object paramObject) {
			paramViewGroup
					.removeView(BaseViewPagerActivity.this.mJazzyViewPager
							.findViewFromObject(paramInt));
		}

		public int getCount() {
			return this.views.size();
		}

		public Object instantiateItem(ViewGroup paramViewGroup, int paramInt) {
			View localView = (View) this.views.get(paramInt);
			paramViewGroup.addView(localView, 0);
			BaseViewPagerActivity.this.mJazzyViewPager.setObjectForPosition(
					localView, paramInt);
			return localView;
		}

		public boolean isViewFromObject(View paramView, Object paramObject) {
			if ((paramView instanceof OutlineContainer)) {
				return ((OutlineContainer) paramView).getChildAt(0) == paramObject;
			} else {
				return paramView == paramObject;
			}
		}
	}
}
