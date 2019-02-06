package com.devhub.official.cvrceapplication;

import android.database.sqlite.SQLiteTableLockedException;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.devhub.official.cvrceapplication.WebFragments.AdminResolved;
import com.devhub.official.cvrceapplication.WebFragments.AdminUnresolved;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        viewPager = findViewById(R.id.container);
        setUpViewPager(viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }
    public void setUpViewPager(ViewPager viewPager){
        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager());
        viewPageAdapter.addFrag(new AdminResolved(),"Resolved");
        viewPageAdapter.addFrag(new AdminUnresolved(),"Unresolved");
        viewPager.setAdapter(viewPageAdapter);
    }
    class  ViewPageAdapter extends FragmentPagerAdapter{

        private  final List<Fragment> mFramentList = new ArrayList<>();
        private  final List<String> mFragmentTitle = new ArrayList<>();

        public ViewPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFramentList.get(position);
        }
        public void addFrag(Fragment fragment,String title){
            mFramentList.add(fragment);
            mFragmentTitle.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            if(position==0)
            {
                return "Resolved Complaints";
            }else
            {
                return "Unresolved Complaints";
            }
        }

        @Override
        public int getCount() {
            return mFramentList.size();
        }
    }
}
