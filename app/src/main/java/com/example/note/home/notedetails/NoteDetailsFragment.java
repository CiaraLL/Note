package com.example.note.home.notedetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.note.R;
import com.example.note.data.NoteDataCatch;
import com.example.note.framework.ui.fragment.BaseFragment;
import com.example.note.model.Note;

/**
 * author: LL
 * created on: 2021/6/16 17:24
 * description:
 */
public class NoteDetailsFragment extends BaseFragment {

  @Nullable
  private Note mNote;


  @NonNull
  private NoteDataCatch mNoteDataCatch;

  //整个页面
  @Nullable
  private View mNoteDetailsView;

  @NonNull
  private EditText mTitleView;

  @NonNull
  private EditText mContentView;

  @NonNull
  private Button mSaveButton;

  public NoteDetailsFragment(@Nullable Note note) {
    mNote = note;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mNoteDataCatch = new NoteDataCatch();
  }



  @Override
  protected int getLayoutResId() {
    return R.layout.fragment_note_detail_layout;
  }

  @Override
  public void onViewCreated(@NonNull View view,
      @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    initView(view);
  }

  private void initView(@NonNull View parentView) {

    mTitleView = parentView.findViewById(R.id.fragment_note_details_page_noteTitle);
    mContentView = parentView.findViewById(R.id.fragment_note_details_page_noteContent);
    if (mNote == null) {
      mTitleView.setVisibility(View.GONE);
      mContentView.setVisibility(View.GONE);
      return;
    }

    mTitleView.setVisibility(View.VISIBLE);
    mContentView.setVisibility(View.VISIBLE);
    mSaveButton = parentView.findViewById(R.id.fragment_note_details_page_save_button);
    mSaveButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        clickSaveButton();
      }
    });
    mTitleView.setText(mNote.mTitle);
    mContentView.setText(mNote.mContent);
  }

  // 保存按钮点击
  private void clickSaveButton() {
    if (mNote == null) {
      return;
    }
    mNote.mTitle = mTitleView.getText().toString();
    mNote.mContent = mContentView.getText().toString();
    mNoteDataCatch.updateNote(mNote);
    Toast.makeText(getContext(), "保存成功", Toast.LENGTH_SHORT).show();
    getActivity().finish();
  }

}
