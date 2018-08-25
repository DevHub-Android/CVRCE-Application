package com.android.devhub.use.cvrceapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONObject;

public class StudentHomeActivity extends AppCompatActivity {
    private JSONObject userComplains,hostelComplains,instiComplains,notificationData;

    public JSONObject getUserComplains(){
        return userComplains;
    }
    public JSONObject getHostelComplains(){
        return hostelComplains;
    }
    public JSONObject getInstiComplains(){
        return instiComplains;
    }
    public JSONObject getNotificationData(){
        return notificationData;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);
        Intent intent = getIntent();
        try{
            userComplains = new JSONObject(intent.getStringExtra("UserComplains"));
            hostelComplains = new JSONObject(intent.getStringExtra("HostelComplains"));
            instiComplains = new JSONObject(intent.getStringExtra("InstiComplains"));
            notificationData = new JSONObject(intent.getStringExtra("NotificationList"));
        }catch (Exception e)
        {
            Log.e("At Sutdent Grid",e.getMessage());
        }
    }
}
