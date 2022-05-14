package com.example.mexpense.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.mexpense.R;
import com.example.mexpense.connections.DBHandler;
import com.example.mexpense.models.TripDetails;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        updateSearch("");

        ImageButton searchTrip = findViewById(R.id.searchTrip);
        EditText searchTxt = findViewById(R.id.searchTxt);

        searchTrip.setOnClickListener(v -> {
            updateSearch(searchTxt.getText().toString());
        });
    }

    public void updateSearch(String txt) {
        DBHandler dbHandler = new DBHandler(this);
        ArrayList<TripDetails> tripDetailsArrayList = dbHandler.searchDetails(txt);

        TripsAdapter tripsAdapter = new TripsAdapter(this, 0, tripDetailsArrayList);

        ListView trips = findViewById(R.id.searchList);

        trips.setAdapter(tripsAdapter);
    }
}