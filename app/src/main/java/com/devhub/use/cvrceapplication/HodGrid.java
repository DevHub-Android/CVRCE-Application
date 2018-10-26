package com.devhub.use.cvrceapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.devhub.use.cvrceapplication.Globals.Globals;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.devhub.use.cvrceapplication.models.HodModel;

import org.json.JSONException;
import org.json.JSONObject;

public class HodGrid extends AppCompatActivity {
    private LinearLayout logout,hostelComplaints,foodComplaints,otherComplaints,dswComplaints,
            placementComplaints,examComplaints,academics;

    Bundle bundle;
    Intent cardIntent;
    String mentorId,name;
    String studRegId;
    String addUrl;
    EditText studentEditText;
    Context context;
    Globals global;
    String serverAddress;
    String first_name;String department;
    static RequestQueue myQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hod_grid);

        Intent intent = getIntent();
        mentorId=intent.getStringExtra("empId");
        department=intent.getStringExtra("department");
        Log.e("deparment",department);
        Log.e("HODID",mentorId);

        name=intent.getStringExtra("name");

        hostelComplaints = findViewById(R.id.hostelCard);
        foodComplaints = findViewById(R.id.foodCard);
        otherComplaints = findViewById(R.id.moreCard);
        dswComplaints = findViewById(R.id.dswCard);
        examComplaints = findViewById(R.id.examCard);
        placementComplaints = findViewById(R.id.placementCard);
        academics=findViewById(R.id.academicsCard);
        logout = findViewById(R.id.logout_btn);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        context = this;
        global = (Globals)this.getApplication();
        serverAddress = URLs.SERVER_ADDR;
        setSupportActionBar(toolbar);

       // Bundle bundle1 = getIntent().getExtras();
     //  mentorId=bundle1.getString("metorid");
        myQueue = global.getVolleyQueue();
        bundle = new Bundle();
        bundle.putString("mentorId",mentorId);
        bundle.putString("department",department);
       // Log.e("deparment",department);
        bundle.putString("first_name",name);
        cardIntent = new Intent(getApplicationContext(),HodHome.class);


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
        academics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putString("choose","academics");
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

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
    }




}

