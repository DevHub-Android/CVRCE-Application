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

import com.devhub.official.cvrceapplication.Globals.Globals;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.devhub.official.cvrceapplication.models.MentorModel;

import org.json.JSONException;
import org.json.JSONObject;

public class MentorGrid extends AppCompatActivity {
    private LinearLayout logout,resolvedComplaints, addStudents,myStudents,hostelComplaints,foodComplaints,otherComplaints,dswComplaints,
            placementComplaints,examComplaints,pending,academics,resetPassword;

    Bundle bundle;
    Intent cardIntent;
    String mentorId;
    String studRegId;
    String addUrl;
    EditText studentEditText;
    Context context;
    Globals global;
    String serverAddress;
    String first_name;
    static RequestQueue myQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mentor_grid_new);
       // addComplaintBtn = findViewById(R.id.addComplaint);
        hostelComplaints = findViewById(R.id.hostelCard);
        foodComplaints = findViewById(R.id.foodCard);
        otherComplaints = findViewById(R.id.moreCard);
        dswComplaints = findViewById(R.id.dswCard);
        examComplaints = findViewById(R.id.examCard);
        placementComplaints = findViewById(R.id.placementCard);
        addStudents = findViewById(R.id.addStudent);
        myStudents = findViewById(R.id.myStudents);
        resolvedComplaints = findViewById(R.id.solvedComplaints);
        pending=findViewById(R.id.pending);
        academics=findViewById(R.id.academicsCard);
        logout = findViewById(R.id.logout);
        resetPassword = findViewById(R.id.reset_password);

         Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        context = this;
        global = (Globals)this.getApplication();
        serverAddress = URLs.SERVER_ADDR;
        setSupportActionBar(toolbar);

        Bundle bundle1 = getIntent().getExtras();
        if(!SharedPrefMentor.getmInstance(this).isLoggedIn())
        {
            startActivity(new Intent(this,MentorLogin.class));
            finish();
        }else{
            MentorModel mentorModel = SharedPrefMentor.getmInstance(getApplicationContext()).getMentor();
            Log.e("empid",mentorModel.getEmpid());
            Log.e("name",mentorModel.getName());
            mentorId = mentorModel.getEmpid();
                    first_name = mentorModel.getName();
        }

        final Intent intent = getIntent();
        myQueue = global.getVolleyQueue();
        bundle = new Bundle();
        bundle.putString("mentorId",mentorId);
        bundle.putString("first_name",first_name);
        cardIntent = new Intent(getApplicationContext(),MentorHome.class);


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
        addStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addStudent();
            }
        });
        myStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showStudents();
            }
        });
        resolvedComplaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Show Resolved Complaints",Toast.LENGTH_SHORT).show();
                showResolvedComplaints();
            }
        });
        pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pendingIntent =new Intent(MentorGrid.this,PendingActivity.class);
                pendingIntent.putExtras(bundle);
                startActivity(pendingIntent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                SharedPrefMentor.getmInstance(getApplicationContext()).logout();
            }
        });
        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pendingIntent = new Intent(MentorGrid.this,ForgetPasswordAuthority.class);
                intent.putExtra("type","mentor");
                startActivity(pendingIntent);
            }
        });
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
        Intent intent = new Intent(MentorGrid.this,MentorResolvedComplaintsActivity.class);
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
                        try {
                            Toast.makeText(global, response.getString("msg"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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
        Intent intent = new Intent(MentorGrid.this,ShowStudentsActivity.class);
        intent.putExtra("mentor_id",mentorId);
        startActivity(intent);

    }

}

