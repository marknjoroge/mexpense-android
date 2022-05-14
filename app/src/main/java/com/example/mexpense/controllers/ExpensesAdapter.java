package com.example.mexpense.controllers;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.mexpense.R;
import com.example.mexpense.models.Expenses;
import com.example.mexpense.models.TripDetails;

import java.util.List;

public class ExpensesAdapter extends ArrayAdapter<Expenses> {
    public ExpensesAdapter(@NonNull Context context, int resource, @NonNull List<Expenses> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Expenses expenses = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_expenses, parent, false);
        }

        TextView expenseOnCard = convertView.findViewById(R.id.expenseOnCard);
        TextView expenseDateCard = convertView.findViewById(R.id.expenseDateCard);
        TextView expenseAmntCard = convertView.findViewById(R.id.expenseAmntCard);
        TextView expenseCommentsCard = convertView.findViewById(R.id.expenseCommentsCard);


        expenseCommentsCard.setText(expenses.getComments());
        expenseAmntCard.setText(expenses.getAmount());
        expenseDateCard.setText(expenses.getTime());
        expenseOnCard.setText(expenses.getType());

        return convertView;
    }
}
