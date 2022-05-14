package com.example.mexpense.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.mexpense.R;
import com.example.mexpense.connections.DBHandler;
import com.example.mexpense.models.Expenses;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AllExpenses extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_expenses);

        int tripId = getIntent().getIntExtra("tripId", 0);

        DBHandler dbHandler = new DBHandler(this);
        ArrayList<Expenses> expensesArrayList = dbHandler.getAllExpenses(tripId);

        ExpensesAdapter expensesAdapter = new ExpensesAdapter(this, 0, expensesArrayList);

        ListView expenses = findViewById(R.id.expensesListView);

        expenses.setAdapter(expensesAdapter);

        FloatingActionButton addExpense = findViewById(R.id.addExpenseBtn);

        addExpense.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddExpenseToTrip.class);
            intent.putExtra("tripID", tripId);
            startActivity(intent);
        });
    }
}