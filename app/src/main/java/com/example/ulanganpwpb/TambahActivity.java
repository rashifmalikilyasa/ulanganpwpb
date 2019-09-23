package com.example.ulanganpwpb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TambahActivity extends AppCompatActivity {

    EditText edtjudul, edtdeskripsi;
    Button btnsubmit;
    Context context;

    @Override public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        context = this;
        edtjudul = findViewById(R.id.edt_judul);
        edtdeskripsi = findViewById(R.id.edt_deskripsi);

        Calendar cal = Calendar.getInstance();

        btnsubmit = findViewById(R.id.submit);
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.submit){
                    DatabaseHelper db = new DatabaseHelper(context);
                    Notes currentnotes = new Notes();
                    String btnStatus = btnsubmit.getText().toString();
                    if (btnStatus.equals("Submit")) {
                        currentnotes.setJudul(edtjudul.getText().toString());
                        currentnotes.setDeskripsi(edtdeskripsi.getText().toString());
                        SimpleDateFormat sdf = new SimpleDateFormat("d-M-yyyy h:m:s a");
                        String date = sdf.format(new Date());

                        currentnotes.setDate(date);
                        db.insert(currentnotes);
                    }
                    if (btnStatus.equals("Update")) {
                        currentnotes.setJudul(edtjudul.getText().toString());
                        currentnotes.setDeskripsi(edtdeskripsi.getText().toString());
                        SimpleDateFormat sdf = new SimpleDateFormat("d-M-yyyy h:m:s a");
                        String date = sdf.format(new Date());

                        currentnotes.setDate(date);
                        db.update(currentnotes);
                    }
                    Intent intent = new Intent(TambahActivity.this, MainActivity.class);
                    startActivity(intent);
                    edtjudul.setText("");
                    edtdeskripsi.setText("");
                    edtjudul.setFocusable(true);
                    btnsubmit.setText("Submit");
                }
            }
        });


    }
}
