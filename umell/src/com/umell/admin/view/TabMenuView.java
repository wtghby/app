package com.umell.admin.view;

import java.util.ArrayList;
import java.util.List;

import com.umell.admin.R;
import com.umell.admin.enums.ViewPagerDirection;
import com.umell.admin.inter.OnJazzyViewPagerItemSelectedListener;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

@SuppressLint({ "NewApi" })
public class TabMenuView extends LinearLayout {
	private OnJazzyViewPagerItemSelectedListener listener;
	private List<Button> mButtons;
	private int size;

	public TabMenuView(Context paramContext) {
		super(paramContext);
	}

	public TabMenuView(Context paramContext, AttributeSet paramAttributeSet) {
		this(paramContext, paramAttributeSet, 0);
	}

	public TabMenuView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
		super(paramContext, paramAttributeSet, paramInt);
		TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.TabMenuView);
		this.size = localTypedArray.getInt(R.styleable.TabMenuView_menuSize, 0);
		initViews();
		localTypedArray.recycle();
	}

	private Button getItem() {
		Button localButton = new Button(getContext());
		localButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1.0F));
		localButton.setBackgroundResource(android.R.color.transparent);
		localButton.setGravity(Gravity.CENTER);
		localButton.setSingleLine(true);
		// Resources localResources = getResources();
		// XmlResourceParser localXmlResourceParser = localResources
		// .getXml(2130837610);
		// try {
		// ColorStateList localColorStateList2 = ColorStateList.createFromXml(
		// localResources, localXmlResourceParser);
		// // localColorStateList1 = localColorStateList2;
		// if (localColorStateList2 != null)
		// localButton.setTextColor(localColorStateList2);
		// localButton.setTextSize(14.0F);
		//
		// } catch (XmlPullParserException localXmlPullParserException) {
		// localXmlPullParserException.printStackTrace();
		// } catch (IOException localIOException) {
		// localIOException.printStackTrace();
		// }
		return localButton;
	}

	private void initViews() {
		if (this.size < 1)
			return;
		// while (true) {
		// return;
		this.mButtons = new ArrayList<Button>();
		OnClick localOnClick = new OnClick();
		for (int i = 0; i < this.size; i++) {
			Button localButton = getItem();
			localButton.setOnClickListener(localOnClick);
			addView(localButton);
			this.mButtons.add(localButton);
		}
		// }
	}

	private void selectTwoItem(int paramInt1, int paramInt2) {
		for (int i = 0;; i++) {
			if (i >= this.mButtons.size()) {
				((Button) this.mButtons.get(paramInt1)).setSelected(true);
				((Button) this.mButtons.get(paramInt2)).setSelected(true);
				return;
			}
			((Button) this.mButtons.get(i)).setSelected(false);
		}
	}

	private void setItemAlpha(int paramInt, float paramFloat) {
		if ((paramInt > -1) && (paramInt < this.mButtons.size()))
			((Button) this.mButtons.get(paramInt)).setAlpha(paramFloat);
	}

	public void initItemTopDrawable(int paramInt1, int paramInt2, CharSequence paramCharSequence) {
		if (paramInt1 < this.mButtons.size()) {
			((Button) this.mButtons.get(paramInt1)).setTag(Integer.valueOf(paramInt2));
			// if (!TextUtils.isEmpty(paramCharSequence))
			// ((Button) this.mButtons.get(paramInt1))
			// .setText(paramCharSequence);
			Drawable localDrawable = getResources().getDrawable(paramInt2);
			localDrawable.setBounds(0, 0, localDrawable.getMinimumWidth(), localDrawable.getMinimumHeight());
			((Button) this.mButtons.get(paramInt1)).setCompoundDrawables(null, localDrawable, null, null);
		}
	}

	public void scrollItem(int paramInt, float paramFloat, ViewPagerDirection paramViewPagerDirection) {
		if (paramViewPagerDirection == ViewPagerDirection.LEFT)
			if (paramInt > -1) {
				selectTwoItem(paramInt, paramInt + 1);
				setItemAlpha(paramInt + 1, paramFloat);
				setItemAlpha(paramInt, 1.0F - paramFloat);
			}
		while ((paramViewPagerDirection != ViewPagerDirection.RIGHT) || (paramInt + 1 >= this.mButtons.size()))
			return;
		selectTwoItem(paramInt, paramInt + 1);
		setItemAlpha(paramInt + 1, paramFloat);
		setItemAlpha(paramInt, 1.0F - paramFloat);
	}

	public void selectItem(int paramInt) {
		if (paramInt < this.mButtons.size())
			;
		for (int i = 0;; i++) {
			if (i >= this.mButtons.size()) {
				((Button) this.mButtons.get(paramInt)).setSelected(true);
				return;
			}
			((Button) this.mButtons.get(i)).setSelected(false);
			((Button) this.mButtons.get(i)).setAlpha(1.0F);
		}
	}

	public void setAllItemAlpha(float paramFloat) {
		for (int i = 0;; i++) {
			if (i >= this.mButtons.size())
				return;
			((Button) this.mButtons.get(i)).setAlpha(paramFloat);
		}
	}

	public void setOnTabMenuViewItemSelectedListener(OnJazzyViewPagerItemSelectedListener paramOnJazzyViewPagerItemSelectedListener) {
		this.listener = paramOnJazzyViewPagerItemSelectedListener;
	}

	class OnClick implements OnClickListener {
		OnClick() {
		}

		public void onClick(View paramView) {
			int i = ((Integer) paramView.getTag()).intValue();
			for (int j = 0; j < mButtons.size(); j++) {
				int tag = (Integer) mButtons.get(j).getTag();
				if (i == tag) {
					selectItem(j);
					if (listener != null) {
						listener.onItemSelected(j);
					}
					break;
				}
				// if (j >= TabMenuView.this.mButtons.size())
				// ;
				// do {
				// return;
				// if (i != ((Integer) ((Button) TabMenuView.this.mButtons
				// .get(j)).getTag()).intValue())
				// break;
				// TabMenuView.this.selectItem(j);
				// } while (TabMenuView.this.listener == null);
				// TabMenuView.this.listener.onItemSelected(j);
				// return;
			}
		}
	}
}
