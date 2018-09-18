package com.devhub.use.cvrceapplication.WebFragments;

import android.content.Context;
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

import com.devhub.use.cvrceapplication.R;


public class ResearchAndDevelopement extends Fragment {
    WebView mWebView;
    public ResearchAndDevelopement() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.content_main, container, false);
        mWebView = (WebView) v.findViewById(R.id.webview_id);
        //mWebView.loadUrl("http://googleweblight.com/?lite_url=http://cvrce.edu.in/");
        mWebView.loadUrl("http://googleweblight.com/i?u=http://www.cvrce.edu.in/research-development.php");
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());
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