package com.devhub.official.cvrceapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.devhub.official.cvrceapplication.Fragments.MentorFragmentAcademics;
import com.devhub.official.cvrceapplication.Fragments.MentorFragmentExam;
import com.devhub.official.cvrceapplication.Fragments.MentorFragmentFood;
import com.devhub.official.cvrceapplication.Fragments.MentorFragmentHostel;
import com.devhub.official.cvrceapplication.Fragments.MentorFragmentIndividual;
import com.devhub.official.cvrceapplication.Fragments.MentorFragmentInstitute;
import com.devhub.official.cvrceapplication.Fragments.MentorFragmetnPlacement;
import com.devhub.official.cvrceapplication.Fragments.PrinciFragmentAcademics;
import com.devhub.official.cvrceapplication.Fragments.PrinciFragmentExam;
import com.devhub.official.cvrceapplication.Fragments.PrinciFragmentExamHostel;
import com.devhub.official.cvrceapplication.Fragments.PrinciFragmentExamIndividual;
import com.devhub.official.cvrceapplication.Fragments.PrinciFragmentExamInstitute;
import com.devhub.official.cvrceapplication.Fragments.PrinciFragmentFood;
import com.devhub.official.cvrceapplication.Fragments.PrinciFragmentPlacement;
import com.devhub.official.cvrceapplication.Globals.Globals;

import org.json.JSONObject;

public class PrincipalHome extends AppCompatActivity {
    private JSONObject userComplains,hostelComplains,instiComplains,notificationData,foodComplains,examComplains,placementComplains,academicsComplaints;
    String choose,first_name,last_name;
    String emp_id;
    int priority;
    String studRegId;

