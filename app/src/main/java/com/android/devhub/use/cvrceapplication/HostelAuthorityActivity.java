package com.android.devhub.use.cvrceapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.android.devhub.use.cvrceapplication.Adapters.Adapter_Complaints;
import com.android.devhub.use.cvrceapplication.Adapters.Adapter_Complaints_Authority;
import com.android.devhub.use.cvrceapplication.Adapters.AuthorityPagerAdapter;
import com.android.devhub.use.cvrceapplication.Adapters.MentorSectionsPagerAdapter;
import com.android.devhub.use.cvrceapplication.Globals.Globals;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


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
    String position,first_name,last_name,emp_Id;
    ProgressDialog progressDialog;
    RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    JSONObject hostel_complaints_data;
    String type;
    private ViewPager mViewPager;
    JSONObject solvedComplaints,unsolvedComplaints;
    private AuthorityPagerAdapter authorityPagerAdapter;
    ArrayList<JSONObject> items;
    public ArrayList<JSONObject> getSolvedComplaints(){
        return items;
    }
    public JSONObject getUnsolvedComplaints(){
        return unsolvedComplaints;
    }
    public int getPriority(){return priority;}
    public String getFirst_name(){return first_name;}
    public String getLast_name(){return last_name;}
    public String getEmp_Id(){return emp_Id;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitvity_authority);

        progressDialog = new ProgressDialog(this);

        serverAddress = URLs.SERVER_ADDR;
        global = (Globals)this.getApplication();
        myQueue = global.getVolleyQueue();

        context = this;
        //recyclerView = (RecyclerView)findViewById(R.id.authorityRecyclerview);
        //change this to dynamic when required
//        facultyId = "123333";
//        priority = 1;
//        domain = "hostel";
//        position = "warden";
        items = new ArrayList<>();
        Bundle bundle = getIntent().getExtras();
        priority = bundle.getInt("priority");
        domain = bundle.getString("domain");
        position = bundle.getString("position");
        first_name = bundle.getString("first_name");
        last_name = position;
        emp_Id = bundle.getString("reg_id");
        Log.e("INFO ABOUT EMPLOYEE :",Integer.toString(priority)+"/ "+domain+"/ "+position);

         type = "";
        //Here we have to check the priority and type of faculty:
        if (domain.equals("other"))
        {
            type = "0";
        }else if(domain.equals("hostel"))
        {
            type = "1";
        }else if(domain.equals("dsw")){
            type ="2";
        }else if(domain.equals("exam")){
            type ="4";
        }else if(domain.equals("food")){
            type ="5";
        }else if(domain.equals("placement")){
            type ="3";
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
                Log.e("Error",error.toString());
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
        final String solved_complaints_url = serverAddress.concat("/public/solved_authority_complaints.php")
                .concat("?type=").concat(type);
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
                       unsolvedComplaints = response;
                        Log.d("response of check", "onResponse: " + response);
                        pagerAdapterCallback();
                      //  setRecyclerViewAdapter();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast toast = Toast.makeText(context, error.getMessage() , Toast.LENGTH_LONG);
                        toast.show();
                    }
                }) ;
                final JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.GET,solved_complaints_url,
                        null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        solvedComplaints = response;
                        Log.e("response of check", "onResponse: " + response);
                        try {
                            JSONArray jsonArray = response.getJSONArray("root");
                            if(jsonArray==null){
                                Log.e("Array JSON","NULL");
                            }else{
                                Log.e("FIRST ELEMENT",jsonArray.get(0).toString());
                            }
                            for(int i = 0;i<jsonArray.length();i++){
                                items.add(jsonArray.getJSONObject(i));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        myQueue.add(request);


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast toast = Toast.makeText(context, error.getMessage() , Toast.LENGTH_LONG);
                        toast.show();
                    }
                }) ;
                myQueue.add(request1);


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
    private void pagerAdapterCallback() {
        authorityPagerAdapter = new AuthorityPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(authorityPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }
//
//    private void setRecyclerViewAdapter() {
//
//        Log.d("jklasjdlkjla", "setRecyclerViewAdapter: inside adapter");
//
//        Activity activity = (HostelAuthorityActivity)context;
//
//        mAdapter = new Adapter_Complaints_Authority(hostel_complaints_data,activity,context,"authority");
//        //Log.i("hagga", complaints_data.toString());
//        mLayoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(mLayoutManager);
//
//        recyclerView.setAdapter(mAdapter);
//
//    }
}
