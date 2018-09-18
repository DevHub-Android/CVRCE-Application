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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.devhub.use.cvrceapplication.Globals.Globals;

import org.json.JSONException;
import org.json.JSONObject;

public class PrincipalGridActivity extends AppCompatActivity {
    private CardView addComplaintBtn,hostelComplaints,foodComplaints,otherComplaints,dswComplaints,
            placementComplaints,examComplaints;

    Bundle bundle;
    Intent cardIntent;
    String mentorId;
    Context context;
    Globals global;
    String serverAddress;
    String first_name;
    static RequestQueue myQueue;
    String position,last_name,emp_Id;
    int priority;
    String domain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_grid);
        // addComplaintBtn = findViewById(R.id.addComplaint);
        hostelComplaints = findViewById(R.id.hostelCard);
        foodComplaints = findViewById(R.id.foodCard);
        otherComplaints = findViewById(R.id.moreCard);
        dswComplaints = findViewById(R.id.dswCard);
        examComplaints = findViewById(R.id.examCard);
        placementComplaints = findViewById(R.id.placementCard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        context = this;
        global = (Globals)this.getApplication();
        serverAddress = URLs.SERVER_ADDR;
        setSupportActionBar(toolbar);
        Bundle bundle1 = getIntent().getExtras();
        priority = bundle1.getInt("priority");
        domain = bundle1.getString("domain");
        position = bundle1.getString("position");
        first_name = bundle1.getString("first_name");
        last_name = position;
        emp_Id = bundle1.getString("reg_id");

        myQueue = global.getVolleyQueue();
        bundle = new Bundle();
        bundle.putString("first_name",first_name);
        bundle.putString("priority",String.valueOf(priority));
        bundle.putString("reg_id",emp_Id);
        cardIntent = new Intent(getApplicationContext(),PrincipalHome.class);


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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.princi_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.resolvedComplaints:
                Toast.makeText(getApplicationContext(),"Show Resolved Complaints",Toast.LENGTH_SHORT).show();
                showResolvedComplaints();
                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void showResolvedComplaints() {
        Intent intent = new Intent(PrincipalGridActivity.this,MentorResolvedComplaintsActivity.class);
        Log.e("mentor_ID",mentorId);
        intent.putExtra("mentor_id",mentorId);
        startActivity(intent);
    }

}
