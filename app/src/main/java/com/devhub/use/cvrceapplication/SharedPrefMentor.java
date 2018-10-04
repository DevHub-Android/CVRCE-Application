package com.devhub.use.cvrceapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.devhub.use.cvrceapplication.models.MentorModel;

public class SharedPrefMentor {
    public static final String SHARED_PREF_NAME ="cvrcementor";
    public static final String KEY_NAME = "keyname";
    public static final String KEY_EMPID = "keyempid";
    public static SharedPrefMentor mInstance;
    public static Context mCtx;

   public SharedPrefMentor(Context context){
        mCtx = context;
   }
   public static  synchronized SharedPrefMentor getmInstance(Context context){
       if(mInstance==null)
       {
           mInstance = new SharedPrefMentor(context);
       }
       return  mInstance;

   }
   public void userLogin(MentorModel mentorModel){
       SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
       SharedPreferences.Editor editor = sharedPreferences.edit();
       editor.putString(KEY_NAME,mentorModel.getName());
       editor.putString(KEY_EMPID,mentorModel.getEmpid());
       editor.apply();


   }
   public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_EMPID,null)!=null;
    }
    public MentorModel getMentor(){
       SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
       return new MentorModel(
               sharedPreferences.getString(KEY_EMPID,null),
               sharedPreferences.getString(KEY_NAME,null)

       )
       ;

    }
    public void logout(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx,MentorLogin.class));
    }
}
