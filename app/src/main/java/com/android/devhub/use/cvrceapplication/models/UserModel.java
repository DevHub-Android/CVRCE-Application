package com.android.devhub.use.cvrceapplication.models;

/**
 * Created by user on 7/24/2018.
 */

public class UserModel {
    private  String regid,username,branch,hostel;

    public UserModel(String regid, String username,String branch,String hostel) {
        this.regid = regid;
        this.username = username;
        this.branch=branch;
        this.hostel=hostel;
    }

    public String getRegid() {
        return regid;
    }

    public void setRegid(String regid) {
        this.regid = regid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch= branch;
    }
    public String getHostel() {
        return hostel;
    }

    public void setHostel(String hostel) {
        this.hostel = hostel;
    }

}
