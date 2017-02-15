package com.google.slashb410.exgroup.ui.group.room.tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.slashb410.exgroup.R;
import com.google.slashb410.exgroup.db.E;
import com.google.slashb410.exgroup.model.group.ShotData;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.slashb410.exgroup.db.E.KEY.shotData;

/**
 * Created by Tacademy on 2017-02-02.
 */

public class GroupShotsFragment extends Fragment {

    LinearLayoutManager linearLayoutManager;

    ArrayList<ShotData> results = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        ButterKnife.bind(getActivity());

        //==========FAKE DATA

        shotData = new ShotData(0, 1, "ss", "슬비의닉네임", "2017년 2월 5일", "줄넘기 500번 30분", "오랜만에 운동했더니 좀만해도 피곤하다", "000", 100, 2, false);
        results.add(0, shotData);

        shotData = new ShotData(1, 2, "ss", "슬비의닉네임", "2017년 2월 5일", "사과 1개 시리얼 한사발", "아 벌써 배고프다", "000", 30, 5, false);
        results.add(1, shotData);

        if(E.KEY.new_write.getNickName()!=null){
            shotData = new ShotData(2, E.KEY.new_write.getBoardType(), "123", E.KEY.new_write.getNickName(), E.KEY.new_write.getDateNTime(),
                    E.KEY.new_write.getSummary(), E.KEY.new_write.getContent(), "pic", 0, 0, false);
            results.add(2, shotData);
        }
        //====================

        View view = inflater.inflate(R.layout.fragment_group_shots, container, false);

        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.shot_recyclerview);
        ShotsAdapter shotsAdapter = new ShotsAdapter(getContext(), results);
        shotsAdapter.notifyDataSetChanged();

        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(shotsAdapter);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

}
