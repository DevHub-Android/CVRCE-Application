package com.devhub.use.cvrceapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.devhub.use.cvrceapplication.models.UserModel;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText editregId,editpass,editcnfPass;
    private ProgressDialog progressDialog;
    Button regBtn;


    Spinner mSpinner;
    String mBranch;//branch for the student-user

    private RadioGroup radioGroup;
    private CircleImageView studentImageView,teacherImageView,parentImageView;

    FrameLayout studentFrameLayout, teacherFrameLayout,parentFrameLayout;


    LinearLayout linearLayout;
    String livesInHostel;

    //userType may be of student-type,teacher-type,parent-type
     String userType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(this);
        setContentView(R.layout.activity_register_new);

        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);

        mSpinner = findViewById(R.id.spinnerBranch);


      mSpinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);



        linearLayout = findViewById(R.id.linearLayout);

        editregId = (EditText)findViewById(R.id.regId);

        editpass = (EditText)findViewById(R.id.pass);
        editcnfPass = (EditText)findViewById(R.id.cnfpass);
        regBtn = (Button)findViewById(R.id.register);









        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Select Your Branch");
        categories.add("CHEM");
        categories.add("CE");
        categories.add("CSE");
        categories.add("EE");
        categories.add("ECE");
        categories.add("IT");
        categories.add("ME");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(dataAdapter);




        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //getting the boolean livesInHostel

                int selectedId = radioGroup.getCheckedRadioButtonId();
                //selectedId gives the id of the selected radio button
                if(R.id.radio_term1 == selectedId)
                {
                    //lives in hostel

                    livesInHostel ="yes";


                }
                else
                {
                    //doesn't live in hostel
                    livesInHostel="no";

                }





                register(editregId.getText().toString(),
                        editpass.getText().toString(),editcnfPass.getText().toString());
            }
        });

    }
    public void register(final String regId,final String pass, String cnfPass){
       if(TextUtils.isEmpty(regId)){
        editregId.setError("Enter Registration ID");
        editregId.requestFocus();
        return;

       }

        if(TextUtils.isEmpty(pass)){
            editpass.setError("Enter Password");
            editpass.requestFocus();
            return;

        }
        if(TextUtils.isEmpty(cnfPass)){
            editcnfPass.setError("Retype Password");
            editcnfPass.requestFocus();
            return;

        }if(!pass.equals(cnfPass)){
            editcnfPass.setError("Password Doesn't Match");
            editcnfPass.requestFocus();
            return;
        }
        if(mBranch.equals("Select Your Branch"))
        {
            Toast.makeText(getApplicationContext(),"Please Select Your Branch",Toast.LENGTH_SHORT).show();
            return;
        }
        class RegistetUser extends AsyncTask<Void,Void,String>{

            @Override
            protected String doInBackground(Void... voids) {
                RequestHandler requestHandler = new RequestHandler();
                HashMap<String,String> params = new HashMap<>();
                params.put("reg_id",regId);
                params.put("password",pass);
                params.put("branch",mBranch);
                params.put("hostel",livesInHostel);
                return requestHandler.sendPostRequest(URLs.URL_REGISTER,params);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog.setMessage("Registering...");
                progressDialog.show();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressDialog.dismiss();

                try{
                    JSONObject jsonObject = new JSONObject(s);
                    if(!jsonObject.getBoolean("error"))
                    {
                        Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(),"User Registered Successfully!" +
                                "Check Your Registered Email To Confirm Registration",Toast.LENGTH_LONG);
//                        JSONObject userJson = jsonObject.getJSONObject("user");
//                        UserModel user = new UserModel(userJson.getString("reg_id"),
//                          userJson.getString("username"),
//                          userJson.getString("firstname"),userJson.getString("lastname"),userJson.getString("email"),userJson.getString("branch"),userJson.getString("hostel"));
//                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
//                        finish();
//                        startActivity(new Intent(getApplicationContext(),ProfileActivity.class));

                    }else{
                        Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_SHORT).show();

                    }


                }catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        }
        RegistetUser ru = new RegistetUser();
        ru.execute();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        // On selecting a spinner item ..
        mBranch = parent.getItemAtPosition(position).toString();
        ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);

    }
}
