package com.umell.admin.view;

import com.umell.admin.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MenuItemView extends RelativeLayout {
	private TextView mText;

	public MenuItemView(Context paramContext) {
		super(paramContext);
	}

	public MenuItemView(Context paramContext, AttributeSet paramAttributeSet) {
		this(paramContext, paramAttributeSet, 0);
	}

	public MenuItemView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
		super(paramContext, paramAttributeSet, paramInt);
		TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.MenuItemView);
		initViews(localTypedArray.getDrawable(R.styleable.MenuItemView_menuItemLeftDrawable), localTypedArray.getString(R.styleable.MenuItemView_menuItemText));
		localTypedArray.recycle();
	}

	private void initViews(Drawable paramDrawable, String paramString) {
		this.mText = new TextView(getContext());
		RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-2, -1);
		localLayoutParams.setMargins(45, 0, 0, 0);
		this.mText.setLayoutParams(localLayoutParams);
		this.mText.setGravity(Gravity.CENTER_VERTICAL);
		this.mText.setDuplicateParentStateEnabled(true);
		paramDrawable.setBounds(0, 0, paramDrawable.getMinimumWidth() + paramDrawable.getMinimumWidth() / 5, paramDrawable.getMinimumHeight() + paramDrawable.getMinimumHeight() / 5);
		this.mText.setCompoundDrawables(paramDrawable, null, null, null);
		this.mText.setCompoundDrawablePadding(45);
		this.mText.setTextSize(17.0F);
		this.mText.setTextColor(Color.BLACK);
		this.mText.setText(paramString);
		addView(this.mText);
		setClickable(true);
		setBackgroundResource(R.drawable.menu_item_background_selector);
	}
}
