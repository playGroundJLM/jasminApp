package com.example.yasmindunsky.playground;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Params extends AppCompatActivity {
    public final int WATER = 0;
    public final int PARKS = 1;
    public final int STAIRS = 2;
    public int incline = 0;
    public boolean[] pref = {false, false, false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_params);

        TextView inclineTitle = (TextView) findViewById(R.id.inclineTitle);
        TextView distanceTitle = (TextView) findViewById(R.id.distanceTitle);
        TextView paramTitle = (TextView) findViewById(R.id.paramTitle);
        TextView prefText = (TextView) findViewById(R.id.prefText);

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Bold.ttf");

        inclineTitle.setTypeface(face);
        distanceTitle.setTypeface(face);
        paramTitle.setTypeface(face);
        prefText.setTypeface(face);
    }

    public void setIncline(View view) {
        incline = (incline + 1) % 3;
        ImageButton button = (ImageButton) findViewById(R.id.incline);
        switch (incline){
            case 0:
            {
                button.setImageResource(R.drawable.incline_choose_1);
            }

            case 1:
            {
                button.setImageResource(R.drawable.incline_choose_2);            }

            case 2:
            {
                button.setImageResource(R.drawable.incline_choose_3);
            }
        }
    }

    public void waterSelection(View view) {
        if (pref[WATER]) {
            // water was already selected, now change state to un-select
            pref[WATER] = false;
        } else {
            pref[WATER] = true;
        }
        view.setSelected(pref[WATER]);
    }

    public void parkSelection(View view) {
        if (pref[PARKS]) {
            // water was already selected, now change state to un-select
            pref[PARKS] = false;
        } else {
            pref[PARKS] = true;
        }
        view.setSelected(pref[PARKS]);
    }

    public void stairSelection(View view) {
        if (pref[STAIRS]) {
            // water was already selected, now change state to un-select
            pref[STAIRS] = false;
        } else {
            pref[STAIRS] = true;
        }
        view.setSelected(pref[STAIRS]);
    }
}