package com.example.note.context;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * author: LL
 * created on: 2021/6/22 23:49
 * description: activity实例管理
 */
public class ActivityContext implements Application.ActivityLifecycleCallbacks {

  // 当前所有的activity
  @NonNull
  private static final List<WeakReference<Activity>> mActivityStack = new ArrayList<>();

  // 单例
  @NonNull
  private static final ActivityContext ACTIVITY_CONTEXT = new ActivityContext();

  // 获得实例
  @NonNull
  public static ActivityContext get() {
    return ACTIVITY_CONTEXT;
  }

  // 获取所有的activity
  @NonNull
  public List<WeakReference<Activity>> getmActivityStack() {
    return mActivityStack;
  }

  @Override
  public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
    addActivity(activity);
  }

  @Override
  public void onActivityStarted(@NonNull Activity activity) { }

  @Override
  public void onActivityResumed(@NonNull Activity activity) { }

  @Override
  public void onActivityPaused(@NonNull Activity activity) { }

  @Override
  public void onActivityStopped(@NonNull Activity activity) { }

  @Override
  public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) { }

  @Override
  public void onActivityDestroyed(@NonNull Activity activity) {
    removeActivity(activity);
  }

  // 添加activity
  private void addActivity(@NonNull Activity activity) {
    for (WeakReference<Activity> activityWeakReference : mActivityStack) {
      if (activityWeakReference.get() == activity) {
        return;
      }
    }
    mActivityStack.add(new WeakReference<>(activity));
  }

  // 移除某activity
  private void removeActivity(@NonNull Activity activity) {
    Iterator<WeakReference<Activity>> iterator = mActivityStack.iterator();
    while (iterator.hasNext()) {
      WeakReference<Activity> activityWeakReference = iterator.next();
      if (activityWeakReference == null || activityWeakReference.get() == activity) {
        iterator.remove();
      }
    }
  }

}
