package com.example.yasmindunsky.playground;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import org.json.*;
import org.w3c.dom.Text;

public class PreRun extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.yasmindunsky.playground.DISTANCE";

    String dist = null;
    int incline = 0;
    boolean water = false;
    boolean power = false;
    boolean parks = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_run);

        TextView line = (TextView) findViewById(R.id.resultTitle);
        TextView distF1 = (TextView) findViewById(R.id.dist_1);
        TextView distF2 = (TextView) findViewById(R.id.dist_2);
        TextView distF3 = (TextView) findViewById(R.id.dist_3);

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Bold.ttf");
        line.setTypeface(face);
        distF1.setTypeface(face);
        distF2.setTypeface(face);
        distF3.setTypeface(face);

        Intent intent = getIntent();
        String message = intent.getStringExtra(Params.EXTRA_MESSAGE);
        JSONObject jsnMessage = null;
        try {
            jsnMessage = new JSONObject(message);
            JSONArray results = (JSONArray) jsnMessage.get("results");

            JSONObject firstResult = results.getJSONObject(0);
            updateParams(firstResult);
            TextView textDist_1 = (TextView) findViewById(R.id.dist_1);
            textDist_1.setText(dist + " KM");

            JSONObject secondResult = results.getJSONObject(1);
            updateParams(secondResult);
            TextView textDist_2 = (TextView) findViewById(R.id.dist_2);
            textDist_2.setText(dist + " KM");

            JSONObject thirdResult = results.getJSONObject(2);
            updateParams(thirdResult);
            TextView textDist_3 = (TextView) findViewById(R.id.dist_3);
            textDist_3.setText(dist + " KM");

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public void run(View view) {
        Intent intent = new Intent(this, Go.class);
        intent.putExtra(EXTRA_MESSAGE, dist);
        startActivity(intent);
    }

    public void updateParams(JSONObject result){
        try {
            dist = Double.toString((double)result.get("dist")).substring(0,4);
            incline = (int)result.get("incline") - 1;
            water = (boolean)result.get("water");
            power = (boolean)result.get("power");
            parks = (boolean)result.get("facilities");


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
