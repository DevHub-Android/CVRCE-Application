package com.devhub.official.cvrceapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.devhub.official.cvrceapplication.WebFragments.Admission;
import com.devhub.official.cvrceapplication.WebFragments.GalleryFragment;
import com.devhub.official.cvrceapplication.WebFragments.GrievanceHome;
import com.devhub.official.cvrceapplication.WebFragments.HomePage;
import com.devhub.official.cvrceapplication.WebFragments.NoticeFragment;
import com.devhub.official.cvrceapplication.WebFragments.ResearchAndDevelopement;
import com.devhub.official.cvrceapplication.WebFragments.ResultFragment;

public class webHome_mainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView mTextMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_home_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_id);
        setSupportActionBar(toolbar);


        //mTextMessage = (TextView) findViewById(R.id.message);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.black));
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        GrievanceHome grievanceHome = new GrievanceHome();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.webview_id, grievanceHome).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_gallery) {
            // mTextMessage.setText("nav_gallery");
            GalleryFragment fragment = new GalleryFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.webview_id, fragment).commit();
        } else if(id == R.id.student_login){

            GrievanceHome fragment = new GrievanceHome();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.webview_id, fragment).commit();
        }else if(id == R.id.Faculty_Login){
            HomePage fragment = new HomePage();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.webview_id, fragment).commit();
        }else if(id == R.id.admin_Login)
        {
            NoticeFragment fragment = new NoticeFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.webview_id, fragment).commit();
        }
        else if(id==R.id.developers)
        {
            startActivity(new Intent(getApplicationContext(),Developers.class));
        }
        else if(id==R.id.hod_Login)
        {
            Admission fragment = new Admission();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.webview_id, fragment).commit();
        }
        else if(id==R.id.result)
        {
            ResultFragment fragment = new ResultFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.webview_id, fragment).commit();
        }else if(id==R.id.nav_rnd)
        {
            ResearchAndDevelopement fragment = new ResearchAndDevelopement();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.webview_id, fragment).commit();
        }else if(id==R.id.admin)
        {
            startActivity(new Intent(getApplicationContext(),AdminLogin.class));
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
