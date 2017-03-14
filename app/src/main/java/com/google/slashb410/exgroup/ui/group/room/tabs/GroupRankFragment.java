package com.google.slashb410.exgroup.ui.group.room.tabs;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.slashb410.exgroup.R;
import com.google.slashb410.exgroup.model.group.group.GroupData;
import com.google.slashb410.exgroup.model.group.group.RankUser;
import com.google.slashb410.exgroup.model.group.group.ResRank;
import com.google.slashb410.exgroup.net.NetSSL;
import com.google.slashb410.exgroup.ui.group.room.GroupHomeActivity;
import com.google.slashb410.exgroup.util.U;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupRankFragment extends Fragment {

    @BindView(R.id.rank1)
    LinearLayout rank1;
    @BindView(R.id.rank2)
    LinearLayout rank2;
    @BindView(R.id.rank3)
    LinearLayout rank3;
    @BindView(R.id.rank4)
    LinearLayout rank4;
    @BindView(R.id.rank5)
    LinearLayout rank5;

    @BindView(R.id.rank1_nick)
    TextView nick1;
    @BindView(R.id.rank2_nick)
    TextView nick2;
    @BindView(R.id.rank3_nick)
    TextView nick3;
    @BindView(R.id.rank4_nick)
    TextView nick4;
    @BindView(R.id.rank5_nick)
    TextView nick5;

    @BindView(R.id.rank1_profile)
    ImageView profile1;
    @BindView(R.id.rank2_profile)
    ImageView profile2;
    @BindView(R.id.rank3_profile)
    ImageView profile3;
    @BindView(R.id.rank4_profile)
    ImageView profile4;
    @BindView(R.id.rank5_profile)
    ImageView profile5;

    ArrayList<RankUser> rankUsers;

    GroupData groupData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       View view= inflater.inflate(R.layout.fragment_group_rank, container, false);
        ButterKnife.bind(this, view);

        //1.그룹데이터 가져오기
        groupData = ((GroupHomeActivity)getActivity()).groupData;

        //2.랭크 세팅
        setRankList();
        return view;
    }

    private void setRankList() {
        rankUsers = new ArrayList<>();

        //1.랭킹 정보 요청
        Call<ResRank> resRank = NetSSL.getInstance().getGroupImpFactory().rank(groupData.getGroup_id());
        resRank.enqueue(new Callback<ResRank>() {
            @Override
            public void onResponse(Call<ResRank> call, Response<ResRank> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getResultCode() == 1) {
                            //2. 랭킹 정보 박기
                            rankUsers = response.body().getResult().getData();

                            nick1.setText(rankUsers.get(0).getNickname());
                            nick2.setText(rankUsers.get(1).getNickname());

                            Picasso.with(getContext()).load(rankUsers.get(0).getPicUrl()).fit().centerCrop().into(profile1);
                            Picasso.with(getContext()).load(rankUsers.get(1).getPicUrl()).fit().centerCrop().into(profile2);


                            //3. 사람수만큼 visibility 세팅
                            switch (rankUsers.size()) {
                                case 0:
                                    U.getInstance().myLog("groupData.getMaxNum == 0");
                                    break;
                                case 2:

                                    rank3.setVisibility(View.GONE);
                                    rank4.setVisibility(View.GONE);
                                    rank5.setVisibility(View.GONE);

                                    break;
                                case 3:
                                    nick3.setText(rankUsers.get(2).getNickname());
                                    Picasso.with(getContext()).load(rankUsers.get(2).getPicUrl()).fit().centerCrop().into(profile3);

                                    rank4.setVisibility(View.GONE);
                                    rank5.setVisibility(View.GONE);

                                    break;
                                case 4:
                                    nick3.setText(rankUsers.get(2).getNickname());
                                    nick4.setText(rankUsers.get(3).getNickname());
                                    Picasso.with(getContext()).load(rankUsers.get(2).getPicUrl()).fit().centerCrop().into(profile3);
                                    Picasso.with(getContext()).load(rankUsers.get(3).getPicUrl()).fit().centerCrop().into(profile4);

                                    rank5.setVisibility(View.GONE);

                                    break;
                                case 5:
                                    nick3.setText(rankUsers.get(2).getNickname());
                                    nick4.setText(rankUsers.get(3).getNickname());
                                    nick5.setText(rankUsers.get(4).getNickname());
                                    Picasso.with(getContext()).load(rankUsers.get(2).getPicUrl()).fit().centerCrop().into(profile3);
                                    Picasso.with(getContext()).load(rankUsers.get(3).getPicUrl()).fit().centerCrop().into(profile4);
                                    Picasso.with(getContext()).load(rankUsers.get(4).getPicUrl()).fit().centerCrop().into(profile5);

                                    break;

                            }

                        }
                    } else {
                        U.getInstance().myLog("resRank Body is null :" + response.message());
                    }
                } else {
                    U.getInstance().myLog("resRank is not successful : " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ResRank> call, Throwable t) {

            }
        });

    }
}
