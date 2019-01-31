package com.devhub.use.cvrceapplication;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.devhub.use.cvrceapplication.models.UserModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class AddComplaint extends AppCompatActivity {
    EditText entDes,entTittle;
    Spinner type;
    RadioGroup postTo;
    String typeInString;
    Button submit;
    String typeInInt;
    ProgressDialog progressDialog;
    String f_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grievance_form);

        type = (Spinner) findViewById(R.id.type);
        submit = findViewById(R.id.submit);
        entTittle = findViewById(R.id.entTittle);
        entDes = findViewById(R.id.entDes);
        progressDialog = new ProgressDialog(this);
        //Bundle bundle = getIntent().getExtras();
        f_name = getIntent().getExtras().getString("name");
      // type.setOnIt  emSelectedListener((AdapterView.OnItemSelectedListener) this);
        ArrayAdapter aa = ArrayAdapter.createFromResource(this,R.array.typeOfProblem,android.R.layout.simple_spinner_item);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(aa);
        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                typeInString = type.getSelectedItem().toString();
               if(typeInString.equals("Other"))
               {
                   typeInInt="0";
               }else if(typeInString.equals("Hostel"))
                {
                    typeInInt="1";
                }else if(typeInString.equals("DSW"))
               {
                   typeInInt="2";
               }else if(typeInString.equals("Placement"))
               {
                   typeInInt="3";
               }else if(typeInString.equals("Exam"))
               {
                   typeInInt="4";
               }else if(typeInString.equals("Food"))
               {
                   typeInInt="5";
               }
               else if(typeInString.equals("Academics"))
               {
                   typeInInt="6";
               }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(TextUtils.isEmpty(entTittle.getText()))
            {
                entTittle.setError("Enter Title");
                entTittle.requestFocus();
                return;
            }else if(TextUtils.isEmpty(entDes.getText()))
            {
                entDes.setError("Enter Description");
                entDes.requestFocus();
                return;
            }


                UserModel userModel = SharedPrefManager.getInstance(getApplicationContext()).getUser();

               addComplaint(String.valueOf(userModel.getRegid()),entDes.getText().toString(),typeInInt,
                       entTittle.getText().toString());
            }
        });


    }
    public void addComplaint(final String reg_id, final String description, final String type

                             , final String title){
     Log.e("In here","addcom");
        class SubmitComplaint extends AsyncTask<Void,Void,String>{
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog.setMessage("Adding Complaint....");
                progressDialog.show();

            }

            @Override
            protected String doInBackground(Void... voids) {
                RequestHandler requestHandler = new RequestHandler();
                HashMap<String,String> params = new HashMap<>();
                params.put("reg_id",reg_id);
                params.put("description",description);
                params.put("type",type);
                params.put("title",title);
                params.put("name",f_name);
                return requestHandler.sendPostRequest(URLs.URL_COMPLAINT,params);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressDialog.dismiss();
                try{
                    Log.e("Possible Error",s);
                    JSONObject response = new JSONObject(s);
                    if(response.getBoolean("error")){
                        Toast.makeText(getApplicationContext(),response.getString("message"),Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),response.getString("message"),Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }
        SubmitComplaint su  = new SubmitComplaint();
        su.execute();
    }
}
