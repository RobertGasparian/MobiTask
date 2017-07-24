package com.example.user.mobitask.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Connections {

    @SerializedName("connections")
    @Expose
    private List<Object> connections = null;
    @SerializedName("from")
    @Expose
    private Object from;
    @SerializedName("to")
    @Expose
    private Object to;
    @SerializedName("stations")
    @Expose
    private Stations stations;

    public List<Object> getConnections() {
        return connections;
    }

    public void setConnections(List<Object> connections) {
        this.connections = connections;
    }

    public Object getFrom() {
        return from;
    }

    public void setFrom(Object from) {
        this.from = from;
    }

    public Object getTo() {
        return to;
    }

    public void setTo(Object to) {
        this.to = to;
    }

    public Stations getStations() {
        return stations;
    }

    public void setStations(Stations stations) {
        this.stations = stations;
    }

}