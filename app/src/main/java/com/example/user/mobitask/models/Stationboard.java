package com.example.user.mobitask.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stationboard {

    @SerializedName("stop")
    @Expose
    private Stop stop;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("operator")
    @Expose
    private String operator;
    @SerializedName("to")
    @Expose
    private String to;

    public Stop getStop() {
        return stop;
    }

    public void setStop(Stop stop) {
        this.stop = stop;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

}