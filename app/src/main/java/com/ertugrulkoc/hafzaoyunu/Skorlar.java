package com.ertugrulkoc.hafzaoyunu;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

public class Skorlar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skorlar);

        DataHelper dataHelper = new DataHelper(this.getApplicationContext());
        Cursor cursor= dataHelper.veriListele();
        while (cursor.moveToNext()){
            Toast.makeText(this, ""+cursor.getString(0)+" -- "+cursor.getString(1)+" -- "+cursor.getString(2), Toast.LENGTH_SHORT).show();
        }



    }


}