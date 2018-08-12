package com.android.devhub.use.cvrceapplication.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.devhub.use.cvrceapplication.ComplaintsActivity;
import com.android.devhub.use.cvrceapplication.Globals.Globals;
import com.android.devhub.use.cvrceapplication.R;
import com.android.devhub.use.cvrceapplication.models.Data_Model_Complaints;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Adapter_Complaints extends RecyclerView.Adapter<Adapter_Complaints.ViewHolder>{


    static String serverAddress;
    static RequestQueue myQueue;
    final int duration = Toast.LENGTH_LONG;
    Globals global;
    Context context;
    //we pass the complaints_data to the next activity
    JSONObject complaint_details;
    Activity parent;

    private ArrayList<Data_Model_Complaints> ComplaintsData ;


    public Adapter_Complaints(JSONObject ndata, Activity a, Context c) {

        JSONArray object1 = null;
        JSONArray object2 = null;
        try {

            object1 = ndata.getJSONObject("root").getJSONArray("complaints");
            object2 = ndata.getJSONObject("root").getJSONArray("users");

        }catch (JSONException e) {
            e.printStackTrace();
        }
        ComplaintsData = Data_Model_Complaints.fromJson(object1, object2);

        context = c;
        parent = a;
        global = ((Globals) a.getApplication());
        serverAddress = global.getServerAddress();
        myQueue = global.getVolleyQueue();


    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public CardView cardView;
        public TextView title;
        public TextView description;
        public TextView postedBy;
        public TextView createdAt;
        public CheckBox reslovedCheckBox;
        public CheckBox mentor_seen_checkbox;
        public TextView position_seen_textview;
        public ViewHolder(final View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.complaint_title);
            description = (TextView) v.findViewById(R.id.complaint_description);
            postedBy = (TextView) v.findViewById(R.id.complaint_posted_by);
            createdAt = (TextView) v.findViewById(R.id.complaint_created_at);
            cardView = (CardView) v.findViewById(R.id.complaint_card_view);
            reslovedCheckBox = (CheckBox)v.findViewById(R.id.is_reslove_box);
            mentor_seen_checkbox = (CheckBox)v.findViewById(R.id.seen_mentor_box);
            position_seen_textview = (TextView)v.findViewById(R.id.position_seen_textview);
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public Adapter_Complaints.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.element_complaints, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Data_Model_Complaints item =  ComplaintsData.get(position);
        String a = "At: "+item.date;
        String b = "By: "+item.name;
        String c = String.valueOf(item.up_vote);
        String d = String.valueOf(item.down_vote);
        holder.title.setText(item.title);
        holder.createdAt.setText(a);
        holder.postedBy.setText(b);
        holder.description.setText(item.description);
        int is_resolved = item.isresolved;
        int is_seen_mentor = item.mentor_seen;
        String position_seen = item.positon_seen;
        final int complaint_id = item.complaint_id;

        if(is_resolved==1) {
            holder.reslovedCheckBox.setChecked(true);
        }
        else if(is_resolved==0){
            holder.reslovedCheckBox.setChecked(false);
        }



        holder.reslovedCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.isresolved = 1;
                holder.reslovedCheckBox.setChecked(true);
                int complains_id = complaint_id;
                String title = item.title;
                String created_at = item.date;
                int priority = item.priority;
                String description = item.description;
                String url_upvote = serverAddress.concat("/public/is_resolved.php?complaint_id=").concat(String.valueOf(complains_id))
                        .concat("&description=")
                        .concat(description)
                        .concat("&title=")
                        .concat(title)
                        .concat("&created_at=")
                        .concat(created_at)
                        .concat("&priority=")
                        .concat(String.valueOf(priority))
                        .concat("&issued_by=")
                        .concat(item.reg_id);

                url_upvote = url_upvote.replaceAll("\\s+","%20");
                Log.i("haggax",url_upvote);
                JsonObjectRequest request0 = new JsonObjectRequest(Request.Method.GET, url_upvote, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("hagga3", "response");
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast toast = Toast.makeText(context, "Network Error", duration);
                        toast.show();
                    }
                });
                //Add the first request in the queue
                myQueue.add(request0);

            }

        });

        if(is_seen_mentor==1) {
            holder.mentor_seen_checkbox.setChecked(true);
        }
        else if(is_seen_mentor==0){
            holder.mentor_seen_checkbox.setChecked(false);
            holder.mentor_seen_checkbox.setEnabled(false);
        }

        holder.position_seen_textview.setText("position: " + item.positon_seen);








        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                Log.i("haggaxx","getting here1");
                String url_complaints_detail = serverAddress.concat("/public/comment_details.php?complaint_id=").
                        concat(String.valueOf(complaint_id));

                JsonObjectRequest request0 = new JsonObjectRequest(Request.Method.GET,url_complaints_detail,
                        null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        complaint_details = response;
                        Intent intent = new Intent(v.getContext(),ComplaintsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("complaint_details", complaint_details.toString());
                        bundle.putString("title",item.title);
                        bundle.putString("postedBy",item.name);
                        bundle.putString("user_id",item.reg_id);
                        bundle.putString("created_at",item.date);
                        bundle.putString("description",item.description);
                        bundle.putString("upvote", String.valueOf(item.up_vote));
                        bundle.putString("downvote", String.valueOf(item.down_vote));
                        bundle.putString("id", String.valueOf(item.complaint_id));
                        intent.putExtras(bundle);
                        v.getContext().startActivity(intent);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast toast = Toast.makeText(context,"Network Error", duration);
                        toast.show();
                    }
                }) ;
                //Add the first request in the queue
                Log.i("yo","getting here2");
                myQueue.add(request0);
            }
        });



    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return ComplaintsData.size();
    }

}