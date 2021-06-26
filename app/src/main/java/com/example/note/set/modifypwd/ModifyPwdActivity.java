package com.example.note.set.modifypwd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.note.home.HomeActivity;
import com.example.note.login.LoginUtil;

/**
 * author: LL
 * created on: 2021/6/22 23:07
 * description: 修改密码
 */
public class ModifyPwdActivity extends AppCompatActivity {

  public static void start(@NonNull Activity activity) {
    Intent intent = new Intent(activity, ModifyPwdActivity.class);
    activity.startActivity(intent);
  }

  private ModifyPasswordListener mModifyPasswordListener = () -> {
    LoginUtil.gotoLoginActivity(ModifyPwdActivity.this);
  };

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getSupportFragmentManager()
        .beginTransaction()
        .replace(android.R.id.content, new ModifyPasswordFragment(mModifyPasswordListener))
        .commit();
  }

  // 监听密码修改
  public interface ModifyPasswordListener {

    void onModifyPwdSuccess();

  }

}
