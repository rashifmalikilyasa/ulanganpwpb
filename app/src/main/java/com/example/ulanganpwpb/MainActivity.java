package com.example.ulanganpwpb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.OnUserClickListener{

    RecyclerView recyclerView;
    Button btn_Tambah, btn_Submit;
    EditText edt_judul, edt_deskripsi;
    Context context;
    RecyclerView.LayoutManager layoutManager;
    List<Notes> listnotesinfo;

    public MainActivity() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();
        recyclerView = findViewById(R.id.recycler_view);

        layoutManager = new LinearLayoutManager(MainActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        edt_judul = findViewById(R.id.edt_judul);
        edt_deskripsi = findViewById(R.id.edt_deskripsi);
        btn_Submit = findViewById(R.id.submit);
        btn_Tambah = findViewById(R.id.btn_tambah);
        btn_Tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TambahActivity.class);
                startActivity(intent);
            }
        });

        setUpRecyclerView();
    }

    private void setUpRecyclerView(){
        DatabaseHelper db = new DatabaseHelper(context);
        listnotesinfo = db.selectUserData();
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(context, listnotesinfo, this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onUserClick(Notes currentnotes, String action) {
        if (action.equals("Edit")){
            edt_judul.setText(currentnotes.getJudul());
            edt_judul.setFocusable(false);
            edt_deskripsi.setText(currentnotes.getDeskripsi());
            btn_Submit.setText("Update");
        }
        if (action.equals("Delete")){
            DatabaseHelper db = new DatabaseHelper(context);
            db.delete(currentnotes.getJudul());
            setUpRecyclerView();
        }
    }
}
