package com.example.note.set.userinfo;

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
import com.example.note.model.User;

/**
 * author: LL
 * created on: 2021/6/23 20:41
 * description: 修改个人信息
 */
public class ModifyInfoFragment extends BaseFragment {

  private EditText mNickNameView;
  private EditText mSexView;
  private Button mSaveButton;
  private UserDataCache mUserDataCache;
  private ModifyInfoActivity.ModifyInfoListener mModifyInfoListener;

  public ModifyInfoFragment(
      @Nullable ModifyInfoActivity.ModifyInfoListener modifyInfoListener) {
    mModifyInfoListener = modifyInfoListener;
  }

  @Override
  protected int getLayoutResId() {
    return R.layout.fragment_modify_info_layout;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    mUserDataCache = new UserDataCache();
  }

  @Override
  public void onViewCreated(@NonNull View view,
      @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    initView(view);
  }

  private void initView(View parentView) {
    mNickNameView = parentView.findViewById(R.id.modify_info_page_name_view);
    mSexView = parentView.findViewById(R.id.modify_info_page_sex_view);
    mSaveButton = parentView.findViewById(R.id.modify_info_page_save_button);

    mSaveButton.setOnClickListener(v -> {
      doModifyInfo();
    });


    mNickNameView.setText(CurrentMe.ME.mNickName);
    mSexView.setText(CurrentMe.ME.mSex);
  }

  // 点击保存按钮
  private void doModifyInfo() {
    String nickName = mNickNameView.getText().toString();
    String sex = mSexView.getText().toString();

    if (TextUtils.isEmpty(nickName)) {
      Toast.makeText(getContext(), "昵称不能为空", Toast.LENGTH_SHORT).show();
      return;
    }

    User temp = new User(CurrentMe.ME.mAccount, CurrentMe.ME.mPassword);
    temp.mSex = sex;
    temp.mNickName = nickName;
    boolean isSuccess = mUserDataCache.updateUser(temp);
    if (isSuccess) {
      CurrentMe.ME.copyFrom(temp);
      Toast.makeText(getContext(), "修改成功", Toast.LENGTH_SHORT).show();
      if (mModifyInfoListener != null) {
        mModifyInfoListener.onModifyInfoSuccess();
      }
    } else {
      Toast.makeText(getContext(), "错误，请稍后重试", Toast.LENGTH_SHORT).show();
    }
  }
}
