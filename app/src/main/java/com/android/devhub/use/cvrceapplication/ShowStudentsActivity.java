package com.android.devhub.use.cvrceapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.devhub.use.cvrceapplication.Adapters.Adapter_Show_Students;
import com.android.devhub.use.cvrceapplication.Globals.Globals;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ShowStudentsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<JSONObject> roots;
    Globals global;
    String serverAddress;
    static RequestQueue myQueue;
    String urlShowStudents;
    String mentor_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_students);

        Intent intent = getIntent();
        mentor_id = intent.getStringExtra("mentor_id");
        Log.d("mentorid", "onCreate: " + mentor_id);

        roots = new ArrayList<>();
        serverAddress = URLs.SERVER_ADDR;
        Log.d("mentorid", "onCreate: " + serverAddress);
        global = (Globals)this.getApplication();
        myQueue = global.getVolleyQueue();
        recyclerView = (RecyclerView)findViewById(R.id.showStudentRecyclerView);

        //create an api call to the show the students
        urlShowStudents = serverAddress.concat("/admin/showStudents.php").concat("?mentor_id=").concat(mentor_id);
        JsonObjectRequest request0 = new JsonObjectRequest(Request.Method.GET, urlShowStudents, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                JSONObject object = null;
                JSONArray myJsonArray = null;
                try {
                     myJsonArray = response.getJSONArray("root");
                    Log.e("uaisd", "onResponse: " + myJsonArray);
                     for(int i = 0;i<myJsonArray.length();i++){
                         Log.e("yo", "onResponse: " + myJsonArray.getJSONObject(i));
                         roots.add(myJsonArray.getJSONObject(i));
                     }
                }catch(Exception e){
                    Log.d("Show students", "onResponse: " + e.getMessage());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast toast = Toast.makeText(ShowStudentsActivity.this, "Network Error", Toast.LENGTH_LONG);
                toast.show();
            }
        });
        //Add the first request in the queue
        myQueue.add(request0);
        if(roots.size()!=0)
        {
            Log.e("Comming here","HELLO");
            for(int i=0;i<roots.size();i++)
            {
                Log.e("Root element",roots.get(i).toString());
            }
            Adapter_Show_Students myRecyclerViewAdaptar = new Adapter_Show_Students(ShowStudentsActivity.this,roots);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ShowStudentsActivity.this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(myRecyclerViewAdaptar);

        }
        class showStudent extends  AsyncTask<Void,Void,ArrayList<JSONObject>>
        {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected ArrayList<JSONObject> doInBackground(Void... voids) {
                urlShowStudents = serverAddress.concat("/admin/showStudents.php").concat("?mentor_id=").concat(mentor_id);
                JsonObjectRequest request0 = new JsonObjectRequest(Request.Method.GET, urlShowStudents, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        JSONObject object = null;
                        JSONArray myJsonArray = null;
                        try {
                            myJsonArray = response.getJSONArray("root");
                            Log.e("uaisd", "onResponse: " + myJsonArray);
                            for(int i = 0;i<myJsonArray.length();i++){
                                Log.e("yo", "onResponse: " + myJsonArray.getJSONObject(i));
                                roots.add(myJsonArray.getJSONObject(i));
                            }
                        }catch(Exception e){
                            Log.d("Show students", "onResponse: " + e.getMessage());
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast toast = Toast.makeText(ShowStudentsActivity.this, "Network Error", Toast.LENGTH_LONG);
                        toast.show();
                    }
                });
                myQueue.add(request0);
                return roots;

            }

            @Override
            protected void onPostExecute(ArrayList<JSONObject> root) {
                super.onPostExecute(root);
                Log.e("Comming here","HELLO");
            for(int i=0;i<root.size();i++)
            {
                Log.e("Root element",root.get(i).toString());
            }
            Adapter_Show_Students myRecyclerViewAdaptar = new Adapter_Show_Students(ShowStudentsActivity.this,root);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ShowStudentsActivity.this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(myRecyclerViewAdaptar);
            }
        }
        showStudent ss = new showStudent();
        ss.execute();





    }
}
