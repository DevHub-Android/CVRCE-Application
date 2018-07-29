package com.android.devhub.use.cvrceapplication.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.devhub.use.cvrceapplication.Adapters.Adapter_Notifications;
import com.android.devhub.use.cvrceapplication.HomeActivity;
import com.android.devhub.use.cvrceapplication.MainActivity;
import com.android.devhub.use.cvrceapplication.R;

import org.json.JSONObject;


public class FragmentNotifications extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    JSONObject notifications_data;





    public FragmentNotifications() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HomeActivity activity = (HomeActivity) getActivity();
        notifications_data =  activity.getNotificationData();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.notification_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        HomeActivity activity = (HomeActivity) getActivity();
        Context context = (HomeActivity) getContext();
        notifications_data =  activity.getNotificationData();
        mAdapter = new Adapter_Notifications(notifications_data,activity,context);
        Log.i("hagga",notifications_data.toString());

        mRecyclerView.setAdapter(mAdapter);
        return view;


    }

}
