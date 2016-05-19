package com.example.yasmindunsky.playground;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getSelection(View view){
        int buttonId = view.getId();
        Intent intent = new Intent(this, MainActivity.class);;
        switch (buttonId){
            case R.id.run:
            {
                intent = new Intent(this, Params.class);
            }

            case R.id.bicycle:
            {
                break;
            }

            case R.id.power:
            {
                break;
            }
        }
        startActivity(intent);
    }
}
