package com.umell.admin.view;


import com.umell.admin.R;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TitleBar extends RelativeLayout
{
  private Button mBack;
  private TextView mText;

  public TitleBar(Context paramContext)
  {
    super(paramContext);
  }

  public TitleBar(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public TitleBar(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initViews();
  }

  private void initViews()
  {
    this.mText = new TextView(getContext());
    this.mBack = new Button(getContext());
    RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(-1, -1);
    this.mText.setLayoutParams(localLayoutParams1);
    this.mText.setGravity(Gravity.CENTER);
    this.mText.setSingleLine(true);
    this.mText.setEllipsize(TextUtils.TruncateAt.END);
    Resources localResources = getResources();
    float f = localResources.getDimension(R.dimen.title_bar_text_size);
    this.mText.setTextSize(0, (int)f);
    ColorStateList localColorStateList = localResources.getColorStateList(R.color.title_bar_text);
    if (localColorStateList != null)
      this.mText.setTextColor(localColorStateList);
    RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(60, RelativeLayout.LayoutParams.MATCH_PARENT);
    this.mBack.setLayoutParams(localLayoutParams2);
    //this.mBack.setBackgroundResource(2130837592);
    Drawable localDrawable = localResources.getDrawable(R.drawable.back_arrow);
    localDrawable.setBounds(0, 0, localDrawable.getMinimumWidth(), localDrawable.getMinimumHeight());
    this.mBack.setCompoundDrawables(localDrawable, null, null, null);
    this.mBack.setGravity(Gravity.CENTER);
    this.mBack.setPadding(10, 0, 0, 0);
    addView(this.mText);
    addView(this.mBack);
  }

  public Button getBackButton()
  {
    return this.mBack;
  }

  public TextView getTitle()
  {
    return this.mText;
  }
}