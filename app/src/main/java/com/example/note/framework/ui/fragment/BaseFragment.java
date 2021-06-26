package com.example.note.framework.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * author: LL
 * created on: 2021/6/22 23:16
 * description: Fragment基类
 */
public abstract class BaseFragment extends Fragment {

  protected View mViewContainer;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    if (mViewContainer != null) {
      return mViewContainer;
    }
    return mViewContainer = inflater.inflate(getLayoutResId(), container, false);
  }

  // 子类的布局，自己实现
  @LayoutRes
  protected abstract int getLayoutResId();

}
