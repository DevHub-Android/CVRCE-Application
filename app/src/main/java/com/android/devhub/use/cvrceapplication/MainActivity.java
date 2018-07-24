package com.android.devhub.use.cvrceapplication;

import android.app.ProgressDialog;
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

import com.android.devhub.use.cvrceapplication.models.UserModel;

import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText password,regId;
    Button login;
    TextView register;
    ProgressDialog progressDialog;
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
                                userJson.getString("username")
                        );
                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
                        finish();
                        startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
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
}
