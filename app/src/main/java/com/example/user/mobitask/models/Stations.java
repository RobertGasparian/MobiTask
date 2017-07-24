package com.example.user.mobitask.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stations {

    @SerializedName("from")
    @Expose
    private List<Object> from = null;
    @SerializedName("to")
    @Expose
    private List<Object> to = null;

    public List<Object> getFrom() {
        return from;
    }

    public void setFrom(List<Object> from) {
        this.from = from;
    }

    public List<Object> getTo() {
        return to;
    }

    public void setTo(List<Object> to) {
        this.to = to;
    }

}