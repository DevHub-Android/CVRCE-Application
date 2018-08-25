package com.android.devhub.use.cvrceapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.devhub.use.cvrceapplication.R;

import org.json.JSONObject;

public class StudentGrid extends AppCompatActivity {
    private CardView addComplaintBtn,hostelComplaints,foodComplaints,otherComplaints,dswComplaints,
    placementComplaints,examComplaints;
    private JSONObject userComplains,hostelComplains,instiComplains,notificationData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_grid);
        addComplaintBtn = findViewById(R.id.addComplaint);
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
        Bundle bundle = new Bundle();
        bundle.putString("NotificationList", notificationData.toString());
        bundle.putString("UserComplains",userComplains.toString());
        bundle.putString("HostelComplains",hostelComplains.toString());
        bundle.putString("InstiComplains",instiComplains.toString());
        addComplaintBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AddComplaint.class));
            }
        });
    }
}
