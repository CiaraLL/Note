package com.example.note.context;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

/**
 * author: LL
 * created on: 2021/6/24 23:58
 * description: 用于提供Gson的上下文
 */
public class GsonContext {

  @NonNull
  private static final GsonContext GSON_CONTEXT = new GsonContext();

  // 提供给业务方的gson对象
  @NonNull
  public Gson GSON = new Gson();

  private GsonContext() {}

  // 单例模式
  public static GsonContext get() {
    return GSON_CONTEXT;
  }

}
