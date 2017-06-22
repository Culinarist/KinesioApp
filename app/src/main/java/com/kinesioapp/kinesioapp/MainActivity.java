package com.kinesioapp.kinesioapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
    @BindView(R.id.left_drawer) ExpandableListView drawerList;
    @BindView(R.id.drawer_content_layout) LinearLayout drawerContent;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindArray(R.array.menu_array) String[] titles;

    private ActionBarDrawerToggle drawerToggle;
    private CharSequence drawerTitle;
    private CharSequence currentTitle;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String,List<String>> listHash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupDrawerLayout();

        // setup ActionBar
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        initData();
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listHash);
        drawerList.setAdapter(listAdapter);

        drawerList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                if (listAdapter.getChildrenCount(i) == 0) {
                    selectItem(i, 0);
                }
                return false;
            }
        });

        drawerList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                selectItem(i, i1);
                return false;
            }
        });

        if (savedInstanceState == null) {
            selectItem(0, 0);
        }
    }

    private void setupDrawerLayout() {
        currentTitle = drawerTitle = getTitle();

        // set a custom shadow that overlays the main content when the drawer opens
        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        drawerLayout.setStatusBarBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.string.drawer_open,
                R.string.drawer_close
        ) {
            public void onDrawerClosed(View view) {
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle(currentTitle);
                }
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle(drawerTitle);
                }
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        drawerLayout.addDrawerListener(drawerToggle);
    }

    /** Swaps fragments in the main content view */
    private void selectItem(int parent, int child) {
        // Create a new fragment
        Fragment fragment;
        Bundle args = new Bundle();

        // Create fragment manager
        FragmentManager fragmentManager = getFragmentManager();

        if (parent == 0) {
            fragment = new FrontPageFragment();

            // Insert the fragment by replacing any existing fragment
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        } else {
            fragment = new InjuriesFragment();
            args.putInt(InjuriesFragment.INJURY_NUMBER, parent);
            fragment.setArguments(args);

            // Insert the fragment by replacing any existing fragment and also add fragment to back stack
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(null).commit();
        }

        // Highlight the selected item, update the currentTitle, and close the drawer
        drawerList.setItemChecked(parent, true);
        setTitle(titles[parent]);
        drawerLayout.closeDrawer(drawerContent);
    }

    @Override
    public void setTitle(CharSequence title) {
        this.currentTitle = title;
        if (getSupportActionBar() != null ) {
            getSupportActionBar().setTitle(this.currentTitle);
        }
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

    private void initData() {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        Collections.addAll(listDataHeader, titles);

        List<String> edmtDev = new ArrayList<>();
        edmtDev.add("This is Expandable ListView");

        List<String> androidStudio = new ArrayList<>();
        androidStudio.add("Expandable ListView");
        androidStudio.add("Google Map");
        androidStudio.add("Chat Application");
        androidStudio.add("Firebase ");

        List<String> xamarin = new ArrayList<>();
        xamarin.add("Xamarin Expandable ListView");
        xamarin.add("Xamarin Google Map");
        xamarin.add("Xamarin Chat Application");
        xamarin.add("Xamarin Firebase ");

        List<String> uwp = new ArrayList<>();
        uwp.add("UWP Expandable ListView");
        uwp.add("UWP Google Map");
        uwp.add("UWP Chat Application");
        uwp.add("UWP Firebase ");

        List<String> empty = new ArrayList<>();

        listHash.put(listDataHeader.get(0),empty);
        listHash.put(listDataHeader.get(1),empty);
        listHash.put(listDataHeader.get(2),empty);
        listHash.put(listDataHeader.get(3),empty);
        listHash.put(listDataHeader.get(4),androidStudio);
        listHash.put(listDataHeader.get(5),xamarin);
        listHash.put(listDataHeader.get(6),uwp);
        listHash.put(listDataHeader.get(7),edmtDev);
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        return drawerToggle.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        int count = getFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            super.onBackPressed();
        } else {
            getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }
}