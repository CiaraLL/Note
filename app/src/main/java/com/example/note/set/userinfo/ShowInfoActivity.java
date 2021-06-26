package com.example.note.set.userinfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * author: LL
 * created on: 2021/6/24 15:47
 * description:展示详情页面的activity
 */
public class ShowInfoActivity extends AppCompatActivity {

  public static void start(@Nullable Activity activity) {
    Intent intent = new Intent(activity, ShowInfoActivity.class);
    activity.startActivity(intent);
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getSupportFragmentManager()
        .beginTransaction()
        .replace(android.R.id.content, new ShowInfoFragment())
        .commit();

  }
}
