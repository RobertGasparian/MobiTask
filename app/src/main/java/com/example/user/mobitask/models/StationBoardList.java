package com.example.user.mobitask.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StationBoardList {

    @SerializedName("stationboard")
    @Expose
    private List<Stationboard> stationboard = null;

    public List<Stationboard> getStationboard() {
        return stationboard;
    }

    public void setStationboard(List<Stationboard> stationboard) {
        this.stationboard = stationboard;
    }

}