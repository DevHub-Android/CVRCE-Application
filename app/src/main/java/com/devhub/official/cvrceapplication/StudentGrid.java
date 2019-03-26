package com.devhub.official.cvrceapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.devhub.official.cvrceapplication.Globals.Globals;
import com.devhub.official.cvrceapplication.R;
import com.devhub.official.cvrceapplication.models.UserModel;

import org.json.JSONException;
import org.json.JSONObject;

public class StudentGrid extends AppCompatActivity {
    private LinearLayout addComplaintBtn,hostelComplaints,foodComplaints,academicsComplaints,otherComplaints,dswComplaints,
    placementComplaints,examComplaints,logout;
    private JSONObject

            userComplains,
            hostelComplains,
            instiComplains,
            notificationData,
            foodComplains,
            examComplains,
            placementComplains,
            academicsComplains;



    Bundle bundle;
   Intent cardIntent;
    EditText password, regId;
    Button login;
    TextView register;
    ProgressDialog progressDialog;
    Globals global;
    static String serverAddress;
    static RequestQueue myQueue;
    String regid, pass;

    String first_name;
    String last_name;
    String hostel;
    String email;
    String reg_id;
    int usertype;
   // Bundle bundle;
    static boolean proceed;
    static String REGID;
    boolean check=false;
    ProgressDialog progressDialogOne;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_grid_new);
        addComplaintBtn = findViewById(R.id.addComplaint);
        hostelComplaints = findViewById(R.id.hostelCard);
        foodComplaints = findViewById(R.id.foodCard);
        otherComplaints = findViewById(R.id.moreCard);
        dswComplaints = findViewById(R.id.dswCard);
        examComplaints = findViewById(R.id.examCard);
        academicsComplaints=findViewById(R.id.academicsCard);
        placementComplaints = findViewById(R.id.placementCard);
        logout = findViewById(R.id.logout_btn);
        progressDialog = new ProgressDialog(StudentGrid.this);
        progressDialogOne = new ProgressDialog(StudentGrid.this);
        progressDialogOne.setCanceledOnTouchOutside(false);
        progressDialog.setCanceledOnTouchOutside(false);
        global = (Globals) this.getApplication();

        serverAddress = URLs.SERVER_ADDR;
        bundle = new Bundle();
        myQueue = global.getVolleyQueue();
        progressDialogOne.setMessage("Fetching Data...");
        progressDialogOne.show();
        if(!SharedPrefManager.getInstance(this).isLoggedIn())
        {
            Log.e("Not","Logged in");
            startActivity(new Intent(StudentGrid.this,MainActivity.class));
            finish();
        }else
        {
            Log.e("It's","Logged in");
            UserModel user = SharedPrefManager.getInstance(getApplicationContext()).getUser();

            regid = user.getRegid();
            pass = user.getPassword();

            if(regid!=null&&pass!=null)
            {
                //();
                getData();
                Log.e("password in grid",pass);

            }else{
                startActivity(new Intent(StudentGrid.this,MainActivity.class));
                finish();
            }


        }

//        final Intent intent = getIntent();
//        try{
//            userComplains = new JSONObject(intent.getStringExtra("UserComplains"));
//            hostelComplains = new JSONObject(intent.getStringExtra("HostelComplains"));
//            instiComplains = new JSONObject(intent.getStringExtra("InstiComplains"));
//            foodComplains = new JSONObject(intent.getStringExtra("foodComplains"));
//            examComplains = new JSONObject(intent.getStringExtra("examComplains"));
//            placementComplains = new JSONObject(intent.getStringExtra("placementComplains"));
//
//            notificationData = new JSONObject(intent.getStringExtra("NotificationList"));
//
//        }catch (Exception e)
//        {
//            Log.e("At Sutdent Grid",e.getMessage());
//        }


        cardIntent = new Intent(getApplicationContext(),StudentHomeActivity.class);

        addComplaintBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AddComplaint.class);
                intent.putExtra("name",first_name);
                startActivity(intent);
            }
        });
        foodComplaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(check)
               {
                   bundle.putString("choose","food");
                   cardIntent.putExtras(bundle);
                   startActivity(cardIntent);
               }else
               {
                   Toast.makeText(StudentGrid.this,"Wait for data to load",Toast.LENGTH_SHORT).show();
               }

            }
        });
        hostelComplaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bundle.putString("choose","hostel");
                cardIntent.putExtras(bundle);
                startActivity(cardIntent);
