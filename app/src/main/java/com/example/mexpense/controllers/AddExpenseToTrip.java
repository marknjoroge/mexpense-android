package com.example.mexpense.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mexpense.R;
import com.example.mexpense.connections.DBHandler;

public class AddExpenseToTrip extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense_to_trip);

        Intent intent = getIntent();
        int tripId = intent.getIntExtra("tripId", 0);

        EditText expenseOn = findViewById(R.id.expenseOn);
        EditText amountSpent = findViewById(R.id.amountSpent);
        EditText date2 = findViewById(R.id.dateOfExpense);
        EditText expenseComments = findViewById(R.id.expenseComments);

        TextView error = findViewById(R.id.fillError2);

        Button submit = findViewById(R.id.submitExpense);

        submit.setOnClickListener(v -> {
            String on = expenseOn.getText().toString();
            String amount = amountSpent.getText().toString();
            String date = date2.getText().toString();
            String comments = expenseComments.getText().toString();

            if (on.equals("") || amount.equals("") || date.equals(""))
                error.setVisibility(View.VISIBLE);
            else
                postAndContinue(on, tripId, amount, date, comments);
        });
    }

    void postAndContinue(String on, int tripId, String amount, String date, String comments) {

        DBHandler dbHandler = new DBHandler(this);
        dbHandler.insertExpense(on, tripId, amount, date, comments);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}