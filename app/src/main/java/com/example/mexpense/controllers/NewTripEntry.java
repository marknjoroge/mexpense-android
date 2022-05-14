package com.example.mexpense.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.mexpense.R;
import com.example.mexpense.connections.DBHandler;

public class NewTripEntry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_trip_entry);

        Button submitEntryBtn = findViewById(R.id.submitTripEntryButton);

        TextView fillError = findViewById(R.id.tripFillAllError);

        EditText tripName = findViewById(R.id.tripName);
        EditText tripDestination = findViewById(R.id.tripDestination);
        EditText tripDate = findViewById(R.id.tripDate);
        EditText tripDescription = findViewById(R.id.tripDescription);

        RadioGroup tripReqAss = findViewById(R.id.tripRequiresAssessmentRadioGroup);

        RadioGroup typeOfTrip = findViewById(R.id.typeOfTripRadio);

        RadioGroup tripFunRadio = findViewById(R.id.tripFunRadio);


        submitEntryBtn.setOnClickListener(v -> {
            String name = tripName.getText().toString();
            String dest = tripDestination.getText().toString();
            String date = tripDate.getText().toString();
            String desc = tripDescription.getText().toString();

            int fun = tripFunRadio.getCheckedRadioButtonId();
            int type = typeOfTrip.getCheckedRadioButtonId();
            int assess = tripReqAss.getCheckedRadioButtonId();



            String funS = fun == R.id.tripFunButton ? "yes" : fun == R.id.tripNotFunButton ? "no" : "";
            String typeS = type == R.id.leisureTripButton ? "leisure" : type == R.id.businessTripButton ? "business" : "";
            String assessS = assess == R.id.assessmentYes ? "yes" : assess == R.id.assessmentNo ? "no" : "";

            if (name.equals("") || dest.equals("") || date.equals("") || desc.equals("") || assessS.equals("") )
                fillError.setVisibility(View.VISIBLE);
            else
                postAndContinue(name, dest, date, desc, funS, typeS, assessS);
        });
    }

    void postAndContinue(String name, String dest, String date, String desc,
                         String fun, String type, String assess) {

        DBHandler dbHandler = new DBHandler(this);
        dbHandler.insertTripEntry(name, dest, date, assess, desc, type, fun);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}