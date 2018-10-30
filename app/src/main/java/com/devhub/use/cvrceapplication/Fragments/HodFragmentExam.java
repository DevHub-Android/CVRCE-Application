package com.devhub.use.cvrceapplication.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.devhub.use.cvrceapplication.Adapters.Adapter_Complaints_Authority;
import com.devhub.use.cvrceapplication.Globals.Globals;
import com.devhub.use.cvrceapplication.HodHome;
import com.devhub.use.cvrceapplication.MentorHome;
import com.devhub.use.cvrceapplication.R;
import com.devhub.use.cvrceapplication.URLs;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;


public class HodFragmentExam extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    JSONObject complaints_data;
    String serverAddress;
    Globals global;
    static RequestQueue myQueue;
    SwipeRefreshLayout swipeRefreshLayout;
    String mentorId;

    public HodFragmentExam() {

    }

    public void SetMntr(String mntor) {
        mentorId=mntor;
        // Required empty public constructor
        Log.e("view err",mentorId);

    }

    //  Log.e("view err","in oncr adapterupdate waala");


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        HodHome activity = (HodHome) getActivity();
        complaints_data = activity.getExamComplains();
        serverAddress = URLs.SERVER_ADDR;
        global = (Globals)activity.getApplication();
        serverAddress = URLs.SERVER_ADDR;
        myQueue = global.getVolleyQueue();
        Log.e("viewerr",mentorId);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("viewerr","in oncreate view");
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_complaints, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.complaint_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        callAdapters();

        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getContext(), "Hey I am Here", Toast.LENGTH_SHORT).show();

                updateData();

            }
        });


        return view;
    }

    private void updateData() {
        class FetchData extends AsyncTask<Void,Void,Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);Log.e("view err","in onPostExe");

                HodHome activity = (HodHome) getActivity();
                String url = serverAddress.concat("/public/hod_exam_complaints.php").concat("?mentor_id=").concat(mentorId);
                JsonObjectRequest request0 = new JsonObjectRequest(Request.Method.GET,url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        //Toast.makeText(global, "Student added Successfully", Toast.LENGTH_SHORT).show();
                        complaints_data = response;
                        Log.d("Chutiya data dekh", "onResponse: " + response);
                        //mAdapter.notifyDataSetChanged();
                        callUpdatedAdapters(complaints_data);
                        swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(getContext(), "Updated", Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Bhai Bhai",error.toString());
                        Toast toast = Toast.makeText(getContext(), "Fragements"+error.getMessage(), Toast.LENGTH_LONG);
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

    private void callUpdatedAdapters(JSONObject complaints) {
        // specify an adapter (see also next example)
        Log.e("view err","in oncr adapterupdate waala");
        HodHome activity = (HodHome) getActivity();
        Context context = (HodHome) getContext();
        complaints_data =  activity.getExamComplains();
        mAdapter = new Adapter_Complaints_Authority(complaints_data,activity,context,"HOD",1,mentorId,activity.getFirst_name(),
                activity.getLast_name());        //Log.i("hagga", complaints_data.toString());

        mRecyclerView.setAdapter(mAdapter);
    }

    private void callAdapters(){
        Log.e("viewerr","in oncr adapterupdate waala");

        // specify an adapter (see also next example)
        HodHome activity = (HodHome) getActivity();
        Context context = (HodHome) getContext();
        complaints_data =  activity.getExamComplains();
        mAdapter = new Adapter_Complaints_Authority(complaints_data,activity,context,"HOD",1,
                mentorId,
                activity.getFirst_name(),
                activity.getLast_name());        //Log.i("hagga", complaints_data.toString());

        mRecyclerView.setAdapter(mAdapter);
    }

}
