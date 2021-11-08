package com.nihalramtripathi.typesnews;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdaptor extends FragmentPagerAdapter {

    int tabcount ;

    public PagerAdaptor( FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.tabcount = behavior;
    }



    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0 :
                return new HomeFragment();

            case 1 :
                return new SportsFragment();
            case 2 :
                return new TechnologyFragment();
            case 3 :
                return new HealthFragment();
            case 4 :
                return new EntertainmentFragment();

            case 5 :
                return new BusinessFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabcount;
    }
}
