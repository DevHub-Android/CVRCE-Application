package com.android.devhub.use.cvrceapplication;

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

import com.android.devhub.use.cvrceapplication.models.UserModel;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText editregId,edituname,editpass,editcnfPass;
    private ProgressDialog progressDialog;
    Button regBtn;

    Spinner mSpinner;
    String mBranch;//branch for the student-user

    private RadioGroup radioGroup;
    private CircleImageView studentImageView,teacherImageView,parentImageView;

    FrameLayout studentFrameLayout,teacherFrameLayout,parentFrameLayout;


    LinearLayout linearLayout;
    boolean livesInHostel;

    //userType may be of student-type,teacher-type,parent-type
     String userType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(this);
        setContentView(R.layout.activity_register);

        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);

        mSpinner = findViewById(R.id.spinner);


        mSpinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        studentFrameLayout = findViewById(R.id.studentFrameLayout);
        teacherFrameLayout = findViewById(R.id.teacherFrameLayout);
        parentFrameLayout = findViewById(R.id.parentFrameLayout);


        studentImageView = findViewById(R.id.studentCircularImageView);
        teacherImageView = findViewById(R.id.teacherCircularImageView);
        parentImageView =findViewById(R.id.parentCircularImageView);

        linearLayout = findViewById(R.id.linearLayout);

        editregId = (EditText)findViewById(R.id.regId);
        edituname = (EditText)findViewById(R.id.uname);
        editpass = (EditText)findViewById(R.id.pass);
        editcnfPass = (EditText)findViewById(R.id.cnfpass);
        regBtn = (Button)findViewById(R.id.register);


        studentImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentFrameLayout.setBackgroundResource(R.drawable.border_circular_image);
                editregId.setHint("Registration ID");

                teacherFrameLayout.setBackgroundResource(0);
                parentFrameLayout.setBackgroundResource(0);
                linearLayout.setVisibility(View.VISIBLE);

                userType="student";

            }
        });

        teacherImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editregId.setHint("User ID");

                teacherFrameLayout.setBackgroundResource(R.drawable.border_circular_image);
                studentFrameLayout.setBackgroundResource(0);
                parentFrameLayout.setBackgroundResource(0);
                linearLayout.setVisibility(View.INVISIBLE);
                userType="teacher";

            }
        });

        parentImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editregId.setHint("User ID");
                parentFrameLayout.setBackgroundResource(R.drawable.border_circular_image);
                teacherFrameLayout.setBackgroundResource(0);
                studentFrameLayout.setBackgroundResource(0);
                linearLayout.setVisibility(View.INVISIBLE);

                userType="parent";
            }
        });




        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
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

                    livesInHostel =true;


                }
                else
                {
                    //doesn't live in hostel
                    livesInHostel=false;

                }





                register(editregId.getText().toString(),edituname.getText().toString(),
                        editpass.getText().toString(),editcnfPass.getText().toString());
            }
        });

    }
    public void register(final String regId, final String uname,final String pass, String cnfPass){
       if(TextUtils.isEmpty(regId)){
        editregId.setError("Enter Registration ID");
        editregId.requestFocus();
        return;

       }
        if(TextUtils.isEmpty(uname)){
            edituname.setError("Enter User name");
            edituname.requestFocus();
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

        }
        class RegistetUser extends AsyncTask<Void,Void,String>{

            @Override
            protected String doInBackground(Void... voids) {
                RequestHandler requestHandler = new RequestHandler();
                HashMap<String,String> params = new HashMap<>();
                params.put("reg_id",regId);
                params.put("username",uname);
                params.put("password",pass);
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
                        JSONObject userJson = jsonObject.getJSONObject("user");
                        UserModel user = new UserModel(userJson.getString("reg_id"),userJson.getString("username"));
                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
                        finish();
                        startActivity(new Intent(getApplicationContext(),ProfileActivity.class));

                    }else{
                        Toast.makeText(getApplicationContext(),"Something Went Wrong",Toast.LENGTH_SHORT).show();
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
        ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
