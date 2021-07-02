package com.example.note.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

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
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    if(keyCode == KeyEvent.KEYCODE_BACK){
      moveTaskToBack(true);
      return true;
    }
    return super.onKeyDown(keyCode, event);
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
