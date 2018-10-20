package com.example.durgesh.goverment_jobs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class myPageAdapter extends FragmentPagerAdapter {

    List<String> listString;
    List<Fragment> listFragement;


    public myPageAdapter(FragmentManager supportFragmentManager, List<String> listString, List<Fragment> listFragement) {
        super(supportFragmentManager);

        this.listString = listString;
        this.listFragement = listFragement;
    }

    @Override
    public Fragment getItem(int position) {
        return listFragement.get(position);
    }

    @Override
    public int getCount() {
        return listString.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listString.get(position);
    }
}
