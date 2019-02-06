package com.devhub.official.cvrceapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.devhub.official.cvrceapplication.models.AuthorityModel;

public class AuthorityTypeCheck extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authority_type_check);
        if(SharedPrefEmployee.getmInstance(this).isLoggedIn())
        {
            AuthorityModel model = SharedPrefEmployee.getmInstance(this).getMentor();
            String domain = model.getDomain();
            Log.e("Model domain",model.getDomain());
            Intent intent ;
            if(domain.equals("all")){
                intent = new Intent(getApplicationContext(),PrincipalGridActivity.class);
            }else {
                intent = new Intent(getApplicationContext(), HostelAuthorityActivity.class);
            }
            intent.putExtra("domain",domain);
            intent.putExtra("priority",model.getPriority());
            intent.putExtra("position",model.getPosition());
            intent.putExtra("first_name",model.getFirst_name());
            intent.putExtra("reg_id",model.getEmpid());
            startActivity(intent);
            finish();
        }else{
            startActivity(new Intent(getApplicationContext(),EmployeeLogin.class));
            finish();
        }
    }
}
