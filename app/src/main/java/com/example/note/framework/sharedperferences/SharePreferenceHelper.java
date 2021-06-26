package com.example.note.framework.sharedperferences;

import java.lang.reflect.Type;

import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.note.context.CurrentMe;
import com.example.note.context.GsonContext;
import com.example.note.context.PreferenceContext;
import com.example.note.model.Note;
import com.example.note.model.User;
import com.google.gson.Gson;

/**
 * author: LL
 * created on: 2021/6/24 23:44
 * description: SH工具类
 */
public class SharePreferenceHelper {

  // 保存
  public static void save(@NonNull String key, @NonNull Object object, boolean userRelative) {
    String json = GsonContext.get().GSON.toJson(object);
    SharedPreferences.Editor editor = PreferenceContext.get().mSharedPreferences.edit();
    if (userRelative) {
      key = getAppendUserKey(key);
    }
    editor.putString(key, json);
    editor.apply();
  }

  // 获取数据
  @Nullable
  public static <T> T get(@NonNull String key, @NonNull Type type, boolean userRelative) {
    if (userRelative) {
      key = getAppendUserKey(key);
    }
    String json = PreferenceContext.get().mSharedPreferences.getString(key, "");
    try {
      return GsonContext.get().GSON.fromJson(json, type);
    } catch (Throwable throwable) {
      return null;
    }
  }

  // 获得和具体用户相关的存储key
  public static String getAppendUserKey(@NonNull String key) {
    return CurrentMe.ME.mAccount + key;
  }

}
