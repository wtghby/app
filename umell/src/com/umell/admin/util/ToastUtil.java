package com.umell.admin.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil
{
  public static void showToast(Context paramContext, CharSequence paramCharSequence)
  {
    Toast.makeText(paramContext, paramCharSequence, Toast.LENGTH_SHORT).show();
  }
}
