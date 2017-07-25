package com.example.user.mobitask.managers;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.example.user.mobitask.R;
import com.example.user.mobitask.adapters.TaskAdapter;
import com.example.user.mobitask.callbacks.DepartureTimeListener;
import com.example.user.mobitask.callbacks.RefreshCallback;
import com.example.user.mobitask.callbacks.StationListListener;
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

public class DepartureTimeManager implements StationListListener {


    private List<String> stations = new ArrayList<>();
    private List<Stationboard> stationBoardList = new ArrayList<>();
    private List<String> departureTimes = new ArrayList<>();
    private DepartureTimer departureTimer;
    private DepartureTimeListener timeListener;
    private int currentPosition = 0;
    private String currentStation;
    private RetrofitController retrofitController;
    private TimeManager timeManager;
    private Context context;
    private RefreshCallback refreshCallback;


    public void requestHandler() {

        timeManager = TimeManager.getTimeManager();
        retrofitController = RetrofitController.getRetrofitController();
        retrofitController.retrofitInit();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {


                if (currentPosition <= stationBoardList.size()) {
                    currentStation = stations.get(currentPosition++);
                    departureTimer.getDepartureTime(currentStation).enqueue(new Callback<StationBoardList>() {
                        @Override
                        public void onResponse(Call<StationBoardList> call, Response<StationBoardList> response) {
                            stationBoardList.addAll(response.body().getStationboard());
                            long time = timeManager.getDepartureTimeDelta(stationBoardList.get(currentPosition).getStop().getDepartureTimestamp());
                            String currentTime = timeManager.convert(time);
                            departureTimes.add(currentTime);
                            timeListener.setDepartureTimes(departureTimes);
                            requestHandler();


                        }

                        @Override
                        public void onFailure(Call<StationBoardList> call, Throwable t) {

                            Toast.makeText(context, R.string.inet_fail,Toast.LENGTH_SHORT).show();


                        }
                    });


                }
                else {
                    currentPosition=0;
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                          refreshCallback.onRefreshCallback();
                        }
                    },30000);
                }
            }
        }, 340);


    }


    @Override
    public void getStationList(List<String> list, Context context) {
        stations = list;
        this.context=context;
        requestHandler();
    }
}
