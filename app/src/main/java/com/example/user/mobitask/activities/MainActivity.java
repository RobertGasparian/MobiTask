package com.example.user.mobitask.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import com.example.user.mobitask.R;
import com.example.user.mobitask.adapters.TaskAdapter;
import com.example.user.mobitask.callbacks.RefreshCallback;
import com.example.user.mobitask.managers.DepartureTimeManager;
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
    private DepartureTimeManager departureTimeManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
       mTaskRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
           @Override
           public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
               super.onScrollStateChanged(recyclerView, newState);
           }

           @Override
           public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
               super.onScrolled(recyclerView, dx, dy);
           }
       });


    }

    private void init(){



        retrofitController=RetrofitController.getRetrofitController();
        retrofitController.retrofitInit();
        mTaskRv=(RecyclerView)findViewById(R.id.task_rv);
        mList=new ArrayList<>();
        swissStations=retrofitController.getmRetrofit().create(SwissStations.class);
        departureTimeManager=DepartureTimeManager.getDepartureTimeManager();
        departureTimeManager.addRefreshCallback(this);


        getData(new StationReceiveListener() {
            @Override
            public void onSuccess(List<Station> stations) {
                mList = stations;
                departureTimeManager.requestHandler();
                List<String> strings=new ArrayList<>();
                for (Station station :
                        mList) {
                    strings.add(station.getName());
                }
                departureTimeManager.getStationList(strings, MainActivity.this);

                mAdapter=new TaskAdapter(mList,MainActivity.this);
                mTaskRv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                mTaskRv.setAdapter(mAdapter);
            }

            @Override
            public void onFail(int code) {

                Toast.makeText(MainActivity.this,"Fail",Toast.LENGTH_SHORT).show();

            }
        });




    }



    private void getData(final StationReceiveListener stationReceiveListener){

        final List<Station> list=new ArrayList<>();
        swissStations.getList().enqueue(new Callback<StationList>() {

            @Override
            public void onResponse(Call<StationList> call, Response<StationList> response) {


                list.addAll(response.body().getStations());


                if (stationReceiveListener != null) {
                    stationReceiveListener.onSuccess(list);
                }


            }

            @Override
            public void onFailure(Call<StationList> call, Throwable t) {

                Toast.makeText(MainActivity.this,"Failure",Toast.LENGTH_SHORT).show();

                if (stationReceiveListener != null) {
                    stationReceiveListener.onFail(0);
                }
            }
        });


    }

    interface StationReceiveListener {
        void onSuccess(List<Station> stations);
        void onFail(int code);
    }

    @Override
    public void onRefreshCallback() {
        getData(new StationReceiveListener() {
            @Override
            public void onSuccess(List<Station> stations) {

                departureTimeManager.requestHandler();

            }

            @Override
            public void onFail(int code) {

                Toast.makeText(MainActivity.this,"Fail",Toast.LENGTH_SHORT).show();

            }
        });

    }


}
