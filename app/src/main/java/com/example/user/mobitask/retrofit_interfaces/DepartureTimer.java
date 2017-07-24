package com.example.user.mobitask.retrofit_interfaces;

import com.example.user.mobitask.models.StationBoardList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by User on 7/24/2017.
 */

public interface DepartureTimer {

    @GET("/v1/stationboard?limit=1")
    Call<StationBoardList>getDepartureTime(@Query("station") String station);
}
