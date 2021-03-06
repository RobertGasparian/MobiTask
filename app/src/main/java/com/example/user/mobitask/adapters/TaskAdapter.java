package com.example.user.mobitask.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.user.mobitask.R;
import com.example.user.mobitask.callbacks.DepartureTimeListener;
import com.example.user.mobitask.callbacks.RefreshCallback;
import com.example.user.mobitask.managers.DepartureTimeManager;
import com.example.user.mobitask.managers.MapManager;
import com.example.user.mobitask.models.Station;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder> implements DepartureTimeListener,RefreshCallback{


    private List<Station> mList;
    private List<String> mTimeList;
    private Context mContext;
    private MapManager mMapManager;
    private DepartureTimeManager departureTimeManager;


    public TaskAdapter(List<Station> mList, Context mContext) {

        this.mList = mList;
        this.mContext = mContext;
        mTimeList=new ArrayList<>();
        departureTimeManager=DepartureTimeManager.getDepartureTimeManager();
        departureTimeManager.setTimeListener(this);
        departureTimeManager.addRefreshCallback(this);
    }

    @Override
    public TaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(mContext).inflate(R.layout.rv_item,parent,false);
        return new TaskHolder(view);

    }

    @Override
    public void onBindViewHolder(TaskHolder holder, int position) {

        Station station=mList.get(position);

        mMapManager=MapManager.getMapManager();
        String url=mMapManager.getMapImage(station.getCoordinate().getX().toString(),
                station.getCoordinate().getY().toString());
        Picasso.with(mContext)
                .load(url)
                .into(holder.mapImage);

        holder.stationName.setText(station.getName());
        if(position<mTimeList.size()&&mTimeList.size()!=0){
            holder.time.setText(mTimeList.get(position));
        }






    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void setDepartureTimes(String times) {

        mTimeList.add(times);
        notifyDataSetChanged();
    }

    @Override
    public void onRefreshCallback() {
        mTimeList.clear();
    }

    class TaskHolder extends RecyclerView.ViewHolder{


        private ImageView mapImage;
        private TextView stationName, time;

        public TaskHolder(View itemView) {
            super(itemView);

            mapImage=(ImageView)itemView.findViewById(R.id.station_map);
            stationName=(TextView)itemView.findViewById(R.id.current_station_name);
            time=(TextView)itemView.findViewById(R.id.next_departure);
        }
    }
}
