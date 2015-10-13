package com.example.rshah4.instagramclient;

import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by rshah4 on 10/9/15.
 */
public class API {
    String clientId;
    String url;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    API(String clientID, String url) {
        this.clientId = clientID;
        this.url = url+"client_id="+clientID;
    }

    void makeCall(final List<Photo> photos, final PhotoAdapter cAdapter, final ListView lvItems) {

        //System.out.println("inside make call");
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(this.getUrl(), new JsonHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject responseBody) {

                        try {

                            JSONArray returnThis = responseBody.getJSONArray("data");
                            for (int i = 0; i < returnThis.length(); i++) {
                                JSONObject photoObject = returnThis.getJSONObject(i);
                                PhotoDAO dataSource = new PhotoDAOImpl();
                                Photo photo = dataSource.createPhoto(photoObject);
                                photos.add(photo);

                                //System.out.println("captions " + photos.get(i).getCaption());
                            }
                            cAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String errorResponse, Throwable e) {
                        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                        System.out.println("failed");
                    }

                }
        );
        System.out.println("length "+photos.size());
    }

}
