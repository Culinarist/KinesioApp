package com.kinesioapp.kinesioapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Locale;

public class InjuriesFragment extends Fragment {
    public static final String INJURY_NUMBER = "planet_number";

    public InjuriesFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_injuries, container, false);
        int i = getArguments().getInt(INJURY_NUMBER);
        String injury = getResources().getStringArray(R.array.menu_array)[i];

        int imageId = getResources().getIdentifier(injury.toLowerCase(Locale.getDefault()),
                "drawable", getActivity().getPackageName());
        ((ImageView) rootView.findViewById(R.id.image)).setImageResource(imageId);
        getActivity().setTitle(injury);
        return rootView;
    }

}
