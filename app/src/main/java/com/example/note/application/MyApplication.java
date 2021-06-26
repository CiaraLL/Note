package com.example.note.application;

import static com.example.note.context.PreferenceContext.APP_SH_KEY;

import android.app.Application;
import android.content.Context;

import com.example.note.context.ActivityContext;
import com.example.note.context.AppEnv;
import com.example.note.context.PreferenceContext;

/**
 * author: LL
 * created on: 2021/6/23 23:57
 * description: 整个app的application
 */
public class MyApplication extends Application {

  @Override
  protected void attachBaseContext(Context base) {
    super.attachBaseContext(base);

    // 初始化全局context
    AppEnv.get().mAppContext = this;

    // 初始化Sh
    PreferenceContext.get().mSharedPreferences =
        this.getSharedPreferences(APP_SH_KEY, Context.MODE_PRIVATE);

    // 监听activity的各个生命周期
    registerActivityLifecycleCallbacks(ActivityContext.get());
  }
}
