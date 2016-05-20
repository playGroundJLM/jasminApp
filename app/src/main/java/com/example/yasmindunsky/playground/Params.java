package com.example.yasmindunsky.playground;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


public class Params extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.yasmindunsky.playground.MESSAGE";

    public final int WATER = 0;
    public final int PARKS = 1;
    public final int STAIRS = 2;
    public int incline = 0;
    public boolean[] pref = {false, false, false};
    private JSONObject to_server;
    private JSONObject from_server;
    private String from_server_string = null;
    private String server_url = "http://13.69.188.203/tracks";
    Intent intent;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

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

        NumberPicker np = (NumberPicker) findViewById(R.id.numberPicker);
        np.setMaxValue(10);
        np.setMinValue(1);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void setIncline(View view) {
        incline = (incline + 1) % 3;
        ImageButton button = (ImageButton) findViewById(R.id.incline);
        switch (incline) {
            case 0: {
                view.setSelected(false);
                view.setFocusable(false);
            }

            case 1: {
                view.setSelected(true);
                view.setFocusable(false);
            }

            case 2: {
                view.setSelected(true);
                view.setFocusable(true);
            }
        }
        if (pref[WATER]) {
            // water was already selected, now change state to un-select
            pref[WATER] = false;
        } else {
            pref[WATER] = true;
        }
        view.setSelected(pref[WATER]);
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

    public void sendData(View view) {
        view.setSelected(true);
        NumberPicker np = (NumberPicker) findViewById(R.id.numberPicker);
        int distance = np.getValue();

//        final String jsonString = "{\"USER_SELECTIONS\":[{\"dist\":" + distance + "},{\"incline\":" + incline + 1
//                + "},{\"water\":" + pref[WATER] + "},{\"stairs\":" + pref[STAIRS] + "},{\"facilities\":" + pref[PARKS] +
//                "},{\"lat\":" + distance + "},{\"long\":" + distance + "}]}";

//        final String jsonString = "{\"dist\":" + distance + "},{\"incline\":" + incline + 1
//                + "},{\"water\":" + pref[WATER] + "},{\"stairs\":" + pref[STAIRS] + "},{\"facilities\":" + pref[PARKS] +
//                "},{\"lat\":" + distance + "},{\"long\":" + distance + "}";

        final String jsonString = "{\"dist\":" + distance + ", \"incline\":" + (incline + 1)
                + ",\"water\":" + pref[WATER] + ",\"stairs\":" + pref[STAIRS] + ",\"facilities\":" + pref[PARKS] +
                ",\"lat\":" + 31.777000 + ",\"long\":" + 35.234555 + "}";

        try {
            to_server = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        new JSONTask().execute(server_url);


        intent = new Intent(this, PreRun.class);
//        intent.putExtra(EXTRA_MESSAGE, from_server_string);
//        new AsyncTask<Void,Void,String>(){
//            @Override
//            protected String doInBackground(Void... params) {
//                return getServerResponse(jsonString);
//            }
//
//            @Override
//            protected void onPostExecute(String result) {
//                // sdlkvkmsdlkvmdslvklsmdlvkmdsvlksdmvkdsmvlkdsv סתם שמתי כאן תז שקיים בעמוד
//                TextView output = (TextView) findViewById(R.id.prefText);
//                output.setText(result);
//            }
//        }.execute();
    }


    public class JSONTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {

            HttpURLConnection connection = null;

            try {

                // Setting connection
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                connection.setRequestMethod("POST");
                connection.connect();

                // Sending output
                OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());

                String output = to_server.toString();
                System.out.println("Sending: " + output);
                writer.write(output);
                writer.flush();
                writer.close();

                // Getting respond
                InputStream input = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                StringBuilder result = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

                return result.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
            return null;
        }

//    private String getServerResponse(String json){
//
//        HttpClient post = new HttpClient();
//        return null;
//    }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);


            try {
                System.out.println("Got back: " + result);
                from_server = new JSONObject(result);
//                JSONArray CS = from_server.getJSONArray("results");
                from_server_string = from_server.toString();
                intent.putExtra(EXTRA_MESSAGE, from_server_string);
                startActivity(intent);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}