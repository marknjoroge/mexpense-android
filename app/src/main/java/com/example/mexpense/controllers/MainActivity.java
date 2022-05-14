package com.example.mexpense.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mexpense.R;
import com.example.mexpense.connections.DBHandler;
import com.example.mexpense.models.TripDetails;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHandler dbHandler = new DBHandler(this);
        ArrayList<TripDetails> tripDetailsArrayList = dbHandler.getAllTrips();

        TripsAdapter tripsAdapter = new TripsAdapter(this, 0, tripDetailsArrayList);

        ListView trips = findViewById(R.id.tripsListView);

        trips.setAdapter(tripsAdapter);

        FloatingActionButton newEntryBtn = findViewById(R.id.newTripEntryBtn);
        FloatingActionButton search = findViewById(R.id.searchBtn);
        FloatingActionButton send = findViewById(R.id.sendDataBtn);

        newEntryBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, NewTripEntry.class);
            startActivity(intent);
        });

        search.setOnClickListener(v -> {
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
        });

        send.setOnClickListener(v -> postDataUsingVolley("l", "k"));
    }


    private void postDataUsingVolley(String name, String job) {
        // url to post our data
        String url = "https://stuiis.cms.gre.ac.uk/COMP1424CoreWS/comp1424cw";

        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

        // on below line we are calling a string
        // request method to post the data to our API
        // in this we are calling a post method.
        StringRequest request = new StringRequest(Request.Method.POST, url, response -> {
            // inside on response method we are
            // hiding our progress bar
            // and setting data to edit text as empty

            // on below line we are displaying a success toast message.
            Toast.makeText(MainActivity.this, "Data added to API ", Toast.LENGTH_SHORT).show();
            try {
                // on below line we are parsing the response
                // to json object to extract data from it.
                JSONObject respObj = new JSONObject(response);

                // below are the strings which we
                // extract from our json object.
                String name1 = respObj.getString("name");
                String job1 = respObj.getString("job");

                // on below line we are setting this string s to our text view.
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Toast.makeText(MainActivity.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show()) {
            @Override
            protected Map<String, String> getParams() {
                // below line we are creating a map for
                // storing our values in key and value pair.
                Map<String, String> params = new HashMap<>();

                // on below line we are passing our key
                // and value pair to our parameters.
                params.put("name", name);
                params.put("job", job);

                // at last we are
                // returning our params.
                return params;
            }
        };

        queue.add(request);
    }
}