package com.example.note.home.notelist;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.note.R;
import com.example.note.addnote.AddNoteActivity;
import com.example.note.data.NoteDataCatch;
import com.example.note.framework.ui.fragment.BaseFragment;
import com.example.note.home.notedetails.NoteDetailsActivity;
import com.example.note.model.Note;

import java.util.List;

/**
 * 笔记页面
 */
public class NoteListFragment extends BaseFragment {

  private RecyclerView mRecyclerView;
  private NoteListAdapter mNoteListAdapter;

  private Button mAddButton;

  @Nullable
  private List<Note> mNoteList;

  @NonNull
  private NoteDataCatch mNoteDataCatch;

  // 笔记列表的监听器
  @NonNull
  private NoteListListener mNoteListListener = new NoteListListener() {
    @Override
    public void onItemLongClickListener(@NonNull Note note) {
      showHandleNoteOptionDialog(note);
    }

    @Override
    public void onItemClickListener(@NonNull Note note) {
      showNoteDetails(note);
      return;
    }
  };

  //用于短按时跳转到note详情页面
  private void showNoteDetails(@NonNull Note note) {
    Activity activity = getActivity();
    if (activity == null || activity.isFinishing()) {
      return;
    }
    NoteDetailsActivity.start(activity, note);
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mNoteDataCatch = new NoteDataCatch();
  }


  @Override
  protected int getLayoutResId() {
    return R.layout.fargment_note_list_layout;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    initView(view);
  }

  @Override
  public void onResume() {
    super.onResume();
    // 刷新界面
    updateNoteList();
  }

  private void initView(@NonNull View parent) {
    mAddButton = parent.findViewById(R.id.note_list_fragment_add_new_note_button);
    mAddButton.setOnClickListener(v -> {
      doAddNote();
    });
    mNoteList = mNoteDataCatch.getAllNotes();
    mRecyclerView = parent.findViewById(R.id.note_list_fragment_note_list_view);
    mNoteListAdapter = new NoteListAdapter(getContext(), mNoteList);

    mNoteListAdapter.setLongClickListener(mNoteListListener);
    LinearLayoutManager manager = new LinearLayoutManager(getContext());
    manager.setOrientation(RecyclerView.VERTICAL);
    mRecyclerView.setLayoutManager(manager);
    mRecyclerView.setAdapter(mNoteListAdapter);

    int noteSize = mNoteList == null ? 0 : mNoteList.size();
    Toast.makeText(getContext(), "一共" + noteSize + "条笔记", Toast.LENGTH_SHORT).show();
  }

  // 用于长按笔记item时，弹出的操作弹窗
  private void showHandleNoteOptionDialog(@NonNull Note note) {
    new AlertDialog.Builder(getContext())
        .setTitle("确定删除该条笔记吗")
        .setNegativeButton("取消", null)
        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            mNoteDataCatch.removeNote(note);
            updateNoteList();
            Toast.makeText(getContext(), "删除成功！", Toast.LENGTH_SHORT).show();
          }
        })
        .create()
        .show();
  }


  private void doAddNote() {
    AddNoteActivity.start(getActivity());
  }

  //刷新界面
  private void updateNoteList() {
    mNoteList = mNoteDataCatch.getAllNotes();
    mNoteListAdapter.update(mNoteList);
  }

}