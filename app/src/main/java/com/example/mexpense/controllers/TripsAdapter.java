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
import com.example.mexpense.models.TripDetails;

import java.util.List;

public class TripsAdapter extends ArrayAdapter<TripDetails> {
    public TripsAdapter(@NonNull Context context, int resource, @NonNull List<TripDetails> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TripDetails tripDetails = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_trip, parent, false);
        }

        ConstraintLayout layout = convertView.findViewById(R.id.cardLayout);

        TextView name = convertView.findViewById(R.id.cardName);
        TextView date = convertView.findViewById(R.id.cardDate);

        layout.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ViewTripEntry.class);
            intent.putExtra("id", tripDetails.getId());
            getContext().startActivity(intent);
        });

        name.setText(tripDetails.getName());
        date.setText(tripDetails.getDate());

        return convertView;
    }
}
