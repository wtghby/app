package com.umell.admin;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.umell.admin.base.BaseViewPagerActivity;
import com.umell.admin.jazzy.viewpager.JazzyViewPager;
import com.umell.admin.view.DotMark;

public class LoadingActivity extends BaseViewPagerActivity
{
  private DotMark mDotMark;
  private Button mStart;

  private void init()
  {
    this.mDotMark = ((DotMark)findViewById(R.id.dot_mark));
    this.mStart = ((Button)mViews.get(mViews.size() - 1).findViewById(R.id.loading_start_main));
    this.mStart.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Intent localIntent = new Intent(LoadingActivity.this.getApplicationContext(), MainActivity.class);
        LoadingActivity.this.startActivity(localIntent);
        LoadingActivity.this.finish();
      }
    });
  }

  protected void initViewPager()
  {
    this.mJazzyViewPager = (JazzyViewPager) findViewById(R.id.view_pager);
    this.mJazzyViewPager.setOnPageChangeListener(new OnPageChanged());
  }

  @SuppressLint({"InflateParams"})
  protected void initViews()
  {
    this.mViews = new ArrayList<View>();
    LayoutInflater localLayoutInflater = LayoutInflater.from(this);
    this.mViews.add(localLayoutInflater.inflate(R.layout.loading_view_pager_1, null));
    this.mViews.add(localLayoutInflater.inflate(R.layout.loading_view_pager_2, null));
    this.mViews.add(localLayoutInflater.inflate(R.layout.loading_view_pager_3, null));
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    init();
  }

  protected void setLayout()
  {
    setContentView(R.layout.loading_layout);
  }

  private class OnPageChanged
    implements OnPageChangeListener
  {
    private OnPageChanged()
    {
    }

    public void onPageScrollStateChanged(int paramInt)
    {
    }

    public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
    {
    }

    public void onPageSelected(int paramInt)
    {
      LoadingActivity.this.mDotMark.selectItem(paramInt);
    }
  }
}