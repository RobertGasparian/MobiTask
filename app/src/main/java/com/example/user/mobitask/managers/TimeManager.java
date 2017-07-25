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
        long delta=(departureTime*1000)-currentMillis;
        return delta;

    }



    public String convert(long timeStamp){

        long timeStampSeconds=timeStamp;

        long minute=60;
        long hour=3600;

        long currentMinute=0,currentHour=0, currentSeconds=0;

        currentHour=timeStampSeconds/hour;
        currentMinute=(timeStampSeconds%hour)/minute;
        currentSeconds=(timeStampSeconds%hour)%minute;

        String convertedTime="";
        if(currentHour!=0){
            convertedTime=convertedTime+currentHour+" hour(s) ";
        }

        if(currentMinute!=0){
            convertedTime=convertedTime+currentMinute+" minute(s) ";
        }
        if(currentHour!=0){
            convertedTime=convertedTime+currentSeconds+" second(s)";
        }

        return convertedTime;
    }
}
