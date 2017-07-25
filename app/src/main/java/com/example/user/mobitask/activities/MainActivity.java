package com.example.user.mobitask.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.user.mobitask.R;
import com.example.user.mobitask.adapters.TaskAdapter;
import com.example.user.mobitask.callbacks.RefreshCallback;
import com.example.user.mobitask.callbacks.StationListListener;
import com.example.user.mobitask.managers.RetrofitController;
import com.example.user.mobitask.models.Station;
import com.example.user.mobitask.models.StationList;
import com.example.user.mobitask.retrofit_interfaces.SwissStations;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements RefreshCallback {


    private RecyclerView mTaskRv;
    private TaskAdapter mAdapter;
    private List<Station> mList;
    private RetrofitController retrofitController;
    private SwissStations swissStations;
    private StationListListener stationListListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();


    }

    private void init(){


        retrofitController=RetrofitController.getRetrofitController();
        retrofitController.retrofitInit();
        mTaskRv=(RecyclerView)findViewById(R.id.task_rv);
        mList=new ArrayList<>();
        mList=getData();
        mAdapter=new TaskAdapter(mList,this);
        mTaskRv.setLayoutManager(new LinearLayoutManager(this));
        mTaskRv.setAdapter(mAdapter);


    }

    private List<Station> getData(){

        final List<Station> list=new ArrayList<>();
       swissStations.getList("Basel*").enqueue(new Callback<StationList>() {

            @Override
            public void onResponse(Call<StationList> call, Response<StationList> response) {

                list.addAll(response.body().getStations());


            }

            @Override
            public void onFailure(Call<StationList> call, Throwable t) {

                Toast.makeText(MainActivity.this,"Failure",Toast.LENGTH_SHORT).show();

            }
        });

        List<String> strings=new ArrayList<>();
        for (Station station :
                list) {
            strings.add(station.getName());
        }
        stationListListener.getStationList(strings, this);

        return list;

    }

    @Override
    public void onRefreshCallback() {
        mList.clear();
        mList=getData();
    }
}
