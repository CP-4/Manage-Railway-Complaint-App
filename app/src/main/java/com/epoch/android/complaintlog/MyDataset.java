package com.epoch.android.complaintlog;

/**
 * Created by dell on 29-Mar-18.
 */

public class MyDataset {

    private String complaint;
    private String location;
    private String time;
    private String tweetLink;
    private Boolean newTweet;
    private int complaintId;

    public MyDataset(String complaint, String location, String time, String tweetLink, Boolean newTweet, int complaintId) {
        this.complaint = complaint;
        this.location = location;
        this.time = time;
        this.tweetLink = tweetLink;
        this.newTweet = newTweet;
        this.complaintId = complaintId;
    }

    public String getComplaint() {
        return complaint;
    }

    public String getLocation() {
        return location;
    }

    public String getTime() {
        return time;
    }

    public String getTweetLink() {
        return tweetLink;
    }

    public Boolean getNewTweet() {
        return newTweet;
    }

    public int getComplaintId() {
        return complaintId;
    }
}
