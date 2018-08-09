package com.android.devhub.use.cvrceapplication.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class Adapter_Complaints_Authority extends RecyclerView.Adapter<Adapter_Complaints_Authority.ViewHolder>{


    static String serverAddress;
    static RequestQueue myQueue;
    final int duration = Toast.LENGTH_LONG;
    Globals global;
    Context context;
    String is_seen_domain;
    //we pass the complaints_data to the next activity
    JSONObject complaint_details;
    Activity parent;
    public static final String my_pref = "CHECKED_DATA";
    public static final String CHECKED_KEY = "is_checked";

    private ArrayList<Data_Model_Complaints> ComplaintsData ;


    public Adapter_Complaints_Authority(JSONObject ndata, Activity a, Context c,String domain) {

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
        is_seen_domain = domain;
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
        public TextView registrationNumaber;
        public CheckBox is_seen;
        public ViewHolder(final View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.complaint_title);
            description = (TextView) v.findViewById(R.id.complaint_description);
            postedBy = (TextView) v.findViewById(R.id.complaint_posted_by);
            createdAt = (TextView) v.findViewById(R.id.complaint_created_at);
            cardView = (CardView) v.findViewById(R.id.complaint_card_view);
            registrationNumaber = (TextView)v.findViewById(R.id.reg_no);
            is_seen = (CheckBox)v.findViewById(R.id.is_seen_checkbox);
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public Adapter_Complaints_Authority.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.element_complaints_faculty, parent, false);
        Adapter_Complaints_Authority.ViewHolder vh = new Adapter_Complaints_Authority.ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final Adapter_Complaints_Authority.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Data_Model_Complaints item =  ComplaintsData.get(position);
        String a = "At: "+item.date;
        String b = "By: "+item.name;
        String registration = "Regid: " + item.reg_id;
        holder.title.setText(item.title);
        holder.createdAt.setText(a);
        holder.postedBy.setText(b);
        holder.description.setText(item.description);
        holder.registrationNumaber.setText(registration);
        int is_resolved = item.isresolved;
        final int complaint_id = item.complaint_id;

        if(is_seen_domain.equals("mentor")){
            int is_seen = item.mentor_seen;

            if(is_seen==1) {
                holder.is_seen.setChecked(true);
            }
            else if(is_seen==0){
                holder.is_seen.setChecked(false);
            }

            holder.is_seen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.mentor_seen = 1;
                    holder.is_seen.setChecked(true);
                    int complains_id = complaint_id;
                    String url_upvote = serverAddress.concat("/public/is_seen_mentor.php?complaint_id=").concat(String.valueOf(complains_id));
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
        }else{
            SharedPreferences myPreferences = context.getSharedPreferences(my_pref,Context.MODE_PRIVATE);
            String is_checked = myPreferences.getString(CHECKED_KEY,null);
            if(is_checked!=null){
                holder.is_seen.setChecked(true);
            }else if(is_checked==null){
                holder.is_seen.setChecked(false);
            }

            holder.is_seen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.mentor_seen = 1;
                    holder.is_seen.setChecked(true);
                    final int complains_id = complaint_id;
                    String url_upvote = serverAddress.concat("/public/position_seen.php?complaint_id=").concat(String.valueOf(complains_id))
                            .concat("&priority=")
                            .concat(String.valueOf(item.priority));
                    Log.i("haggax",url_upvote);
                    JsonObjectRequest request0 = new JsonObjectRequest(Request.Method.GET, url_upvote, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            Log.i("hagga3", "response");
                            SharedPreferences.Editor editor = context.getSharedPreferences(my_pref,Context.MODE_PRIVATE).edit();
                            editor.putString(CHECKED_KEY,"Not Null");
                            editor.apply();
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


        }




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
                        Toast toast = Toast.makeText(context, "Network Error", duration);
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
