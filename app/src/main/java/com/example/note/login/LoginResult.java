package com.example.note.login;

import androidx.annotation.IntDef;

/**
 * author: 13482
 * created on: 2021/6/20 16:51
 * description: 登录结果的注解
 */
@IntDef({
    LoginResult.UN_KNOW,
    LoginResult.SUCCESS,
    LoginResult.ACCOUNT_ERROR,
    LoginResult.PASSWORD_ERROR,
})
public @interface LoginResult {

  int UN_KNOW = 0;    // 未知错误
  int SUCCESS = 1;    // 成功
  int ACCOUNT_ERROR = 2;  // 账号不存在
  int PASSWORD_ERROR = 3; // 密码错误

}
