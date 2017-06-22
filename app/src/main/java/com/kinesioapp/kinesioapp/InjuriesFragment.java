package com.kinesioapp.kinesioapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class InjuriesFragment extends Fragment {

    @BindView(R.id.favoriteButtonId) ToggleButton favoriteButton;

    private String favoriteOne;

    public static final String INJURY_NUMBER = "planet_number";

    public InjuriesFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_injuries, container, false);
        ButterKnife.bind(this, rootView);

        int i = getArguments().getInt(INJURY_NUMBER);
        String injury = getResources().getStringArray(R.array.menu_array)[i];

        int imageId = getResources().getIdentifier(injury.toLowerCase(Locale.getDefault()),
                "drawable", getActivity().getPackageName());
        ((ImageView) rootView.findViewById(R.id.image)).setImageResource(imageId);
        getActivity().setTitle(injury);

        return rootView;
    }

    @OnCheckedChanged(R.id.favoriteButtonId)
    public void toggleFavorite(boolean isChecked) {
        if (isChecked) {
            Toast.makeText(getActivity(),"REMOVE!",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(),"ADD!",Toast.LENGTH_SHORT).show();
        }

    }

}
