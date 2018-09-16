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
        setContentView(R.layout.activity_student_login);
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
                    if(!jsonObject.getBoolean("error")) {
                        UserModel.REGID = regid;
                        //Toast.makeText(getApplicationContext(),"Comming Here!!",Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        JSONObject userJson = jsonObject.getJSONObject("user");
                        Log.e("First_Name", userJson.getString("firstname"));
                        UserModel user = new UserModel(
                                userJson.getString("reg_id"),
                                userJson.getString("username"),
                                userJson.getString("firstname"),
                                userJson.getString("lastname"),
                                userJson.getString("email"),
                                userJson.getString("branch"),
                                userJson.getString("hostel"),
                                userJson.getString("password")
                        );
                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
                        successCallback();
                        // finish();


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



        //hide progressBar here
        progressDialog.hide();


        startActivity(intent);
        finish();

    }
}
