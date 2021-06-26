package com.example.note.util;

import java.lang.ref.WeakReference;

import android.app.Activity;
import androidx.annotation.NonNull;

import com.example.note.context.ActivityContext;
import com.example.note.login.LoginActivity;

/**
 * author: LL
 * created on: 2021/6/26 18:42
 * description: ACTIVITY工具类
 */
public class ActivityUtil {

  /**
   * 关闭所有的除传入的clazz的activity
   *
   * @param clazz
   */
  public static void closeAllActivities(@NonNull Class<? extends Activity> clazz) {
    for (WeakReference<Activity> activityReference : ActivityContext.get().getmActivityStack()) {
      if (activityReference == null) {
        continue;
      }
      Activity temp = activityReference.get();
      if (temp == null) {
        continue;
      }
      Class<? extends Activity> tempClass = temp.getClass();
      // 其他activity都关闭
      if (!(tempClass == clazz)) {
        temp.finish();
      }
    }
  }

}
