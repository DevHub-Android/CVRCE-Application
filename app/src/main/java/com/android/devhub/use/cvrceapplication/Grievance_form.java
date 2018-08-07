package com.android.devhub.use.cvrceapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

public class Grievance_form extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_home_main);

        AlertDialog.Builder dialog =new AlertDialog.Builder(this);
        dialog.setTitle("GRIEVANCE FORM");

        LayoutInflater inflater = LayoutInflater.from(this);
        View submit_layout = inflater.inflate(R.layout.activity_grievance_form,null);

        dialog.setView(submit_layout);
        dialog.setCancelable(false);
        dialog.setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getApplicationContext(),webHome_mainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"Submitted succefully",Toast.LENGTH_SHORT).show();

            }
        });
        dialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
                Intent intent = new Intent(getApplicationContext(),webHome_mainActivity.class);
                startActivity(intent);
            }
        });
        dialog.show();
    }
}
