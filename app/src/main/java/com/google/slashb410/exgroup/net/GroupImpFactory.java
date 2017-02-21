package com.google.slashb410.exgroup.net;

import com.google.slashb410.exgroup.model.group.group.ResGroupList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Tacademy on 2017-02-21.
 */

public interface GroupImpFactory {

    //D_2. 가입 그룹 리스트 불러오기
    @GET("groups")
    Call<ResGroupList> groupList();
}
