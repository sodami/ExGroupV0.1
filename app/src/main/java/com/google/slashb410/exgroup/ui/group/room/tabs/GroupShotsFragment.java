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
import com.google.slashb410.exgroup.model.group.group.BoardData;
import com.google.slashb410.exgroup.model.group.group.ResBoardList;
import com.google.slashb410.exgroup.net.NetSSL;
import com.google.slashb410.exgroup.ui.group.room.GroupHomeActivity;
import com.google.slashb410.exgroup.util.U;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Tacademy on 2017-02-02.
 */

public class GroupShotsFragment extends Fragment {

    LinearLayoutManager linearLayoutManager;

    ArrayList<BoardData> boardDatas;
    RecyclerView recyclerView;
    ShotsAdapter shotsAdapter;

    ResBoardList boardList;
    int groupId;
    String groupId_str;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_group_shots, container, false);

        groupId = ((GroupHomeActivity)getActivity()).groupId;
        groupId_str = String.valueOf(groupId);

        //게시글 내용 불러오기
        setBoardList(groupId_str);

        recyclerView = (RecyclerView) view.findViewById(R.id.shot_recyclerview);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    private void setBoardList(String groupId) {
        Call<ResBoardList> resBoardList = NetSSL.getInstance().getGroupImpFactory().boardList(groupId);
        resBoardList.enqueue(new Callback<ResBoardList>() {
            @Override
            public void onResponse(Call<ResBoardList> call, Response<ResBoardList> response) {
                if(response.body()==null) {
                    U.getInstance().myLog("setGroupList body is NULL");
                    return;
                }
                if(response.body().getBoardList().size()!=0) {
                    boardList = response.body();
                    U.getInstance().myLog("boardList Size : "+boardList.getBoardList().size());

                    boardDatas = boardList.getBoardList();

                    shotsAdapter = new ShotsAdapter(getContext(), boardDatas);
                    shotsAdapter.notifyDataSetChanged();

                    linearLayoutManager = new LinearLayoutManager(getContext());
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(shotsAdapter);


                }
            }

            @Override
            public void onFailure(Call<ResBoardList> call, Throwable t) {
                U.getInstance().myLog("접근실패 : "+t.toString());
            }
        });
    }

}
