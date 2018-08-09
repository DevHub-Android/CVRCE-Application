package com.android.devhub.use.cvrceapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class EmployeeLogin extends AppCompatActivity {
    EditText password,empId;
    Button signIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_login);
        password = findViewById(R.id.pass);
        empId = findViewById(R.id.empId);
        signIn = findViewById(R.id.login);
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
                class EmployeeSignin extends AsyncTask<Void,Void,String> {
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
                        return requestHandler.sendPostRequest(URLs.URL_EMPLOYEE_LOGIN,hashMap);
                        //return null;
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            if(!jsonObject.getBoolean("error")){
                                JSONObject userJson =jsonObject.getJSONObject("user");
                                String empid = userJson.getString("reg_id");
                                String position = userJson.getString("position");
                                String domain = userJson.getString("domain");
                                int priority = userJson.getInt("priority");
                                Intent intent = new Intent(getApplicationContext(),HostelAuthorityActivity.class);
                                intent.putExtra("domain",domain);
                                intent.putExtra("priority",priority);
                                intent.putExtra("position",position);
                                startActivity(intent);
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
                EmployeeSignin employeeSingin = new EmployeeSignin();
                employeeSingin.execute();
            }
        });


    }
}
