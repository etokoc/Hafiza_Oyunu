package com.ertugrulkoc.hafzaoyunu;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Skorlar extends AppCompatActivity {

    String birinci,ikinci,ucuncu;
    int first, second, third;
    int hamleSayisi;
    TextView view_birinci,view_ikinci,view_ucuncu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skorlar);

        view_birinci = findViewById(R.id.txt_birinci);
        view_ikinci = findViewById(R.id.txt_ikinci);
        view_ucuncu = findViewById(R.id.txt_ucuncu);

        DataHelper dataHelper = new DataHelper(this.getApplicationContext());
        Cursor cursor = dataHelper.veriListele();
        first = 100;
        second = 100;
        third = 100;
        while (cursor.moveToNext()){
            if (Integer.parseInt(cursor.getString(1)) < first) {
                first = Integer.parseInt(cursor.getString(1));
                birinci="Hamle Sayısı: "+cursor.getString(1)+" Mod: "+cursor.getString(0)+" Tarih: "+cursor.getString(2);
            } else if (Integer.parseInt(cursor.getString(1)) < second) {
                second = Integer.parseInt(cursor.getString(1));
                ikinci="Hamle Sayısı: "+cursor.getString(1)+" Mod: "+cursor.getString(0)+" Tarih: "+cursor.getString(2);
            } else if (Integer.parseInt(cursor.getString(1)) < third) {
                third = Integer.parseInt(cursor.getString(1));
                ucuncu="Hamle Sayısı: "+cursor.getString(1)+" Mod: "+cursor.getString(0)+" Tarih: "+cursor.getString(2);}
        }

        view_birinci.setText(birinci);
        view_ikinci.setText(ikinci);
        view_ucuncu.setText(ucuncu);



    }


}