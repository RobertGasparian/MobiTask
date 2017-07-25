package com.example.user.mobitask.managers;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.example.user.mobitask.R;
import com.example.user.mobitask.callbacks.DepartureTimeListener;
import com.example.user.mobitask.callbacks.RefreshCallback;
import com.example.user.mobitask.models.StationBoardList;
import com.example.user.mobitask.models.Stationboard;
import com.example.user.mobitask.retrofit_interfaces.DepartureTimer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by User on 7/24/2017.
 */

public class DepartureTimeManager {


    private List<String> stations = new ArrayList<>();
    private List<Stationboard> stationBoardList = new ArrayList<>();
    private DepartureTimer departureTimer;
    private DepartureTimeListener timeListener;
    private int currentPosition = 0;
    private String currentStation;
    private RetrofitController retrofitController;
    private TimeManager timeManager;
    private Context context;
    private List<RefreshCallback> refreshCallbacks=new ArrayList<>();
    private static DepartureTimeManager instance=null;



    private DepartureTimeManager(){
    }


    public static DepartureTimeManager getDepartureTimeManager(){

        if(instance==null){
            instance=new DepartureTimeManager();
        }

        return instance;
    }


    public void requestHandler() {

        timeManager = TimeManager.getTimeManager();
        retrofitController = RetrofitController.getRetrofitController();
        retrofitController.retrofitInit();
        departureTimer=retrofitController.getmRetrofit().create(DepartureTimer.class);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (currentPosition < stations.size()&&stations.size()!=0) {
                    currentStation = stations.get(currentPosition);
                    departureTimer.getDepartureTime(currentStation).enqueue(new Callback<StationBoardList>() {
                        @Override
                        public void onResponse(Call<StationBoardList> call, Response<StationBoardList> response) {
                            stationBoardList.addAll(response.body().getStationboard());
                            long time = timeManager.getDepartureTimeDelta(stationBoardList.get(currentPosition).getStop().getDepartureTimestamp());
                            String currentTime = timeManager.convert(time);
                            timeListener.setDepartureTimes(currentTime);
                            currentPosition++;
                            requestHandler();


                        }

                        @Override
                        public void onFailure(Call<StationBoardList> call, Throwable t) {

                            Toast.makeText(context, R.string.inet_fail,Toast.LENGTH_SHORT).show();


                        }
                    });


                }
                else {
                    Handler handler=new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            for (RefreshCallback refreshCallback: refreshCallbacks
                                 ) {
                                refreshCallback.onRefreshCallback();
                            }
                        }
                    },10000);
                    currentPosition=0;
                }
            }
        }, 340);


    }



    public void getStationList(List<String> list, Context context) {
        stations = list;
        this.context=context;
    }

    public void addRefreshCallback(RefreshCallback refreshCallback) {
        this.refreshCallbacks.add(refreshCallback);
    }

    public void setTimeListener(DepartureTimeListener timeListener) {
        this.timeListener = timeListener;
    }
}
