package com.devhub.official.cvrceapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.devhub.official.cvrceapplication.Fragments.MentorPending;
import com.devhub.official.cvrceapplication.Globals.Globals;

import org.json.JSONObject;

public class PendingActivity extends AppCompatActivity {
    private JSONObject pendingComplaints;
    static RequestQueue myQueue;
    Globals global;
    String mentorId;
    String first_name,last_name;
    String serverAddress;
    public JSONObject getPendingComplaints(){
        return pendingComplaints;
    }
    public String getFirst_name(){return first_name;}
    public String getLast_name(){return last_name;}
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_home);
        Intent intent = getIntent();
        mentorId = intent.getStringExtra("mentorId");
        first_name=intent.getStringExtra("first_name");
        last_name = "Mentor";
        global = (Globals)this.getApplication();
        serverAddress = URLs.SERVER_ADDR;
        myQueue = global.getVolleyQueue();

        successCallback();
    }
    public String getMentorId(){
        return mentorId;
    }
    public void successCallback() {

        String url_pending_complaints = serverAddress.concat("/public/PendingComplaints.php").concat("?mentor_id=").concat(mentorId);
Log.e("url_pending",url_pending_complaints);
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,url_pending_complaints,null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                pendingComplaints = response;

                Log.e("pendingcomplaints", "onResponse: " + pendingComplaints);

                addFragment();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast toast = Toast.makeText(PendingActivity.this, error.getMessage(), Toast.LENGTH_LONG);
                Log.e("ERROR IN PENDING ",error.getMessage());
                toast.show();
            }
        }) ;
        myQueue.add(request );



    }
    private void addFragment () {
        FragmentManager fragmentManager
                = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction
                = fragmentManager.beginTransaction();


        MentorPending fragment = new MentorPending();
        fragmentTransaction.add(R.id.container, fragment);
        fragmentTransaction.commit();

    }
}