package com.example.note.set.modifypwd;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.note.R;
import com.example.note.context.CurrentMe;
import com.example.note.data.UserDataCache;
import com.example.note.framework.ui.fragment.BaseFragment;

/**
 * author: LL
 * created on: 2021/6/21 21:13
 * description: 修改密码
 */
public class ModifyPasswordFragment extends BaseFragment {

  // 旧密码输入框
  private EditText mOldPasswordView;

  // 两个新密码输入框
  private EditText mFirstPasswordView;
  private EditText mSecondPasswordView;

  @NonNull
  private Button mSaveButton;

  private UserDataCache mUserDataCache;

  @Nullable
  private ModifyPwdActivity.ModifyPasswordListener mModifyPasswordListener;

  public ModifyPasswordFragment(
      @Nullable ModifyPwdActivity.ModifyPasswordListener modifyPasswordListener) {
    mModifyPasswordListener = modifyPasswordListener;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mUserDataCache = new UserDataCache();
  }

  @Override
  public void onViewCreated(
      @NonNull View view,
      @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    initView(view);
  }

  @Override
  protected int getLayoutResId() {
    return R.layout.fragment_modify_password_layout;
  }

  private void initView(@NonNull View paren) {
    mOldPasswordView = paren.findViewById(R.id.modify_password_page_old_pwd_view);
    mFirstPasswordView = paren.findViewById(R.id.modify_password_page_first_new_pwd_view);
    mSecondPasswordView = paren.findViewById(R.id.modify_password_page_second_new_pwd_view);
    mSaveButton = paren.findViewById(R.id.modify_pwd_page_save_button);

    mSaveButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        doModifyPassword();
      }
    });
  }

  // 修改密码
  private void doModifyPassword() {
    String oldPwd = mOldPasswordView.getText().toString();
    String firstNewPwd = mFirstPasswordView.getText().toString();
    String secondNewPwd = mSecondPasswordView.getText().toString();

    // 判断3个都不能为空
    if (TextUtils.isEmpty(oldPwd) || TextUtils.isEmpty(firstNewPwd) ||
        TextUtils.isEmpty(secondNewPwd)) {
      Toast.makeText(getContext(), "密码不能为空", Toast.LENGTH_SHORT).show();
      return;
    }

    // 判断两个新密码要一样
    if (!TextUtils.equals(firstNewPwd, secondNewPwd)) {
      Toast.makeText(getContext(), "两次输入的新密码不一致", Toast.LENGTH_SHORT).show();
      return;
    }

    if (CurrentMe.ME == null) {
      Toast.makeText(getContext(), "未知错误！！", Toast.LENGTH_SHORT).show();
      return;
    }

    // 老密码输入错粗
    if (!TextUtils.equals(oldPwd, CurrentMe.ME.mPassword)) {
      Toast.makeText(getContext(), "老密码输入错粗!", Toast.LENGTH_SHORT).show();
      return;
    }

    // 比较旧密码是不是当前的密码一样
    if (TextUtils.equals(CurrentMe.ME.mPassword, firstNewPwd)) {
      Toast.makeText(getContext(), "新密码不能与旧密码一致", Toast.LENGTH_SHORT).show();
      return;
    }

    CurrentMe.ME.mPassword = firstNewPwd;
    mUserDataCache.updateUser(CurrentMe.ME);

    // toast密码修改成功
    Toast.makeText(getContext(), "密码修改成功！", Toast.LENGTH_SHORT).show();
    if (mModifyPasswordListener != null) {
      mModifyPasswordListener.onModifyPwdSuccess();
    }
  }

}
