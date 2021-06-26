package com.example.note.model;

import java.io.Serializable;
import java.util.UUID;

import androidx.annotation.Nullable;

/**
 * 笔记信息
 */
public class Note implements Serializable {

    public String mId;
    public String mTitle;
    public String mContent;

    public Note(String mTitle, String mContent) {
        mId = UUID.randomUUID().toString();
        this.mTitle = mTitle;
        this.mContent = mContent;
    }

    // 拷贝，只拷贝笔记的内容，id不变
    public void copyFrom(@Nullable Note note){
      if(note == null){
        return;
      }
      mTitle = note.mTitle;
      mContent = note.mContent;
    }

}
