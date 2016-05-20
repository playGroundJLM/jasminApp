package com.example.yasmindunsky.playground;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Go extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go);

        TextView dist = (TextView) findViewById(R.id.dist);
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Bold.ttf");
        dist.setTypeface(face);
        Intent intent = getIntent();
        String message = intent.getStringExtra(PreRun.EXTRA_MESSAGE);
        dist.setText(message);
    }
}
