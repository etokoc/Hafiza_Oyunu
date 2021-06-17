package com.ertugrulkoc.hafzaoyunu;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

public class ZorActivity extends AppCompatActivity implements View.OnClickListener {
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
    TextView hamleSonuc;
    private Object childView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zor);
        hamleSayisiView = findViewById(R.id.txt_hamle);
        buttonVeri = new Hashtable<>();
        varolanlar = new ArrayList<>();
        gridLayout = findViewById(R.id.zor_gridView);
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
        int adet = 8;
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
        images.add(R.drawable.cow);
        images.add(R.drawable.frog);
        images.add(R.drawable.monkey);
        images.add(R.drawable.dog);
        images.add(R.drawable.sheep);
    }

    private void buttonImageAdd() {
        for (int i = 0; i < buttons.size(); i++) {
            if (i <= 7) {
//                buttons.get(i).setImageResource(images.get(varolanlar.get(i)));
                buttonVeri.put(buttons.get(i), images.get(varolanlar.get(i)));
            } else {
                createRandomNumber();
                for (int a = i; a < buttons.size(); a++) {
//                    buttons.get(a).setImageResource(images.get(varolanlar.get(a - 3)));
                    buttonVeri.put(buttons.get(a), images.get(varolanlar.get(a-8 )));
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
                            if (secilenButtonlar[tiklamaAdet - 1].getId() != secilenButtonlar[tiklamaAdet].getId()) {
                                checkCardImage();
                            } else {
                                tiklamaAdet = 0;
                            }
                        } else {
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
                    buttons.remove(secilenButtonlar[1]);
                    buttons.remove(secilenButtonlar[2]);
                    if (buttons.size() < 1){
                        bildirimGoster(""+hamle_sayisi);
                    }
                } else {
                    for (int a = 0; a < buttons.size(); a++) {
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
    Dialog dialog;
    Button button_yeniOyun,button_oyundanCik,button_anaEkranaDon;
    Intent intent;
    private void bildirimGoster(String hamleSayisi) {
        veritabaninaKaydet();
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.item_dialog);
        button_anaEkranaDon =dialog.findViewById(R.id.button_AnaEkranaDon);
        button_yeniOyun=dialog.findViewById(R.id.button_yeniOyun);
        button_oyundanCik=dialog.findViewById(R.id.button_oyundanCik);
        hamleSonuc = dialog.findViewById(R.id.txt_hamleSonuc);
        hamleSonuc.setText(hamleSayisi + "");
        dialog.show();
        button_yeniOyun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(),ZorActivity.class);
                startActivity(intent);
            }
        });
        button_anaEkranaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        button_oyundanCik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });
    }

    DataHelper dataHelper;
    private void veritabaninaKaydet() {
        Date date = new Date();
        DateFormat dateFormat =new SimpleDateFormat("dd/MM/yyyy");
        dataHelper = new DataHelper(this.getApplicationContext());
        dataHelper.veriEkle(hamle_sayisi+"",dateFormat.format(date),"Zor");
    }
}





