package com.android.devhub.use.cvrceapplication;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.devhub.use.cvrceapplication.models.UserModel;

import org.json.JSONObject;

import java.util.HashMap;

public class AddComplaint extends AppCompatActivity {
    EditText entDes,entTittle;
    Spinner type;
    RadioGroup postTo;
    String typeInString;
    Button register;
    String typeInInt;
    ProgressDialog progressDialog;
    String typeOfComplaint[] ={"Individual","Hostel","Institute"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_complaint);
        postTo = findViewById(R.id.postTo);
        type = (Spinner) findViewById(R.id.type);
        register = findViewById(R.id.addComplaints);
        entTittle = findViewById(R.id.entTittle);
        entDes = findViewById(R.id.entDes);
        progressDialog = new ProgressDialog(this);
      // type.setOnIt  emSelectedListener((AdapterView.OnItemSelectedListener) this);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,typeOfComplaint);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(aa);
        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                typeInString = type.getSelectedItem().toString();
               if(typeInString.equals("Individual"))
               {
                   typeInInt="0";
               }else if(typeInString.equals("Hostel"))
                {
                    typeInInt="1";
                }else
               {
                   typeInInt="2";
               }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String facultyVisible="1",studentVisible="0";
                int id = postTo.getCheckedRadioButtonId();
                if(R.id.radio_term1==id)
                {
                    studentVisible="1";
                }else
                {
                    studentVisible="0";
                }
                UserModel userModel = SharedPrefManager.getInstance(getApplicationContext()).getUser();

               addComplaint(String.valueOf(userModel.getRegid()),entDes.getText().toString(),typeInInt,studentVisible,facultyVisible,
                       entTittle.getText().toString());
            }
        });


    }
    public void addComplaint(final String reg_id, final String description, final String type,
                             final String student_vis, final String faculty_vis
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
                params.put("student_vis",student_vis);
                params.put("faculty_vis",faculty_vis);
                params.put("title",title);
                return requestHandler.sendPostRequest(URLs.URL_COMPLAINT,params);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressDialog.dismiss();
                try{
                    JSONObject response = new JSONObject(s);
                    if(response.getBoolean("error")){
                        Toast.makeText(getApplicationContext(),response.getString("message"),Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),response.getString("message"),Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        SubmitComplaint su  = new SubmitComplaint();
        su.execute();
    }
}
