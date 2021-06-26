package com.example.note.login;

import java.util.HashMap;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.example.note.context.CurrentMe;
import com.example.note.data.UserDataCache;
import com.example.note.model.User;

/**
 * author: LL
 * created on: 2021/6/18 16:03
 * description: 用于登录的逻辑处理，与UI分离
 */
public class LoginHandler {

  @NonNull
  private Context mContext;

  @NonNull
  private UserDataCache mUserDataCatch;

  // 用于保存上次登录的信息
  public static final String SH_LAST_LOGIN_INFO = "last_login_info";
  // 账号
  public static final String SH_LAST_LOGIN_ACCOUNT = "last_login_account";
  // 密码
  public static final String SH_LAST_LOGIN_PWD = "last_login_pwd";
  // 是否记住密码
  public static final String SH_LAST_LOGIN_IS_REMEMBER_PWD = "last_login_is_remember_pwd";

  public LoginHandler(@NonNull Context context) {
    mContext = context;
    mUserDataCatch = new UserDataCache();
  }

  /**
   * 登录
   *
   * @param isRememberPwd 是否记住密码
   */
  @LoginResult
  public int login(
      @NonNull String account,
      @NonNull String password,
      boolean isRememberPwd) {
    User user = mUserDataCatch.getUserByAccount(account);
    // 该用户不存在
    if (user == null) {
      return LoginResult.ACCOUNT_ERROR;
    }

    // 判断密码
    if (!TextUtils.equals(password, user.mPassword)) {
      return LoginResult.PASSWORD_ERROR;
    }

    saveAccount(account);
    // 记住密码
    if (isRememberPwd) {
      savePwd(password);
    } else {
      savePwd("");
    }
    // 是否记住密码
    saveIsRememberPwd(isRememberPwd);
    CurrentMe.ME = user;
    return LoginResult.SUCCESS;
  }

  /**
   * 上次登录的账号
   */
  @NonNull
  public String getLastLoginAccount() {
    SharedPreferences sharedPreferences =
        mContext.getSharedPreferences(SH_LAST_LOGIN_INFO, Context.MODE_PRIVATE);
    return sharedPreferences.getString(SH_LAST_LOGIN_ACCOUNT, "");
  }

  /**
   * 上次登录的账号的密码
   */
  @NonNull
  public String getLastLoginPwd() {
    SharedPreferences sharedPreferences =
        mContext.getSharedPreferences(SH_LAST_LOGIN_INFO, Context.MODE_PRIVATE);
    return sharedPreferences.getString(SH_LAST_LOGIN_PWD, "");
  }

  /**
   * 是否记住密码
   */
  @NonNull
  public boolean getIsRememberPwd() {
    SharedPreferences sharedPreferences =
        mContext.getSharedPreferences(SH_LAST_LOGIN_INFO, Context.MODE_PRIVATE);
    return sharedPreferences.getBoolean(SH_LAST_LOGIN_IS_REMEMBER_PWD, false);
  }

  // 用于记住上次登录的账号
  private void saveAccount(@NonNull String account) {
    SharedPreferences sharedPreferences =
        mContext.getSharedPreferences(SH_LAST_LOGIN_INFO, Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putString(SH_LAST_LOGIN_ACCOUNT, account);
    editor.apply();
  }

  // 用于记住上次登录的密码
  private void savePwd(@NonNull String password) {
    SharedPreferences sharedPreferences =
        mContext.getSharedPreferences(SH_LAST_LOGIN_INFO, Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putString(SH_LAST_LOGIN_PWD, password);
    editor.apply();
  }

  // 保存是否记住密码
  private void saveIsRememberPwd(boolean isRememberPwd) {
    SharedPreferences sharedPreferences =
        mContext.getSharedPreferences(SH_LAST_LOGIN_INFO, Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putBoolean(SH_LAST_LOGIN_IS_REMEMBER_PWD, isRememberPwd);
    editor.apply();
  }

}
