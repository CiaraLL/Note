package com.example.note.framework.ui.fragment.tabfragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.note.R;
import com.google.android.material.tabs.TabLayout;

/**
 * author: LL
 * created on: 2021/6/20 22:42
 * description: 支持tab切换的fragment：内部可以在多个fragment之间切换
 */
public abstract class TabFragment extends Fragment {

  private View mViewContainer;

  protected TabLayout mTabLayout;
  private ViewPager mFragmentPager;
  private FragmentStatePagerAdapter mPagerAdapter;

  private TabLayout.OnTabSelectedListener mTabSelectedListener =
      new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {

          setSelect(tab.getPosition());
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) { }

        @Override
        public void onTabReselected(TabLayout.Tab tab) { }
      };

  @Nullable
  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    if (mViewContainer != null) {
      return mViewContainer;
    }
    return mViewContainer = inflater.inflate(
          getLayoutId(),
        container,
        false);
  }

  @Override
  public void onViewCreated(
      @NonNull View view,
      @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    // 子类
    mTabLayout = view.findViewById(R.id.tab_layout);
    mFragmentPager = view.findViewById(R.id.fragment_pager);

    // 初始化tabLayout
    mTabLayout.setOnTabSelectedListener(mTabSelectedListener);
    List<TabLayout.Tab> tabList = getTabList();
    for (TabLayout.Tab tab : tabList) {
      mTabLayout.addTab(tab);
    }

    // 初始化viewPager
    List<Fragment> fragmentList = getFragmentList();
    mPagerAdapter = new FragmentStatePagerAdapter(getFragmentManager()) {
      @NonNull
      @Override
      public Fragment getItem(int position) {
        return fragmentList.get(position);
      }

      @Override
      public int getCount() {
        return fragmentList.size();
      }
    };
    mFragmentPager.setAdapter(mPagerAdapter);

    // viewPager和tabLayout联动
    mFragmentPager.setOnPageChangeListener(
        new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
  }

  // 让自己下实现自己的页面布局
  @LayoutRes
  protected abstract int getLayoutId();

  // 所有的页面
  @NonNull
  protected abstract List<TabFragmentDelegate> getFragmentDelegates();

  /**
   * 选中某一个页面
   *
   * @param position 页面的index
   */
  private void setSelect(int position) {
    mFragmentPager.setCurrentItem(position);
  }

  // 获得所有的fragment
  @NonNull
  private List<Fragment> getFragmentList() {
    List<Fragment> fragments = new ArrayList<>();
    List<TabFragmentDelegate> fragmentDelegates = getFragmentDelegates();
    for (TabFragmentDelegate tabFragmentDelegate : fragmentDelegates) {
      fragments.add(tabFragmentDelegate.mFragment);
    }
    return fragments;
  }

  // 获得所有的tab
  @NonNull
  private List<TabLayout.Tab> getTabList() {
    List<TabLayout.Tab> tabs = new ArrayList<>();
    List<TabFragmentDelegate> fragmentDelegates = getFragmentDelegates();
    for (TabFragmentDelegate tabFragmentDelegate : fragmentDelegates) {
      tabs.add(tabFragmentDelegate.mTab);
    }
    return tabs;
  }

}
