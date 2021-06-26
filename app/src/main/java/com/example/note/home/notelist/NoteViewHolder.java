package com.example.note.home.notelist;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.note.R;
import com.example.note.data.NoteDataCatch;
import com.example.note.model.Note;


/**
 * 控制单个笔记item的UI展示
 */
public class NoteViewHolder extends RecyclerView.ViewHolder {

    private TextView mTitleView;
    private TextView mContentView;

    public NoteViewHolder(@NonNull View itemView) {
        super(itemView);
        mTitleView = itemView.findViewById(R.id.note_item_fragment_title);
        mContentView = itemView.findViewById(R.id.note_item_fragment_content);

    }

    public void binData(@Nullable Note note) {
        if (note == null) {
            mContentView.setVisibility(View.GONE);
            mTitleView.setVisibility(View.GONE);
        }
        mTitleView.setText(note.mTitle);
        mContentView.setText(note.mContent);
    }

}
