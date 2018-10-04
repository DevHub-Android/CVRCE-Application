package com.devhub.use.cvrceapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.devhub.use.cvrceapplication.models.MentorModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class MentorLogin extends AppCompatActivity  {
    EditText empId,password;
    Button signIn;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_login);
        password = findViewById(R.id.pass);
        empId = findViewById(R.id.empId);
        signIn = findViewById(R.id.login);
        context = this;
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(empId.getText())){
                    empId.setError("Enter Employee ID");
                    empId.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(password.getText())){
                    password.setError("Enter Employee ID");
                    password.requestFocus();
                    return;
                }
                class MentorSingin extends AsyncTask<Void,Void,String>{
                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();

                    }

                    @Override
                    protected String doInBackground(Void... voids) {
                        RequestHandler requestHandler = new RequestHandler();
                        HashMap<String,String> hashMap = new HashMap<>();
                        hashMap.put("reg_id",empId.getText().toString());
                        hashMap.put("password",password.getText().toString());
                        return requestHandler.sendPostRequest(URLs.URL_MENTOR_LOGIN,hashMap);
                        //return null;
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            if(!jsonObject.getBoolean("error")){
                                JSONObject userJson =jsonObject.getJSONObject("user");
                                String empid = userJson.getString("empid");
                                String name = userJson.getString("name");
                                String department = userJson.getString("department");
                                MentorModel mentorModel = new MentorModel(empid,name);
                                SharedPrefMentor.getmInstance(context).userLogin(mentorModel);
                                successCallback();

                              //  finish();
                            }else
                            {
                                Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }
                MentorSingin mentorSingin = new MentorSingin();
                mentorSingin.execute();
            }
        });


    }
    public void successCallback()
    {
        Intent intent = new Intent(getApplicationContext(),MentorGrid.class);


        startActivity(intent);
    }


}