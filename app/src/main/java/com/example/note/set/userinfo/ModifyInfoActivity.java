package com.example.note.set.userinfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * author: 13482
 * created on: 2021/6/23 21:12
 * description:
 */
public class ModifyInfoActivity extends AppCompatActivity {

  private ModifyInfoListener mModifyInfoListener = ()->{
    finish();
  };

  public static void start(@NonNull Activity activity){
    Intent intent = new Intent(activity, ModifyInfoActivity.class);
    activity.startActivity(intent);
  }

  @Override
  protected void onCreate(@Nullable  Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getSupportFragmentManager()
        .beginTransaction()
        .replace(android.R.id.content,new ModifyInfoFragment(mModifyInfoListener))
        .commit();
  }

  public interface ModifyInfoListener{
    void onModifyInfoSuccess();
  }
}
