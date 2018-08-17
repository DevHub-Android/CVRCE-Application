package com.android.devhub.use.cvrceapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.devhub.use.cvrceapplication.Adapters.MentorSectionsPagerAdapter;
import com.android.devhub.use.cvrceapplication.Adapters.SectionsPagerAdapter;
import com.android.devhub.use.cvrceapplication.Globals.Globals;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MentorActivity extends AppCompatActivity {

    String studRegId;
    String mentorId;
    EditText studentEditText;
    static RequestQueue myQueue;
    Globals global;
    Context context;
    String serverAddress;
    String addUrl;

    String name;

    private ViewPager mViewPager;
    private MentorSectionsPagerAdapter mSectionsPagerAdapter;
    ProgressDialog progressDialog;
    private JSONObject userComplains,hostelComplains,instiComplains;
    public JSONObject getUserComplains(){
        return userComplains;
    }
    public JSONObject getHostelComplains(){
        return hostelComplains;
    }
    public JSONObject getInstiComplains(){
        return instiComplains;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;
        global = (Globals)this.getApplication();
        serverAddress = URLs.SERVER_ADDR;
        myQueue = global.getVolleyQueue();

      //  mentorId = "578";
        Bundle bundle = getIntent().getExtras();
        mentorId = bundle.getString("empid");
        name = bundle.getString("name");
        Log.e("MENTOR ACTIVITY INFO",mentorId+" "+name);

        progressDialog = new ProgressDialog(this);


        successCallback();
    }
    public String getMentorId(){
        return mentorId;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mentor_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.addStudent:
                addStudent();
                return true;
            case R.id.students:
                showStudents();
                return true;
            case R.id.resolvedComplaints:
                Toast.makeText(getApplicationContext(),"Show Resolved Complaints",Toast.LENGTH_SHORT).show();
                showResolvedComplaints();
                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void showResolvedComplaints() {
        Intent intent = new Intent(MentorActivity.this,MentorResolvedComplaintsActivity.class);
        Log.e("mentor_ID",mentorId);
        intent.putExtra("mentor_id",mentorId);
        startActivity(intent);
    }

    private void addStudent() {
        LayoutInflater factory = LayoutInflater.from(this);
        final View deleteDialogView = factory.inflate(R.layout.add_student_dialog, null);
        final AlertDialog deleteDialog = new AlertDialog.Builder(this).create();
        deleteDialog.setView(deleteDialogView);
        studentEditText = deleteDialogView.findViewById(R.id.addStudentText);
        deleteDialogView.findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studRegId = studentEditText.getText().toString();

                addUrl = serverAddress.concat("/admin/addStudent.php").concat("?user_id=").concat(studRegId).concat("&mentor_id=").
                        concat(mentorId);
                Log.e("Add Url",addUrl);
                JsonObjectRequest request0 = new JsonObjectRequest(Request.Method.GET,addUrl, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(global, "Student added Successfully", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("MENTOR ADD STUDENTS",error.toString());
                        Toast toast = Toast.makeText(context, "error in mentor add students"+error.getMessage(), Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
                //Add the first request in the queue
                myQueue.add(request0);


            }
        });
        deleteDialogView.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDialog.dismiss();
            }
        });

        deleteDialog.show();
    }

    private void showStudents(){
        //String url_show_students = serverAddress.concat("/admin/showStudents.php").concat("?mentor_id=").concat(mentorId);
                Intent intent = new Intent(MentorActivity.this,ShowStudentsActivity.class);
                intent.putExtra("mentor_id",mentorId);
                startActivity(intent);

            }

            public void successCallback(){
               class FetchData extends AsyncTask<Void,Void,Void>{

                   @Override
                   protected Void doInBackground(Void... voids) {
                       return null;
                   }

                   @Override
                   protected void onPostExecute(Void aVoid) {
                       super.onPostExecute(aVoid);
                       progressDialog.dismiss();
                       Log.d("inside", "successCallback: " + "inside the method");
                       String url_user_complaints = serverAddress.concat("/public/mentor_individual_complaints.php").concat("?mentor_id=").concat(mentorId);
                       String url_hostel_complaints = serverAddress.concat("/public/mentor_hostel_complaints.php").concat("?mentor_id=").concat(mentorId);
                       String url_insti_complaints = serverAddress.concat("/public/mentor_institute_complaints.php").concat("?mentor_id=").concat(mentorId);
                        Log.e("HOSTEL LINK MENTOR",url_hostel_complaints);
                       final JsonObjectRequest request4 = new JsonObjectRequest(Request.Method.GET,url_insti_complaints,null, new Response.Listener<JSONObject>() {

                           @Override
                           public void onResponse(JSONObject response) {
                               instiComplains = response;
                               Log.e("institute complaint", "onResponse: " + instiComplains);
                               pagerAdapterCallback();

                           }
                       }, new Response.ErrorListener() {
                           @Override
                           public void onErrorResponse(VolleyError error) {
                               Toast toast = Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG);
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
                               toast.show();
                           }
                       }) ;

                       myQueue.add(request2);
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

    private void pagerAdapterCallback() {
        mSectionsPagerAdapter = new MentorSectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }


}

