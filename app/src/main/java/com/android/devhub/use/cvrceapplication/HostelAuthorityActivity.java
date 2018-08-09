package com.android.devhub.use.cvrceapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.android.devhub.use.cvrceapplication.Adapters.Adapter_Complaints;
import com.android.devhub.use.cvrceapplication.Globals.Globals;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;


//This activity is for all the authorities I have by mistakely named as onlu HostelAuthority

public class HostelAuthorityActivity extends AppCompatActivity {

    static RequestQueue myQueue;
    Globals global;
    Context context;
    String serverAddress;
    String addUrl;
    String facultyId;
    int priority;
    String domain;
    String position;
    ProgressDialog progressDialog;
    RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    JSONObject hostel_complaints_data;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostel_authority);

        progressDialog = new ProgressDialog(this);

        serverAddress = URLs.SERVER_ADDR;
        global = (Globals)this.getApplication();
        myQueue = global.getVolleyQueue();

        context = this;
        recyclerView = (RecyclerView)findViewById(R.id.authorityRecyclerview);
        //change this to dynamic when required
        facultyId = "12345";
//        priority = 1;
//        domain = "hostel";
//        position = "warden";
        Bundle bundle = getIntent().getExtras();
        priority = bundle.getInt("priority");
        domain = bundle.getString("domain");
        position = bundle.getString("position");
        Log.e("INFO ABOUT EMPLOYEE :",Integer.toString(priority)+"/ "+domain+"/ "+position);

         type = "";
        //Here we have to check the priority and type of faculty:
        if (domain.equals("self"))
        {
            type = "0";
        }else if(domain.equals("hostel"))
        {
            type = "1";
        }else {
            type ="2";
        }
        hostelSuccessCallBack();



    }

    private void hostelSuccessCallBack() {
        checkStatus();
    }

    public void checkStatus(){
        String urlCheck = serverAddress.concat("/admin/checkStatus.php");
        Log.e("URL",urlCheck);

                JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET,
                        urlCheck,
                        null,
                        new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.e("response of check", "onResponse: " + response);
                hostelAuthorityCallback();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast toast = Toast.makeText(context, error.toString(), Toast.LENGTH_LONG);
                toast.show();
            }
        }) ;

        Log.e("URL3",urlCheck);

        myQueue.add(request2);
        Log.e("URL2",urlCheck);
    }

    private void hostelAuthorityCallback() {
        final String hostel_Url = serverAddress.concat("/admin/hostel_authority_complaints.php")
                .concat("?priority=").concat(String.valueOf(priority)).concat("&type=").concat(type);
        Log.e("here in hostel callback",hostel_Url);
        class FetchData extends AsyncTask<Void,Void,Void>{

            @Override
            protected Void doInBackground(Void... voids) {
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                progressDialog.dismiss();
                Log.e("In :","Post execute");
                final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,hostel_Url,
                        null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        hostel_complaints_data = response;
                        Log.d("response of check", "onResponse: " + response);
                        setRecyclerViewAdapter();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast toast = Toast.makeText(context, error.getMessage() , Toast.LENGTH_LONG);
                        toast.show();
                    }
                }) ;

                myQueue.add(request);


            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog.setMessage("Loading please wait.....");
                progressDialog.show();
            }
        }

        FetchData fetchData = new FetchData();
        fetchData.execute();

    }

    private void setRecyclerViewAdapter() {

        Log.d("jklasjdlkjla", "setRecyclerViewAdapter: inside adapter");

        Activity activity = (HostelAuthorityActivity)context;

        mAdapter = new Adapter_Complaints(hostel_complaints_data,activity,context);
        //Log.i("hagga", complaints_data.toString());
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(mAdapter);

    }
}
