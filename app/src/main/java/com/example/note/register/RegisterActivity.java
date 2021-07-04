package com.example.note.register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.note.R;
import com.example.note.data.UserDataCache;
import com.example.note.login.LoginActivity;
import com.example.note.model.User;

/**
 * 注册activity
 */
public class RegisterActivity extends AppCompatActivity {

  private UserDataCache mUserDataCache;
  private EditText mAccountEditText;
  private EditText mPasswordEditText;
  private Button mRegisterButton;

  // 对外暴露打开子自己的方式
  public static void start(@NonNull Activity activity) {
    Intent intent = new Intent(activity, RegisterActivity.class);
    activity.startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_regisetr_layout);
    mUserDataCache = new UserDataCache();
    initView();
  }

  private void initView() {
    mAccountEditText = findViewById(R.id.register_page_register_account_view);
    mPasswordEditText = findViewById(R.id.register_page_password_view);
    mRegisterButton = findViewById(R.id.register_page_confirm_button);
    mRegisterButton.setOnClickListener(view -> {
      doRegister();
    });
  }

  // 注册
  private void doRegister() {
    String account = mAccountEditText.getText().toString();
    String password = mPasswordEditText.getText().toString();
    if (TextUtils.isEmpty(account)) {
      Toast.makeText(RegisterActivity.this, "请输入要注册的账号", Toast.LENGTH_SHORT).show();
      mAccountEditText.requestFocus();
      return;
    }
    if (TextUtils.isEmpty(password)) {
      Toast.makeText(RegisterActivity.this, "请输入注册账号的密码", Toast.LENGTH_SHORT).show();
      mPasswordEditText.requestFocus();
      return;
    }

    if (mUserDataCache.getUserByAccount(account) != null) {
      Toast.makeText(RegisterActivity.this, "用户已经注册过", Toast.LENGTH_SHORT).show();
      return;
    }

    mUserDataCache.putUser(new User(account, password));
    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
    // 注册成功跳转登录页面
    LoginActivity.start(RegisterActivity.this);
    finish();
  }
}