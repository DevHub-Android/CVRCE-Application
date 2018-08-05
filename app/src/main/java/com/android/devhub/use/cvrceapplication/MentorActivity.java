package com.android.devhub.use.cvrceapplication;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.devhub.use.cvrceapplication.Globals.Globals;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

public class MentorActivity extends AppCompatActivity {

    String studRegId;
    String mentorId;
    EditText studentEditText;
    static RequestQueue myQueue;
    Globals global;
    Context context;
    String serverAddress;
    String addUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor);

        context = this;
        global = (Globals)this.getApplication();
        serverAddress = URLs.SERVER_ADDR;
        myQueue = global.getVolleyQueue();





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mentor_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.addStudent:
                addStudent();
                return true;
            case R.id.students:
                showStudents();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void addStudent() {
        LayoutInflater factory = LayoutInflater.from(this);
        final View deleteDialogView = factory.inflate(R.layout.add_student_dialog, null);
        final AlertDialog deleteDialog = new AlertDialog.Builder(this).create();
        deleteDialog.setView(deleteDialogView);
        studentEditText = deleteDialogView.findViewById(R.id.addStudentText);
        deleteDialogView.findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studRegId = studentEditText.getText().toString();
                mentorId = "5678";
                addUrl = serverAddress.concat("/admin/addStudent.php").concat("?user_id=").concat(studRegId).concat("&mentor_id=").
                        concat(mentorId);
                JsonObjectRequest request0 = new JsonObjectRequest(Request.Method.GET,addUrl, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(global, "Student added Successfully", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast toast = Toast.makeText(context, "Student added Successfully", Toast.LENGTH_LONG);
                        toast.show();
                    }
                });
                //Add the first request in the queue
                myQueue.add(request0);


            }
        });
        deleteDialogView.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDialog.dismiss();
            }
        });

        deleteDialog.show();
    }

    private void showStudents(){

    }
}
