package com.devhub.use.cvrceapplication.Adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.devhub.use.cvrceapplication.Fragments.AuthoritySolved;
import com.devhub.use.cvrceapplication.Fragments.AuthorityUnsolved;

public class AuthorityPagerAdapter extends FragmentPagerAdapter {
    public AuthorityPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position)
        {
            case 0:
                fragment = new AuthorityUnsolved();
                break;
            case 1 :
                fragment = new AuthoritySolved();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:
                return "Unsolved Complaints";
            case 1:
                return  "Solved Complaints";
        }
        return null;
    }
}
