package com.android.devhub.use.cvrceapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.android.devhub.use.cvrceapplication.models.UserModel;


/**
 * Created by user on 7/24/2018.
 */

public class SharedPrefManager {
    private static  final String SHARED_PREF_NAME="cvrceapplicationref";
    private static final String KEY_USERNAME="keyuser";
    private static final String KEY_REGID="keyregid";
    private static SharedPrefManager mInstance;
    private static Context mCtx;
    private  SharedPrefManager (Context context){
        mCtx = context;
    }
    public static synchronized SharedPrefManager getInstance(Context context){
        if(mInstance==null)
        {
            mInstance = new SharedPrefManager(context);
        }
        return  mInstance;
    }
    public void userLogin(UserModel user){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_REGID,user.getRegid());
        editor.putString(KEY_USERNAME,user.getUsername());
        editor.apply();

    }
    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_REGID,null)!=null;
    }
    public UserModel getUser(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return new UserModel(
                sharedPreferences.getString(KEY_REGID,null),
                sharedPreferences.getString(KEY_USERNAME,null)
        );
    }
    public void logout(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx,MainActivity.class));
    }
}