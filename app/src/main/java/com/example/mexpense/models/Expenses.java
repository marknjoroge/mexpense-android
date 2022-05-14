package com.example.mexpense.models;

public class Expenses {

    private int id;
    private String type;
    private String amount;
    private String time;
    private String comments;
    private int tripID;

    public Expenses(int id, int tripID, String type, String amount, String time, String comments) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.time = time;
        this.comments = comments;
        this.tripID = tripID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getTripID() {
        return tripID;
    }

    public void setTripID(int tripID) {
        this.tripID = tripID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
