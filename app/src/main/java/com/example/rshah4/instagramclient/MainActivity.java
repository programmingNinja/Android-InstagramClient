package com.example.rshah4.instagramclient;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    public static final String POPULAR_PHOTO_URL = "https://api.instagram.com/v1/media/popular?";
    public static final String POPULAR_PHOTO_CLIENT_ID = "367bb259c6de4d8a8ae9910d1290325f";

    private static final int REQUEST_CODE = 1;
    private SwipeRefreshLayout swipeContainer;

    PhotoAdapter cAdapter;
    ListView lvItems;

    List<Photo> photos = new ArrayList<Photo>();
    API test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        test = new API(POPULAR_PHOTO_CLIENT_ID, POPULAR_PHOTO_URL);
        photos = new ArrayList<Photo>();
        cAdapter = new PhotoAdapter(getApplicationContext(), photos);
        lvItems = new ListView(getApplicationContext());
        //test.makeCall(photos, cAdapter, lvItems);
        this.makeCall();
        lvItems.setAdapter(cAdapter);
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                makeCall();
                swipeContainer.setRefreshing(false);
                cAdapter.notifyDataSetChanged();
            }
        });
        cAdapter.notifyDataSetChanged();
        /*System.out.println("size inside main " + photos.size());
        for (int i=0 ; i<photos.size() ; i++) {
            System.out.println("captions "+photos.get(i).getCaption());
        }*/

        System.out.println("exisiting item size " + photos.size());
        lvItems = (ListView) findViewById(R.id.lv_all_photos);


    }


    public void onItemClick(int position) {
        /*System.out.println("main onlcik");
        Intent i = new Intent(MainActivity.this, EditItemActivity.class);
        editTask = existingItems.get(position);
        System.out.println("sending task id "+editTask.id);
        i.putExtra("taskName", existingItems.get(position));
        editPos = position;
        //i.putExtra("taskName", editTask);
        //i.putExtra("in_reply_to", "george");
        //i.putExtra("code", 400);
        //startActivity(i);
        startActivityForResult(i, REQUEST_CODE);

        System.out.println(editTask.getTask() + " edited from string list");
        //aToDoAdapter.notifyDataSetChanged();
        cAdapter.notifyDataSetChanged();*/

    }

    void makeCall() {

        //System.out.println("inside make call");
        final List<Photo> photos = new ArrayList<Photo>();
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(test.getUrl(), new JsonHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject responseBody) {

                        try {

                            JSONArray returnThis = responseBody.getJSONArray("data");
                            for (int i=0 ; i<returnThis.length() ; i++) {
                                JSONObject photoObject = returnThis.getJSONObject(i);
                                PhotoDAO dataSource = new PhotoDAOImpl();
                                Photo photo = dataSource.createPhoto(photoObject);
                                photos.add(photo);

                            }
                            cAdapter = new PhotoAdapter(getApplicationContext(), photos);
                            lvItems.setAdapter(cAdapter);
                            cAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            cAdapter.notifyDataSetChanged();
                        }

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String errorResponse, Throwable e) {
                        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                        System.out.println("failed");
                    }

                }
        );
    }
}
