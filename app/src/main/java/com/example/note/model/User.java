package com.example.note.model;

import androidx.annotation.NonNull;

/**
 * 用户信息
 */
public class User {
  public String mAccount;
  public String mPassword;
  public String mNickName; // 昵称
  public String mSex; // 性别

  public User(String mAccount, String mPassword) {
    this.mAccount = mAccount;
    this.mPassword = mPassword;

    // 昵称默认是账号
    mNickName = mAccount;
  }

  public void copyFrom(@NonNull User user) {
    mPassword = user.mPassword;
    mNickName = user.mNickName;
    mSex = user.mSex;
  }
}
