package com.epoch.android.complaintlog;

import com.android.volley.toolbox.StringRequest;

import java.io.Serializable;

/**
 * Created by dell on 29-Mar-18.
 */

public class MyDataset implements Serializable{

    public int complaintId;
    private String complaintDept;
    private String query;
    private String email;
    private String pts;
    private String trainNum;
    private String trainName;
    private String seatNo;
    private String station;
    private String complaintLink;
    private int resolved;
    private int newComplaint;
    private String time;


    public MyDataset(int complaintId, String complaintDept, String query, String email, String pts, String trainNum, String trainName, String seatNo, String station, String complaintLink, int resolved, int newComplaint, String time) {
        this.complaintId = complaintId;
        this.complaintDept = complaintDept;
        this.query = query;
        this.email = email;
        this.pts = pts;
        this.trainNum = trainNum;
        this.trainName = trainName;
        this.seatNo = seatNo;
        this.station = station;
        this.complaintLink = complaintLink;
        this.resolved = resolved;
        this.newComplaint = newComplaint;
        this.time = time;
        if (time.isEmpty()) {
            time = "HH:MM";
        }
    }

    public int getComplaintId() {
        return complaintId;
    }

    public String getComplaintDept() {
        return complaintDept;
    }

    public String getQuery() {
        return query;
    }

    public String getEmail() {
        return email;
    }

    public String getPts() {
        return pts;
    }

    public String getTrainNum() {
        return trainNum;
    }

    public String getTrainName() {
        return trainName;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public String getStation() {
        return station;
    }

    public String getComplaintLink() {
        return complaintLink;
    }

    public int getResolved() {
        return resolved;
    }

    public int getNewComplaint() {
        return newComplaint;
    }

    public String getTime() {
        return time;
    }

    public String getComplaintIdString(){
        return "Complaint #" + complaintId;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
