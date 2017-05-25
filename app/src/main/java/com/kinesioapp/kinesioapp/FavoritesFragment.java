package com.kinesioapp.kinesioapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class FavoritesFragment extends android.app.Fragment implements View.OnClickListener {

    public static final String POSITION_KEY = "FragmentPositionKey";
    private int position;

    public static FavoritesFragment newInstance(Bundle args) {
        FavoritesFragment fragment = new FavoritesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        position = getArguments().getInt(POSITION_KEY);

        View root = inflater.inflate(R.layout.fragment_favorites, container, false);

        TextView textview = (TextView) root.findViewById(R.id.textViewPosition);
        textview.setText(Integer.toString(position));
        textview.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(v.getContext(), "Clicked Position: " + position, Toast.LENGTH_LONG).show();
    }
}
