package com.ertugrulkoc.hafzaoyunu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Intent intent;
    Button kolay_button, orta_button, zor_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        kolay_button = findViewById(R.id.button_Kolay);
        orta_button = findViewById(R.id.button_Orta);
        zor_button = findViewById(R.id.button_Zor);

        kolay_button.setOnClickListener(MainActivity.this);
        orta_button.setOnClickListener(MainActivity.this);
        zor_button.setOnClickListener(MainActivity.this);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_Kolay:
                istekOlustur(KolayActivity.class);
                break;
            case R.id.button_Orta:istekOlustur(OrtaActivity.class);
            break;
            case R.id.button_Zor:
                istekOlustur(ZorActivity.class);
                break;
        }
    }

    private void istekOlustur(Class ornekSinif){
        intent = new Intent(this.getApplicationContext(),ornekSinif);
        startActivity(intent);
    }
}