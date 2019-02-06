package com.devhub.official.cvrceapplication;

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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.devhub.official.cvrceapplication.Globals.Globals;

import org.json.JSONException;
import org.json.JSONObject;

public class PrincipalGridActivity extends AppCompatActivity {
    private LinearLayout logout,resolvedComplaints,hostelComplaints,foodComplaints,otherComplaints,dswComplaints,
            placementComplaints,examComplaints,pending,academics;;

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
        setContentView(R.layout.activity_principal_grid);
        // addComplaintBtn = findViewById(R.id.addComplaint);
        logout = findViewById(R.id.logout);
        resolvedComplaints = findViewById(R.id.solvedComplaints);

        hostelComplaints = findViewById(R.id.hostelCard);
        foodComplaints = findViewById(R.id.foodCard);
        otherComplaints = findViewById(R.id.moreCard);
        dswComplaints = findViewById(R.id.dswCard);
        examComplaints = findViewById(R.id.examCard);
        placementComplaints = findViewById(R.id.placementCard);
        pending=findViewById(R.id.pending);
        academics=findViewById(R.id.academicsCard);
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
        resolvedComplaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Show Resolved Complaints",Toast.LENGTH_SHORT).show();
                showResolvedComplaints();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                SharedPrefEmployee.getmInstance(getApplicationContext()).logout();
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
        Log.e("mentor_ID",emp_Id);
        intent.putExtra("mentor_id",emp_Id);
        startActivity(intent);
    }



}
