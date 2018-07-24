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
import android.widget.Toast;

import com.android.devhub.use.cvrceapplication.models.UserModel;

import org.json.JSONObject;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    EditText editregId,edituname,editpass,editcnfPass;
    private ProgressDialog progressDialog;
    Button regBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(this);
        setContentView(R.layout.activity_register);
        editregId = (EditText)findViewById(R.id.regId);
        edituname = (EditText)findViewById(R.id.uname);
        editpass = (EditText)findViewById(R.id.pass);
        editcnfPass = (EditText)findViewById(R.id.cnfpass);
        regBtn = (Button)findViewById(R.id.register);
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register(editregId.getText().toString(),edituname.getText().toString(),
                        editpass.getText().toString(),editcnfPass.getText().toString());
            }
        });

    }
    public void register(final String regId, final String uname,final String pass, String cnfPass){
       if(TextUtils.isEmpty(regId)){
        editregId.setError("Enter Registration ID");
        editregId.requestFocus();
        return;

       }
        if(TextUtils.isEmpty(uname)){
            edituname.setError("Enter User name");
            edituname.requestFocus();
            return;

        }
        if(TextUtils.isEmpty(pass)){
            editpass.setError("Enter Password");
            editpass.requestFocus();
            return;

        }
        if(TextUtils.isEmpty(cnfPass)){
            editcnfPass.setError("Retype Password");
            editcnfPass.requestFocus();
            return;

        }
        class RegistetUser extends AsyncTask<Void,Void,String>{

            @Override
            protected String doInBackground(Void... voids) {
                RequestHandler requestHandler = new RequestHandler();
                HashMap<String,String> params = new HashMap<>();
                params.put("reg_id",regId);
                params.put("username",uname);
                params.put("password",pass);
                return requestHandler.sendPostRequest(URLs.URL_REGISTER,params);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog.setMessage("Registering...");
                progressDialog.show();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressDialog.dismiss();
                try{
                    JSONObject jsonObject = new JSONObject(s);
                    if(!jsonObject.getBoolean("error"))
                    {
                        Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                        JSONObject userJson = jsonObject.getJSONObject("user");
                        UserModel user = new UserModel(userJson.getString("reg_id"),userJson.getString("username"));
                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
                        finish();
                        startActivity(new Intent(getApplicationContext(),ProfileActivity.class));

                    }else{
                        Toast.makeText(getApplicationContext(),"Something Went Wrong",Toast.LENGTH_SHORT).show();
                    }


                }catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        }
        RegistetUser ru = new RegistetUser();
        ru.execute();
    }
}
