package com.example.rshah4.instagramclient;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by rshah4 on 10/10/15.
 */

public class PhotoDAOImpl implements PhotoDAO {

    @Override
    public Photo createPhoto(JSONObject photoObject) {
        Photo photo = new Photo();
        User user = new User();

        try {
            user.setUsername(photoObject.getJSONObject("user").getString("username"));
            user.setProfileImageURL(photoObject.getJSONObject("user").getString("profile_picture"));

            photo.setUser(user);
            photo.setImageURL(photoObject.getJSONObject("images").getJSONObject("standard_resolution").getString("url"));
            if (photoObject.getJSONObject("caption") != null) {
                photo.setCaption(photoObject.getJSONObject("caption").getString("text"));
            } else photo.setCaption("");
            photo.setTimeStamp(photoObject.getString("created_time"));
            System.out.println("object time "+photo.getTimeStamp());
            photo.setLikeCount(photoObject.getJSONObject("likes").getInt("count"));

        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        return photo;
    }
}

