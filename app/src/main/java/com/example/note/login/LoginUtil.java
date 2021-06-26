package com.example.note.login;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.example.note.util.ActivityUtil;

/**
 * author: LL
 * created on: 2021/6/26 18:49
 * description: 登录工具类
 */
public class LoginUtil {

  /**
   * 跳转到登录页面
   */
  public static void gotoLoginActivity(@NonNull Activity currentActivity){
    LoginActivity.start(currentActivity);
    // 关闭除登录activity之外的其他所有activity
    ActivityUtil.closeAllActivities(LoginActivity.class);
  }

}
