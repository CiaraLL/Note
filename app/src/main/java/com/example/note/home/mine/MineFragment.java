package com.example.note.home.mine;

import java.io.File;
import java.lang.ref.WeakReference;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.note.R;
import com.example.note.context.ActivityContext;
import com.example.note.context.CurrentMe;
import com.example.note.framework.ui.fragment.BaseFragment;
import com.example.note.login.LoginActivity;
import com.example.note.login.LoginUtil;
import com.example.note.set.userinfo.ModifyInfoActivity;
import com.example.note.set.modifypwd.ModifyPwdActivity;
import com.example.note.set.userinfo.ShowInfoActivity;
import com.example.note.util.ActivityUtil;

/**
 * author: LL
 * created on: 2021/6/20 22:17
 * description: 首页面的我的页面
 */
public class MineFragment extends BaseFragment {

  private static final int REQUEST_PICTURE = 0;
  private static final int REQUEST_PHOTOGRAPH = 1;
  private static final int REQUEST_PHOTO_CUT = 2;
  private static Uri tempUri;


  // 昵称view
  private TextView mUserNameView;
  // 用户头像
  private ImageView mUserHeaderView;
  private TextView mShowInfoView;

  private Button mModifyMessage;
  private Button mLogOutView;
  private Button mModifyPassword;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  protected int getLayoutResId() {
    return R.layout.fragment_mine_layout;
  }

  @Override
  public void onViewCreated(@NonNull View view,
      @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    initView(view);
  }

  @Override
  public void onResume() {
    super.onResume();
    // 页面展示的时候刷新一下数据
    update();
  }

  private void initView(@NonNull View parentView) {
    mUserNameView = parentView.findViewById(R.id.mine_page_user_nick_name);
    mUserHeaderView = parentView.findViewById(R.id.mine_page_user_header_image_view);
    mShowInfoView = parentView.findViewById(R.id.mine_page_show_info_view);
    mModifyMessage = parentView.findViewById(R.id.mine_page_modify_info);
    mLogOutView = parentView.findViewById(R.id.main_page_log_out);
    mModifyPassword = parentView.findViewById(R.id.mine_page_modify_password);

    mShowInfoView.setOnClickListener(v -> {
      Activity activity = getActivity();
      if (activity != null) {
        ShowInfoActivity.start(activity);
      }
    });

    mModifyMessage.setOnClickListener(v -> {
      Activity activity = getActivity();
      if (activity != null) {
        ModifyInfoActivity.start(activity);
      }

    });
    mLogOutView.setOnClickListener(v -> {
      Activity activity = getActivity();
      if (activity == null) {
        return;
      }
      LoginUtil.gotoLoginActivity(activity);
    });

    mModifyPassword.setOnClickListener(v -> {
      Activity activity = getActivity();
      if (activity != null) {
        ModifyPwdActivity.start(activity);
      }
    });

    update();
  }

  // 数据刷新
  private void update() {
    mUserNameView.setText(CurrentMe.ME.mNickName);
  }

}
