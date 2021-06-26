package com.example.note.home.notedetails;

import java.io.Serializable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.note.R;
import com.example.note.model.Note;

/**
 * author: LL
 * created on: 2021/6/16 17:21
 * description:
 */
public class NoteDetailsActivity extends AppCompatActivity {

  //
  private static final String NOTE_INTENT_KEY = "note";

  //对外暴露打开自己的方式
  public static void start(@NonNull Activity activity, @NonNull Note note) {
    Intent intent = new Intent(activity, NoteDetailsActivity.class);
    intent.putExtra(NOTE_INTENT_KEY,note);
    activity.startActivity(intent);
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_note_details_layout);

    Note note = null;
    Intent intent = getIntent();
    Serializable extra = intent.getSerializableExtra(NOTE_INTENT_KEY);
    if(extra instanceof Note){
      note = (Note) extra;
    }

    getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.note_details_activity_container, new NoteDetailsFragment(note))
        .commit();
  }

}
