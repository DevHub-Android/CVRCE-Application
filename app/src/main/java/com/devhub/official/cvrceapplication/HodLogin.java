package com.devhub.official.cvrceapplication;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.devhub.official.cvrceapplication.models.HodModel;
import com.devhub.official.cvrceapplication.models.MentorModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class HodLogin extends AppCompatActivity  {
    EditText empId,password;
    Button signIn;
    Context context;
    String empid ;
    String name ;
    String department;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hod_login_new);
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
                                 empid = userJson.getString("empid");
                                 name = userJson.getString("name");
                                 Log.e("line77loginHod",name);
                                 department = userJson.getString("department");
                               //  Log.e("deptLOGINBEFORESUCCESS",department);
                                HodModel hodModel = new HodModel(empid,name,department);
                                //SharedPrefMentor.getmInstance(context).userLogin(hodModel);
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
    {        Log.e("LOGIN","in sucesscallback of login");


        Intent intent = new Intent(getApplicationContext(),HodGrid.class);
        intent.putExtra("empId",empid);
        intent.putExtra("name",name);
        intent.putExtra("department",department);
      //  Log.e("deptLOGIN",department);
        Log.e("nameLOGIN",name);


        //   startActivity(intent);
    }


}