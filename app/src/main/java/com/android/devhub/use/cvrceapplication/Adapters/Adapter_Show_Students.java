package com.android.devhub.use.cvrceapplication.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.devhub.use.cvrceapplication.R;

import org.json.JSONObject;

import java.util.ArrayList;

public class Adapter_Show_Students extends RecyclerView.Adapter<Adapter_Show_Students.MyViewHolder> {

    private Context context;
    LayoutInflater inflater;
    ArrayList<JSONObject> objects;


    public Adapter_Show_Students(Context myContext, ArrayList<JSONObject> objects){
        context = myContext;
        this.objects = objects;
        inflater = LayoutInflater.from(this.context);



    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = inflater.inflate(R.layout.element_show_student,parent,false);
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        try {
            String first_name = objects.get(position).getString("first_name");
            String contact = objects.get(position).getString("contact");
            String registrationId = objects.get(position).getString("REGID");
            Log.d("items", "onBindViewHolder: " + first_name);
            Log.d("size", "onBindViewHolder: " + objects.size());
            holder.contactTextView.setText(contact);
            holder.regidTextView.setText(registrationId);
            holder.nameTextView.setText(first_name);
        }catch (Exception e){
            Log.d("could be", "onBindViewHolder: " + e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView,regidTextView,contactTextView;
        public MyViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            regidTextView = itemView.findViewById(R.id.regidTextView);
            contactTextView = itemView.findViewById(R.id.contactTextView);
        }
    }


}
