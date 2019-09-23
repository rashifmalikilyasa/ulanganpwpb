package com.example.ulanganpwpb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.NotesViewHolder> {

    Context context;
    OnUserClickListener listener;

    List<Notes> listnotesinfo;
    public RecyclerViewAdapter(Context context, List<Notes> listnotesinfo, OnUserClickListener listener){
        this.context=context;
        this.listnotesinfo=listnotesinfo;
        this.listener=listener;
    }
    public interface OnUserClickListener{
        void onUserClick(Notes currentnotes, String action);
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewtype){
       LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
       View view = layoutInflater.inflate(R.layout.user_row_item_list, parent, false);
       NotesViewHolder notesViewHolder = new NotesViewHolder(view);
       return notesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position){
        final Notes currentnotes = listnotesinfo.get(position);
        holder.ctxtjudul.setText(currentnotes.getJudul());
        holder.ctxtdeskripsi.setText(currentnotes.getDeskripsi());
        holder.ctxtdate.setText(currentnotes.getDate());
        holder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onUserClick(currentnotes,"Edit");
            }
        });
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onUserClick(currentnotes, "Delete");
            }
        });
    }

    @Override
    public int getItemCount(){

        return listnotesinfo.size();

    }

    public class NotesViewHolder extends RecyclerView.ViewHolder{

        TextView ctxtjudul, ctxtdeskripsi, ctxtdate;
        Button btn_edit, btn_delete;
        public NotesViewHolder(@NonNull View itemview){
            super(itemview);
            ctxtjudul = itemview.findViewById(R.id.tf_judul);
            ctxtdeskripsi = itemview.findViewById(R.id.tf_deskripsi);
            ctxtdate = itemview.findViewById(R.id.tf_tanggal);
            btn_edit = itemview.findViewById(R.id.btnEdit);
            btn_delete = itemview.findViewById(R.id.btnHapus);
        }

    }
}
