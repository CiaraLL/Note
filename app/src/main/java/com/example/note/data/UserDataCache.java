package com.example.note.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.note.framework.sharedperferences.SharePreferenceHelper;
import com.example.note.model.User;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;


/**
 * 用户数据缓存
 */
public class UserDataCache {

  // SharedPreferences中存储用户信息的key
  public static final String SH_USER_NAME = "user";

  // 用户信息的缓存不需要和具体的用户绑定
  private static final boolean IS_CACHE_USER_RELATIVE = false;

  //获取所有用户的信息 map<账号，user>
  public HashMap<String, User> getAllUser() {
    return SharePreferenceHelper.get(
        SH_USER_NAME,
        new TypeToken<HashMap<String, User>>() {}.getType(),
        IS_CACHE_USER_RELATIVE);
  }

  // 找某用户
  @Nullable
  public User getUserByAccount(@Nullable String account) {
    HashMap<String, User> allUser = getAllUser();
    // 该用户不存在
    if (allUser == null) {
      return null;
    }
    return allUser.get(account);
  }

  /**
   * 存储user
   *
   * @param isOverride 根据user.mAccount,是否复写
   * @return
   */
  public boolean putUser(@NonNull User user, boolean isOverride) {
    HashMap<String, User> userMap = getAllUser();
    if (userMap == null) {
      userMap = new HashMap<>();
    }
    if (userMap.containsKey(user.mAccount) && !isOverride) {
      return false;
    }
    userMap.put(user.mAccount, user);
    SharePreferenceHelper.save(SH_USER_NAME, userMap, IS_CACHE_USER_RELATIVE);
    return true;
  }

  /**
   * 修改用户信息，根据用户account索引
   */
  public boolean updateUser(@NonNull User user) {
    return putUser(user, true);
  }

}
