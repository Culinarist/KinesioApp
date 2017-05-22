package com.kinesioapp.kinesioapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FrontPageFragment extends Fragment {

    public FrontPageFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_front_page, container, false);

        getActivity().setTitle(R.string.app_name);
        return rootView;
    }

}
