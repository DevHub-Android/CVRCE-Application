package com.android.devhub.use.cvrceapplication;

import android.os.HandlerThread;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 7/24/2018.
 */

public class RequestHandler {
    public String sendPostRequest(String requestUrl , HashMap<String, String>postDataParams){
        URL url;
        StringBuilder sb = new StringBuilder();
        try{
            url = new URL(requestUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setReadTimeout(15000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream os = httpURLConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
            writer.write(getPostDataString(postDataParams));
            writer.flush();
            writer.close();
            os.close();
            int responseCode = httpURLConnection.getResponseCode();
            if(responseCode== HttpURLConnection.HTTP_OK)
            {
                BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                sb = new StringBuilder();
                String response;
                while((response=br.readLine())!=null){
                    sb.append(response);
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        Log.e("Response",sb.toString());
        return sb.toString();
    }
    public String getPostDataString(HashMap<String,String> params) throws UnsupportedEncodingException{
        StringBuilder result = new StringBuilder();
        boolean first = false;
        for(Map.Entry<String,String> entry : params.entrySet())
        {
            if(first)
            {
                first=false;
            }else{
                result.append("&");
                result.append(URLEncoder.encode(entry.getKey(),"UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(entry.getValue(),"UTF-8"));
            }
        }
        Log.e("URL",result.toString());
        return result.toString();

    }
}
