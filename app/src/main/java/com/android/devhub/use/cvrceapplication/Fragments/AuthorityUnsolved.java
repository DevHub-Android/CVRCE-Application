package com.android.devhub.use.cvrceapplication.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.devhub.use.cvrceapplication.Adapters.Adapter_Complaints_Authority;
import com.android.devhub.use.cvrceapplication.HostelAuthorityActivity;
import com.android.devhub.use.cvrceapplication.R;

import org.json.JSONObject;


public class AuthorityUnsolved extends Fragment {
   private JSONObject unsolved_complaints_data;
   private RecyclerView mRecyclerView;
   private  RecyclerView.Adapter mRecyclerViewAdapter;
   private  RecyclerView.LayoutManager mRecylclerViewLayoutManager;
   public AuthorityUnsolved(){

   }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HostelAuthorityActivity authorityActivity = (HostelAuthorityActivity)getActivity();
        unsolved_complaints_data = authorityActivity.getUnsolvedComplaints();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       final  View view = inflater.inflate(R.layout.fragment_authority_unsolved,container,false);
       mRecyclerView = view.findViewById(R.id.complaint_recycler_view);
       mRecyclerView.setHasFixedSize(true);
       mRecylclerViewLayoutManager = new LinearLayoutManager(getContext());
       mRecyclerView.setLayoutManager(mRecylclerViewLayoutManager);
       HostelAuthorityActivity authorityActivity = (HostelAuthorityActivity)getActivity();
       unsolved_complaints_data = authorityActivity.getUnsolvedComplaints();
       Context context = (HostelAuthorityActivity)getContext();
       mRecyclerViewAdapter = new Adapter_Complaints_Authority(unsolved_complaints_data,authorityActivity,context,"authority");
       mRecyclerView.setAdapter(mRecyclerViewAdapter);





        return view;
    }
}
