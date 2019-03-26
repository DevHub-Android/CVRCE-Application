package com.devhub.official.cvrceapplication.WebFragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.devhub.official.cvrceapplication.AdminLogin;
import com.devhub.official.cvrceapplication.AuthorityTypeCheck;
import com.devhub.official.cvrceapplication.Developers;
import com.devhub.official.cvrceapplication.HodLoginNew;
import com.devhub.official.cvrceapplication.MentorGrid;
import com.devhub.official.cvrceapplication.R;
import com.devhub.official.cvrceapplication.StudentGrid;


public class GrievanceHome extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Context mContext ;
    private LinearLayout student,teacher,hod,admin,principal,developers;







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.grievance_home, container, false);
        student = view.findViewById(R.id.student);
        teacher = view.findViewById(R.id.teacher);
        hod = view.findViewById(R.id.hod);
        admin= view.findViewById(R.id.admin);
        principal = view.findViewById(R.id.principal);
        developers = view.findViewById(R.id.developers);
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext,StudentGrid.class));
            }
        });
        teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext,MentorGrid.class));
            }
        });
        developers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext,Developers.class));
            }
        });
        principal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext,AuthorityTypeCheck.class));
            }
        });
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, AdminLogin.class));
            }
        });
        hod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext,HodLoginNew.class));
            }
        });
        return  view;

    }

    // TODO: Rename method, update argument and hook method into UI event


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext =context;

    }


}