    EditText studentEditText;
    static RequestQueue myQueue;
    Globals global;
    Context context;
    String serverAddress;
    String addUrl;
    ProgressDialog progressDialog;
    String name;
    public JSONObject getAcademicsComplaints(){
        return academicsComplaints;
    }
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
    public String getFirst_name(){
        Log.e("ingetFirstNAME",first_name);
        return first_name;}
    public String getLast_name(){return last_name;}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_home);
        Intent intent = getIntent();
        choose = intent.getStringExtra("choose");
        //mentorId = intent.getStringExtra("mentorId");
        first_name = intent.getStringExtra("first_name");
        Log.e("firstnameprinci",first_name);
        emp_id = intent.getStringExtra("reg_id");
        priority = Integer.parseInt(intent.getStringExtra("priority"));
        String c=intent.getStringExtra("priority");
        Log.e("principrinci",c);

        last_name = "Principal";
        // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        context = this;
        global = (Globals)this.getApplication();
        serverAddress = URLs.SERVER_ADDR;
        myQueue = global.getVolleyQueue();
        Log.e("CHOOSE",choose);

        //addFragment();
        progressDialog = new ProgressDialog(this);


        successCallback();
    }

    public int getPriority() {
        return priority;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void successCallback(){
        class FetchData extends AsyncTask<Void,Void,Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                progressDialog.dismiss();
                Log.d("inside", "successCallback: " + "inside the method");
                String url_user_complaints = serverAddress.concat("/public/princi_individual_complaints.php");
                String url_hostel_complaints = serverAddress.concat("/public/princi_hostel_complaints.php");
                String url_insti_complaints = serverAddress.concat("/public/princi_institute_complaints.php");
                String url_exam_complaints = serverAddress.concat("/public/princi_exam_complaints.php");
                String url_food_complaints = serverAddress.concat("/public/princi_food_complaints.php");
                String url_academics_complaints = serverAddress.concat("/public/princi_academics_complaints.php");

                String url_placement_complaints = serverAddress.concat("/public/princi_placement_complaints.php");
                Log.e("HOSTEL LINK MENTOR",url_food_complaints);
                final JsonObjectRequest request4 = new JsonObjectRequest(Request.Method.GET,url_insti_complaints,null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        instiComplains = response;

                        Log.e("institute complaint", "onResponse: " + instiComplains);
                        addFragment();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast toast = Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG);
                        Log.e("ERROR IN MENTOR INSI",error.getMessage());
                        toast.show();
                    }
                }) ;



                final JsonObjectRequest request3 = new JsonObjectRequest(Request.Method.GET,url_hostel_complaints,null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        hostelComplains = response;
                        Log.d("hostelcomplaints", "onResponse: " + hostelComplains);
                        myQueue.add(request4 );
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast toast = Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG);
                        Log.e("ERROR IN HOSTEL ",error.getMessage());
                        toast.show();
                    }
                }) ;


                final JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET,url_user_complaints,null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        userComplains = response;
                        Log.d("usercomplaints", "onResponse: " + userComplains);
                        myQueue.add(request3 );


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast toast = Toast.makeText(context, error.getMessage() , Toast.LENGTH_LONG);
                        Log.e("ERROR IN MENTOR USER",error.getMessage());
                        toast.show();
                    }
                }) ;
                final JsonObjectRequest request5 = new JsonObjectRequest(Request.Method.GET,url_food_complaints,null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        foodComplains = response;
                         Log.e("prinici food ", "onResponse: " + foodComplains);
                        myQueue.add(request2 );


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast toast = Toast.makeText(context, error.getMessage() , Toast.LENGTH_LONG);
                        Log.e("ERROR IN MENTOR FOOD",error.getMessage());
                        toast.show();
                    }
                }) ;
                final JsonObjectRequest request6 = new JsonObjectRequest(Request.Method.GET,url_exam_complaints,null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        examComplains = response;
                          Log.e("exam complains",examComplains.toString());
                        myQueue.add(request5 );


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast toast = Toast.makeText(context, error.getMessage() , Toast.LENGTH_LONG);
                        Log.e("ERROR IN MENTOR EXAM",error.getMessage());
                        toast.show();
                    }
                }) ;
                final JsonObjectRequest request7 = new JsonObjectRequest(Request.Method.GET,url_placement_complaints,null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        placementComplains = response;
                        //Log.d("usercomplaints", "onResponse: " + userComplains);
                        myQueue.add(request6);


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast toast = Toast.makeText(context, error.getMessage() , Toast.LENGTH_LONG);
                        Log.e("ERROR IN PLCMNT",error.getMessage());
                        toast.show();
                    }
                }) ;
                final JsonObjectRequest request8 = new JsonObjectRequest(Request.Method.GET,url_academics_complaints,null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        academicsComplaints = response;
                        //  Log.d("usercomplaints", "onResponse: " + userComplains);
                        myQueue.add(request7 );


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast toast = Toast.makeText(context, error.getMessage() , Toast.LENGTH_LONG);
                        Log.e("ERROR IN academics",error.getMessage());
                        toast.show();
                    }
                }) ;
                myQueue.add(request8);

            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog.setMessage("Loading please wait....");
                progressDialog.show();
            }
        }

        FetchData fetchData = new FetchData();
        fetchData.execute();

    }
    private  void addFragment(){
        FragmentManager fragmentManager
                = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction
                = fragmentManager.beginTransaction();
        if(choose.equals("hostel"))
        { Log.d("inkk","hi");
            PrinciFragmentExamHostel fragment = new PrinciFragmentExamHostel();
            fragmentTransaction.add(R.id.container,fragment);
            fragmentTransaction.commit();
        }else if(choose.equals("food"))
        {
            Log.e("CHOOSEN :","FOOD");
            PrinciFragmentFood fragment = new PrinciFragmentFood();
            fragmentTransaction.add(R.id.container,fragment);
            fragmentTransaction.commitAllowingStateLoss();
        }else if(choose.equals("other"))
        {
            PrinciFragmentExamIndividual fragment = new PrinciFragmentExamIndividual();
            fragmentTransaction.add(R.id.container,fragment);
            fragmentTransaction.commitAllowingStateLoss();
        }
        else if(choose.equals("exam"))
        {
            Log.e("CHOOSEN :","EXAM");
            Toast.makeText(getApplicationContext(), "Going to exam", Toast.LENGTH_SHORT).show();
            PrinciFragmentExam fragment = new PrinciFragmentExam();
            fragmentTransaction.add(R.id.container,fragment);
            fragmentTransaction.commitAllowingStateLoss();
        }
        else if(choose.equals("placement"))
        {
            PrinciFragmentPlacement fragment = new PrinciFragmentPlacement();
            fragmentTransaction.add(R.id.container,fragment);
            fragmentTransaction.commitAllowingStateLoss();
        }else if(choose.equals("dsw"))
        {
            PrinciFragmentExamInstitute fragment = new PrinciFragmentExamInstitute();
            fragmentTransaction.add(R.id.container,fragment);
            fragmentTransaction.commitAllowingStateLoss();
        }

        else if(choose.equals("academics"))
        {
            PrinciFragmentAcademics fragment = new PrinciFragmentAcademics();
            fragmentTransaction.add(R.id.container,fragment);
            fragmentTransaction.commitAllowingStateLoss();
        }



    }
}
