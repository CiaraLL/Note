package com.example.note.context;

import androidx.annotation.Nullable;
import androidx.core.view.accessibility.AccessibilityViewCommand;

import com.example.note.model.User;

/**
 * author: LL
 * created on: 2021/6/22 23:33
 * description: 当前用户
 */
public class CurrentMe {

  // 确保登录成功后，一定不为空
  @Nullable
  public static User ME;

}
