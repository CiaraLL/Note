package com.example.note.home;

import java.util.ArrayList;
import java.util.List;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.example.note.R;
import com.example.note.framework.ui.fragment.tabfragment.TabFragment;
import com.example.note.framework.ui.fragment.tabfragment.TabFragmentDelegate;
import com.example.note.home.mine.MineFragment;
import com.example.note.home.notelist.NoteListFragment;
import com.google.android.material.tabs.TabLayout;

/**
 * author: LL
 * created on: 2021/6/20 22:49
 * description: 首页面Fragment
 */
public class HomeTabFragment extends TabFragment {

  @Override
  @LayoutRes
  protected int getLayoutId() {
    return R.layout.fragment_home_layout;
  }


  @NonNull
  @Override
  protected List<TabFragmentDelegate> getFragmentDelegates() {
    List<TabFragmentDelegate> fragmentDelegates = new ArrayList<>();
    fragmentDelegates.add(getNoteListFragmentDelegate());
    fragmentDelegates.add(getMineFragmentDelegate());
    return fragmentDelegates;
  }

  @NonNull
  private TabFragmentDelegate getNoteListFragmentDelegate() {
    TabLayout.Tab tab = mTabLayout.newTab();
    tab.setCustomView(getTabView("笔记"));
    return new TabFragmentDelegate(tab, new NoteListFragment());
  }

  @NonNull
  private TabFragmentDelegate getMineFragmentDelegate() {
    TabLayout.Tab tab = mTabLayout.newTab();
    tab.setCustomView(getTabView("我的"));
    return new TabFragmentDelegate(tab, new MineFragment());
  }

  /**
   * 用于构建tab的view
   *
   * @param tabText tab的文案
   */
  private View getTabView(@NonNull String tabText) {
    TextView textView = new TextView(getContext());
    textView.setText(tabText);
    textView.setGravity(Gravity.CENTER);
    ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT);
    textView.setLayoutParams(layoutParams);
    return textView;
  }

}
