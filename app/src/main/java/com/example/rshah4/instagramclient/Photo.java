package com.example.rshah4.instagramclient;

/**
 * Created by rshah4 on 10/9/15.
 */
public class Photo {
    String imageURL;
    String caption;
    String timeStamp;
    int likeCount;

    User user;

    Photo() {}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) { this.likeCount = likeCount; }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {

        if(caption == null)
            this.caption = "";
        else
        this.caption = caption;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public static String shortenTimeSpanString (String tString) {
        if (tString.contains("hour")) {
            return tString.split(" ")[0] + "h";
        } else if (tString.contains("minute")) {
            return tString.split(" ")[0] + "m";
        } else if (tString.contains("second")) {
            return tString.split(" ")[0] + "s";
        }
        return tString;
    }
}
