package com.devhub.use.cvrceapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {
    Button login;
   EditText empId,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        empId = findViewById(R.id.empId);
        pass =findViewById(R.id.pass);
        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(empId.getText()))
                {
                    empId.setError("Enter Admin Id");
                    empId.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(pass.getText()))
                {
                    pass.setError("Enter Password");
                    pass.requestFocus();
                    return;
                }

                if(empId.getText().toString().equals("admin-cvrce")&&pass.getText().toString().equals("cvrce123")){
                    startActivity(new Intent(getApplicationContext(),AdminActivity.class));

                }else{
                    Toast.makeText(getApplicationContext(),"Invalid Username Or Password",Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}
