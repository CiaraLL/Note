package com.example.note.context;

import android.app.Application;
import android.content.Context;
import androidx.annotation.NonNull;

/**
 * author: 13482
 * created on: 2021/6/23 23:47
 * description: app上下文环境
 */
public class AppEnv {

  private static final AppEnv sAppEnv = new AppEnv();

  // 用作全局的context,保证在系统启动的时候已经有值
  public Application mAppContext;

  public static AppEnv get() {
    return sAppEnv;
  }

}
