package com.example.rshah4.instagramclient;

import com.example.rshah4.instagramclient.Photo;

import org.json.JSONObject;

/**
 * Created by rshah4 on 10/10/15.
 */
public interface PhotoDAO {
    Photo createPhoto(JSONObject photoObject) ;

}
