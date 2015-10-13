package com.example.rshah4.instagramclient;

import android.content.Context;
import android.graphics.Color;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

/**
 * Created by rshah4 on 10/9/15.
 */
public class PhotoAdapter extends ArrayAdapter<Photo> {


    public PhotoAdapter(Context context, List<Photo> photos) {
        super(context, android.R.layout.simple_list_item_1, photos);

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.photo_cell, parent, false);
        }
        Photo photo = getItem(position);
        System.out.println("photo ts " + photo.getTimeStamp());
        String prettyTimeString = "";
        if(!photo.getTimeStamp().isEmpty()) {
            prettyTimeString  = DateUtils.getRelativeTimeSpanString(Long.valueOf(photo.getTimeStamp().trim()) * 1000,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
        }

        TextView userName = (TextView)convertView.findViewById(R.id.tvUsername);
        TextView caption = (TextView)convertView.findViewById(R.id.tvCaption);
        TextView likeCount = (TextView)convertView.findViewById(R.id.tvLikeCount);
        TextView timeAgo = (TextView) convertView.findViewById(R.id.tvTimeCreated);

        ImageView profilePic = (ImageView)convertView.findViewById(R.id.ivprofilePicture);
        ImageView instagramPic = (ImageView)convertView.findViewById(R.id.ivImage);

        userName.setText(photo.getUser().getUsername());
        caption.setText(photo.getCaption());
        timeAgo.setText(Photo.shortenTimeSpanString(prettyTimeString));
        likeCount.setText(photo.getLikeCount() + " likes");

        Transformation transformationForDP = new RoundedTransformationBuilder()
                .borderColor(Color.BLACK)
                .borderWidthDp(1)
                .cornerRadiusDp(40)
                .oval(false)
                .build();
        Transformation transformationForImg = new RoundedTransformationBuilder()
                .borderColor(Color.BLACK)
                .cornerRadiusDp(10)
                .oval(false)
                .build();

        profilePic.setImageResource(0);
        Picasso
                .with(getContext())
                .load(photo.getUser()
                      .getProfileImageURL())
                .fit()
                .transform(transformationForDP)
                .into(profilePic);

        instagramPic.setImageResource(0);
        Picasso
                .with(getContext())
                .load(photo.getImageURL())
                .transform(transformationForImg)
                .into(instagramPic);

        /*convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("string: " );
                MainActivity sct = (MainActivity) activity;

                /****  Call  onItemClick Method inside CustomListViewAndroidExample Class ( See Below )****/

                //sct.onItemClick(position);
                /*System.out.println("int: " + dataModel.getAnInt());
                System.out.println("double: " + dataModel.getaDouble());
                System.out.println("otherData: " + dataModel.getOtherData());*/
                //Toast.makeText(parent.getContext(), "view clicked: " + dataModel.getOtherData(), Toast.LENGTH_SHORT).show();
            /*}
        });*/

        return convertView;

    }

}
