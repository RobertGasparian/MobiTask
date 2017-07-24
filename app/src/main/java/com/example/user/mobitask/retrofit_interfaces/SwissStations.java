package com.example.user.mobitask.retrofit_interfaces;

import com.example.user.mobitask.models.StationList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by User on 7/24/2017.
 */

public interface SwissStations {

    @GET("v1/locations?")
    Call<StationList> getList(@Query("stations") String stations);
}
