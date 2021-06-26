package com.example.note.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 首页面
 */
public class HomeActivity extends AppCompatActivity {

  public static void start(Activity activity) {
    Intent intent = new Intent(activity, HomeActivity.class);
    activity.startActivity(intent);
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getSupportFragmentManager()
        .beginTransaction()
        .replace(android.R.id.content, new HomeTabFragment())
        .commit();
  }

}
