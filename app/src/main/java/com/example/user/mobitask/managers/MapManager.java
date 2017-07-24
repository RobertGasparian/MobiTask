package com.example.user.mobitask.managers;

import com.example.user.mobitask.models.Station;

/**
 * Created by User on 7/24/2017.
 */

public class MapManager {


    private static MapManager instance=null;

    private  MapManager() {
    }

    public static MapManager getMapManager(){
        if(instance==null){
            instance=new MapManager();
        }

        return instance;
    }

    public String getMapImage(String latitude, String longitude){

        String url = "http://maps.google.com/maps/api/staticmap?center=" + latitude + "," + longitude + "&zoom=10&size=120x120&sensor=false";

        return url;

    }
}
