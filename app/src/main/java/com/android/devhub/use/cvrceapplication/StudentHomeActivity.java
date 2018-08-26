package com.android.devhub.use.cvrceapplication;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.devhub.use.cvrceapplication.Fragments.FragmentExam;
import com.android.devhub.use.cvrceapplication.Fragments.FragmentFood;
import com.android.devhub.use.cvrceapplication.Fragments.FragmentHostel;
import com.android.devhub.use.cvrceapplication.Fragments.FragmentIndividual;
import com.android.devhub.use.cvrceapplication.Fragments.FragmentInstitute;
import com.android.devhub.use.cvrceapplication.Fragments.FragmentPlacemnt;

import org.json.JSONException;
import org.json.JSONObject;

import static com.android.devhub.use.cvrceapplication.R.id.studentHomeFrame;

public class StudentHomeActivity extends AppCompatActivity {
    private JSONObject userComplains,hostelComplains,instiComplains,notificationData,foodComplains,examComplains,placementComplains;
    String choose;

    public JSONObject getUserComplains(){
        return userComplains;
    }
    public JSONObject getHostelComplains(){
        return hostelComplains;
    }
    public JSONObject getInstiComplains(){
        return instiComplains;
    }
    public JSONObject getFoodComplains(){
        return foodComplains;
    }
    public JSONObject getPlacementComplains(){
        return placementComplains;
    }
    public JSONObject getExamComplains(){
        return examComplains;
    }
    public JSONObject getNotificationData(){
        return notificationData;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);
        Intent intent = getIntent();
        choose = intent.getStringExtra("choose");
        Log.e("CHOOSE",choose);
        try{
            Log.e("Hostel Complaints",intent.getStringExtra("HostelComplains"));
            Log.e("Exam Complaints",intent.getStringExtra("examComplains"));
            userComplains = new JSONObject(intent.getStringExtra("UserComplains"));
            hostelComplains = new JSONObject(intent.getStringExtra("HostelComplains"));
            instiComplains = new JSONObject(intent.getStringExtra("InstiComplains"));
            foodComplains = new JSONObject(intent.getStringExtra("foodComplains"));
            examComplains = new JSONObject(intent.getStringExtra("examComplains"));
            placementComplains = new JSONObject(intent.getStringExtra("placementComplains"));
           // notificationData = new JSONObject(intent.getStringExtra("NotificationList"));




        }catch (JSONException e)
        {
            Log.e("At Sutdent Home",e.getMessage());
        }
        addFragment();
    }
    private  void addFragment(){
        FragmentManager fragmentManager
                = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction
                = fragmentManager.beginTransaction();
        if(choose.equals("hostel"))
        {
          FragmentHostel fragment = new FragmentHostel();
          fragmentTransaction.add(R.id.studentHomeFrame,fragment);
          fragmentTransaction.commit();
        }else if(choose.equals("food"))
        {
            FragmentFood fragment = new FragmentFood();
            fragmentTransaction.add(R.id.studentHomeFrame,fragment);
            fragmentTransaction.commit();
        }else if(choose.equals("other"))
        {
            FragmentIndividual fragment = new FragmentIndividual();
            fragmentTransaction.add(R.id.studentHomeFrame,fragment);
            fragmentTransaction.commit();
        }
        else if(choose.equals("exam"))
        {
            FragmentExam fragment = new FragmentExam();
            fragmentTransaction.add(R.id.studentHomeFrame,fragment);
            fragmentTransaction.commit();
        }
        else if(choose.equals("placement"))
        {
            FragmentPlacemnt fragment = new FragmentPlacemnt();
            fragmentTransaction.add(R.id.studentHomeFrame,fragment);
            fragmentTransaction.commit();
        }else if(choose.equals("dsw"))
        {
            FragmentInstitute fragment = new FragmentInstitute();
            fragmentTransaction.add(R.id.studentHomeFrame,fragment);
            fragmentTransaction.commit();
        }




    }
}
