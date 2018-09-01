package com.devhub.use.cvrceapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.devhub.use.cvrceapplication.R;

import org.json.JSONObject;

public class StudentGrid extends AppCompatActivity {
    private CardView addComplaintBtn,hostelComplaints,foodComplaints,otherComplaints,dswComplaints,
    placementComplaints,examComplaints;
    private JSONObject userComplains,hostelComplains,instiComplains,notificationData,foodComplains,examComplains,placementComplains;
    Bundle bundle;
   Intent cardIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_grid);
        addComplaintBtn = findViewById(R.id.addComplaint);
        hostelComplaints = findViewById(R.id.hostelCard);
        foodComplaints = findViewById(R.id.foodCard);
        otherComplaints = findViewById(R.id.moreCard);
        dswComplaints = findViewById(R.id.dswCard);
        examComplaints = findViewById(R.id.examCard);
        placementComplaints = findViewById(R.id.placementCard);
        final Intent intent = getIntent();
        try{
            userComplains = new JSONObject(intent.getStringExtra("UserComplains"));
            hostelComplains = new JSONObject(intent.getStringExtra("HostelComplains"));
            instiComplains = new JSONObject(intent.getStringExtra("InstiComplains"));
            foodComplains = new JSONObject(intent.getStringExtra("foodComplains"));
            examComplains = new JSONObject(intent.getStringExtra("examComplains"));
            placementComplains = new JSONObject(intent.getStringExtra("placementComplains"));

            notificationData = new JSONObject(intent.getStringExtra("NotificationList"));

        }catch (Exception e)
        {
            Log.e("At Sutdent Grid",e.getMessage());
        }
        bundle = new Bundle();
        Log.e("EXAM COMPLAINTS",examComplains.toString());
        bundle.putString("NotificationList", notificationData.toString());
        bundle.putString("UserComplains",userComplains.toString());
        bundle.putString("HostelComplains",hostelComplains.toString());
        bundle.putString("InstiComplains",instiComplains.toString());
        bundle.putString("foodComplains",foodComplains.toString());
        bundle.putString("examComplains",examComplains.toString());
        bundle.putString("placementComplains",placementComplains.toString());

        cardIntent = new Intent(getApplicationContext(),StudentHomeActivity.class);

        addComplaintBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AddComplaint.class));
            }
        });
        foodComplaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putString("choose","food");
                cardIntent.putExtras(bundle);
                startActivity(cardIntent);
            }
        });
        hostelComplaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bundle.putString("choose","hostel");
                cardIntent.putExtras(bundle);
                startActivity(cardIntent);
            }
        });
        dswComplaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putString("choose","dsw");
                cardIntent.putExtras(bundle);
                startActivity(cardIntent);
            }
        });
        otherComplaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putString("choose","other");
                cardIntent.putExtras(bundle);
                startActivity(cardIntent);
            }
        });

        examComplaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putString("choose","exam");
                cardIntent.putExtras(bundle);
                startActivity(cardIntent);
            }
        });
        placementComplaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putString("choose","placement");
                cardIntent.putExtras(bundle);
                startActivity(cardIntent);
            }
        });

    }
}
