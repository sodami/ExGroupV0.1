package com.google.slashb410.exgroup.net;

import com.google.slashb410.exgroup.model.group.ResStandard;
import com.google.slashb410.exgroup.model.group.group.ReqMakeGroup;
import com.google.slashb410.exgroup.model.group.group.ResGroupList;
import com.google.slashb410.exgroup.model.group.group.ResGroupSearch;
import com.google.slashb410.exgroup.model.group.group.ResGroupWaitingMember;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Tacademy on 2017-02-21.
 */

public interface GroupImpFactory {

    //B_1. 그룹 생성하기
//    @Multipart
//    @POST("groups")
//    Call<ResStandard> makeGroup (@Body ReqMakeGroup reqMakeGroup,
//                                 @Part("photo")MultipartBody.Part file);
    @POST("groups")
    Call<ResStandard> makeGroup (@Body ReqMakeGroup reqMakeGroup);


    //B_2. 그룹 검색하기
    @GET("groups/{searchId}")
    Call<ResGroupSearch> searchGroup (@Path("searchId") String searchId);

    //C_3. 그룹에 가입했지만 인증하지 않은 사람 나타내기
    @GET("groups/{groupid}/uncertifiedMem")
    Call<ResGroupWaitingMember> waitingMember(@Path("groupid") String groupId);

    //D_2. 가입 그룹 리스트 불러오기
    @GET("groups")
    Call<ResGroupList> groupList();
}
