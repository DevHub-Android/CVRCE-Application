package com.android.devhub.use.cvrceapplication.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.android.devhub.use.cvrceapplication.Fragments.FragmentHostel;
import com.android.devhub.use.cvrceapplication.Fragments.FragmentIndividual;
import com.android.devhub.use.cvrceapplication.Fragments.FragmentInstitute;
import com.android.devhub.use.cvrceapplication.Fragments.FragmentNotifications;

public class SectionsPagerAdapter extends FragmentPagerAdapter{
    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new FragmentNotifications();
                break;
            case 1:
                fragment = new FragmentIndividual();
                break;
            case 2:
                fragment = new FragmentHostel();
                break;
            case 3:
                fragment = new FragmentInstitute();
                break;
        }
        return fragment;
    }


    @Override
    public int getCount() {
        // Show 4 total pages.
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "My Notifications";
            case 1:
                return "My Complains";
            case 2:
                return "Hostel Complains";
            case 3:
                return "Institute Complains";
        }
        return null;
    }

}
