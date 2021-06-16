package com.ertugrulkoc.hafzaoyunu;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

public class KolayActivity extends AppCompatActivity implements View.OnClickListener {
    final Handler handler = new Handler();
    List<Integer> images;
    List<ImageButton> buttons;
    GridLayout gridLayout;
    int tiklamaAdet = 0;
    int sayi = 0;
    TextView hamleSayisiView;
    int hamle_sayisi = 0;
    List<Integer> varolanlar;
    Random rnd;
    Hashtable<ImageButton, Integer> buttonVeri;
    Integer[] secilenResimler = new Integer[3];
    ImageButton[] secilenButtonlar = new ImageButton[3];
    private Object childView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kolay);
        hamleSayisiView = findViewById(R.id.txt_hamle);
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

    @Override
    public void onClick(View view) {
        if (tiklamaAdet < 2) {
            for (int i = 0; i < buttons.size(); i++) {
                if (view instanceof ImageButton) {
                    if (view.getId() == buttons.get(i).getId()) {
                        tiklamaAdet++;
                        ((ImageButton) view).setImageResource(buttonVeri.get(buttons.get(i)));
//                        buttons.get(i).setTag(buttonVeri.get(buttons.get(i)));
                        secilenResimler[tiklamaAdet] = buttonVeri.get(buttons.get(i));
                        secilenButtonlar[tiklamaAdet] = buttons.get(i);
                        if (tiklamaAdet == 2) {
                            if (secilenButtonlar[tiklamaAdet - 1].getId() != secilenButtonlar[tiklamaAdet].getId() ) {
                                checkCardImage();
                            }else{
                                tiklamaAdet = 0;
                            }
                        }else {
                            secilenButtonlar[tiklamaAdet].setEnabled(false);
                        }
                    }
                }
            }
        }
    }

    private void checkCardImage() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (secilenResimler[1] == secilenResimler[2]) {
                    secilenButtonlar[1].setEnabled(false);
                    secilenButtonlar[1].setVisibility(View.GONE);
                    secilenButtonlar[2].setEnabled(false);
                    secilenButtonlar[2].setVisibility(View.GONE);
                }else {
                    for(int a=0; a<buttons.size(); a++){
                        buttons.get(a).setEnabled(true);
                    }
                }
                cardClosing();
                tiklamaAdet = 0;
            }
        }, 1000);

        if (secilenResimler[1] != secilenResimler[2]) {
            hamle_sayisi++;
            hamleSayisiView.setText("" + hamle_sayisi);
        }
    }
}





