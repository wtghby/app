package com.umell.admin.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.umell.admin.R;
import java.util.ArrayList;
import java.util.List;

/**
 * 滑动时，小点选择提示的view
 * 
 * @author hui
 * 
 */
@SuppressLint({ "NewApi" })
public class DotMark extends LinearLayout {
	private List<ImageView> mImageViews;
	private int size;

	public DotMark(Context paramContext) {
		super(paramContext);
	}

	public DotMark(Context paramContext, AttributeSet paramAttributeSet) {
		this(paramContext, paramAttributeSet, 0);
	}

	public DotMark(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
		super(paramContext, paramAttributeSet, paramInt);
		TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.DotMark);
		this.size = localTypedArray.getInt(R.styleable.DotMark_size, 0);
		initViews();
		localTypedArray.recycle();
	}

	private void initViews() {
		if (this.size < 1)
			return;
		this.mImageViews = new ArrayList<ImageView>();

		for (int i = 0; i < size; i++) {
			LinearLayout localLinearLayout = new LinearLayout(getContext());
			localLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1.0F));
			if (getOrientation() == HORIZONTAL) {
				localLinearLayout.setOrientation(VERTICAL);
			}

			ImageView localImageView = new ImageView(getContext());
			LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(20, 20);
			localLayoutParams.gravity = Gravity.CENTER;
			localImageView.setLayoutParams(localLayoutParams);

			localLinearLayout.addView(localImageView);

			addView(localLinearLayout);

			this.mImageViews.add(localImageView);
		}

		selectItem(0);
	}

	/**
	 * 设置指定位置为选中状态
	 * 
	 * @param position
	 *            选中的位置
	 */
	public void selectItem(int position) {
		if (position < this.size)
			;
		for (int i = 0;; i++) {
			if (i >= this.size) {
				((ImageView) this.mImageViews.get(position)).setBackgroundResource(R.drawable.ic_dot_select);
				return;
			}
			((ImageView) this.mImageViews.get(i)).setBackgroundResource(R.drawable.ic_dot_noselect);
		}
	}
}