package com.example.mexpense.models;

import androidx.annotation.NonNull;

public class TripDetails {

    private int id;
    private String name;
    private String destination;
    private String date;
    private String requiresRiskAssessment;
    private String description;
    private String typeOfTrip;
    private String wasItFun;

    public TripDetails() {}
    public TripDetails(int id,
                       String name,
                       String destination,
                       String date,
                       String requiresRiskAssessment,
                       String description,
                       String typeOfTrip,
                       String wasItFun) {

        this.id  = id;
        this.name = name;
        this.date = date;
        this.destination = destination;
        this.requiresRiskAssessment = requiresRiskAssessment;
        this.description = description;
        this.typeOfTrip = typeOfTrip;
        this.wasItFun = wasItFun;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRequiresRiskAssessment() {
        return requiresRiskAssessment;
    }

    public void setRequiresRiskAssessment(String requiresRiskAssessment) {
        this.requiresRiskAssessment = requiresRiskAssessment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeOfTrip() {
        return typeOfTrip;
    }

    public void setTypeOfTrip(String typeOfTrip) {
        this.typeOfTrip = typeOfTrip;
    }

    public String getWasItFun() {
        return wasItFun;
    }

    public void setWasItFun(String wasItFun) {
        this.wasItFun = wasItFun;
    }

    @NonNull
    @Override
    public String toString() {
        return "TripDetails{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", destination='" + destination + '\'' +
                ", date='" + date + '\'' +
                ", requiresRiskAssessment='" + requiresRiskAssessment + '\'' +
                ", description='" + description + '\'' +
                ", typeOfTrip='" + typeOfTrip + '\'' +
                ", wasItFun='" + wasItFun + '\'' +
                '}';
    }
}
