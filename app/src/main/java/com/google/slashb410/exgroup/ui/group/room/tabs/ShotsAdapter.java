package com.google.slashb410.exgroup.ui.group.room.tabs;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.slashb410.exgroup.R;
import com.google.slashb410.exgroup.model.group.ShotData;

import java.util.ArrayList;

/**
 * Created by Tacademy on 2017-02-03.
 */

public class ShotsAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<ShotData> results;


    public ShotsAdapter(Context context, ArrayList<ShotData> results) {
        this.context = context;
        this.results = results;
    }

    @Override
    public ShotsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_shot_card, parent, false);
        return new ShotsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ((ShotsHolder)holder).bindOnCard(results.get(position));


    }

    @Override
    public int getItemCount() {
        return this.results.size();
    }
}

