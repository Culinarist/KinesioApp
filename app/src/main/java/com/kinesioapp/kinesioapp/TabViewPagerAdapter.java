package com.kinesioapp.kinesioapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;

public class TabViewPagerAdapter extends FragmentPagerAdapter {

    private Context context;


    public TabViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle args = new Bundle();
        if (position == 0) {
            return FavoritesFragment.newInstance(args);
        } else {
            return RecentFragment.newInstance(args);
        }
    }

    @Override
    public String getPageTitle(int position) {
        if (position == 0) {
            return context.getResources().getString(R.string.favorites);
        } else {
            return context.getResources().getString(R.string.last_viewed);
        }
    }
}