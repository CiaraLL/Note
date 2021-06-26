package com.example.note.framework.ui.fragment.tabfragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

/**
 * author: LL
 * created on: 2021/6/20 22:39
 * description: 单个页面的能力：一个tab +一个fragment
 */
public class TabFragmentDelegate {

  @NonNull
  public TabLayout.Tab mTab;
  @NonNull
  public Fragment mFragment;

  public TabFragmentDelegate(@NonNull TabLayout.Tab tab,
      @NonNull Fragment fragment) {
    mTab = tab;
    mFragment = fragment;
  }

}
