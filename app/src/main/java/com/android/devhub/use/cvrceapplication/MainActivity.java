package com.android.devhub.use.cvrceapplication;

import android.app.ProgressDialog;
import android.app.VoiceInteractor;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.devhub.use.cvrceapplication.Globals.Globals;
import com.android.devhub.use.cvrceapplication.models.UserModel;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

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
    JSONObject userComplains,hostelComplains,instiComplains,notificationData;

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

        global = ((Globals) this.getApplication());

        global.setServerAddress("http://192.168.43.226/cvrce");

        serverAddress = global.getServerAddress();

        myQueue = global.getVolleyQueue();

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
                        Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                        JSONObject userJson = jsonObject.getJSONObject("user");
                        UserModel user = new UserModel(
                                userJson.getString("reg_id"),
                                userJson.getString("username"),
                                userJson.getString("branch"),
                                userJson.getString("hostel")
                        );
                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
                        finish();

                        String url_notification = serverAddress.concat("/public/notifications.php");
                        String url_user_complaints = serverAddress.concat("/public/user_complaints.php");
                        String url_hostel_complaints = serverAddress.concat("/public/hostel_complaints.php");
                        String url_insti_complaints = serverAddress.concat("/public/institute_complaints.php");

                        final JsonObjectRequest request4 = new JsonObjectRequest(Request.Method.GET,url_insti_complaints,null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                instiComplains = response;
                                successCallback();
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast toast = Toast.makeText(context, "Network Error", duration);
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
                                Toast toast = Toast.makeText(context, "Network Error", duration);
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
                                Toast toast = Toast.makeText(context, "Network Error", duration);
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
                                Toast toast = Toast.makeText(context, "Network Error", duration);
                                progressDialog.hide();
                                toast.show();
                            }
                        }) ;
                        myQueue.add(request1);


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
        Intent intent = new Intent(this,HomeActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("NotificationList", notificationData.toString());
        bundle.putString("UserComplains",userComplains.toString());
        bundle.putString("HostelComplains",hostelComplains.toString());
        bundle.putString("InstiComplains",instiComplains.toString());


        //hide progressBar here
        progressDialog.hide();

        intent.putExtras(bundle);
        startActivity(intent);
        finish();

    }
}
