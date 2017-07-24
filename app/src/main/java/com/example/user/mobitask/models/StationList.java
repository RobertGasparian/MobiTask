package com.example.user.mobitask.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StationList {

    @SerializedName("stations")
    @Expose
    private List<Station> stations = null;

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

}