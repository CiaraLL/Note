package com.example.note.addnote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.note.R;
import com.example.note.data.NoteDataCatch;
import com.example.note.home.HomeActivity;
import com.example.note.model.Note;

/**
 * author: LL
 * created on: 2021/6/11 15:43
 * description: 添加笔记activity
 */
public class AddNoteActivity extends AppCompatActivity {
  private NoteDataCatch mNoteDataCatch;
  private EditText mTitleEditText;
  private EditText mContentEditText;
  private Button mSaveButton;

  public static void start(@NonNull Activity activity) {
    Intent intent = new Intent(activity, AddNoteActivity.class);
    activity.startActivity(intent);
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_note_layout);
    mNoteDataCatch = new NoteDataCatch();
    initData();
  }

  private void initData() {
    mTitleEditText = findViewById(R.id.add_note_page_addTitle);
    mContentEditText = findViewById(R.id.fragment_note_details_page_noteContent);
    mSaveButton = findViewById(R.id.add_note_page_save_button);
    mSaveButton.setOnClickListener(v -> {
      doAddNote();
    });
  }

  private void doAddNote() {
    String title = mTitleEditText.getText().toString();
    String content = mContentEditText.getText().toString();
    if (TextUtils.isEmpty(title) || TextUtils.isEmpty(content)) {
      Toast.makeText(this, "请输入笔记标题或者内容", Toast.LENGTH_SHORT).show();
      return;
    }

    mNoteDataCatch.saveNote(new Note(title, content));
    Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    HomeActivity.start(AddNoteActivity.this);
    finish();
  }
}
