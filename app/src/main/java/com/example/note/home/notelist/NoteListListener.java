package com.example.note.home.notelist;

import androidx.annotation.NonNull;

import com.example.note.model.Note;

/**
 * author: 13482
 * created on: 2021/6/15 22:22
 * description: 笔记列表的回调
 */
public interface NoteListListener {

  /**
   * 长按
   * @param note
   */
  void onItemLongClickListener(@NonNull Note note);

  /**
   * 点击
   * @param note
   */
  void onItemClickListener(@NonNull Note note);

}
