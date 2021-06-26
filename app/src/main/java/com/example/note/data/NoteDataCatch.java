package com.example.note.data;

import java.util.ArrayList;
import java.util.List;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.note.context.CurrentMe;
import com.example.note.framework.sharedperferences.SharePreferenceHelper;
import com.example.note.model.Note;
import com.google.gson.reflect.TypeToken;

/**
 * author: LL
 * created on: 2021/6/14 19:58
 * description: 笔记数据缓存
 */
public class NoteDataCatch {

  public static final String SH_NOTES = "notes";

  // 笔记的缓存是和具体的用户绑定的
  private static final boolean IS_CACHE_USER_RELATIVE = true;

  // 获取所有的笔记
  @Nullable
  public List<Note> getAllNotes() {
    return SharePreferenceHelper.get(
        SH_NOTES,
        new TypeToken<List<Note>>() {}.getType(),
        IS_CACHE_USER_RELATIVE);
  }

  // 保存单条笔记
  public boolean saveNote(@NonNull Note note) {
    List<Note> notes = new ArrayList();
    notes.add(note);
    return saveNotes(notes, false);
  }

  /**
   * 保存多条笔记
   *
   * @param notes      要保存的笔记
   * @param isOverride 是否直接覆盖原来的
   * @return
   */
  public boolean saveNotes(@NonNull List<Note> notes, boolean isOverride) {
    List<Note> noteList = isOverride ? new ArrayList<>() : getAllNotes();
    if (noteList == null) {
      noteList = new ArrayList<>();
    }
    noteList.addAll(notes);
    SharePreferenceHelper.save(SH_NOTES, noteList, IS_CACHE_USER_RELATIVE);
    return true;
  }

  // 删除
  public void removeNote(@Nullable Note note) {
    List<Note> noteList = getAllNotes();
    if (noteList == null) {
      return;
    }
    // 要删除的笔记
    List<Note> deleteNotes = new ArrayList<>();
    for (Note item : noteList) {
      // id相同时，视为同一条笔记
      if (TextUtils.equals(item.mId, note.mId)) {
        deleteNotes.add(item);
      }
    }
    for (Note item : deleteNotes) {
      noteList.remove(item);
    }
    saveNotes(noteList, true);
  }

  /**
   * 更新某笔记
   *
   * @param note
   */
  public void updateNote(@Nullable Note note) {
    if (note == null) {
      return;
    }
    // 要修改的笔记
    List<Note> noteList = getAllNotes();
    if (noteList == null) {
      return;
    }
    for (Note item : noteList) {
      // id相同时，视为同一条笔记
      if (TextUtils.equals(item.mId, note.mId)) {
        item.copyFrom(note);
      }
    }
    saveNotes(noteList, true);
  }

}
