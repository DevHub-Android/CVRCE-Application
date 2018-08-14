package com.android.devhub.use.cvrceapplication;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.devhub.use.cvrceapplication.Adapters.SectionsPagerAdapter;
import com.android.devhub.use.cvrceapplication.Globals.Globals;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class HomeActivity extends AppCompatActivity {

    static String serverAddress;
    static RequestQueue volleyQueue;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    Globals global;

    private ViewPager mViewPager;
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
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        //Get the data from the intent
        Intent intent = getIntent();

//        if(!SharedPrefManager.getInstance(this).isLoggedIn())
//        {
//            finish();
//            Intent intentX = new Intent(this,MainActivity.class);
//            startActivity(intentX);
//        }

        try{
            userComplains = new JSONObject(intent.getStringExtra("UserComplains"));
            hostelComplains = new JSONObject(intent.getStringExtra("HostelComplains"));
            instiComplains = new JSONObject(intent.getStringExtra("InstiComplains"));
            notificationData = new JSONObject(intent.getStringExtra("NotificationList"));
        }
        catch (JSONException e){
            Toast.makeText(global, "here", Toast.LENGTH_SHORT).show();
        }
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        global = ((Globals) this.getApplication());
        serverAddress = global.getServerAddress();
        volleyQueue = global.getVolleyQueue();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public void logout_method(MenuItem item){
        finish();
        SharedPrefManager.getInstance(getApplicationContext()).logout();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));

    }
    public void call_resolve(MenuItem item)
    {

    }
    public void add_complaint(MenuItem item)
    {
        Intent intent = new Intent(HomeActivity.this,AddComplaint.class);
        startActivity(intent);
    }
    //commit trying again
    //Click Listener for logout
//    public void logout_method(MenuItem item) {
//
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//
//    }
//
//    //Click Listener for logout
//    public void logout(MenuItem item) {
//        final Context context = getApplicationContext();
//        final int duration = Toast.LENGTH_LONG;
//
//
//        String url_logout = serverAddress.concat("/default/logout.json");
//        JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.GET,url_logout,null, new Response.Listener<JSONObject>() {
//
//            @Override
//            public void onResponse(JSONObject response) {
//                Toast toast = Toast.makeText(context, "Logged Out", duration);
//                toast.show();
//                finish();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast toast = Toast.makeText(context, "Network Error", duration);
//                toast.show();
//            }
//        }) ;
//
//        volleyQueue.add(request1);
//
//    }
//
//    public void call_resolve(MenuItem item) {
//        final Context context = getApplicationContext();
//        final int duration = Toast.LENGTH_LONG;
//
//        Log.i("hagga1","getting here200");
//        String url_resolving = serverAddress.concat("/default/all_complaints_to_be_resolved.json");
//        Log.i("hagga1","getting here100");
//        JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.GET,url_resolving,null, new Response.Listener<JSONObject>() {
//
//            @Override
//            public void onResponse(JSONObject response) {
//                Log.i("hagga1", "getting here");
//                success_callback(response);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast toast = Toast.makeText(context, "Network Error", duration);
//                toast.show();
//            }
//        }) ;
//
//        volleyQueue.add(request1);
//
//    }
//
//    public void success_callback(JSONObject response){
//        Log.i("hagga1","getting here");
//        Intent intent = new Intent(this,resolveActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putString("complaints", response.toString());
//        intent.putExtras(bundle);
//        startActivity(intent);
//        Log.i("hagga1", "getting here000");
//    }
//
//    public void add_complaint(MenuItem item){
//        Intent intent = new Intent(this,add_complaint.class);
//        Bundle bundle = new Bundle();
//        startActivity(intent);
//    }
}
