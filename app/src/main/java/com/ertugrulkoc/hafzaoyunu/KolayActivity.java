package com.ertugrulkoc.hafzaoyunu;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KolayActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton b1;
    List<Integer> images;
    List<ImageButton> buttons;
    GridLayout gridLayout;
    List<Integer> rnd_list;
    List<ImageButton> usesButtons;
    List<Integer> usesImages;
    int tiklamaAdet = 0;
    int sayi;
    List<Integer> varolanlar;
    Random rnd;
    List<Integer> uretilenler;
    int c = 0;
    private Object childView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kolay);
        b1 = findViewById(R.id.image1);
        varolanlar = new ArrayList<>();
        usesImages = new ArrayList<>();
        usesButtons = new ArrayList<>();
        rnd_list = new ArrayList<>();
        gridLayout = findViewById(R.id.kolay_gridView);
        images = new ArrayList<>();
        buttons = new ArrayList<>();
        animalInitilatizions();
        buttonInitilatizions();
        rnd = new Random();
        uretilenler = new ArrayList<>();
        createRandomNumber();
        buttonImageAdd();
        listenerInitilatizion();

    }

    private void createRandomNumber() {
        for (int i = 0; i < 6; i++) {
            sayi = rnd.nextInt(3);
            while (varolanlar.contains(sayi)) {
                sayi = rnd.nextInt(3);
            }
            if (varolanlar.size()<4){
                if (!varolanlar.contains(sayi)) {
                    varolanlar.add(sayi);
                }
            }else {
                uretilenler=varolanlar;
                varolanlar.clear();
            }
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
           // buttons.get(i).setImageResource(images.get(uretilenler.get(i)));
        }

    }

    @Override
    public void onClick(View view) {
        if (tiklamaAdet < 2) {
            for (int i = 0; i < buttons.size(); i++) {
                if (view instanceof ImageButton) {
                    if (view.getId() == buttons.get(i).getId()) {
                        //    buttons.get(i).setImageResource(images.get(createRandomNumber()));
                        //  tiklamaAdet++;
                    }
                }
            }
        } else {
            cardClosing();
            tiklamaAdet = 0;
        }
    }


}

