package com.devhub.official.cvrceapplication.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.devhub.official.cvrceapplication.Fragments.FragmentHostel;
import com.devhub.official.cvrceapplication.Fragments.FragmentIndividual;
import com.devhub.official.cvrceapplication.Fragments.FragmentInstitute;
import com.devhub.official.cvrceapplication.Fragments.FragmentNotifications;
import com.devhub.official.cvrceapplication.Fragments.MentorFragmentHostel;
import com.devhub.official.cvrceapplication.Fragments.MentorFragmentIndividual;
import com.devhub.official.cvrceapplication.Fragments.MentorFragmentInstitute;

public class MentorSectionsPagerAdapter extends FragmentPagerAdapter {
    public MentorSectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new MentorFragmentIndividual();
                break;
            case 1:
                fragment = new MentorFragmentHostel();
                break;
            case 2:
                fragment = new MentorFragmentInstitute();
                break;
        }
        return fragment;
    }


    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Individual Complains";
            case 1:
                return "Hostel Complains";
            case 2:
                return "Institute Complains";
        }
        return null;
    }
}
