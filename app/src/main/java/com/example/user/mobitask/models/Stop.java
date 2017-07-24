package com.example.user.mobitask.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stop {

    @SerializedName("station")
    @Expose
    private Station station;
    @SerializedName("arrival")
    @Expose
    private Object arrival;
    @SerializedName("arrivalTimestamp")
    @Expose
    private Object arrivalTimestamp;
    @SerializedName("departure")
    @Expose
    private String departure;
    @SerializedName("departureTimestamp")
    @Expose
    private Long departureTimestamp;
    @SerializedName("platform")
    @Expose
    private String platform;
    @SerializedName("prognosis")
    @Expose
    private Prognosis prognosis;

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Object getArrival() {
        return arrival;
    }

    public void setArrival(Object arrival) {
        this.arrival = arrival;
    }

    public Object getArrivalTimestamp() {
        return arrivalTimestamp;
    }

    public void setArrivalTimestamp(Object arrivalTimestamp) {
        this.arrivalTimestamp = arrivalTimestamp;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public Long getDepartureTimestamp() {
        return departureTimestamp;
    }

    public void setDepartureTimestamp(Long departureTimestamp) {
        this.departureTimestamp = departureTimestamp;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Prognosis getPrognosis() {
        return prognosis;
    }

    public void setPrognosis(Prognosis prognosis) {
        this.prognosis = prognosis;
    }

}