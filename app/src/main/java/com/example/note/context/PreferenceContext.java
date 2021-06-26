package com.example.note.context;

import android.content.SharedPreferences;

/**
 * author: LL
 * created on: 2021/6/24 23:30
 * description: 用于提供SharedPreference
 */
public class PreferenceContext {

  // 整个app的sh的key
  public static final String APP_SH_KEY = "APP";
  // 单例
  private static final PreferenceContext PREFERENCE_CONTEXT = new PreferenceContext();

  // 应当保证在系统启动的时候初始化完成
  public SharedPreferences mSharedPreferences;

  private PreferenceContext() {}

  public static PreferenceContext get() {
    return PREFERENCE_CONTEXT;
  }

}
