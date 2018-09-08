package com.devhub.use.cvrceapplication;

import android.app.ProgressDialog;
import android.app.VoiceInteractor;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.devhub.use.cvrceapplication.Globals.Globals;
import com.devhub.use.cvrceapplication.models.UserModel;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {



    EditText password,regId;
    Button login;
    TextView register;
    ProgressDialog progressDialog;
    Globals global;
    static String serverAddress;
    static RequestQueue myQueue;
    JSONObject userComplains,hostelComplains,instiComplains,notificationData,foodComplaints,examComplaints,placementComplaints;
    String first_name;
    String last_name;
    String hostel;
    String email;
    String reg_id;
    int usertype;
    static boolean proceed;
    static String REGID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog = new ProgressDialog(this);
       // userName = (EditText)findViewById(R.id.uname);
        regId = (EditText)findViewById(R.id.regId);
        password = (EditText)findViewById(R.id.pass);
        login = (Button)findViewById(R.id.login);
        register = findViewById(R.id.register);

        global = (Globals)this.getApplication();



        //global.setServerAddress("http://192.168.1.4/www");
       // global.setServerAddress("http://172.29.8.90:8080/cvrce");


        //global.setServerAddress("http://172.29.8.90:8080/cvrce");


        serverAddress = URLs.SERVER_ADDR;

        myQueue = global.getVolleyQueue();
        if(SharedPrefManager.getInstance(this).isLoggedIn())
        {
            Log.e("It","works");
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(regId.getText().toString(),password.getText().toString());

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
    public void login(final String regid,final String pass){
        final Context context = getApplicationContext();
        final int duration = Toast.LENGTH_LONG;
        if(TextUtils.isEmpty(regId.getText())){
            regId.setError("Enter Registration ID");
            regId.requestFocus();

            return;
        }
        if(TextUtils.isEmpty(password.getText()))
        {
            password.setError("Enter Password");
            password.requestFocus();
            return;
        }
        class UserLogin extends AsyncTask<Void,Void,String> {
            @Override
            protected String doInBackground(Void... voids) {
                RequestHandler requestHandler = new RequestHandler();
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("reg_id",regid);
                hashMap.put("password",pass);
                return requestHandler.sendPostRequest(URLs.URL_LOGIN,hashMap);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressDialog.dismiss();
                try{
                    JSONObject jsonObject = new JSONObject(s);
                    if(!jsonObject.getBoolean("error"))
                    {
                        UserModel.REGID=regid;
                        //Toast.makeText(getApplicationContext(),"Comming Here!!",Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                        JSONObject userJson = jsonObject.getJSONObject("user");
                        Log.e("First_Name",userJson.getString("firstname"));
                        UserModel user = new UserModel(
                                userJson.getString("reg_id"),
                                userJson.getString("username"),
                                userJson.getString("firstname"),
                                userJson.getString("lastname"),
                                userJson.getString("email"),
                                userJson.getString("branch"),
                                userJson.getString("hostel")
                        );
                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
                       // finish();


                        String url_login = serverAddress.concat("/public/login.php?userid=");
                        url_login = url_login.concat(regid).concat("&password=").concat(pass);
                        String url_notification = serverAddress.concat("/public/notifications.php");
                        String url_user_complaints = serverAddress.concat("/public/user_complaints.php?user_id=").concat(regid);
                        String url_hostel_complaints = serverAddress.concat("/public/hostel_complaints.php?user_id=").concat(regid);
                        String url_insti_complaints = serverAddress.concat("/public/institute_complaints.php?user_id=").concat(regid);
                        String url_food_complaints = serverAddress.concat("/public/food_complaints.php?user_id=").concat(regid);
                        String url_exam_complaints = serverAddress.concat("/public/exam_complaints.php?user_id=").concat(regid);
                        String url_placement_complaints = serverAddress.concat("/public/placement_complaints.php?user_id=").concat(regid);
                        Log.e("URL_NOTIFY",url_login);


                        final JsonObjectRequest request4 = new JsonObjectRequest(Request.Method.GET,url_insti_complaints,null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                instiComplains = response;
                                successCallback();
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast toast = Toast.makeText(context, error.getMessage(), duration);
                                Log.e("request4",error.getMessage());
                                progressDialog.hide();
                                toast.show();
                            }
                        }) ;



                        final JsonObjectRequest request3 = new JsonObjectRequest(Request.Method.GET,url_hostel_complaints,null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                hostelComplains = response;
                                myQueue.add(request4 );
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast toast = Toast.makeText(context, error.getMessage(), duration);
                                Log.e("request3 hostel ",error.getMessage());
                                progressDialog.hide();
                                toast.show();
                            }
                        }) ;


                        final JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET,url_user_complaints,null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {

                                userComplains = response;
                                myQueue.add(request3 );


                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast toast = Toast.makeText(context, error.getMessage() , duration);
                                Log.e("request2 user",error.getMessage());
                                progressDialog.hide();
                                toast.show();
                            }
                        }) ;

                        final JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.GET,url_notification,null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {

                                notificationData = response;
                                myQueue.add(request2);
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast toast = Toast.makeText(context, error.getMessage(), duration);
                                Log.e("request1",error.getMessage());
                                progressDialog.hide();
                                toast.show();
                            }
                        }) ;
                        final JsonObjectRequest request5 = new JsonObjectRequest(Request.Method.GET,url_food_complaints,null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {

                            foodComplaints = response;
                            myQueue.add(request1);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast toast = Toast.makeText(context, error.getMessage(), duration);
                            Log.e("request1",error.getMessage());
                            progressDialog.hide();
                            toast.show();
                        }
                    }) ;

                        final JsonObjectRequest request6 = new JsonObjectRequest(Request.Method.GET,url_exam_complaints,null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {

                                examComplaints = response;
                                myQueue.add(request5);
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast toast = Toast.makeText(context, error.getMessage(), duration);
                                Log.e("request1",error.getMessage());
                                progressDialog.hide();
                                toast.show();
                            }
                        }) ;
                        final JsonObjectRequest request7 = new JsonObjectRequest(Request.Method.GET,url_placement_complaints,null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {

                                placementComplaints = response;
                                myQueue.add(request6);
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast toast = Toast.makeText(context, error.getMessage(), duration);
                                Log.e("request1",error.getMessage());
                                progressDialog.hide();
                                toast.show();
                            }
                        }) ;
                        JsonObjectRequest request0 = new JsonObjectRequest(Request.Method.GET,url_login,null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {

                                try {
                                    proceed = true;
                                    if(!proceed){
                                        Toast toast = Toast.makeText(context, "Invalid Username or Password", duration);
                                        progressDialog.hide();
                                        toast.show();
                                    }
                                    else if(proceed){
                                        JSONObject details;
                                        //Get the user details from the server response
                                        details = response.getJSONObject("users");
                                        first_name = details.getString("first_name");
                                        last_name = details.getString("last_name");
                                        email = details.getString("email");
//                                        reg_id = details.getString("REGID");
//                                        Toast.makeText(context, reg_id, Toast.LENGTH_LONG).show();
//                                        usertype = details.getInt("usertype");
//                                        hostel = details.getString("hostel");
                                        //After getting the user details, set the global variables in app for this session
                                        global.setName(first_name.concat(" ").concat(last_name));
                                        global.setEmail(email);
                                        global.setHostel("boys");
                                        global.setUser_type(1);
                                        global.setIs_loggedin(true);
                                        Log.d("hello", "onResponse: " + details);
                                        //add the next request in the queue
                                        myQueue.add(request7);

                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast toast = Toast.makeText(context, error.toString(), duration);
                                Log.e("request0",error.toString());
                                progressDialog.hide();
                                toast.show();
                            }
                        }) ;
                        //Add the first request in the queue
                        myQueue.add(request0);


                    }else
                    {
                        Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e)
                {
                    e.printStackTrace();
                }

            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog.setMessage("Logging in...");
                progressDialog.show();
            }
        }
        UserLogin ul = new UserLogin();
        ul.execute();
    }
    public void successCallback() {
        Intent intent = new Intent(this,StudentGrid.class);
        Bundle bundle = new Bundle();
        bundle.putString("NotificationList", notificationData.toString());
        bundle.putString("UserComplains",userComplains.toString());
        bundle.putString("HostelComplains",hostelComplains.toString());
        bundle.putString("InstiComplains",instiComplains.toString());
        bundle.putString("foodComplains",foodComplaints.toString());
        bundle.putString("examComplains",examComplaints.toString());
        bundle.putString("placementComplains",placementComplaints.toString());


        //hide progressBar here
        progressDialog.hide();

        intent.putExtras(bundle);
        startActivity(intent);
        finish();

    }
}
