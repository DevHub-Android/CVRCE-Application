package com.devhub.official.cvrceapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.devhub.official.cvrceapplication.models.AuthorityModel;
import com.devhub.official.cvrceapplication.models.MentorModel;

public class SharedPrefEmployee {
    public static final String SHARED_PREF_NAME ="cvrceemployee";
    public static final String KEY_NAME = "keyname";
    public static final String KEY_POSITION = "keypostion";
    public static final String KEY_PRIORITY = "keyprioriyt";
    public static final String KEY_EMPID = "keyempid";
    public static final String KEY_DOMAIN = "keydomain";
    public static SharedPrefEmployee mInstance;
    public static Context mCtx;

    public SharedPrefEmployee(Context context){
        mCtx = context;
    }
    public static  synchronized SharedPrefEmployee getmInstance(Context context){
        if(mInstance==null)
        {
            mInstance = new SharedPrefEmployee(context);
        }
        return  mInstance;

    }
    public void userLogin(AuthorityModel authModel){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_NAME,authModel.getFirst_name());
        editor.putString(KEY_EMPID,authModel.getEmpid());
        editor.putString(KEY_POSITION,authModel.getPosition());
        editor.putInt(KEY_PRIORITY,authModel.getPriority());
        editor.putString(KEY_DOMAIN,authModel.getDomain());
        editor.apply();


    }
    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_EMPID,null)!=null;
    }
    public AuthorityModel getMentor(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return new AuthorityModel(
                sharedPreferences.getString(KEY_EMPID,null),
                sharedPreferences.getString(KEY_POSITION,null),
                sharedPreferences.getString(KEY_DOMAIN,null),
                sharedPreferences.getInt(KEY_PRIORITY,0),
                sharedPreferences.getString(KEY_NAME,null)


        )
                ;

    }
    public void logout(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx,EmployeeLogin.class));
    }
}
