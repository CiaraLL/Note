package com.example.note.login;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.note.R;
import com.example.note.context.CurrentMe;
import com.example.note.framework.ui.fragment.BaseFragment;
import com.example.note.home.HomeActivity;
import com.example.note.model.User;
import com.example.note.register.RegisterActivity;

/**
 * 登录页面
 */
public class LoginFragment extends BaseFragment {

  //整个页面
  @Nullable
  private View mLoginLayout;

  // 账号和密码输入框
  @NonNull
  private EditText mAccountEditText;
  @NonNull
  private EditText mPasswordEditText;

  // 登录，去注册
  @NonNull
  private Button mLoginButton;
  @NonNull
  private TextView mRegisterTextView;

  private CheckBox mRememberCheckBox;

  // 用于登录逻辑处理
  @NonNull
  private LoginHandler mLoginHandler;

  @Nullable
  private LoginActivity.LoginListener mLoginListener;


  public LoginFragment(@Nullable LoginActivity.LoginListener loginListener) {
    mLoginListener = loginListener;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mLoginHandler = new LoginHandler(getContext());
  }


  @Override
  protected int getLayoutResId() {
    return R.layout.fragment_login_layout;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    initView(view);
  }

  private void initView(@NonNull View parentView) {
    mAccountEditText = parentView.findViewById(R.id.fragment_login_page_account_editText);
    mPasswordEditText = parentView.findViewById(R.id.fragment_login_page_password_editView);
    mLoginButton = parentView.findViewById(R.id.fragment_login_confirm_button);
    mRegisterTextView = parentView.findViewById(R.id.fragment_login_register_view);
    mRememberCheckBox = parentView.findViewById(R.id.remember_pwd);

    mLoginButton.setOnClickListener(view -> {
      doLogin();
    });

    mRegisterTextView.setOnClickListener(view -> {
      jumpToRegisterPage();
    });

    mAccountEditText.setText(mLoginHandler.getLastLoginAccount());
    mPasswordEditText.setText(mLoginHandler.getLastLoginPwd());

    // 设置是否记住密码
    mRememberCheckBox.setChecked(mLoginHandler.getIsRememberPwd());
  }

  // 登录
  private void doLogin() {
    String account = mAccountEditText.getText().toString();
    String password = mPasswordEditText.getText().toString();
    // 是否记住密码
    boolean isRememberPwd = mRememberCheckBox.isChecked();

    // 判断账号和密码输入框内容是否为空
    if (TextUtils.isEmpty(account)) {
      Toast.makeText(getContext(), "请输入登录账号", Toast.LENGTH_SHORT).show();
      mAccountEditText.requestFocus();
      return;
    }
    if (TextUtils.isEmpty(password)) {
      Toast.makeText(getContext(), "请输入密码", Toast.LENGTH_SHORT).show();
      mPasswordEditText.requestFocus();
      return;
    }

    int loginResult = mLoginHandler.login(account, password, isRememberPwd);
    switch (loginResult) {
      // 该用户不存在
      case LoginResult.ACCOUNT_ERROR:
        Toast.makeText(getContext(), "该账号不存在，请重新输入", Toast.LENGTH_SHORT).show();
        mAccountEditText.requestFocus();
        break;

      case LoginResult.PASSWORD_ERROR:
        Toast.makeText(getContext(), "密码错误", Toast.LENGTH_SHORT).show();
        mPasswordEditText.requestFocus();
        break;

      case LoginResult.SUCCESS:
        // 跳转主页面
        Activity activity = getActivity();
        if (activity == null) {
          return;
        }
        HomeActivity.start(activity);
        // 登录成功时回调
        if (mLoginListener != null) {
          mLoginListener.onLoginSuccess();
        }
        break;

      default:
        Toast.makeText(getContext(), "未知错误，请稍后重试！", Toast.LENGTH_SHORT).show();
        break;
    }
  }

  //去注册页面
  private void jumpToRegisterPage() {
    Activity activity = getActivity();
    if (activity == null) {
      return;
    }
    RegisterActivity.start(activity);
  }

}
