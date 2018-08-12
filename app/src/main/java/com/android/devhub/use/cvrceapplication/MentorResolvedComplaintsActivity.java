package com.android.devhub.use.cvrceapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.devhub.use.cvrceapplication.Adapters.Adapter_Resolved_Complaints;
import com.android.devhub.use.cvrceapplication.Globals.Globals;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MentorResolvedComplaintsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<JSONObject> items;
    Globals global;
    static RequestQueue myQueue;
    String serverAddress;
    Context context;
    String mentor_id;
    ProgressDialog dialog;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_resolved_complaints);

        items = new ArrayList<>();
        recyclerView = (RecyclerView)findViewById(R.id.resolvedRecyclerView);
        context = this;
        global = (Globals)this.getApplication();
        serverAddress = URLs.SERVER_ADDR;
        myQueue = global.getVolleyQueue();
        dialog = new ProgressDialog(context);

//        Intent intent = getIntent();
//        mentor_id = intent.getStringExtra("mentor_id");

        mentor_id = "5678";


        successCallBack();








    }

    private void successCallBack() {

        class FetchData extends AsyncTask<Void,Void,Void>{

            @Override
            protected Void doInBackground(Void... voids) {
                return null;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                dialog.setMessage("Loading Please Wait...");
                dialog.show();
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                dialog.dismiss();
                String addUrl = serverAddress.concat("/public/get_resolved_complaints.php")
                        .concat("?mentor_id=")
                        .concat(mentor_id);
                JsonObjectRequest request0 = new JsonObjectRequest(Request.Method.GET,addUrl, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("asasdadsad", "onResponse: " + response);
                        try {
                            JSONArray jsonArray = response.getJSONArray("root");
                            for(int i = 0;i<jsonArray.length();i++){
                                items.add(jsonArray.getJSONObject(i));
                            }
                            setAdapters();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Toast.makeText(global, "Student added Successfully", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("MENTOR ADD STUDENTS",error.toString());
                        Toast toast = Toast.makeText(context, "error in mentor add students"+error.getMessage(), Toast.LENGTH_LONG);
                        toast.show();
                    }
                });
                //Add the first request in the queue
                myQueue.add(request0);

            }
        }
        FetchData fetchData = new FetchData();
        fetchData.execute();


    }

    private void setAdapters() {
        Adapter_Resolved_Complaints adapter_resolved_complaints = new Adapter_Resolved_Complaints(this,items);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter_resolved_complaints);
    }
}
