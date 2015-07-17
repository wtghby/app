package com.umell.admin.base;

import android.app.Activity;
import android.os.Bundle;

public abstract class BaseActivity extends Activity
{

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setLayout();
    initViewPager();
    initViews();
  }

  protected abstract void setLayout();
  
  protected abstract void initViewPager();

  protected abstract void initViews();
}
