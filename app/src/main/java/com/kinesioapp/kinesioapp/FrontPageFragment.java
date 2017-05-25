package com.kinesioapp.kinesioapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FrontPageFragment extends Fragment {

    @BindView(R.id.viewPager) ViewPager viewPager;
    @BindView(R.id.tabLayout) TabLayout tabLayout;

    public FrontPageFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_front_page, container, false);

        getActivity().setTitle(R.string.app_name);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

        // Give the TabLayout the ViewPager
        tabLayout.setupWithViewPager(viewPager);
    }

    public static class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public Fragment getItem(int position) {
            Bundle args = new Bundle();
            args.putInt(FavoritesFragment.POSITION_KEY, position);
            return FavoritesFragment.newInstance(args);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Child Fragment " + position;
        }

    }

}
