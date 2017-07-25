package com.example.user.mobitask.managers;

import com.example.user.mobitask.retrofit_interfaces.SwissStations;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by User on 7/25/2017.
 */

public class RetrofitController {

    private Retrofit mRetrofit;



    private static RetrofitController instance=null;

    private RetrofitController(){}

    public static RetrofitController getRetrofitController(){
        if(instance==null){
            instance=new RetrofitController();
        }

        return instance;
    }

    public void retrofitInit(){

        mRetrofit=new Retrofit.Builder()
                .baseUrl("http://transport.opendata.ch/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

}
