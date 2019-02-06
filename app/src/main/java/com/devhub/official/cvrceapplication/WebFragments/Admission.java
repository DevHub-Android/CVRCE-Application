package com.devhub.official.cvrceapplication.WebFragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.devhub.official.cvrceapplication.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Admission extends Fragment {

WebView mWebView;

    public Admission() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.content_main, container, false);
        mWebView = (WebView) v.findViewById(R.id.webview_id);
        mWebView.loadUrl("http://googleweblight.com/i?u=http://cvrce.edu.in/AdmissionEnquiryForm.php");
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
               // progressBar.setVisibility(View.GONE);
                mWebView.loadUrl("javascript:(function() { " +
                        "var head = document.getElementsByClassName('wl-hdr')[0].style.display='none'; " +
                        "})()");
            }
        });
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        mWebView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String s, String s1, String s2, String s3, long l) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(s)));
            }
        });

        return v;
    }

}
