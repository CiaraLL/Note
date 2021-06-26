package com.example.note.set.userinfo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.note.R;
import com.example.note.context.CurrentMe;
import com.example.note.framework.ui.fragment.BaseFragment;

/**
 * author: LL
 * created on: 2021/6/24 14:10
 * description:展示详情页面的fragment
 */
public class ShowInfoFragment extends BaseFragment {

  private TextView mNickNameView;
  private TextView mSexView;
  private Button mUpdateInfoButton;

  @Override
  public void onViewCreated(@NonNull View view,
      @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    initView(view);
  }

  @Override
  protected int getLayoutResId() {
    return R.layout.fragment_show_info_layout;
  }

  private void initView(View parentView) {
    mNickNameView = parentView.findViewById(R.id.fragment_show_info_page_nickname_view);
    mSexView = parentView.findViewById(R.id.fragment_show_info_page_sex_view);
    mUpdateInfoButton = parentView.findViewById(R.id.fragment_show_info_page_update_info_button);

    mUpdateInfoButton.setOnClickListener(v -> {
      clickUpdateInfoButton();
    });

    mNickNameView.setText(CurrentMe.ME.mNickName);
    mSexView.setText(CurrentMe.ME.mSex);
  }

  private void clickUpdateInfoButton() {
    getActivity().finish();
    ModifyInfoActivity.start(getActivity());
  }
}
