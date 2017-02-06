package com.google.slashb410.exgroup.ui.group.room.tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.slashb410.exgroup.R;

/**
 * Created by Tacademy on 2017-02-02.
 */

public class GroupPlanetFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_group_planet, container, false);
    }
}
