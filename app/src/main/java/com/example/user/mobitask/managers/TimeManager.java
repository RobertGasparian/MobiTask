package com.example.user.mobitask.managers;

/**
 * Created by User on 7/24/2017.
 */

public class TimeManager {


    private static TimeManager instance=null;

    private TimeManager() {
    }

    public static TimeManager getTimeManager(){

        if(instance==null){
            instance=new TimeManager();
        }

        return instance;

    }

    public long getDepartureTimeDelta(long departureTime){


        long currentMillis=System.currentTimeMillis();
        long delta=departureTime-currentMillis;
        return delta;

    }

    public long getNextDeparture(long...delta){

        long minDelta=0;

        for (int i = 0; i < delta.length; i++) {

            if(minDelta>delta[i]){
                minDelta=delta[i];
            }

        }

        return minDelta;

    }

    public String convert(long timeStamp){

        long minute=60;
        long hour=3600;

        long currentMinute=0,currentHour=0, currentSecontds=0;

        currentHour=timeStamp/hour;
        currentMinute=(timeStamp%hour)/minute;
        currentSecontds=(timeStamp%hour)%minute;

        String convertedTime="";
        if(currentHour!=0){
            convertedTime=convertedTime+currentHour+" hour(s)";
        }

        if(currentMinute!=0){
            convertedTime=convertedTime+currentMinute+" minute(s)";
        }
        if(currentHour!=0){
            convertedTime=convertedTime+currentSecontds+" second(s)";
        }

        return convertedTime;
    }
}