//
              //  Toast.makeText(StudentGrid.this,"Feature to be added!",Toast.LENGTH_SHORT).show();
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
               // bundle.putString("choose","other");
                //cardIntent.putExtras(bundle);
                //startActivity(cardIntent);
                Toast.makeText(StudentGrid.this,"Feature to be added!",Toast.LENGTH_SHORT).show();
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
                Toast.makeText(StudentGrid.this,"Feature to be added!",Toast.LENGTH_SHORT).show();
            }
        });
        academicsComplaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putString("choose","academics");
                cardIntent.putExtras(bundle);
                startActivity(cardIntent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                SharedPrefManager.getInstance(getApplicationContext()).logout();
            }
        });

    }
    public void getData() {
        final Context context = getApplicationContext();
        final int duration = Toast.LENGTH_LONG;
        String url_login = serverAddress.concat("/public/login.php?userid=");
        url_login = url_login.concat(regid).concat("&password=").concat(pass);
        String url_notification = serverAddress.concat("/public/notifications.php");
        String url_user_complaints = serverAddress.concat("/public/user_complaints.php?user_id=").concat(regid);
        String url_hostel_complaints = serverAddress.concat("/public/hostel_complaints.php?user_id=").concat(regid);
        String url_insti_complaints = serverAddress.concat("/public/institute_complaints.php?user_id=").concat(regid);
        String url_food_complaints = serverAddress.concat("/public/food_complaints.php?user_id=").concat(regid);
        String url_academics_complaints = serverAddress.concat("/public/academics_complaints.php?user_id=").concat(regid);

        String url_exam_complaints = serverAddress.concat("/public/exam_complaints.php?user_id=").concat(regid);
        String url_placement_complaints = serverAddress.concat("/public/placement_complaints.php?user_id=").concat(regid);
       // Log.e("URL_ACADEMICS",);

        Log.e("URL_NOTIFY", url_login);


        final JsonObjectRequest request4 = new JsonObjectRequest(Request.Method.GET, url_insti_complaints, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                instiComplains = response;
                successCallback();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast toast = Toast.makeText(context, error.getMessage(), duration);
                Log.e("request4", error.getMessage());
                progressDialog.hide();
                toast.show();
            }
        });


        final JsonObjectRequest request3 = new JsonObjectRequest(Request.Method.GET, url_hostel_complaints, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                hostelComplains = response;
                myQueue.add(request4);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast toast = Toast.makeText(context, error.getMessage(), duration);
                Log.e("request3 hostel ", error.getMessage());
                progressDialog.hide();
                toast.show();
            }
        });


        final JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, url_user_complaints, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                userComplains = response;
                myQueue.add(request3);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast toast = Toast.makeText(context, error.getMessage(), duration);
                Log.e("request2 user", error.getMessage());
                progressDialog.hide();
                toast.show();
            }
        });

        final JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.GET, url_notification, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                notificationData = response;
                myQueue.add(request2);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast toast = Toast.makeText(context, error.getMessage(), duration);
                Log.e("request1", error.getMessage());
                progressDialog.hide();
                toast.show();
            }
        });
        final JsonObjectRequest request5 = new JsonObjectRequest(Request.Method.GET, url_food_complaints, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                foodComplains = response;
                myQueue.add(request1);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast toast = Toast.makeText(context, error.getMessage(), duration);
                Log.e("request1", error.getMessage());
                progressDialog.hide();
                toast.show();
            }
        });

        final JsonObjectRequest request6 = new JsonObjectRequest(Request.Method.GET, url_exam_complaints, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                examComplains = response;
                myQueue.add(request5);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast toast = Toast.makeText(context, error.getMessage(), duration);
                Log.e("request1", error.getMessage());
                progressDialog.hide();
                toast.show();
            }
        });
        final JsonObjectRequest request7 = new JsonObjectRequest(Request.Method.GET, url_placement_complaints, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                placementComplains = response;
                myQueue.add(request6);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast toast = Toast.makeText(context, error.getMessage(), duration);
                Log.e("request1", error.getMessage());
                progressDialog.hide();
                toast.show();
            }
        });
        final JsonObjectRequest request8 = new JsonObjectRequest(Request.Method.GET, url_academics_complaints, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                academicsComplains = response;
                myQueue.add(request7);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast toast = Toast.makeText(context, error.getMessage(), duration);
                Log.e("request1", error.getMessage());
                progressDialog.hide();
                toast.show();
            }
        });
        JsonObjectRequest request0 = new JsonObjectRequest(Request.Method.GET, url_login, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {
                    proceed = true;
                    if (!proceed) {
                        Toast toast = Toast.makeText(context, "Invalid Username or Password", duration);
                        progressDialog.hide();
                        toast.show();
                    } else if (proceed) {
                        JSONObject details;
                        //Get the user details from the server response
                        details = response.getJSONObject("users");
                        first_name = details.getString("first_name");
                        last_name = details.getString("last_name");
                        email = details.getString("email");
//                                        reg_id = details.getString("REGID");
////                                        Toast.makeText(context, reg_id, Toast.LENGTH_LONG).show();
////                                        usertype = details.getInt("usertype");
////                                        hostel = details.getString("hostel");
                        //After getting the user details, set the global variables in app for this session
                        global.setName(first_name.concat(" ").concat(last_name));
                        global.setEmail(email);
                        global.setHostel("boys");
                        global.setUser_type(1);
                        global.setIs_loggedin(true);
                        Log.d("hello", "onResponse: " + details);
                        //add the next request in the queue
                        myQueue.add(request8);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast toast = Toast.makeText(context, error.toString(), duration);
                Log.e("request0 in grid", error.toString());
                progressDialog.hide();
                toast.show();
            }
        });
        //Add the first request in the queue
        myQueue.add(request0);



    }
    public  void successCallback(){
        check=true;
        progressDialogOne.dismiss();
        Log.e("Comming","here");
        //  Log.e("EXAM COMPLAINTS",examComplains.toString());
        bundle.putString("NotificationList", notificationData.toString());
        bundle.putString("UserComplains",userComplains.toString());
        bundle.putString("HostelComplains",hostelComplains.toString());
        bundle.putString("InstiComplains",instiComplains.toString());
        bundle.putString("foodComplains",foodComplains.toString());
        bundle.putString("examComplains",examComplains.toString());
        bundle.putString("placementComplains",placementComplains.toString());
        bundle.putString("academicsComplains",academicsComplains.toString());
        Log.e("academics",academicsComplains.toString());

    }
}
