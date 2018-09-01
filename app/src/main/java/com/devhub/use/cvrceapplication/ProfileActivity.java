package com.devhub.use.cvrceapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.devhub.use.cvrceapplication.models.UserModel;

public class ProfileActivity extends AppCompatActivity {
    TextView regId,uname,branch,hostel;
    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        if(!SharedPrefManager.getInstance(this).isLoggedIn())
        {
            finish();
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        regId = findViewById(R.id.reg);
        uname = findViewById(R.id.uname);
        branch=findViewById(R.id.branch);
        hostel=findViewById(R.id.hostel);
        logout = findViewById(R.id.logout);
        UserModel user = SharedPrefManager.getInstance(this).getUser();
        regId.setText("REGISTRATION ID : "+String.valueOf(user.getRegid()));
        uname.setText("USERNAME : "+String.valueOf(user.getUsername()));
        branch.setText("BRANCH : "+String.valueOf(user.getBranch()));
        hostel.setText("HOSTEL : "+String.valueOf(user.getHostel()));
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                SharedPrefManager.getInstance(getApplicationContext()).logout();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

    }
}
