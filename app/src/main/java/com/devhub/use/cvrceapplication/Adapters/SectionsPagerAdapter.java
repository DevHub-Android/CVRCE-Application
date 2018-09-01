package com.devhub.use.cvrceapplication.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.devhub.use.cvrceapplication.Fragments.FragmentHostel;
import com.devhub.use.cvrceapplication.Fragments.FragmentIndividual;
import com.devhub.use.cvrceapplication.Fragments.FragmentInstitute;
import com.devhub.use.cvrceapplication.Fragments.FragmentNotifications;

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
                fragment = new FragmentIndividual();
                break;
            case 1:
                fragment = new FragmentHostel();
                break;
            case 2:
                fragment = new FragmentInstitute();
                break;
        }
        return fragment;
    }


    @Override
    public int getCount() {
        // Show 4 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "My Complains";
            case 1:
                return "Hostel Complains";
            case 2:
                return "Institute Complains";
        }
        return null;
    }

}
