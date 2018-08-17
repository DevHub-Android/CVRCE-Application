package com.android.devhub.use.cvrceapplication.Fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.devhub.use.cvrceapplication.Adapters.Adapter_Resolved_Complaints;
import com.android.devhub.use.cvrceapplication.HostelAuthorityActivity;
import com.android.devhub.use.cvrceapplication.R;

import org.json.JSONObject;

import java.util.ArrayList;


public class AuthoritySolved extends Fragment {
   private RecyclerView mRecyclerView;
   private RecyclerView.Adapter mRecyclerViewAdapter;
   private RecyclerView.LayoutManager mRecyclerViewLayoutManager;
   ArrayList<JSONObject> solved_complaints_data;
   public AuthoritySolved(){

   }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HostelAuthorityActivity authorityActivity = (HostelAuthorityActivity) getActivity();
        solved_complaints_data = authorityActivity.getSolvedComplaints();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         final View view = inflater.inflate(R.layout.fragment_authority_solved,container,false);
         mRecyclerView = view.findViewById(R.id.resolvedRecyclerView);
         mRecyclerView.setHasFixedSize(true);
         mRecyclerViewLayoutManager = new LinearLayoutManager(getContext());
         mRecyclerView.setLayoutManager(mRecyclerViewLayoutManager);
         HostelAuthorityActivity authorityActivity = (HostelAuthorityActivity)getActivity();
        solved_complaints_data = authorityActivity.getSolvedComplaints();
        Log.e("SOLVED COMPLAINTS_DATA",solved_complaints_data.toString());
        Context context = getContext();
        mRecyclerViewAdapter = new Adapter_Resolved_Complaints(context,solved_complaints_data);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

         return view;
    }
}
