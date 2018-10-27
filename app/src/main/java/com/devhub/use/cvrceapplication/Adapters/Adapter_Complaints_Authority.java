package com.devhub.use.cvrceapplication.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.devhub.use.cvrceapplication.ComplaintsActivity;
import com.devhub.use.cvrceapplication.ComplaintsAuthorityActivity;
import com.devhub.use.cvrceapplication.Globals.Globals;
import com.devhub.use.cvrceapplication.MentorGrid;
import com.devhub.use.cvrceapplication.R;
import com.devhub.use.cvrceapplication.URLs;
import com.devhub.use.cvrceapplication.models.Data_Model_Complaints;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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
    int priority;
    String emp_id;
    int complaint_id;
    String first_name;
    String last_name;

    AlertDialog alertDialog;
    public static final String my_pref = "CHECKED_DATA";
    public static final String CHECKED_KEY = "is_checked";
    ViewGroup parentView;

    private ArrayList<Data_Model_Complaints> ComplaintsData ;


    public Adapter_Complaints_Authority(JSONObject ndata, Activity a, Context c,String domain,int priority,String emp_id,String first_name,
                                        String last_name) {

        JSONArray object1 = null;
        JSONArray object2 = null;
        try {

            object1 = ndata.getJSONObject("root").getJSONArray("complaints");
            object2 = ndata.getJSONObject("root").getJSONArray("users");
            Log.e("obj1",object1.toString());
            Log.e("obj2",object2.toString());

        }catch (JSONException e) {
            e.printStackTrace();
        }
        ComplaintsData = Data_Model_Complaints.fromJson(object1, object2);

        this.emp_id = emp_id;
        this.first_name = first_name;
        this.last_name = last_name;
        context = c;
        parent = a;
        is_seen_domain = domain;
        global = ((Globals) a.getApplication());
        serverAddress = URLs.SERVER_ADDR;
        this.priority = priority;
        myQueue = global.getVolleyQueue();
        Log.e("domain",is_seen_domain);



    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public CardView cardView;
        public TextView title;
        public TextView description;
        public TextView postedBy;
        public TextView createdAt;
        public TextView registrationNumaber;
        public TextView complaint_id_view;
        public CheckBox is_seen;
        public Button callBtn;
        ImageButton forward;
        public TextView domain_text_view;
        private ImageButton changeDomain;
        ProgressDialog progressDialogNew;
        public ViewHolder(final View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.complaint_title);
            description = (TextView) v.findViewById(R.id.complaint_description);
            postedBy = (TextView) v.findViewById(R.id.complaint_posted_by);
            createdAt = (TextView) v.findViewById(R.id.complaint_created_at);
            cardView = (CardView) v.findViewById(R.id.complaint_card_view);
            registrationNumaber = (TextView)v.findViewById(R.id.reg_no);
            is_seen = (CheckBox)v.findViewById(R.id.is_seen_checkbox);
            callBtn = v.findViewById(R.id.callBtn);
            complaint_id_view = (TextView)v.findViewById(R.id.complaint_id);
            forward = v.findViewById(R.id.forward);
            domain_text_view = (TextView)v.findViewById(R.id.domain);
            changeDomain = v.findViewById(R.id.change_domain);
            progressDialogNew = new ProgressDialog(v.getContext());

        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public Adapter_Complaints_Authority.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        // create a new view
        View v;
        parentView = parent;
        if(is_seen_domain.equals("mentor"))
        {
            v= LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.element_complaints_faculty, parent, false);

        }else
        {
          v=LayoutInflater.from(parent.getContext()).inflate(R.layout.element_auth_complaint,parent,false)   ;
        }

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
        final String contact = item.contact;
        complaint_id = item.complaint_id;
        //Toast.makeText(parent, item.title, Toast.LENGTH_SHORT).show();
        holder.title.setText(item.title);
        holder.createdAt.setText(a);
        holder.postedBy.setText(b);
        holder.description.setText(item.description);
        holder.registrationNumaber.setText(registration);
        holder.complaint_id_view.setText("Complaint id: "+item.complaint_id);
                //cid = String.valueOf(item.complaint_id);
        int is_resolved = item.isresolved;

        int type = item.type;
        Log.e("DOMAIN TYPE",String.valueOf(type));
        String domain = "";
        if(type==0)
        {
            domain = "Domain: Other";
        }else if(type==1)
        {
            domain = "Domain: Hostel";
        }else if(type==2)
        {
            domain = "Domain: DSW";
        }else if(type==3)
        {
            domain = "Domain: Placement";
        }else if(type==4)
        {
            domain = "Domain: Exam";
        }
        else if(type==5)
        {
            domain = "Domain: Food";
        }
        else if(type==6)
        {
            domain = "Domain: Academics";
        }
        holder.domain_text_view.setText(domain);


        holder.forward.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             if(item.priority<=item.max_priority){
                 final AlertDialog.Builder builder;
                 if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                     builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
                 } else {
                     builder = new AlertDialog.Builder(context);
                 }
                 builder.setTitle("Pass on Complaint")
                         .setMessage("Are you sure you want to Pass this complaint to higher authority!!")
                         .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                             public void onClick(DialogInterface dialog, int which) {
                                 String url_pass_on = serverAddress.concat("/admin/complaint_pass_on.php")
                                         .concat("?complaint_id=")
                                         .concat(String.valueOf(item.complaint_id));
                                 JsonObjectRequest requestPassOn = new JsonObjectRequest(Request.Method.GET, url_pass_on, null, new Response.Listener<JSONObject>() {

                                     @Override
                                     public void onResponse(JSONObject response) {
                                         Log.e("pass ho gaya na",response.toString());
                                         Toast.makeText(context,"Complaint is passed on to the higher authority",Toast.LENGTH_SHORT).show();
                                     }
                                 }, new Response.ErrorListener() {
                                     @Override
                                     public void onErrorResponse(VolleyError error) {
                                         Toast toast = Toast.makeText(context, "Network Error", duration);
                                         toast.show();
                                     }
                                 });
                                 //Add the first request in the queue
                                 myQueue.add(requestPassOn);
                             }
                         })
                         .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                             public void onClick(DialogInterface dialog, int which) {
                                 dialog.dismiss();
                             }
                         })
                         .setIcon(android.R.drawable.ic_dialog_alert)
                         .show();
             }else{
                 Toast.makeText(context, "Complaint is at its Highest level possible!", Toast.LENGTH_SHORT).show();
             }
         }
     });
        holder.changeDomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("BUILDIGN DIALOGUE","coming here");
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(parentView.getContext());
            //LayoutInflater layoutInflater =  ((Activity)context).getLayoutInflater()
            String cidText = holder.complaint_id_view.getText().toString();
            final  String cid = cidText.substring(cidText.lastIndexOf(':')+2);


               Log.e("CID",cid);
            View mView = LayoutInflater.from(parent.getApplicationContext()).inflate(R.layout.custom_dialouge_spinner,parentView,false);
            alertBuilder.setTitle("Choose Domain");
                final Spinner mSpinner = mView.findViewById(R.id.spinner_domain);
                List<String> categories = new ArrayList<String>();
                categories.add("Select A Domain");
                categories.add("Hostel");
                categories.add("DSW");
                categories.add("Placement");
                categories.add("Exam");
                categories.add("Food");
                categories.add("Others");

                ArrayAdapter<String> mAdapter = new ArrayAdapter<>(parent.getApplicationContext(),android.R.layout.simple_spinner_item,categories);
                mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSpinner.setAdapter(mAdapter);
                alertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       String selectedItem = mSpinner.getSelectedItem().toString();
                       if(selectedItem.equals("Select A Domain"))
                       {
                           Toast.makeText(context,"Please Select A Domain!",Toast.LENGTH_SHORT).show();
                       }else
                       {
                           String type="" ;
                           if(selectedItem.equals("Hostel"))
                           {
                                type = "1";
                           }else if(selectedItem.equals("Others"))
                           {
                               type ="0";
                           }else if(selectedItem.equals("DSW"))
                           {
                               type ="2";
                           }else if(selectedItem.equals("Placement"))
                           {
                               type ="3";
                           }else if(selectedItem.equals("Exam"))
                           {
                               type ="4";
                           }else if(selectedItem.equals("Food"))
                           {
                               type ="5";
                           }
                           updateDomain(type,cid);
                       }


                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context,"HI",Toast.LENGTH_SHORT).show();
                    }
                }).setView(mView).show();

            }
        });
       holder.callBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(Intent.ACTION_DIAL);
               intent.setData(Uri.parse("tel:"+contact));
               view.getContext().startActivity(intent);
           }
       });

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
//            SharedPreferences myPreferences = context.getSharedPreferences(my_pref,Context.MODE_PRIVATE);
//
//            String is_checked = myPreferences.getString(CHECKED_KEY,null);
//            if(is_checked!=null){
//                holder.is_seen.setChecked(true);
//            }else if(is_checked==null){
//                holder.is_seen.setChecked(false);
//            }
            if(item.positon_seen.isEmpty()==false&&priority==item.priority){
                holder.is_seen.setChecked(true);
            }else{
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
//                            SharedPreferences.Editor editor = context.getSharedPreferences(my_pref,Context.MODE_PRIVATE).edit();
//                            editor.putString(CHECKED_KEY,"Not Null");
//                            editor.apply();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast toast = Toast.makeText(context, error.toString(), duration);
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
                        Intent intent = new Intent(v.getContext(),ComplaintsAuthorityActivity.class);
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
                        bundle.putString("emp_id",emp_id);
                        bundle.putString("first_name",first_name);
                        bundle.putString("last_name",last_name);
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
    public void updateDomain(String type,String cid){
        //int cid = complaint_id;
        final ProgressDialog progressDialog = new ProgressDialog(context);

       final String url_update_domain = serverAddress.concat("/public/mentor_change_domain.php?complaint_id=").concat(cid)
                .concat("&domain=").concat(type);
       Log.e("URL_DOMAIN",url_update_domain);
        class UpdateAsync extends AsyncTask<Void,Void,Void>{
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog.setMessage("Updating domain!");
                progressDialog.show();
            }

            @Override
            protected Void doInBackground(Void... voids) {
                JsonObjectRequest newRequest = new JsonObjectRequest(Request.Method.GET, url_update_domain, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            progressDialog.dismiss();
                            Toast.makeText(context,jsonObject.getString("msg"),Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });
                myQueue.add(newRequest);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                ((Activity)context).finish();


            }
        }
        UpdateAsync updateAsync = new UpdateAsync();
        updateAsync.execute();

    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return ComplaintsData.size();
    }

}
