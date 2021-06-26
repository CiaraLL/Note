package com.example.note.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.note.R;
import com.example.note.register.RegisterActivity;

/**
 * 登录activity
 */
public class LoginActivity extends AppCompatActivity {

  // 登录监听器
  private LoginListener mLoginListener = new LoginListener() {
    @Override
    public void onLoginSuccess() {
      // 登录成功时，activity关闭
      finish();
    }
  };

  public static void start(@NonNull Activity activity) {
    Intent intent = new Intent(activity, LoginActivity.class);
    activity.startActivity(intent);
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login_layout);
    getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.login_activity_container, new LoginFragment(mLoginListener))
        .commit();
  }

  // 登录监听器
  public interface LoginListener {

    // 登录成功时回调
    void onLoginSuccess();

  }
}
