package com.example.mexpense.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mexpense.R;
import com.example.mexpense.connections.DBHandler;
import com.example.mexpense.models.TripDetails;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class ViewTripEntry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_trip_entry);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);

        DBHandler dbHandler = new DBHandler(this);
        TripDetails tripDetails = new TripDetails();

        tripDetails = dbHandler.getSingleTrip(id);

        TextView nameT = findViewById(R.id.name);
        TextView dest = findViewById(R.id.destination);
        TextView dateT = findViewById(R.id.date);
        TextView markFun = findViewById(R.id.markAsFun);
        TextView markNoFun = findViewById(R.id.markeAsNotFun);
        TextView leis = findViewById(R.id.leisure);
        TextView bus = findViewById(R.id.business);
        TextView descript = findViewById(R.id.description);
        ImageView yesAssess = findViewById(R.id.yesRiskAssessment);
        ImageView noAssess = findViewById(R.id.noRiskAssessment);

        FloatingActionButton expenses = findViewById(R.id.expensesBtn);
        FloatingActionButton deleteEntry = findViewById(R.id.deleteTripEntry);

        nameT.setText(tripDetails.getName());
        dest.setText(tripDetails.getDestination());
        dateT.setText(tripDetails.getDate());
        descript.setText(tripDetails.getDescription());

        if (Objects.equals(tripDetails.getWasItFun(), "yes"))
            markFun.setVisibility(View.VISIBLE);
        else if (Objects.equals(tripDetails.getWasItFun(), "no"))
            markNoFun.setVisibility(View.VISIBLE);

        if (Objects.equals(tripDetails.getTypeOfTrip(), "leisure"))
            leis.setVisibility(View.VISIBLE);
        else if (Objects.equals(tripDetails.getTypeOfTrip(), "business"))
            bus.setVisibility(View.VISIBLE);

        if (Objects.equals(tripDetails.getRequiresRiskAssessment(), "yes"))
            yesAssess.setVisibility(View.VISIBLE);
        else if (Objects.equals(tripDetails.getRequiresRiskAssessment(), "no"))
            noAssess.setVisibility(View.VISIBLE);

        TripDetails finalTripDetails = tripDetails;
        deleteEntry.setOnClickListener(v -> {
            dbHandler.deleteTripDetails(finalTripDetails.getId());
            dbHandler.deleteExpense(finalTripDetails.getId());
            Toast.makeText(this, "deleted successfully", Toast.LENGTH_LONG).show();

            Intent intent1 = new Intent(this, MainActivity.class);
            startActivity(intent1);
        });

        TripDetails finalTripDetails1 = tripDetails;
        expenses.setOnClickListener(v -> {
            Intent intent1 = new Intent(this, AllExpenses.class);
            intent1.putExtra("id", finalTripDetails1.getId());
            startActivity(intent1);
        });
    }
}