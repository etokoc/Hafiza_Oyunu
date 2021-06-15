package com.ertugrulkoc.hafzaoyunu;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

public class KolayActivity extends AppCompatActivity implements View.OnClickListener {
    List<Integer> images;
    List<ImageButton> buttons;
    GridLayout gridLayout;
    int tiklamaAdet = 0;
    int sayi = 0;
    List<Integer> varolanlar;
    Random rnd;
    Hashtable<ImageButton, Integer> buttonVeri;
    private Object childView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kolay);
        buttonVeri = new Hashtable<>();
        varolanlar = new ArrayList<>();
        gridLayout = findViewById(R.id.kolay_gridView);
        images = new ArrayList<>();
        buttons = new ArrayList<>();
        animalInitilatizions();
        buttonInitilatizions();
        rnd = new Random();
        sayi = 0;
        createRandomNumber();
        buttonImageAdd();
        listenerInitilatizion();

    }

    private void createRandomNumber() {
        varolanlar.clear();
        int adet = 3;
        for (int i = 0; i < adet; i++) {
            sayi = rnd.nextInt(adet);
            while (varolanlar.contains(sayi)) {
                sayi = rnd.nextInt(adet);
            }
            varolanlar.add(sayi);
        }
        Log.i("uretilen", "" + varolanlar);
    }

    private void listenerInitilatizion() {
        for (ImageButton button : buttons) {
            button.setOnClickListener(this);
        }
    }

    private void buttonInitilatizions() {
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            childView = gridLayout.getChildAt(i);
            if (childView instanceof ImageButton) {
                buttons.add((ImageButton) childView);
            }
        }
    }

    private void cardClosing() {
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setImageResource(R.drawable.brain);
        }
    }

    private void animalInitilatizions() {
        images.add(R.drawable.bee);
        images.add(R.drawable.horse);
        images.add(R.drawable.chicken);
    }

    private void buttonImageAdd() {
        for (int i = 0; i < buttons.size(); i++) {
            if (i <= 2) {
//                buttons.get(i).setImageResource(images.get(varolanlar.get(i)));
                buttonVeri.put(buttons.get(i), images.get(varolanlar.get(i)));
            } else {
                createRandomNumber();
                for (int a = i; a < buttons.size(); a++) {
//                    buttons.get(a).setImageResource(images.get(varolanlar.get(a - 3)));
                    buttonVeri.put(buttons.get(a), images.get(varolanlar.get(a - 3)));
                }
                break;
            }
        }

    }

    Integer[] secilenler= new Integer[3];
    @Override
    public void onClick(View view) {
        if (tiklamaAdet < 2) {
            for (int i = 0; i < buttons.size(); i++) {
                if (view instanceof ImageButton) {
                    if (view.getId() == buttons.get(i).getId()) {
                        tiklamaAdet++;
                        ((ImageButton) view).setImageResource(buttonVeri.get(buttons.get(i)));
                        buttons.get(i).setTag(buttonVeri.get(buttons.get(i)));
                        secilenler[tiklamaAdet]=buttonVeri.get(buttons.get(i));
                    }
                }
            }
        } else {
            checkCardImage();
            cardClosing();
            tiklamaAdet = 0;
        }
    }

    private void checkCardImage() {
        if (secilenler[1] == secilenler[2]){
            Toast.makeText(this, "ayni", Toast.LENGTH_SHORT).show();
        }
    }
}





