package com.example.note.home.notelist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.note.R;
import com.example.note.data.NoteDataCatch;
import com.example.note.model.Note;

import java.util.List;
import javax.xml.transform.Result;

/**
 * 笔记列表适配器
 */
public class NoteListAdapter extends RecyclerView.Adapter<NoteViewHolder> {

  @Nullable
  private NoteListListener mNoteListListener;

  @NonNull
  private Context mContext;

  @NonNull
  private List<Note> mNoteList;

  public NoteListAdapter(@NonNull Context context, @NonNull List<Note> noteList) {
    this.mContext = context;
    this.mNoteList = noteList;
  }

  public void setLongClickListener(@Nullable NoteListListener noteListListener) {
    mNoteListListener = noteListListener;
  }

  public void update(@Nullable List<Note> mNoteList){
    this.mNoteList = mNoteList;
    notifyDataSetChanged();
  }

  @NonNull
  @Override
  public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(mContext).inflate(R.layout.note_item_layout, parent, false);
    return new NoteViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
    Note note = mNoteList.get(position);
    holder.binData(note);

    holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
      @Override
      public boolean onLongClick(View v) {
        if (mNoteListListener != null) {
          mNoteListListener.onItemLongClickListener(note);
          return true;
        }
        return false;
      }
    });
    holder.itemView.setOnClickListener(view -> {
      if (mNoteListListener != null) {
        mNoteListListener.onItemClickListener(note);
      }
    });
  }

  @Override
  public int getItemCount() {
    if(mNoteList == null){
      return 0;
    }
    return mNoteList.size();
  }

}
