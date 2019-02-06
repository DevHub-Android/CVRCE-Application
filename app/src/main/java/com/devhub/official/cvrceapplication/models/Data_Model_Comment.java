package com.devhub.official.cvrceapplication.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Data_Model_Comment {

    public String posted_by;
    public String date;
    public String description;
    public String email;
    public String time_elapsed;

    public Data_Model_Comment(String name, String date, String description, String email, String times_readable) {
        this.posted_by = name;
        this.date = date;
        this.description = description;
        this.email = email;
        this.time_elapsed = times_readable;
    }



    public Data_Model_Comment(JSONObject object1) {
        try {
            String name1 = object1.getString("first_name");
            this.posted_by = name1.concat(" ").concat(object1.getString("last_name"));
            this.description = object1.getString("description");
            this.date = object1.getString("created_at");
            this.time_elapsed = date;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<Data_Model_Comment> fromJson(JSONObject jsonObjects) {


        ArrayList<Data_Model_Comment> gradesData = new ArrayList<Data_Model_Comment>();
        JSONArray object1 = null;
        //JSONArray object2 = null;
        try {
            object1 = jsonObjects.getJSONArray("root");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < object1.length(); i++) {
            try {
                gradesData.add(new Data_Model_Comment(object1.getJSONObject(object1.length()-i-1)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return gradesData;
    }


}