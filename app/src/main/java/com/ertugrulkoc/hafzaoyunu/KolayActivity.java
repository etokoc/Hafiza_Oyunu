package com.ertugrulkoc.hafzaoyunu;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class KolayActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton b1;
    List<Integer> images;
    List<ImageButton> buttons;
    GridLayout gridLayout;
    private Object childView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kolay);
        b1 = findViewById(R.id.image1);
        gridLayout = findViewById(R.id.kolay_gridView);
        images = new ArrayList<>();
        buttons = new ArrayList<>();
        animalInitilatizions();
        buttonInitilatizions();
        listenerInitilatizion();{
        }
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

    private void animalInitilatizions() {
        images.add(R.drawable.bee);
        images.add(R.drawable.horse);
        images.add(R.drawable.chicken);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(this, "12312", Toast.LENGTH_SHORT).show();
    }
}